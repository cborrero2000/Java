import java.util.*;

public class instname {

	// practice of getClass and instanceof
	
	//X x = new X(); Interfaces don't have constructors so they cannot be instantiated. emnum have private constructors
					// therefore they cannot be instantiated too
	public static void main(String[] args) {
		
		X x1 = new A2();
		X x2 = new B2();
		X x3= new C2();
		
		System.out.println("|x1: " + x1.getClass() + " |x2: " + x2.getClass() + " |x3: " + x3.getClass() + " |X: "+ X.class
				+" |A2: "+ A2.class+"|");
		
		List lst = new ArrayList();
		
		System.out.println("lst: " + lst.getClass()); 
		
		System.out.println("x1 instanceof X: " + (x1 instanceof X));
		System.out.println("x2 instanceof X: " + (x2 instanceof X));
		System.out.println("x3 instanceof X: " + (x3 instanceof X));
		System.out.println("x1 instanceof A2: " + (x1 instanceof A2));
		System.out.println("x2 instanceof A2: " + (x2 instanceof A2));
		System.out.println("x3 instanceof A2: " + (x3 instanceof A2));
		System.out.println("x1 instanceof B2: " + (x1 instanceof B2));
		System.out.println("x2 instanceof B2: " + (x2 instanceof B2));
		System.out.println("x3 instanceof B2: " + (x3 instanceof B2));
		System.out.println("x1 instanceof C2: " + (x1 instanceof C2));
		System.out.println("x2 instanceof C2: " + (x2 instanceof C2));
		System.out.println("x3 instanceof C2: " + (x3 instanceof C2));
		
		//C2 x4 = (C2) new A2();
		//C2 x5 = (C2) new B2();
		C2 x6= new C2();
		
		//System.out.println("x4 instanceof C2: " + (x4 instanceof C2));
		//System.out.println("x5 instanceof C2: " + (x5 instanceof C2));
		System.out.println("x6 instanceof C2: " + (x6 instanceof C2));
		
	}
	
}

interface X{}

class A2 implements X{}

class B2 extends A2{}

class C2 extends B2{}