import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

class SkipList<K extends Comparable<K>, E> implements Dictionary<K, E>, Iterable<E> {

	public class SkipNode<K extends Comparable<K>, E> {
		private KVPair<K, E> rec;
		private SkipNode<K, E>[] forward;

		public E element() {
			return rec.value();
		}

		public K key() {
			return rec.key();
		}

		@SuppressWarnings("unchecked")
		public SkipNode(K key, E elem, int level) {
			rec = new KVPair<K, E>(key, elem);
			forward = new SkipNode[level + 1];
			for (int i = 0; i < level; i++)
				forward[i] = null;
		}

		public String toString() {
			return rec.toString();
		}
	}

	private SkipNode<K, E> head;
	private int level;
	private int size;
	static private Random ran = new Random(); // Hold the Random class object

	public SkipList() {
		head = new SkipNode<K, E>(null, null, 0);
		level = -1;
		size = 0;
	}

	// Pick a level using a geometric distribution
	int randomLevel() {
		int lev;
		for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) // ran is random generator
			; // Do nothing
		return lev;
	}

	private void adjustHead(int newLevel) {
		SkipNode<K, E> temp = head;
		head = new SkipNode<K, E>(null, null, newLevel);
		for (int i = 0; i <= level; i++)
			head.forward[i] = temp.forward[i];
		level = newLevel;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/** Insert a key, element pair into the skip list */
	@Override
	public void insert(K key, E elem) {
		int newLevel = randomLevel(); // New node's level
		if (newLevel > level) // If new node is deeper
			adjustHead(newLevel); // adjust the header
		// Track end of level
		SkipNode<K, E>[] update = new SkipNode[level + 1];
		SkipNode<K, E> x = head; // Start at header node
		for (int i = level; i >= 0; i--) { // Find insert position
			while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0))
				x = x.forward[i];
			update[i] = x; // Track end at level i
		}
		x = new SkipNode<K, E>(key, elem, newLevel);
		for (int i = 0; i <= newLevel; i++) { // Splice into list
			x.forward[i] = update[i].forward[i]; // Who x points to
			update[i].forward[i] = x; // Who points to x
		}
		size++; // Increment dictionary size
	}

