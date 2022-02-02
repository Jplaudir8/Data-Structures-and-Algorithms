package com.bigarithmetic;

public class SinglyLinkedListObj {
    public class ListNode {

        private Object value;
        private ListNode next;

        public ListNode(Object value) {
            this.value = value;
            this.next = null;
        }


        public void setNext(ListNode next) {
            this.next = next;
        }


        public ListNode getNext() {
            return this.next;
        }
        
        public Object getValue() {
            return this.value;
        }
    }

    private ListNode head;
    private int size;
    
    public SinglyLinkedListObj() {
        this.head = null;
        this.size = 0;
    }

    public SinglyLinkedListObj(Object newVal) {
        this.head = new ListNode(newVal);
        this.size = 1;
    }
    /**
     * Add new value to the beginning of linked list.
     * 
     * @param newVal
     *            : new value to be added
     */
    public void addFirst(Object newVal) {
        ListNode newNode = new ListNode(newVal);
        ListNode oldHead = this.head;
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
    public void addLast(Object newVal) {
        ListNode newNode = new ListNode(newVal);
        ListNode tail = this.head;

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


    public Object removeFirst() {
        ListNode removedHead = this.head;
        if (removedHead == null)
            return null;
        
        this.head = removedHead.getNext();
        size--;
        return removedHead.value;
    }
    
    public int getSize() {
        return this.size;
    }
    
    
    public ListNode getHead() {
        return this.head;
    }
    
    /**
     * String Form of a LinkedList
     *
     * @return A String in the form of {} returned.
     */
    @Override
    public String toString() {
        StringBuilder completeNumber = new StringBuilder("");
        ListNode currNode = this.head;
        while (currNode != null) {
            completeNumber.insert(0, (Integer) currNode.value);
            currNode = currNode.getNext();
        }
        return completeNumber.toString();
    }
}
