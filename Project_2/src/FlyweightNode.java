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
 * The FlyweightNode class represents an empty node of the DNAtree data
 * structure.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Feb 25, 2022
 */
public final class FlyweightNode extends DNATreeNode {

    /**
     * FlyweightNode instance representing an empty node.
     */
    private static FlyweightNode flyweight = new FlyweightNode();

    /**
     * Returns the flyweight node, an instance of FlyweightNode
     * 
     * @return flyweight the node
     */
    public static FlyweightNode getFlyweight() {
        return flyweight;
    }


    /**
     * Prints "E"
     * 
     * @param level
     *            the level of the node in tree
     */
    public void printNode(int level) {
        System.out.println("E");
    }


    /**
     * Prints "E"
     * 
     * @param level
     *            the level of the node in tree
     */
    @Override
    public void printNodeLengths(int level) {
        System.out.println("E");

    }


    /**
     * Prints "E"
     * 
     * @param level
     *            the level of the node in tree
     */
    @Override
    public void printNodeStats(int level) {
        System.out.println("E");

    }

}
