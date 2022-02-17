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

import java.util.Scanner;
import student.TestCase;

/**
 * Tests the BigNumArithmetic class
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * 
 */
public class BigNumArithmeticTest extends TestCase {

    private SinglyLinkedList<Integer> num1;
    private SinglyLinkedList<Integer> num2;
    private SinglyLinkedList<Integer> num3;
    private SinglyLinkedList<Integer> num4;
    private SinglyLinkedList<Integer> num5;
    private SinglyLinkedList<Integer> num10;
    private SinglyLinkedList<Integer> num11;

    /**
     * Initializes empty list, lists with a small number of items, and
     * list with a large number of items
     */
    public void setUp() {

        num1 = new SinglyLinkedList<Integer>();
        num2 = new SinglyLinkedList<Integer>();
        num3 = new SinglyLinkedList<Integer>();
        num4 = new SinglyLinkedList<Integer>();
        num5 = new SinglyLinkedList<Integer>();
        num10 = new SinglyLinkedList<Integer>();
        num11 = new SinglyLinkedList<Integer>();

        // 8762
        num1.addLast(2);
        num1.addLast(6);
        num1.addLast(7);
        num1.addLast(8);

        // 97236
        num2.addLast(6);
        num2.addLast(3);
        num2.addLast(2);
        num2.addLast(7);
        num2.addLast(9);

        // 0
        num3.addLast(0);

        // 824
        num4.addLast(4);
        num4.addLast(2);
        num4.addLast(8);

        // 1
        num5.addLast(1);

        num10.addLast(0);
        num10.addLast(1);

        num11.addLast(1);
        num11.addLast(1);

    }


    /**
     * Tests operationIsValid method
     */
    public void testOperationIsValid() {
        String[] operationLine1 = { "000000056669777", "99999911111", "+",
            "352324012", "+", "03", "^", "555557778", "*" };
        String[] operationLine2 = { "*", "00069777", "99999911111", "+",
            "352324012" };
        String[] operationLine3 = { "000000056669777", "99999911111", "+",
            "352324012", "+", "03", "^", "555557778", "*", "^" };

        String[] operationLineSum = { "00005666", "99977111", "+", "3557022",
            "+" };
        String[] operationLineMulti = { "000002340", "224576", "*", "354783",
            "*", "904856", "*" };
        String[] operationLinePow = { "000002340", "26", "^", "354783", "^",
            "904856", "^" };

        String[] operationLineSumFail = { "00005666", "+", "3557022", "+" };
        String[] operationLineMultiFail = { "000002340", "*", "354783", "*",
            "904856", "*" };
        String[] operationLinePowFail = { "000002340", "^", "354783", "^",
            "904856", "^" };

        String[] operationLineSumMulti = { "000000056669777", "99999911111",
            "+", "352324012", "+", "03", "*" };
        String[] operationLineSumPow = { "000000056669777", "99999911111", "+",
            "352324012", "+", "03", "^" };
        String[] operationLinePowMulti = { "000000056669777", "03", "^",
            "555557778", "*" };

        String[] operationLineSumMultiFail = { "000000056669777", "99999911111",
            "+", "+", "03", "*" };
        String[] operationLineSumPowFail = { "000000056669777", "99999911111",
            "+", "+", "03", "^" };
        String[] operationLinePowMultiFail = { "000000056669777", "03", "^",
            "*" };

        String[] operationLineNoOperator = { "000000056669777", "99999911111" };
        String[] operationLineNoOperands = { "*", "+", "^" };

        // everything until return false return false when operators >operands
        assertTrue(BigNumArithmetic.operationIsValid(operationLine1));
        assertFalse(BigNumArithmetic.operationIsValid(operationLine2));
        assertFalse(BigNumArithmetic.operationIsValid(operationLine3));

        // testing only addition
        assertTrue(BigNumArithmetic.operationIsValid(operationLineSum));
        // testing only multiplication
        assertTrue(BigNumArithmetic.operationIsValid(operationLineMulti));
        // testing only power
        assertTrue(BigNumArithmetic.operationIsValid(operationLinePow));

        assertFalse(BigNumArithmetic.operationIsValid(operationLineSumFail));
        assertFalse(BigNumArithmetic.operationIsValid(operationLineMultiFail));
        assertFalse(BigNumArithmetic.operationIsValid(operationLinePowFail));

        assertTrue(BigNumArithmetic.operationIsValid(operationLineSumMulti));
        assertTrue(BigNumArithmetic.operationIsValid(operationLineSumPow));
        assertTrue(BigNumArithmetic.operationIsValid(operationLinePowMulti));

        assertFalse(BigNumArithmetic.operationIsValid(
            operationLineSumMultiFail));
        assertFalse(BigNumArithmetic.operationIsValid(operationLineSumPowFail));
        assertFalse(BigNumArithmetic.operationIsValid(
            operationLinePowMultiFail));

        assertFalse(BigNumArithmetic.operationIsValid(operationLineNoOperator));
        assertFalse(BigNumArithmetic.operationIsValid(operationLineNoOperands));
    }


