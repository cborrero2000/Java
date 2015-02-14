package com.carlos2;

import java.util.*;
import java.io.*;


import com.carlos.TestClass;

import java.io.IOException;
import java.lang.String;

public class TestClass2 extends TestClass {

	//TestClass2(){}
	
	void doNothing2(){
	
		TestClass2 tc2 = new TestClass2();
		
		TestClass tc = new TestClass2();
		
		TestClass tc3 = new TestClass();
		
		//TestClass2 tc4 = new TestClass();
		
		tc2.var1=0;			// To access a protected member (variable or method) of a Super Class
		tc2.doNothing();	// It has to be invoked with the Reference Value of Subclass type
		//tc.var1=0;		// It will throw a compiler error for the Reference Value of Superclass  
		//tc.doNothing();	// type
		//tc3.var1=0;			// And it doesn't matter is the instance is from the SuperClass if you are using
		//tc3.doNothing();	// a Reference variable of the Super Class as well
		//tc4.var1();		// This will fail too because the everything (Reference Variable and  
		//tc4.doNothing();	// instance) when referring to a protected variable or method of a SuperClass
							// have to come from the Subclass (Ref. variable and Instance).
		
		
	}
	
	static void intting(boolean...arg){
		
	}
	
	String className="RefObject";
	
	TestClass2(){
		className="RefObject_250";
	}
	
	// void method1(Object ob){  Still won't null the Passed Object regardless if the method is static or not
	static void method1(Object ob){
		ob=null;
	}
	
	
	static int j=10;
	static int i=j;
	public int rick=45;
	public double getBalance(double amt){
		int tt=4;
		tt++;
		tt =rick;
		return amt*3;
	}
	
