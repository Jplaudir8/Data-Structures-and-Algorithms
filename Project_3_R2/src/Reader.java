import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
 * The Reader class manages the process of reading records
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class Reader {
	private long fileOffset = 0;
	private int recordLength = 0;
	private long fileLength = 0;
	private long totalRecords = 0;
	private int blockLen = 0;
	private long totalBlocks = 0;
	private long currentBlock = 0;
	private RandomAccessFile random;

	public Reader(String inFile, int recordLength, int blockLength) {
		try {
			random = new RandomAccessFile(inFile, "r");
			fileLength = random.length();
			this.recordLength = recordLength;
			blockLen = blockLength;
			totalRecords = fileLength / recordLength;
			totalBlocks = fileLength / (recordLength * blockLen);
			if (fileLength % (recordLength * blockLen) != 0) {
				totalBlocks += 1;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Method to retrieve a record from binary file
     * 
     * @param record
     *            byte array to hold the record
     * @param offset
     *            offset of the record to be retrieved
     * @return Status of the read operation
     */
    public int getRecord(byte[] record, long offset) {
        try {
            if (offset < fileLength) {
                random.seek(offset);
                random.read(record);
            }
            else {
                return -1;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    /**
     * Method to retrieve the block from binary file
     * 
     * @param block
     *            byte array to hold the block
     * @param offset
     *            offset of the block
     * @param len
     *            length of the block
     * @return number of records read from the file
     */
    public int getBlockFrom(byte[][] block, long offset, int len) {
        int recordCnt = 0;
        fileOffset = offset;
        try {
            for (int i = 0; i < len; i++) {
                if (fileOffset > totalRecords * recordLength) {
                    break;
                }
                random.seek(fileOffset);
                random.read(block[i]);
                fileOffset = random.getFilePointer();
                recordCnt++;
            }
            if (len == blockLen) {
                currentBlock++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return recordCnt;
    }
    
    /**
     * Method to retrieve the next block from the binary file
     * 
     * @param block
     *            byte array to hold the block
     * @return status of the read operation
     */
    public int getNextBlock(byte[][] block) {
        if ((currentBlock == totalBlocks) || (block.length != blockLen)) {
            closeFile();
            return -1;
        }
        try {
            for (int i = 0; i < blockLen; i++) {
                random.seek(fileOffset);
                random.read(block[i]);
                fileOffset = random.getFilePointer();
            }
            currentBlock++;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Get the current offset in binary file
     * 
     * @return current file offset
     */
    public long getFileOffset() {
        try {
            return random.getFilePointer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * Get the end offset of binary file
     * 
     * @return end offset
     */
    public long getEndOffset() {
        return fileLength;
    }
    
    
    /**
     * Get file statistics
     * 
     * @return array containing total bytes, total records, total blocks
     */
    public long[] getFileStats() {
        long[] stats = { fileLength, totalRecords, totalBlocks };
        return stats;
    }
	
	
	
	/**
	 * Close the binary file that is open
	 */
	public void closeFile() {
		try {
			random.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