//	@Override
//	public E remove(K key) {
//		SkipNode<K, E> currNode = head; // Dummy header node
//		SkipNode<K, E> prevNode = null;
//		for (int i = level; i >= 0; i--) { // For each level...
//			while ((currNode.forward[i] != null) && (currNode.forward[i].key().compareTo(key) < 0)) { // go forward
//				prevNode = currNode;
//				currNode = currNode.forward[i]; // Go one last step
//			}
//		}
//		prevNode = currNode;
//		currNode = currNode.forward[0]; // currNode is now node to remove
//
//		// if current node is null, we did not find anything
//		if (currNode == null) {
//			return null;
//		}
//
//		// check all references that prevNode has
//		for (int i = 0; i < prevNode.forward.length; i++) {
//			// if at ith level prevNode points to currNode, re reference to next
//			if (prevNode.forward[i] == currNode) {
//				prevNode.forward[i] = currNode.forward[i];
//			}
//		}
//		// update size
//		this.size--;
//		return currNode.element();
//	}
	
	@Override
	public E remove(K key) {
		SkipNode<K, E> x = head;
        SkipNode<K, E> y = searchNode(key);
        if (y == null) // if no node can be found
            return null;
        SkipNode<K, E>[] replace = y.forward;
        int currentLevel = x.forward.length - 1; // Gets level 
        while (x != null) { // For traversing the skip list

            for (int i = currentLevel; i >= 0; i--) { // traverses list
                if (x.forward[i] != null) {
                    /*
                     * Every time there is a reference to the node as given
                     * by the key, replaces the pointers such that node is
                     * removed 
                     */
                    if ((x.forward[i] == y))
                        x.forward[i] = replace[i];
                }
            }

            x = x.forward[0]; // Move node forward by one
            if (x != null) // Gets new depth if x does not equal null
                currentLevel = x.forward.length - 1;
        }
        
        size--;
        return y.element(); // If removal is successful, return skip node
	}
	
	private SkipNode<K, E> searchNode(K searchKey) {
        SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) // For each level...
            while ((x.forward[i] != null) && // go forward
                    (searchKey.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i]; // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (searchKey.compareTo(x.key()) == 0))
            return x; // Node has been found
        else
            return null; // Not found
    }
	
	public E removeByElement(E element) {
		SkipNode<K, E> currNode = this.head;
		 
		SkipNode<K, E> toRemove = searchSkipNode(element);
		
		if (toRemove == null) {
			return null; // not found
		}
		
//		SkipNode<K, E>[] replace = toRemove.forward;
		
        int currentLevel = currNode.forward.length - 1;
        while (currNode != null) {

            for (int i = currentLevel; i >= 0; i--) { // traverses the list
                if (currNode.forward[i] != null) {
                    /*
                     * Every time there is a reference to the node as given
                     * by the key, replaces the pointers such that node is
                     * removed 
                     */
                    if ((currNode.forward[i] == toRemove))
                        currNode.forward[i] = toRemove.forward[i];
                }

            }

            currNode = currNode.forward[0]; // Moves node forward by one
            if (currNode != null) // Gets new depth if x does not equal null
                currentLevel = currNode.forward.length - 1;
        }

        this.size--;
        return element;
	}
	
	private SkipNode<K, E> searchSkipNode(E element){
		SkipNode<K, E> node = head; // Start at start of list
        int currentLevel = level - 1;
        
        /*
         * This is for traversing the skip list when the largest depth size
         * is not one
         */
        if (node.forward.length != 1) {
            while (node != null) { // Traverses skip list

                // Checks each pointer in skip node
                for (int i = currentLevel; i >= 0; i--) { 
                    if (node.forward[i] != null) {
                        // if it found a matching node
                        if (element.equals(node.forward[i].element())) {
                            node = node.forward[i];
                            return node;
                        }
                    }
                }

                node = node.forward[0]; //moves one node
                /*
                 * If there are more nodes in the skip list, 
                 */
                if (node != null) { 
                    currentLevel = node.forward.length - 1;
                }
            }
        } 
        /*  
         * For traversing the skip list when each node of the skip list
         * only has a depth of one
         */
        else {
            while (node != null) {
                if (node.forward[0] == null)
                    return null; // if not found
                if (element.equals(node.forward[0].element())) { // if found
                    node = node.forward[0]; // moves one node
                    return node; //returns result
                }
                node = node.forward[0]; //moves one node forward

            }
        }
        return null; // if not found
	}
	
	@Override
	public SinglyLinkedList<E> find(K key) {
		SkipNode<K, E> currNode = head; // Dummy header node
		for (int i = level; i >= 0; i--) // For each level...
			while ((currNode.forward[i] != null) && (currNode.forward[i].key().compareTo(key) < 0)) // go forward
				currNode = currNode.forward[i]; // Go one last step
		currNode = currNode.forward[0]; // Move to actual record, if it exists
		SinglyLinkedList<E> rectList = new SinglyLinkedList<>();
		// Collect all matches
		while ((currNode != null) && (currNode.key().compareTo(key) == 0)) {
			rectList.addLast(currNode.element());
			currNode = currNode.forward[0];
		}
		return rectList.size() > 0 ? rectList : null;
	}

	@Override
	public int size() {
		return this.size;
	}

	public void printNodes() {
		SkipNode currNode = this.head;
		System.out.println("SkipList dump:");
		while (currNode != null) {
			System.out.println("Node has depth " + currNode.forward.length + ", Value (" + currNode.element() + ")");
			currNode = currNode.forward[0];
		}
		System.out.println("SkipList size is: " + size());
	}

	@Override
	public Iterator<E> iterator() {
		return new SkipListIterator();
	}

	public class SkipListIterator implements Iterator<E> {

		SkipNode currNode = head;

		@Override
		public boolean hasNext() {
			if (currNode == null && head.forward[0] != null) {
				return true;
			} else if (currNode != null) {
				return currNode.forward[0] != null;
			}

			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (currNode == null && head.forward[0] != null) {
				currNode = head.forward[0];
				return (E) currNode.element();
			} else if (currNode != null) {
				currNode = currNode.forward[0];
				return (E) currNode.element();
			}
			throw new NoSuchElementException();
		}

	}
}