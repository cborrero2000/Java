import java.util.*;

public class StaticFlow {

	String n1;
	String n2;
	// Execution Order Seq: 
	StaticFlow(){
		n1="Hello";
		n2=n1;
		System.out.println("Inside StaticFlow Constructor");
	}
	//static{System.out.println("Static_Block");} 	// static block has to be outside of the main. they get executed when
													//instantiating the class
	
	{System.out.println("Non_static_Block_Out_Main");} // non-static block can be inside or outside of the main. They get executed when
	//instantiating the class
	
	public static void main(String[] args) {
		List lst1 = new ArrayList<Integer>();
		List lst2 = new ArrayList<String>();
		{System.out.println("Non_static_Block_In_Main_1");}
		StaticFlow t1 = new StaticFlow();
		StaticFlow t2 = new StaticFlow();
		{System.out.println("Non_static_Block_In_Main_2");}
	}
	
	static{System.out.println("Static_Block_Out_Of_Main");} // static block has to be outside of the main. they get executed when
}