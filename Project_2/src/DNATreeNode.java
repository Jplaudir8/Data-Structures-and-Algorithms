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
 * The DNATreeNode class represents a Node part of the DNAtree data structure.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public abstract class DNATreeNode {

    /**
     * Prints the node at a specific level representation to the project
     * 
     * @param level
     *            level value
     */
    public void print(int level) {
        for (int i = 1; i <= level; i++) {
            System.out.print("  ");
        }
        printNode(level);
    }


    /**
     * Adds a number of indents based on the current level and prints lengths of
     * sequences.
     * 
     * @param level
     *            level value
     */
    public void printLengths(int level) {
        for (int i = 1; i <= level; i++) {
            System.out.print("  ");
        }
        printNodeLengths(level);
    }


    /**
     * Adds a number of indents based on the given level and prints statistical
     * distribution of sequences.
     * 
     * @param level
     *            level value
     */
    public void printStats(int level) {
        for (int i = 1; i <= level; i++) {
            System.out.print("  ");
        }
        printNodeStats(level);
    }


    /**
     * Searches the tree using a SearchResults for getting all the sequences
     * 
     * @param res
     *            the SearchResults object being updated
     */
    public void searchAll(SearchResults res) {
        res.incrementNodesVisited();
    }


    /**
     * Searches for given input sequence.
     * 
     * @param level
     *            the current level in the tree
     * @param sequence
     *            the search term
     * @param match
     *            true if search term is exact
     * @param res
     *            the SearchResults object being updated
     */
    public void search(
        int level,
        char[] sequence,
        boolean match,
        SearchResults res) {

        res.incrementNodesVisited();
    }


    /**
     * Print out the DNA tree, including both the node structure and the
     * sequences it contains
     * 
     * @param level
     *            prints the node at the level
     */
    public abstract void printNode(int level);


    /**
     * Output is identical to that of the print command, except that the length
     * of the sequence is printed after the sequence for all sequences stored in
     * the database.
     * 
     * @param level
     *            prints the node at the level
     */
    public abstract void printNodeLengths(int level);


    /**
     * Output is identical to that of the print command, except that the letter
     * breakdown (by percentage) of the sequence is printed after the sequence
     * for all sequences stored in the database.
     * 
     * @param level
     *            prints the node at the level
     */
    public abstract void printNodeStats(int level);

}
