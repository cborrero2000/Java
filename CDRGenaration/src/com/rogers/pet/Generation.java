package com.rogers.pet;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Generation {
	
	public static void main(String[] args) {
		
	FileInputStream fis = null;
	FileReader fr = null;
	File file_in = new File("C:\\Users\\carlos\\Desktop\\JFolder\\STOIMM01_FIMM1205_ID0000_T20140212161518_R1.DAT.ASCII");
	File file_out = new File("C:\\Users\\carlos\\Desktop\\JFolder\\bans_out.txt");
	File file_out_2 = new File("C:\\Users\\carlos\\Desktop\\JFolder\\bans_out_2.txt");
	File file_out_3 = new File("C:\\Users\\carlos\\Desktop\\JFolder\\bans_out_3.txt");
	byte[] bFile = new byte[(int)file_in.length()];
	FileOutputStream fos = null;
	ByteArrayOutputStream baos = null;
	
	
	
	try{
		fis = new FileInputStream(file_in);
		fr = new FileReader(file_in);

		BufferedWriter bw = new BufferedWriter(new FileWriter(file_out_2));
		bw.write("BufferedWriter is writing...");
		
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(file_out_3));
		bw2.write("BufferedWriter is writing...");
		
		fis.read(bFile);
		fr.read();
		fr.close();
		bw.close();
		bw2.close();
		
//		fos = new FileOutputStream(file_out);
//		baos.write(bFile);
//		baos.close();
		
//		fos.write(bFile);
//		fos.close();
		
		
		
//		BufferedOutputStream bos = new BufferedOutputStream(new ByteArrayOutputStream(new FileOutputStream(file_out)));
		
		baos = new ByteArrayOutputStream();
		baos.write(bFile);
		
		DataOutputStream w = new DataOutputStream(baos);
		
		OutputStream output = null;
        output = new BufferedOutputStream(baos);
        BufferedOutputStream boos = new BufferedOutputStream(output);
//        boos.wri
//        baos.writeTo(output);
        
        
	    output.write(bFile);
        output.close();
		
		
		System.out.println("Done");
		
	}
	catch(IOException e){
		e.printStackTrace();
	}
	finally{
		System.out.println("finally executed");
	}
	
	}
	
}
 