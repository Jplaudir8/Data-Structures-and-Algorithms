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
		
	}
	
}