	public static void main(String...args) throws Throwable{
		
		Vector<String> set= new Vector<String>();
		//String[] arr=set.toArray();
		Object[] arr1=set.toArray();
		
		System.out.println((args[0].split(args[1])).length);
		
		String str= "Hello Merequetengue";
		System.out.println(str.getClass()); // class java.lang.String
		
		str=null;
		Object obj = new Object();
		System.out.println(obj.getClass()); // Output: class java.lang.Object
		System.out.println(str instanceof Object);
		System.out.println(obj instanceof Object);
		System.out.println(str instanceof String);
		System.out.println(obj instanceof String);
		System.out.println("What !!!");
		
		Integer studentRollNo = 145;
		
		Object num1 = new String("145");
		
		System.out.println("num1: "+num1.toString());
		
		Object num2 = studentRollNo;
		System.out.println("num2: "+num2.toString());
		System.out.println("studentRollNo: "+studentRollNo);
		studentRollNo = null;
		System.out.println("num2: "+num2.toString());
		System.out.println("studentRollNo: "+studentRollNo);
		
		Object num3 = 146;
		Object num4 = num3.toString();
		
		
		//Object num4 = num2.toString();
		System.out.println("num4: "+num4.toString());
		//num4 = 146;
		System.out.println("num4: "+num4.toString());
		System.out.println(num1.equals(num4));
		
		System.out.println("Class of num1: "+num1.getClass());
		System.out.println("Class of num2: "+num2.getClass());
		System.out.println("Class of num4: "+num4.getClass());
		System.out.println(num1.equals(num4));
		
		char[] cchar = new char[5];
		
		System.out.println(/*cchar[0]+*/cchar[4] + "here");
		String[] strTest={"123","hello","world"};
		char c = 1;
		byte b = 2;
		Integer Int = new Integer(0);
		System.out.println(strTest[new Integer(2)]);
		int count =  args.length-1;
		for(String el:args)
		{
			System.out.println("count: "+count);
			//args[count]="AA"; good bye friend! x7+y8-z9=4P [4789][+-=]
			count--;
			System.out.println("count: "+count);
		}
		System.out.println(args[0]);
		
		System.out.println("Module: "+-50%25.0);
		
		double dd=0d;
		System.out.println("dd: "+dd);
		
		String strr="Hello";
		System.out.println(strr.length());
		
		StringBuilder stringBuilder = new StringBuilder();
		Formatter formatter = new Formatter(stringBuilder);
		System.out.println(stringBuilder.length()); 	// 0
		System.out.println(stringBuilder.capacity()); 	// 16
		String format="%.2f";
		formatter.format("Pi is approximately " + format + " and e is about " + format, Math.PI, Math.E);
		System.out.println(stringBuilder);
		
		boolean brt;
		if(brt=true){
			System.out.println(brt);
		}
		
		int[][] myArray = new int[10][10];
		if(myArray[0][0]<10)
			System.out.println("Truly True");
		
		
		int[] myArray2 = new int[10];
		
		System.out.println(myArray.length);
		System.out.println(myArray2.length);
		myArray[1][2]=5;

		System.out.println(myArray[1][3]);
		//System.out.println(myArray2[0].length);
		
		String num = "Hello";
		//num=null;
		
		System.out.println("num: " + num);
		
		if (num instanceof Object)
			System.out.println("Yes it is,... Why Not");
		else
			System.out.println("Not it's not, Are you crazy or Out of your mind ????");
		
		//////////////////////////////////
		try{
			
		}
		//catch(Exception e){ // try block will need either one of them catch or finally, Also both will be ok
		finally{ // Only One finally clause is accepted unless it is embedded in the catch clause
			
		}
		//////////////////////////////////
		
		
		System.out.println((args[0].split(args[1])).length); // 0
		System.out.println("Halleluya Yeshua");
		
		boolean a = true;
		boolean b1 = false;
		boolean c1 = true;
		
		if(a && (b1=c1)){
			System.out.println("It's True");
		}
		else{
			System.out.println("It's false");
		}
		
		Object a2 = new String("Java Tiger");
		Object b2 = 99;
		Object c2 = new Vector<String>();
		Object o2 = a2;
		a2=c2;
		c2=b2;
		b2=a2;
		
		String A1="Hello";
		String A2="Hello";
		String B1="World";
		String A3= new String("Hello");
		
		System.out.println(A1.equals(A2));
		System.out.println(A1.equals(B1));
		System.out.println(A2.equals(B1));
		System.out.println(A1==A3);
		System.out.println(A1.equals(A3));
		
		//Integer[] I1= new Integer[4];
		
		//intting(4);
		
		Boolean[] i1= new Boolean[2];
		
		//I1=i1;
		
		//System.out.println("I1: "+I1 +" i1: "+i1);
		
		//intting(i1);
		
		List list = new ArrayList<Integer>();
		list.add(new String("123"));
		System.out.println(list);
		
		try{
			String s = "Hello World! 3 + 3.0 = 6 ";

		      // create a new scanner with the specified String Object
		      Scanner scanner = new Scanner(s);
		   // print the line
		      System.out.println("" + scanner.nextLine());

		      // check if there is an IO exception
		      System.out.println("" + scanner.ioException());
		      
		      throw new IOException("");
		      //throw new RuntimeException();
		}
		//catch(RuntimeException ne1){ //You can throw and catch a RuntimeException
		catch(IOException ne1){
			try{
				String arg2= args[1];
			}
			catch(Exception ne){
				throw ne;
				
				//System.out.println("Inside Exception");
			}
			finally{
				System.out.println("Finally block 1");
			}
		}
		finally{
			System.out.println("Finally block 2");
		}
		
		A b5 = new B();
		
		System.out.println(b5.method);
		System.out.println(b5.attribute());
		
		String msg="This is Super Good";
		String[] arr=msg.split(" ");
		System.out.println(arr[0]);
		System.out.println(Arrays.toString(arr));
		String arr2="Yeshua is the Lion of Judah";
		byte by=7;
		char cr=10;
		boolean bn=true;
		System.out.println(arr2.substring(by));
		System.out.println(arr2.substring(cr));
		System.out.println(arr2.substring(0,6));
		
		A aa = new A();
		B bb = new B();
		aa.wrapperTest();
		bb.wrapperTest();
		
		
		System.out.println("A:" + aa.get() + "  B:" + bb.get());
		B bbb = new B(); bbb.aMethod();
		
		String s1 = "Java";
		String s2 = "java";
		
		if(s1.equalsIgnoreCase(s2))
			System.out.println("EQUAL");
		else
			System.out.println("NO EQUAL");
		
		if(s1==(s2))
			System.out.println("EQUAL");
		else
			System.out.println("NO EQUAL");
		
		if(s1.equals(s2))
			System.out.println("EQUAL");
		else
			System.out.println("NO EQUAL");
		
		TestClass2 refOb=new TestClass2(); 
		method1(refOb);
		//tc2.method1(refOb);
		System.out.println(refOb.className);
		
		System.out.println("0%4= " + 0%4);
		
		int num11 = 5;
		
		System.out.println("num: "+ num11++);
		System.out.println("num: "+ num11);
		
		for(int num31=0; num31 <2; ++num31)
			System.out.println("num3: "+ num31);
		
		
		A ash = new B();
		A bsh = new C();
		//ash=bsh;
		//bsh=ash;
		B Bsh = new C();
		//C csh = (C)ash;
		//ash=Bsh;
		Bsh=(B)bsh;
		
		AA aa1=new BB();
		AA bb1=new CC();
		aa1=bb1;
		CC bb2=(CC)aa1;
		
		int abc=010;
	    int nine=011;
	    System.out.println("Octal 011 ="+nine);
	    System.out.println("octal O17 =" + abc);
	    
	    int x=10;
	    int y=10;
	    if(x<100) y=100;
	    	if(x>=100) y = x *10;
	    		System.out.println("The y is: " + y);
	    		
	    System.out.println("i is: " + i);
	    System.out.println("j is: " + j);
	    System.out.println(i == j);
	    
	    AAA obj1 = new BBB();
	  //  System.out.println(obj.s +  obj1.getName());//
	    TestClass2 tcx = new TestClass2();
	   Integer val=20;
	    System.out.println(tcx.getBalance(val));
	    
	    ArrayList<Integer> arrlist = new ArrayList<Integer>(5);

	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(19);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.add(5);
	    arrlist.ensureCapacity(15);
	    
	    for(Integer inti : arrlist)
	    	System.out.println(inti);
	    
	    byte bs=2;
	    short ss=1;
	    char cs=2;
	    Integer ixp=new Integer("2");
	    int inti=2;
	    		
	    		
	    System.out.println("ixp.equals(inti): "+ixp.equals(inti));
	    System.out.println("ixp==inti: "+(ixp==inti));

	    switch(/*ss*/ixp)
	    {
		    case 1: 
		    	System.out.println("cool");
		    	break;
		    case 2:
		    	System.out.println("super");
		    	break;
	    }
	    
	    A B = new A();
	    B A = new B();
	    
	    //A = (B)B;
		
	    ArrayList<String> al = new ArrayList<String>();
	    al.add("one");
	    al.add("two");
	    al.add("three");
	    String[] s=al.toArray(new String[0]);
	    for(String str2:s)
	    	System.out.print(str2);
	    
	    Class AA = AA.class; 
	    
	    System.out.print("\r");
		String array2D[][]={{"1","2","3"},{"4","5","6","7"}};
		System.out.print(array2D[1].getClass()+"");
		System.out.println(array2D[1].getClass().isArray() + "");
		
		//StringBuilder xb1 = new StringBuilder("snorkler");
		//StringBuilder xb2 = new StringBuilder("yoodler");
		
		StringBuilder xb1 = new StringBuilder("12345678");
		StringBuilder xb2 = new StringBuilder("ABCDEFG");
		
		//System.out.println(xb1.append(xb2.substring(2,5).toUpperCase()));
		//System.out.println(xb2.insert(3, xb1.append("a")));
		System.out.println(xb1.replace(3, 4, xb2.substring(4)).append(xb2.append(false)));
		
		
		System.out.println(args[0]+ " Here");
		
		System.out.println("args.length: " + args.length);
		
		for (int i = 0; i < args.length; i++)
			System.out.print(i == 0 ? args[i] : " " + args[i]);
		//good bye friend!
		

}	

}

