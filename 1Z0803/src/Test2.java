//- Also non-static variables cannot be used within static method as main, (To access and instance variable you need to declare first the instance otherwise you will get always a compiler error).
//- Instance fields and Static variables get initialized automatically



public class Test2 {
	static float width, height;

	public static void main(String[] args) {
		float fl;
		
		// fl=10.0;// Compilation Error. double cannot be upgraded into a floater
		
		fl=10; // Compiles fine, integer is getting upgrade to a floater
		
		Test2 tst;
		
		//tst.width; // Local variable are not provided default values
		Test2.width=10; // Static Variables are accessed through the class name
		System.out.println("height: " + height);  // Static Variables  and instance variables get initialized automatically
													// That doesn't happen with local variables
		System.out.print(fl);
	}

}