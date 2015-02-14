package enthuware;
import static enthuware.PrintScreen.*;

public class Enthuware_30 {
	
	//static{throw new Exception();}Initializer does not complete normally
	//{throw new Exception();}Initializer does not complete normally
	
public static void main(String[] args) throws ClassNotFoundException {
	Ccx objC = new Ccx();
	
	objC.m1();
	
	psln(Class.forName("java.lang.String"));
	psln(Class.forName("java.lang.System"));
	psln(Class.forName("java.util.ArrayList"));
}
}


class Aax{
	char c='a';
   public void m1() { psln("m1...Aax");  }
}
class Bbx extends Aax{
	char c='b';
   public void m1() { psln("m1...Bbx");  }
}
class Ccx extends Bbx{
	char c='c';
   public void m1(){
	   psln("m1...Ccx");
	   //((Bbx) this ).m1();  // Recursive call... Stack Overflow 
	   psln(((Bbx)this).c);
   }
}