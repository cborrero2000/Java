
public class PrivateConstructor {
	private PrivateConstructor(){
		System.out.println("calling PrivateConstructor constructor");
	}
	
	void method(){}
}

//class TestPC extends PrivateConstructor{	//Implicit super constructor PrivateConstructor() is not visible for default constructor. Must define an explicit constructor
//	public static void main(String[] args) {
		
//		new PrivateConstructor().method(); //	new PrivateConstructor().method();
//	}

//}

abstract class TestAbs{
	final void method1(){};
	final void method2(){};
}
