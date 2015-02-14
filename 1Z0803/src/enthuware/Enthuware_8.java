package enthuware;

public class Enthuware_8 {
	public static void main(String[] args) {
			A obj = new A();
//			B obj = new B();
//			C obj = new C();
//			D obj = new D();
	
			if((obj instanceof A) && ! (obj instanceof C) && ! (obj instanceof D))
				System.out.println("obj is of type " + obj.getClass());
			
			if((obj instanceof B) && ! (obj instanceof C))
				System.out.println("obj is of type " + obj.getClass());
	}
}
