package carlos;
import java.util.*;

class Shape{ Shape() throws Exception{} }

class Rectangle extends Shape{

	/*Rectangle() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}*/
	
	Rectangle() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*public void doNothing(){		
		this();					// This cannot be. It has to be within a constructor
	}*/
}

class Test {

	public static void main(String[] args) {
		//Vector<Shape> v = new Vector<Rectangle>();	// Generics has to provide the same type of Objects only way is that the Reference do no have a Generic and it will be a super class of the Generic type specified in the instance
		//Vector<Rectangle> v = new Vector<Shape>();
	}
}