    /**
     * Tests add operator method
     */
    public void testAdd() {
        SinglyLinkedList<Integer> expectedRes1 =
            new SinglyLinkedList<Integer>();
        SinglyLinkedList<Integer> expectedRes2 =
            new SinglyLinkedList<Integer>();

        // 105,998
        expectedRes1.addLast(8);
        expectedRes1.addLast(9);
        expectedRes1.addLast(9);
        expectedRes1.addLast(5);
        expectedRes1.addLast(0);
        expectedRes1.addLast(1);

        // 9,586
        expectedRes2.addLast(6);
        expectedRes2.addLast(8);
        expectedRes2.addLast(5);
        expectedRes2.addLast(9);

        // when num1 is larger in digits
        assertTrue(BigNumArithmetic.add(num1, num2).equals(expectedRes1));
        // when num4 is larger in digits
        assertTrue(BigNumArithmetic.add(num1, num4).equals(expectedRes2));
    }


    /**
     * Tests multiply operator method
     */
    public void testMultiplication() {
        SinglyLinkedList<Integer> expectedRes1 =
            new SinglyLinkedList<Integer>();

        // 851981832
        expectedRes1.addLast(2);
        expectedRes1.addLast(3);
        expectedRes1.addLast(8);
        expectedRes1.addLast(1);
        expectedRes1.addLast(8);
        expectedRes1.addLast(9);
        expectedRes1.addLast(1);
        expectedRes1.addLast(5);
        expectedRes1.addLast(8);

        assertTrue(BigNumArithmetic.multiply(num1, num2).equals(expectedRes1));
        assertTrue(BigNumArithmetic.multiply(num4, num5).equals(num4));

    }


    /**
     * Tests exponentiation operator method
     */
    public void testPow() {
        SinglyLinkedList<Integer> expectedRes1 =
            new SinglyLinkedList<Integer>();
        SinglyLinkedList<Integer> expectedRes2 =
            new SinglyLinkedList<Integer>();

        // 100000000000
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(0);
        expectedRes1.addLast(1);

        // 25,937,424,601
        expectedRes2.addLast(1);
        expectedRes2.addLast(0);
        expectedRes2.addLast(6);
        expectedRes2.addLast(4);
        expectedRes2.addLast(2);
        expectedRes2.addLast(4);
        expectedRes2.addLast(7);
        expectedRes2.addLast(3);
        expectedRes2.addLast(9);
        expectedRes2.addLast(5);
        expectedRes2.addLast(2);

        assertTrue(BigNumArithmetic.pow(num1, num3).equals(num5));
        assertTrue(BigNumArithmetic.pow(num1, num5).equals(num1));
        assertTrue(BigNumArithmetic.pow(num10, num11).equals(expectedRes1));
        assertTrue(BigNumArithmetic.pow(num11, num10).equals(expectedRes2));
    }


