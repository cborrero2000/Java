
public class Club {
	private String first;
	private String last;
	private static int member;
	
	public int x;
	
	Club(String f, String l){
		first = f;
		last = l;
		member++;
		
		System.out.printf("Constructor for %s %s, members %d\n", first, last, member);
	}
	
	public String getFirst(){
		return first;
	}
	
	public String getLast(){
		return last;
	}
	
	public static int getMember(){
		return member;
	}	
	
}
