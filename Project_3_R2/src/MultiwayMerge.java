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
 * Represents a merger class that uses replacement selection before the merging
 * phase.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class MultiwayMerge {

	private static final int RECORDS_PER_BLOCK = 512;
	private static final int BYTES_PER_RECORD = 16;
	private static final int WORKING_SPACE_BLOCKS = 8;

	private Record[] inputBuffer;
	private Record[] workingMemory;
	private Record[] outputBuffer;

	private Writer runfileWr;
	private Reader runfileRd;
	private Reader binaryFile;
	private String runFileName = "runfile";
	private LinkedList<Run> runTracker;

	/**
	 * Constructor that initializes all necessary spaces that will be used to
	 * perform and file objects that will be used for sorting.
	 * 
	 * @param bFile binary file to sort
	 */
	public MultiwayMerge(String bFile) {
		binaryFile = new Reader(bFile, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
		inputBuffer = new Record[RECORDS_PER_BLOCK];
		workingMemory = new Record[WORKING_SPACE_BLOCKS * RECORDS_PER_BLOCK];
		outputBuffer = new Record[RECORDS_PER_BLOCK];
		runTracker = new LinkedList<Run>();
	}

	/**
	 * Loads records into input buffer.
	 * 
	 * @param fileDesc Binary file descriptor
	 * @return Status of read operation
	 */
	private int fillInputBuffer(Reader fileDesc) {
		byte[][] blockOfRecords = new byte[RECORDS_PER_BLOCK][BYTES_PER_RECORD];
		int readStatus = -1;
		if ((fileDesc != null) && (inputBuffer != null)) {
			readStatus = fileDesc.getNextBlock(blockOfRecords);
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
	 * @param reader reader file manipulator
	 * @param writer writer file manipulator
	 * @param offset offset of binary file
	 * @param lenBlk size of block to be scanned
	 * @return number of records read into input buffer
	 */
	private int fillInputBufferFrom(Reader reader, Writer writer, long offset, int lenBlk) {
		byte[][] byteBlock = new byte[RECORDS_PER_BLOCK][BYTES_PER_RECORD];
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
	 * Perform Replacement Selection algorithm to generate runs.
	 */
	public void runReplacementSelection() {
		int o = 0;
		int i = inputBuffer.length;
		int nextRunElements = 0;
		int runLength = 0;
		int fillStatus = 0;
		long prevOffset = 0;
		Writer.deleteIfExists(runFileName);
		runfileWr = new Writer(runFileName, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
		runfileRd = new Reader(runFileName, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
		for (int k = 0; k < WORKING_SPACE_BLOCKS; k++) {
			fillInputBuffer(binaryFile);
			System.arraycopy(inputBuffer, 0, workingMemory, k * RECORDS_PER_BLOCK, RECORDS_PER_BLOCK);
		}
		MinHeap<Record> heap = new MinHeap<Record>(workingMemory, workingMemory.length, workingMemory.length);
		do {
			while (!heap.isEmpty()) {
				runLength++;
				if (o == outputBuffer.length) {
					for (int k = 0; k < outputBuffer.length; k++) {
						runfileWr.writeRecord(outputBuffer[k].getCompleteRecord());
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
					} else {
						heap.replaceRoot(inputBuffer[i]);
						nextRunElements++;
					}
					i++;
				} else {
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
			runTracker.add(new Run(runLength, prevOffset));
			prevOffset = runfileRd.getFileOffset();
			if (nextRunElements != 0) {
				System.arraycopy(workingMemory, workingMemory.length - nextRunElements, workingMemory, 0,
						nextRunElements);
				heap.setHeapSize(nextRunElements);
				heap.buildheap();
				nextRunElements = 0;
				runLength = 0;
			}
		} while (!heap.isEmpty());
		runfileWr.closeFile();
	}

	/**
	 * Get the index of smallest record in an array.
	 * 
	 * @param recArray array of records
	 * @return index of smallest record
	 */
	private int getSmallestIndexOf(Record[] recArray) {
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
	 * Perform merging phase of runs of records.
	 * 
	 * @param runInfo runs to be merged
	 * @param runs    number of runs to be merged
	 */
	private void mergeRuns(Run[] runInfo, int runs) {
		Writer writer = new Writer(runFileName, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
		Reader reader = new Reader(runFileName, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
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
			blkSize = (blockCount > 1) ? RECORDS_PER_BLOCK : runInfoLength;
			readCnt = fillInputBufferFrom(reader, writer, runFileOffset, blkSize);
			System.arraycopy(inputBuffer, 0, workingMemory, l * RECORDS_PER_BLOCK, readCnt);
			effectiveRecordSize = effectiveRecordSize + runInfoLength;

			runInfoLength = runInfoLength - readCnt;
			runFileOffset = reader.getFileOffset();
			blockCount -= 1;
			recCnt[l] = readCnt;
			runElements[l] = workingMemory[l * RECORDS_PER_BLOCK];
			blkPtr[l] = 0;
		}
		int runSize = effectiveRecordSize;
		int o = 0;
		while (effectiveRecordSize > 0) {
			if (o == outputBuffer.length) {
				for (int k = 0; k < outputBuffer.length; k++) {
					writer.writeRecord(outputBuffer[k].getCompleteRecord(), tempOffset);
					tempOffset += BYTES_PER_RECORD;
				}
				o = 0;
			}
			int idx = getSmallestIndexOf(runElements);
			outputBuffer[o] = runElements[idx];
			o++;
			blkPtr[idx] = blkPtr[idx] + 1;
			for (int i = 0; i < runs; i++) {
				if ((blkPtr[i] == recCnt[i]) && (runInfo[i].getBlockCount() > 0)) {
					int runInfoLength = runInfo[i].getRunLength();
					long runFileOffset = runInfo[i].getRunFileOffset();
					int blockCount = runInfo[i].getBlockCount();
					blkSize = (blockCount > 1) ? RECORDS_PER_BLOCK : runInfoLength;
					readCnt = fillInputBufferFrom(reader, writer, runFileOffset, blkSize);
					System.arraycopy(inputBuffer, 0, workingMemory, i * RECORDS_PER_BLOCK, readCnt);
					runInfoLength = runInfoLength - readCnt;
					runFileOffset = reader.getFileOffset();
					blockCount -= 1;
					recCnt[i] = readCnt;
					blkPtr[i] = 0;
				} else if ((blkPtr[i] == recCnt[i]) && (runInfo[i].getBlockCount() == 0)) {
					runElements[i] = null;
				}
			}
			if (runElements[idx] != null) {
				runElements[idx] = workingMemory[idx * RECORDS_PER_BLOCK + blkPtr[idx]];
			}
			effectiveRecordSize--;
		}
		if (o > 0) {
			for (int k = 0; k < o; k++) {
				writer.writeRecord(outputBuffer[k].getCompleteRecord(), tempOffset);
				tempOffset += BYTES_PER_RECORD;
			}
			o = 0;
		}
		runTracker.add(new Run(runSize, prevOffset));
		writer.closeFile();
	}

	/**
	 * Run Multiway merge for all the runs generated by replacement selection
	 */
	public void runMultiwayMerge() {
		Run[] runInfo = new Run[WORKING_SPACE_BLOCKS];
		while (runTracker.getLength() > 1) {
			int i = 0;
			while ((!runTracker.isEmpty()) && (i != WORKING_SPACE_BLOCKS)) {
				runInfo[i] = runTracker.pop();
				i++;
			}
			mergeRuns(runInfo, i);
		}
		Run finalRecord = runTracker.pop();
		String outFileName = "outfile";
		Writer.deleteIfExists(outFileName);
		Writer outfile = new Writer(outFileName, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
		runfileRd = new Reader(runFileName, BYTES_PER_RECORD, RECORDS_PER_BLOCK);
		outfile.copyFromFile(runfileRd, finalRecord.getRunFileOffset(), finalRecord.getRunLength() * BYTES_PER_RECORD);
		outfile.closeFile();
		runfileRd.closeFile();
	}

}
