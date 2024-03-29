package carlos;

import java.io.*;
import java.util.Date;

class TestMain {
	
	public static void main(String[] args) throws Exception {

		int comm=Integer.parseInt(args[0]);
		
		StringBuilder sb = new StringBuilder();
		
		switch(comm) {
		case 0 :
			Test t=new Test();
			t.setNumber(800);
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("file"));
			oos.writeObject(t);
			oos.close();
			break;
		
		case 1 :
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("file"));
			t=(Test)ois.readObject();
			System.out.println(t.getNumber());
			ois.close();
			break;

		default:
			System.out.println("invalid command");
			break;
		}
	}
}


class Test implements Serializable {
	
	transient int number=200;
	
	Test() {
		number =300;
	}
	
	void setNumber(int number) {
		this.number = number;
	}
	
	int getNumber() {
		return number;
	}
}
