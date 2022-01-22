package com.bigarithmetic;

public class SinglyLinkedList {

    public class ListNode {

        public int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
            this.next = null;
        }


        public void setNext(ListNode next) {
            this.next = next;
        }


        public ListNode getNext() {
            return this.next;
        }
    }

    public ListNode head;

    public SinglyLinkedList() {
        this.head = null;
    }


    /**
     * Add new value to the beginning of linked list.
     * 
     * @param newVal
     *            : new value to be added
     */
    public void addFirst(int newVal) {
        ListNode newNode = new ListNode(newVal);
        ListNode oldHead = this.head;
        this.head = newNode;

        if (oldHead != null)
            this.head.setNext(oldHead);

    }


    /**
     * Add new value to the end of linked list.
     * 
     * @param newVal
     *            : new value to be added
     */
    public void addLast(int newVal) {
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
    }

}
