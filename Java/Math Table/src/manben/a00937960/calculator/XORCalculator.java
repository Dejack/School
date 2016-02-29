/**
 * 
 */
package manben.a00937960.calculator;

/**
 * @author Manben Chen : A00937960
 * @version 10/13/2015
 */
public class XORCalculator extends Calculator{

	/**
	 * Default constructor that passes "&" to the super constructor
	 */
	public XORCalculator() {
		super("^");
	}
	
	/**
	 * @param x An integer value to be XOR'd
	 * @param y An integer value to be XOR'd
	 * @return the XOR value of x and y
	 */
	public float calcValue(final int x, final int y) {
		return x ^ y;
	}
}