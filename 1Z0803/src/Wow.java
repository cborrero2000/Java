public abstract class Wow {
private int wow;
public Wow (int wow) {
this.wow = wow;
}
public void wow () {}
private void wowza () {}

public static void main(String[] args) {
	System.out.println("Abstract classes can have constructors and main methods");
}
}


final class SuperFinal{
	SuperFinal(){
		
	}
	
	public static void main(String[] args) {
		System.out.println("Final classes can have constructors and main methods");
	}
}

interface Zint{}

abstract class Abs implements Zint{ // An abstract class can implement an Interface
	
}

abstract class Abs2 extends Abs{	// An abstract class can extend another Abstract class
	
}

abstract class Abs3 extends Wow{
	Abs3(){
		super(5);
	}
}