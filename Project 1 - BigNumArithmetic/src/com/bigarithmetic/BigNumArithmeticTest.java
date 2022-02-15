package com.bigarithmetic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import student.TestCase;

public class BigNumArithmeticTest extends TestCase {

    private BigNumArithmetic rpn;
    private SinglyLinkedList<Integer> num1;
    private SinglyLinkedList<Integer> num2;
    private SinglyLinkedList<Integer> num3;
    private SinglyLinkedList<Integer> num4;
    private SinglyLinkedList<Integer> num5;
    private SinglyLinkedList<Integer> num10;
    private SinglyLinkedList<Integer> num11;

// public void testScanFile() throws FileNotFoundException {
// BigNumArithmetic.scanFile("MixedOperations1Line.txt");
// Scanner sc = new Scanner(new File("RPNOutput.txt"));
// assertEquals(
// "56669777 99999911111 + 352324012 + 3 ^ 555557778 * =
// 562400792227677956625810678708149922000000",
// sc.nextLine().toString());
// }

    public void setUp() {
        rpn = new BigNumArithmetic();
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

        assertTrue(rpn.operationIsValid(operationLine1)); // everything
        assertFalse(rpn.operationIsValid(operationLine2)); // until return false
        assertFalse(rpn.operationIsValid(operationLine3)); // return false when
                                                           // operators >
                                                           // operands

        assertTrue(rpn.operationIsValid(operationLineSum)); // testing only
                                                            // addition
        assertTrue(rpn.operationIsValid(operationLineMulti)); // testing only
                                                              // multiplication
        assertTrue(rpn.operationIsValid(operationLinePow)); // testing only
                                                            // power

        assertFalse(rpn.operationIsValid(operationLineSumFail));
        assertFalse(rpn.operationIsValid(operationLineMultiFail));
        assertFalse(rpn.operationIsValid(operationLinePowFail));

        assertTrue(rpn.operationIsValid(operationLineSumMulti));
        assertTrue(rpn.operationIsValid(operationLineSumPow));
        assertTrue(rpn.operationIsValid(operationLinePowMulti));

        assertFalse(rpn.operationIsValid(operationLineSumMultiFail));
        assertFalse(rpn.operationIsValid(operationLineSumPowFail));
        assertFalse(rpn.operationIsValid(operationLinePowMultiFail));

        assertFalse(rpn.operationIsValid(operationLineNoOperator));
        assertFalse(rpn.operationIsValid(operationLineNoOperands));
    }


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

        assertTrue(rpn.add(num1, num2).equals(expectedRes1)); // when num1 is
                                                              // larger in
                                                              // digits
        assertTrue(rpn.add(num1, num4).equals(expectedRes2)); // when num4 is
                                                              // larger in
                                                              // digits

    }


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

        assertTrue(rpn.multiply(num1, num2).equals(expectedRes1));
        assertTrue(rpn.multiply(num4, num5).equals(num4));

    }


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

        assertTrue(rpn.pow(num1, num3).equals(num5));
        assertTrue(rpn.pow(num1, num5).equals(num1));
        assertTrue(rpn.pow(num10, num11).equals(expectedRes1));
        assertTrue(rpn.pow(num11, num10).equals(expectedRes2));
    }


    public void testSimpleAddition() {

        String[] operationLineSum = { "102232", "7526", "+" };
        assertEquals(rpn.calculateLine(operationLineSum),
            "102232 7526 + = 109758");

    }


    public void testComplexAddition() {
        String[] operationLineSum = { "102232", "7526", "+", "99678", "+" };
        assertEquals(rpn.calculateLine(operationLineSum),
            "102232 7526 + 99678 + = 209436");
    }


    public void testSimpleExponentiation() {
        String[] operationLineExp1 = { "20", "11", "^" };
        String[] operationLineExp2 = { "20", "10", "^" };
        assertEquals(rpn.calculateLine(operationLineExp1),
            "20 11 ^ = 204800000000000");
        assertEquals(rpn.calculateLine(operationLineExp2),
            "20 10 ^ = 10240000000000");
    }


    public void testComplexExponentiation() {
        String[] operationLineExp = { "9", "6", "^", "2", "^" };
        assertEquals(rpn.calculateLine(operationLineExp),
            "9 6 ^ 2 ^ = 282429536481");
    }


    public void testSimpleMultiplication() {
        String[] operationLineMulti = { "105998", "9586", "*" };
        assertEquals(rpn.calculateLine(operationLineMulti),
            "105998 9586 * = 1016096828");
    }


    public void testComplexMultiplication() {
        String[] operationLineMulti = { "10598", "9586", "*", "455", "*", "800",
            "*" };
        assertEquals(rpn.calculateLine(operationLineMulti),
            "10598 9586 * 455 * 800 * = 36979643792000");
    }


    public void testSimpleExpressions() {
        String[] operationLineExp = { "2", "10", "*", "7", "+", "10", "^" };
        assertEquals(rpn.calculateLine(operationLineExp),
            "2 10 * 7 + 10 ^ = 205891132094649");
    }


    public void testInvalidExpressions() {
        String[] operationLineExp = { "2", "10", "*", "7", "+", "10", "^",
            "*" };
        assertEquals(rpn.calculateLine(operationLineExp),
            "2 10 * 7 + 10 ^ * =");
    }

}
