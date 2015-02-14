package enthuware;
import static enthuware.PrintScreen.*;



interface I
{
	void m1();
}

abstract class Klass
{
	void m1() {};
}

class SubClass extends Klass implements I
{
	public void m1(){}
	public static void m2(Integer...Int){
		ps("Printing..." +  Int[0]);
	}
}

public class Enthuware_49 {
	static int value = 0; //1
public static void main(String[] args) {
	Integer[] I = {10,9,8,7,6,5};
	new SubClass().m2(I);
	
//	Object obj = new Object[]{"",56,444,new Object[]{1,2,3}, new Object[]{}, {}}; //No Valid = {}
	
	 int r2ndArgument = Integer.parseInt(args[2]); //3
     if( true == 2 > 10 ) //4  
     {
        value = -10;
     }
     else{
        value =  r2ndArgument;
     }
     for( ; value>0; value--) System.out.println("A"); //5
	
}
}
