package com.bigarithmetic;


public class SinglyLinkedListObj {
    public class ListNode {

        public Object value;
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
    }

    public ListNode head;

    public SinglyLinkedListObj() {
        this.head = null;
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
    }
    
    public Object removeFirst() {
        ListNode removedHead = this.head;
        if (removedHead == null) return null;
        
        this.head = removedHead.getNext();
        return removedHead.value;
    }
    
    // TODO Might need to implement toString() method
}
