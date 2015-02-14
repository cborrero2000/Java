package enthuware;

public class Enthuware_37 {

	   public Enthuware_37(){
	      s1 = sM1(" 1");
	   }
	   static String s1 = sM1(" a");
	   String s3 = sM1(" 2");{
	      s1 = sM1(" 3");
	   }
	   static{
	      s1 = sM1(" b");
	   }
	   static String s2 = sM1(" c");
	   String s4 = sM1(" 4");
	    public static void main(String args[]){
	    	System.out.print(" wc_main");
	    	Enthuware_37 it = new Enthuware_37();
	    	System.out.print(" bye_main");
	    }
	    
		   static String s5 = sM1(" x");
	    private static String sM1(String s){
	       System.out.print(s);  return s;
	    }
	}