
//public class Sequence {
		/*Sequence(){
			System.out.print("c "); }
		
		{ System.out.print("y "); } 
		
		public static void main(String[] args) { 
			new Sequence().go(); 
		} 
		
		void go() { System.out.print("g "); } 
		
		
		
		static { System.out.print("x "); }*/
		
		
		
		/*public class Sequence {
			Sequence(String n) { name = n; } 
			String name;
			public static void main(String[] args) {
				Sequence m1 = new Sequence("guitar");
				Sequence m2 = new Sequence("tv"); 
			System.out.println(m2.equals(m1));
			}
			public boolean equals(Object o) {
				Sequence m = (Sequence) o;
			if(m.name != null)
			return true;
			return false;
			}
			}*/
		//Vector
interface Rideable {
String getGait();
}
public class Sequence implements Rideable {
int weight = 2;
public static void main(String[] args) {
new Sequence().go(8);
}
void go(int speed) {
++speed;
weight++;
int walkrate = speed * weight;
System.out.print(walkrate + getGait());
}
public String getGait() {
return " mph, lope";
}
}
		


/*class Alpha {
String getType() { return "alpha"; }
}
class Beta extends Alpha {
String getType() { return "beta"; }
}
class Gamma extends Beta {
String getType() { return "gamma"; }
public static void main(String[] args) {
Gamma g1 = (Gamma) new Alpha();
Gamma g2 = (Gamma) new Beta();
System.out.println(g1.getType() + " " + g2.getType());
}
}*/


/*class Feline {
public String type = "f ";
public Feline() {
System.out.print("feline ");
}
}
public class Sequence extends Feline {
public Sequence() {
System.out.print("cougar ");
}
public static void main(String[] args) {
new Sequence().go();
}
void go() {
type = "c ";
System.out.print(this.type + super.type);
}
}*/



//}
