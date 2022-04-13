//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;

import student.TestCase;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

/**
* Tests the ExternalSort class.
*
* @author Joan Piayet Perez Lozano (joanperezl)
* @author Raena Rahimi Bafrani (raenar)
* @version March 22, 2022
*/
public class ExternalsortTest extends TestCase {
    
    
    /**
     * set up for tests
     */
    public void setUp() {
        //nothing to set up.
    }
    
    /**
     * Get code coverage of the class declaration.
     * @throws FileNotFoundException 
     */
    public void testExternalsortInit() throws FileNotFoundException {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        String[] in = {"sampleInput16b.bin", "16"};
        try {
            GenBinaryDataFile.main(in);
            String[] args = { "sampleInput26.bin", "26" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Test Genfile_proj3 class constructor
     */
    public void testGenFileDataClass() {
        GenBinaryDataFile genBinary = new GenBinaryDataFile();
        assertNotNull(genBinary);
    }

}
