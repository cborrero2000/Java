package enthuware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static enthuware.PrintScreen.*;

public class Enthuware_42 {
	static int ix;
	enum Color{Blue, yellow, red, white, purple}
	
	Enthuware_42(int x){}
	
    public static void trySomething () throws FileNotFoundException {
         //implementation omitted
    }
    public static void main (String[] args) throws Exception {
    		try {
        	trySomething();
//        	int x, y =0;
//        	ps(x + "" + y);	// The local variable x may not have been initialized
        	
        	StringBuilder sb = new StringBuilder("Hello");
        	ps("sb.capacity(): " + sb.capacity());
        	
        	ArrayList al = new ArrayList();
        	
        	Enthuware_42 e_obj = new Enthuware_42(1);
        	
        	e_obj.trySomething();
        	e_obj.ix=5;

        	ps("Color.Blue: " + Color.Blue);
        	
        	ps("Color.Blue.equals(Blue): " + Color.Blue.toString().contains("Blue"));
        	ps("Color.Blue.equals(Blue): " + Color.Blue.toString().equals("Blue"));
        	
    	} catch (FileNotFoundException ex) {
    		//throw new Exception();
    	}
    }
    }

class TestConstructor extends Enthuware_42{
	TestConstructor(){
	super(2);
	}
	
}
