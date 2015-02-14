package enthuware;

public class Enthuware_15 {
	
	static int iS=1;
	int iNS=1;
	
	static void mSInt(){};
	void mNSInt(){};
	
	public static void main(String[] args) {
        Flyer f = new Eagle();
        Eagle e = new Eagle();
        Bat b = new Bat();
        
        if(f instanceof Flyer) System.out.println("f is a Flyer");
        if(e instanceof Bird) System.out.println("e is a Bird");
//        if(b instanceof Bird) System.out.println("f is a Bird"); Incompatible conditional operand types Bat and Bird
        
        System.out.println((int)(5/1.05));
//        method1();
    }
	
//	static void method1(){method1();};
}

interface Flyer{ }
class Bird implements Flyer { }
class Eagle extends Bird { }
class Bat { }