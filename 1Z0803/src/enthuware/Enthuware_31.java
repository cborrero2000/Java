//package enthuware;
//
//public class Enthuware_31 {
//
//
//	
//	public static void main(String[] args) {
//		byte b=1;
//		short s=1;
//		char c=1;
//		int i=1;
//		
//		i=b*b;
//		
//		i = b;	// No casting needed because int is bigger than a byte.
//		i=b<<b;
//		i=b>>b;
//		
//		b = (byte)i;
////		b = b + i;		Resuts is int time which does not fit into a byte.
//		b +=i;			// Int is narrowed into a byte as long the value hold by i fits into an int.
//		
//		final int ix=1;
//		char cx = ix;	// CONSTANT values up to int can be assigned without cast to variables of lesser size
//						// as long as the constant value fits into the size of the target variable.
//		
//		for(;Math.random()<.05? true : false;){ }
//	}
//	
//}
//
//interface I{
//	void m1();
//}
//
//abstract class Klass{
//	public void m1(){};
//}
//
//class subClass extends Klass implements I{
////	public void m1(){};
//}