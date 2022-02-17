
import java.io.File;
import java.util.Scanner;

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
 * Represents a calculator of integer numbers that can have a size bigger than
 * normal 32-bit and 64-bit integers. Possible operations that it can hold are:
 * Addition, Multiplication and Exponentiation.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Jan 28, 2022
 */
public class BigNumArithmetic {

    /**
     * Removes possible leading zeroes that token may contain.
     * 
     * @param token
     *            token to be modified.
     * @return token without leading zeroes.
     */
    private static String removeLeadingZeroes(String token) {
        // token could be 0, +, -, ^, [1 - 9]
        if (token.length() == 1) {
            return token;
        }

        int currIdx = 0;
        for (; currIdx < token.length(); currIdx++) {
            // if we are in the last character of the token means all previous
            // digits were 0's. If this last one is 0 as well, we keep it.
            if (currIdx == token.length() - 1 && token.charAt(currIdx) == '0') {
                return "0";
            }
            // break when the current digit != 0
            if (token.charAt(currIdx) != '0') {
                break;
            }
        }

        // If we get here then we have not reach the end of the token, there are
        // digits different from 0 starting from the index currIdx.

        return token.substring(currIdx);
    }


    /**
     * Returns integer form of SinglyLinkedList object. This method is only
     * used when we are sure linkedListNum can be held in a 32-bit integer.
     * 
     * @param linkedListNum
     *            linked list object from which integer form is going to be
     *            obtained.
     * @return 32-bit integer form of linkedListNum
     */
    private static int getInteger(SinglyLinkedList<Integer> linkedListNum) {
        SinglyLinkedList<Integer>.ListNode<Integer> currNode = linkedListNum
            .getHead();
        String numStr = "";
        String reversedNumStr = "";
        // 0 -> 1
        // c
        while (currNode != null) {
            reversedNumStr += "" + (Integer)currNode.getValue();
            currNode = currNode.getNext();
        }

        for (int i = reversedNumStr.length() - 1; i >= 0; i--) {
            numStr += reversedNumStr.charAt(i);
        }
        return Integer.valueOf(numStr);
    }


    /**
     * Adds integer representation of num1 and num2 linked lists and returns the
     * result as another singly linked list instance.
     * 
     * @param num1
     *            first operand to be added.
     * @param num2
     *            second operand to be added.
     * @return result of the addition of num1 and num2.
     */
    public static SinglyLinkedList<Integer> add(
        SinglyLinkedList<Integer> num1,
        SinglyLinkedList<Integer> num2) {

        SinglyLinkedList<Integer>.ListNode<Integer> currN1 = num1.getHead();
        SinglyLinkedList<Integer>.ListNode<Integer> currN2 = num2.getHead();
        SinglyLinkedList<Integer> result = new SinglyLinkedList<Integer>();
        int carry = 0;
        while (currN1 != null || currN2 != null) {
            int n1 = currN1 != null ? currN1.getValue() : 0;
            int n2 = currN2 != null ? currN2.getValue() : 0;
            int n3 = n1 + n2 + carry;
            carry = n3 / 10; // getting possible carry
            result.addLast(n3 % 10); // adding unit
            if (currN1 != null) {
                currN1 = currN1.getNext();
            }
            if (currN2 != null) {
                currN2 = currN2.getNext();
            }
        }

        if (carry > 0) {
            result.addLast(carry);
        }

        return result;
    }


    /**
     * Multiplies the integer representation of num1 and num2 linked lists and
     * returns the product.
     * 
     * @param num1
     *            first operand to be multiplied. (Multiplicand).
     * @param num2
     *            second operand to be multiplied. (Multiplier).
     * @return the product of num1 and num2.
     */
    public static SinglyLinkedList<Integer> multiply(
        SinglyLinkedList<Integer> num1,
        SinglyLinkedList<Integer> num2) {
        SinglyLinkedList<Integer>.ListNode<Integer> currN1 = num1.getHead();
        SinglyLinkedList<Integer>.ListNode<Integer> currN2 = num2.getHead();
        SinglyLinkedList<Integer> result = new SinglyLinkedList<Integer>(0);
        int rowNumber = 0;


        while (currN1 != null) {

            SinglyLinkedList<Integer> currProductRes =
                new SinglyLinkedList<Integer>();
            int carry = 0;

            // generate zeros depending in which row we are
            for (int i = 0; i < rowNumber; i++) {
                currProductRes.addFirst(0);
            }

            SinglyLinkedList<Integer>.ListNode<Integer> dummyN2 = currN2;

            // multiply currN1 digit with every digit of the other operand
            while (dummyN2 != null) {
                int n1 = (Integer)currN1.getValue();
                int n2 = (Integer)dummyN2.getValue();

                int n3 = n1 * n2 + carry;
                carry = n3 / 10;
                currProductRes.addLast(n3 % 10);

                dummyN2 = dummyN2.getNext();
            }

            if (carry > 0) {
                currProductRes.addLast(carry);
            }

            result = add(result, currProductRes);
            rowNumber++;
            currN1 = currN1.getNext();
        }
        return result;
    }


