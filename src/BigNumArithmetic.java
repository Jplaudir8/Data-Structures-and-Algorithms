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

import java.io.File;
import java.util.Scanner;

/**
 * The BigNumArithmetic class will read from the input-file a series of
 * expressions, with one expression per line. all expressions will be in Reverse
 * Polish Notation (RPN).
 * 
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 * 
 * 
 */
public class BigNumArithmetic {
//
// /**
// * Remove possible leading zeroes that token may contain.
// *
// * @param token
// * @return
// */
// private static String removeLeadingZeroes(String token) {
//
// // token == 0, +, -, ^, [1 - 9]
// if (token.length() == 1) {
// return token;
// }
//
// int currIdx = 0;
// for (currIdx = 0; currIdx < token.length(); currIdx++) {
// // if we are in the last character of the token means all previous
// // digits were 0's. If this last one is 0 as well, then we keep it.
// if (currIdx == token.length() - 1 && token.charAt(currIdx) == '0') {
// return "0";
// }
// // break when the current digit != 0
// if (token.charAt(currIdx) != '0') {
// break;
// }
// }
//
// // if we did not reach the end of the token then there are
// // digits different from 0 starting from the index currIdx.
// StringBuilder trimmedToken = new StringBuilder("");
// for (int i = currIdx; i < token.length(); i++) {
// trimmedToken.append(token.charAt(i));
// }
// return trimmedToken.toString();
// }
//
//
// // process the multiply operation
// private static LinkedList<Object> multiply(
// LinkedList<Object> num1,
// LinkedList<Object> num2) {
// LinkedList.Node<Object> currN1 = num1.getHead();
// LinkedList.Node<Object> currN2 = num2.getHead();
// LinkedList<Object> result = new LinkedList<Object>();
// int rowNumber = 0;
//
// while (currN1 != null) {
//
// LinkedList.Node<Object> dummyN2 = currN2;
// LinkedList<Object> currProductRes = new LinkedList<Object>();
// int carry = 0;
// // generate zeros depending in which row we are
// for (int i = 0; i < rowNumber; i++) {
// currProductRes.addBottom(0);
// }
//
// while (dummyN2 != null) {
// int n1 = (Integer)currN1.getData();
// int n2 = (Integer)dummyN2.getData();
// int n3 = n1 * n2 + carry;
// carry = n3 / 10;
// currProductRes.addBottom(n3 % 10);
//
// dummyN2 = dummyN2.getNext();
// }
//
// if (carry > 0) {
// currProductRes.addBottom(carry);
// }
//
// result = add(result, currProductRes);
// rowNumber++;
// currN1 = currN1.getNext();
// }
// return result;
// }
//
//// private static LinkedList<Object> exponentiation(
//// LinkedList<Object> num1,
//// LinkedList<Object> num2) {
//// return num2;
//// }
//
//
// // process the add operation
// private static LinkedList<Object> add(
// LinkedList<Object> num1,
// LinkedList<Object> num2) {
//
// LinkedList.Node<Object> currN1 = num1.getHead();
// LinkedList.Node<Object> currN2 = num2.getHead();
// LinkedList<Object> result = new LinkedList<Object>();
// int carry = 0;
// while (currN1 != null || currN2 != null) {
// int n1 = currN1 != null ? (Integer)currN1.getData() : 0;
// int n2 = currN2 != null ? (Integer)currN2.getData() : 0;
// int n3 = n1 + n2 + carry;
// carry = n3 / 10; // getting possible carry
// result.addBottom(n3 % 10); // adding unit
// if (currN1 != null) {
// currN1 = currN1.getNext();
// }
//
// if (currN2 != null) {
// currN2 = currN2.getNext();
// }
//
// }
//
// if (carry > 0) {
// result.addBottom(carry);
// }
//
// return result;
// }
//
//
// private static LinkedList<Object> getLinkedList(String currToken) {
// LinkedList<Object> newNumber = new LinkedList<Object>();
// for (int i = currToken.length() - 1; i >= 0; i--) {
// String currDigit = String.valueOf(currToken.charAt(i));
// newNumber.addBottom(Integer.valueOf(currDigit));
// }
// return newNumber;
// }
//
// // QUESTION: Might have to add a validation that checks whether the operands
// // are in the right places
// // Otherwise stack could break when popping more elements than current size
// // e.g. *2 36
//
//
// // Make sure number of operators = number of operands - 1
// private static boolean operationIsValid(String[] operationArray) {
//
// int numOfOperands = 0;
// int numOfOperators = 0;
// for (int i = 0; i < operationArray.length; i++) {
// String currToken = operationArray[i];
// if (currToken.equals("+") || currToken.equals("*") || currToken
// .equals("^")) {
// numOfOperators++;
// }
// else {
// numOfOperands++;
// }
// }
// return (numOfOperands - 1) == numOfOperators;
// }
//
//
//
// /**
// * getInteger
// *
// * @param linkedListNum
// * the array of operations
// * @return result of the operation
// */
// public static int getInteger(LinkedList<Object> linkedListNum) {
// LinkedList.Node<Object> currNode = linkedListNum.getHead();
//// int generatedNum = 0;
// StringBuilder numStr = new StringBuilder("");
//
// while (currNode != null) {
// numStr.insert(0, (Integer) currNode.getData());
// currNode = currNode.getNext();
// }
//
// return Integer.valueOf(numStr.toString());
// }
//
//
// /**
// * Calculates the math
// *
// * @param operationLine
// * the array of operations
// * @return result of the operation
// */
// @SuppressWarnings("unchecked")
// public static String calculateLine(String[] operationLine) {
// // First validate that it will be possible to do the math.
//
// if (!operationIsValid(operationLine)) {
// return String.join(" ", operationLine) + " =";
// }
//
// // create a stack and StringBuilder to be used for printin in file
// Stack<Integer> numsStack = new Stack<Integer>();
// StringBuilder resultPrint = new StringBuilder("");
//
// for (int i = 0; i < operationLine.length; i++) {
// // if current token is an operator then pop 2 elements from stack,
// // solve and push res
// // else its a number so push it to stack
// String currToken = removeLeadingZeroes(operationLine[i]);
//
// resultPrint.append(currToken + " ");
//
// if (currToken.equals("+") || currToken.equals("*") || currToken
// .equals("^")) {
// LinkedList<Object> numA = (LinkedList<Object>)numsStack.pop();
// LinkedList<Object> numB = (LinkedList<Object>)numsStack.pop();
// LinkedList<Object> result = new LinkedList<Object>();
// switch (currToken) {
// case "+":
// result = add(numA, numB);
// break;
// case "*":
// result = multiply(numA, numB);
// break;
// case "^":
// result = add(numA, numB);
// break;
// default:
// // no other cases
// break;
//
// }
//
// numsStack.push(result);
// }
// else {
// // push element found
// LinkedList<Object> newNum = getLinkedList(currToken);
// numsStack.push(newNum);
// }
// }
//
// resultPrint.append("= " + numsStack.pop().toString());
// return resultPrint.toString();
// }

    /**
     * Scans the input file
     *
     * @param filename
     *            the input file
     */
    public static void scanFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename)); // Create our new
            // scanner
            while (sc.hasNextLine()) { // While the scanner has information to
                // read
                String currentLine = sc.nextLine(); // Read the next term
                if (currentLine.trim().length() == 0) {
                    continue;
                }

                // separate every element and store them in array
                String[] currentLineArr = currentLine.trim().split(" +");
//                String lineResult = calculateLine(currentLineArr);
                System.out.print(currentLineArr);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Scans the input file
     *
     * @param args
     *            the string
     */
    public static void main(String[] args) {
        String filename = args[0];
        scanFile(filename);
    }
}
