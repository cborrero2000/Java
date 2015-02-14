
public class SuperString {
	
	double[] measurements;
	
	public double[] getMeasurements(){
		return measurements;
	}
	
	public String toString(){
		return "Super String";
	}
	
	public Object toString(String str) {
		return "Super " + str;
	}
}

class SubString extends SuperString{
	public String toString() {
		return "Sub String";
	}
	
	public String toString(String str){
		return "Sub " + str;
	}
}		//IllegalArgumentException

class Testing{
	public static void main(String[] args) {
		System.out.println(new SubString().toString());
		System.out.println(new SuperString().toString("String"));
		System.out.println(((Object)new SubString()).toString());
		System.out.println(((Object)new SuperString()).toString());
//		System.out.println(((Object)new SubString()).toString("String"));
//		System.out.println(((Object)new SuperString()).toString("String"));
		
		char c='C';
		int i= 67;
		if ((char)i == c)
			System.out.println("c is "+ i);
		else
			System.out.println("c is not "+ i + "but" + (int)c );
		
	}
}
