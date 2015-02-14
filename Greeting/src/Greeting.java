import java.util.Scanner;

public class Greeting {
	public static void main(String[] args) {
		
		String message;
		
		System.out.println("Enter your name:");
		Scanner scan = new Scanner(System.in);
		message = scan.nextLine();
		
		ShowMessage greet = new ShowMessage();
		greet.displayMessage(message);
				
	}

}
