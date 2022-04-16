import student.TestCase;

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
 * Tests the LinkedList class.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class LinkedListTest extends TestCase {
    private LinkedList<Integer> emptyList;
    private LinkedList<Integer> smallList;

    /**
     * Initializes empty list, lists with a small number of items, and
     * list with a large number of items
     */
    public void setUp() {
        emptyList = new LinkedList<Integer>();
        smallList = new LinkedList<Integer>();

        smallList.add(1);
        smallList.add(2);
        smallList.add(3);

    }


    /**
     * Tests the add method. Ensures that it adds the object is added at the end
     * and the size is increased
     */
    public void testAdd() {
//        assertEquals(0, emptyList.getSize());
        assertTrue(emptyList.isEmpty());
        assertEquals(emptyList.getTail(), emptyList.getHead());
        emptyList.add(1);
        
        assertFalse(emptyList.isEmpty());
        emptyList.add(2);
//        int head = emptyList.getHead().getValue();
        assertNull(emptyList.getHead().getValue());
//        assertEquals(2, emptyList.getSize());
        assertEquals(2, emptyList.getLength());
        int tail = emptyList.getTail().getValue();
        assertEquals(2, tail);
//        smallList.emptyList();
//        assertEquals(0, smallList.getLength());

    }


    /**
     * Tests the remove method. Ensures that it removes the object
     * and the size is decreased
     */
    public void testRemove() {

        smallList.remove(1);
        assertEquals(2, smallList.getLength());
//        assertEquals(2, smallList.getSize());
        smallList.remove(2);
        smallList.remove(4);
        smallList.remove(3);
        smallList.remove(5);
        assertEquals(0, smallList.getLength());
//        assertEquals(0, smallList.getSize());

    }


    /**
     * Tests the pop method. Ensures that it pops the object from the top
     */
    public void testPop() {

        smallList.pop();
        assertEquals(2, smallList.getLength());
        smallList.pop();
        smallList.pop();
        assertEquals(0, smallList.getLength());

        assertNull(emptyList.pop());

    }

}