    /**
     * Returns linked list form of the given token.
     * 
     * @param currToken
     *            token to be casted to a Singly Linked List.
     * @return linked list form of current token.
     */
    private static SinglyLinkedList<Integer> getLinkedList(String currToken) {
        SinglyLinkedList<Integer> newNumber = new SinglyLinkedList<Integer>();
        for (int i = currToken.length() - 1; i >= 0; i--) {
            String currDigit = String.valueOf(currToken.charAt(i));
            newNumber.addLast(Integer.valueOf(currDigit));
        }
        return newNumber;
    }


    /**
     * Checks whether an intended operation is valid.
     * 
     * @param operationArray
     *            operation line segmented in an array of String values.
     * @return true or false whether the operation is valid or not.
     */
    public static boolean operationIsValid(String[] operationArray) {
        int numOfOperands = 0;
        int numOfOperators = 0;
        for (int i = 0; i < operationArray.length; i++) {

            String currToken = operationArray[i];

            if (currToken.equals("+") || currToken.equals("*") || currToken
                .equals("^")) {
                if (i == 0) {
                    return false;
                }
                numOfOperators++;
            }
            else {
                numOfOperands++;
            }
        }
        return (numOfOperands - 1) == numOfOperators;
    }


    /**
     * Calculates exponentiation of num1 raised to num2 and returns the result.
     * 
     * @param num1
     *            base of the calculation.
     * @param num2
     *            exponent of the calculation.
     * @return result of performing exponentiation
     */
    public static SinglyLinkedList<Integer> pow(
        SinglyLinkedList<Integer> num1,
        SinglyLinkedList<Integer> num2) {

        if (getInteger(num2) == 0) {
            return new SinglyLinkedList<Integer>((Integer)1);
        }

        if (getInteger(num2) == 1) {
            return num1;
        }
        // step 1
        SinglyLinkedList<Integer> squaredNum1 = multiply(num1, num1);
        SinglyLinkedList<Integer> result = squaredNum1;
        int exponent = getInteger(num2);
        if (exponent % 2 == 0) {
            exponent /= 2;
        }
        else {
            exponent = ((exponent - 1) / 2);
            result = multiply(result, num1);
        }

        for (int i = 2; i <= exponent; i++) {
            result = multiply(result, squaredNum1);
        }

        // if exponent is odd we need to multiply by num1 as part of the formula

        return result;
    }


    /**
     * Performs calculation of current intended operation of numbers.
     * 
     * @param operationLine
     *            operation line segmented in an array of String values.
     * @return intended operation with or without result, based on if it is
     *         valid or not, respectively.
     */
    public static String calculateLine(String[] operationLine) {
        // First validate that it will be possible to do the math.

        if (!operationIsValid(operationLine)) {
            // invalid operation so just remove leading zeroes and print
            // intended operation
            String currentToken = "";
            for (int i = 0; i < operationLine.length; i++) {
                currentToken += removeLeadingZeroes(operationLine[i]) + " ";
            }
            return currentToken + "=";
        }

        // create a stack and string to be used for printing
        Stack<SinglyLinkedList<Integer>> numsStack =
            new Stack<SinglyLinkedList<Integer>>();
        String resultPrint = "";

        for (int i = 0; i < operationLine.length; i++) {
            // if current token is an operator then pop 2 elements from stack,
            // solve and push result to stack
            // else its a number so push into stack
            String currToken = removeLeadingZeroes(operationLine[i]);

            resultPrint += currToken + " ";

            if (currToken.equals("+") || currToken.equals("*") || currToken
                .equals("^")) {
                SinglyLinkedList<Integer> numA =
                    (SinglyLinkedList<Integer>)numsStack.pop();
                SinglyLinkedList<Integer> numB =
                    (SinglyLinkedList<Integer>)numsStack.pop();
                SinglyLinkedList<Integer> result =
                    new SinglyLinkedList<Integer>();
                if (currToken.equals("+")) {
                    result = add(numA, numB);
                }
                if (currToken.equals("*")) {
                    result = multiply(numA, numB);
                }
                if (currToken.equals("^")) {
                    result = pow(numB, numA);
                }

                numsStack.push(result);
            }
            else {
                // push number found
                SinglyLinkedList<Integer> newNum = getLinkedList(currToken);
                numsStack.push(newNum);
            }
        }

        resultPrint += "= " + removeLeadingZeroes(numsStack.pop().toString());
        return resultPrint;
    }


    /**
     * Scans file to be calculated.
     * 
     * @param filename
     *            file path to be used for calculation.
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
                String lineResult = calculateLine(currentLineArr);

                System.out.println(lineResult);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * the main method
     * 
     * @param args
     *            string to be scanned
     */
    public static void main(String[] args) {
        String filename = args[0];
        scanFile(filename);
    }
}
