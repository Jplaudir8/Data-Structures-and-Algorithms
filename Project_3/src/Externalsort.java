import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This program represents an external sort algorithm. 
 * The program sorts a set of fixed-length records of 16 bytes. 
 * It starts by generating a series of sorted subsets(runs) 
 * through replacement selection. Once these runs have been generated, 
 * they go through a merging phase, where a multi-way merge algorithm 
 * is implemented. The program finalizes by generating a file with 
 * all the records sorted. Access to the binary file to be sorted is performed
 * through RandomAccessFile to preserve I/O efficiency.
 */

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
 * The class containing the main method.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version March 22, 2022
 */
public class Externalsort {

    /**
     * @param args
     *            Command line parameters
     */
    public static void main(String[] args) {
        // Your main method code goes here.
        if (args.length != 1) {
            System.out.println("Invalid command and/or input...");
            return;
        }

        MultiwayMerge rs = new MultiwayMerge(args[0]);
        rs.replacementSelection();
        rs.runMultiwayMerge();

        String outFileName = "outfile";
        Record[] firstRecords;
        if (Files.exists(Paths.get(outFileName))) {
            ReaderWriter sortedFile = new ReaderWriter(outFileName,
                16, 512);
            long[] fileStats = sortedFile.getFileStats();
            int numBlocks = (int)fileStats[2];
            firstRecords = new Record[numBlocks];
            int status = 0;
            for (int i = 0; i < numBlocks; i++) {
                byte[] record = new byte[16];
                status = sortedFile.getRecord(record, i * 8192);
                if (status == 0) {
                    firstRecords[i] = new Record(record);
                }
            }
            sortedFile.closeFile();
            int numLinesToPrint = firstRecords.length / 5;
            if (firstRecords.length % 5 != 0) {
                numLinesToPrint += 1;
            }
            for (int j = 0; j < numLinesToPrint; j++) {
                int k = 0;
                while (((5 * j + k) < firstRecords.length) && (k < 4)) {
                    System.out.print(firstRecords[5 * j + k].toString() + " ");
                    k++;
                }
                if (((5 * j + k) < firstRecords.length)) {
                    System.out.print(firstRecords[5 * j + k].toString());
                }
                System.out.println("");
            }
        }
    }
}