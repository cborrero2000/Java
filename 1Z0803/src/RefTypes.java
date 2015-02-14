
public class RefTypes {

}

class X2 implements Z{
	public String toString(){ return "I am X2";}
	public static void main(String[] args) {
		Y2 myY=new Y2();
		X2 myX=myY;
		Z myZ=myX;
		System.out.println(myZ);
		System.out.println(myZ.getClass());//getClass()	Returns the runtime class of this Object.
		System.out.println(myZ.getClass().getName());
		System.out.println(myZ instanceof X2);
		System.out.println(myZ instanceof Y2);
		System.out.println(myZ instanceof Z);
		System.out.println(Z.class/*.getName()*/);
		System.out.println(Z.class.getName());
		System.out.println(X2.class/*.getName()*/);
		System.out.println(Y2.class.getName());
		System.out.println(Y2.class.getName());
		
		int xyz=0;
		for(;xyz <10; xyz++){
			
		}
		
	}
}

class Y2 extends X2{
	public String toString() {return "I am Y2";}
}

interface Z{}
