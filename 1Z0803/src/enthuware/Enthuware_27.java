package enthuware;

public class Enthuware_27 {

}

interface Bozo{ int type = 0; public void jump(); }

class Type1Bozo implements Bozo{
	//public Type1Bozo(){type = 1;} //	The final field Bozo.type cannot be assigned
		public void jump(){ System.out.println("jumping..."+type);
	}
		
	public static void main(String[] args){ Bozo b = new Type1Bozo(); b.jump();}}