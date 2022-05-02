import student.TestCase;

/**
 * Tests the rectangle figure class
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version April 16, 2022
 */
public class RectangleFigureTest extends TestCase {

    private RectangleFigure rectangle;

    /**
     * Set up for test cases.
     */
    public void setUp() {
        rectangle = new RectangleFigure("Rectangle", 1, 2, 3, 4);
    }


    /**
     * Tests getName()
     */
    public void testGetName() {
        assertEquals(rectangle.getName(), "Rectangle");
    }


    /**
     * Tests getWidth()
     */
    public void testGetWidth() {
        assertEquals(rectangle.getWidth(), 3);
    }


    /**
     * Tests getHeight()
     */
    public void testGetHeight() {
        assertEquals(rectangle.getHeight(), 4);
    }


    /**
     * Tests getX()
     */
    public void testGetX() {
        assertEquals(rectangle.getxCoord(), 1);
    }


    /**
     * Tests getY()
     */
    public void testGetY() {
        assertEquals(rectangle.getyCoord(), 2);
    }


    /**
     * Tests validity
     */
    public void testToString() {
        assertEquals(rectangle.toString(), "Rectangle, 1, 2, 3, 4");
    }


    /**
     * Tests getY()
     */
    @SuppressWarnings("unused")
    public void testInvalidRec() {

        Exception e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("", 1, 2, 3, 4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("1Rec", 1, 2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec ", 1, 2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec_", 1, 2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertFalse(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", -1, 2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1, -2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", -1, -2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1, 2, 0, 4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1, 2, 3, 0);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1, 2, 0, 0);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

    }


    /**
     * Tests validity
     */
    @SuppressWarnings("unused")
    public void testInvalidRec2() {
        Exception e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1025, 2, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1, 1025, 3,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 10, 2, 1015,
                4);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1, 10, 3,
                1015);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", 1025, 1025,
                1025, 1025);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

        e = null;
        try {
            RectangleFigure invalidRec = new RectangleFigure("Rec", -1, -2,
                1025, 1026);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);

    }

}
