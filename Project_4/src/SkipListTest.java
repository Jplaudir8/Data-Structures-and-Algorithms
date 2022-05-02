import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Tests the rectangle figure class
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version April 16, 2022
 */
public class SkipListTest extends TestCase {
    private SkipList<Integer, String> skipList;

    /**
     * Set up for test cases.
     */
    public void setUp() {
        skipList = new SkipList<Integer, String>();
    }


    /**
     * Tests
     */
    public void testNew() {

        skipList.insert(12, "A");
        skipList.insert(11, "C");
        skipList.insert(35, "D");
        skipList.insert(13, "G");
        skipList.insert(14, "H");
        skipList.insert(111, "K");
        skipList.insert(123, "P");
        skipList.insert(-123, "Y");
        skipList.insert(23, "U");

        assertEquals(skipList.size(), 9);

        skipList.remove(35);
        assertEquals(skipList.size(), 8);

        assertNull(skipList.remove(200));

        assertNull(skipList.find(200));

//        skipList.printNodes();
    }
    
    /**
     * Tests hasNext method from iterator
     */
    public void testHasNext() {
        skipList.insert(14, "H");
        skipList.insert(111, "K");
        skipList.insert(123, "P");
        skipList.insert(-123, "Y");
        Iterator<String> iter = skipList.iterator();
        assertTrue(iter.hasNext());
      
        
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertFalse(iter.hasNext());
        
        Exception thrown = null;
        try {
            iter.next();
        }
        catch (NoSuchElementException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);
        
        skipList = new SkipList<Integer, String>();
        iter = skipList.iterator();
        assertFalse(iter.hasNext());
              
    }
    

    /**
     * Tests next method from iterator
     */
    public void testNext() {
        Iterator<String> iter = skipList.iterator();
        Exception thrown = null;
        try {
            iter.next();
        }
        catch (NoSuchElementException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);

    }
    
    /**
     * Tests next method from iterator
     */
    public void testNext2() {
                
        skipList.insert(14, "H");
        skipList.insert(111, "K");
        skipList.insert(123, "P");
        skipList.insert(-123, "Y");
        Iterator<String> iter = skipList.iterator();
        assertEquals(iter.next(), "Y");
        assertEquals(iter.next(), "H");
        iter.next();
        iter.next();
        
//        Exception thrown1 = null;        
//        try {
//            iter.next();
//        } 
//        catch (NoSuchElementException exception) {
//            thrown1 = exception;
//        }
//        assertNotNull(thrown1);
//        assertTrue(thrown1 instanceof NoSuchElementException);
        
    }
}
