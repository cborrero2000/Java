package enthuware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Enthuware_5 {
	static int aa;
	public static void main(String[] args) {
	int cc, bb;
	
	cc = bb = aa;
	
	
	System.out.println("loop_1");
	
	
	int jj=0;	
outer:
	for(jj=0;;jj++){
		System.out.println("jj: " + jj);
		if(jj==5)
			break outer;
	}
	
	System.out.println("after_loop_1");
	
	int counter = 0;
//    outer:
outer:    for (int i = 0; i < 3; i++) {
//        middle:
        middle:       for (int j = 0; j < 3; j++) {
            inner:
            for (int k = 0; k < 3; k++) {
                if (k - j > 0) {
                    break middle;
                }
                counter++;
            }
        }
    }
    System.out.println(counter);
    
    String str1 = "Hello";
    System.out.println(str1);
    str1 = "World";
    System.out.println(str1);
    System.out.println(str1);
    
    //ArrayList <int> al = new ArrayList<>(); No primitives can be stored in ArrayList
    
    Collection c = new ArrayList();
    Collections d;
    
	
}
}
