import java.util.ArrayList;
import java.util.List;

public class Principal {
	
	public static void main(String[] args) {
		System.out.println("Hello World Carlos Borrero CE Inc.");
		
		ArrayList aList = new ArrayList();
		
		//ArrayList<Object>aList = new<Object>ArrayList(); Same thing as above
		//because By Default the ArrayList is for Objects if you  want to specify 
		//the ArrayList for integers only, then you need to specify as follows
		//ArrayList<Integer>aList = new<Integer>ArrayList();
		
		aList.add("God");
		aList.add(4);
		aList.add(true);
		aList.add(12.15F);
		
		System.out.println(aList/*.toString()*/);
		
		Float f = (Float)aList.get(3);
		
		int i = (int)aList.get(1);
		
		System.out.println(f+3);
		
		System.out.println("i+3: " + (i+3));
		
		System.out.println(aList.get(0));
		
		String s = (String)aList.get(0);
		
		System.out.println(s+ " " +3);
		
		assert (s.length() > 0): "s.length: " + s.length();
		
		
		///////////////////////////////////////////////////////
		ArrayList al = new ArrayList();
		System.out.println("Initial size of al: " + al.size());
		
		//add elements to the array list
		al.add("SCJP6 Training Lab");
		al.add("Java SE 7 OCA Training Lab");
		al.add("Java SE 7 OCP tranining Lab");
		al.add("OCE Java EE 6 EJB Training Lab");
		al.add("SCEA Training Lab");
		al.add("SCMAD training Lab");
		al.add(1, "SCBCD Training Lab");
		
		System.out.println("Size of al after additions: " + al.size());
		
		// display the array list
		
		System.out.println("Content of al: " + al);
		
		//Remove elements from the array list
		
		al.remove("OCE Java EE 6 EJB Training Lab");
		al.remove(2);
		System.out.println("Size of al after deletions: " + al.size());
		System.out.println("Contents of al: " + al);
		
		Object ia[] = al.toArray();
		System.out.println("Object Array ia: " + ia);
		System.out.println("ia length: " + ia.length);
		
		
		 

  
		//List<Integer> a=new ArrayList<Integer>();  
		//a.add(new StringBuilder(new Integer(10)));  
		//System.out.println(a); 

		
		byte num = 8;
		num = (byte)(num >>> 2);
		System.out.println("byte: " + num);
		
	}

}