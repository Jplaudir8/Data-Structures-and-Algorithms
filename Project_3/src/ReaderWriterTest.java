import java.io.IOException;
import student.TestCase;

/**
 * Tests for ReadWriter class
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class ReaderWriterTest extends TestCase {

    /**
     * Test 
     */
    public void testReadWriter() {
        String[] input = { "sampleInput8.bin", "8" };
        try {
            GenBinaryDataFile.main(input);
            ReaderWriter bf = new ReaderWriter("sampleInput8.bin",
                16, 512);
            assertNotNull(bf);
            long[] stat = bf.getFileStats();
            assertEquals(8, stat[2]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Test 
     */
    public void testReadWriterB() {
        String[] input = { "sampleInput8.bin", "8" };
        try {
            GenBinaryDataFile.main(input);
            ReaderWriter bf = new ReaderWriter("sampleInput8.bin",
                16, 512);
            assertNotNull(bf);
            long eo = bf.getEndOffset();
            byte[] b = new byte[16];
            int ret = bf.getRecord(b, eo + 16);
            assertEquals(-1, ret);
            byte[][] b1 = new byte[10][16];
            ret = bf.getBlock(b1, eo + 16, 10);
            assertEquals(0, ret);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
