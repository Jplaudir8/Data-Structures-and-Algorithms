package com.bigarithmetic;

/**
 * Represents a basic implementation of a singly linked list data structure.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * @param <T>
 */
public class SinglyLinkedList<T> {

    /**
     * Represents a node in a singly linked list. This node stores data
     * along with having a pointer to the next node in the list.
     *
     * @author Joan Perez Lozano
     * @author Raena Rahimi Bafrani
     * @version Jan 28, 2022
     * @param <T>
     *            data type
     */
    public class ListNode<T> {

        /**
         * The value of this ListNode.
         */
        private T value;

        /**
         * The next subsequent node of this ListNode.
         */
        private ListNode<T> next;

        /**
         * Creates a new ListNode with the given value.
         *
         * @param value
         *            values.
         */
        public ListNode(T value) {
            this.value = value;
            this.next = null;
        }


        /**
         * Sets instance variable next, to another instance of ListNode.
         * 
         * @param next
         *            instance of next ListNode
         */
        public void setNext(ListNode<T> next) {
            this.next = next;
        }


        /**
         * Returns the next node to which this node is pointing to.
         *
         * @return next next ListNode in sequence.
         */
        public ListNode<T> getNext() {
            return this.next;
        }


        /**
         * Returns value from this ListNode object.
         *
         * @return value this ListNode's value.
         */
        public T getValue() {
            return this.value;
        }
    }

    /**
     * The head node of this singly linked list.
     */
    private ListNode<T> head;

    /**
     * The current size of this singly linked list.
     */
    private int size;

    /**
     * Creates a new singly linked list.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }


    /**
     * Creates a new singly linked list with head set to the given value.
     * 
     * @param newVal
     *            first value to be added to this new singly linked list.
     */
    public SinglyLinkedList(T newVal) {
        this.head = new ListNode(newVal);
        this.size = 1;
    }


    /**
     * Adds new value to beginning of this singly linked list.
     * 
     * @param newVal
     *            value to be added to beginning of this singly linked list.
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
     * Adds new value to the end of this singly linked list.
     * 
     * @param newVal
     *            value to be added to the end of this singly linked list.
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


    /**
     * Removes current head of this singly linked list.
     * 
     * @return value first value of this singly linked list that has been
     *         removed.
     */
    public T removeFirst() {
        ListNode<T> removedHead = this.head;
        if (removedHead == null)
            return null;

        this.head = removedHead.getNext();
        size--;
        return removedHead.value;
    }


    /**
     * Returns current size of this singly linked list.
     * 
     * @return size size of this singly linked list.
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Returns current head of this singly linked list.
     * 
     * @return head head node of this singly linked list.
     */
    public ListNode<T> getHead() {
        return this.head;
    }


    /**
     * Returns a string representation of this singly linked list content.
     *
     * @return numberStr string form of this singly linked list.
     */
    @Override
    public String toString() {
        SinglyLinkedList<Integer> reversedList = new SinglyLinkedList();
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
