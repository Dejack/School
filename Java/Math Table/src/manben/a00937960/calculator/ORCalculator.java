/**
 * 
 */
package manben.a00937960.calculator;

/**
 * @author Manben Chen : A00937960
 * @version 10/13/2015
 */
public class ORCalculator extends Calculator{

	/**
	 * Default constructor that passes "&" to the super constructor
	 */
	public ORCalculator() {
		super("|");
	}
	
	/**
	 * @param x An integer value to be OR'd
	 * @param y An integer value to be OR'd
	 * @return the OR value of x and y
	 */
	public float calcValue(final int x, final int y) {
		return x | y;
	}
}