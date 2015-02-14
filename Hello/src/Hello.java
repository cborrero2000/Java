//import java.util.Scanner;

public class Hello {
	public static void main(String args[])
	{
		int count;
		double tuna =5.28;

		//Scanner bucky = new Scanner(System.in);
		
		System.out.println("Hello Venezuela");
		System.out.print("Hello Venezuela");
		System.out.println("");
		System.out.print(tuna);
		
		//System.out.println(bucky.nextLine());
		
		//System.out.print("After bucky.nextLine()");
		
		count = 1;
		count++;
	//}
	
	class Parent { } 
	class DerivedOne extends Parent { } 
	class DerivedTwo extends Parent { }

	//Which of the following statements is correct for the following expression.
	// Ans: Compile fines at runtime and compilation.
	Parent p = new Parent(); 
	
	DerivedOne d1 = new DerivedOne();
	
	DerivedTwo d2 = new DerivedTwo();
	p = d1;
	
	
	int[][] array = new int[10][5];
	
	System.out.println("array lenght: " + array.length);
	System.out.println("array hashCode: " + array.hashCode());
		
	int[][][] array2 = new int[10][5][2];
	
	System.out.println("array2 lenght: " + array2.length);
	System.out.println("array2 hashCode: " + array2.hashCode());
	
	int[][][][][] array3 = new int[12][6][10][5][2];
	
	System.out.println("array3 lenght: " + array3.length);
	System.out.println("array3 hashCode: " + array3.hashCode());
	
	}
}

