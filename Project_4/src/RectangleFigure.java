import java.util.Objects;

public class RectangleFigure {
	private String name;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;

	public RectangleFigure(String name, int xCoord, int yCoord, int width, int height) {
		if (!validProperties(name, xCoord, yCoord, width, height)) {
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private boolean validProperties(String name, int xCoord, int yCoord, int width, int height) {

		// Check correctness of naming format
		if (!nameIsValid(name)) {
			// System.out.println("invalid name");
			return false;
		}

		// Check for negative coordinates
		if (xCoord < 0 || yCoord < 0) {
			// System.out.println("invalid coordinates");
			return false;
		}

		// Check for positive dimensions
		if (width <= 0 || height <= 0) {
			// System.out.println("invalid width or height dimensions");
			return false;
		}

		// Validate it is within world box constraints
		if (xCoord < 0 || xCoord > 1024 || yCoord < 0 || yCoord > 1024 || xCoord + width > 1024
				|| yCoord + height > 1024) {
			// System.out.println("outside world box constraints");
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
			// System.out.println("less than 1");
			return false;
		}

		// check for correctness of characters
		for (int i = 0; i < rectName.length(); i++) {
			int currChar = rectName.charAt(i);
			// if first character is not a letter
			if (i == 0 && !Character.isLetter(currChar)) {
				// System.out.println("first character is not a letter");
				return false;
			}

			if (i >= 1 && !Character.isLetterOrDigit(currChar) && currChar != '_') {
				// System.out.println("some remaining character is not a letter, digit neither
				// _");
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return name + ", " + xCoord + ", " + yCoord + ", " + width + ", " + height;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(height, name, width, xCoord, yCoord);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RectangleFigure other = (RectangleFigure) obj;
		return height == other.height && Objects.equals(name, other.name) && width == other.width
				&& xCoord == other.xCoord && yCoord == other.yCoord;
	}
	
	

}
