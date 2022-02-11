package com.bigarithmetic;

import java.util.NoSuchElementException;

/**
 * Represents a last-in first-out stack of singly linked list objects which
 * represent numbers that cannot fit in normal 32-bit and 64-bit integers.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * @param <T>
 */
public class Stack<U> {

    /**
     * SinglyLinkedList instance that represents this stack's structure.
     */
    private SinglyLinkedList<U> stack;

    /**
     * The current size of this stack.
     */
    private int size;

    /**
     * The maximum size of this stack.
     */
    public static final int MAX_SIZE = Integer.MAX_VALUE;

    /**
     * Creates a new stack.
     */
    public Stack() {
        this.stack = new SinglyLinkedList<U>();
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
     * Pushes a new linked list object to this stack.
     * 
     * @param newValue
     *            value to be added.
     * @error if this stack is full.
     */
    public void push(U newValue) throws NoSuchElementException {
        if (this.size < MAX_SIZE) {
            this.stack.addFirst(newValue);
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
    public U pop() throws NoSuchElementException {
        if (this.size != 0) {
            U topValue = this.stack.removeFirst();
            this.size--;
            return topValue;
        }
        else {
            throw new NoSuchElementException();
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
            return this.stack.getHead().getValue();
        }
        else {
            return null;
        }
    }
}
