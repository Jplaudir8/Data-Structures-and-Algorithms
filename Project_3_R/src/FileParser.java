import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
/**
 * 
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class FileParser {

    private RandomAccessFile inputRAF;
    private File file;
    /**
     * the size in bytes of one record
     */
    public static final int RECORDSIZE = 16;

    /**
     * 
     * @param file
     *            string commands
     * 
     */
    public FileParser(String file) throws FileNotFoundException {
        File inputFile = new File(file);
        inputRAF = new RandomAccessFile(inputFile, "r");
    }


    /**
     * Command line parameters
     * 
     * @return up to 16 bytes if there is any, null otherwise
     * @throws IOException
     */
    public Record readRecord() throws IOException {

        byte[] singleRecord = new byte[16];

        // if existent, load up to 16 bytes from input file (1 record)
        if (inputRAF.read(singleRecord) != -1) {
            Record newRecord = new Record(singleRecord);
            return newRecord;
        }

        return null;
    }

}
