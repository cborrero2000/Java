package com.carlos;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class TestPipes {

	public static void writeData(OutputStream os){ // I had to declare this method static here because I will use it in the main method and since main is static it requires this method to be static as well if you want to call it within the main method. 
	
		int[] intArray = {1,2,3,4,5,8};
		
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os)); // BufferedOutputStream cannot instantiate OutputStream so we have to pass it the instance already created (os)
		
		try{
			for(int i : intArray){
				dos.writeInt(i);
				System.out.println("DataOutputStream: " + i);
			}
			
			System.out.println("");
				
			dos.flush();
			dos.close();
		
		}catch(IOException e){
			e.printStackTrace();
		}
	
	}
	
	public static void readData(InputStream is){
		DataInputStream dis = new DataInputStream(new BufferedInputStream(is)); // BufferedInputStream cannot instantiate InputStream so we have to pass it the instance already created (is)
	
		boolean eof = false;
		int iValue=0;
		
		try{
			while(!eof){
				iValue = dis.readInt();
				System.out.println("DataInputStream: " + iValue);
			}
		}catch(IOException e){
			eof = true;
		}
	}

	public static void main(String[] args)throws IOException{
		
		final PipedOutputStream pos = new PipedOutputStream();
		final PipedInputStream pis = new PipedInputStream(pos);
				
		Runnable writeOut = new Runnable(){   // Implementing run by using anonymous class technique. At the same time we are creating an object of the anonymous class
			public void run(){
				writeData(pos); 
			}
		};
		
		Runnable readIn = new Runnable(){
			public void run(){
				readData(pis);
			}
		};
		
		Thread threadOut = new Thread(writeOut, "threadOut");
		Thread threadIn = new Thread(readIn, "threadIn");
		
		threadOut.start();
		threadIn.start();
	}
}