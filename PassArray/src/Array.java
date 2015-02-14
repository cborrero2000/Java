
public class Array {
	public static void main(String[] args) {
		int[] bucky = {3, 4, 5, 6, 7}; 
		change(bucky);
		
		for(int x: bucky)
			System.out.println(x);
	}
	
	public static void change(int[] x)
	{
		for(int counter=0; counter<x.length; counter++){
			//System.out.println("Before " + x[counter]);
			x[counter]+=5;
			//System.out.println("After " + x[counter]);
		}
	}

}
