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
 * The LeafNode class represents a leaf node of the DNATree data structure. This
 * type of leaf node stores a DNA Sequence.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class LeafNode extends DNATreeNode {

    /**
     * DNA sequence in form of a primitive array of characters.
     */
    private final char[] dnaSequence;

    /**
     * Constructor initializes the dnaSequence field
     * 
     * @param sequence
     *            the dnaSequence initialized
     */
    public LeafNode(String sequence) {
        dnaSequence = sequence.toCharArray();
    }


    /**
     * Checks if the sequence contains leafNode's sequence
     * 
     * @param nodeValue
     *            the leafNode's sequence
     * @return true if the sequences are the same
     */
    public boolean containsSequence(LeafNode nodeValue) {
        return String.valueOf(this.dnaSequence).equals(String.valueOf(
            nodeValue.dnaSequence));
    }


    /**
     * Returns the string of dnaSequence
     * 
     * @return String the string of dnaSequence
     */
    public String toString() {
        return String.valueOf(dnaSequence);
    }


    /**
     * Converts string value
     * 
     * @return converts the value to string
     */
    public String getString() {
        return String.valueOf(dnaSequence);
    }


    /**
     * Finds the right index for the a leaf node being traversed.
     *
     *
     * @param depth
     *            the depth where the character in dnaSequenceis located
     * @return the integer value of the current level of the character
     */
    public int getIndexOfByDepth(int depth) {
        String dNASequence = "ACGT$";
        if (depth < dnaSequence.length) {
            return dNASequence.indexOf(dnaSequence[depth]);
        }
        return 4;

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
        System.out.println(String.valueOf(dnaSequence));

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
        System.out.println(String.valueOf(dnaSequence) + " "
            + dnaSequence.length);

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
        double total = dnaSequence.length;
        int aCount = 0;
        int cCount = 0;
        int gCount = 0;
        int tCount = 0;
        for (char currLetter : dnaSequence) {
            if (currLetter == 'A') {
                aCount++;
            }
            if (currLetter == 'C') {
                cCount++;
            }
            if (currLetter == 'G') {
                gCount++;
            }
            if (currLetter == 'T') {
                tCount++;
            }
        }

        String statsA = String.format("%.2f", aCount / total * 100);
        String statsC = String.format("%.2f", cCount / total * 100);
        String statsG = String.format("%.2f", gCount / total * 100);
        String statsT = String.format("%.2f", tCount / total * 100);

        System.out.println(String.valueOf(dnaSequence) + " A:" + statsA + " C:"
            + statsC + " G:" + statsG + " T:" + statsT);

    }


    /**
     * This method updates results.
     * 
     * @param res
     *            the SearchResults object to be updated
     */
    public void searchAll(SearchResults res) {
        res.incrementNodesVisited();
        res.addResult(dnaSequence);
    }


    /**
     * Method implemented for recursively finding a given sequence(s) and
     * storing it in results, based on the current depth of the tree.
     * 
     * @param depth
     *            the current depth in the tree
     * @param searchSequence
     *            the search term
     * @param match
     *            true if search term is exact
     * @param res
     *            the SearchResults object being updated
     */
    public void search(
        int depth,
        char[] searchSequence,
        boolean match,
        SearchResults res) {
        res.incrementNodesVisited();
        if (match) {
            if (toString().equals(String.valueOf(searchSequence))) {
                res.addResult(searchSequence);
            }
        }
        else {
            if (getString().startsWith(String.valueOf(searchSequence))) {
                res.addResult(this.dnaSequence);
            }
        }
    }

}
