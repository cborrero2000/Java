import java.util.*;

public class Principal {

	public static void main(String[] args) {
		
		final Formatter x;
		
		try{
			x = new Formatter("carlos.txt");
			System.out.println("You just have created a File");
		}
		catch(Exception e){
			System.out.println("You got an error");
		}
		
		
		
	}
	
}
