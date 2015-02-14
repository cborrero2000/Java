
public class Compound {
	double principal;
	double rate;
	double amount;
	
	public static void main(String[] args) {
		
		double principal = 10000;
		double rate = 0.01;
		double amount;
		
		for(int day = 1; day<=20; day++)
		{
			amount = principal*Math.pow(1 + rate, day);
			System.out.println(day + "   " + amount);
		}
		
	}

}
