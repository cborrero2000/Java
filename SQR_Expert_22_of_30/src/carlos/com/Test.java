package carlos.com;

import java.util.*;

class A{
	//A() throws Exception {}
}
public class Test extends A{
	
	Test(){}
	void calc(){};
	
	
	public static void main(String[] args) {
		System.out.println("Inside Main");
		
		Integer i =5;
		String str="Hello";
		str.length();
		
		int x=16;
		float fl=i/0.5f;
		
		Set <Integer> tes= new TreeSet<Integer>();
		
		tes.add(5);
		tes.add(5);
		tes.add(5);
		System.out.println(tes);
		
		Test objTest = new Test();
		objTest.new inner().display();
		new Test().new inner().display();
		
	}
	
	String $s = "Outer_Class";
	class inner{
		
		//String $s = "Inner_Class";
		
		void display(){
			System.out.println("$s: " + Test.this.$s);
			
			StringBuilder sb = new StringBuilder(10);
			sb.length();
			
		}
		Runnable writeOut = new Runnable(){   // Implementing run by using anonymous class technique. At the same time we are creating an object of the anonymous class
			public void run(){
				//writeData(pos); 
			}
		};
		
	}
	

	
	


}
