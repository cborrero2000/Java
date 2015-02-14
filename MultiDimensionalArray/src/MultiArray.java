
public class MultiArray {
	public static void main(String[] args) {
	int[][] firstArray = {{2,3,4,5,6,7},{1,2,3,4,5,9}};
	int[][] secondArray = {{1,2,3,4},{1},{7,7,7,6,6,5,4,43,3,3,3,3,2,2}};
	
	System.out.println("Displaying FirstArray");
	display(firstArray);
	System.out.println("Displaying secondArray");
	display(secondArray);
	}

	
	public static void display(int x[][]){
		for(int row=0; row<x.length; row++)
		{
			for(int column=0; column<x[row].length; column++)
			{
				System.out.print(x[row][column] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
}