import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame{

	private JLabel item1;
	
	GUI(){
		super("This is My Title");
		setLayout(new FlowLayout());
		item1 =  new JLabel("This is my Label");
		item1.setToolTipText("This Text is shown when hovering");
		add(item1);
	}
}
