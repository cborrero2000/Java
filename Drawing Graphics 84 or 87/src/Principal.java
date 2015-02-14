import javax.swing.JFrame;

public class Principal {
	public static void main(String[] args) {
		JFrame j = new JFrame("Painting");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Drawing d = new Drawing();
		j.add(d);
		j.setSize(400,250);
		j.setVisible(true);
	}
}
