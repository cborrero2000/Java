
public class Present {
	private String name;
	private ThisString birthday;
	
	Present(String theName, ThisString theDate){
		name = theName;
		birthday = theDate;
	}
	
	public String toString(){
		return String.format("My name is %s and I my birthday is on %s", name, birthday);
	}

}
