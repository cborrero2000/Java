package com.carlos;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;

public class InterThreadComm {

	public static void main(String[] args) throws IOException{
			
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream(pos); // Can be done viceversa, it does not matter

		NumGen ng = new NumGen(pos);
		RunAvg ra = new RunAvg(pis);
			
		ng.start();
		ra.start();
	}
}

class NumGen extends Thread{
	DataOutputStream dos;
		
	public NumGen(OutputStream out){
		try{
			dos = new DataOutputStream(new BufferedOutputStream(out));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			Random ran = new Random(); // import java.util Class here
			int in;
			while(true){
				in = (int)(Math.random()*100_000+1);
				//System.out.println("in: " + in);
				dos.writeInt(in);
				dos.flush();
				sleep(2000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class RunAvg extends Thread // Reads numbers from NumGet. It requires PipedInputString
{
	DataInputStream dis;
	public RunAvg(InputStream in){
		dis = new DataInputStream(new BufferedInputStream(in));
	}
	
	public void run(){
		try{
			int in=0;
			int iter=1;
			int avg1=0;
			int avg2=0;
			while(true){
				in=dis.readInt();
				avg2+=in;
				avg1=(avg2/iter);
				//clearConsole();
				System.out.println("Number: " + in + "  Average1: " + avg1 + "  Summatory: " + avg2 + "  # Iterations: " + iter);
				iter++;
				sleep(2000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void clearConsole(){
		try{
	        String os = System.getProperty("os.name");
	        System.out.println("os: " + os);
	        if (os.contains("Windows")){
	        	System.out.println("os.contains(Windows)");  
	        	Runtime.getRuntime().exec("cls");
	        }
	          else{
	              System.out.println("os does not contain_Windows");
	        	  Runtime.getRuntime().exec("clear");
	          }
	      }
	    catch(Exception exception){
	        //  Handle exception.
	    }
	}
	
	
}