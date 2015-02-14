import java.util.ArrayList;

public class VarAssign {


	public static void main(String[] args) {
		int x=0; int y=0;
		
		System.out.println("x: " + x++);
		System.out.println("y: " + --y);
		
		char [] chA = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'};
		
		System.arraycopy(chA, 2, chA, 1, 5);
		for(char c: chA)
			System.out.println(" " + c);
		
		int[][] ai = {{1,2,3,3,4},{1,2},{8}};
		System.out.println(ai.getClass().isArray());
		System.out.println(ai[2].getClass().isArray());
		
		ArrayList<String> al = new ArrayList<>();
		al.add("Johan");
		al.add("Alex");
		al.add("Leen");
		
		System.out.println(al.get(2).length());
		System.out.println(al.size());
	}
}
