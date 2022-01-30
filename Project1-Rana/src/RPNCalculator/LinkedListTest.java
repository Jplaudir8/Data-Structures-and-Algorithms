package RPNCalculator;

import student.TestCase;

//-------------------------------------------------------------------------
/**
* Test class for LinkedList.
*
*
*  @author Raena Rahimi Bafrani
*  @version Jan 28, 2022
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
     * Tests the addFirst and addLast method
     */
    public void testRemove() {
        assertEquals(emptyList.size(), 0);

        emptyList.addTop("A");
        emptyList.addBottom("B");
        assertEquals(emptyList.size(), 2);
        assertEquals(emptyList.toString(), "{A, B}");
        assertEquals(emptyList.remove(), "A");
        assertEquals(emptyList.toString(), "{B}");

    }
}
