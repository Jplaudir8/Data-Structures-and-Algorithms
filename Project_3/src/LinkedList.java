
/**
 * Represents a basic implementation of a linked list data structure.
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
        private T value;
        private ListNode<T> next;

        /**
         * Creates a new ListNode with the given value.
         * 
         * @param record
         *            record to be inserted
         */
        public ListNode(T record) {
            this.value = record;
            this.next = null;
        }


        /**
         * Returns value from this ListNode object.
         *
         * @return this ListNode's value.
         */
        public T getValue() {
            return this.value;
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
         * Sets instance variable next, to another instance of ListNode.
         * 
         * @param next
         *            instance of next ListNode
         */
        public void setNext(ListNode<T> next) {
            this.next = next;
        }
    }

    /**
     * The head and tail node of this linked list.
     */
    private ListNode<T> head;
    private ListNode<T> tail;

    /**
     * Creates a new linked list.
     */
    public LinkedList() {
        head = new ListNode<T>(null);
        tail = head;
    }


    /**
     * Method to check if list is empty
     * 
     * @return T/F based upon if size = 0.
     */
    public boolean isEmpty() {
        return (head == tail);
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
     * Returns the # of nodes in the linked list.
     * head node not included
     * 
     * @return int length of the linked list
     */
    public int getLength() {
        int length = 0;
        ListNode<T> curr = head.getNext();
        while (curr != null) {
            length++;
            curr = curr.getNext();
        }
        return length;
    }


    /**
     * Adds a node to the end of the List.
     * 
     * @param obj
     *            data passed by user to be added.
     */
    public void add(T obj) {
        ListNode<T> nodeToAdd = new ListNode<T>(obj);
        ListNode<T> curr;
        if (head.getNext() == null) {
            head.setNext(nodeToAdd);
            tail = nodeToAdd;
        }
        else {
            curr = tail;
            curr.setNext(nodeToAdd);
            tail = nodeToAdd;
        }
    }


    /**
     * Removes the top item of the list
     * 
     * @return item removes
     */
    public T pop() {
        ListNode<T> first = head.getNext();
        ListNode<T> temp;
        if (first != null) {
            temp = first.getNext();
            head.setNext(temp);
            if (tail == first) {
                tail = head;
            }
            return first.getValue();
        }
        else {
            return null;
        }
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
                break;
            }
            curr = curr.getNext();
        }
    }
}
