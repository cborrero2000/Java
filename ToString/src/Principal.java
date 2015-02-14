
public class Principal {
	
	public static void main(String[] args) {
		
		ThisString objThisString = new ThisString(15, 16, 17);
		Present objPresent = new Present("Carlos", objThisString);
		
		System.out.printf("%s\n", objPresent);
	}

}
