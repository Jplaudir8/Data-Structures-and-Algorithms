import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FileParser {
	
	RandomAccessFile inputRAF;
	
	public FileParser(String file) throws FileNotFoundException {
		File inputFile = new File(file);
		inputRAF = new RandomAccessFile(inputFile, "r");
	}
	
	
	
}
