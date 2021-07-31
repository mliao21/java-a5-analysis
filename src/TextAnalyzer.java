import java.util.Scanner;

/**
 * TextAnalyzer class takes String text given by user input through
 * TextAnalysisGUI class and calculates its total number of sentences
 * and words contained in the text.
 * 
 * @author Melissa Liao
 *
 */
public class TextAnalyzer {
	
	private int numOfSentences = 0;
	private int numOfWords = 0;
	
	/**
	 * @param line represents the line of a text and counts
	 * the number of sentences by splitting and finding 
	 * text that contains ".!?" signs.
	 * 
	 * @return returns to the total number of sentences per line.
	 */
	public int countSentences(String line) {
		String[] tok = line.trim().split("[.!?]");
		if(tok[0].equals("")) return 0;
		else return tok.length;
	}
	
	/**
	 * @param line represents the line of a text and counts
	 * the number of words by given whitespace in between.
	 * 
	 * @return returns to the total number of words per line.
	 */
	public int countWords(String line) {
		String[] tok = line.trim().split("\\s");
		if(tok.length == 1 && tok[0].equals("")) return 0;
		else return tok.length;
	}
	
	/**
	 * @param text represents the whole text that is going
	 * to be analyzed by textInput() method. It scans each line
	 * of the text and uses the countSentences() and countWords() methods
	 * to calculate the total sum of sentences and words of the
	 * whole text.
	 * 
	 */
	public void textInput(String text) {
		Scanner s = new Scanner(text);
		String line;
		// Scanner will go through each line from the text
		while(s.hasNextLine()) {
			line = s.nextLine();
			// It adds in the count of each line to the number of sentences and words
			numOfSentences += countSentences(line);
			numOfWords += countWords(line);
		}
	}
	
	// It appends the result of numOfWords and numOfSentences in a String
	@Override
	public String toString() {
		StringBuilder display = new StringBuilder();
		display.append("***Analyzing text... ");
		display.append("\nNumber of Words: " + numOfWords);
		display.append("\nNumber of Sentences: " + numOfSentences);
		
		return display.toString();
	}
	
}
