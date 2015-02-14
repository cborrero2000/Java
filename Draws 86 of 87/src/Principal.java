import javax.swing.JFrame;
import javax.swing.JButton;


public class Principal {
	public static void main(String[] args) {
		Draws ds = new Draws();
		JFrame f = new JFrame("Shapes");
		f.add(ds);
		f.setSize(300,600);
		f.setVisible(true);
	}
}
