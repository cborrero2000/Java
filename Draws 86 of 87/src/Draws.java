import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class Draws extends JPanel {
	
		private JButton button = new JButton("This is a Button");
		
		public void paintComponent(Graphics g){

			super.paintComponent(g);
			
			g.setColor(Color.black);
			g.drawLine(20, 20, 120, 80);
			
			g.setColor(Color.BLUE);
			g.drawRect(20, 120, 120, 60);
			
			g.setColor(Color.RED);
			g.fillOval(20, 200, 120, 60);
			
			g.setColor(Color.CYAN);
			g.fill3DRect(20, 280, 120, 60, true);
			
			g.setColor(Color.CYAN);
			g.fill3DRect(20, 380, 120, 60, false);
			
			add(button);
			
			//super.paintComponent(g);  // with this instruction here only the 
										// JButton is drawn because the supper class knows
										// how to draw a JButton but not a the shapes we just draw
			
			g.setColor(Color.PINK);
			g.fill3DRect(20, 380, 120, 60, false);
			
			super.paintComponent(g);
			super.paintComponent(g);
			super.paintComponent(g);
			super.paintComponent(g);
			super.paintComponent(g);
			
			g.fill3DRect(20, 380, 120, 60, false);
		}
		
	}

