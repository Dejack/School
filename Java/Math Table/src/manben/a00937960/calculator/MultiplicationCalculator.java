/**
 * 
 */
package manben.a00937960.calculator;


/**
 * @author Manben Chen : A00937960
 * @version 10/11/2015
 *
 */
public class MultiplicationCalculator extends Calculator {
	/**
	 * Default constructor that passes "*" to the super constructor
	 */
	public MultiplicationCalculator() {
		super("*");
	}

	/**
	 * @return The product of x and y as float
	 * @param x
	 *            the value to be multiplied
	 * @param y
	 *            the value to be multiplied
	 */
	public float calcValue(final int x, final int y) {
		return x * y;
	}
}
