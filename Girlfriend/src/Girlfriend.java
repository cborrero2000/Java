import java.util.Scanner;

public class Girlfriend {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//System.out.println("Enter the name of your first Girlfriend:");
		//String name = scan.nextLine();
		
		GFMethods gf = new GFMethods("Keyla");
		GFMethods gf2 = new GFMethods("Nathaly");
		//gf.setName(name);
		gf.sayingHerName();
		gf2.sayingHerName();
	}

}
