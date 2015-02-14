import java.io.*;
import java.io.PrintStream;
import java.io.PrintWriter;

class Employee implements Serializable {
	private String firstName;
	private String lastName;
	private transient String SSN;
	private transient int i;
	
	Employee(){
		
	}
	
	Employee(String fName, String lName, String SSN, int i){
		this.firstName = fName;
		this.lastName = lName;
		this.SSN = SSN;
		this.i = i;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer(40);
		sb.append("First Name: ");
		sb.append(firstName);
		sb.append(", Last Name: ");
		sb.append(lastName);
		sb.append(", SSN: ");
		sb.append(SSN);
		sb.append(", i: ");
		sb.append(i);
		return sb.toString();
	}
}

public class Principal{

	public static void main(String[] args) throws Exception { // throws Exception is not need if I have try and catch but I wanted to remember that.
		
		// Serializing
		Employee e = new Employee("Peter","Parker","123456789",5);
		
		try{
		ObjectOutputStream outOBJ = new ObjectOutputStream(new FileOutputStream("employee.ser"));
		outOBJ.writeObject(e);
		outOBJ.close();
		}
		catch(IOException i){
			System.out.println("Error Serializing and Objetc");
		}
		
		//Deserializing
		try{
			ObjectInputStream inOBJ = new ObjectInputStream(new FileInputStream("employee.ser"));
			Employee e2 = new Employee();
			e2 =(Employee) inOBJ.readObject();
			System.out.println("Object e:  " + e);
			System.out.println("Object e2: " + e2);
			inOBJ.close();
		}
		catch(IOException i){
			
		}
		catch(ClassNotFoundException i){
			
		}
	}
}
