
/**
 * Multiway Merge and Replacement Selection Functions
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class MultiwayMerge {

    private static final int MAX_WORKING_BLOCKS = 8;
    private static final int RECORD_SIZE = 16;
    private static final int BLOCK_RECORDS = 512;
    private ReaderWriter binaryFile;
    private Record[] inputBuffer;
    private Record[] outputBuffer;
    private Record[] workingMemory;
    private ReaderWriter runfile;
    private String runFileName = "runfile";
    private LinkedList<Run> runLL;

    /**
     * Run Class
     */
    private class Run {
        private int runLength;
        private long runFileOffset;
        private int blockCount;

        /**
         * Run class constructor
         * 
         * @param length
         *            run length
         * @param offset
         *            run offset
         */
        private Run(int length, long offset) {
            runLength = length;
            runFileOffset = offset;
            blockCount = runLength / BLOCK_RECORDS;
            if (runLength % BLOCK_RECORDS != 0) {
                blockCount += 1;
            }
        }
    }

    /**
     * MultiwayMerge's constructor.
     * 
     * @param bFile
     *            binary file to be sorted
     */
    public MultiwayMerge(String bFile) {
        binaryFile = new ReaderWriter(bFile, RECORD_SIZE, BLOCK_RECORDS);
        workingMemory = new Record[MAX_WORKING_BLOCKS * BLOCK_RECORDS];
        inputBuffer = new Record[BLOCK_RECORDS];
        outputBuffer = new Record[BLOCK_RECORDS];
        runLL = new LinkedList<Run>();
    }


    /**
     * Helper method to fill the records in input buffer
     * 
     * @param file
     *            Binary file
     * @return Status of read process
     */
    private int fillRecInput(ReaderWriter file) {
        byte[][] byteBlock = new byte[BLOCK_RECORDS][RECORD_SIZE];
        int readStatus = -1;
        if ((file != null) && (inputBuffer != null)) {
            readStatus = file.getNextBlock(byteBlock);
        }
        if (readStatus != -1) {
            for (int i = 0; i < BLOCK_RECORDS; i++) {
                inputBuffer[i] = new Record(byteBlock[i]);
            }
        }
        return readStatus;
    }


    /**
     * Helper method to fill the records in input buffer from binary file
     * 
     * @param file
     *            Binary file
     * @param offset
     *            offset in binary file
     * @param lengthBlock
     *            length of block to read
     * @return # of records read into input buffer
     */
    private int fillRecInputFrom(
        ReaderWriter file,
        long offset,
        int lengthBlock) {
        byte[][] btBlock = new byte[BLOCK_RECORDS][RECORD_SIZE];
        int record = 0;
        if ((file != null) && (inputBuffer != null)) {
            record = file.getBlock(btBlock, offset, lengthBlock);
        }
        if (record != 0) {
            for (int i = 0; i < record; i++) {
                inputBuffer[i] = new Record(btBlock[i]);
            }
        }
        return record;
    }


    /**
     * Performs Replacement Selection to produce runs
     */
    public void replacementSelection() {
        int o = 0;
        int i = inputBuffer.length;
        int nextRunElements = 0;
        int runLength = 0;
        int fillStatus = 0;
        long previousOffset = 0;
        ReaderWriter.deleteIfAny(runFileName);
        runfile = new ReaderWriter(runFileName, RECORD_SIZE, BLOCK_RECORDS);
        for (int k = 0; k < MAX_WORKING_BLOCKS; k++) {
            fillRecInput(binaryFile);
            System.arraycopy(inputBuffer, 0, workingMemory, k * BLOCK_RECORDS,
                BLOCK_RECORDS);
        }
        MinHeap<Record> h = new MinHeap<Record>(workingMemory,
            workingMemory.length, workingMemory.length);
        do {
            while (!h.isEmpty()) {
                runLength++;
                if (o == outputBuffer.length) {
                    for (int k = 0; k < outputBuffer.length; k++) {
                        runfile.writeRecord(outputBuffer[k]
                            .getCompleteRecord());
                    }
                    o = 0;
                }
                if (i == inputBuffer.length) {
                    fillStatus = fillRecInput(binaryFile);
                    i = 0;
                }
                if (fillStatus != -1) {
                    outputBuffer[o] = h.getMin();
                    if (inputBuffer[i].compareTo(outputBuffer[o]) > 0) {
                        h.insertAtRoot(inputBuffer[i]);
                    }
                    else {
                        h.replaceRoot(inputBuffer[i]);
                        nextRunElements++;
                    }
                    i++;
                }
                else {
                    outputBuffer[o] = h.removeMin();
                }
                o++;
            }
            if (o > 0) {
                for (int k = 0; k < o; k++) {
                    runfile.writeRecord(outputBuffer[k].getCompleteRecord());
                }
                o = 0;
            }
            runLL.add(new Run(runLength, previousOffset));
            previousOffset = runfile.getFileOffset();
            if (nextRunElements != 0) {
                System.arraycopy(workingMemory, workingMemory.length
                    - nextRunElements, workingMemory, 0, nextRunElements);
                h.setHeapSize(nextRunElements);
                h.buildheap();
                nextRunElements = 0;
                runLength = 0;
            }
        }
        while (!h.isEmpty());
        runfile.closeFile();
    }


    /**
     * Returns the position of smallest record
     * 
     * @param recordArray
     *            array of records
     * @return smallest record
     */
    private int smallestRecord(Record[] recordArray) {
        int minIndex = 0;
        Record min = recordArray[0];
        int p = 0;
        while (p < recordArray.length) {
            if (recordArray[p] != null) {
                min = recordArray[p];
                minIndex = p;
                break;
            }
            p++;
        }
        for (int i = minIndex + 1; i < recordArray.length; i++) {
            if ((recordArray[i] != null) && (min.compareTo(
                recordArray[i]) > 0)) {
                min = recordArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


    /**
     * Performs the merge based on working memory
     * 
     * @param runInfo
     *            runs to be merged
     * @param runs
     *            # of runs to be merged
     */
    private void merge(Run[] runInfo, int runs) {
        ReaderWriter rw = new ReaderWriter(runFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        Record[] runElements = new Record[runs];
        int[] block = new int[runs];
        int[] recordCount = new int[runs];
        int readCount = 0;
        int recSize = 0;
        int blockSize = 0;
        long previousOffset = rw.getEndOffset();
        long tempOffset = previousOffset;
        for (int l = 0; l < runs; l++) {
            blockSize = (runInfo[l].blockCount > 1)
                ? BLOCK_RECORDS
                : runInfo[l].runLength;
            readCount = fillRecInputFrom(rw, runInfo[l].runFileOffset,
                blockSize);
            System.arraycopy(inputBuffer, 0, workingMemory, l * BLOCK_RECORDS,
                readCount);
            recSize = recSize + runInfo[l].runLength;
            runInfo[l].runLength = runInfo[l].runLength - readCount;
            runInfo[l].runFileOffset = rw.getFileOffset();
            runInfo[l].blockCount -= 1;
            recordCount[l] = readCount;
            runElements[l] = workingMemory[l * BLOCK_RECORDS];
            block[l] = 0;
        }
        int runSize = recSize;
        int o = 0;
        while (recSize > 0) {
            if (o == outputBuffer.length) {
                for (int k = 0; k < outputBuffer.length; k++) {
                    rw.writeRecord(outputBuffer[k].getCompleteRecord(),
                        tempOffset);
                    tempOffset += RECORD_SIZE;
                }
                o = 0;
            }
            int index = smallestRecord(runElements);
            outputBuffer[o] = runElements[index];
            o++;
            block[index] = block[index] + 1;
            for (int i = 0; i < runs; i++) {
                if ((block[i] == recordCount[i])
                    && (runInfo[i].blockCount > 0)) {
                    blockSize = (runInfo[i].blockCount > 1)
                        ? BLOCK_RECORDS
                        : runInfo[i].runLength;
                    readCount = fillRecInputFrom(rw, runInfo[i].runFileOffset,
                        blockSize);
                    System.arraycopy(inputBuffer, 0, workingMemory, i
                        * BLOCK_RECORDS, readCount);
                    runInfo[i].runLength = runInfo[i].runLength - readCount;
                    runInfo[i].runFileOffset = rw.getFileOffset();
                    runInfo[i].blockCount -= 1;
                    recordCount[i] = readCount;
                    block[i] = 0;
                }
                else if ((block[i] == recordCount[i])
                    && (runInfo[i].blockCount == 0)) {
                    runElements[i] = null;
                }
            }
            if (runElements[index] != null) {
                runElements[index] = workingMemory[index * BLOCK_RECORDS
                    + block[index]];
            }
            recSize--;
        }
        if (o > 0) {
            for (int k = 0; k < o; k++) {
                rw.writeRecord(outputBuffer[k].getCompleteRecord(), tempOffset);
                tempOffset += RECORD_SIZE;
            }
            o = 0;
        }
        runLL.add(new Run(runSize, previousOffset));
        rw.closeFile();
    }


    /**
     * Performs the multi merge run
     */
    public void runMultiwayMerge() {
        Run[] runInfo = new Run[MAX_WORKING_BLOCKS];
        while (runLL.getLength() > 1) {
            int i = 0;
            while ((!runLL.isEmpty()) && (i != MAX_WORKING_BLOCKS)) {
                runInfo[i] = runLL.pop();
                i++;
            }
            merge(runInfo, i);
        }
        Run finalRecord = runLL.pop();
        String outFileName = "outfile";
        ReaderWriter.deleteIfAny(outFileName);
        ReaderWriter outfile = new ReaderWriter(outFileName, RECORD_SIZE,
            BLOCK_RECORDS);
        runfile = new ReaderWriter(runFileName, RECORD_SIZE, BLOCK_RECORDS);
        outfile.copy(runfile, finalRecord.runFileOffset, finalRecord.runLength
            * RECORD_SIZE);
        outfile.closeFile();
        runfile.closeFile();
    }
}
