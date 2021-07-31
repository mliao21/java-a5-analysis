
/**
 * 
 * Interface defining a set of basic statistics methods:
 *  - Minimum value
 *  - Maximum value
 *  - Sum of all values
 *  - Mean of all values
 * 
 * @author Yves Pauchard
 *
 */
public interface IBasicStats {
	
	public abstract double getMin();
	
	public abstract double getMax();
	
	public abstract double getSum();
	
	public abstract double getMean();

}
