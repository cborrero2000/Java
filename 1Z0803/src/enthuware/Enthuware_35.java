//What will the following program print when run?
package enthuware;

public class Enthuware_35 {
	   public static void main(String args[] ){
		   AZ b = new BZ("good bye","MERENGUE");  
		   
//		   for (int i=5; i=0; i--) { }

//		   int j=5;
//		         for(int i=0, j+=5;  i<j ; i++) {  j--;  }

//		   int i, j;
//		       for (j=10; i<j; j--) { i += 2; }

//		   int i=10;
//		       for ( ; i>0 ; i--) { }

		   for (int i=0, j=10; i<j; i++, --j) {;}
		   
		   
	   }
}

class AZ{
   AZ() { this("hello", " world");  }
   AZ(String s) { System.out.println(s); }
   AZ(String s1, String s2){ this(s1 + s2); }
}

class BZ extends AZ{
   BZ(){ super("good bye"); };
   BZ(String s){ super(s, " world"); }
   BZ(String s1, String s2){ this(s1 + s2 + " ! "); }
}