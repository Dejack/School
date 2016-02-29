/**
 * 
 */
package manben.a00937960.calculator;


/**
 * @author Manben Chen : A00937960
 * @version 10/11/2015
 */
public class SubtractionCalculator extends Calculator {
	
	/**
	 * Default constructor that passes "-" to super constructor
	 */
	public SubtractionCalculator() {
		super("-");
	}
	/**
	 * @return the difference of x and y as float
	 * @param x the value to be subtracted by
	 * @param y the value to be subtracted
	 */
	public float calcValue(final int x, final int y) {
		return x - y;
	}
}
