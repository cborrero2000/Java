import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame{
	private String details;
	private JLabel statusBar;
	
	GUI(){
		super("Click Counter");
		statusBar =  new JLabel("Default");
		add(statusBar, BorderLayout.SOUTH);
		addMouseListener(new MouseClass());
	}
	
	class MouseClass extends MouseAdapter{
		
		public void mouseClicked(MouseEvent event){
			details = String.format("You clicked %d ", event.getClickCount());
			
			if(event.isMetaDown())
				details += "with right mouse button";
			else if(event.isAltDown())
				details += "with center mouse button";
			else
				details += "with left mouse button";

			statusBar.setText(details);
		}
	}
}
