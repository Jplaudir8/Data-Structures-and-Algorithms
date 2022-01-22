package com.bigarithmetic;

public class Stack {
    public SinglyLinkedListObj stack;
    public int size;
    public static final int MAX_SIZE = Integer.MAX_VALUE;

    public Stack() {
        this.stack = new SinglyLinkedListObj();
        this.size = 0;
    }


    public boolean isEmpty() {
        return this.size == 0;
    }


    public void push(SinglyLinkedListObj newValue) {
        if (this.size < MAX_SIZE) {
            this.stack.addFirst(newValue);
            this.size++;
        }
        else {
            throw new Error("Stack is full!");
        }
    }


    public Object pop() {
        if (this.size != 0) {
            Object topValue = this.stack.removeFirst();
            this.size--;
            return topValue;
        }
        else {
            throw new Error("Stack is empty, cannot pop!");
        }
    }
    
    public Object peek() {
        if (this.size != 0) {
            return this.stack.head.value;
        } else {
            return null;
        }
    }
}
