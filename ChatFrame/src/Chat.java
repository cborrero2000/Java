import javax.swing.*;
import java.awt.*;

public class Chat {
public static void main(String[] args) {
	
	JFrame frame = new JFrame("Chat Frame");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,400);
	
	JButton send = new JButton("send");
	JButton reset = new JButton("reset");
	
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("FILE");
	JMenu m2 = new JMenu("Help");
	
	mb.add(m1);
	mb.add(m2);
	
	JMenuItem m11 = new JMenuItem("Open");
	JMenuItem m22 = new JMenuItem("Save as");
	
	m1.add(m11);
	m1.add(m22);
	
	frame.getContentPane().add(BorderLayout.NORTH, mb);

	
	JTextField tField = new JTextField(10);
	JLabel tArea = new JLabel("Enter Text");
		
	JPanel panelS = new JPanel();
	panelS.add(tArea);
	panelS.add(tField);
	panelS.add(send);
	panelS.add(reset);
	panelS.doLayout();
	
	frame.getContentPane().add(BorderLayout.SOUTH, panelS);
	JTextArea ta = new JTextArea();
	
	frame.getContentPane().add(BorderLayout.CENTER, ta);
	
	
	
	
	
	
	
	frame.setVisible(true);
	
	
}
}
