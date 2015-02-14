package enthuware;
import java.io.*;

	class Great{
		public void doStuff() throws /*IOException*/ FileNotFoundException{
	    }    
	}

	class Amazing extends Great { 
	  public void doStuff() throws IllegalArgumentException{
	  }    
	}

	public class Enthuware_34 {
	    public static void main(String[] args) throws IOException{
	        Great g = new Amazing();
	        g.doStuff();
	        
	        int i = 1;
	        int j = i++;
	        if( (i==++j) | (i++ == j) ){
	          i+=j;
	        }
	        System.out.println(i);
	        
	    }
	}