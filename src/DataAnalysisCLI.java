import java.io.IOException;

/**
 * 
 * Implements a command line interface to:
 *  - ask the user for a csv filename
 *  - displays csv filename content
 *  - ask the user to select a column
 *  - displays column values with basic statistics
 *  
 *  Uses:
 *  - KeyboardReader for I/O
 *  - DataFrame to load csv data
 *  - DataSeries to calculate basic statistics.
 * 
 * @author Yves Pauchard
 *
 */
public class DataAnalysisCLI {
	
	private DataFrame data;
	private KeyboardReader reader;

	public DataAnalysisCLI() {
		reader = new KeyboardReader();
	}
	
	/**
	 * Implements the user dialog
	 */
	public void run() {
		reader.display("*** Basic statistics of csv columns ***\n");
		reader.prompt(" Enter a filename, e.g test.csv: ");
		String filename = reader.getKeyboardInput();
		
		//DataFrame constructor may throw IOException
		// exit method if there is an error loading the csv file.
		try {
			data = new DataFrame(filename);
		}catch(IOException e) {
			reader.display(e.getMessage() + " - please try again.\n");
			return;
		}
		
		// Print information on data frame loaded from csv
		reader.display(" shape=("+data.getNumOfRows()+", "+data.getNumOfCols()+")\n");
		reader.display("\n"+data.toString());
		
		
		// We ask the user to select a column
		reader.display("\n Select a column by index:\n");
		String[] names = data.getHeaderNames();
		for(int i=0; i<names.length; i++) {
			reader.display(i+": "+names[i]+"\n");
		}
		reader.prompt("\n Enter desired column index: ");
		int col = reader.getKeyboardInteger();
		
		//Checking if user selected a valid column index
		if(col>=0 && col < data.getNumOfCols()) {
			// Display the column and basic statistics
			DataSeries s = data.getColumnByIndex(col);
			
			reader.display(s.toString());
			reader.display("\nsize = "+s.size());
			reader.display("\nmin = "+s.getMin());
			reader.display("\nmax = "+s.getMax());
			reader.display("\nsum = "+s.getSum());
			reader.display("\nmean = "+s.getMean());
			reader.display("\n*** END ***\n");
		}else {
			reader.display("Sorry "+col+" is not a valid column index.\n");
			reader.display("Please try again.\n");
		}
		
		
	}
	
	public static void main(String[] args) {
		DataAnalysisCLI app = new DataAnalysisCLI();
		app.run();

	}

}
