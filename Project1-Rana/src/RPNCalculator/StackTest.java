package RPNCalculator;

import student.TestCase;


/**
 * Tests the stack class
 *
 * @author Raena Rahimi Bafrani
 * @version Jan 28, 2022
 *
 */
public class StackTest extends TestCase {
    private Stack<Integer> emptyStack;
    private Stack<Integer> smallStack;

    /**
     *  Sets up the test cases.
     */
    public void setUp() {
        emptyStack = new Stack<Integer>();
        smallStack = new Stack<Integer>();

        smallStack.push(1);
        smallStack.push(2);
        smallStack.push(3);

    }

// ----------------------------------------------------------
    /**
     *  checks if the stack is empty
     */
    public void testIsEmpty() {

        assertEquals(emptyStack.size(), 0);
        assertTrue(emptyStack.isEmpty());

        assertEquals(smallStack.size(), 3);
        assertFalse(smallStack.isEmpty());


    }


    /**
     *  tests push() and pop() and peek() methods
     */
    public void testPushNdPopNdPeek() {

        emptyStack.push(1);
        assertEquals(emptyStack.size(), 1);
        assertEquals(emptyStack.peek(), 1);
        assertEquals(emptyStack.pop(), 1);


    }
}
