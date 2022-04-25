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

/**
 * This class represents a command parser that reads through inputs and based on
 * them executes actions for the Skip List.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Apr 16, 2022
 */
public class Command {

	private String cmd;
	private Rectangle1 rectangle;
	

	public Command(String input, Rectangle1 rectangle) {
		this.cmd = input;
		this.rectangle = rectangle;
		
	}

	public void executeCommandOperation() {
		if (cmd.trim().length() == 0) {
			return;
		}

		// Developing method calls for:
		
		String[] currentLineArr = cmd.toLowerCase().trim().split(" +");
		
		if (currentLineArr[0].equals("insert")) {
			// insert(name, x, y, w, h),
			rectangle.insert(currentLineArr[1], 
					Integer.valueOf(currentLineArr[2]), 
					Integer.valueOf(currentLineArr[3]), 
					Integer.valueOf(currentLineArr[4]), 
					Integer.valueOf(currentLineArr[5])
					);
		} else if (currentLineArr[0].equals("remove")) {
			if (currentLineArr.length == 2) {
				// remove by matching name
				// remove(name)
			} else {
				// remove by matching coordinates + dimensions
				// remove(x, y, w, h)
			}
		} else if (currentLineArr[0].equals("regionsearch")) {
			rectangle.regionSearch(Integer.valueOf(currentLineArr[1]), 
					Integer.valueOf(currentLineArr[2]), 
					Integer.valueOf(currentLineArr[3]), 
					Integer.valueOf(currentLineArr[4]));
		} else if (currentLineArr[0].equals("intersections")) {
			rectangle.intersections();
		} else if (currentLineArr[0].equals("search")) {
			rectangle.search(currentLineArr[1]);
		} else if (currentLineArr[0].equals("dump")) {
			rectangle.dump();
		}
		
	}
	
	
}
