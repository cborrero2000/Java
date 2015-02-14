
abstract public class Food {

	public void eat(){
		System.out.println("This food is great");
	}
	
	abstract void lit(); // where there's and abstract method, its class has to be abstract or it won't compile.
}
