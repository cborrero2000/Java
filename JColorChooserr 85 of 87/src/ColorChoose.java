import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorChoose extends JFrame {

	JButton b;
	Color color = Color.WHITE;
	JPanel pane;
	
	ColorChoose(){
		super("Color Box");
		b = new JButton("Choose a Color");
		pane = new JPanel();
		pane.setBackground(color);
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				color = JColorChooser.showDialog(null, "Pick a color" ,color);
				if (color == null)
					color = Color.WHITE;
					
					pane.setBackground(color);
				

				
			}
		});

	
	add(pane, BorderLayout.CENTER);
	add(b, BorderLayout.SOUTH);
	setSize(475, 150);
	setVisible(true);
	}
	
	}
