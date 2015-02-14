import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame{

	private JButton lb;
	private JButton cb;
	private JButton rb;
	private FlowLayout layout;
	private Container container;
	
	Layout(){
		super("Title Layout");
		layout = new FlowLayout();
		container = getContentPane();
		setLayout(layout);

		lb = new JButton("LEFT");
		cb = new JButton("CENTER");
		rb = new JButton("RIGHT");
		
		add(lb);
		add(cb);
		add(rb);
		
		
		lb.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
					layout.setAlignment(FlowLayout.LEFT);
					layout.layoutContainer(container);
				}
				}
		);
		
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				layout.setAlignment(FlowLayout.CENTER);
				layout.layoutContainer(container);
			}
			}
	);
		
		rb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				layout.setAlignment(FlowLayout.RIGHT);
				layout.layoutContainer(container);
			}
			}
	);
		
	}
}



