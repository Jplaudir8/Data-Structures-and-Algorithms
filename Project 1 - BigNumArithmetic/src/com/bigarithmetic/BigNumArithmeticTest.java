package com.bigarithmetic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import student.TestCase;

public class BigNumArithmeticTest extends TestCase {


//    public void shouldTakeUserInput() {
//        Scanner sc = new Scanner(System.in);
//        calculator.scanFile("");
//        String input = "add 5";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        assertEquals(
//            "56669777 99999911111 + 352324012 + 3 ^ 555557778 * = 562400792227677956625810678708149922000000",
//            sc.nextLine().toString());
//    }
    
    public void testScanFile() throws FileNotFoundException {
        BigNumArithmetic.scanFile("/Users/joanperezlozano/Downloads/DSATestingInputs/MixedOperations1Line.txt");
        Scanner sc = new Scanner(new File("/Users/joanperezlozano/Downloads/RPNOutput.txt"));
        assertEquals(
            "56669777 99999911111 + 352324012 + 3 ^ 555557778 * = 562400792227677956625810678708149922000000",
            sc.nextLine().toString());
    }
}
