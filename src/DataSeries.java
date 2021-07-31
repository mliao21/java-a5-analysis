
/**
 * DataSeries class creates a one dimensional array and retrieves
 * the values from the column chosen by the user in csv file.
 * It calculates and implements the basic statistics information
 * from IBasicStats interface. The class is used by DataFrame and DataAnalysisCLI.
 * 
 * @author Melissa Liao
 *
 */
public class DataSeries implements IBasicStats {
	
	private String label;
	private double[] data;
	private int count;
	
	/**
	 * The DataSeries constructor initializes the size of 
	 * the data and the count.
	 * 
	 * @param label represents the header name that is called
	 * in DataFrame class's getColumnByIndex() method.
	 */
	public DataSeries(String label) {
		this.label = label;
		data = new double[100];
		count = 0;
	}
	
	/**
	 * add() method creates the series of data from the column index.
	 * The method is called in DataFrame class's getColumnByIndex() method.
	 * 
	 * @param number represents the value that is added to the data array.
	 * Each element added to the array is counted.
	 */
	public void add(double number) {
		data[count] = number;
		count++;
	}
	
	public int size() {
		return this.count;
	}
	
	// toString() method is created to display the series in a presentable way
	@Override
	public String toString() {
		StringBuilder display = new StringBuilder();
		display.append(label + " [ ");
		// any series that has more than 10 values will only display the first 10 values
		if (count > 10) {
			for(int i=0; i<10; i++) {
				display.append(data[i]+" ");
			} // 3 dots is displayed at the end of the series to indicate that there are more values/
			display.append("... ]");
		}
		// any series that contains 10 or less values will display the whole series data
		else if (count <= 10) {
			for(int i=0; i<count; i++) {
				display.append(data[i]+" ");
			}
			display.append("]");
		}

		return display.toString();
	}
		
	// it gets the lowest value from the series
	public double getMin() {
		double min = data[0];
		for (int i=1; i<count; i++) {
			if (data[i] < min) {
				min = data[i];
			}
		}
		return min;
	}
	
	// it gets the highest value from the series
	public double getMax() {
		double max = data[0];
		for (int i=1; i<count; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		return max;
	}
	
	// calculates the sum of the series
	public double getSum() {
		double sum = 0;
		for (int i=0; i<count; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	// calculates the average value of the series
	public double getMean() {
		double mean = 0;
		mean = getSum()/this.count;
		return mean;
	}

}
