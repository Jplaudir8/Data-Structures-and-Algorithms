package com.bigarithmetic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
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

public class BigNumArithmetic {

    /**
     * Remove possible leading zeroes that token may contain.
     * 
     * @param token
     * @return token without leading zeroes
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
     * Obtain integer form of SinglyLinkedListObj object. This method is only
     * used when we are sure linkedListNum can be held in a 32-bit integer.
     * @param linkedListNum
     * @return 32-bit integer form of linkedListNum
     */
    public static int getInteger(SinglyLinkedListObj<Integer> linkedListNum) {
        SinglyLinkedListObj<Integer>.ListNode<Integer> currNode = linkedListNum.getHead();
        int generatedNum = 0;
        StringBuilder numStr = new StringBuilder("");
        
        while (currNode != null) {
            numStr.insert(0, (Integer)currNode.getValue());
            currNode = currNode.getNext();
        }

        return Integer.valueOf(numStr.toString());
    }
    
    
    private static SinglyLinkedListObj<Integer> add(
        SinglyLinkedListObj<Integer> num1,
        SinglyLinkedListObj<Integer> num2) {

        SinglyLinkedListObj<Integer>.ListNode<Integer> currN1 = num1.getHead();
        SinglyLinkedListObj<Integer>.ListNode<Integer> currN2 = num2.getHead();
        SinglyLinkedListObj<Integer> result = new SinglyLinkedListObj<Integer>();
        int carry = 0;
        while (currN1 != null || currN2 != null) {
            int n1 = currN1 != null ? currN1.getValue() : 0;
            int n2 = currN2 != null ? currN2.getValue() : 0;
            int n3 = n1 + n2 + carry;
            carry = n3 / 10; // getting possible carry
            result.addLast(n3 % 10); // adding unit
            if (currN1 != null)
                currN1 = currN1.getNext();
            if (currN2 != null)
                currN2 = currN2.getNext();
        }

        if (carry > 0) {
            result.addLast(carry);
        }

        return result;
    }


    private static SinglyLinkedListObj<Integer> multiply(
        SinglyLinkedListObj<Integer> num1,
        SinglyLinkedListObj<Integer> num2) {
        SinglyLinkedListObj<Integer>.ListNode<Integer> currN1 = num1.getHead();
        SinglyLinkedListObj<Integer>.ListNode<Integer> currN2 = num2.getHead();
        SinglyLinkedListObj<Integer> result = new SinglyLinkedListObj<Integer>(0);
        int rowNumber = 0;

        // TODO test with special cases when multiple a number by 0 or by 1
        
        while (currN1 != null) {

            SinglyLinkedListObj<Integer> currProductRes = new SinglyLinkedListObj<Integer>();
            int carry = 0;

            // generate zeros depending in which row we are
            for (int i = 0; i < rowNumber; i++) {
                currProductRes.addFirst(0);
            }
            
            SinglyLinkedListObj<Integer>.ListNode<Integer> dummyN2 = currN2;
            
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


    private static SinglyLinkedListObj<Integer> getLinkedList(String currToken) {
        SinglyLinkedListObj<Integer> newNumber = new SinglyLinkedListObj<Integer>();
        for (int i = currToken.length() - 1; i >= 0; i--) {
            String currDigit = String.valueOf(currToken.charAt(i));
            newNumber.addLast(Integer.valueOf(currDigit));
        }
        return newNumber;
    }

    // QUESTION: Might have to add a validation that checks whether the operands
    // are in the right places
    // Otherwise stack could break when popping more elements than current size
    // e.g. *2 36


    // Make sure number of operators = number of operands - 1
    private static boolean operationIsValid(String[] operationArray) {
        int numOfOperands = 0;
        int numOfOperators = 0;
        for (int i = 0; i < operationArray.length; i++) {
            String currToken = operationArray[i];
            if (currToken.equals("+") || currToken.equals("*") || currToken
                .equals("^")) {
                numOfOperators++;
            }
            else {
                numOfOperands++;
            }
        }
        return (numOfOperands - 1) == numOfOperators;
    }


    private static SinglyLinkedListObj<Integer> pow(
        SinglyLinkedListObj<Integer> num1,
        SinglyLinkedListObj<Integer> num2) {

        if (getInteger(num2) == 0) {
            return new SinglyLinkedListObj<Integer>((Integer)1);
        }

        if (getInteger(num2) == 1) {
            return num1;
        }

        SinglyLinkedListObj<Integer> squaredNum1 = multiply(num1, num1);

        // generate exponent
        int exponent = getInteger(num2);
        if (exponent % 2 == 0) {
            exponent /= 2;
        }
        else {
            exponent = ((exponent - 1) / 2);
        }

        SinglyLinkedListObj<Integer> result = squaredNum1;

        for (int i = 2; i <= exponent; i++) {
            result = multiply(result, squaredNum1);
        }
        
        // if exponent is odd we need to multiply by num1 as part of the formula
        if (exponent % 2 != 0) {
            result = multiply(result, num1);
        }
        
        return result;
    }


    public static String calculateLine(String[] operationLine) {
        // First validate that it will be possible to do the math.

        if (!operationIsValid(operationLine)) {
            // invalid operation so just remove leading zeroes and print
            // intended operation
            StringBuilder currentToken = new StringBuilder("");
            for (int i = 0; i < operationLine.length; i++) {
                currentToken.append(removeLeadingZeroes(operationLine[i]) + " ");
            }
            return currentToken.toString() + "=";
        }

        // create a stack and StringBuilder to be used for printing in file
        Stack numsStack = new Stack();
        StringBuilder resultPrint = new StringBuilder("");

        for (int i = 0; i < operationLine.length; i++) {
            // if current token is an operator then pop 2 elements from stack,
            // solve and push result to stack
            // else its a number so push into stack
            String currToken = removeLeadingZeroes(operationLine[i]);

            resultPrint.append(currToken + " ");

            if (currToken.equals("+") || currToken.equals("*") || currToken
                .equals("^")) {
                SinglyLinkedListObj<Integer> numA = (SinglyLinkedListObj<Integer>)numsStack.pop();
                SinglyLinkedListObj<Integer> numB = (SinglyLinkedListObj<Integer>)numsStack.pop();
                SinglyLinkedListObj<Integer> result = new SinglyLinkedListObj<Integer>();
                switch (currToken) {
                    case "+":
                        result = add(numA, numB);
                        break;
                    case "*":
                        result = multiply(numA, numB);
                        break;
                    case "^":
                        result = pow(numB, numA);
                        break;
                    default:
                        // no other operator
                        break;
                }

                numsStack.push(result);
            }
            else {
                // push number found
                SinglyLinkedListObj<Integer> newNum = getLinkedList(currToken);
                numsStack.push(newNum);
            }
        }

        resultPrint.append("= " + removeLeadingZeroes(numsStack.pop().toString()));
        return resultPrint.toString();
    }


    public static void scanFile(String filename) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("RPNOutput.txt"));
            // BufferedWriter bw = new BufferedWriter(new FileWriter("../outputFiles/RPNOutput.txt"));
            Scanner sc = new Scanner(new File(filename));// Create our new
                                                         // scanner
            while (sc.hasNextLine()) { // While the scanner has information to
                                       // read
                String currentLine = sc.nextLine();// Read the next term
                if (currentLine.trim().length() == 0)
                    continue;
                // separate every element and store them in array
                String[] currentLineArr = currentLine.trim().split(" +");
                String lineResult = calculateLine(currentLineArr);
                
                bw.write(lineResult + "\n");
                
                System.out.println(lineResult);
            }
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String filename = args[0];
        scanFile(filename);
    }
}
