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
	
	private String name;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	
	public Rectangle1(String name, int xCoord, int yCoord, int width, int height) {
		if (!validProperties(name, xCoord, yCoord, width, height)) {
			// throw new IllegalArgumentException("One or more invalid properties for the world box.");
			// print error to console awaiting for prof answer. 
		}
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width  = width;
		this.height = height;
	}
	
	public boolean validProperties(String name, int xCoord, int yCoord, int width, int height) {
		
		// Check correctness of naming format
		if (!nameIsValid(name)) {
			return false;
		}
		
		// Check for negative coordinates
		if (xCoord < 0 || yCoord < 0) {
			return false;
		}
		
		// Check for positive dimensions
		if (width <= 0 || height <= 0) {
			return false;
		}
		
		// Validate it is within world box constraints
		
		
		return true;
	}
	
	/**
	 * Validates the the correctness of the naming convention for the rectangle.
	 * @param rectName
	 * @return
	 */
	private boolean nameIsValid(String rectName) {
		if (rectName.length() < 1) {
			// Requires at least one character
			return false;
		}
		
		// check for correctness of remaining characters
		for (int i = 0; i < rectName.length(); i++) {
			int currChar = rectName.charAt(0);
			// if first character is not a letter
			if (i == 0 && !Character.isLetter(currChar)) {
				return false;
			}
			
			if (i >= 1 && !Character.isLetterOrDigit(currChar) && currChar != '_') {
				return false;
			}			
		}
		return true;
	}
	
	public static void main(String[] args) {
		// Read commands
		Scanner scannedFile = scanFile(args[0]);
		while (scannedFile.hasNext()) {
			String line = trimWhitespaceOf(scannedFile.nextLine());
			if (!line.isEmpty()) {
				Command currCommand = new Command(line);
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
