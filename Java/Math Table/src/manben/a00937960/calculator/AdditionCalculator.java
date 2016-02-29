/**
 * 
 */
package manben.a00937960.calculator;

/**
 * @author Manben Chen : A00937960
 * @version 10/09/2015
 */
public class AdditionCalculator extends Calculator{
	
	/**
	 * Default constructor that passes "+" to the super constructor
	 */
	public AdditionCalculator() {
		super("+");
	}
	
	/**
	 * @return the sum of x and y as float
	 * @param x the value to be added
	 * @param y the value to be added
	 */
	public float calcValue(final int x, final int y) {
		return x + y;
	}
}
