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

	private DNATreeNode[] internalNodes;

	public InternalNode() {
		internalNodes = new DNATreeNode[5];

		for (int i = 0; i < 5; i++) {
			internalNodes[i] = FlyweightNode.getFlyweight();
		}
	}

	public void insert(LeafNode newLeafNode) {
		insertDFS(0, newLeafNode);
	}
	
	// helper method that inserts recursively
	private void insertDFS(int depth, LeafNode newLeafNode) {
		
		int index = newLeafNode.getIndexOfByDepth(depth);
		DNATreeNode internalNodeChild = internalNodes[index];
		
		if (internalNodeChild instanceof FlyweightNode) {
			internalNodeChild = newLeafNode; // root becomes a LeafNode
			System.out.print("Sequence " + newLeafNode.toString() + " inserted at level " + depth + 1);
		} else if (internalNodeChild instanceof LeafNode) {
			if (index == 4) {
				System.out.print("Sequence "+ newLeafNode.toString() + " already exists");
				return;
			}
			
			LeafNode nodeToLeaf = (LeafNode) internalNodeChild;
			if (nodeToLeaf.containsSequence(newLeafNode)) {
				System.out.println("sequence " + newLeafNode.toString() + " already exists");
				return;
			}
			
			InternalNode newInternalNode = new InternalNode();
			internalNodeChild = newInternalNode;
			newInternalNode.insertDFS(depth + 1, nodeToLeaf);
			newInternalNode.insertDFS(depth + 1, newLeafNode);
			
		} else {
			InternalNode newInternalNode = (InternalNode) internalNodeChild;
			newInternalNode.insertDFS(depth + 1, newLeafNode);
		}
		
	}

	@Override
	public void print(int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print(" ");
		}
		
		System.out.print("I");
		for (DNATreeNode currInternal : internalNodes) {
			currInternal.print(depth + 1);
        }
	}
	
	
	
	
	
}
