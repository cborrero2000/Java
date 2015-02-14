package enthuware;

public class Enthuware_17 {
	   public static void main(String[] args){
		      int[][] a = { { 00, 01 }, { 10, 11 } };
		      int i = 99;
		      try {
		         a[val()][i = 1]++;
		      } catch (Exception e) {
		         System.out.println( i+", "+a[1][1]);
		      }
		   }
		   static int val() throws Exception {  
			   System.out.println("print inside the catch");
			   int a= Integer.MIN_VALUE;
			   System.out.println(Integer.toBinaryString(a));
			   a=-a;
			   System.out.println(Integer.toBinaryString(a));
		     throw new Exception("unimplemented");  
//		     System.out.println("print inside the catch");
		   }
}
