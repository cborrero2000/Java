package enthuware;
import static enthuware.PrintScreen.*;
public class Enthuware_48 {
	
	   public static void main(String[] args){
		      System.out.println("calculating");
//		      Calculator x = null;
//		      x.calculate();
		   
//		   new Calculator();
		      
		      int i = Integer.MIN_VALUE;
		      
		      psln(Integer.toBinaryString(i));
		      psln(Integer.toBinaryString(-i));
		      
		   }
}

abstract class Calculator{
	   abstract void calculate();

	}