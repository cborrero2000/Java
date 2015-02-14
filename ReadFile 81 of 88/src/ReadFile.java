import java.io.*;
import java.util.*;

public class ReadFile {
	
	private Scanner x;
	
	public void openFile(){
		try {
			x = new Scanner(new File("C:\\users\\carlos\\workspace\\ReadFile 81 of 88\\src\\chinese.txt"));
			System.out.println("Yeahh It opened the File :)");
		} catch (FileNotFoundException e) {
			System.out.println("Error open the file");
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		while(x.hasNext()){
			String a = x.next();
			String b = x.next();
			String c= x.next();
			
			System.out.println(a+" "+b+" "+c);
		}
	}
	
	public void closeFile(){
		x.close();
	}

}
