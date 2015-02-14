
public class Loop {
	public static void main(String[] args) {
		int[] bucky = {1, 2, 3, 4, 5};
		int total=0;
		
		for(int x: bucky)
		{
			System.out.println(x);
			total +=x;
		}
		
		System.out.println("\n" + total);
	}

}
