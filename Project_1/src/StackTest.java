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

import java.util.EmptyStackException;
import student.TestCase;

/**
 * Tests the Stack class
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 *
 */
public class StackTest extends TestCase {
    private Stack<String> emptyStack;
    private Stack<String> smallStack;

    /**
     * Sets up the test cases.
     */
    public void setUp() {
        emptyStack = new Stack<String>();
        smallStack = new Stack<String>();
        
        smallStack.push("A");
        smallStack.push("B");
        smallStack.push("C");

    }


// ----------------------------------------------------------
    /**
     * checks if the stack is empty
     */
    public void testIsEmpty() {

        assertEquals(emptyStack.size(), 0);
        assertTrue(emptyStack.isEmpty());

        assertEquals(smallStack.size(), 3);
        assertFalse(smallStack.isEmpty());

    }


    /**
     * tests push() method
     */
    public void testPush() {

        emptyStack.push("A");
        assertEquals(emptyStack.size(), 1);

        smallStack.setCapacity(1000);
        for (int i = 0; i < 997; i++) {
            smallStack.push("C");
        }
        assertEquals(smallStack.size(), 1000);

        Error thrown = null;
        try {
            smallStack.push("A");
        }
        catch (Error error) {

            thrown = error;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof Error);

    }


    /**
     * tests pop() method
     */
    public void testPop() {

        assertEquals(smallStack.pop(), "C");
        assertEquals(smallStack.pop(), "B");
        assertEquals(smallStack.pop(), "A");

        Exception thrown = null;
        try {
            smallStack.pop();
        }
        catch (Exception exception) {

            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof EmptyStackException);

    }


    /**
     * tests peek() method
     */
    public void testPeek() {

        Exception thrown = null;
        try {
            emptyStack.peek();
        }
        catch (Exception exception) {

            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof EmptyStackException);

        assertEquals(smallStack.peek(), "C");
        assertNotSame(smallStack.peek(), "A");

    }

}
