import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

/**
 * Tests the ExternalSort class.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class ExternalsortTest extends TestCase {
    private ByteArrayOutputStream outputStream;
    private Externalsort exSort;

    /**
     * set up for tests
     */
    public void setUp() {
        exSort = new Externalsort();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }


    /**
     * Tests the main method in external sort
     * 
     * @throws FileNotFoundException
     */
    public void testExternalsort() throws FileNotFoundException {

        assertNotNull(exSort);

        String[] input = { "sampleInput16.bin", "16" };
        try {
            GenBinaryDataFile.main(input);
            String[] args = { "sampleInput16.bin" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String[] input1 = { "sampleInput26.bin", "26" };
        try {
            GenBinaryDataFile.main(input1);
            String[] args = { "sampleInput26.bin" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Test invalid sort
     * 
     * @throws FileNotFoundException
     */
    public void testExternalsortInvalid() throws FileNotFoundException {

        assertNotNull(exSort);
        String[] args = {};
        Externalsort.main(args);

        assertEquals(outputStream.toString().trim(),
            "Invalid command and/or input...");
    }


    /**
     * Test Genfile_proj3 class constructor
     */
    public void testGenFileDataClass() {
        GenBinaryDataFile genBinary = new GenBinaryDataFile();
        assertNotNull(genBinary);
    }

}
