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
 * This class reads through the inputs
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public class Command {
    private String str;
    private DNAtree tree;

    /**
     * Constructor
     * 
     * @param dnaTree
     *            name of the tree
     * @param input
     *            string commands
     */
    public Command(String input, DNAtree dnaTree) {

        str = input;
        tree = dnaTree;
    }


    /**
     * Execution of the commands
     * 
     */
    public void executeTreeOperation() {
        if (str.trim().length() == 0) {
            return;
        }
        String[] currentLineArr = str.trim().split(" +");
        if (currentLineArr[0].equals("insert")) {
            tree.insert(currentLineArr[1]);

        }
        else if (currentLineArr[0].equals("remove")) {
            tree.remove(currentLineArr[1]);

        }
        else if (currentLineArr[0].equals("search")) {
            tree.search(currentLineArr[1]);

        }
        else if (currentLineArr[0].equals("print")) {
            if (currentLineArr.length == 1) {
                tree.print();
            }
            else if (currentLineArr[1].equals("lengths")) {
                tree.printLengths();
            }
            else if (currentLineArr[1].equals("stats")) {
                tree.printStats();
            }
        }
    }

}
