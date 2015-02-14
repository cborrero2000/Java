package enthuware;

public class Enthuware_4 {
	public static void main(String[] args) {
		//	A obj = new A();
		//	B obj = new B();
		//	C obj = new C();
			D obj = new D();
	
			if((obj instanceof A) && ! (obj instanceof C) && ! (obj instanceof D))
				System.out.println("obj is of type B");
			
			int ii=0;
			do{
//				System.out.printf("%d%3 = %d \n",i, i%3);
				System.out.printf("%d mod 3 = %d \n",ii, ii%3);
			}while(ii++<11);
			
			int ArrayList; //Classess are not reserved keywords like class, int,  instanceof, so can be used as name
			
			boolean b = false;
		      int i = 1;
		      do{
		         i++ ;
		      } while (b = !b);
		      System.out.println( i );
	}
	
	public void switchTest(byte x){

		switch(x){
			case 'b':   // 1
			default :   // 2
			case -2:    // 3
			case 80:    // 4
		}
		
		
		try{
			int xy=0;
			int x1=0;
//			while (false) { x1=3; } Unreachable code
			

		}
		catch(Exception e){
		
		}
		finally{}
		
		
	}
}

class A {}
	class B extends A {}
	class C extends B {}
	class D extends C {}