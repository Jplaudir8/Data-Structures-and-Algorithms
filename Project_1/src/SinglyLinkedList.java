
// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

// -------------------------------------------------------------------------
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
    @SuppressWarnings("hiding")
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
         * @return this ListNode's value.
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
        this.head = new ListNode<T>(newVal);
        this.size = 1;
    }


    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }


    /**
     * Method to check if list is empty
     *
     * @return T/F based upon if size = 0.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }


    /**
     * Adds new value to beginning of this singly linked list.
     * 
     * @param newVal
     *            value to be added to beginning of this singly linked list.
     */
    public void addFirst(T newVal) {
        ListNode<T> newNode = new ListNode<T>(newVal);
        ListNode<T> oldHead = this.head;
        this.head = newNode;

        if (oldHead != null) {
            this.head.setNext(oldHead);
        }
        size++;
    }


    /**
     * Adds new value to the end of this singly linked list.
     * 
     * @param newVal
     *            value to be added to the end of this singly linked list.
     */
    public void addLast(T newVal) {
        ListNode<T> newNode = new ListNode<T>(newVal);
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
     * @return first value of this singly linked list that has been
     *         removed.
     */
    public T removeFirst() {
        ListNode<T> removedHead = this.head;
        if (removedHead == null) {
            return null;
        }

        this.head = removedHead.getNext();
        size--;
        return removedHead.value;
    }


    /**
     * Returns current size of this singly linked list.
     * 
     * @return size of this singly linked list.
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Returns current head of this singly linked list.
     * 
     * @return head node of this singly linked list.
     */
    public ListNode<T> getHead() {
        return this.head;
    }


    /**
     * Returns a string representation of this singly linked list content.
     *
     * @return string form of this singly linked list.
     */
    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        SinglyLinkedList<Integer> reversedList = new SinglyLinkedList<>();
        ListNode<Integer> currNode =
            (SinglyLinkedList<T>.ListNode<Integer>)this.head;
        while (currNode != null) {
            reversedList.addFirst((Integer)currNode.value);
            currNode = currNode.getNext();
        }

        // traverse linked list to print
        String numberStr = "";
        currNode = (SinglyLinkedList<T>.ListNode<Integer>)reversedList.head;
        while (currNode != null) {
            numberStr += currNode.value;
            currNode = currNode.getNext();
        }
        return numberStr;
    }


    /**
     * Returns True if both LinkedLists contain the same elements.
     * 
     * @return T/F based upon if they're equal or not.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            SinglyLinkedList<T> other = (SinglyLinkedList<T>)obj;
            if (other.getSize() == this.getSize()) {
                ListNode<T> current = head.getNext();
                ListNode<T> otherCurrent = other.head.getNext();
                while (current != null) {
                    if (!current.value.equals(otherCurrent.value)) {
                        return false;
                    }
                    current = current.getNext();
                    otherCurrent = otherCurrent.getNext();
                }
                return true;
            }
        }

        return false;
    }
}
