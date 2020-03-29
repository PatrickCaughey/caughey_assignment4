package caughey_problem1;
import java.io.*;
import java.util.HashSet;

public class DuplicateRemover {
	
	private HashSet<String> uniqueWords;
	
	public DuplicateRemover() {
		uniqueWords = new HashSet<String>();
	}
	
	private void tryAdd(StringBuffer buffer) {
		String word = buffer.toString();
		if (!word.equals("")) {
			uniqueWords.add(word);
		}
		buffer.setLength(0);
	}
	
	public void remove(String dataFile) {
		StringBuffer buffer = new StringBuffer();
		int c = 0;
		FileReader input = null;
		try {
			input = new FileReader(dataFile);
			
			while ((c = input.read()) != -1) {
				if ((c <= 32 && c >= 0) || c == 127) {
					tryAdd(buffer);
				} else {
					buffer.append(Character.toLowerCase((char)c));
				}
			}
			tryAdd(buffer);
			
		} catch (FileNotFoundException e) {
			System.out.printf("Could not find %s.%n", dataFile);
			System.exit(-1);
		} catch (IOException e) {
			System.out.printf("Error reading from %s.%n", dataFile);
			System.exit(-1);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				System.out.printf("Could not close input stream.%n");
				System.exit(-1);
			}
		}
	}
	
	public void write(String outputFile) {
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(new File(outputFile)));
			for (String word : uniqueWords) {
				output.write(word + " \n");
			}
		} catch (IOException e) {
			System.out.printf("Error writing to or opening %s.%n", outputFile);
			System.exit(-1);
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				System.out.printf("Could not close output stream.%n");
				System.exit(-1);
			}
		}
	}
	
}
