import javax.swing.JFrame;

public class Principal {
	public static void main(String[] args) {
		GUI window = new GUI();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(250, 250);
		window.setVisible(true);
	}

}
