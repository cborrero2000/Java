import javax.swing.JOptionPane;
public class GUI {
	
	public static void main(String[] args) {
		
	
	String fn = new String(); 
	String sn = new String();
	int num1, num2, sum;
	
	fn = JOptionPane.showInputDialog("Enter First Number");
	sn = JOptionPane.showInputDialog("Enter Second Number");
	
	num1 = Integer.parseInt(fn);
	num2 = Integer.parseInt(sn);
	sum = num1 + num2;
	
	JOptionPane.showMessageDialog(null, "The answer is " + sum, "THE TITLE", JOptionPane.PLAIN_MESSAGE);
	}
}
