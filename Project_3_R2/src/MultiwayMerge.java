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

    private static final int BLOCK_RECORDS = 512;
    private static final int RECORD_SIZE = 16;
    private static final int MAX_WORKING_BLOCKS = 8;
    private Record[] workingMemory;
    private Record[] inputBuffer;
    private Record[] outputBuffer;
    private Writer runfileWr;
    private Reader runfileRd;
    private Reader binaryFile;
    private String runFileName = "runfile";
    private LinkedList<Run> runList;

    /**
     * Constructor that initializes all necessary spaces that will be used to perform 
     * and file objects that will be used for sorting.
     * 
     * @param binFile
     *            binary file to be sorted
     */
    public MultiwayMerge(String binFile) {
        binaryFile = new Reader(binFile, RECORD_SIZE,
            BLOCK_RECORDS);
        workingMemory = new Record[MAX_WORKING_BLOCKS * BLOCK_RECORDS];
        inputBuffer = new Record[BLOCK_RECORDS];
        outputBuffer = new Record[BLOCK_RECORDS];
        runList = new LinkedList<Run>();
    }


    /**
     * Loads records into input buffer
     * 
     * @param fileDesc
     *            Binary file descriptor
     * @return Status of read operation
     */
    private int fillInputBuffer(Reader fileDesc) {
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
     * @param reader
     *            Binary file descriptor
     * @param offset
     *            offset in binary file
     * @param lenBlk
     *            length of block to read
     * @return number of records read into input buffer
     */
    private int fillInputBufferFrom(
        Reader reader,
        Writer writer,
        long offset,
        int lenBlk) {
        byte[][] byteBlock = new byte[BLOCK_RECORDS][RECORD_SIZE];
        int numRecords = 0;
        if ((reader != null) && (inputBuffer != null)) {
            numRecords = reader.getBlockFrom(byteBlock, offset, lenBlk);
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
        runfileWr = new Writer(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        runfileRd = new Reader(runFileName, RECORD_SIZE, BLOCK_RECORDS);
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
                        runfileWr.writeRecord(outputBuffer[k]
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
                    runfileWr.writeRecord(outputBuffer[k].getCompleteRecord());
                }
                o = 0;
            }
            runList.add(new Run(runLength, prevOffset));
            prevOffset = runfileRd.getFileOffset();
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
        runfileWr.closeFile();
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
        Writer writer = new Writer(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        Reader reader = new Reader(runFileName, RECORD_SIZE,
                BLOCK_RECORDS);
        Record[] runElements = new Record[runs];
        int[] blkPtr = new int[runs];
        int[] recCnt = new int[runs];
        int readCnt = 0;
        int effectiveRecordSize = 0;
        int blkSize = 0;
        long prevOffset = reader.getEndOffset();
        long tempOffset = prevOffset;
        for (int l = 0; l < runs; l++) {
            int runInfoLength = runInfo[l].getRunLength();
            long runFileOffset = runInfo[l].getRunFileOffset();
            int blockCount = runInfo[l].getBlockCount();
            blkSize = (blockCount > 1) ? BLOCK_RECORDS : runInfoLength;
            readCnt = fillInputBufferFrom(reader, writer, runFileOffset, blkSize);
            System.arraycopy(inputBuffer, 0, workingMemory, l * BLOCK_RECORDS,
                readCnt);
            effectiveRecordSize = effectiveRecordSize + runInfoLength;

            runInfoLength = runInfoLength - readCnt;
            runFileOffset = reader.getFileOffset();
            blockCount -= 1;
            recCnt[l] = readCnt;
            runElements[l] = workingMemory[l * BLOCK_RECORDS];
            blkPtr[l] = 0;
        }
        int runSize = effectiveRecordSize;
        int o = 0;
        while (effectiveRecordSize > 0) {
            if (o == outputBuffer.length) {
                for (int k = 0; k < outputBuffer.length; k++) {
                    writer.writeRecord(outputBuffer[k].getCompleteRecord(),
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
                if ((blkPtr[i] == recCnt[i]) && (runInfo[i]
                    .getBlockCount() > 0)) {
                    int runInfoLength = runInfo[i].getRunLength();
                    long runFileOffset = runInfo[i].getRunFileOffset();
                    int blockCount = runInfo[i].getBlockCount();
                    blkSize = (blockCount > 1) ? BLOCK_RECORDS : runInfoLength;
                    readCnt = fillInputBufferFrom(reader, writer, runFileOffset, blkSize);
                    System.arraycopy(inputBuffer, 0, workingMemory, i
                        * BLOCK_RECORDS, readCnt);
                    runInfoLength = runInfoLength - readCnt;
                    runFileOffset = reader.getFileOffset();
                    blockCount -= 1;
                    recCnt[i] = readCnt;
                    blkPtr[i] = 0;
                }
                else if ((blkPtr[i] == recCnt[i]) && (runInfo[i]
                    .getBlockCount() == 0)) {
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
                writer.writeRecord(outputBuffer[k].getCompleteRecord(), tempOffset);
                tempOffset += RECORD_SIZE;
            }
            o = 0;
        }
        runList.add(new Run(runSize, prevOffset));
        writer.closeFile();
    }


    /**
     * Run Multiway merge for all the runs generated by replacement selection
     */
    public void runMultiwayMerge() {
        Run[] runInfo = new Run[MAX_WORKING_BLOCKS];
        while (runList.getLength() > 1) {
            int i = 0;
            while ((!runList.isEmpty()) && (i != MAX_WORKING_BLOCKS)) {
                runInfo[i] = runList.pop();
                i++;
            }
            mergeRuns(runInfo, i);
        }
        Run finalRecord = runList.pop();
        String outFileName = "outfile";
        Writer.deleteIfExists(outFileName);
        Writer outfile = new Writer(outFileName,
            RECORD_SIZE, BLOCK_RECORDS);
        runfileRd = new Reader(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        outfile.copyFromFile(runfileRd, finalRecord.getRunFileOffset(),
            finalRecord.getRunLength() * RECORD_SIZE);
        outfile.closeFile();
        runfileRd.closeFile();
    }

}
