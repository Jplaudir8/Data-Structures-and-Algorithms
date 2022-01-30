package RPNCalculator;

import java.util.EmptyStackException;

/**
 * Creates a Stack class
 *
 * @author Raena Rahimi Bafrani
 * @version Jan 28, 2022
 * @param <T>
 *
 */
public class Stack<T> {
    //~ Fields ................................................................
    private LinkedList<Object> list;
    private int size;
    /**
     * The maximum size of the stack
     */
    public static final int MAX_SIZE = Integer.MAX_VALUE;

    //~ Constructors ..........................................................
    /**
     * Creates a default Constructor.
     */
    public Stack() {
        list = new LinkedList<Object>();
       size = 0;
    }

    //~Public  Methods ........................................................
    /**
     * Push a new node to the top of the Stack which will contain
     * the data provided (a number).
     *
     * @param obj The number entered  by user
     * @throws EmptyStackException when the stack is empty
     */
    public void push(Object obj)
    {
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
    public Object pop()
    {
        if (!isEmpty()) {
            return list.remove();
        }
        size--;
        throw new EmptyStackException();

    }

    /**
     * Determines the top element of the stack
     * @return top element
     * @throws EmptyStackException when the stack is empty
     */
    public Object peek()
    {
        if(!isEmpty())
        {
            return list.head.getData();
        }
        throw new EmptyStackException();
    }

    /**
     * Check if list is empty
     *
     * @return Boolean value indicating if stack is empty or not
     */
    public boolean isEmpty()
    {
            return size == 0;
    }

    /**
     * Check if list is empty
     *
     * @return Boolean value indicating if stack is empty or not
     */
    public Object size()
    {
            return size;
    }

    /**
     * Print all data on the Stack
     */
    public void printStack()
    {
            list.toString();
    }

}
