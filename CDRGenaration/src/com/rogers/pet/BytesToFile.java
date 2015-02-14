package com.rogers.pet;


	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	 
	public class BytesToFile
	{
	    public static void main( String[] args )
	    {
	    	FileInputStream fileInputStream=null;
	 
//	        File file = new File("C:\\testing.txt");
	        
	        File file_in = new File("C:\\Users\\carlos\\Desktop\\JFolder\\testing.txt");
	        File file_out = new File("C:\\Users\\carlos\\Desktop\\JFolder\\testing2.txt");
	 
	        byte[] bFile = new byte[(int) file_in.length()];
	 
	        try {
	            //convert file into array of bytes
		    fileInputStream = new FileInputStream(file_in);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
	 
		    //convert array of bytes into file
		    FileOutputStream fileOuputStream = 
	                  new FileOutputStream(file_out); 
		    fileOuputStream.write(bFile);
		    fileOuputStream.close();
	 
		    System.out.println("Done");
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	
	

}
