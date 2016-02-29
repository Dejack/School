package manben.a00937960.displayer;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JButton;

import manben.a00937960.table.Table;


public class DisplayerFrame
    extends JFrame
{
	
	private static final int TABLE_SIZE_ADJUSTMENT = 2;
	
    public void init(final Table table)
    {
        setTitle(table.getDescription() + "(" + table.getStart() + ", " + (table.getSize() + table.getStart())  + ")");
        setLayout(new GridLayout(table.getSize() + TABLE_SIZE_ADJUSTMENT,
                                   table.getSize() + TABLE_SIZE_ADJUSTMENT));

        add(new JButton(table.getDescription()));
        
        for(int col = 0; col <= table.getSize(); col++)
        {
            add(new JButton(Integer.toString(table.getStart() + col)));
        }
        
        for(int row = 0; row <= table.getSize(); row++)
        {
            add(new JButton(Integer.toString(table.getStart() + row)));
            
            for(int col = 0; col <= table.getSize(); col++)
            {
                add(new JButton(Float.toString(table.getValueAt(row, col))));
            }
        }
    }
}
