package enthuware;

public class Enthuware_6 {
	public static void main(String args[]) throws Exception{
	       try{
	          m1();
	          System.out.println("A");
	       }
	       finally{
	          System.out.println("B");
	       }
	       System.out.println("C");
	       
	       Enthuware_6 e6 = new Enthuware_6();
	       e6 = new Enthuware_6();
	   }
	   public static void m1() throws Exception { /*throw new Exception();*/ }
}
