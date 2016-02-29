/**
 * 
 */
package manben.a00937960.calculator;


/**
 * @author Manben Chen : A00937960
 * @version 10/11/2015
 *
 */
public class DivisionCalculator extends Calculator {

	/**
	 * Default constructor that passes "/" to super constructor
	 */
	public DivisionCalculator() {
		super("/");
	}

	/**
	 * @returns the quotient of x and y as float
	 * @param x
	 *            the value to be divided
	 * @param y
	 *            the value to be divided by
	 */
	public float calcValue(final int x, final int y) {
		if (y == 0) {
			return 0;
		} else {
			return (float) x / (float) y;
		}
	}
}
