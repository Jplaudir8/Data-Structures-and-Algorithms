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
 * This is a basic implementation of a linked list
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * @param <T>
 */
public class LinkedList<T> {

    /**
     * This represents a node in a singly linked list. This node stores data
     * along with having a pointer to the next node in the list
     *
     * @author Joan Perez Lozano
     * @author Raena Rahimi Bafrani
     * @version Jan 28, 2022
     * @param <T>
     *            data type
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

// /**
// * Constructor with nextNode Param.
// *
// * @param data
// * values of Nodes passed.
// * @param nextNode
// * NexNode of the New Node Passed.
// */
// public Node(T data, Node<T> nextNode) {
// this.data = data;
// this.next = nextNode;
// }


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

// /**
// * Sets the next Node to what the user wants.
// *
// * @param nextNode
// * The nextNode passed to be set.
// */
// public void setNext(Node<T> nextNode) {
// this.next = nextNode;
// }


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






    
    // ~ Fields ................................................................
    private Node<T> head;

    // the size of the linked list
    private int size;

    // ~ Constructors ..........................................................
    /**
     * Creates an empty linked list Constructor.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }
    // ~Public Methods ........................................................


    /**
     * Method to check if list is empty
     *
     * @return T/F based upon if size = 0.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    
    /**
     * Method to check if list is empty
     *
     * @return T/F based upon if size = 0.
     */
    public Node<T> getHead() {
        return head;
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
    public void addBottom(T d) {

        Node<T> last = new Node<T>(d);
        Node<T> temp = head;
        if (temp == null) {
            head = last;
        }
        else {
            while (temp.next != null) {
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
    public T remove() {

        Node<T> remove = head;
        if (remove == null) {
            return null;
        }

        head = remove.getNext();
        size--;
        return remove.getData();
    }


//    /**
//     * Gets the object at the given position
//     *
//     * @param index
//     *            where the object is located
//     * @return The object at the given position
//     * @throws IndexOutOfBoundsException
//     *             if no node at the given index
//     */
//    public T get(int index) {
//        Node<T> current = head;
//        int currentIndex = 0;
//        T data = null;
//        while (current != null) {
//            if (currentIndex == index) {
//                data = current.data;
//            }
//            currentIndex++;
//            current = current.next;
//        }
//
//        // check if the data was null...
//        if (data == null) {
//            // ... if so throw an exception
//            throw new IndexOutOfBoundsException("Index exceeds the size.");
//        }
//        return data;
//    }

// /**
// * Method to get object at any given position.
// *
// * @param index
// * index in where the object is located at.
// *
// * @return The Node/Object at the given position
// *
// * @throws IndexOutOfBoundsException
// * if no node exists at such index.
// */
// public T get(int index) {
//
// return getNodeAtIndex(index).getData();
// }
//
//
// /**
// * Private Helper Method
// * Gets the node at the specified Index
// * Similar Method to that of Lab 10 & 9.
// *
// * @param index
// * @return node at index
// */
// private Node<T> getNodeAtIndex(int index) {
// if (index < 0 || this.size() <= index) {
// throw new IndexOutOfBoundsException("No element exists at "
// + index);
// }
// Node<T> current = head.getNext();
// for (int i = 0; i < index; i++) {
// current = current.getNext();
// }
// return current;
// }


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
