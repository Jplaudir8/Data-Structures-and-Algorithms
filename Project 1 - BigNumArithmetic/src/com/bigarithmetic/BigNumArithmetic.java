package com.bigarithmetic;

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

public class BigNumArithmetic {

    /**
     * Remove possible leading zeroes that number may contain.
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


    public static void main(String[] args) {
        String filename = args[0];
        beginParsing(filename);
    }


    public static void beginParsing(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));// Create our new
                                                         // scanner
            while (sc.hasNext()) {// While the scanner has information to read
                String numOrOperator = sc.next();// Read the next term
                System.out.println(removeLeadingZeroes(numOrOperator));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
