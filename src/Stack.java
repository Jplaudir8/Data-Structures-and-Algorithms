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

/**
 * Creates a Stack class
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * @param <T>
 */
public class Stack<T> {
    // ~ Fields ................................................................
    private LinkedList<Object> list;
    private int size;
    /**
     * The maximum size of the stack
     */
    public static final int MAX_SIZE = 1000;

    // ~ Constructors ..........................................................
    /**
     * Creates a default Constructor.
     */
    public Stack() {

        list = new LinkedList<Object>();
        size = 0;
    }


    // ~Public Methods ........................................................
    /**
     * Push a new node to the top of the Stack which will contain
     * the data provided (a number).
     *
     * @param obj
     *            The number entered by user
     * @throws EmptyStackException
     *             when the stack is empty
     */
    public void push(Object obj) {
        if (size < MAX_SIZE) {
            list.addTop(obj);
            size++;
        }
        else {
            throw new EmptyStackException();
        }
    }


    /**
     * Pop a node from the top of the Stack
     *
     * @return An integer that was on the top of the Stack
     */
    public Object pop() {
        if (!isEmpty()) {
            Object remove = list.remove();
            size--;
            return remove;

        }

        throw new EmptyStackException();

    }


    /**
     * Determines the top element of the stack
     * 
     * @return top element
     * @throws EmptyStackException
     *             when the stack is empty
     */
    public Object peek() {
        if (!isEmpty()) {
            return list.getHead();
        }
        throw new EmptyStackException();
    }


    /**
     * Check if list is empty
     *
     * @return Boolean value indicating if stack is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Check if list is empty
     *
     * @return Boolean value indicating if stack is empty or not
     */
    public int size() {
        return size;
    }

}
