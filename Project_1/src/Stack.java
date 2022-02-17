
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Represents a last-in first-out stack of singly linked list objects which
 * represent numbers that cannot fit in normal 32-bit and 64-bit integers.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * @param <U>
 */
public class Stack<U> {

    /**
     * SinglyLinkedList instance that represents this stack's structure.
     */
    private SinglyLinkedList<U> list;

    /**
     * The current size of this stack.
     */
    private int size;

    /**
     * The maximum size of this stack.
     */
    private static int capacity = Integer.MAX_VALUE;

    /**
     * Creates a new stack.
     */
    public Stack() {
        this.list = new SinglyLinkedList<U>();
        this.size = 0;
    }


    /**
     * Checks if this stack is empty.
     * 
     * @return the size of this stack
     */
    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     * Sets a new store capacity to this stack.
     * 
     * @param capacity
     *            new capacity to be assigned to this stack
     */
    public void setCapacity(int capacity) {
        Stack.capacity = capacity;
    }


    /**
     * Returns capacity of this stack.
     * 
     * @return capacity of this stack
     */
    public int getCapacity() {
        return Stack.capacity;
    }


    /**
     * Pushes a new linked list object to this stack.
     * 
     * @param newValue
     *            value to be added.
     * @error if this stack is full.
     */
    public void push(U newValue) throws NoSuchElementException {
        if (this.size < capacity) {
            this.list.addFirst(newValue);
            this.size++;
        }
        else {
            throw new Error("Stack is full!");
        }
    }


    /**
     * Removes the linked list at the top of this stack and returns that object.
     * 
     * @return linked list object at the top of this stack.
     * @exception NoSuchElementException
     *                if this stack is empty.
     */
    public U pop() {
        if (this.size != 0) {
            U topValue = this.list.removeFirst();
            this.size--;
            return topValue;
        }
        else {
            throw new EmptyStackException();
        }
    }


    /**
     * Returns linked list at the top of this stack without removing it.
     * 
     * @return linked list at the top of this stack, otherwise null if it does
     *         not exist.
     * 
     */
    public U peek() {
        if (this.size != 0) {
            return this.list.getHead().getValue();
        }
        else {
            throw new EmptyStackException();
        }
    }


    /**
     * Check the size of the stack
     *
     * @return Boolean value indicating if stack is empty or not
     */
    public int size() {
        return size;
    }
}
