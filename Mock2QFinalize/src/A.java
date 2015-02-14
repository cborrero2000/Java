import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A extends java.lang.Object{

	int i;
	
	A(int i){
		this.i=i;
	}
	
	protected void finalize(){
		System.out.println(i);
	}
	
	private void method1(){};
	public void method1(int x){};
	
	public static void main(String[] args)throws Throwable {
		A a1= new A(5);
		a1.finalize();
		System.out.println("Yello");
		A a2=new A(10);
		a1=null; 
		a2=null;
		System.out.println("Blue");
		System.gc();
		
		System.out.println("Octal Number: "+0100);
		int age=28;
		
		assert (age<30):"Getting old dude " + age + "years is alot";
		
		String strx="Hello Amigo!!!";
		System.out.println(strx);
		System.out.println("What: "+strx.startsWith("Hello"));
		System.out.println("What: "+strx.endsWith("!"));
		System.out.println(strx.charAt(6));
		int x,y;
		x=1;
		y=2;
		
		System.out.println(x+y +"abc"+x+y);
		
		ArrayList depNoList = new ArrayList<Integer>();
		
		byte b1=~5+1;
		System.out.println(b1);
		
		int a=12;
		float b=123f;
		int c = (int)(a+b);
		
		class LocalClass{
			int i=8;
		}
		
		String[] z = {"a","b","c","d"};//1
        List<String> aList =  Arrays.asList(z);//2
        aList.add("e");//3
		
	}
	
}
