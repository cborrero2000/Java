import java.io.*;
import java.util.ArrayList;

//import java.lang.*;  // No need to import it since automatically all classes import it automatically

public class Main {

	
	public static void main(String[] args) throws IOException{
		BufferedReader object = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Please Enter a Sentence:\n");
		
		/*try{
		String s = object.readLine();
		System.out.printf("%d\n", s.length());
		System.out.println(s.toCharArray());
		}
		catch(IOException e){
			
		}*/
		
		//////////////////////////////////////////////
		String name1 = "Jumping into C++";
		String name2 = new String("Jumping into C++");
		String name3 = "Jumping into C++";
		String name4 = new String("Jumping into C++");
		String name5 = name4;
		//////////////////////////////////////////////
		
		String validationS = "umping";
		
		if(name1.startsWith(validationS, 1))
			System.out.println("String Starts With " + validationS);
		else
			System.out.println("String does NOT Starts With " + validationS);

		String endValString = "C++";
		
		if(name1.endsWith(endValString))
			System.out.println("String Ends With " + endValString);
		else
			System.out.println("String does NOT Ends With " + endValString);

		
		if(name1==name2)
			System.out.println("name 1 and name 1 are equal (==)");
		else
			System.out.println("name 1 and name 2 are not equal (==)");
		
		
		if(name1.equals(name2))
			System.out.println("name 1 and name 2 are equal");
		else
			System.out.println("name 1 and name 2 are not equal");
		

		if(name1==name3)
			System.out.println("name1 and name 3 are equal (==)");
		else
			System.out.println("name1 and name 3 are not equal (==)");
		
		
		if(name1.equals(name3))
			System.out.println("name 1 and name 3 are equal");
		else
			System.out.println("name 1 and name 3 are not equal");
	
		if(name2==name4)
			System.out.println("name2 and name 4 are equal (==)");
		else
			System.out.println("name2 and name 4 are not equal (==)");
		
		
		if(name2.equals(name4))
			System.out.println("name 2 and name 4 are equal");
		else
			System.out.println("name 2 and name 4 are not equal");
		
		if(name4==name5)
			System.out.println("name4 and name 5 are equal (==)");
		else
			System.out.println("name4 and name 5 are not equal (==)");
		
		
		if(name4.equals(name5))
			System.out.println("name 4 and name 5 are equal");
		else
			System.out.println("name 4 and name 5 are not equal");
		
		//////////////////////////////////////////////
		/*String name1 = "epractizelabs";
		String name2 = new String("EPractizelabs");
		String name3 = "EPractizeLabs";*/
		//////////////////////////////////////////////		
		
		
		// 1st case
		if(name1.compareTo(name2)==0)
			System.out.println("The Strings are equal");
		else if(name1.compareTo(name2)<0)
			System.out.println("name2 follows name1");
		else
			System.out.println("name1 follows name2");

		// 2nd case
		if(name1.compareTo(name3)==0)
			System.out.println("The Strings are equal");
		else if(name1.compareTo(name3)<0)
			System.out.println("name3 follows name1");
		else
			System.out.println("name1 follows name3");
		
		// 3rd case
		if(name2.compareTo(name3)==0)
			System.out.println("The Strings are equal");
		else if(name2.compareTo(name3)<0)
			System.out.println("name3 follows name2");
		else
			System.out.println("name2 follows name3");
		
		System.out.println(name1.replace('Z', 'J'));
		System.out.println("Hillo Miriquitingui".replace('i','e'));
		
		for(int i=0; i < name1.length(); i++)
			System.out.print(name1.charAt(i));
		
		//System.out.println("\n"+name1.indexOf('i'));
		//System.out.println("\n"+name1.lastIndexOf('i'));
		
		System.out.println("\n"+name1.indexOf("Jumpin"));
		System.out.println("\n"+name1.indexOf("in"));
		System.out.println("\n"+name1.lastIndexOf("in"));
		
		System.out.println("\n"+name1.replaceAll("mping","piter"));
		
		Integer intObj = new Integer(35);
		
		Float intFloat = intObj.floatValue();
		
		Float floatObj = new Float("152");
		System.out.println(floatObj);
		
		Double dbObj = new Double("1532");
		System.out.println(dbObj);
		
		Long longObj = new Long("7");
		System.out.println(longObj);
		
		Integer intObj2 = new Integer("33");
		System.out.println(intObj2);
		
		System.out.println(intFloat);
		
		int myVar2;
		String name = "Brian Christopher";
		
		if(name instanceof String)
			System.out.println("name is an instance of String");
		else
			System.out.println("name is NOT an instance of String");
			
		
		System.out.println(7*4/7);
		
		System.out.println(7/7*4);
		
		System.out.println(Integer.toBinaryString(2));
		System.out.println(Integer.toBinaryString(2 << 11));
		System.out.println(Integer.toBinaryString(7));
		System.out.println(Integer.toBinaryString(7>>1));
		System.out.println(Integer.toBinaryString(7>>>1));
		
		System.out.println("0100|4: " + (0100|4));// Octal notation starts with zero 0 so 0100 means 64 = 8 * 8
		System.out.println(0b101); //bynary notation
		System.out.println(0xb100); //hexadecimal notation
		
        BufferedReader userInput = new BufferedReader 
                (new InputStreamReader(System.in));
        
		ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("Italian Riviera");
        myArr.add("Jersey Shore");
		System.out.println("ArrayList contains: "+myArr.toString());
		
		 myArr.add("Puerto Rico");
	        myArr.add("Los Cabos Corridor");
	        myArr.add("Lubmin");
	        myArr.add("Coney Island");
	        myArr.add("Karlovy Vary");
	        myArr.add("Bourbon-l'Archambault");
	        myArr.add("Walt Disney World Resort");
	        myArr.add("Barbados");
	 
	        System.out.println("Stupid Vacation Resort Adviser");
	        System.out.println("Enter your name:");
	        String namex = userInput.readLine();
	        Integer nameLength = namex.length();
	        if (nameLength == 0) 
	        { 
	            System.out.println("empty name entered");
	            return;
	        } 
	 
	        Integer vacationIndex = nameLength % myArr.size();
	 
	        System.out.println("myArr.size(): " + myArr.size());
	        System.out.println("vacationIndex: " + vacationIndex);
	        System.out.println("4%5: " + 4%5);
	        
	        System.out.println("\nYour name is "+namex+", its length is " + 
	                        nameLength + " characters,\n" +
	                        "that's why we suggest you to go to " 
	                        + myArr.get(vacationIndex));
	    } 
		}
		
		
		
	

