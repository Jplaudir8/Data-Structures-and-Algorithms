// WARNING: This program uses the Assertion class. When it is run,
// assertions must be turned on. For example, under Linux, use:
// java -ea GenBinaryDataFile

/**
 * Generate a data file. The size is a multiple of 8192 bytes.
 * Each record is one long and one double.
 */
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
// import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
// import java.math.*;
import java.util.Random;

/**
 * 
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class GenBinaryDataFile {

    /**
     * Constant: short ints
     */
    static final int NUMRECS = 512;

    // Holds the Random class object
    static private Random value = new Random();

    /**
     * Returns long values
     * 
     * @return long value
     */
    static long randLong() {
        return value.nextLong();
    }


    /**
     * Returns double values
     * 
     * @return double value
     */
    static double randDouble() {
        return value.nextDouble();
    }


    /**
     * The main method that generates input binary file
     * 
     * @param args
     *            string input file
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        long val;
        double val2;
        assert (args.length == 2) : "\nUsage: "
            + "GenBinaryDataFile <filename> <size>"
            + "\nOptions \nSize is measured in blocks of 8192 bytes";

        int filesize = Integer.parseInt(args[1]); // Size of file in blocks
        DataOutputStream file = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(args[0])));

        for (int i = 0; i < filesize; i++) {
            for (int j = 0; j < NUMRECS; j++) {
                val = (long)(randLong());
                file.writeLong(val);
                val2 = (double)(randDouble());
                file.writeDouble(val2);
            }
        }

        file.flush();
        file.close();
    }

}
