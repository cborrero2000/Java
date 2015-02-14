
public class Apples {
	
	public static void main (String arguments[])
	{
		Animal[] list = new Animal[2];
		Dog d = new Dog();
		Cow c = new Cow();
		
		list[0]=d;
		list[1]=c;
		
		System.out.println("Hello!");
		
		for(Animal x : list)
		{
			x.showAction();
		}

		Animal a = new Animal();
		//d = (Dog) a; // Compiler gives an error here
		a = d;
		
	}

}


