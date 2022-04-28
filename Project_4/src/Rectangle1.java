import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
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
	
	private SkipList<String, RectangleFigure> skipList;
	
	// implementing methods:
	// remove(name)
	// remove(x, y, w, h)
	
	public Rectangle1 () {
		skipList = new SkipList<>();
	}
	
	public void insert(String name, int x, int y, int w, int h) {
		try {
			RectangleFigure newRectangle = new RectangleFigure(name, x, y, w, h);
			// if object creation succeeded, insert rectangle
			skipList.insert(name, newRectangle);
		} catch(IllegalArgumentException e) {
			System.out.println("Rectangle rejected: (" + name + ", " + x + ", " + y + ", " + w + ", "
					+ h + ")");
		}
	}
	
	/**
	 * Remove rectangle with input name, if there is more than one match, remove the first found.
	 * @param name
	 */
	public void remove(String name) {
		RectangleFigure removedRectangle = skipList.remove(name);
		
		if (removedRectangle == null) {
			System.out.println("Rectangle not removed: " + name);
		} else {
			System.out.println("Rectangle removed: (" + removedRectangle + ")");
		}
	}
	
	public void remove(int x, int y, int width, int height) {
		if (width <= 0 || height <= 0) {
			System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + width + ", " + height + ")");
			return;
		}
		Iterator<RectangleFigure> itr = skipList.iterator();
		while (itr.hasNext()) {
			RectangleFigure currRect = itr.next();
			// if exists, remove it
			if (currRect.getxCoord() == x && 
					currRect.getyCoord() == y && 
					currRect.getWidth() == width &&
					currRect.getHeight() == height) {
				RectangleFigure rectRemoved = skipList.remove(currRect.getName());
				System.out.println("Rectangle removed: (" + rectRemoved + ")");
				return;
			}
		}
		System.out.println("Rectangle not found: (" + x + ", " + y + ", " + width + ", " + height + ")");
	}
	
	public void regionSearch(int x, int y, int width, int height) {
		// validate params
		if (width <= 0 || height <= 0) {
			System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + width + ", " + height + ")");
			return;
		}
		
		// bottom left and top right coordinates of region search rectangle
		int bottomLeft1_x = x;
		int bottomLeft1_y = y;
		int topRight1_x = x + width;
		int topRight1_y = y + height;
		
		System.out.println("Rectangles intersecting region (" + x + ", " + y + ", " + width + ", " + height + "):");
		// iterate through elements and print if intersects
		Iterator<RectangleFigure> itr = skipList.iterator();
		while (itr.hasNext()) {
			// bottom left and top right coordinates of current rectangle
			RectangleFigure currRect = itr.next();
			int bottomLeft2_x = currRect.getxCoord();
			int bottomLeft2_y = currRect.getyCoord();
			int topRight2_x = currRect.getxCoord() + currRect.getWidth();
			int topRight2_y = currRect.getyCoord() + currRect.getHeight();
			
			// skip non-intersects
			if (bottomLeft1_x > topRight2_x || topRight1_x < bottomLeft2_x) {
				continue;
			}
			
			if (bottomLeft1_y > topRight2_y || topRight1_y < bottomLeft2_y) {
				continue;
			}
			System.out.println(currRect); // print intersect
		}
	}
	
	public void intersections() {
		System.out.println("Intersection pairs:");
		Iterator<RectangleFigure> outterItr = skipList.iterator();
		while (outterItr.hasNext()) {
			RectangleFigure outterRect = outterItr.next();
			Iterator<RectangleFigure> innerItr = skipList.iterator();
			while (innerItr.hasNext()) {
				RectangleFigure innerRect = innerItr.next();
				// if both point to same object, skip
				if (outterRect == innerRect) {
					continue;
				}
				
				// iterate through elements and print if intersect
				// outterRect corners
				int bottomLeft1_x = outterRect.getxCoord();
				int bottomLeft1_y = outterRect.getyCoord();
				int topRight1_x = outterRect.getxCoord() + outterRect.getWidth();
				int topRight1_y = outterRect.getyCoord() + outterRect.getHeight();
				
				
				// innerRect corners
				int bottomLeft2_x = innerRect.getxCoord();
				int bottomLeft2_y = innerRect.getyCoord();
				int topRight2_x = innerRect.getxCoord() + innerRect.getWidth();
				int topRight2_y = innerRect.getyCoord() + innerRect.getHeight();
				
				// skip non-intersects
				if (bottomLeft1_x > topRight2_x || topRight1_x < bottomLeft2_x) {
					continue;
				}
				
				if (bottomLeft1_y > topRight2_y || topRight1_y < bottomLeft2_y) {
					continue;
				}
				
				System.out.println("(" + outterRect + " | " + innerRect + ")");
			}
		}
	}
	
	
	public void search(String rectangleName) {
		// System.out.println("search " + rectangleName);
		SinglyLinkedList<RectangleFigure> rectList = skipList.find(rectangleName);
		if (rectList != null) {
			SinglyLinkedList<RectangleFigure>.ListNode<RectangleFigure> currNode = rectList.getHead();
			while (currNode != null) {
				System.out.println("(" + currNode.getValue().toString() + ")");
				currNode = currNode.getNext();
			}			
		} else {
			System.out.println("Rectangle not found: " + rectangleName);
		}
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
