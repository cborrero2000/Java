package enthuware;

public class Enthuware_38 {
public static void main(String[] args) {
	Object ob = null;
	
//	System.out.println(null);	This won't compile
	
	System.out.println("" + ob + true + "");	//This will compile uses toString method, that's why
	
	double d = 10D;
	
	System.out.println("d: " + d);
	
}
}
