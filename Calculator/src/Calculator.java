import java.util.Scanner;

public class Calculator {
	
	public static void main(String[] args) {
		
	
	double fnum, snum, answ, reminder;
	
	Scanner scan = new Scanner(System.in);
	
	System.out.println("Enter First Number:");
	fnum = scan.nextDouble();
	
	System.out.println("Enter Second Number:");
	snum = scan.nextDouble();
	
	answ = fnum + snum;
	
	reminder = fnum % snum;
	
	System.out.println(answ);
	
	System.out.println("Reminder: " + fnum % snum);
	System.out.println("Reminder: " + reminder);
	
	}

}
