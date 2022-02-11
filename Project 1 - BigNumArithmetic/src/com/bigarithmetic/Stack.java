package com.bigarithmetic;

/**
 * Represents an implementation of a stack data structure.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * @param <T>
 */
public class Stack<U> {
    
    /**
     * 
     */
    private SinglyLinkedList<U> stack;
    private int size;
    public static final int MAX_SIZE = Integer.MAX_VALUE;

    public Stack() {
        this.stack = new SinglyLinkedList<U>();
        this.size = 0;
    }


    public boolean isEmpty() {
        return this.size == 0;
    }


    public void push(U newValue) {
        if (this.size < MAX_SIZE) {
            this.stack.addFirst(newValue);
            this.size++;
        }
        else {
            throw new Error("Stack is full!");
        }
    }


    public U pop() {
        if (this.size != 0) {
            U topValue = this.stack.removeFirst();
            this.size--;
            return topValue;
        }
        else {
            throw new Error("Stack is empty, cannot pop!");
        }
    }


    public U peek() {
        if (this.size != 0) {
            return this.stack.getHead().getValue();
        }
        else {
            return null;
        }
    }
}
