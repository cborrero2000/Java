package enthuware;

public class Enthuware_12 {
	public static void main(String[] args){
	      int d = 0;
	      try{
	         int i = (1 / d)* (doIt());
	      } catch (Exception e){
	         System.out.println(e);
	      }
	   }
	   public static int doIt() throws Exception{
	      throw new Exception("Forget It");
	   }
}
