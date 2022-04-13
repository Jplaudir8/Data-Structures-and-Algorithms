import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

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
 * The Writer class will write records being read
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class Writer {
	private long fileOffset = 0;
	private int recordLength = 0;
	private long fileLength = 0;
	private long totalRecords = 0;
	private int blockLen = 0;
	private long totalBlocks = 0;
	private long currentBlock = 0;
	private RandomAccessFile random;

	public Writer(String inFile, int recordLength, int blockLength) {
		try {
			random = new RandomAccessFile(inFile, "w");
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
     * Method to copy records between files
     * 
     * @param ip
     *            source binary file
     * @param offset
     *            copy offset
     * @param len
     *            number of record to copy
     */
    public void copyFromFile(Reader ip, long offset, int len) {
        try {
            byte[] b = new byte[len];
            if (ip.getRecord(b, offset) == 0) {
                random.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to write records in binary file
     * 
     * @param data
     *            byte array that holds the data
     */
    public void writeRecord(byte[] data) {
        try {
            random.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    
    /**
     * Method to write records at particular offset
     * 
     * @param data
     *            byte array that holds the data
     * @param offset
     *            write offset
     */
    public void writeRecord(byte[] data, long offset) {
        try {
            random.seek(offset);
            random.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete the binary file
     * 
     * @param fileName
     *            File to be deleted
     */
    public static void deleteIfExists(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Close the binary file that is open
     */
    public void closeFile() {
        try {
            random.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
