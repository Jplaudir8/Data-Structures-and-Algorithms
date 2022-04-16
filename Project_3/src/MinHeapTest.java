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
 * Tests the MinHeap class
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class MinHeapTest extends TestCase {
    private MinHeap<Integer> minHeap;

    /**
     * setUp creates a new Minheap and test array
     */
    public void setUp() {
        Integer[] test = new Integer[15];
        minHeap = new MinHeap<Integer>(test, 0, 9);
    }


    /**
     * tests size and insert method
     * 
     */
    public void testSizeAndInsert() {
        assertEquals(minHeap.heapSize(), 0);
        assertEquals(minHeap.getMaxHeapSize(), 9);
        assertTrue(minHeap.isEmpty());
        assertNull(minHeap.getMin());
        minHeap.insert(78);
        assertEquals(minHeap.heapSize(), 1);
        int pos = minHeap.getPosition(0);
        assertEquals(pos, 78);
        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        minHeap.insert(21);
        minHeap.insert(2);
        assertFalse(minHeap.isFull());
        minHeap.insert(65);
        minHeap.insert(64);
        minHeap.insert(34);

        assertTrue(minHeap.isFull());
        assertFalse(minHeap.isEmpty());
        minHeap.insert(45);
        assertTrue(minHeap.isFull());

        int min = minHeap.getMin();
        assertEquals(min, 2);
    }


    /**
     * tests isLeaf method
     * 
     */
    public void testIsLeaf() {
        minHeap.insert(78);
        assertTrue(minHeap.isLeaf(0));
        assertFalse(minHeap.isLeaf(1));
        minHeap.insert(54);
        assertFalse(minHeap.isLeaf(0));
        assertTrue(minHeap.isLeaf(1));
    }


    /**
     * tests branches leftChild, rightChild and parentChild methods
     * 
     */
    public void testLeftRightParent() {
        minHeap.insert(78);
        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        assertEquals(minHeap.leftChild(0), 1);
        assertEquals(minHeap.rightChild(0), 2);

        assertEquals(minHeap.leftChild(2), -1);
        assertEquals(minHeap.rightChild(1), -1);

        assertEquals(minHeap.parent(2), 0);
        assertEquals(minHeap.parent(3), 1);
        assertEquals(minHeap.parent(0), -1);
        assertEquals(minHeap.parent(-2), -1);
    }


    /**
     * tests removeMin method
     * 
     */
    public void testRemoveMin() {
        assertNull(minHeap.removeMin());

        minHeap.insert(78);
        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        int min = minHeap.removeMin();
        assertEquals(36, min);

        minHeap.insert(81);
        minHeap.insert(21);
        minHeap.insert(2);
        min = minHeap.removeMin();
        assertEquals(2, min);

        min = minHeap.removeMin();
        assertEquals(21, min);

        minHeap.setHeapSize(5);
        assertEquals(minHeap.heapSize(), 5);

    }


    /**
     * tests remove method
     * 
     */
    public void testRemove() {
        assertNull(minHeap.remove(0));
        assertNull(minHeap.remove(1));
        assertNull(minHeap.remove(-1));

        minHeap.insert(78);
        int remove = minHeap.remove(0);
        assertEquals(78, remove);

        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        remove = minHeap.remove(0);
        assertEquals(36, remove);

        minHeap.insert(81);
        minHeap.insert(21);
        minHeap.insert(2);
        remove = minHeap.remove(2);
        assertEquals(81, remove);

        assertNull(minHeap.remove(5));

    }


    /**
     * tests modify method
     * 
     */
    public void testModify() {
        minHeap.modify(0, 3);
        assertNull(minHeap.getPosition(0));
        assertNull(minHeap.getPosition(1));

        minHeap.modify(-1, 3);
        assertNull(minHeap.getPosition(0));

        minHeap.modify(1, 3);
        assertNull(minHeap.getPosition(0));

        minHeap.insert(78);
        minHeap.modify(1, 3);
        assertNull(minHeap.getPosition(1));
        int pos = minHeap.getPosition(0);
        assertEquals(78, pos);

        minHeap.modify(0, 73);
        pos = minHeap.getPosition(0);
        assertEquals(73, pos);

        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        minHeap.modify(1, 25);
        pos = minHeap.getPosition(0);
        assertEquals(25, pos);
        pos = minHeap.getPosition(1);
        assertEquals(36, pos);

    }


    /**
     * tests siftdown method
     * 
     */
    public void testSiftDown() {
        minHeap.siftdown(0);
        minHeap.siftdown(1);
        minHeap.siftdown(-1);

        assertNull(minHeap.getPosition(0));
        assertNull(minHeap.getPosition(1));
    }


    /**
     * tests update method
     * 
     */
    public void testUpdate() {
        minHeap.update(0);
        assertNull(minHeap.getPosition(0));
        assertNull(minHeap.getPosition(1));

        minHeap.insert(78);
        minHeap.update(0);
        assertNull(minHeap.getPosition(1));
        int pos = minHeap.getPosition(0);
        assertEquals(78, pos);

        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        minHeap.update(1);
        pos = minHeap.getPosition(0);
        assertEquals(36, pos);
        pos = minHeap.getPosition(1);
        assertEquals(78, pos);

    }


    /**
     * tests replace and insert at root method
     * 
     */
    public void testRep() {
        minHeap.replaceRoot(5);
        assertNull(minHeap.getPosition(0));

        minHeap.insertAtRoot(10);
        assertNull(minHeap.getPosition(0));

        minHeap.insert(5);
        int pos = minHeap.getPosition(0);
        assertEquals(5, pos);

        minHeap.insertAtRoot(25);
        pos = minHeap.getPosition(0);
        assertEquals(25, pos);

        minHeap.replaceRoot(15);
        pos = minHeap.getPosition(0);
        assertEquals(15, pos);

        minHeap.replaceRoot(35);
        pos = minHeap.getPosition(0);
        assertEquals(15, pos);
        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        pos = minHeap.getPosition(0);
        assertEquals(36, pos);

        minHeap.replaceRoot(23);
        pos = minHeap.getPosition(0);
        assertEquals(54, pos);
    }


    /**
     * tests buildHeap method
     * 
     */
    public void testBuildHeap() {
        minHeap.insert(78);
        minHeap.insert(54);
        minHeap.insert(36);
        minHeap.insert(81);
        minHeap.insert(21);
        minHeap.update(4);
        minHeap.insert(2);
        minHeap.insert(65);
        minHeap.insert(64);
        minHeap.insert(34);
        minHeap.insert(45);
        minHeap.buildheap();
        int pos = minHeap.getPosition(0);
        assertEquals(2, pos);
        pos = minHeap.getPosition(4);
        assertEquals(78, pos);
    }

}
