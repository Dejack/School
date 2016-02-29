/**
 * 
 */
package manben.a00937960.calculator;

/**
 * @author Manben Chen : A00937960
 *
 */
public abstract class Calculator {
	
	private String description; 
	
	protected Calculator(String s) {
		description = s;
	}
	
	
	
	/**
	 * @return the description as String
	 */
	public String getDescription() {
		return description;
	}

	public abstract float calcValue(final int x, final int y);
}
