import java.util.*;

public class Test {

	String n1;
	String n2;
	// Execution Order Seq: 
	Test(){
		n1="Hello";
		n2=n1;
		System.out.println("Inside Test Constructor");
	}
	
	public static void method12(){
		// static{System.out.println("Static_Block");} No static block inside of static or non-static methods
		{System.out.println("Non-Static_Block_method12");} // Non-static blocks can be inside or static and non-static methods
	}
	
	public void method14(){
		// static{System.out.println("Static_Block");} No static block inside of static or non-static methods
		{System.out.println("Non-Static_Block_method14");} // Non-static blocks can be inside or static and non-static methods
	}
	
	//public static int main(String[] args) {...}
	//Error: Main method must return a value of type void in class Test, please 
	//	define the main method as:
	//   public static void main(String[] args)
	
	//static{System.out.println("Static_Block");} // static block has to be outside of the main. they get executed when
												//instantiating the class
	
	{System.out.println("Non_static_Block_outside_main");} // non-static block can be inside or outside of the main. They get executed when
	//instantiating the class
	
	public static void main(String[] args) {
		System.out.println("static void main been being executed");
		List lst1 = new ArrayList<Integer>();
		List lst2 = new ArrayList<String>();
//		{System.out.println("Non_static_Block_inside_main");}
		Test t1 = new Test();
		{System.out.println("Non_static_Block_inside_main");}
//		Test t2 = new Test();
		
		
		ArrayList<String> lst3 = new ArrayList<String>();
		lst3.remove(new ArrayList());
		
		String[][] str = new String[5][];
		
		
		System.out.println(lst1.toString()); // output: []
		
		System.out.println(lst1.getClass()); // output: class java.util.ArrayList
		System.out.println(lst2.getClass()); // output: class java.util.ArrayList
		//return 1; NOOOOOOO main can only be void
		
		System.out.println("args: " + args[0]);
		
		main(new Integer(12)); //It compiles fine
	}
	
	static{System.out.println("Static_Block_after_main");} // static block has to be outside of the main. they get executed when
	
