// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
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
 * @version March 22, 2022
 */
public class Run {
    /**
     * Constant: bytes per block
     */
    public static final int BLOCK_RECORDS = 512;
    private int runLength;
    private long runFileOffset;
    private int blockCount;

    /**
     * runRecord class constructor
     * 
     * @param len
     *            run length
     * @param offset
     *            run offset
     */
    public Run(int len, long offset) {
        runLength = len;
        runFileOffset = offset;
        blockCount = runLength / BLOCK_RECORDS;
        if (runLength % BLOCK_RECORDS != 0) {
            blockCount += 1;
        }
    }


    /**
     * Returns the length of run
     * 
     * @return runLength
     *         run length
     */
    public int getRunLength() {
        return runLength;
    }


    /**
     * Returns the offset of run
     * 
     * @return runFileOffset
     * 
     */
    public long getRunFileOffset() {
        return runFileOffset;
    }


    /**
     * Returns the number of blocks
     * 
     * @return blockCount
     *         the number of blocks
     */
    public int getBlockCount() {
        return blockCount;
    }
}
