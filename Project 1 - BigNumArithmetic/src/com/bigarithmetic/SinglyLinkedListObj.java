package com.bigarithmetic;

public class SinglyLinkedListObj<T> {
    public class ListNode<T> {

        private T value;
        private ListNode<T> next;

        public ListNode(T value) {
            this.value = value;
            this.next = null;
        }


        public void setNext(ListNode<T> next) {
            this.next = next;
        }


        public ListNode<T> getNext() {
            return this.next;
        }
        
        public T getValue() {
            return this.value;
        }
    }

    private ListNode<T> head;
    private int size;
    
    public SinglyLinkedListObj() {
        this.head = null;
        this.size = 0;
    }

    public SinglyLinkedListObj(T newVal) {
        this.head = new ListNode(newVal);
        this.size = 1;
    }
    /**
     * Add new value to the beginning of linked list.
     * 
     * @param newVal
     *            : new value to be added
     */
    public void addFirst(T newVal) {
        ListNode<T> newNode = new ListNode(newVal);
        ListNode<T> oldHead = this.head;
        this.head = newNode;

        if (oldHead != null)
            this.head.setNext(oldHead);
        size++;
    }


    /**
     * Add new value to the end of linked list.
     * 
     * @param newVal
     *            : new value to be added
     */
    public void addLast(T newVal) {
        ListNode<T> newNode = new ListNode(newVal);
        ListNode<T> tail = this.head;

        if (tail == null) {
            this.head = newNode;
        }
        else {
            while (tail.getNext() != null) {
                tail = tail.next;
            }
            tail.setNext(newNode);
        }
        size++;
    }


    public T removeFirst() {
        ListNode<T> removedHead = this.head;
        if (removedHead == null)
            return null;
        
        this.head = removedHead.getNext();
        size--;
        return removedHead.value;
    }
    
    public int getSize() {
        return this.size;
    }
    
    
    public ListNode<T> getHead() {
        return this.head;
    }
    
    
    /**
     * String Form of a LinkedList
     *
     * @return A String returned.
     */
    @Override
    public String toString() {
        SinglyLinkedListObj<Integer> reversedList = new SinglyLinkedListObj();
        ListNode currNode = this.head;
        while (currNode != null) {
            reversedList.addFirst((Integer)currNode.value);
            currNode = currNode.getNext();
        }
        
        // traverse linked list to print
        String numberStr = "";
        currNode = reversedList.head;
        while (currNode != null) {
            numberStr += currNode.value;
            currNode = currNode.getNext();
        }
        return numberStr;
    }
}
