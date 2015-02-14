import java.util.Random;

public class Rand {

	public static void main(String[] args) {
	int number;
		
	Random dice = new Random();
	
	for(int counter=1 ; counter<=10; counter++)
	{
		number = 1 + dice.nextInt(6);
		System.out.println(number);
	}
	}
}
