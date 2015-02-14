
public class Static {
	int y = 2;
	private static int z=3;
// Static blocks, functions and variables are executed when the .class files are loaded into the JVM
// so at this point that static blocks have been executed none instance variables have been created
// so if an instance variable is intended to be accessed within a static method, it will throw and error
// because it won't find the instance variable because the object hasn't been created yet. Objects and 
// instance variables are created in the heap by the JIT (Just in time Code producer), which form part
// of a later step in the JVM process. Then in order to access the wanted instance variable the variable
// has to be created within a static context and since what's inside of the static method will be executed
//	when the classes (.class files) are loade, then the instance variable it will create in the heap and the
// execution of the static block that utilizes the instance variable will compile file since th iv exists.
//	an instance or object of this class and then.

	
	
	public static void main(String arg[])
	{
		int x;
		x = 1;
		System.out.println(x);
		Static YA = new Static();
		System.out.println(YA.y);
		getX();
	
	}
	
	
	public static int getX(){ // Note I am accessing a static variable with a non-static method.
		System.out.println(z);
		return z;
	}
	
}
