import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Writer {
	
	private long fileOffset = 0;
    private int recordLength = 0;
    private long fileLength = 0;
    private long totalRecords = 0;
    private int blockLen = 0;
    private long totalBlocks = 0;
    private long currentBlock = 0;
    private RandomAccessFile random;

    /**
     * Constructor for BinaryFileOperator class
     * 
     * @param inFile
     *            input file
     * @param recordLength
     *            length of each record
     * @param blockLength
     *            length of block of records
     */
    public Writer(
        String inFile,
        int recordLength,
        int blockLength) {
        try {
            random = new RandomAccessFile(inFile, "rw");
            fileLength = random.length();
            recordLength = recordLength;
            blockLen = blockLength;
            totalRecords = fileLength / recordLength;
            totalBlocks = fileLength / (recordLength * blockLen);
            if (fileLength % (recordLength * blockLen) != 0) {
                totalBlocks += 1;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
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
    public void copyFromFile(BinaryFileOperator ip, long offset, int len) {
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

}
