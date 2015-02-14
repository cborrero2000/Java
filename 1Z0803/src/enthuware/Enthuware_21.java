package enthuware;



public class Enthuware_21 extends Holder {
	static String str1="Hello";
	static float f;
	
	public void methodee1(){
//		static String str2="Hello";
	}
	
	public static void main(String[] args){
		final Holder a = new Holder(5);
		Holder b = new Holder(10);
		a.link = b;
		b.link = setIt(a, b);
//		System.out.println(a.link.value+" "+b.link.value);
		System.out.println("f: " + f);
label1:		System.out.println("Math.round: " + Math.round(5.49999999));
		System.out.println(5/4);
		System.out.println(5/4.0);
		
		Float f = 1.0f;
//		static String str2="Hello";
		String s="hello";
		System.out.println(s instanceof Object );
		
		Object AASDD = new String("A");
		System.out.println("AASDD: " + AASDD);
		
		boolean ba[] = new boolean[1];
		Boolean Ba[] = new Boolean[1];
		System.out.println(ba[0]);
		System.out.println(Ba[0]);
		
		try{
			System.out.println("try");
			return;
		}catch(Exception e){
			System.out.println("catch");
			return;
		}finally{
			System.out.println("finally");
			return;
		}
//		System.out.println("after finally");
//			return;
	   }
}

enum Colors{RED, YELLOW, BLUE};


class Holder{

		Holder(){}
	   int value = 1;
	   Holder link;
	   public Holder(int val){ this.value = val; }
	   public static Holder setIt(final Holder x, final Holder y){
	       x.link = y.link;
	       return x;
	   }
	}