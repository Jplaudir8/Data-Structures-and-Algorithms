
public class RectangleFigure {
	private String name;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;

	public RectangleFigure(String name, int xCoord, int yCoord, int width, int height) {
		System.out.println("insert " + name + " " + xCoord + " " + yCoord + " " + width + " " + height);
		if (!validProperties(name, xCoord, yCoord, width, height)) {
			System.out.println("Rectangle rejected: (" + name + ", " + xCoord + ", " + yCoord + ", " + width + ", "
					+ height + ")");
			throw new IllegalArgumentException("Rectangle");
			// print error to console awaiting for prof answer.
		} else {
			System.out.println("Rectangle inserted: (" + name + ", " + xCoord + ", " + yCoord + ", " + width + ", "
					+ height + ")");
		}
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
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
		if (xCoord < 0 || xCoord > 1024 || yCoord < 0 || yCoord > 1024 || xCoord + width > 1024
				|| yCoord + height > 1024) {
			return false;
		}

		return true;
	}

	/**
	 * Validates the the correctness of the naming convention for the rectangle.
	 * 
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
}
