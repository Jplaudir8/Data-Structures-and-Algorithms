// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * 
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 * @param <T>
 *            the type
 */
public class LinkedList<T> {

    /**
     * Represents a node in a linked list. This node stores data
     * along with having a pointer to the next node in the list.
     *
     * @author Joan Perez Lozano
     * @author Raena Rahimi Bafrani
     * @version March 22, 2022
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
         * @param val
         *            values.
         */
        public ListNode(T val) {
            this.value = val;
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
     * The head and tail node of this linked list.
     */
    private ListNode<T> head;
    private ListNode<T> tail;

    /**
     * The current size of this linked list.
     */
    private int size;

    /**
     * Creates a new linked list.
     */
    public LinkedList() {
// this.head = null;
        head = new ListNode<T>(null);
        tail = head;
        this.size = 0;
    }


    /**
     * Method to check if list is empty
     *
     * @return T/F based upon if size = 0.
     */
    public boolean isEmpty() {
        return size == 0;
// return head == tail;
    }


    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns current head of this singly linked list.
     * 
     * @return head node of this singly linked list.
     */
    public ListNode<T> getHead() {
        return head;
    }


    /**
     * Returns current head of this singly linked list.
     * 
     * @return head node of this singly linked list.
     */
    public ListNode<T> getTail() {
        return tail;
    }


    /**
     * Adds a node to the end of the List.
     * 
     * @param obj
     *            data passed by user to be added.
     * 
     */
    public void add(T obj) {

        ListNode<T> enteredNode = new ListNode<T>(obj);
        ListNode<T> tempTail;
        if (head.next == null) {
            head.setNext(enteredNode);
            tail = enteredNode;

        }
        else {
            tempTail = tail;
            tempTail.setNext(enteredNode);
            tail = enteredNode;
        }
        size++;

    }


    /**
     * Removes the top item of the list
     *
     * @return item removes
     */
    public T pop() {
        ListNode<T> nodeToRemove = head.next;
        ListNode<T> temp;
        if (nodeToRemove == null) {
            return null;

        }
        else {
            temp = nodeToRemove.next;
            head.setNext(temp);
            if (tail == nodeToRemove) {
                tail = head;
            }
            return nodeToRemove.getValue();
        }
    }


    /**
     * This method returns the number of nodes in the linked list. The count
     * does not include the head node.
     * 
     * @return int length of the linked list
     */
    public int getLength() {
        int len = 0;
        ListNode<T> curr = head.getNext();
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }


    /**
     * Remove the node from the linked list
     * 
     * @param record
     *            record to be removed
     */
    public void remove(T record) {
        ListNode<T> curr = head;
        ListNode<T> temp;
        while (curr.getNext() != null) {
            if (curr.getNext().getValue() == record) {
                if (curr.getNext() == tail) {
                    tail = curr;
                }
                temp = curr.getNext().getNext();
                curr.setNext(temp);
                size--;
                break;
            }
            curr = curr.getNext();
        }
    }


    /**
     * delete the complete list
     */
    public void emptyList() {
        head.setNext(null);
        tail = head;
    }

}
