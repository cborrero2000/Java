package enthuware;

public class Enthuware_22 {
	public static void main(String[] args){
	       switch(Integer.parseInt(args[1]))  //1
	       {
	          case 0 :
	             boolean b = false;
	             System.out.println("b_0: "+b);
	             break;
	     
	          case 1 :
	             b = true; //2
	             System.out.println("b_1: "+b);
	             break;
	       }
	       
//	       if(b) System.out.println(args[2]);
	   }
}
