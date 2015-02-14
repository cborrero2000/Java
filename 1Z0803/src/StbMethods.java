
public class StbMethods {
public static void main(String[] args) {
	StringBuilder b1 = new StringBuilder("snorkler");
	StringBuilder b2 = new StringBuilder("yoodler");
	b1.append(b2.substring(2, 5).toUpperCase());
	System.out.println("b1: " + b1 + "            b2: " + b2);
	
	b1 = new StringBuilder("snorkler");
	b2 = new StringBuilder("yoodler");
	b2.insert(3,  b1.append("a"));
	System.out.println("b1: " + b1 + "              b2: " + b2);
	
	b1 = new StringBuilder("snorkler");
	b2 = new StringBuilder("yoodler");
	b1.replace(3, 4, b2.substring(4)).append(b2.append(false));
	System.out.println("b1: " + b1 + " b2: " + b2);
	
	 int arr1[] = { 50, 1, 2, 3, 4, 5 };
     int arr2[] = { 70, 10, 20, 30, 40, 50 };
   
     // copies an array from the specified source array
     System.arraycopy(arr1, 0, arr2, 0, 1);
     System.out.print("array2 = ");
     System.out.print(arr2[0] + " ");
     System.out.print(arr2[1] + " ");
     System.out.print(arr2[2] + " ");
     System.out.print(arr2[3] + " ");
     System.out.print(arr2[4] + " ");
     
     float fl=12;
     int hexnum=0Xff;
     
     System.out.println("hexnum: " + hexnum);
     
	
	}
}