    /**
     * Tests a series of simple addition expressions (adding only 2 numbers at a
     * time.
     */
    public void testSimpleAddition() {

        String[] operationLineSum = { "102232", "7526", "+" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineSum),
            "102232 7526 + = 109758");

    }


    /**
     * Tests a series of complex addition expressions, adding 2 or more numbers
     * at a time. This test includes numbers whose digits add up to 10 or more,
     * causing carry-overs
     */
    public void testComplexAddition() {
        String[] operationLineSum = { "102232", "7526", "+", "99678", "+" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineSum),
            "102232 7526 + 99678 + = 209436");
    }


    /**
     * Tests a series of simple exponentiations.
     */
    public void testSimpleExponentiation() {
        String[] operationLineExp1 = { "20", "11", "^" };
        String[] operationLineExp2 = { "20", "10", "^" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineExp1),
            "20 11 ^ = 204800000000000");
        assertEquals(BigNumArithmetic.calculateLine(operationLineExp2),
            "20 10 ^ = 10240000000000");
    }


    /**
     * Tests a series of compound exponentiation expressions. Note that this
     * test may time out if you don't follow the algorithm described in the
     * project spec.
     */
    public void testComplexExponentiation() {
        String[] operationLineExp = { "9", "6", "^", "2", "^" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineExp),
            "9 6 ^ 2 ^ = 282429536481");
    }


    /**
     * Tests a series of simple multiplication expressions (multiplying only 2
     * numbers at a time).
     */
    public void testSimpleMultiplication() {
        String[] operationLineMulti = { "105998", "9586", "*" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineMulti),
            "105998 9586 * = 1016096828");
    }


    /**
     * Tests a series of complex multiplication expressions. Expressions contain
     * 2 or numbers, and results may be large.
     */
    public void testComplexMultiplication() {
        String[] operationLineMulti = { "10598", "9586", "*", "455", "*", "800",
            "*" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineMulti),
            "10598 9586 * 455 * 800 * = 36979643792000");
    }


    /**
     * Tests simple expressions in reverse polish notation, using multiple
     * arithmetic operators.
     */
    public void testSimpleExpressions() {
        String[] operationLineExp = { "2", "10", "*", "7", "+", "10", "^" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineExp),
            "2 10 * 7 + 10 ^ = 205891132094649");
    }


    /**
     * When an expression has too many operators and not enough operands, print
     * out the expression and leave the answer blank.
     */
    public void testInvalidExpressions() {
        String[] operationLineExp = { "2", "10", "*", "7", "+", "10", "^",
            "*" };
        assertEquals(BigNumArithmetic.calculateLine(operationLineExp),
            "2 10 * 7 + 10 ^ * =");
    }


    /**
     * Tests the leadingZeroesOnZero method
     */
    public void testLeadingZeroesOnZero() {
        String[] operationLine1 = { "000000", "255", "+" };
        String[] operationLine2 = { "000040", "255", "+" };
        String[] operationLine3 = { "004", "255", "+" };
        String[] operationLine4 = { "", "255", "+" };
        String[] operationLine5 = { "5", "255", "/" };

        assertEquals(BigNumArithmetic.calculateLine(operationLine1),
            "0 255 + = 255");
        assertEquals(BigNumArithmetic.calculateLine(operationLine2),
            "40 255 + = 295");
        assertEquals(BigNumArithmetic.calculateLine(operationLine3),
            "4 255 + = 259");
        assertEquals(BigNumArithmetic.calculateLine(operationLine4),
            " 255 + = 255");
        assertEquals(BigNumArithmetic.calculateLine(operationLine5),
            "5 255 / =");

    }


    /**
     * Tests the add and multiply
     */
    public void testSumAndMultiply() {
        String[] operationLine1 = { "000000", "255", "+", "45", "*" };
        assertEquals(BigNumArithmetic.calculateLine(operationLine1),
            "0 255 + 45 * = 11475");
    }


    /**
     * Tests the add and exponentiation
     */
    public void testSumAndExponent() {
        String[] operationLine1 = { "8", "3", "^", "10", "+" };
        assertEquals(BigNumArithmetic.calculateLine(operationLine1),
            "8 3 ^ 10 + = 522");
    }


    /**
     * Tests the multiply and exponentiation
     */
    public void testMultiplyAndExponent() {
        String[] operationLine1 = { "10", "5", "*", "2", "^" };
        assertEquals(BigNumArithmetic.calculateLine(operationLine1),
            "10 5 * 2 ^ = 2500");
    }


    /**
     * Tests the scanFile method
     */
    public void testScanFileValid() {
        String inputFile = "MixedOperations.txt";
        BigNumArithmetic.scanFile(inputFile);
        assertFuzzyEquals(systemOut().getHistory(),
            "40 255 + = 295\n20 30 * = 600");
    }


    /**
     * Tests the main method
     */
    public void testMain() {
        String[] s = { "A", "B" };
        BigNumArithmetic.main(s);
        Scanner scanner = new Scanner(s.toString());
        assertEquals(scanner.next().toString(), s.toString());

    }


    /**
     * Tests the main method
     */
    public void testCodeCoverage() {
        BigNumArithmetic rpn = new BigNumArithmetic();
        assertNotNull(rpn);
        BigNumArithmetic.scanFile("SampleOutput.txt");
        BigNumArithmetic.scanFile("SampleInput.txt");
    }

}
