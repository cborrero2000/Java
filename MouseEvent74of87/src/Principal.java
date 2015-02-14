import javax.swing.JFrame;


public class Principal {
	public static void main(String[] args) {
		GUI obj = new GUI();
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setSize(300, 300);
		obj.setVisible(true);
	}
}
