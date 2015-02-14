import java.io.FileNotFoundException;
import java.io.IOException;


public class TestB {
	
	protected int ix=4;
	
void m1(int x){System.out.println("int Overloaded");};
void m1(Integer x){System.out.println("Integer Overloaded");};

public static void main(String[] args) {
	int x1=2;
	Integer x2=3;
	
	TestB tb = new TestB();
	
	tb.m1(x1);
	tb.m1(x2);
	
	
	String str = "Hello";
	StringBuilder stb = new StringBuilder("Hello");
	
	if(str.equals(stb))
		System.out.println("They are equa xl");
	else
		System.out.println("They are NOT equal x");
	
	if(str.contentEquals(stb))
		System.out.println("They are equal y");
	else
		System.out.println("They are NOT equal y");
}
}

class TesTC{
	void m2(){
		new TestB().ix++;
	}
	
	interface Intern{
		void me1(); // Only none(default), public and abstract. 
		
	}
}


abstract class ABSX{
	 void mabs() throws IOException{
	 }
}

class SubABS extends ABSX{
	void mabs()throws /*Exception*/FileNotFoundException{
	}
}

class Cool{
	static int ex;
	void mc() throws IOException{}
	Cool mc1() { return new Cool();}
}

class SubCool extends Cool{
	int ex;
	void mc() throws /*Exception*/FileNotFoundException{}
	SubCool mc1() {return new SubCool();}
}