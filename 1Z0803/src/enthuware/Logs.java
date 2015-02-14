package enthuware;

import java.util.List;

public class Logs {
    /////////////////////////////////////////// Custom Print methods////////////////////////////////////////////////
    public static void ps(Object obj){ // print screen function. Just as System.out.print
           System.out.println(obj);
    }
    public static void psf(Object obj1, Object obj2){ // print screen function. Just as System.out.printf
           System.out.printf(obj1.toString(),obj2);
    }
    public static void psn(Object obj){ // print screen NO new line function. Just as System.out.println
           System.out.print(obj);
    }
    public static void ps(Object obj1, Object obj2){ // print screen new line function.Just as System.out.printf plus "\n" ln
           System.out.printf(obj1.toString()+"\n",obj2);
    }
    
    public static void psHEX(List<Byte> list){ // print screen function. Just as System.out.print
  	  for(int i=0; i < list.size(); i++){
  		  System.out.printf("%X ", list.get(i));
  	  }
  	  ps("");
 }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
