
public class FileParser {
	
	DNAtree tree;
	
	public FileParser() {
		tree = new DNAtree();
	}
	
	public void executeTreeOperation(String currentLine) {
		
		if (currentLine.trim().length() == 0) {
			return;
		}
		
		// separate every element and store them in array
		// this array should always contain only two elements
		String[] currentLineArr = currentLine.trim().split(" +");
		
		switch(currentLineArr[0]) {
		case "insert":
			System.out.println("we insert " + currentLineArr[1]);
			break;
			
		case "remove":
			System.out.println("we remove " + currentLineArr[1]);
			break;
			
		case "search":
			System.out.println("we search " + currentLineArr[1]);
			break;
			
		case "print":
			if (currentLineArr.length == 1) {
				// print alone
				System.out.println("we print alone");
			} else if (currentLineArr[1].equals("lengths")) {
				// print lengths
				System.out.println("we print lengths");
			} else if (currentLineArr[1].equals("stats")) {
				// print stats
				System.out.println("we print stats");
			}
			break;
		}
	}
	

}
