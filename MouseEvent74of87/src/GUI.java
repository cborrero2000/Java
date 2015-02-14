import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
	JPanel mousePanel;
	JLabel statusBar;
	
	GUI(){
		super("Mouse Events");
		
		mousePanel = new JPanel();
		mousePanel.setBackground(Color.WHITE);
		add(mousePanel, BorderLayout.CENTER);
		
		statusBar = new JLabel("default");
		add(statusBar, BorderLayout.SOUTH);
		
		HandlerClass handler = new HandlerClass();
		
		mousePanel.addMouseListener(handler);
		mousePanel.addMouseMotionListener(handler);
	}
	
	private class HandlerClass implements MouseListener, MouseMotionListener{

		public void mouseClicked(MouseEvent event) {
			statusBar.setText(String.format("Clicked at %d,%d", event.getX(), event.getY()));
		}

		public void mouseEntered(MouseEvent arg0) {
			statusBar.setText("The mouse has left the window");
			mousePanel.setBackground(Color.RED);
		}
		public void mouseExited(MouseEvent arg0) {
			statusBar.setText("You left the area");
			mousePanel.setBackground(Color.BLUE);
		}
		public void mousePressed(MouseEvent arg0) {
			statusBar.setText("You press down the mouse");
		}
		public void mouseReleased(MouseEvent arg0) {
			statusBar.setText("You released the button");
		}
		public void mouseDragged(MouseEvent arg0) {
			statusBar.setText("You are dragging the mouse");
		}
		public void mouseMoved(MouseEvent arg0) {
			statusBar.setText("You moved the mouse");
		}
	}
}