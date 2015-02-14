import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Drop extends JFrame{
	
	private JComboBox box;
	private JLabel picture;
	
	private String[] filename = {"flippy.png", "33.png"};
	private Icon[] pics = {new ImageIcon(getClass().getResource(filename[0])), new ImageIcon(getClass().getResource(filename[1]))};
	
	Drop(){
		super("DropDownList");
		setLayout(new FlowLayout());

		box = new JComboBox(filename);
		
		box.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent ie){
							if(ie.getStateChange() == ie.SELECTED)
								picture.setIcon(pics[box.getSelectedIndex()]);
					}
				}
		);
		
		add(box);
		picture = new JLabel(pics[0]);
		add(picture);
	}
}