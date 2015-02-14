
public class DefaultConstructor {
	
//	DefaultConstructor(int x){ //Implicit super constructor DefaultConstructor() is undefined. Must explicitly invoke another constructor
//		
//	}
	
	DefaultConstructor(){
		System.out.println("DefaultConstructor: man-made Parameterless Constructor");
	
}
	
	DefaultConstructor(int ix){
		System.out.println("DefaultConstructor: Argumentative Constructor");
	
}
	
	public static void main(String[] args) {
	
		int a,b,c=0;
//		int a,b,c;
		
		int[]xx=null;
		Object obj=null;
		String str=null;
		String[] str2=null;
		String[][] str3=null;
		String[][][] str4=null;
		System.out.println(xx);
		System.out.println(obj);
		System.out.println(str);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		int ids=0;
		do
//		while(true)
			if(true)
			ids++;
			else
				ids--;
//		ids--;
//		ids+=1;
		while(false);
		
		new Clasic();
		new Clasic(1);
		
	}
}

class Clasic extends DefaultConstructor{
	Clasic(int i){
		System.out.println("Clasic: Argumentative Constructor");
	}
	
	Clasic(){
		System.out.println("Clasic: Parameterless Constructor");
	}
}
