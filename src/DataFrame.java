import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * DataFrame class reads, retrieves and converts all data from
 * csv file to a double 2D array. It takes the headername from
 * the file and counts its number of columns and rows.
 * 
 * @author Melissa Liao
 *
 */
public class DataFrame {
	
	private static int MAXROWS = 100;
	private String[] headerNames;
	private double[][] dataArray;
	private int numOfRows = 0;
	private int numOfCols = 0;
	
	public String[] getHeaderNames() {
		return this.headerNames;
		}
	
	public int getNumOfRows() {
		return this.numOfRows;
		}
	
	public int getNumOfCols() {
		return this.numOfCols;
		}
	
	/**
	 * getColumnByIndex() method is to retrieve the column index
	 * chosen by the user input in DataAnalysisCLI class. From there,
	 * it will find and add all the values of the dataArray that 
	 * belongs to the chosen column.
	 * 
	 * @param col represents the index of the column chosen in CLI
	 * @return it returns to DataSeries object type
	 */
	public DataSeries getColumnByIndex(int col) {
		// instantiate an object of DataSeries type with the column chosen by user
		DataSeries series = new DataSeries(getHeaderNames()[col]);
		for(int i=0; i<this.numOfRows; i++) {
			// it finds all the values in the dataArray from same column and adds to series
			series.add(this.dataArray[i][col]);
		}		
		return series;
	}

	/**
	 * DataFrame constructor is called in DataAnalysisCLI class
	 * to read and retrieve the data in csv file. It reads line by line
	 * and adds elements of the header and main data into an array.
	 * 
	 * @param filename represents the name of the file that the user
	 * wants to look into
	 * 
	 * @throws IOException message if file name input is not valid
	 * or not found in the path
	 */
	public DataFrame(String filename) throws IOException {
		Scanner s = new Scanner(new File(filename), "UTF-8");
		// first line of the file, reads the header
		String header = s.nextLine();
		// identify and separates elements of the header with ","
		this.headerNames = header.trim().split(",");
		// counts the number of columns
		this.numOfCols = this.headerNames.length;
		for(int i=0; i<headerNames.length; i++) {
			// trims and cleans each element of the header
			this.headerNames[i] = headerNames[i].trim();
		}
		
		// instantiate and create a 2D double array
		this.dataArray = new double[DataFrame.MAXROWS][this.numOfCols];
		// starts to read from the second line until the last
		while(s.hasNextLine()) {
			String line = s.nextLine();
			// every element of each line is identify with a comma
			String[] values = line.trim().split(",");
			for(int i=0; i<values.length; i++) {
				// values parsed into double type and added to array
				this.dataArray[this.numOfRows][i] = Double.valueOf(values[i].trim());
			}
			// for each line is counted as the number of rows 
			this.numOfRows++;
		}
		s.close();
	}	
	
	// toString() method is created to display data for DataAnalysisCLI in a presentable way
	@Override 
	public String toString() {		
		StringBuilder display = new StringBuilder();
		int sumOfChars = 0; // variable created to count the number of characters in headerNames
		for (int i=0; i<headerNames.length; i++) {
			display.append(" | ");
			display.append(headerNames[i]);
			// length of each element in header are counted
			sumOfChars += headerNames[i].length();
		}
		display.append(" | \n");
		// use total number of characters counted in header 
		// to create the length of the line for display 
		for (int i=0; i<sumOfChars; i++) {
			display.append("--");
		}
		display.append("\n");
		
		// display each elements of the data in a better way for user
		// only displays 10 rows or less from data file
		if (numOfRows > 10) {
			for(int row=0; row<10; row++) {
				for(int col=0; col<this.dataArray[row].length; col++) {
					display.append(" | ");
					display.append(dataArray[row][col]);
				}
				display.append(" | \n");
			}// adds three dots to communicate user than there are more rows in data
			display.append("    .	 \n");
			display.append("    .	 \n");
			display.append("    .	 \n");
		}
		else if (numOfRows <= 10) {
			for(int row=0; row<numOfRows; row++) {
				for(int col=0; col<this.dataArray[row].length; col++) {
					display.append(" | ");
					display.append(dataArray[row][col]);
				}
				display.append(" | \n");
			}
		}
		return display.toString();
	}

}
