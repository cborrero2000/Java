package carlos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class PreTest{
	PreTest(int x){
		
	}
}

class Super{}
class Sub extends Super{}
class Sub2 extends Sub{}

public class Test extends PreTest { 
	public static void main(String[] args) { 
		Byte i = 5; 
		byte k = 5; 
		aMethod(i,k); 
		
		String[] a = new String[]{"30","40","50","10","20"};
		
		List lst=Arrays.asList(a);
		System.out.println("lst: " + lst);
		
		Collections.sort(lst);
		
		System.out.println("lst: " + lst);
		
		//lst.add(a) // lst is fixes after being filled with Arrays.asList(a); It throws and RunTime Exception 
		//System.out.println("lst: " + lst);
		
		
		
		ArrayList<Super> al = new ArrayList<>();
		al.add(new Super());
		al.add(new Sub());
		al.add(new Sub2());
		System.out.println(al);

		ArrayList alg = new ArrayList<>();
		alg.add("Hello");
		alg.add("World");
		alg.add(5);
		for(Object obj : alg){
			System.out.println(obj+" is an instance of "+obj.getClass());
			String str=(String) obj;
		}
		
		
	} 
	

	
	Test(int x){
		super(1);		
	}

	static void aMethod(byte i,Byte k){ 
		System.out.println("Inside 1"); 
	}
	
	static void aMethod(byte i,int k){ 
		System.out.println("Inside 2"); 
	} 

	static void aMethod(Byte i,Byte k){ 
		System.out.println("Inside 3 "); 
	} 
	
	static void aMethod(Byte i,byte k){ 
		System.out.println("Inside 4 "); 
	} 	
	
}