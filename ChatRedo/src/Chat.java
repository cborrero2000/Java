import javax.swing.*;
import java.awt.*;

public class Chat {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Chat Frame");
		
		frame.setSize(400, 400);
		
		/**** MenuBar **************************/
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("FILE");
		JMenu help = new JMenu("Help");
		JMenuItem m11 = new JMenuItem("Open");
		JMenuItem m12 = new JMenuItem("Save as");
		
		mb.add(file);
		mb.add(help);
		file.add(m11);
		file.add(m12);
		
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter Text");
		JTextField text = new JTextField(10);
		JButton send = new JButton("Send");
		JButton reset = new JButton("Reset");
		
		panel.add(label);
		panel.add(text);
		panel.add(send);
		panel.add(reset);
		
		JTextArea ta = new JTextArea();
		
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.getContentPane().add(BorderLayout.CENTER, ta);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		
		frame.setVisible(true);
		
	}
	
}
