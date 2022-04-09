import java.util.ArrayList;
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * 
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class MultiwayMerge {
    
    
    private static final int BLOCK_RECORDS = 512;
    private static final int RECORD_SIZE = 16;
    private static final int MAX_WORKING_BLOCKS = 8;
    private Record[] workingMemory;
    private Record[] inputBuffer;
    private Record[] outputBuffer;
    private BinaryFileOperator runfile;
    private BinaryFileOperator binaryFile;
    private String runFileName = "runfile";
    private ArrayList<Run> runList;
    
    /**
     * Replacement Selection Constructor
     * 
     * @param binFile
     *            binary file to be sorted
     */
    public MultiwayMerge(String binFile) {
        binaryFile = new BinaryFileOperator(binFile, RECORD_SIZE, BLOCK_RECORDS);
        workingMemory = new Record[MAX_WORKING_BLOCKS * BLOCK_RECORDS];
        inputBuffer = new Record[BLOCK_RECORDS];
        outputBuffer = new Record[BLOCK_RECORDS];
        runList = new ArrayList<Run>();
    }
    
    /**
     * Service to fill the records in input buffer from binary file
     * 
     * @param fileDesc
     *            Binary file descriptor
     * @return Status of read operation
     */
    private int fillInputBuffer(BinaryFileOperator fileDesc) {
        byte[][] byteBlock = new byte[BLOCK_RECORDS][RECORD_SIZE];
        int readStatus = -1;
        if ((fileDesc != null) && (inputBuffer != null)) {
            readStatus = fileDesc.getNextBlock(byteBlock);
        }
        if (readStatus != -1) {
            for (int i = 0; i < BLOCK_RECORDS; i++) {
                inputBuffer[i] = new Record(byteBlock[i]);
            }
        }
        return readStatus;
    }
    
    /**
     * Service to fill the records in input buffer from binary file
     * 
     * @param fileDesc
     *            Binary file descriptor
     * @param offset
     *            offset in binary file
     * @param lenBlk
     *            length of block to read
     * @return number of records read into input buffer
     */
    private int fillInputBufferFrom(
        BinaryFileOperator fileDesc,
        long offset,
        int lenBlk) {
        byte[][] byteBlock = new byte[BLOCK_RECORDS][RECORD_SIZE];
        int numRecords = 0;
        if ((fileDesc != null) && (inputBuffer != null)) {
            numRecords = fileDesc.getBlockFrom(byteBlock, offset, lenBlk);
        }
        if (numRecords != 0) {
            for (int i = 0; i < numRecords; i++) {
                inputBuffer[i] = new Record(byteBlock[i]);
            }
        }
        return numRecords;
    }
    
    /**
     * Run Replacement Selection to generate runs
     */
    public void runReplacementSelection() {
        int o = 0;
        int i = inputBuffer.length;
        int nextRunElements = 0;
        int runLength = 0;
        int fillStatus = 0;
        long prevOffset = 0;
        BinaryFileOperator.deleteIfExists(runFileName);
        runfile = new BinaryFileOperator(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        for (int k = 0; k < MAX_WORKING_BLOCKS; k++) {
            fillInputBuffer(binaryFile);
            System.arraycopy(inputBuffer, 0, workingMemory, k * BLOCK_RECORDS,
                BLOCK_RECORDS);
        }
        MinHeap<Record> heap = new MinHeap<Record>(workingMemory,
            workingMemory.length, workingMemory.length);
        do {
            while (!heap.isEmpty()) {
                runLength++;
                if (o == outputBuffer.length) {
                    for (int k = 0; k < outputBuffer.length; k++) {
                        runfile.writeRecord(outputBuffer[k]
                            .getCompleteRecord());
                    }
                    o = 0;
                }
                if (i == inputBuffer.length) {
                    fillStatus = fillInputBuffer(binaryFile);
                    i = 0;
                }
                if (fillStatus != -1) {
                    outputBuffer[o] = heap.getMin();
                    if (inputBuffer[i].compareTo(outputBuffer[o]) > 0) {
                        heap.insertAtRoot(inputBuffer[i]);
                    }
                    else {
                        heap.replaceRoot(inputBuffer[i]);
                        nextRunElements++;
                    }
                    i++;
                }
                else {
                    outputBuffer[o] = heap.removeMin();
                }
                o++;
            }
            if (o > 0) {
                for (int k = 0; k < o; k++) {
                    runfile.writeRecord(outputBuffer[k].getCompleteRecord());
                }
                o = 0;
            }
            runList.insert(new Run(runLength, prevOffset));
            prevOffset = runfile.getFileOffset();
            if (nextRunElements != 0) {
                System.arraycopy(workingMemory, workingMemory.length
                    - nextRunElements, workingMemory, 0, nextRunElements);
                heap.setHeapSize(nextRunElements);
                heap.buildheap();
                nextRunElements = 0;
                runLength = 0;
            }
        }
        while (!heap.isEmpty());
        runfile.closeFile();
    }
    
    /**
     * Get the index of smallest record in an array
     * 
     * @param recArray
     *            array of records
     * @return index of smallest record
     */
    private int findSmallestRecordIndex(Record[] recArray) {
        int minIndex = 0;
        Record min = recArray[0];
        int p = 0;
        while (p < recArray.length) {
            if (recArray[p] != null) {
                min = recArray[p];
                minIndex = p;
                break;
            }
            p++;
        }
        for (int i = minIndex + 1; i < recArray.length; i++) {
            if ((recArray[i] != null) && (min.compareTo(recArray[i]) > 0)) {
                min = recArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    
    
    /**
     * Merge runs using working memory
     * 
     * @param runInfo
     *            runs to be merged
     * @param runs
     *            number of runs to be merged
     */
    private void mergeRuns(Run[] runInfo, int runs) {
        BinaryFileOperator rf = new BinaryFileOperator(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        Record[] runElements = new Record[runs];
        int[] blkPtr = new int[runs];
        int[] recCnt = new int[runs];
        int readCnt = 0;
        int effectiveRecordSize = 0;
        int blkSize = 0;
        long prevOffset = rf.getEndOffset();
        long tempOffset = prevOffset;
        for (int l = 0; l < runs; l++) {
            blkSize = (runInfo[l].blockCount > 1)
                ? BLOCK_RECORDS
                : runInfo[l].runLength;
            readCnt = fillInputBufferFrom(rf, runInfo[l].runFileOffset,
                blkSize);
            System.arraycopy(inputBuffer, 0, workingMemory, l * BLOCK_RECORDS,
                readCnt);
            effectiveRecordSize = effectiveRecordSize + runInfo[l].runLength;
            runInfo[l].runLength = runInfo[l].runLength - readCnt;
            runInfo[l].runFileOffset = rf.getFileOffset();
            runInfo[l].blockCount -= 1;
            recCnt[l] = readCnt;
            runElements[l] = workingMemory[l * BLOCK_RECORDS];
            blkPtr[l] = 0;
        }
        int runSize = effectiveRecordSize;
        int o = 0;
        while (effectiveRecordSize > 0) {
            if (o == outputBuffer.length) {
                for (int k = 0; k < outputBuffer.length; k++) {
                    rf.writeRecord(outputBuffer[k].getCompleteRecord(),
                        tempOffset);
                    tempOffset += RECORD_SIZE;
                }
                o = 0;
            }
            int idx = findSmallestRecordIndex(runElements);
            outputBuffer[o] = runElements[idx];
            o++;
            blkPtr[idx] = blkPtr[idx] + 1;
            for (int i = 0; i < runs; i++) {
                if ((blkPtr[i] == recCnt[i]) && (runInfo[i].blockCount > 0)) {
                    blkSize = (runInfo[i].blockCount > 1)
                        ? BLOCK_RECORDS
                        : runInfo[i].runLength;
                    readCnt = fillInputBufferFrom(rf, runInfo[i].runFileOffset,
                        blkSize);
                    System.arraycopy(inputBuffer, 0, workingMemory, i
                        * BLOCK_RECORDS, readCnt);
                    runInfo[i].runLength = runInfo[i].runLength - readCnt;
                    runInfo[i].runFileOffset = rf.getFileOffset();
                    runInfo[i].blockCount -= 1;
                    recCnt[i] = readCnt;
                    blkPtr[i] = 0;
                }
                else if ((blkPtr[i] == recCnt[i])
                    && (runInfo[i].blockCount == 0)) {
                    runElements[i] = null;
                }
            }
            if (runElements[idx] != null) {
                runElements[idx] = workingMemory[idx * BLOCK_RECORDS
                    + blkPtr[idx]];
            }
            effectiveRecordSize--;
        }
        if (o > 0) {
            for (int k = 0; k < o; k++) {
                rf.writeRecord(outputBuffer[k].getCompleteRecord(), tempOffset);
                tempOffset += RECORD_SIZE;
            }
            o = 0;
        }
        runList.insert(new Run(runSize, prevOffset));
        rf.closeFile();
    }


    /**
     * Run Multiway merge for all the runs generated by replacement selection
     */
    public void runMultiwayMerge() {
        Run[] runInfo = new Run[MAX_WORKING_BLOCKS];
        while (runList.getListLength() > 1) {
            int i = 0;
            while ((!runList.isEmpty()) && (i != MAX_WORKING_BLOCKS)) {
                runInfo[i] = runList.pop();
                i++;
            }
            mergeRuns(runInfo, i);
        }
        Run finalRecord = runList.pop();
        String outFileName = "outfile";
        BinaryFileOperator.deleteIfExists(outFileName);
        BinaryFileOperator outfile = new BinaryFileOperator(outFileName,
            RECORD_SIZE, BLOCK_RECORDS);
        runfile = new BinaryFileOperator(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        outfile.copyFromFile(runfile, finalRecord.runFileOffset,
            finalRecord.runLength * RECORD_SIZE);
        outfile.closeFile();
        runfile.closeFile();
    }

}
