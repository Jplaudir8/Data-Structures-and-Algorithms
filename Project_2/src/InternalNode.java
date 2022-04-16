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

/**
 * The InternalNode class represents an internal node of the DNAtree data
 * structure. This type of node is defined as a node with 5 children of type
 * FlyweightNode.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class InternalNode extends DNATreeNode {

    /**
     * Children nodes of the InternalNode.
     */
    private final DNATreeNode[] children = new DNATreeNode[5];

    /**
     * that instantiates the children nodes with an instance of FlyweightNode
     */
    public InternalNode() {
        for (int i = 0; i < children.length; i++) {
            children[i] = FlyweightNode.getFlyweight();
        }
    }


    /**
     * Insert method that implements the insertion of a LeafNode instance. The
     * FlyweightNode instance is replaced with LeadNode since a LeafNode our
     * only node that can hold data in itself.
     *
     * @param depth
     *            the current depth of the tree that is being traversed.
     * @param newLeafNode
     *            the LeafNode to be inserted
     * @param isNewNode
     *            is false when newLeafNode has to be reorganized within the
     *            DNAtree. Is true when newLeafNode is a new node for the
     *            DNAtree.
     */
    public void insert(int depth, LeafNode newLeafNode, boolean isNewNode) {
        int position = newLeafNode.getIndexOfByDepth(depth);
        DNATreeNode child = children[position];
        if (child instanceof FlyweightNode) {
            children[position] = newLeafNode;
            if (isNewNode) {
                System.out.println("sequence " + newLeafNode
                    + " inserted at level " + (depth + 1));
            }
            return;
        }
        if (child instanceof LeafNode) {
            if (position == 4) {
                System.out.println("sequence " + newLeafNode
                    + " already exists");
                return;
            }

            LeafNode leafNode = (LeafNode)child;
            if (leafNode.containsSequence(newLeafNode)) {
                System.out.println("sequence " + newLeafNode
                    + " already exists");
                return;
            }

            InternalNode internalNode = new InternalNode();
            children[position] = internalNode;
            internalNode.insert(depth + 1, leafNode, false);
            internalNode.insert(depth + 1, newLeafNode, true);
            return;
        }
        InternalNode toInsert = (InternalNode)child;
        toInsert.insert(depth + 1, newLeafNode, true);

    }


    /**
     * Remove method that takes given LeafNode starting at given level to remove
     *
     * @param depth
     *            the current depth of the tree that is being traversed.
     * @param nodeToRemove
     *            node to be removed
     * @return a child node if it is the only child after removal or null if
     *         there are more children remaining
     */
    public DNATreeNode remove(int depth, LeafNode nodeToRemove) {
        int position = nodeToRemove.getIndexOfByDepth(depth);
        DNATreeNode child = children[position];
        if (child instanceof FlyweightNode) {
            System.out.println("sequence " + nodeToRemove + " does not exist");
            return null;
        }

        if (child instanceof LeafNode) {
            LeafNode leafNode = (LeafNode)child;
            if (leafNode.getString().equals(nodeToRemove.getString())) {
                children[position] = FlyweightNode.getFlyweight();
                System.out.println("sequence " + nodeToRemove + " removed");
                return getNode();
            }
            else {
                System.out.println("sequence " + nodeToRemove
                    + " does not exist");
                return null;
            }
        }

        InternalNode internalleafNode = (InternalNode)child;
        DNATreeNode nodeValue = internalleafNode.remove(depth + 1,
            nodeToRemove);

        if (nodeValue != null) {
            children[position] = nodeValue;
            nodeValue = getNode();
        }
        return nodeValue;
    }


    /**
     * Searches all the sequences for this node then updates results.
     * 
     * @param res
     *            search all nodes visited
     */
    public void searchAll(SearchResults res) {
        res.incrementNodesVisited();
        for (DNATreeNode node : children) {
            node.searchAll(res);
        }
    }


    /**
     * Search method for all sequences matching given sequence/prefix then
     * updates results through depth.
     * 
     * @param depth
     *            the depth of the tree
     * @param sequence
     *            the sequence to be searched
     * @param match
     *            true if finds the match
     * @param res
     *            the SearchResults object
     */
    public void search(
        int depth,
        char[] sequence,
        boolean match,
        SearchResults res) {
        char ch = 0;
        if (depth < sequence.length) {
            ch = sequence[depth];
        }

        if (ch == 0 && !match) {
            searchAll(res);
            return;
        }

        res.incrementNodesVisited();
        DNATreeNode child = null;
        switch (ch) {
            case 'A':
                child = children[0];
                break;
            case 'C':
                child = children[1];
                break;
            case 'G':
                child = children[2];
                break;
            case 'T':
                child = children[3];
                break;
            case 0:
                child = children[4];
                break;
            default:
                // do nothing
        }

        if (child instanceof FlyweightNode) {
            res.incrementNodesVisited();
            return;
        }

        if (child instanceof LeafNode) {
            res.incrementNodesVisited();
            LeafNode leafNode = (LeafNode)child;
            if (match) {
                if (leafNode.getString().equals(String.valueOf(sequence))) {
                    res.addResult(sequence);
                }
            }
            else {
                if (leafNode.getString().startsWith(String.valueOf(sequence))) {
                    res.addResult(leafNode.getString().toCharArray());
                }
            }
            return;
        }

        child.search(depth + 1, sequence, match, res);
    }


    /**
     * Helper method for remove() that returns the node value.
     * 
     * @return the node value
     */
    public DNATreeNode getNode() {
        int value = 0;
        DNATreeNode returnedNode = null;
        for (DNATreeNode node : children) {
            if (!(node instanceof FlyweightNode)) {
                value += 1;
                if (value > 1) {
                    return null;
                }
                returnedNode = node;
            }
        }
        if (returnedNode instanceof InternalNode) {
            return null;
        }
        return returnedNode;
    }


    /**
     * Print out the DNA tree, including both the node structure and the
     * sequences it contains
     * 
     * @param depth
     *            prints the node at the depth
     */
    @Override
    public void printNode(int depth) {
        System.out.println("I");
        for (DNATreeNode node : children) {
            node.print(depth + 1);
        }

    }


    /**
     * Output is identical to that of the print command, except that the length
     * of the sequence is printed after the sequence for all sequences stored in
     * the database.
     * 
     * @param depth
     *            prints the node at the depth
     */
    @Override
    public void printNodeLengths(int depth) {
        System.out.println("I");
        for (DNATreeNode node : children) {
            node.printLengths(depth + 1);
        }

    }


    /**
     * Output is identical to that of the print command, except that the letter
     * breakdown (by percentage) of the sequence is printed after the sequence
     * for all sequences stored in the database.
     * 
     * @param depth
     *            prints the node at the depth
     */
    @Override
    public void printNodeStats(int depth) {
        System.out.println("I");
        for (DNATreeNode node : children) {
            node.printStats(depth + 1);
        }

    }

}
