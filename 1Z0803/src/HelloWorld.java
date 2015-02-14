//
//
//public class HelloWorld {
//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        System.out.println("Considering a circle with a diameter of 5 cm, it has:");
//        System.out.println("A circumference of " + (Math.PI * 5) + " cm");
//        System.out.println("And an area of " + (Math.PI * Math.pow(2.5,2)) + " sq. cm");
//    }
//}

//Can instead be written as:


import static java.lang.Math.*;
import static java.lang.System.out;
public class HelloWorld {
	
	void method1(){}
	
    public static void main(String[] args) {
        out.println("Hello World!");
        out.println("Considering a circle with a diameter of 5 cm, it has:");
        out.println("A circumference of " + (PI * 5) + " cm");
        out.println("And an area of " + (PI * pow(2.5,2)) + " sq. cm");
    
        byte by = 0b0000_0001;
        
        System.out.println("by: " + by);
        System.out.println(by << 30);
        System.out.println(by << 31);
        System.out.println(by >>> 3);
        System.out.println("by: " + by);
        
        for(int i=4; i<5 ; i++){
        	System.out.println("i: " + i);
        }
 
        System.out.println("args[0]: " + args[0]);
        by = (byte) 0b1111_1111;		// A '1' at the beginning is minus sign of the whole byte
        System.out.println("by1: " + by);
        System.out.println("(byte) 0b1111_1111: " + (byte) 0b1111_1111);
        System.out.println("0b1111_1111: " + 0b1111_1111);
        
        by = (byte) 0b1000_0000;
        System.out.println("by2: " + by); // A '1' at the beginning is minus sign of the whole byte
        
        by = (byte) 0b0111_1111;
        System.out.println("by3: " + by); // A '0' at the beginning makes it all positive;
        
        new HelloWorld().method1(); 
        
        int [][] ia = new int[4][];
        int [][] ia2= new int[6][];
        System.out.println(ia.length);
//        System.out.println(ia[0].length); //java.lang.NullPointerException
        ia[0]=ia2[3];
        
        char [][] ca2D = {{'c','u','p'},{'j','a','v','a'}};
        
        System.out.println(ca2D); //[[C@4693c5e7
        System.out.println(ca2D[0]); //cup
        
//        while(3>0 ? false:true){
//        	
//        }
        
        int x=0;
        
        while (x<10 ? true:false){
        	x++;
        	System.out.println("x: "+x);
        	
        }
        
        short shorty=3;
        
        switch(shorty){
        
        	case 3:
        	System.out.println("You are Java Certified in Jesus Name");
        	
        	default:
        		System.out.println("You have a New Amazing, Spiritually and Financially Blessed Job in Jesus Name");
        }
        
        String str1=null;
        String str2="Yes";
        
        if (str1==str2){
        	System.out.println("They are equal in value");
        }
        
//        if (str1.equals(str2)){
//        	System.out.println("They are equal in value");
//        }else{
//        	System.out.println("They are NO equal in value");
//        }
        
    }
}