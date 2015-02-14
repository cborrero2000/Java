
public class StaticEx {
StaticEx(){
	System.out.println("Constructor StaticEx");
}
}

class Outer extends StaticEx{
	Outer(){
		System.out.println("Constructor Outer");
	}
	public static class StaticInner{
		StaticInner(){
		System.out.println("Constructor StaticInner");
		}
	}
	
	public static void main(String[] args) {
		StaticInner si = new StaticInner();
		
		si.toString();
		System.out.println(true);
		
		Outer out = new Outer();
	}
	
//	public static void main(String[] args) {
//		StaticInner si = new StaticInner();
//		
//		si.toString();
//		System.out.print(true);
//	}
}
