package RPNCalculator;


// -------------------------------------------------------------------------
/**
 * This is a basic implementation of a linked list
 *
 *
 *  @author Raena Rahimi Bafrani
 *  @version Jan 28, 2022
 *  @param <T>
 */
public class LinkedList<T> {

    /**
     * This represents a node in a singly linked list. This node stores data
     * along with having a pointer to the next node in the list
     *
     *  @author Raena Rahimi Bafrani
     *  @version Jan 28, 2022
     *  @param <T> data type
     */
    public static class Node<T> {

        private T data;
        private Node<T> next;

        /**
         * Constructor Node with No next param.
         *
         * @param data
         *            values of Nodes data passed.
         */
        public Node(T data) {
            this.data = data;
        }


        /**
         * Constructor with nextNode Param.
         *
         * @param data
         *            values of Nodes passed.
         * @param nextNode
         *            NexNode of the New Node Passed.
         */
        public Node(T data, Node<T> nextNode) {
            this.data = data;
            this.next = nextNode;
        }


        /**
         * Return data field.
         *
         * @return Nodes Data / Contents.
         */
        public T getData() {
            return this.data;
        }


        /**
         * Returns the next node in the sequence.
         *
         * @return Next Node in sequence.
         */
        public Node<T> getNext() {
            return next;
        }


        /**
         * Sets the next Node to what the user wants.
         *
         * @param nextNode
         *            The nextNode passed to be set.
         */
        public void setNext(Node<T> nextNode) {
            this.next = nextNode;
        }


        /**
         * Sets the Data at a specific location desired by user.
         *
         * @param obj
         *            New Data being passed in to be set.
         */
        public void setData(T obj) {
            data = obj;
        }

    }
    //~ Fields ................................................................
    /**
     * Creates a head node.
     */
    public Node<T> head;

    // the size of the linked list
    private int size;

    //~ Constructors ..........................................................
    /**
     * Creates an empty linked list Constructor.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }
    //~Public  Methods ........................................................

    /**
     * Method to check if list is empty
     *
     * @return T/F based upon if size = 0.
     */
    public boolean isEmpty() {
        return this.size() == 0;
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
     * Adds a node to the first of the List.
     *
     * @param d
     *            data passed by user to be added.
     *
     */
    public void addTop(T d) {
        Node<T> first = new Node<T>(d);
        first.next = this.head;
        head = first;
        size++;
    }

    /**
     * Adds a node to the first of the List.
     *
     * @param d
     *            data passed by user to be added.
     *
     */
    public void addBottom(T d){

        Node<T> last = new Node<T>(d);
        Node<T> temp = head;
        if (temp == null) {
            head = last;
        }
        else {
            while(temp.next!= null) {
                temp = temp.next;
            }
            temp.next = last;
            size++;
        }
    }

    /**
     * Deletes a node from the first of the List.
     *
     * @return A number of the type T
     *
     */
    public T remove(){

        Node<T> remove = head;
        if (remove == null) {
            return null;
        }

        head = remove.getNext();
        return remove.getData();
    }


    /**
     * String Form of a LinkedList
     *
     * @return A String in the form of {} returned.
     */
    @Override
    public String toString() {
        String result = "{";

        Node<T> current = head;
        while (current != null) {
            result += "" + current.data;
            current = current.next;
            if (current != null) {
                result += ", ";
            }
        }
        result += "}";
        return result;
    }


}
