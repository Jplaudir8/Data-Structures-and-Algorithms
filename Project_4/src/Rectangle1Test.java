import java.io.FileNotFoundException;
import java.io.IOException;
import student.TestCase;

/**
 * Tests the rectangle1 class
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version April 16, 2022
 */
public class Rectangle1Test extends TestCase {
    
    /**
     * Tests Invalid files
     */
    public void testInvalidFile() {
        String[] inputFile = { "T.txt" };
        

        Exception e = null;
        try {
            Rectangle1.main(inputFile);
            
        }
        catch (Exception exception) {
            e = exception;
        }
        assertFalse(e instanceof FileNotFoundException);

    }

    /**
     * Tests invalid insert
     */
    public void testInvalidInsert() {
        Rectangle1 rectangle1 = new Rectangle1();
        rectangle1.insert("Rectangle", 1, 2, 3, 4);

        Exception e = null;
        try {
            rectangle1.insert("", -1, -20, 3, 4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertFalse(e instanceof IllegalArgumentException);

    }


    /**
     * Tests insert
     */
    public void testInsert() {
        String[] inputFile = { "Test5.txt" };
        Rectangle1.main(inputFile);
        assertEquals("Rectangle inserted: (rectA, 1, 0, 2, 4)\n"
            + "Rectangle inserted: (b, 2, 0, 4, 8)\n"
            + "Rectangle inserted: (rectC, 4, 0, 9, 6)\n", systemOut()
                .getHistory());

    }


    /**
     * Tests provided syntax
     */
    public void testSyntax() {

        Rectangle1 dum = new Rectangle1();
        assertNotNull(dum);
        String[] inputFile = { "SyntaxTest.txt" };
        Rectangle1.main(inputFile);

        assertEquals("Rectangle rejected: (r_r, -1, -20, 3, 4)\n"
            + "Rectangle rejected: (rec, 7, -8, 1, 3)\n"
            + "Rectangle rejected: (virtual_rec0, 1, 1, 0, 0)\n"
            + "Rectangle rejected: (virtual_REC0, 0, 0, 11, 0)\n"
            + "Rectangle rejected: (inExistRec_0, 1, 1, -1, -2)\n"
            + "Rectangle rejected: (11, 11, 0, 0)\n" + "Intersection pairs:\n"
            + "SkipList dump:\n" + "Node has depth 1, Value (null)\n"
            + "SkipList size is: 0\n" + "Rectangle not found: r_r\n"
            + "Rectangle not removed: r_r\n"
            + "Rectangle rejected: (1, 1, 0, 0)\n"
            + "Rectangles intersecting region (-5, -5, 20, 20):\n", systemOut()
                .getHistory());

    }


    /**
     * Tests additional commands
     */
    public void testAdditionalTest() {

        String[] inputFileNew = { "Test.txt" };
        Rectangle1.main(inputFileNew);
        assertTrue(systemOut().getHistory().startsWith(
            "Rectangle inserted: (a, 10, 10, 15, 15)\n"
                + "Rectangle inserted: (b, 11, 11, 5, 5)\n"
                + "Rectangle inserted: (c, 0, 0, 1000, 10)\n"
                + "Rectangle inserted: (d, 0, 0, 10, 1000)\n"
                + "Rectangle removed: (a, 10, 10, 15, 15)\n"
                + "Rectangle removed: (b, 11, 11, 5, 5)\n"
                + "Rectangle not removed: f\n"
                + "Rectangle not removed: (1, 1, 1, 1)\n"
                + "Rectangle not removed: (1000000, 1, 1, 1)\n"
                + "Rectangle not removed: (1, 100000, 1, 1)\n"
                + "Rectangle rejected: (a, 1, 1, 1, 1000000)\n"
                + "Rectangle not found: a\n"));

        for (int i = 0; i < 50; i++) {
            Rectangle1.main(inputFileNew);
        }

    }


    /**
     * Tests coverage
     */
    public void testCoverage() {

        String[] inputFile = { "Test1.txt" };
        Rectangle1.main(inputFile);

        assertEquals("Rectangle inserted: (a, 10, 10, 15, 15)\n"
            + "Rectangle inserted: (b, 11, 11, 5, 5)\n"
            + "Rectangle inserted: (c, 0, 0, 1000, 10)\n"
            + "Rectangle inserted: (d, 0, 0, 10, 1000)\n"
            + "Rectangle inserted: (a, 10, 10, 15, 15)\n"
            + "Rectangle inserted: (b, 11, 11, 5, 5)\n"
            + "Rectangle inserted: (a, 0, 0, 1000, 10)\n"
            + "Rectangle inserted: (b, 0, 0, 910, 10)\n"
            + "Rectangles intersecting region (900, 5, 15, 15):\n"
            + "a, 0, 0, 1000, 10\n"
            + "b, 0, 0, 910, 10\n"
            + "c, 0, 0, 1000, 10\n"
            + "Rectangles intersecting region (9, 900, 5, 500):\n"
            + "d, 0, 0, 10, 1000\n"
            + "Intersection pairs:\n"
            + "(a, 0, 0, 1000, 10 | b, 0, 0, 910, 10)\n"
            + "(a, 0, 0, 1000, 10 | c, 0, 0, 1000, 10)\n"
            + "(a, 0, 0, 1000, 10 | d, 0, 0, 10, 1000)\n"
            + "(a, 10, 10, 15, 15 | a, 10, 10, 15, 15)\n"
            + "(a, 10, 10, 15, 15 | b, 11, 11, 5, 5)\n"
            + "(a, 10, 10, 15, 15 | b, 11, 11, 5, 5)\n"
            + "(a, 10, 10, 15, 15 | a, 10, 10, 15, 15)\n"
            + "(a, 10, 10, 15, 15 | b, 11, 11, 5, 5)\n"
            + "(a, 10, 10, 15, 15 | b, 11, 11, 5, 5)\n"
            + "(b, 0, 0, 910, 10 | a, 0, 0, 1000, 10)\n"
            + "(b, 0, 0, 910, 10 | c, 0, 0, 1000, 10)\n"
            + "(b, 0, 0, 910, 10 | d, 0, 0, 10, 1000)\n"
            + "(b, 11, 11, 5, 5 | a, 10, 10, 15, 15)\n"
            + "(b, 11, 11, 5, 5 | a, 10, 10, 15, 15)\n"
            + "(b, 11, 11, 5, 5 | b, 11, 11, 5, 5)\n"
            + "(b, 11, 11, 5, 5 | a, 10, 10, 15, 15)\n"
            + "(b, 11, 11, 5, 5 | a, 10, 10, 15, 15)\n"
            + "(b, 11, 11, 5, 5 | b, 11, 11, 5, 5)\n"
            + "(c, 0, 0, 1000, 10 | a, 0, 0, 1000, 10)\n"
            + "(c, 0, 0, 1000, 10 | b, 0, 0, 910, 10)\n"
            + "(c, 0, 0, 1000, 10 | d, 0, 0, 10, 1000)\n"
            + "(d, 0, 0, 10, 1000 | a, 0, 0, 1000, 10)\n"
            + "(d, 0, 0, 10, 1000 | b, 0, 0, 910, 10)\n"
            + "(d, 0, 0, 10, 1000 | c, 0, 0, 1000, 10)\n", systemOut()
                .getHistory());
    }


    /**
     * @throws IOException
     * @throws NumberFormatException
     */
    public void testDump() throws NumberFormatException, IOException {
        String[] inputFile = { "Test2.txt" };
        Rectangle1.main(inputFile);
        assertTrue(systemOut().getHistory().contains("SkipList size is: 3\n"));
    }


    /**
     * Tests pairs
     */
    public void testList() {
        String[] inputFile = { "Test3.txt" };
        Rectangle1.main(inputFile);

        assertTrue(systemOut().getHistory().endsWith("Intersection pairs:\n"
            + "(b, 2, 0, 4, 8 | rectA, 1, 0, 2, 4)\n"
            + "(b, 2, 0, 4, 8 | rectC, 4, 0, 9, 6)\n"
            + "(rectA, 1, 0, 2, 4 | b, 2, 0, 4, 8)\n"
            + "(rectC, 4, 0, 9, 6 | b, 2, 0, 4, 8)\n"
            + "Rectangles intersecting region (1, 1, 1, 1):\n"
            + "b, 2, 0, 4, 8\n" + "rectA, 1, 0, 2, 4\n"));
    }


    /**
     * Test remove
     */
    public void testRemove() {
        String[] inputFile = { "Test4.txt" };
        Rectangle1.main(inputFile);

        assertTrue(systemOut().getHistory().endsWith(
            "Rectangle not removed: (1, 1, 1, 1)\n"
                + "Rectangle not removed: (1, 0, 2, 4)\n"
                + "Rectangle removed: (rectC, 4, 0, 9, 6)\n"
                + "Rectangle removed: (b, 2, 0, 4, 8)\n"));
    }

}
