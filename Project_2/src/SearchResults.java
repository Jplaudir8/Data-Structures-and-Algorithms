import java.util.ArrayList;

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
 * The SearchResults class represents a storage for all the sequences that match
 * a sequence that is being searched.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class SearchResults {
    private int numNodesVisited;

    private ArrayList<char[]> results;

    /**
     * Sets initial nodes visited value to zero and
     * initializes matches as array list
     * 
     */
    public SearchResults() {
        numNodesVisited = 0;
        results = new ArrayList<char[]>();
    }


    /**
     * Returns the # of nodes visited.
     * 
     * @return nodes visited.
     */
    public int getNumNodesVisited() {
        return numNodesVisited;
    }


    /**
     * Returns all the matches.
     * 
     * @return matches
     */
    public ArrayList<char[]> getResults() {
        return results;
    }


    /**
     * Counts the number of visited nodes.
     */
    public void incrementNodesVisited() {
        numNodesVisited++;
    }


    /**
     * Adds the sequence to results ArrayList.
     * 
     * @param sequence
     *            value of sequence to results arraylist
     */
    public void addResult(char[] sequence) {
        results.add(sequence);
    }

}
