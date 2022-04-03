import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileParser {
	
	RandomAccessFile inputRAF;
	
	public FileParser(String file) throws FileNotFoundException {
		File inputFile = new File(file);
		inputRAF = new RandomAccessFile(inputFile, "r");
	}
	
	public Record readRecord() throws IOException {
		
		byte[] singleRecord = new byte[16];
		
		// if existent, load up to 16 bytes from input file (1 record)
		if (inputRAF.read(singleRecord) != -1) {
			Record newRecord = new Record(singleRecord);
			return newRecord;
		} 
		
		return null;
	}
	
}
