import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
 * 
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class DNAtree {

	private DNATreeNode root;

	public DNAtree() {
		root = FlyweightNode.getFlyweight();
	}

	
	/**
	 * Inserts a new sequence in this DNATree.
	 * 
	 * @param newSequence
	 * @param idx             
	 */
	public void insert(String newSequence) {
		
		if (newSequence == null) {
			return;
		}
		
		// check sequence has valid characters
		if (!validSequence(newSequence.toCharArray())) {
			return;
		}
		
		LeafNode newNode = new LeafNode();
		newNode.insert(newSequence.toCharArray());
		
		
		// if root node is a FlyweightNode, we insert sequence directly.
		// root must become a LeafNode
		if (root instanceof FlyweightNode) {
			root = newNode; // root becomes a LeafNode
			System.out.print("Sequence " + newSequence + " inserted at level 0");
			
		} else if (root instanceof LeafNode) {
			// if root node is a LeafNode already, we need to split.
			LeafNode rootToLeaf = (LeafNode) root;
			
			// if sequence already exists, terminate function
			if (rootToLeaf.containsSequence(newNode)) {
				System.out.print("Sequence " + newSequence + " already exists");
				return;
			}
			
			//root = new InternalNode(); // split
			
			InternalNode newInternalRoot = (InternalNode) root;
			newInternalRoot.insert(rootToLeaf); // move leaf down
			newInternalRoot.insert(newNode); // insert new node
			
			
		} else if (root instanceof InternalNode) {
			InternalNode internalNode = (InternalNode) root;
			internalNode.insert(newNode);
		}
		
	}
	
	
	public void remove(String sequenceToRemove) {
		
	}
	
	private boolean validSequence(char[] sequence) {
		
		if (sequence == null) {
			return false;
		}
		
		if (sequence.length == 0) {
			return false;
		}
		
		String DNASequence = "ACGT";
		
		for (Character currChar : sequence) {
			if (!DNASequence.contains(currChar.toString().toUpperCase())) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void executeTreeOperation(String currentLine) {
		
		if (currentLine.trim().length() == 0) {
			return;
		}
		
		// separate every element and store them in array
		// this array should always contain only two elements
		String[] currentLineArr = currentLine.trim().split(" +");
		
		if (currentLineArr[0].equals("insert")) {
			System.out.println("we insert " + currentLineArr[1]);
			
		} else if (currentLineArr[0].equals("remove")) {
			System.out.println("we remove " + currentLineArr[1]);
			
		} else if (currentLineArr[0].equals("search")) {
			System.out.println("we search " + currentLineArr[1]);
			
		} else if (currentLineArr[0].equals("print")) {
			if (currentLineArr.length == 1) {
				// print alone
				System.out.println("we print alone");
			} else if (currentLineArr[1].equals("lengths")) {
				// print lengths
				System.out.println("we print lengths");
			} else if (currentLineArr[1].equals("stats")) {
				// print stats
				System.out.println("we print stats");
			}
		}
	}
	
	/**
	 * 
	 * @param args the input file of commands
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Scanner sc = new Scanner(new File(args[0])); // Create new scanner
			while (sc.hasNextLine()) { // While the scanner has information to
										// read
				executeTreeOperation(sc.nextLine());
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
