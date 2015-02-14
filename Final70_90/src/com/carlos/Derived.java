package com.carlos;

class Base{
	int i =1999;
	
	static void method1(){}
	
	static void method1(int x){}
	
	public void aMethod(){
	System.out.println("Base.amethod()");
	}
	
	public void bMethod(){
		System.out.println("Base.amethod()b");
		}
	
	protected void btest(){};
	
	Base(){
		aMethod();
		bMethod();
		
	}
	
	Base(int icos){
		this.ico=icos;
	}
	
	static int ico=0;
}

public class Derived extends Base{

	int i=-1;
	//null
	//Derived de = (Derived)new Base();
	
	protected static void method1(){}
	
	public static void method1(int x){}
	
	
	public static void methodT(int x){}
	public static int methodT(int x, int y){return 33;}
	
	Derived(){
		super();
	}
	
	public static void main(String[] args) {
		Base b=new Derived();
		Base c=new Base();
		System.out.println(b.i);
		b.aMethod();
		c.aMethod();
		
		String s1 = "Java";
		String s2 = "Jaya";
		String s3 = s2.replace('y', 'v');
		
		System.out.println("s1: "+s1);
		System.out.println("s2: "+s2);
		System.out.println("s3: "+s3);
		
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		
		Base bv=new Base(5);
		System.out.println("ico: "+ico);
		bv=null;
		System.out.println(bv.ico);
		
		
		Derived tc = new Derived();
        tc.switchString("B");
        double num=2.01;
        float numf=2.01f;
        //int num=2;
        if (num==numf)
        	System.out.println("true double and float comparison");
        else
        	System.out.println("false double and float comparison");
        
        System.out.println("Carlos Alexander Borrero Marquez approved with 100% the OCAJP7 Certification \r In the name of Jesus");
	}
	
	public void aMethod(){
		System.out.println("Derived.aMethod()");
	}
	
	public void btest(){}; //protected and public are accepted 
	
	public void switchString(String input){
    switch(input){
        case "a" : System.out.println( "apple" );
        case "b" : System.out.println( "bat" );
            break;
        case "B" : System.out.println( "big bat" );                
        default : System.out.println( "none" );
    	}
	}
	
	public int setVar(int a, float b, int c){ return (int)(a + b + c); }
	
	public String setVar(int a){return ""; }
	
	
	
}
