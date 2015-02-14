
public class Arguments {
	public static void main(String[] args) {
		
		average(1, 2, 3, 10);
		
	}
	
	//public static void average(int y, int... numbers) It's also valid, var-arg has to be always the last of a method's argument
	public static void average(int... numbers)
	{
		double total=0;
		double average;
		
		for(int x:numbers)
			total +=x;
		
		average = total/numbers.length;
		System.out.println("The Average is " + average);
	}
}
