
public class Principal {
	
	public static void main(String[] args) {
	Club member1 = new Club("Megan", "Fox");
	Club member2 = new Club("Natalie", "Portman");
	Club member3 = new Club("Lindsy", "Lohan");
	
	System.out.println();
	System.out.println(member1.getFirst());
	System.out.println(member1.getLast());
	System.out.println(member1.getMember());
	
	System.out.println(Club.getMember()); //Class method
	}
	
	
	
}
