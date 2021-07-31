import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * TextAnalysis Graphical User Interface allows the user to input
 * text and display its total number of words and sentences.
 * 
 * @author Melissa Liao
 *
 */
public class TextAnalysisGUI extends JFrame implements ActionListener {
	private JTextArea input, output;
	private JButton analyze, clear;
	private TextAnalyzer analyzer;

	public TextAnalysisGUI(String title) { 
		// new TextAnalyzer is instantiated
		this.analyzer = new TextAnalyzer();
		
		// creates input and output area and defines their size
		input = new JTextArea(21, 87);	
		output = new JTextArea(4, 20);
		
		// sets a button to clear text
		clear = new JButton("CLEAR");
		clear.addActionListener(this);
		
		// sets a button to analyze text
		analyze = new JButton("ANALYZE");
		analyze.addActionListener(this);
		
		// set the input area in a JPanel at the top
		JPanel inputPanel = new JPanel();
		inputPanel.add(new JLabel("Type or copy text below..."));
		inputPanel.add(input);
		
		// set the output area in a JPanel at the center
		JPanel outputPanel = new JPanel();
		outputPanel.add(output);
		
		// sets the buttons in a JPanel at the bottom
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 2));
		buttonPanel.add(analyze);
		buttonPanel.add(clear);
		
		// defines the layout of the panels
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("North", inputPanel);
		contentPane.add("Center", outputPanel);
		contentPane.add("South", buttonPanel);
		
		// defines the size of the GUI window
		setSize(800, 500);
		setTitle(title);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
				
		//TODO: Add handling of display button - Displays text analysis
		if (e.getSource() == analyze) {
			// get text input by user and converts it to String type
			String text = input.getText();
			// input text is passed in to textInput() method
			analyzer.textInput(text);
			// results are displayed by overriding toString() method
			output.append(analyzer.toString());
		}
		
		// TODO: Add handling of clear button - Analyzes new text
		if (e.getSource() == clear) {
			// a new TextAnalyzer is instantiated
			this.analyzer = new TextAnalyzer();
			// added to clear the input and output area
			input.setText("");
			output.setText("");
		}
	}

	public static void main(String[] args) {
		new TextAnalysisGUI("Text Analysis");

	}

}
