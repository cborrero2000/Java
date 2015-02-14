
public class Principal {
	public static void main(String[] args) {
		String a = "apples";
		String b = "bucky";
		String c = "BUCKY";
		String d = "BUCKY";

		System.out.println(a.length());
		
		if(a.equals("apples"))
			System.out.println("apples are equal !!!!");
		
		if(b.equals(c))
			System.out.println("buckys are equal !!!!");
		
		if(b.equalsIgnoreCase(c))
			System.out.println("buckys are equal Merengue!!!!");
		
		if(c == d)
			System.out.println("c and d are the same object !!!!");
		else
			System.out.println("c and d are different objects !!!!");
		
	}
}
