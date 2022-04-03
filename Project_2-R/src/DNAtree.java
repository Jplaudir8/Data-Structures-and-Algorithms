
// import java.io.File;
// import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
// import java.util.Scanner;

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
 * The DNAtree class represents a Trie-like data structure that stores DNA sequences.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class DNAtree {
	
	/**
	 * Represents a node of the trie data structure.
	 */
    private static DNATreeNode root;

    /**
     * Assigns a FlyweightNode instance to the root.
     */
    public DNAtree() {
        root = FlyweightNode.getFlyweight();
    }


    /**
     * The main method that processes input file through scanFile.
     * 
     * @param args
     *            input file
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        DNAtree tree = new DNAtree();
// try {
// Scanner sc = new Scanner(new File(args[0])); // Create new scanner
// while (sc.hasNextLine()) { // While the scanner has information to
// // read
// String line = sc.nextLine();
// if (!line.isEmpty() && line.length() > 2) {
// // Step 2 - remove any spaces
// String trimmedLine = trimWhite(line);
// if (trimmedLine.length() > 1) {
// executeTreeOperation(trimmedLine, tree);
// }
// }
// sc.close();
// }
// }
// catch (Exception e) {
// e.printStackTrace();
// }
        Scanner scannedFile = null;
        scannedFile = scanFile(args[0]);
        while (scannedFile.hasNextLine()) {
            String line = scannedFile.nextLine();
            if (!line.isEmpty() && line.length() > 2) {
                String trimmedLine = trimWhite(line);
                if (trimmedLine.length() > 1) {
// executeTreeOperation(trimmedLine, tree);
                    Command cm = new Command(trimmedLine, tree);
                    cm.executeTreeOperation();
                }
            }
        }
        scannedFile.close();
    }

    /**
     * Trims any extra whitespace present either at the beginning, end or in between commands.
     * 
     * @param line a String containing the commands to be trimmed.
     * @return a String with the extra whitespace removed.
     */
    private static String trimWhite(String line) {
        String processedLine = line.trim();
        processedLine = processedLine.replaceAll("\\s+", " ");
        return processedLine;
    }


    /**
     * Scans the file to be read.
     * 
     * @param filename
     *            name of the file to be read
     * @throws FileNotFoundException
     *             if file not found
     * @return scanned file as a scanner
     */
    public static Scanner scanFile(String filename)
        throws FileNotFoundException {
        FileReader file = new FileReader(filename);
        return new Scanner(file);
    }

// /**
// * Execution of the commands
// *
// * @param currentLine
// * to be read
// * @param tree
// * the DNATree tree
// */
// static void executeTreeOperation(String currentLine, DNAtree tree) {
// if (currentLine.trim().length() == 0) {
// return;
// }
// String[] currentLineArr = currentLine.trim().split(" +");
// if (currentLineArr[0].equals("insert")) {
// tree.insert(currentLineArr[1]);
//
// }
// else if (currentLineArr[0].equals("remove")) {
// tree.remove(currentLineArr[1]);
//
// }
// else if (currentLineArr[0].equals("search")) {
// tree.search(currentLineArr[1]);
//
// }
// else if (currentLineArr[0].equals("print")) {
// if (currentLineArr.length == 1) {
// // print
// System.out.println("tree dump:");
// root.print(0);
// }
// else if (currentLineArr[1].equals("lengths")) {
// // print lengths
// System.out.println("tree dump:");
// root.printLengths(0);
// }
// else if (currentLineArr[1].equals("stats")) {
// // print stats
// System.out.println("tree dump:");
// root.printStats(0);
// }
// }
// }


    /**
     * Prints tree dump.
     */
    public void print() {
        System.out.println("tree dump:");
        root.print(0);
    }


    /**
     * Prints tree dump along with length of each node content.
     */
    public void printLengths() {
        System.out.println("tree dump:");
        root.printLengths(0);
    }


    /**
     * Prints tree dump along with statistical distribution of the letter breakdown of every sequence.
     */
    public void printStats() {
        System.out.println("tree dump:");
        root.printStats(0);
    }


    /**
     * Inserts a sequence into the tree. The method will try to find the closest
     * node for the sequence to live at without ambiguity, relocating the
     * other
     * nodes as needed to provide space efficiency.
     * 
     * @param sequence
     *            the new DNA sequence to insert
     */
    public void insert(String sequence) {
    	
    	// ADDED IF
    	if (sequence == null) {
    		return;
    	}
    	
    	if (!sequenceIsValid(sequence.toCharArray())) {
    		return;
    	}

        LeafNode newnode = new LeafNode(sequence);
        
        if (root instanceof FlyweightNode) {
            root = newnode;
            System.out.println("sequence " + sequence + " inserted at level "
                + 0);
        }

        else if (root instanceof LeafNode) {
            LeafNode leafnode = (LeafNode)root;
            if (leafnode.containsSequence(newnode)) {
                System.out.println("sequence " + sequence + " already exists");
                return;
            }
            if (leafnode.getSequence().equals(sequence)) {
                System.out.println("sequence " + sequence + " already exists");
                return;
            }
            root = new InternalNode();
            InternalNode internalLeafNode = (InternalNode)root;
            internalLeafNode.insert(0, leafnode, false);
            internalLeafNode.insert(0, newnode, true);
        }
        // ADDED ELSE IF
        else if (root instanceof InternalNode) {
            InternalNode internalLeafNode = (InternalNode)root;
            internalLeafNode.insert(0, newnode, true);
        }
    }


    /**
     * Removes the given sequence in this DNAtree.
     * 
     * @param sequence
     *            string version of sequence value to be removed from tree
     */
    public void remove(String sequence) {

        if (root instanceof FlyweightNode) {
            System.out.println("sequence " + sequence + " does not exist");
            return;
        }

        else if (root instanceof LeafNode) {
            LeafNode leafRoot = (LeafNode)root;
            if (leafRoot.getString().equals(sequence)) {
                root = FlyweightNode.getFlyweight();
                System.out.println("sequence " + sequence + " removed");
            }

            else {
                System.out.println("sequence " + sequence + " does not exist");
            }
        }

        else {
            InternalNode internalLeafNode = (InternalNode)root;
            LeafNode leafToRemove = new LeafNode(sequence);
            DNATreeNode node = internalLeafNode.remove(0, leafToRemove);
            if (node != null) {
                root = node;
            }
        }
    }

    // ADDED THIS
    /**
     * Validates that given input is a valid DNA sequence.
     * @param sequence
     * @return
     */
    private static boolean sequenceIsValid(char[] sequence) {
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
    
    /**
     * Searches for the given sequence in this DNAtree.
     * 
     * @param sequence
     *            string version of sequence to be searched in this DNAtree.
     */
    public void search(String sequence) {

        boolean match = sequence.endsWith("$");

        if (match) {
            sequence = sequence.substring(0, sequence.length() - 1);
        }
        SearchResults res = new SearchResults();
        char[] sequenceChar = sequence.toCharArray();
        root.search(0, sequenceChar, match, res);
        System.out.println("# of nodes visited: " + res.getNumNodesVisited());
        if (res.getResults().size() == 0) {
            System.out.println("no sequence found");
        }
        else {
            for (char[] seq : res.getResults()) {
                System.out.println("sequence: " + String.valueOf(seq));
            }
        }
    }

}