	public static int main(Integer args){
	//public static int main(String[] args){ Error Duplicated even return  type is int instead of void
	//public static int main(String... args){ Error Duplicated even using epsilon ... instead of []
	//public static int main(int args){ // Good overloading of the main since parameter is Integer instead of String.
											// It's not executed as the Main main
	//public static int main(int[] args)	//Good overloading
	//public static int main(Integer[] args)	//Good overloading
		List lst1 = new ArrayList<Integer>();
		List lst2 = new ArrayList<String>();
		lst1.add(33);
		
		
		
		System.out.println("main_2"+lst1.toString()); // output: []
		
		System.out.println("main_2"+lst1.getClass()); // output: class java.util.ArrayList
		System.out.println("main_2"+lst2.getClass()); // output: class java.util.ArrayList
		
		
		StringBuilder sb= new StringBuilder("SE"); 
		StringBuilder sb1= new StringBuilder(3);
		StringBuilder [] sb2= new StringBuilder[]{};
		//sb.remove() There is not remove method in StringBuilder class
		
		sb.append("EE").append("ME").append("TE").substring(1,2); // substring has to be at the end of the StringBuffer, this is a String method.
		System.out.println(sb);
		sb.delete(2, 3);
		System.out.println(sb);
		
		//sb.append("EE").append("ME").append("TE").substring(1,2).append("Hello"); ERROR substring should be the last one
		
		//abstract int var=3; IN JAVA VARIABLES CANNOT BE ABSTRACT
		
		String removeTest="Hello World";
		//removeTest.remove(2);  Remove Method is contained only on ArrayList
		
		System.out.println(30 - 12/(2*5)+1); // output 30, 12/10 = 1 since it's treated as an integer: 30 - 1 + 1
		
		Test t = new Test();
		
		System.out.println(1<2? "Hello":"World"); // Prints "Hello"
		
		
		String message1 = "Wham bam!";
		String message2 = new String("Wham bam!");
		if (message1 == message2)
		System.out.println("They match");
		if (message1.equals(message2))
		System.out.println("They really match");
//		What is the result?
//		A. They match
//		They really match
//		B. They really match
//		C. They match
//		D. Nothing Prints
//		E. They really match
//		They really match 
		
		int Var1=-5;
		int Var2=Var1--;
		int Var3=Var2;
		//int Var4=++Var1;
		
		System.out.println("Var1: " + Var1 + " Var2: " + Var2 + " Var3: " + Var3 /*+ " Var4: " + Var4*/);
		
		String h1="Hello";
		String h2=h1;
		String h3=h2;
		
		if(h1==h2)
			System.out.println("They Match");
		if(h3.equals(h3))
			System.out.println("They Really Match");
		
		String [] str = new String[5]; // length = 5 even if not initialized any of its elements
		
		System.out.println("str.length: " + str.length);
		
		char[] c = new char[5]; // length = 5 even if not initialized any of its elements
		
		System.out.println("c.length: " + c.length);
		c[0]='a';
		c[1]='\u0000';
		c[2]='a';
		//c[7]='d'; (Runtime Exception) Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 7
		System.out.println("c.length: " + c.length);
		
		System.out.println("c[0]: " + c[0]);
		System.out.println("c[1]: " + c[1]);
		System.out.println("c[2]: " + c[2]);
		
		String [] str1 = new String[3];
		//str1[10]="hello"; // java.lang.ArrayIndexOutOfBoundsException: 10
		
		str1[0]="one";
		str1[2]="three";
		
		System.out.println("str1.length: " + str1.length);
		System.out.println("str1: " + str1[0]+","+str1[1]+","+str1[2]);// output:str1: one,null,three
		//System.out.println("str1: " + str1[0]+","+str1[1]+","+str1[3]);// (Rutime Execption) java.lang.ArrayIndexOutOfBoundsException: 3
		
		
//		Given:
//			#1
//			package handy.dandy;
//			public class KeyStroke {
//			public void typeExclamation() {
//			System.out.println("!")
//			}
//			}
//			#2
//			package handy; /* Line 1 */
//			public class Greet { /* Line 2 */
//			public static void main(String[] args) { /* Line 3 */
//			String greeting = "Hello"; /* Line 4 */
//			System.out.print(greeting); /* Line 5 */
//			Keystroke stroke = new Keystroke; /* Line 6 */
//			stroke.typeExclamation(); /* Line 7 */
//			} /* Line 8 */
//			} /* Line 9 */
//			What three modifications, made independently, made to class greet, enable the code to compile
//			and run?
//			A. Line 6 replaced with handy.dandy.keystroke stroke = new KeyStroke ( );
//			B. Line 6 replaced with handy.*.KeyStroke = new KeyStroke ( );
//			C. Line 6 replaced with handy.dandy.KeyStroke Stroke = new handy.dandy.KeyStroke();
//			D. import handy.*; added before line 1
//			E. import handy.dandy.*; added after line 1
//			F. import handy.dandy,KeyStroke; added after line 1
//			G. import handy.dandy.KeyStroke.typeException(); added before line 1 
		
		B.methodA1();
		
		return 2;
		
		
//		Consider the following two classes (in the same package but defined in different source files):
//
//			public class Square {
//			    double side = 0;
//			    double area;
//
//			    public Square(double length){        this.side = length;    }
//
//			    public double getSide() {  return side;    }
//
//			    public void setSide(double side) {  this.side = side;   }
//
//			    double getArea() {   return area;   }
//			}
//			public class TestClass {
//			    public static void main(String[] args) throws Exception {
//			        Square sq = new Square(10.0);
//			        sq.area = sq.getSide()*sq.getSide();
//			        System.out.println(sq.getArea());
//			    }
//			}
//			You are assigned the task of refactoring the Square class to make it better in terms of encapsulation. What changes will you make to this class?
//					Make the side field private and remove the area field.
//					public double getArea(){ return side*side; }
		
		
	}
	
}

class A{}
class B extends A{
	
	void methodA(){}
	public static void methodA1(){
		System.out.println("methodA1");
	}
	
	
}
class C{
	public static void main(String[] args) {
		
		
		
		
		B.methodA1();//Called with fully qualified name 
		//methodA1();//Called without fully qualified name but then class C has to extend class B
		
		A a = new A();
		B b = new B();
		
		a = (B)b;
		a = (A)b;
		b = (B)a;
		//b = (A)a; Never OK
		//b = a; Never OK
		
		
//		while(int x=1; x<100){  While argument can only handle an boolean value
//			x++;
//		}
		
		while(new Boolean(true)){  
			
		}
		
		boolean bo=true;
		while(bo){  
			
		}
	}
}