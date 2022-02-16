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

//import java.io.File;
import java.util.Scanner;
import student.TestCase;

/**
 * The BigNumArithmetic class will read from the input-file a series of
 * expressions, with one expression per line. all expressions will be in Reverse
 * Polish Notation (RPN).
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * 
 * @return
 */
public class BigNumArithmeticTest extends TestCase {
//    private BigNumArithmetic bigNum;
    
    
    /**
     * Initializes empty list, lists with a small number of items, and
     * list with a large number of items
     */
//    public void setUp() {
//        bigNum = new BigNumArithmetic();
//        }

    
    /**
     * Tests the main method
     */
    public void testMain() {
        String[] s = {"A", "B"};
        BigNumArithmetic.main(s);
        Scanner scanner = new Scanner(s.toString());
        assertEquals(scanner.next().toString(), s.toString());
        
    }

}
