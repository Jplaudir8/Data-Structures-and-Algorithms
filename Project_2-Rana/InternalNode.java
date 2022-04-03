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
 * Tests the DNAtree class
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class InternalNode extends DNATreeNode {

    private final DNATreeNode[] children = new DNATreeNode[5];

    /**
     * Constructor that populates the root with 5 children
     */
    public InternalNode() {
        for (int i = 0; i < children.length; i++) {
            children[i] = FlyweightNode.getFlyweight();
        }
    }


    /**
     * Insert method that implements the insertion of a LeafNode.
     *
     * @param level
     *            the level in the tree currently
     * @param newnode
     *            the LeafNode to be inserted
     * @param isNode
     *            true if new node is being inserted false when already existing
     *            node is reorganized in tree
     */
    public void insert(int level, LeafNode newnode, boolean isNode) {
        char ch = newnode.getCharAt(level);
        int position = 0;
        switch (ch) {
            case 'A':
                position = 0;
                break;
            case 'C':
                position = 1;
                break;
            case 'G':
                position = 2;
                break;
            case 'T':
                position = 3;
                break;
            case '$':
                position = 4;
                break;
            default:
                // do nothing
        }
        DNATreeNode child = children[position];
        if (child instanceof FlyweightNode) {
            children[position] = newnode;
            if (isNode) {
                System.out.println("sequence " + newnode + " inserted at level "
                    + (level + 1));
            }
            return;
        }
        if (child instanceof LeafNode) {
            if (position == 4) {
                System.out.println("sequence " + newnode + " already exists");
                return;
            }

            LeafNode leafNode = (LeafNode)child;
            if (leafNode.containsSequence(newnode)) {
                System.out.println("sequence " + newnode + " already exists");
                return;
            }

            InternalNode internalNode = new InternalNode();
            children[position] = internalNode;
            internalNode.insert(level + 1, leafNode, false);
            internalNode.insert(level + 1, newnode, true);
            return;
        }
        InternalNode toInsert = (InternalNode)child;
        toInsert.insert(level + 1, newnode, true);

    }

// public void insert(LeafNode newLeafNode) {
// insertDFS(0, newLeafNode);
// }
//
//
// // helper method that inserts recursively
// private void insertDFS(int depth, LeafNode newLeafNode) {
//
// int index = newLeafNode.getIndexOfByDepth(depth);
// DNATreeNode internalNodeChild = children[index];
//
// if (internalNodeChild instanceof FlyweightNode) {
// internalNodeChild = newLeafNode; // root becomes a LeafNode
// System.out.println("sequence " + newLeafNode + " inserted at level "
// + (depth + 1));
// }
// else if (internalNodeChild instanceof LeafNode) {
// if (index == 4) {
// System.out.println("sequence " + newLeafNode
// + " already exists");
// return;
// }
//
// LeafNode nodeToLeaf = (LeafNode)internalNodeChild;
// if (nodeToLeaf.containsSequence(newLeafNode)) {
// System.out.println("sequence " + newLeafNode
// + " already exists");
// return;
// }
//
// InternalNode newInternalNode = new InternalNode();
// internalNodeChild = newInternalNode;
// newInternalNode.insertDFS(depth + 1, nodeToLeaf);
// newInternalNode.insertDFS(depth + 1, newLeafNode);
//
// }
// else {
// InternalNode newInternalNode = (InternalNode)internalNodeChild;
// newInternalNode.insertDFS(depth + 1, newLeafNode);
// }
//
// }

// /**
// * Remove method that takes given LeafNode starting at given level to remove
// *
// * @param level
// * the level in the tree currently
// * @param nodeRemove
// * node to be removed
// * @return a child node if it is the only child after removal or null if
// * there
// * are more children remaining
// */
// public DNATreeNode remove(LeafNode nodeRemove) {
// return remove(0, nodeRemove);
//
// }

// /**
// * Remove method that takes given LeafNode starting at given level to
// * remove
// *
// * @param level
// * the level in the tree currently
// * @param nodeRemove
// * node to be removed
// * @return a child node if it is the only child after removal or null if
// * there
// * are more children remaining
// */
// public DNATreeNode remove(int depth, LeafNode nodeRemove) {
// int index = nodeRemove.getIndexOfByDepth(depth);
// DNATreeNode internalNodeChild = children[index];
// if (internalNodeChild instanceof FlyweightNode) {
// System.out.println("sequence " + nodeRemove + " does not exist");
// return null;
// }
//
// // Case: The child is a leaf; replace with empty, and check for merge
// if (internalNodeChild instanceof LeafNode) {
// LeafNode nodeToleaf = (LeafNode)internalNodeChild;
// if (nodeToleaf.getString().equals(nodeRemove
// .getString())) {
// children[index] = FlyweightNode.getFlyweight();
// System.out.println("sequence " + nodeRemove + " removed");
// return getNode();
// }
// else {
// System.out.println("sequence " + nodeRemove
// + " does not exist");
// return null;
// }
// }
//
// // Case: The child is an InternaleafNode
// DNATreeNode newInternalNode = internalNodeChild;
// DNATreeNode nodeValue = ((InternalNode)newInternalNode).remove(depth
// + 1, nodeRemove);
//
// // Perform merge
// if (nodeValue != null) {
// // replace internal with leaf since this internal has just one
// // leaf
// children[index] = nodeValue;
// nodeValue = getNode();
// }
// return nodeValue;
// }


    /**
     * Remove method that takes given LeafNode starting at given level to remove
     *
     * @param level
     *            the level in the tree currently
     * @param nodeRemove
     *            node to be removed
     * @return a child node if it is the only child after removal or null if
     *         there
     *         are more children remaining
     */
    public DNATreeNode remove(int level, LeafNode nodeRemove) {
        char ch = nodeRemove.getCharAt(level);
        int position = 0;
        switch (ch) {
            case 'A':
                position = 0;
                break;
            case 'C':
                position = 1;
                break;
            case 'G':
                position = 2;
                break;
            case 'T':
                position = 3;
                break;
            case '$':
                position = 4;
                break;
            default:
                // do nothing
        }
        DNATreeNode child = children[position];
        if (child instanceof FlyweightNode) {
            System.out.println("sequence " + nodeRemove + " does not exist");
            return null;
        }

        // Case: The child is a leaf; replace with empty, and check for merge
        if (child instanceof LeafNode) {
            LeafNode leafNode = (LeafNode)child;
            if (leafNode.getString().equals(nodeRemove.getString())) {
                children[position] = FlyweightNode.getFlyweight();
                System.out.println("sequence " + nodeRemove + " removed");
                return getNode();
            }
            else {
                System.out.println("sequence " + nodeRemove
                    + " does not exist");
                return null;
            }
        }

        // Case: The child is an InternaleafNode
        InternalNode internaleafNode = (InternalNode)child;
        DNATreeNode nodeValue = internaleafNode.remove(level + 1, nodeRemove);

        // Perform merge
        if (nodeValue != null) {
            // replace internal with leaf since this internal has just one
            // leaf
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
     * updates
     * results through depth.
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
        if (depth >= 0 && depth < sequence.length) {
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
            case '$':
                child = children[4];
                break;
            default:
                // do nothing
        }

        // Perform an match-search match
// if (ch == 0 && match) {
// res.incrementNodesVisited();
// if (child instanceof LeafNode) {
// LeafNode leafNode = (LeafNode)child;
// res.addResult(leafNode.getStringValue().toCharArray());
// }
// return;
// }

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

        // InternaleafNode: progress to next level
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
     * sequences
     * it contains
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
     * of
     * the sequence is printed after the sequence for all sequences stored in
     * the
     * database.
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
     * for
     * all sequences stored in the database.
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
