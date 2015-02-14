import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	GUI(){
		super("The Title");
		setLayout(new FlowLayout());
		
		JButton reg;
		JButton custom;
		
		reg = new JButton("Regular Button");
		add(reg);
		
		
		Icon b = new ImageIcon(getClass().getResource("33-code-and-pixel-logos.png"));
		Icon x = new ImageIcon(getClass().getResource("Flippy_x_Flakey_by_Kitmit13.png"));
		
		custom = new JButton("Custom Button", b);
		custom.setRolloverIcon(x);
		add(custom);
	
		HandlerClass handler = new HandlerClass();
		reg.addActionListener(handler);
		custom.addActionListener(handler);
	}
	
	
		private class HandlerClass implements ActionListener{
			
			public void actionPerformed(ActionEvent event){
				
				JOptionPane.showMessageDialog(null, String.format("%s", event.getActionCommand()));
				
			}
		}
		}
