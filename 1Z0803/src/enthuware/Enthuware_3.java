package enthuware;

public class Enthuware_3 {
	public static void main(String[] args) {
		
		Object t = new Integer(107);
		int k = ((Integer) t).intValue()/9;
		System.out.println(k);
		System.out.println("After Printing k");
		
		
	}
}

class Base{
	   void method1() throws java.io.IOException, NullPointerException{
	      someMethod("arguments");
	      // some I/O operations
	   }
	   int someMethod(String str){
	      if(str == null) throw new NullPointerException();
	      else return str.length();
	   }
	}
	
	class NewBase extends Base{
	      void method1(){
	           someMethod("args");
	      }
	}
	
	
	