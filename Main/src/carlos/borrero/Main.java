package carlos.borrero;

class A{
	
	A(){
		System.out.print("Super ");
	}
	
	A(int i){
		System.out.println("x: " + 1);
	}
}

public class Main extends A {
	
	public static void main(String [] args) {
		new Main(1L).Main();
	}

	public Main(int x) {
		this();
		System.out.print(" " + (x * 2));
	}

	public Main(long x) {
		this((int) x);
		System.out.print(" " + x);
	}
	
	public Main() {
		//super(5);
		super(); 
		System.out.print("No arg Sub");
	}
	
	void Main() {
		System.out.print(" method "); 
	}
}