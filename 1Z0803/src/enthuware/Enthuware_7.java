package enthuware;

public class Enthuware_7 {
	public static void main(String[] args) {
	AA[] a,a1;
	BB[] b;
	a = new AA[10]; 	a1=a;
	b = new BB[20];
	a = b; // 1
	b = (BB[]) a;  // 2
	//b = (BB[]) a1; // 3
	
	
	Object t = new Integer(107); 
	int k = ((Integer) t).intValue()/9; 
	System.out.println(k);
	
	
}}

class AA { }
class BB extends AA{}