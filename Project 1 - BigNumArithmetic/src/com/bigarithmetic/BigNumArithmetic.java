package com.bigarithmetic;

import java.io.File;
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
     * @return
     */
    private static String removeLeadingZeroes(String token) {

        // token == 0, +, -, ^, [1 - 9]
        if (token.length() == 1)
            return token;

        int currIdx = 0;
        for (currIdx = 0; currIdx < token.length(); currIdx++) {
            // if we are in the last character of the token means all previous
            // digits were 0's. If this last one is 0 as well, then we keep it.
            if (currIdx == token.length() - 1 && token.charAt(currIdx) == '0') {
                return "0";
            }
            // break when the current digit != 0
            if (token.charAt(currIdx) != '0') {
                break;
            }
        }

        // if we did not reach the end of the token then there are
        // digits different from 0 starting from the index currIdx.
        StringBuilder trimmedToken = new StringBuilder("");
        for (int i = currIdx; i < token.length(); i++) {
            trimmedToken.append(token.charAt(i));
        }
        return trimmedToken.toString();
    }


    private static SinglyLinkedListObj add(
        SinglyLinkedListObj num1,
        SinglyLinkedListObj num2) {
        
        SinglyLinkedListObj.ListNode currN1 = num1.head;
        SinglyLinkedListObj.ListNode currN2 = num2.head;
        SinglyLinkedListObj result = new SinglyLinkedListObj();
        int carry = 0;
        while (currN1 != null || currN2 != null) {
            int n1 = currN1 != null ? (Integer)currN1.value : 0;
            int n2 = currN2 != null ? (Integer)currN2.value : 0;
            int n3 = n1 + n2 + carry;
            carry = n3 / 10; // getting possible carry
            result.addLast(n3 % 10); // adding unit
            if (currN1 != null) currN1 = currN1.getNext();
            if (currN2 != null) currN2 = currN2.getNext();
        }
        System.out.println("finished Loop");
        if (carry > 0) {
            result.addLast(carry);
        }
        
        return result;
    }


    private static SinglyLinkedListObj getLinkedListInteger(String currToken) {
        SinglyLinkedListObj newNumber = new SinglyLinkedListObj();
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

    public static String calculateLine(String[] operationLine) {
        // First validate that it will be possible to do the math.
        
        if (!operationIsValid(operationLine)) {
            return Arrays.toString(operationLine);
        }
        
        // create a stack and StringBuilder to be used for printin in file
        Stack numsStack = new Stack();
        StringBuilder resultPrint = new StringBuilder("");
        
        for (int i = 0; i < operationLine.length; i++) {
            // if current token is an operator then pop 2 elements from stack,
            // solve and push res
            // else its a number so push it to stack
            String currToken = removeLeadingZeroes(operationLine[i]);
            System.out.print(currToken + " ");
            resultPrint.append(currToken + " ");
            
            if (currToken.equals("+") || currToken.equals("*") || currToken
                .equals("^")) {
                SinglyLinkedListObj numA = (SinglyLinkedListObj)numsStack.pop();
                SinglyLinkedListObj numB = (SinglyLinkedListObj)numsStack.pop();
                System.out.println("we popped stack twice " + numA.toString() + numB.toString());
                SinglyLinkedListObj result = new SinglyLinkedListObj();
                switch (currToken) {
                    case "+":
                        result = add(numA, numB);
                        break;
                        
                }
                
                numsStack.push(result);
            }
            else {
                // push element found
                SinglyLinkedListObj newNum = getLinkedListInteger(currToken);
                numsStack.push(newNum);
            }
        }
        
        resultPrint.append("= " + numsStack.pop().toString());
        return resultPrint.toString();
    }


    public static void scanFile(String filename) {
        try {
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
                System.out.print(lineResult);
            }
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
