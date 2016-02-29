/**
 * 
 */
package manben.a00937960.displayer;

import manben.a00937960.table.Table;


/**
 * @author Manben Chen : A00937960
 * @version 10/09/2015
 */
public class ConsoleDisplayer implements Displayer {

	/**
	 * 
	 * @param table
	 *            The table to be displayed Displays the table to the screen
	 */
	public void displayTable(Table table) {
		System.out.print("    " + table.getDescription());
		for (int i = table.getStart(); i <= (table.getStart() + table.getSize()); i++) {
			System.out.format("%6d", i);
		}
		System.out.println();
		System.out.print("     ");
		for (int i = 0; i < (table.getSize() + 1); i++) {
			System.out.print("------");
		}

		System.out.println();
		int index = 0;
		for (int i = table.getStart(); i <= (table.getSize() + table.getStart()); i++) {
			System.out.format("%3d |", i);
			if (table.getDescription().equals("/")) {
				for (int k = 0; k <= (table.getSize()); k++) {
					System.out.format("%6.2f", table.getValueAt(index, k));
				}
			} else {
				for (int k = 0; k <= (table.getSize()); k++) {
					System.out.format("%6.0f", table.getValueAt(index, k));
				}
			}
			System.out.println();
			index++;
		}
	}
}
