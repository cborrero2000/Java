class Feline {
public String type = "f ";
public Feline() {
System.out.print("feline ");
}
}
public class Cougar extends Feline {
public Cougar() {
System.out.print("cougar ");
}

public void changeString(String st){
	st = "Yellow";
}
public static void main(String[] args) {
new Cougar().go();
}
void go() {
type = "c ";
type = "d "; // it replaces the whole string with a totally new value
type.concat("hello"); // It doesn't add anything to an existing String value
System.out.println(this.type + super.type);
new Cougar().changeString(super.type); // String passed by value doesn't get modified in any way this, super or type
System.out.println(this.type + super.type);
Cougar c = new Cougar();
c.type="New_Value";
System.out.println(this.type + super.type);

Feline fe = new Feline();
fe.type="New_F_Value";
System.out.println(this.type + super.type);
//type="Value";
System.out.println("Last Print: "+this.type +" "+ super.type +" "+ fe.type +" "+c.type+" "+ new Cougar().type);
new Cougar().type="Ramsky";
System.out.println("\rLast Print: "+this.type/*local class variable type*/ +" "+ super.type/*local class variable type*/
		+" "+ fe.type/*object variable type*/ +" "+c.type/*object variable type*/+" "+ new Cougar().type);
}
}