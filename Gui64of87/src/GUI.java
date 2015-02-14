import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
	private JTextField tf;
	private JCheckBox boldBox;
	private JCheckBox italicBox;
	
	GUI(){
		super("The Title");
		setLayout(new FlowLayout());
	
		tf = new JTextField("This is a sentence", 20);
		tf.setFont(new Font("Serif", Font.PLAIN, 14));
		
		add(tf);
		
		boldBox = new JCheckBox("bold");
		italicBox = new JCheckBox("italic");
		
		HandlerClass handler = new HandlerClass();
	
		boldBox.addItemListener(handler);
		italicBox.addItemListener(handler);
	
		add(boldBox);
		add(italicBox);	
	
	}
	
	private class HandlerClass implements ItemListener{
		public void itemStateChanged(ItemEvent ie){
			
			Font font = null;
			
			if(boldBox.isSelected() && italicBox.isSelected())
				font = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
			else if(boldBox.isSelected())
				font = new Font("Serif", Font.BOLD, 14);
			else if(italicBox.isSelected())
				font = new Font("Serif", Font.ITALIC, 14);
			else
				font = new Font("Serif", Font.PLAIN, 14);
			
			tf.setFont(font);
			
			}
		}
}
