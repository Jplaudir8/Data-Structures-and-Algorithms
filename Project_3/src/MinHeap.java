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
 * Minheap manages the data for external sort
 * up to date again
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 * @param <E>
 *            the generic type to be stored in the heap
 */
public class MinHeap<E extends Comparable<E>> {
    // Pointer to the heap array
    private E[] heap;
    // Maximum size of the heap
    private int maxSize;
    // Number of things now in the heap
    private int n;

    /**
     * Constructor supporting preloading of heap contents
     * 
     * @param h
     *            pointer to the heap array
     * @param num
     *            the # of items in the heap currently
     * @param max
     *            the maximum size of the heap
     */
    public MinHeap(E[] h, int num, int max) {
        heap = h;
        n = num;
        maxSize = max;
        buildheap();
    }


    /**
     * Gets the current number of items in the heap
     * 
     * @return n the current size of the heap
     *
     */
    public int heapSize() {
        return n;
    }


    /**
     * Set the heap size
     * 
     * @param size
     *            new heap size
     */
    public void setHeapSize(int size) {
        n = size;
    }


    /**
     * Gets the max heap size
     * 
     * @return maxSize the maximum size of the heap
     *
     */
    public int getMaxHeapSize() {
        return maxSize;
    }


    /**
     * Returns the minimum element in the heap
     * 
     * @return minimum element
     */
    public E getMin() {
        if (isEmpty()) {
            return null;
        }
        else {
            return heap[0];
        }
    }


    /**
     * Returns the item at the position
     * 
     * @param pos
     *            the given position
     * @return the item at the position
     */
    public E getPosition(int pos) {
        return heap[pos];
    }


    /**
     * Check if heap is empty
     * 
     * @return is heap empty
     */
    public boolean isEmpty() {
        return (n == 0);
    }


    /**
     * Check if heap is empty
     * 
     * @return is heap empty
     */
    public boolean isFull() {
        if (n >= maxSize) {
            System.out.println("heap is Full");
            return true;
        }
        return false;
    }


    /**
     * Indicates weather the item is in leaf position
     * 
     * @param pos
     *            the position of the item
     * @return true the if pos is a leaf position, false otherwise
     *
     */
    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }


    /**
     * Demonstrates the position of the left child of the current position
     * 
     * @param pos
     *            the position of the item
     * @return position for left child of pos
     *
     */
    public int leftChild(int pos) {
        if (pos >= n / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    /**
     * Demonstrates the position of the right child of the current position
     * 
     * @param pos
     *            the position of the item
     * @return position for right child of pos
     *
     */
    public int rightChild(int pos) {
        if (pos >= (n - 1) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }


    /**
     * Demonstrates the position of the parent of the current position
     * 
     * @param pos
     *            the position of the item
     * @return position for parent of pos
     *
     */
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * Inserts an item into the heap
     * 
     * @param item
     *            the item to be inserted
     * @return status of insert process
     */
    public int insert(E item) {
        if (n >= maxSize) {
            return -1;
        }
        int curr = n++;
        heap[curr] = item;
        while ((curr != 0) && (heap[curr].compareTo(heap[parent(curr)]) < 0)) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
        return 0;
    }


    /**
     * Puts the item in the correct position
     * 
     * @param pos
     *            the current position of the item
     *
     */
    public void siftdown(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return;
        } // Illegal position
        while (!isLeaf(pos)) {
            int l = leftChild(pos);
            int r = rightChild(pos);
            if ((l < (n - 1)) && (heap[l].compareTo(heap[r]) > 0)) {
                l = r;
            }
            if (heap[pos].compareTo(heap[l]) <= 0) {
                return;
            }
            swap(pos, l);
            pos = l;
        }
    }


    /**
     * Builds up the contents of Heap
     */
    public void buildheap() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    /**
     * Removes and returns the minimum item
     * 
     * @return the removed minimum item in the heap
     *
     */
    public E removeMin() {
        if (n == 0) {
            return null;
        } // Removing from empty heap
        heapSwap(heap, 0, --n); // Swap maximum with last item
        siftdown(0); // Put new heap root val in correct place

        return heap[n];
    }


    /**
     * Removes and returns the item at the current position
     * 
     * @param pos
     *            the current position of the item
     * @return the removed item at the given position
     */
    public E remove(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return null;
        } // Illegal heap position
        if (pos == (n - 1)) {
            n--;
        } // Last element, no work to be done
        else {
            heapSwap(heap, pos, --n); // Swap with last item
            update(pos);
        }
        return heap[n];
    }


    /**
     * Modifies the item at the given position
     * 
     * @param pos
     *            the given position
     * @param item
     *            the item to be modified
     *
     */
    public void modify(int pos, E item) {
        if ((pos < 0) || (pos >= n)) {
            return;
        } // Illegal heap position
        heap[pos] = item;
        update(pos);
    }


    /**
     * Updates the item at the position, and restores the heap property
     * 
     * @param pos
     *            the given position
     *
     */
    public void update(int pos) {
        // If it is a big item, push it up
        while ((pos > 0) && (heap[parent(pos)].compareTo(heap[pos]) > 0)) {
            heapSwap(heap, pos, parent(pos));
            pos = parent(pos);
        }
        siftdown(pos); // If it is little, push down
    }


    /**
     * Helper method that swaps elements between the given positions
     */
    private void heapSwap(E[] heapSwap, int i, int j) {
        E temp = heapSwap[i];
        heapSwap[i] = heapSwap[j];
        heapSwap[j] = temp;
    }


    /**
     * Swaps elements in the heap
     */
    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    /**
     * Replaces the root with the given item
     * 
     * @param item
     *            item for replacement
     */
    public void replaceRoot(E item) {
        if (isEmpty()) {
            return;
        }
        swap(0, --n);
        heap[n] = item;
        if (n > 0) {
            siftdown(0);
        }
    }


    /**
     * Insert the item at root
     * 
     * @param item
     *            item to be inserted
     * 
     */
    public void insertAtRoot(E item) {
        if (isEmpty()) {
            return;
        }
        heap[0] = item;
        siftdown(0);
    }
}
