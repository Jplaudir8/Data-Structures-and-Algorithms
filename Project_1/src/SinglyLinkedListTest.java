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
 * Tests the SinglyLinkedList class
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 */

public class SinglyLinkedListTest extends TestCase {
    private SinglyLinkedList<Integer> emptyList;
    private SinglyLinkedList<Integer> smallList;
    private SinglyLinkedList<Integer> bigList;

    /**
     * Initializes empty list, lists with a small number of items, and
     * list with a large number of items
     */
    public void setUp() {
        emptyList = new SinglyLinkedList<Integer>();
        smallList = new SinglyLinkedList<Integer>();

        smallList.addFirst(1);
        smallList.addFirst(2);
        smallList.addFirst(3);

        bigList = new SinglyLinkedList<Integer>();

        for (int i = 0; i < 100; i++) {
            bigList.addFirst(1 + i);
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

        emptyList.addFirst(1);
        emptyList.addLast(2);
        assertEquals(emptyList.size(), 2);
        assertEquals(emptyList.toString(), "21");

        emptyList.removeFirst();
        emptyList.removeFirst();
        emptyList.addLast(2);
        assertEquals(emptyList.toString(), "2");

        smallList.addFirst(4);
        smallList.addLast(5);
        assertEquals(smallList.size(), 5);
        assertEquals(smallList.toString(), "51234");

        for (int i = 0; i < 50; i++) {
            bigList.addFirst(2 + i);
            bigList.addLast(3 + i);
        }
        assertEquals(bigList.size(), 200);

    }


    /**
     * Tests the remove method
     */
    public void testremoveFirst() {
        assertEquals(emptyList.size(), 0);
        assertNull(emptyList.removeFirst());

        emptyList.addFirst(1);
        emptyList.addLast(2);
        assertEquals(emptyList.size(), 2);
        assertEquals(emptyList.toString(), "21");
        emptyList.removeFirst();
        assertEquals(emptyList.toString(), "2");

    }


    /**
     * Tests equals method
     */
    public void testEquals() {
        assertTrue(emptyList.equals(emptyList));

// SinglyLinkedList<Integer> emptyListA = new SinglyLinkedList<Integer>();
// assertTrue(emptyList.equals(emptyListA));

        SinglyLinkedList<Integer> nullList = null;
        assertFalse(emptyList.equals(nullList));

        Object obj = new Object();
        assertFalse(emptyList.equals(obj));

        assertFalse(emptyList.equals(smallList));

        for (int i = 0; i < 100; i++) {
            emptyList.addFirst(1);
        }
        assertFalse(emptyList.equals(bigList));

        for (int i = 0; i < 100; i++) {
            emptyList.removeFirst();
        }

        emptyList.addFirst(1);
        emptyList.addFirst(2);
        emptyList.addFirst(3);
        assertTrue(emptyList.equals(smallList));
    }

}
