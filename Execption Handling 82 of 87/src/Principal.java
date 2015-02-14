import java.util.*;

public class Principal {
	public static void main(String[] args) {
		int x = 1;
		do{
			try{
				Scanner input = new Scanner(System.in);
				System.out.println("Enter first num: ");
				int	n1 = input.nextInt();
				System.out.println("Enter second num: ");
				int	n2 = input.nextInt();
				int sum = n1/n2;
				System.out.println("Result: " + sum);
				x =2;
			}
			catch(Exception e){
				System.out.println("You cannot do that");
			}
		}while(x==1);
	}
}
