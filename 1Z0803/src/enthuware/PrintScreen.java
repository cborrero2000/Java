package enthuware;

public class PrintScreen {
	public static void ps(Object obj){ // print screen function. Just as System.out.print
		System.out.print(obj);
	}
	public static void ps(Object obj1, Object obj2){ // print screen function. Just as System.out.printf
		System.out.printf(obj1.toString(),obj2);
	}
	public static void psln(Object obj){ // print screen new line function. Just as System.out.println
		System.out.println(obj);
	}
	public static void psln(Object obj1, Object obj2){ // print screen new line function. Just as System.out.println with formatting
		System.out.printf(obj1.toString()+"\n",obj2);
	}
}
