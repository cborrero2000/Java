
public class BC1 {

	public static void main(String[] args) {
		
			int [] [] array2D = {{0, 1, 2}, {3, 4, 5, 6}};
			Integer [] [] array2D2 = {{0, 1, 2}, {3, 4, 5, 6}};
			float[][] fa = {{1F,2F,3F,4F,5F,6F},{3F,5F,4F,2F}};
			Float[][] Fa = {{1F,2F,3F,4F,5F,6F},{3F,5F,4F,2F}};
			Float flt=3F;
			
			System.out.println(array2D2[1].getClass() + "");
			System.out.println(fa[1].getClass() + "");
			System.out.println(Fa[1].getClass() + "");
			System.out.println(flt.getClass() + "");

			System.out.print(array2D.length+ "" );
			System.out.print(array2D[0].length+ "" );
			System.out.print(array2D[1].getClass(). isArray() + "");
			System.out.print(array2D.getClass(). isArray() + "");
			System.out.println (array2D[0][1]);
			
			int [] [] array2D3 = null;
//			System.out.println(array2D3.getClass() + ""); //java.lang.NullPointerException

//			System.out.print(array2D3.length+ "" ); //java.lang.NullPointerException
//			System.out.print(array2D3[0].length+ "" );
//			System.out.print(array2D3[1].getClass(). isArray() + "");
//			System.out.print(array2D3.getClass(). isArray() + "");
			
			int [] [] iA = new int[2][];
			System.out.println(iA.length+ "" );
			//System.out.print(iA[0].length+ "" ); //java.lang.NullPointerException
			//System.out.print(iA[1].length+ "" ); //java.lang.NullPointerException
			//System.out.print(iA[2].length+ "" ); // java.lang.ArrayIndexOutOfBoundsException
			
			String str = "Hello";
			System.out.println(str.charAt(0));
			System.out.println(str.charAt(4));
			//System.out.println(str.charAt(5));
			
			String name="";
			String name2;
			System.out.println(name); //blank not null
			System.out.println(name.length()); //blank not null
			
			//System.out.println(name2); //Compilation Error. Variable not initialized
	}
}
