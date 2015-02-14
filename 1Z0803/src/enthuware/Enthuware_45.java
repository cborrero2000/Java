package enthuware;
import static enthuware.PrintScreen.*;

public class Enthuware_45 {
	public static void main(String[] args) {
		new Cougar().go();
//		String strN="123_456";
		
//		int i = Integer.parseInt(strN);	// java.lang.NumberFormatException
//		System.out.println("i: " + i);
		char c = 48;
		int i2 = 5;
		
		System.out.println(""+c);
		
		int[] arr = {1,2,3,4};
		
		 for(int i: arr){
			 ps("  i: " + i);
			 i++;
		 }
		psln("");
		 for(int i: arr){
			 ps("  i: " + i);
			 i++;
			 ps("  iX: " + i);
		 }
		 
		String[] strA = {"one","two","three"};
		String str1="Hi";
		for(String str : strA){
			str1+=str;
			psln(str1);
		}
		
		System.out.println(/*2+4 <= 1+5 ==*/	(2+4) <= (1+5));
		
		
		}
}

class Feline {
public String type = "f "; // It's not hidden so you will always modify it in the super class even if you are modifying it from the subclass
public Feline() {
System.out.print("feline ");
}
}
class Cougar extends Feline {

public Cougar() {
System.out.print("cougar ");
}

void go() {
System.out.print(this.type + super.type);
type = "c ";
System.out.print(this.type + super.type);
}
}