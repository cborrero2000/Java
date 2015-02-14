import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(Color.BLUE);
		g.fillRect(25, 25, 100, 30);
		
		g.setColor(new Color(145,122,194));
		g.fillRect(25, 65, 100, 30);
		
		g.setColor(Color.RED);
		g.drawString("This is a text", 25, 120);
	}
}