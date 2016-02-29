package manben.a00937960.displayer;
import javax.swing.JFrame;

import manben.a00937960.table.Table;


public class SwingDisplayer
    implements Displayer
{
    public void displayTable(final Table table)
    {        
        final DisplayerFrame frame;
        
        frame = new DisplayerFrame();
        frame.init(table);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}