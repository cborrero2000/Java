
public enum Constants {
	carlosa("papi", "1975"),
	karla("mamita", "1974"),
	vanessa("vanesonga", "2008"),
	rosa("mama", "1942"),
	carlos("papa", "1947"),
	coky("con", "2005");

	private  String desc;
	private  String year;
	
	Constants(String name, String birthYear){
		desc = name;
		year = birthYear;
	}
	
	public String getDesc(){
		return String.format("%s", desc);
	}
		
	public String getYear(){
		return String.format("%s", year);
	}
}