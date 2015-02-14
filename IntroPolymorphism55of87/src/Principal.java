
public class Principal {
public static void main(String[] args) {
	
	Food[] foodObj = new Food[2];
	
	foodObj[0] = new PotPie();
	foodObj[1] = new Tuna();
	
	for(Food x: foodObj)
	{
		x.eat();
	}
	
	// Bucky tutoria 56 of 87
	System.out.println("\nBucky tutoria 56 of 87\n");
	
	
	Fatty fat = new Fatty(); 
	//Food fo = new Food(); // Food was declare abstract to make sure we'll only use the subclasses that are more specific because Food is a concept that is too broad too general to use it. I should not say to my daughter, You daughter go and eat food because she won't know waht to eat I have to tell her Go and eat a cheese burguer and a apple pie. Also by making a class abstract not objects can be created from this class. Also, if there is an bastract method in a abstract super class, we'll have to implement it before we can create an object in the subclass.
	Food fotu = new Tuna();
	PotPie po = new PotPie();
	
	//fat.digest(fo);
	fat.digest(fotu);
	fat.digest(po);
		
	}
}
