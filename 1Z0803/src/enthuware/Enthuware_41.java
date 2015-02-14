package enthuware;

public class Enthuware_41 {
     public String toString() {
    	 if(false)
    	 throw new IllegalArgumentException();
          return "Super String";
}
     public Object toString(String str) {
          return "Super " + str;
     }
     
 	public static void main(String[] args) {
		System.out.println(((Object) new Enthuware_41()).toString());
}

class SubString extends Enthuware_41 {
     public String toString() {
          return "Sub String";
     }
     public String toString(String str) {
          return "Sub " + str;
     }
}
}