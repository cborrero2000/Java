
public class Principal {
	public static void main(String[] args) {
	
	PotPie objPotPie = new PotPie();
	Tuna objTuna = new Tuna();
	
	objPotPie.eat();
	objTuna.eat();
	//objPotPie.cook(); Since cook is a private method it doesn't get inherited
	//objTuna.cook();   by PotPie and Tuna. Only public methods are inherited.
	}
}

//A subclass inherits all of the public and protected members of its parent, no matter what package the subclass is in. If the subclass is in the same package as its parent, it also inherits the package-private members of the parent. You can use the inherited members as is, replace them, hide them, or supplement them with new members:
//A subclass does not inherit the private members of its parent class. However, if the superclass has public or protected methods for accessing its private fields, these can also be used by the subclass.
//A nested class has access to all the private members of its enclosing class—both fields and methods. Therefore, a public or protected nested class inherited by a subclass has indirect access to all of the private members of the superclass.
//A nested class is a member of its enclosing class. Non-static nested classes (inner classes) have access to other members of the enclosing class, even if they are declared private. Static nested classes do not have access to other members of the enclosing class. As a member of the OuterClass, a nested class can be declared private, public, protected, or package private. (Recall that outer classes can only be declared public or package private.)