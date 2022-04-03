import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Tests the DNAtree class
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class DNAtreeTest extends TestCase {

    private DNAtree tree;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up test cases.
     */
    public void setUp() {
        tree = new DNAtree();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }


    /**
     * Tests a simple remove method
     * 
     * @throws IOException
     *             true if the test passes
     */
    public void testRemoveSimple() throws IOException {
        Command cm1 = new Command("insert ACGT", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert GCCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("remove ACGT", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("remove GCCA", tree);
        cm4.executeTreeOperation();

        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence GCCA inserted at level 1\n" + "sequence ACGT removed\n"
            + "sequence GCCA removed", outputStream.toString().trim());
    }


    /**
     * Tests a simple insertion
     * 
     * @throws IOException
     *             true if the test passes
     */
    public void testInsertSimple() throws IOException {
        Command cm1 = new Command("insert AAAA", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert GCAT", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("insert CGTAA", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("insert AGTAA", tree);
        cm4.executeTreeOperation();
        Command cm5 = new Command("insert ACTTA", tree);
        cm5.executeTreeOperation();

        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence GCAT inserted at level 1\n"
            + "sequence CGTAA inserted at level 1\n"
            + "sequence AGTAA inserted at level 2\n"
            + "sequence ACTTA inserted at level 2", outputStream.toString()
                .trim());
    }


    /**
     * Tests a complex insertion
     * 
     * @throws IOException
     *             true if the test passes
     */
    public void testInsert1() throws IOException {
        Command cm1 = new Command("insert GATC", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert ACGTCCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("insert ACGTAGGTT", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("insert ACGTCCAT", tree);
        cm4.executeTreeOperation();

        assertEquals("sequence GATC inserted at level 0\n"
            + "sequence ACGTCCA inserted at level 1\n"
            + "sequence ACGTAGGTT inserted at level 5\n"
            + "sequence ACGTCCAT inserted at level 8", outputStream.toString()
                .trim());
    }


    /**
     * Tests a complex remove method
     * @throws IOException
     *             true if the test passes
     */
    public void testRemove1() throws IOException {
        Command cm1 = new Command("insert ACGT", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert GCCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("remove GCCA", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("remove GCCA", tree);
        cm4.executeTreeOperation();

        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence GCCA inserted at level 1\n" + "sequence GCCA removed\n"
            + "sequence GCCA does not exist", outputStream.toString().trim());
    }


    /**
     * Tests a complex remove method
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testRemove2() throws IOException {
        Command cm1 = new Command("insert ACGT", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert CGCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("insert CACA", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("insert CCAG", tree);
        cm4.executeTreeOperation();
        Command cm5 = new Command("insert CTA", tree);
        cm5.executeTreeOperation();
        Command cm6 = new Command("print", tree);
        cm6.executeTreeOperation();
        Command cm7 = new Command("remove CGCA", tree);
        cm7.executeTreeOperation();
        Command cm8 = new Command("remove CTA", tree);
        cm8.executeTreeOperation();
        Command cm9 = new Command("remove CCAG", tree);
        cm9.executeTreeOperation();
        Command cm10 = new Command("print", tree);
        cm10.executeTreeOperation();

        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence CGCA inserted at level 1\n"
            + "sequence CACA inserted at level 2\n"
            + "sequence CCAG inserted at level 2\n"
            + "sequence CTA inserted at level 2\n" + "tree dump:\n" + "I\n"
            + "  ACGT\n" + "  I\n" + "    CACA\n" + "    CCAG\n" + "    CGCA\n"
            + "    CTA\n" + "    E\n" + "  E\n" + "  E\n" + "  E\n"
            + "sequence CGCA removed\n" + "sequence CTA removed\n"
            + "sequence CCAG removed\n" + "tree dump:\n" + "I\n" + "  ACGT\n"
            + "  CACA\n" + "  E\n" + "  E\n" + "  E", outputStream.toString()
                .trim());
    }


    /**
     * Tests a simple search method
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testSearch() throws IOException {
        Command cm1 = new Command("insert ACGT", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert GCCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("print", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("remove ACGT", tree);
        cm4.executeTreeOperation();
        Command cm5 = new Command("print", tree);
        cm5.executeTreeOperation();
        Command cm6 = new Command("search GCCA", tree);
        cm6.executeTreeOperation();

        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence GCCA inserted at level 1\n" + "tree dump:\n" + "I\n"
            + "  ACGT\n" + "  E\n" + "  GCCA\n" + "  E\n" + "  E\n"
            + "sequence ACGT removed\n" + "tree dump:\n" + "GCCA\n"
            + "# of nodes visited: 1\n" + "sequence: GCCA", outputStream
                .toString().trim());
    }


    /**
     * Tests a simple print
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testPrintSimple() throws IOException {
        Command cm1 = new Command("insert CGTA", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert CAAA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("insert CA", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("insert CAAAAAACCTTCAATCGTA", tree);
        cm4.executeTreeOperation();
        Command cm5 = new Command("insert CATGGGAA", tree);
        cm5.executeTreeOperation();
        Command cm6 = new Command("insert TGGCAA", tree);
        cm6.executeTreeOperation();
        Command cm7 = new Command("remove CGTA", tree);
        cm7.executeTreeOperation();
        Command cm8 = new Command("insert CACT", tree);
        cm8.executeTreeOperation();
        Command cm9 = new Command("print", tree);
        cm9.executeTreeOperation();
        Command cm10 = new Command("insert AATA", tree);
        cm10.executeTreeOperation();
        Command cm11 = new Command("insert TA", tree);
        cm11.executeTreeOperation();

        assertEquals("sequence CGTA inserted at level 0\n"
            + "sequence CAAA inserted at level 2\n"
            + "sequence CA inserted at level 3\n"
            + "sequence CAAAAAACCTTCAATCGTA inserted at level 5\n"
            + "sequence CATGGGAA inserted at level 3\n"
            + "sequence TGGCAA inserted at level 1\n"
            + "sequence CGTA removed\n" + "sequence CACT inserted at level 3\n"
            + "tree dump:\n" + "I\n" + "  E\n" + "  I\n" + "    I\n"
            + "      I\n" + "        I\n" + "          CAAAAAACCTTCAATCGTA\n"
            + "          E\n" + "          E\n" + "          E\n"
            + "          CAAA\n" + "        E\n" + "        E\n" + "        E\n"
            + "        E\n" + "      CACT\n" + "      E\n" + "      CATGGGAA\n"
            + "      CA\n" + "    E\n" + "    E\n" + "    E\n" + "    E\n"
            + "  E\n" + "  TGGCAA\n" + "  E\n"
            + "sequence AATA inserted at level 1\n"
            + "sequence TA inserted at level 2", outputStream.toString()
                .trim());
    }


    /**
     * Tests the print for lengths
     * 
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testPrintLengths() throws IOException {
        Command cm1 = new Command("insert ACGT", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert CGCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("insert CACA", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("insert CCAG", tree);
        cm4.executeTreeOperation();
        Command cm5 = new Command("insert CTAAAAAGGCAAT", tree);
        cm5.executeTreeOperation();
        Command cm6 = new Command("remove CGCA", tree);
        cm6.executeTreeOperation();
        Command cm7 = new Command("print lengths", tree);
        cm7.executeTreeOperation();

        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence CGCA inserted at level 1\n"
            + "sequence CACA inserted at level 2\n"
            + "sequence CCAG inserted at level 2\n"
            + "sequence CTAAAAAGGCAAT inserted at level 2\n"
            + "sequence CGCA removed\n" + "tree dump:\n" + "I\n" + "  ACGT 4\n"
            + "  I\n" + "    CACA 4\n" + "    CCAG 4\n" + "    E\n"
            + "    CTAAAAAGGCAAT 13\n" + "    E\n" + "  E\n" + "  E\n" + "  E",
            outputStream.toString().trim());
    }


    /**
     * Tests the print for stats
     * 
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testPrintStats() throws IOException {
        Command cm1 = new Command("insert ACGT", tree);
        cm1.executeTreeOperation();
        Command cm2 = new Command("insert CGCA", tree);
        cm2.executeTreeOperation();
        Command cm3 = new Command("insert CACA", tree);
        cm3.executeTreeOperation();
        Command cm4 = new Command("insert CCAG", tree);
        cm4.executeTreeOperation();
        Command cm5 = new Command("insert CTAAAAAGGCAAT", tree);
        cm5.executeTreeOperation();
        Command cm6 = new Command("remove CGCA", tree);
        cm6.executeTreeOperation();
        Command cm7 = new Command("print stats", tree);
        cm7.executeTreeOperation();

        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence CGCA inserted at level 1\n"
            + "sequence CACA inserted at level 2\n"
            + "sequence CCAG inserted at level 2\n"
            + "sequence CTAAAAAGGCAAT inserted at level 2\n"
            + "sequence CGCA removed\n" + "tree dump:\n" + "I\n"
            + "  ACGT A:25.00 C:25.00 G:25.00 T:25.00\n" + "  I\n"
            + "    CACA A:50.00 C:50.00 G:0.00 T:0.00\n"
            + "    CCAG A:25.00 C:50.00 G:25.00 T:0.00\n" + "    E\n"
            + "    CTAAAAAGGCAAT A:53.85 C:15.38 G:15.38 T:15.38\n" + "    E\n"
            + "  E\n" + "  E\n" + "  E", outputStream.toString().trim());

    }


    /**
     * Tests the sample input provided
     * 
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testP2SampleInput() throws IOException {

        DNAtree.main(new String[] { "SampleInput.txt" });
        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence AAAA inserted at level 2\n"
            + "sequence AA inserted at level 3\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4\n"
            + "sequence ACTGGGAA inserted at level 3\n"
            + "sequence ACGT removed\n" + "sequence ACCTT inserted at level 3\n"
            + "sequence ACTTA inserted at level 4\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA\n"
            + "        AAACCCCGGTGAAAACGTA\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA\n" + "    I\n" + "      E\n" + "      ACCTT\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA\n" + "        ACTTA\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  E\n" + "  E\n"
            + "sequence TATA inserted at level 1\n"
            + "sequence TCG inserted at level 2\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA 4\n"
            + "        AAACCCCGGTGAAAACGTA 19\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA 2\n" + "    I\n" + "      E\n" + "      ACCTT 5\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA 8\n" + "        ACTTA 5\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA 4\n" + "    TCG 3\n" + "    E\n"
            + "    E\n" + "    E\n" + "  E\n" + "tree dump:\n" + "I\n" + "  I\n"
            + "    I\n" + "      I\n"
            + "        AAAA A:100.00 C:0.00 G:0.00 T:0.00\n"
            + "        AAACCCCGGTGAAAACGTA A:42.11 C:26.32 G:21.05 T:10.53\n"
            + "        E\n" + "        E\n" + "        E\n" + "      E\n"
            + "      E\n" + "      E\n"
            + "      AA A:100.00 C:0.00 G:0.00 T:0.00\n" + "    I\n"
            + "      E\n" + "      ACCTT A:20.00 C:40.00 G:0.00 T:40.00\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA A:37.50 C:12.50 G:37.50 T:12.50\n"
            + "        ACTTA A:40.00 C:20.00 G:0.00 T:40.00\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA A:50.00 C:0.00 G:0.00 T:50.00\n"
            + "    TCG A:0.00 C:33.33 G:33.33 T:33.33\n" + "    E\n" + "    E\n"
            + "    E\n" + "  E\n" + "# of nodes visited: 5\n"
            + "sequence: AAAA\n" + "# of nodes visited: 13\n"
            + "sequence: AAAA\n" + "sequence: AAACCCCGGTGAAAACGTA\n"
            + "sequence: AA\n" + "# of nodes visited: 4\n"
            + "no sequence found", outputStream.toString().trim());
    }

// /**
// * Testing the insertNode method.
// */
// public void testinsertNode()
// {
// tree.insert("A");
// tree.insert("AA");
//// tree.insertNode("F");
// String test = "ACT";
//// tree.insertNode(test);
//// tree.insertNode("AGT");
//// tree.print();
//// tree.printLengths();
//// tree.printStats();
// assertEquals("ACT", test);
//
// }

}
