package enthuware;
import static enthuware.Logs.*;


class Aqua{
	public void method1(){
	}
}
public class Enthuware_40 {

	public static void main(String[] args) {
		test();
	     new Aqua().method1();
	     
	     ps(10 * (5 + 8) % (132 - 2) ^ 7);	// Sol: 7 % is module not division
   	     
	}
	
	public static void test(){
		double d = 10.99999;
		int i = (int)d;
		System.out.println("i: " + i);
		System.out.println("i: sdf " + Math.round(10.450000000000000001));
		System.out.println("43e1: " +  43e1);
	}
	
	public float parseFloat( String s ){
	     float f = 0.0f;      // 1
	     try{
	          f = Float.valueOf( s ).floatValue();    // 2
	          return f ;      // 3
	     }
	     catch(NumberFormatException nfe){
	        f = Float.NaN ;    // 4
	      return f;     // 5
	     }
	     finally {
	         return f;     // 6
	     }
	     //return f ;    // 7
	     
	     

	     
	 }


	
//	public void test(){
//		boolean b=true;
//		switch(b){	//Cannot switch on a value of type boolean. Only convertible int values, strings or enum variables are permitted
//		
//		}
//	}
	
	
}
