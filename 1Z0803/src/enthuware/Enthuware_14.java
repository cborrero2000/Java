package enthuware;

public class Enthuware_14 {
	
	static void method(int[] ix){};
	
	public static void main(String[] args) {
		Integer i = new Integer(42); 
		int ii = 42;
		Long ln = new Long(42); 
		Double d = new Double(42.0);
		
		System.out.println(i.equals(d));
		System.out.println(d.equals(ln));
		System.out.println(ln.equals(42));
		System.out.println(i.equals(42));
		
//		System.out.println(ln.equals(i==ln));
//		System.out.println(ln.equals(ln==d));
		
//		method(1,2);
	}

}
