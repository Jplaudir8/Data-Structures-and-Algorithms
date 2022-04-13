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
         * @param value
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
     * The head node of this linked list.
     */
    private ListNode<T> head;

    /**
     * The current size of this linked list.
     */
    private int size;

    /**
     * Creates a new linked list.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Pop function for LL
     * 
     * @return poped node from LL
     */
    public T pop() {
        ListNode<T> first = head.getNext();
        ListNode<T> temp;
        if (first != null) {
            temp = first.getNext();
            head.setNext(temp);
            
            return first.getValue();
        }
        else {
            return null;
        }
    }
    
    /**
     * Inserts data into the linked list
     * 
     * @param record
     *            record to be inserted
     */
    public void insert(T record) {
    	ListNode<T> newNode = new ListNode<T>(record);
    	
    	if (head == null) {
    		head = newNode;
    	}
    	
        if (head.getNext() == null) {
            head.setNext(newNode);
        }
        else {
        	ListNode<T> curr = head;
        	while (curr.getNext() != null) {
        		curr = curr.getNext();
        	}
            curr.setNext(newNode);
        }
    }
    
    /**
     * This method returns the number of nodes in the linked list. The count
     * does not include the head node.
     * 
     * @return int length of the linked list
     */
    public int getListLength() {
        int length = 0;
        ListNode<T> curr = head.getNext();
        while (curr != null) {
            length++;
            curr = curr.getNext();
        }
        return length;
    }
    
    /**
     * Check if the list is empty
     * 
     * @return boolean check for empty list
     */
    public boolean isEmpty() {
        return (head == null);
    }
}