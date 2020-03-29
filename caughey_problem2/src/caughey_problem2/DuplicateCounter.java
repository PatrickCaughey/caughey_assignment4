package caughey_problem2;
import java.io.*;
import java.util.HashMap;

public class DuplicateCounter {
	
	private HashMap<String,Integer> wordCounter;
	
	public DuplicateCounter() {
		wordCounter = new HashMap<String,Integer>();
	}
	
	private void tryAdd(StringBuffer buffer) {
		String word = buffer.toString();
		if (!word.equals("")) {
			int was;
			if (wordCounter.get(word) == null) was = 0;
			else was = wordCounter.get(word);
			wordCounter.put(word, was + 1);
		}
		buffer.setLength(0);
	}
	
	public void count(String dataFile) {
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
			for (String word : wordCounter.keySet()) {
				output.write(word + " " + wordCounter.get(word) + " \n");
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
