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
 * Represents a merger class that uses replacement selection before the merging process.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class MultiwayMerge {
    
    private static final int RECORDS_PER_BLOCK = 512;
    private static final int BYTES_PER_RECORD = 16;
    private static final int WORKING_SPACE_BLOCKS = 8;
    
    private BinaryFileOperator runfile;
    private BinaryFileOperator binaryFile;
    
    private Record[] inputBuffer;
    private Record[] workingMemory;
    private Record[] outputBuffer;
    
    private String runFileName = "runfile";
    private LinkedList<Run> runTracker;
    
    /**
     * Constructor that initializes all necessary spaces that will be used to perform 
     * and file objects that will be used for sorting.
     * 
     * @param binFile
     *            binary file to be sorted
     */
    public MultiwayMerge(String binFile) {
        binaryFile = new BinaryFileOperator(binFile, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
        inputBuffer = new Record[RECORDS_PER_BLOCK];
        workingMemory = new Record[WORKING_SPACE_BLOCKS * RECORDS_PER_BLOCK];
        outputBuffer = new Record[RECORDS_PER_BLOCK];
        runTracker = new LinkedList<Run>();
    }
    
    /**
     * Loads records into input buffer
     * 
     * @param fileDesc
     *            Binary file descriptor
     * @return Status of read operation
     */
    private int fillInputBuffer(BinaryFileOperator fileDesc) {
        byte[][] blockOfRecords = new byte[RECORDS_PER_BLOCK][BYTES_PER_RECORD];
        int readStatus = -1;
        if ((fileDesc != null) && (inputBuffer != null)) {
            readStatus = fileDesc.extractBlock(blockOfRecords);
        }
        if (readStatus != -1) {
            for (int i = 0; i < RECORDS_PER_BLOCK; i++) {
                inputBuffer[i] = new Record(blockOfRecords[i]);
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
        byte[][] byteBlock = new byte[RECORDS_PER_BLOCK][BYTES_PER_RECORD];
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
        Writer.deleteIfExists(runFileName);
        runfile = new BinaryFileOperator(runFileName, BYTES_PER_RECORD,
        		RECORDS_PER_BLOCK);
        for (int k = 0; k < WORKING_SPACE_BLOCKS; k++) {
            fillInputBuffer(binaryFile);
            System.arraycopy(inputBuffer, 0, workingMemory, k * RECORDS_PER_BLOCK,
            		RECORDS_PER_BLOCK);
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
            runTracker.insert(new Run(runLength, prevOffset));
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
     * Perform merging sub-task to help merging runs. 
     * 
     * @param runInfo
     *            runs to be merged
     * @param runs
     *            number of runs to be merged
     */
    private void mergeRuns(Run[] runInfo, int runs) {
        BinaryFileOperator rf = new BinaryFileOperator(runFileName, BYTES_PER_RECORD,
        		RECORDS_PER_BLOCK);
        Record[] runElements = new Record[runs];
        int[] blkPtr = new int[runs];
        int[] recCnt = new int[runs];
        int readCnt = 0;
        int effectiveRecordSize = 0;
        int blkSize = 0;
        long prevOffset = rf.getEndOffset();
        long tempOffset = prevOffset;
        for (int l = 0; l < runs; l++) {
            blkSize = runInfo[l].blockCount > 1
                ? RECORDS_PER_BLOCK
                : runInfo[l].runLength;
            readCnt = fillInputBufferFrom(rf, runInfo[l].runFileOffset,
                blkSize);
            System.arraycopy(inputBuffer, 0, workingMemory, l * RECORDS_PER_BLOCK,
                readCnt);
            effectiveRecordSize = effectiveRecordSize + runInfo[l].runLength;
            runInfo[l].runLength = runInfo[l].runLength - readCnt;
            runInfo[l].runFileOffset = rf.getFileOffset();
            runInfo[l].blockCount -= 1;
            recCnt[l] = readCnt;
            runElements[l] = workingMemory[l * RECORDS_PER_BLOCK];
            blkPtr[l] = 0;
        }
        int runSize = effectiveRecordSize;
        int o = 0;
        while (effectiveRecordSize > 0) {
            if (o == outputBuffer.length) {
                for (int k = 0; k < outputBuffer.length; k++) {
                    rf.writeRecord(outputBuffer[k].getCompleteRecord(),
                        tempOffset);
                    System.out.println(outputBuffer[k].toString());
                    tempOffset += BYTES_PER_RECORD;
                }
                o = 0;
            }
            int idx = findSmallestRecordIndex(runElements);
            outputBuffer[o] = runElements[idx];
            o++;
            blkPtr[idx] = blkPtr[idx] + 1;
            for (int i = 0; i < runs; i++) {
                if ((blkPtr[i] == recCnt[i]) && (runInfo[i].blockCount > 0)) {
                    blkSize = runInfo[i].blockCount > 1
                        ? RECORDS_PER_BLOCK
                        : runInfo[i].runLength;
                    readCnt = fillInputBufferFrom(rf, runInfo[i].runFileOffset,
                        blkSize);
                    System.arraycopy(inputBuffer, 0, workingMemory, i
                        * RECORDS_PER_BLOCK, readCnt);
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
                runElements[idx] = workingMemory[idx * RECORDS_PER_BLOCK
                    + blkPtr[idx]];
            }
            effectiveRecordSize--;
        }
        if (o > 0) {
            for (int k = 0; k < o; k++) {
                rf.writeRecord(outputBuffer[k].getCompleteRecord(), tempOffset);
                tempOffset += BYTES_PER_RECORD;
            }
            o = 0;
        }
        runTracker.insert(new Run(runSize, prevOffset));
        rf.closeFile();
    }


    /**
     * Perform multi-way merge to unify and sort all runs generated through 
     * replacement selection in working space.
     */
    public void runMultiwayMerge() {
        Run[] runInfo = new Run[WORKING_SPACE_BLOCKS];
        while (runTracker.getListLength() > 1) {
            int i = 0;
            while ((!runTracker.isEmpty()) && (i != WORKING_SPACE_BLOCKS)) {
                runInfo[i] = runTracker.pop();
                i++;
            }
            mergeRuns(runInfo, i);
        }
        Run finalRecord = runTracker.pop();
        String outFileName = "outfile";
        BinaryFileOperator.deleteIfExists(outFileName);
        BinaryFileOperator outfile = new BinaryFileOperator(outFileName,
            BYTES_PER_RECORD, RECORDS_PER_BLOCK);
        runfile = new BinaryFileOperator(runFileName, BYTES_PER_RECORD,
        		RECORDS_PER_BLOCK);
        outfile.copyFromFile(runfile, finalRecord.runFileOffset,
            finalRecord.runLength * BYTES_PER_RECORD);
        outfile.closeFile();
        runfile.closeFile();
    }

}