class TestClass{
	static class InnerClass{
		
		TestClass tcx = new TestClass();
		
		void method(){
		if(tcx instanceof TestClass){
			
		}
		}
	}
}

class MyClass{
	
	protected MyClass(){
		
	}
	
	int subTotal;
	static int total;
	static MyClass mc = new MyClass();
	MyClass mc2 = new MyClass();
	
	public void calculate(){
		int local;
		
		mc2=this.mc;
		
		local=this.total; 		// Good
		local=this.subTotal; 	// Good
		//this=new MyClass(); 	// Wrong
		//this.local=4;			// Wrong
		this.total=total; 		// Good
		
		char[] cchar = new char[5];
		System.out.println(cchar + "here");
		
	}


}

class A{
String method = "Variable 1";

//syncronized int bbbb=5;
volatile int xxddd=3;

	A(/*String name*/){
		
	}

	static void method1(){}
	
	static void method1(int x){}
	
	
	String attribute(){
		return method;
	}
	
	int wrapperTest(){
		System.out.println("wrapperTest Class-A");
		return 0;
	}
	
	Object get(){
		return "OBJ-A";
	}
	

	
}

class B extends A{
	String method = "Variable 2";
	
	B(){
		//super("Hello");
	}

	static void method1(){}
	
	String attribute(){
		String arr[] = new String[10];
		//arr[1]=new Object();
		Object obj[] = new Object[5];
		obj[1]="Hello";
		return method;
	}	
	
	int wrapperTest(){
		System.out.println("wrapperTest Class-B");
		return 0;
	}
	
	String get(){
		return "STR-A";
	}
	
	public void aMethod() { System.out.println("In aMethod");}
}

class C extends B{
	
}

interface AA{}

class BB implements AA{}

class CC extends BB{}


abstract class AAA{
	abstract String getName() throws Throwable;
}

class BBB extends AAA{
	
	String s = "OCP Java SE 7";
	String getName() throws Exception {
		if(true) throw new Exception("Error"); return s;
    }
}
