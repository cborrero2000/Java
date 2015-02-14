import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		String s1;
		
		s1 = JOptionPane.showInputDialog("Enter Text");
		
		JOptionPane.showMessageDialog(null, s1);
		
		Animal[] animalList = new Animal[5];
		Dog d = new Dog();
		Fish f = new Fish();
		
		animalList[0] = d;
		animalList[1] = d;
		
		System.out.println("Merequetengue");
	}
	
}
