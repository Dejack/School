package manben.a00937960.table;

import manben.a00937960.calculator.Calculator;

/**
 * 
 */

/**
 * @author Manben Chen : A00937960
 * @version 04/10/2015
 *
 */
public class Table {

	// Fields
	private int start;
	private int stop;
	private int size;
	private float[][] handle;
	private String description;

	public Table(final int start, final int stop, final Calculator calc) {
		this.start = start;
		this.stop = stop;
		description = calc.getDescription();
		createTable(start,stop,calc);
		size = stop - start;

	}

	/**
	 * Displays the table to the screen
	 */

	private void createTable(final int begin, final int finish, final Calculator calc) {

		int difference = finish - begin;
		// Creating a 2d float array that fits finish - begin elements
		handle = new float[difference + 1][difference + 1];
		for (int i = 0; i <= difference; i++) {
			for (int k = 0; k <= difference; k++) {
				handle[i][k] = calc.calcValue(i + begin, k + begin);
			}
		}
	}
	
	/**
	 * 
	 * @param row The row of value you wish to get
	 * @param col The column of the value you wish to get
	 * @return the value at [row][column] as float, returns 0 on failure.
	 */
	public float getValueAt(final int row, final int col) {
		if (row > size || col > size) {
			System.out.println("Invalid parameters for row or col");
			return 0;
		} else {
			return handle[row][col];
		}
	}

	/**
	 * @param type
	 *            the type to set
	 */

	/**
	 * @return the start as int
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @return the stop as int
	 */
	public int getStop() {
		return stop;
	}

	/**
	 * @return the description as String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the size as int
	 */
	public int getSize() {
		return size;
	}

	
	
	
}
