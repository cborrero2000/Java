package enthuware;

public class Enthuware_33 {

		public static double getSwitch(String str){
			return Double.parseDouble(str.substring(1, str.length()-2) );}

		public static void main(String args []){
//			switch(getSwitch(args[0])){
//			case 0.0 : System.out.println("Hello"); 
//			case 1.0 : System.out.println("World"); break; 
//			default : System.out.println("Good Bye");
//			}
			
			System.out.println(getSwitch(args[0]));
			
			double db = 0.;
			
			System.out.println("db: " + db);
			
			Integer itn = 1;
			
			itn.equals(null);
			itn=null;
//			itn.equals(null);
			
		      int arr1[] = { 0, 1, 2, 3, 4, 5 };
		      int arr2[] = { 0, 10, 20, 30, 40, 50 };
		    
		      // copies an array from the specified source array
		      System.arraycopy(arr1, 2, arr2, 1, 3);
		      System.out.print("array2 = ");
		      System.out.print(arr2[0] + " ");
		      System.out.print(arr2[1] + " ");
		      System.out.print(arr2[2] + " ");
		      System.out.print(arr2[3] + " ");
		      System.out.print(arr2[4] + " ");
			
			}}