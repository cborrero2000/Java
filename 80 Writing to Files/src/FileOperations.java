import java.util.*;

public class FileOperations {
	
	private Formatter x;
	
	
	public void open(){
		try{
			x = new Formatter("salary.txt");
		}
		catch(Exception e){
			System.out.println("There is an error");
		}
	}
	
	public void write(){
		x.format("%s%s%s", "Carlos ", "37 ", "$240,000");
	}
	
	public void close(){
		x.close();
	}
}
