import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class List extends JFrame{
	private JList list;
	private static String[] colorNames = {"blue", "red", "white", "black"};
	private static Color[] colors = {Color.BLUE, Color.RED, Color.WHITE, Color.BLACK};
	
	List(){
		super("List Program");
		setLayout(new FlowLayout());
		
		list = new JList(colorNames);
		list.setVisibleRowCount(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add(new JScrollPane(list)); // Place a frame and scrolldown bar if needed
		//add(list); // Doesn't put a mark nor scrolldown bar
		
		list.addListSelectionListener(
				new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent lse){
						getContentPane().setBackground(colors[list.getSelectedIndex()]);					
				}
			}
		);
		
	}
}
