package enthuware;
import static enthuware.PrintScreen.*;

import java.util.ArrayList;
 class Enthuware_44 implements Cloneable {
	
	    @Override  
	    public Object clone() throws CloneNotSupportedException {  
	        return super.clone();  
	    }  
	
	    static void main(String args) {}
	
public static void main(String[] args) {
	String str = null;
	int[][] iArr = null;
	Object obj = null;
	Enthuware_44 e44 = null;
	
	psln("str: " + str);
	psln("iArr: " + iArr);
	psln("obj: " + obj);
	psln("e44: " + e44);
	
//	ps(obj.toString());	//java.lang.NullPointerException
//	ps(obj.getClass());	//java.lang.NullPointerException
	
	StringBuilder sb = new StringBuilder("HelloWorld");
	String str1 = "HelloWorld";
	
			ps(str1.equals(sb.toString()));
			
//			ps(str1==sb);	//Incompatible operand types String and StringBuilder
			
//			Abs abs =  new Abs();	// Abstract classes cannot be instantiated
			
			ArrayList<Integer> list = new ArrayList<>(1);
			list.add(1001);
			list.add(1002);
//			ps(list.get(list.size()));
			

}
}

abstract class Abs{ Abs(){};}

