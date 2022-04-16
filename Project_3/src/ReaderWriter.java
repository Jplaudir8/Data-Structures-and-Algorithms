import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class represents the process of
 * reading and writing the binary file
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class ReaderWriter {

    private long fileOffset = 0;
    private int recLen = 0;
    private long fileLength = 0;
    private long totalRecords = 0;
    private int blockLen = 0;
    private long totalBlocks = 0;
    private long currentBlock = 0;
    private RandomAccessFile random;

    /**
     * Constructor for ReaderWriter class
     * 
     * @param inputFile
     *            input file
     * @param recordLength
     *            length of each record
     * @param blockLength
     *            length of block of records
     */
    public ReaderWriter(String inputFile, int recordLength, int blockLength) {
        try {
            random = new RandomAccessFile(inputFile, "rw");
            fileLength = random.length();
            recLen = recordLength;
            blockLen = blockLength;
            totalRecords = fileLength / recLen;
            totalBlocks = fileLength / (recLen * blockLen);
            if (fileLength % (recLen * blockLen) != 0) {
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
     * This method gets a record from binary file
     * 
     * @param record
     *            byte array that holds the record
     * @param offset
     *            offset of the record to get
     * @return Status of the read process
     * 
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
     * This methods returns the next block from the binary file
     * 
     * @param block
     *            byte array that holds the block
     * @return status of the read process
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
     * This method gets a block from binary file
     * 
     * @param block
     *            byte array that holds the block
     * @param offset
     *            offset of the block
     * @param length
     *            length of the block
     * @return # of records read from the file
     */
    public int getBlock(byte[][] block, long offset, int length) {
        int recordCount = 0;
        fileOffset = offset;
        try {
            for (int i = 0; i < length; i++) {
                if (fileOffset > totalRecords * recLen) {
                    break;
                }
                random.seek(fileOffset);
                random.read(block[i]);
                fileOffset = random.getFilePointer();
                recordCount++;
            }
            if (length == blockLen) {
                currentBlock++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return recordCount;
    }


    /**
     * Returns file statistics
     * 
     * @return array with total bytes, total records, total blocks
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
     * Returns the end offset of binary file
     * 
     * @return end offset
     */
    public long getEndOffset() {
        return fileLength;
    }


    /**
     * This method writes the records in binary file
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
     * This method writes records at the given offset
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
     * Close the open binary file
     */
    public void closeFile() {
        try {
            random.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method makes copy records between files
     * 
     * @param binaryFile
     *            source binary file
     * @param offset
     *            copy offset
     * @param length
     *            number of record to copy
     */
    public void copy(ReaderWriter binaryFile, long offset, int length) {
        try {
            byte[] b = new byte[length];
            if (binaryFile.getRecord(b, offset) == 0) {
                random.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns file statistics
     * 
     * @return array with total bytes, total records, total blocks
     */
    public long[] getFileStats() {
        long[] stats = { fileLength, totalRecords, totalBlocks };
        return stats;
    }


    /**
     * Deletes the binary file
     * 
     * @param fileName
     *            File to be deleted
     */
    public static void deleteIfAny(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
