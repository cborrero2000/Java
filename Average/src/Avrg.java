import java.util.Scanner;

public class Avrg {
	public static void main(String[] args) {
		
		int total=0;
		int grade;
		int count=0;
		int average=0;

		Scanner scan = new Scanner(System.in);
		
		while(count<10)
		{
			grade = scan.nextInt();
			total += grade;
			count++;
		}
		
		average = total/10;
		
		System.out.println("The average is: " + average);
	}
}
