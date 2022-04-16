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
 * This class represents a command parser that reads through inputs and based on them executes actions for the Skip List.
 *
 * @author Joan Piayet Perez Lozano (joanperezl)
 * @author Raena Rahimi Bafrani (raenar)
 * @version Apr 16, 2022
 */
public class Command {
	
	private String cmd; 
	
	public Command(String input) {
		cmd = input;
	}
	
	public void executeCommandOperation() {
		if (cmd.trim().length() == 0) {
            return;
        }
		
		// Develop method calls...
	}
}
