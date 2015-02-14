package enthuware;

import java.io.IOException;

public final class Enthuware_24 {
	public static void main(String[] args) {
		
		C0 c;
		try {
			c = new C0();
			
			((B0)c).perform_work();
			System.out.println('a' + 1 );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	final class inner{
		
	}

}

class A0 {  A0()throws IOException{} public void perform_work(){System.out.println("A0");}  }
class B0 extends A0 {  B0()throws Exception{} public void perform_work(){System.out.println("B0");}  }
class C0 extends B0 { C0()throws Exception{} public void perform_work(){System.out.println("C0");}  }