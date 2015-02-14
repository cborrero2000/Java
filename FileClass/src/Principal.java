import java.io.File;

public class Principal {
	public static void main(String[] args) {
		
		File x = new File("C:\\Test\\testing.txt");
		
		if(x.exists())
			System.out.println(x.getName() + " exists !!!");
		else
			System.out.println("This file doesn't exist");
			
	}
}
