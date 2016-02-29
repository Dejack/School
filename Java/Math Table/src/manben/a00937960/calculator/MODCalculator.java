/**
 * 
 */
package manben.a00937960.calculator;

/**
 * @author Manben Chen : A00937960
 * @version 10/13/2015
 */
public class MODCalculator extends Calculator{
	
	/**
	 * Default constructor that passes "%" to the super constructor
	 */
	public MODCalculator() {
		super("%");
	}
	
	
	/**
	 * @param x the value to be modulo'd
	 * @param y the value to be modulo'd by
	 * @return the remainder of x / y as float
	 */
	public float calcValue(final int x, final int y) {
		return x % y;
	}
}
