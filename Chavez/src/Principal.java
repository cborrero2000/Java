import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
	
		String name;
		String saludo;
		
		name = JOptionPane.showInputDialog("Please enter your name");
		
		saludo = String.format("Hola %s Como esta el merengue", name);
		
		JOptionPane.showMessageDialog(null, saludo);
		
	}
	
	
	
}
