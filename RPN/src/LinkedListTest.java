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

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Linked list class
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 */

public class LinkedListTest extends TestCase {
    private LinkedList<String> emptyList;
    private LinkedList<String> smallList;
    private LinkedList<String> bigList;

    /**
     * Initializes empty list, lists with a small number of items, and
     * list with a large number of items
     */
    public void setUp() {
        emptyList = new LinkedList<String>();
        smallList = new LinkedList<String>();

        smallList.addTop("A");
        smallList.addTop("B");
        smallList.addTop("C");

        bigList = new LinkedList<String>();

        for (int i = 0; i < 100; i++) {
            bigList.addTop("A" + i);
        }

    }


    /**
     * Tests isEmpty method
     */
    public void testIsEmpty() {
        assertTrue(emptyList.isEmpty());

        assertFalse(smallList.isEmpty());
    }


    /**
     * Tests size method
     */
    public void testSize() {
        assertEquals(emptyList.size(), 0);
        assertEquals(smallList.size(), 3);
        assertEquals(bigList.size(), 100);
    }


    /**
     * Tests the addFirst and addLast method
     */
    public void testAddInteger() {
        assertEquals(emptyList.size(), 0);

        emptyList.addTop("A");
        emptyList.addBottom("B");
        assertEquals(emptyList.size(), 2);
        assertEquals(emptyList.toString(), "{A, B}");

        emptyList.remove();
        emptyList.remove();
        emptyList.addBottom("B");
        assertEquals(emptyList.toString(), "{B}");

        smallList.addTop("D");
        smallList.addBottom("E");
        assertEquals(smallList.size(), 5);
        assertEquals(smallList.toString(), "{D, C, B, A, E}");

        for (int i = 0; i < 50; i++) {
            bigList.addTop("B" + i);
            bigList.addBottom("C" + i);
        }
        assertEquals(bigList.size(), 200);

    }


    /**
     * Tests the remove method
     */
    public void testRemove() {
        assertEquals(emptyList.size(), 0);
        assertEquals(emptyList.remove(), null);

        emptyList.addTop("A");
        emptyList.addBottom("B");
        assertEquals(emptyList.size(), 2);
        assertEquals(emptyList.toString(), "{A, B}");
        assertEquals(emptyList.remove(), "A");
        assertEquals(emptyList.toString(), "{B}");

    }


//    /**
//     * Tests the get node at index method
//     */
//    public void testGetAtIndex() {
//        Exception thrown = null;
//        try {
//            emptyList.get(0);
//        }
//        catch (Exception exception) {
//
//            thrown = exception;
//        }
//
//        // checks whether an Exception was actually thrown
//        assertNotNull(thrown);
//
//        // checks whether the right type of Exception was thrown
//        assertTrue(thrown instanceof IndexOutOfBoundsException);
//
//        emptyList.addTop("A");
//        emptyList.addBottom("B");
//        assertEquals(emptyList.get(0), "A");
//
//        assertEquals(smallList.get(1), "B");
//
//        Exception throwN = null;
//        try {
//            smallList.get(5);
//        }
//        catch (Exception exception) {
//
//            throwN = exception;
//        }
//
//        // checks whether an Exception was actually thrown
//        assertNotNull(throwN);
//
//        // checks whether the right type of Exception was thrown
//        assertTrue(throwN instanceof IndexOutOfBoundsException);
//
//    }
}

