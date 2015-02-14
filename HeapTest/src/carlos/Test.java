package carlos;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Test {
	
	static void writeData(OutputStream os){
		
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));
		
		//int[] inArray = {1,2,3,4,5};
		
		try{
			//for(int i :  inArray)

			dos.writeInt((int)(Math.random()*100000));
			dos.flush();
			
		}catch(IOException e){

		}
	}
	
	public static void readData(InputStream is) throws IOException {
		
		DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
		
		int inValue;
		int iter = 0;
		int inTotal = 0;
		int inAvg = 0;
		
		try{
			while(true){
				iter++;
				inValue = dis.readInt();
				inTotal += inValue;
				inAvg = inTotal/iter;

				System.out.println("AVg: " + inAvg +" Value: " + inValue + " iter: " + iter + " Acum: " + inTotal);
			}
		}catch(IOException e){
		}
	}
		
	public static void main(String[] args) throws IOException {
		
		final PipedOutputStream pos = new PipedOutputStream();
		final PipedInputStream pis = new PipedInputStream(pos);
		
		Runnable writeOut = new Runnable() {
			public void run(){
				
				while(true)/*for(int i=0; i < 3; i++)*/{
					
					writeData(pos);

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		Runnable readIn = new Runnable() {
			public void run(){
				
				while(true)/*for(int i=0; i < 3; i++)*/{
					
					try {
						readData(pis);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e){
						e.printStackTrace();
					}
				}
			}
		};
		
		Runnable objInt = new Runnable(){
			
			Integer inGrow = 2;
			
			public void run(){
				while(true){
					inGrow++;
					System.out.println("inGrow: " + inGrow);
					try{
						Thread.sleep((int)(Math.random()*2000));
						
					}catch(InterruptedException e){
					e.printStackTrace();
					}
				}
			}
		};
		
		
		Runnable handler = new Runnable(){
			
			public void run(){
				File f = new File("test.txt");
				
				try{
					while(true){
						f.createNewFile();
						BufferedWriter out = new BufferedWriter(new FileWriter(f));
						out.write("Hello");
						out.close();
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		};
		
		Runnable hm = new Runnable(){
		    HashMap map = new HashMap();
		    
			public void run(){
				while(true){
				    map.put(new Integer( 2 ), "two" );
				    map.put( new Integer((int)(Math.random()*1000000)), "four" );
				    System.out.println( map );
				    System.out.println();
				    
				    try{
				    	Thread.sleep((int)(Math.random()*2000));
				    }catch(InterruptedException e){
				    	e.printStackTrace();
				    }
				}
			}
		};
		
		Thread threadIn = new Thread(readIn, "ThreadIn");
		Thread threadOut = new Thread(writeOut, "ThreadOut");
		Thread threadInt = new Thread(objInt, "ThreadInt"); 
		Thread threadFile = new Thread(handler, "ThreadFile");
		Thread threadhm = new Thread(hm, "Threadhm");
		
			threadOut.start();
			threadIn.start();
			threadInt.start();
			threadhm.start();
	}
}
