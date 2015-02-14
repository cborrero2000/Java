package enthuware;
import static enthuware.PrintScreen.*;
public class Enthuware_52 {

	public static void main(String[] args) {
		
		new Enthuware_52().parseFloat("0.0");
		
		 int i = 0;
         i =  Integer.parseInt("one");  
         ps(i + "i");
	}
	
	public float parseFloat( String s ){
		   float f = 0.0f;
		   try{
		      f = Float.valueOf( s ).floatValue();
		      return f ;
		   }
		   catch(NumberFormatException nfe){
			   System.out.println("NumberFormatException");
		      f = Float.NaN ;
		      return f;
		   }
		   finally{
		      f = 10.0f;
		      return f;
		   }
		}
	
}

