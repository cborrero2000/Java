import java.io.FileNotFoundException;
import java.io.IOException;

class StatsTest{
	
	static void ms(){
		SuperClass2.stats++;	
	}
	
}

class SuperClass2 {
	static int stats=100;
	boolean b1=true;
	int success=1;
	
	
	
    public void doIt(String str, Integer... data)/*throws IOException*/ {
    	
    	stats++;
    	
 String signature = "(String, Integer[])";
 System.out.println(str + " " + signature);
 success++; 	// a non-static variable or state is available in a non-static method without creating an instance of it  
 try{
	 throw new IOException();
 }catch (IOException e){
	 
 }
// return 1;
 }
}

public final class CheckedExceptions extends SuperClass2 {
    public void doIt(String str, Integer... data) /*throws FileNotFoundException*/ {
        String signature = "(String, Integer[])";
        System.out.println("Overridden: " + str + " " + signature);
        try{
       	 throw new IOException();
        }catch (IOException e){
       	 
        }
//        return 0;
    }

    public static void main(String... args) {
        SuperClass2 sb = new CheckedExceptions();
//        try {
            sb.doIt("hello", 3);
//        } catch (Exception e) {
//        }//Object
    }
}