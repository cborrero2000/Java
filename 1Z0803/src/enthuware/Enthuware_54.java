package enthuware;
import static enthuware.PrintScreen.*;
public class Enthuware_54 {
	public static void main (String[] args) {
		   int var = 10;
		   VarScope scope = new VarScope();
		   scope.var = var + 2;
		   scope.adjustVar(var + 2);
		   System.out.println("var = " + var);
		   
		   int[ ] i[ ] = new int[][]{ { 1, 2 }, { 1 }, { }, { 1, 2, 3 } } ;  
		   Object[ ] i2[ ] = { { 1, 2 }, { 1 }, { }, { 1, 2, 3 } } ;
//		   Object[][] obj = new Object[][]{"", 12, 3, {},7}; // Type mismatch: cannot convert from String to Object[] Type mismatch: cannot convert from int to Object[]
//		   int i3[ ] = new int[]{  1, 2 ,  1 , { },  1, 2, 3  } ;  //Type mismatch: cannot convert from Object[] to int
		   
		   scope=null;
		   ps("scope: "+scope instanceof Object);
		}
}

class VarScope {
int var;

public void adjustVar(int var) {
   var += 2;
}
}

class SmartPhone {
    float screenResolution, width, height;


    static void main (String[] args) {
         SmartPhone phone=null;
         phone.height = 112.2f;
         phone.width = 56.8f;
         System.out.format("%.0f dpi - %.1f X %.1f",
         phone.screenResolution, phone.height, phone.width);
         
      
    }
}