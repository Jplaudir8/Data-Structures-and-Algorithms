import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//On my honor:
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project
//with anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

/**
 * The Rectangle class represents a Skip List data structure that stores...(to
 * elaborate)
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Apr 16, 2022
 */
public class Rectangle1 {
	
	private SkipList skipList;
	
	// implementing methods:
	// insert(name, x, y, w, h),
	// remove(name)
	// remove(x, y, w, h)
	// regionsearch(x, y, w, h)
	// intersections()
	// search(name)
	// dump()
	
	public Rectangle1 () {
		skipList = new SkipList();
	}
	
	public void insert(String name, int x, int y, int w, int h) {
		try {
			RectangleFigure newRectangle = new RectangleFigure(name, x, y, w, h);
			// if object creation succeeded, insert rectangle
			skipList.insert(name, newRectangle);
		} catch(IllegalArgumentException e) {
			System.out.println("Rectangle rejected: (" + name + ", " + x + ", " + y + ", " + w + ", "
					+ h + ")\n");
		}
	}
	
	public void remove(String name) {
		
	}
	
	public void remove(int x, int y, int width, int height) {
		
	}
	
	public void regionSearch(int x, int y, int w, int height) {
		
	}
	
	public void intersections() {
		
	}
	
	
	public void search() {
		
	}
	
	public void dump() {
		skipList.printNodes();
	}
	
	public static void main(String[] args) {
		Rectangle1 rectangleControl = new Rectangle1();
		// Read commands
		Scanner scannedFile = scanFile(args[0]);
		while (scannedFile.hasNext()) {
			String line = trimWhitespaceOf(scannedFile.nextLine());
			if (!line.isEmpty()) {
				Command currCommand = new Command(line, rectangleControl);
				currCommand.executeCommandOperation();
			}
		}
		scannedFile.close();
	}

	/**
	 * Trims leading and trailing whitespace. And also extra space that might be
	 * present in between the commands.
	 * 
	 * @param lineRead
	 * @return
	 */
	private static String trimWhitespaceOf(String lineRead) {
		String cleanedLine = lineRead.trim().replaceAll("\\s+", " ");
		return cleanedLine;
	}

	/**
	 * Scans the file to be read
	 * 
	 * @param filePath path to the file to be read
	 * @return scanned file as a scanner
	 */
	public static Scanner scanFile(String filePath) {
		try {
			FileReader file = new FileReader(filePath);
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
