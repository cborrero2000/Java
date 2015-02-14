import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class GenHex {
	static String strfileName;
	static String[][] mscid = null;
	final static int BYTE=2;
	final static int numberOfHeaderLinesInCSVFile = 1;
	static int fileLinePointer=0;
	static int numberLinesCSV=0;
	
	//Custom Print methods
	public static void ps(Object obj){ // print screen function. Just as System.out.print
		System.out.println(obj);
	}
	public static void psf(Object obj1, Object obj2){ // print screen function. Just as System.out.printf
		System.out.printf(obj1.toString(),obj2);
	}
	public static void psn(Object obj){ // print screen NO new line function. Just as System.out.println
		System.out.print(obj);
	}
	public static void ps(Object obj1, Object obj2){ // print screen new line function.Just as System.out.printf plus "\n" ln
		System.out.printf(obj1.toString()+"\n",obj2);
	}
      
	public static void psListHEX(List<Byte> list){ // print screen function. Just as System.out.print
		for(int i=0; i < list.size(); i++){
			System.out.printf("%X ", list.get(i));
		}
		ps("");
	}
	
	public static void psByteHEX(byte[] byteArray, boolean space)
	{
		 for(byte by: byteArray){
			 if(space == true)
			 {
				 psn(String.format("%X ",by));
			 }
			 else
			 {
				 psn(String.format("%X",by));
			 }
				 
 		  }
		 ps("");
	}

	public static void psFileInputStreamHEX(FileInputStream fis)
	{
		StringBuilder builder = new StringBuilder();
		int ch;
		try {
			while((ch = fis.read()) != -1){
				builder.append((char)ch);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(builder.toString());
	}
	
	static byte[] toPrimitiveByteArray(Byte[] wrappedArray) {	// converts Byte[] to byte[]
		byte[] array = new byte[wrappedArray.length];
    	  
		for(int i = 0; i < wrappedArray.length; i++){
			array[i] = wrappedArray[i].byteValue();
		}
		return array;
	}
      
	static Byte[] toWrapperByteArray(byte[] primitiveArray) {	// convert byte[] to Byte[]
		Byte[] array = new Byte[primitiveArray.length];
    	  
		for(int i = 0; i < primitiveArray.length; i++)
			array[i] = primitiveArray[i];
		return array;
	}
      
	static void AsnASCII(List<Byte> list, String str){  // retrieves value of passed literal field and attach it along with its length to the ListArray
		byte[] byteArray = new byte[str.length()];
    	  
		if("".equals(str))
			return;
    		  
    	  byteArray = str.getBytes();
  	
    	  list.add((byte)str.length());	// Adding field length
    	  
    	  for(byte b: byteArray)	// Adding string field one byte at the time
    		  list.add(b);
      }
      
      static void AsnINT_LOMD(List<Byte> list, String str){  // compress two characters in a string into a hex byte ("AB" -> 0xAB) (compress rate 2:1) therefore length gets divided by 2
    	  
    	  if("".equals(str))
    		  return;
    	  str = Long.toHexString(new Long(str)).toUpperCase();	// Convert decimal number in string representation into hexadecimal string representation ("171" -> "AB")
    	  
     	 if (str.length() % 2 != 0){	// If decimal number in string has one digit, it will attach a zero since in the final step to convert into bytes requires two digits ("1" -> "01")
    		 str = "0" + str;
     	 }
     	 
     	 list.add((byte)(str.length() / 2)); 	// Add only half of the string length due to the ascii to byte compression
       	  
     	 for(int i=0; i<str.length(); i+=2){	// convert a pair of characters in the string into one byte at the time
     		 String tempStr;
     		 tempStr = str.substring(i,i+2);
     		 list.add((byte)Integer.parseInt(tempStr,16));	// Important to use base 16 otherwise hexadecimal representations like "A" will throw an NumberFormatException because by default will be waiting a decimal number (0...10) no letters
     	 }
      }
      
      static void AsnINT(List<Byte> list, String str){  // compress two decimals in a string into a hex byte ("12" -> 0x12) (compress rate 2:1) therefore length gets divided by 2
    	  
    	  if("".equals(str))
    		  return;
    	  
    	  str = Long.toHexString(new Long(str)).toUpperCase();	// Convert decimal number in string representation into hexadecimal string representation ("171" -> "AB")
    	  
    	  if (str.length() % 2 != 0){	// If decimal number in string has one digit, it will attach a zero since in the final step to convert into bytes requires two digits ("1" -> "01")
    		  str = "0" + str;
    	  }
          else
          {
        	  if ((int)Math.signum((byte)Integer.parseInt(str,16)) < 0){	// If the hexadecimal integer value is negative two leading zeros will be inserted ("AB" -> -125(int) then "00AB")
        		  str = "00" + str;
        	  }
          }
    	 
       	  list.add((byte)(str.length() / 2)); 	// Add only half of the string length due to the ascii to byte compression
       	  
       	  for(int i=0; i<str.length(); i+=2){	// convert a pair of characters in the string into one byte at the time
    		  String tempStr;
	    	  tempStr = str.substring(i,i+2);
	    	  list.add((byte)Integer.parseInt(tempStr,16));	// Important to use base 16 otherwise hexadecimal representations like "A" will throw an NumberFormatException because by default will be waiting a decimal number (0...10) no letters
    	  }
      }
      
      static void AsnHEX(List<Byte> list, String str, boolean lengthFlag){  // compress or convert two hex characters in a string into a hex byte ("AB" -> 0xAB) (compress rate 2:1) therefore length gets divided by 2. Input should already come in hex format so no need to do zero appending like we did in AsnINT method 
    	  if("".equals(str))
    		  return;
    	  if(lengthFlag==true){
    		  list.add((byte)(str.length() / 2)); 	// Add only half of the string length due to the ascii to byte compression
    	  }
    	  
    	  for(int i=0; i<str.length()-1; i+=2){	// convert a pair of characters in the string into one byte at the time
    		  String tempStr;
	    	  tempStr = str.substring(i,i+2);
	    	  list.add((byte)Integer.parseInt(tempStr,16));	// Important to use base 16 otherwise hexadecimal representations like "A" will throw an NumberFormatException because by default will be waiting a decimal number (0...10) no letters
    	  }
      }
	
      public static File[] listFiles() 
  	{
  		//Directory path here
  		String path = "."; 
   
  		String files;
  		File folder = new File(path);
  		File[] listOfFiles = folder.listFiles(); 
   
  		return listOfFiles;
  		
  		/*for (int i = 0; i < listOfFiles.length; i++) 
  		{
   
  			if (listOfFiles[i].isFile()) 
  			{
  				files = listOfFiles[i].getName();
  				if (files.endsWith(".DAT"))
  				{
  					System.out.println(files);
  				}
  			}
  		}*/
  	}
      
      public static byte[] addRLHFeatureIUM1205(byte[] byteArray){
    	  final List<Byte> fileData = new ArrayList<Byte>();
    	  
    	  StringBuilder sbHexa = new StringBuilder();	// string builder to hold on hexa-format bytes		    

		    int i=0;					// current position of pointer (value is in digits not in pairs)
		    int recordLength=0;			// current record length (value is in pairs not in digit)
		    int fileLength=0;
		    int tagLengthOrig=0;
		    int tagLengthNew=0;
		    String LacTacHex="";
		    String strMscid="";
		    byte[] hFile =null;
		    int tagDataLengthOrig=0;
		    int tagDataLengthNew=0;
		    int posRecordLength=0;		// Storing record-length position (value is in digits not in pair)
		    int diffRecordLength=0;
		    String newHexLength="";

		    for(byte by:byteArray){			// converts byte array into string builder
		    	sbHexa.append(String.format("%02X",byteArray[i])); 
		    	i++;										
		    }
		    
		    fileLength=sbHexa.length();
		    
		    i=0;
		    
		    if( sbHexa.substring(i, i+2).equals("A5") ){	//checks that first byte of input file is 'A5'
		    	i+=BYTE*2;	// move 2 bytes ahead
		    	recordLength=Integer.parseInt(sbHexa.substring(i, i+2), 16); // converting and Hexadecimal String into a integer (decimal of course)
		    	posRecordLength=i;	
		    	i+=BYTE;	// move to first tag
		    	
		    	while(i < fileLength){	//checking if end of the file has been reached
	    		
		    		if((sbHexa.substring(i, i+2).equals("44"))){	// checking for field 44 'mscid' PLMNID + LAC/TAC + Cell_ID/ECI
		    			// modify mscid value (and length and record length if needed)
		    			i+=BYTE;	// move to length byte
		    			tagLengthOrig=Integer.parseInt(sbHexa.substring(i, i+2), 16); // converting and Hexadecimal String into a integer (decimal of course)
		    			
		    			if(mscid[fileLinePointer][3].equalsIgnoreCase("LTE"))
		    			{
		    				strMscid="D";
		    			}
		    			else
		    			{
		    				strMscid="C";
		    			}
		    			
		    			ps("fileLinePointer: " + fileLinePointer);
		    			ps("mscid[fileLinePointer][0]: " + mscid[fileLinePointer][0]);
		    			ps("mscid[fileLinePointer][1]: " + mscid[fileLinePointer][1]);
		    			ps("mscid[fileLinePointer][2]: " + mscid[fileLinePointer][2]);
		    			
		    			strMscid= strMscid.concat(mscid[fileLinePointer][0]);
		    			
		    			strMscid= strMscid.concat(Long.toHexString(new Long(mscid[fileLinePointer][1])).toUpperCase());
		    			
		    			strMscid= strMscid.concat(Long.toHexString(new Long(mscid[fileLinePointer][2])).toUpperCase());
		    			
		    			fileLinePointer++;
		    			
		    			if(fileLinePointer >= (numberLinesCSV-1)){
		    				fileLinePointer=0;
		    				ps("reset fileLinePointer: " + fileLinePointer);
		    			}
		    			
		    			
		    			if(strMscid.length()%2 != 0){		// Filling the last byte if needed
		    				strMscid= strMscid.concat("F");
		    			}

		    			strMscid = String.format("%02X",(strMscid.length()/2))+strMscid;	// inserting the length at the beginning
		    			
		    			tagLengthNew = strMscid.length();

		    	        hFile=String.valueOf(2).getBytes(); // converts string builder content into byte arrays
		    	        
		    		    sbHexa.insert(i, strMscid);

		    		    i+=tagLengthNew;	// We move to the old existing tag 44 length and value already existing. It's possition has been shifted as we inserted the new length and value but have not delete yet the old length and value
		    		    
		    		    sbHexa.delete(i, i+(tagLengthOrig+1)*BYTE);	// delete old mscid tag length + value
	    		    
		    		    tagDataLengthOrig=(tagLengthNew-BYTE)/2;	//
		    		    tagDataLengthNew=(tagLengthOrig*BYTE)/2;
		    		    
		    		    diffRecordLength = tagDataLengthOrig - tagDataLengthNew;
		    		    
		    		    if(diffRecordLength != 0){
		    		    	recordLength+=diffRecordLength;
		    		    	fileLength+=diffRecordLength*BYTE;
		    		    	newHexLength=Integer.toHexString(recordLength);
		    		    	sbHexa.replace(posRecordLength, posRecordLength+2, newHexLength);	// replacing adjusted record length
		    		    }
		    		    
		    		    // 	Since tag 44 has been modified We move now to next Record meaning Tag 'A5' by going to the end of the current record
		    		    int diffCurrentPosToRecordBeginning = i - posRecordLength*BYTE;	// i is the current position of the pointer in the string builder. Adding one byte to count the byte position of the record length byte which is not counted in its own value
		    		    int totalRecordLength=(recordLength+3)*BYTE;
		    		    int nextA5Tag = i + totalRecordLength - (diffCurrentPosToRecordBeginning+4*BYTE); // in another words: Record length - (Current Position - Position of Record Length + 8 positions of two initial bytes (A5|81))
		    		    i=nextA5Tag;

		    		    if(i >= fileLength)	// if next nextA5Tag position is greater than file end we exit (we finished). No more fields 44 to modify
		    		    {
		    		    	break;	
		    		    }
		    		    											// Move to the next A5 Tag
		    		    if( sbHexa.substring(i, i+2).equals("A5") )	// We verify next tag is a IUM1205 init tag 'A5' if not then we exit because we don't want to edit anything else because we can made the file corrupted and it will not get processed. Let's leave what we modified previously then, so we'll exit the loop now since we have lost track of where we are. If we have counted well then we should be on the Init Tag 'A5'
		    		    {
		    		    	i+=BYTE*2;	// move 2 bytes ahead to get to the record length 
		    		    	recordLength=Integer.parseInt(sbHexa.substring(i, i+2), 16); // storing in decimal format the record length of the next record in the file
		    		    	posRecordLength=i;	// Storing record-length position
		    		    	
		    		    	i+=BYTE;	// move to first tag after the record length
		    		    }
		    		    else	// We lost track of the positions in the file then we exit to avoid corrupting any data
		    		    {

		    		    	break;
		    		    }
		    		}
		    		else{
		    			i+=BYTE;	// move to length byte
		    			tagLengthOrig=Integer.parseInt(sbHexa.substring(i, i+2), 16); // converting tag length from Hexadecimal String into a integer (decimal of course)
		    			i+=BYTE;				// moving pointer from tag length to tag data first position
		    			i+=BYTE*tagLengthOrig;	// moving from first position of current tag data to first position of next tag
		    		}
		    	}	// end while
		    }

		    Byte[] lbFile;
		    
		    for(int j=0; j<sbHexa.length(); j+=2){ //Converting StringBuilder into array of bytes compressed as hexa
		    	AsnHEX(fileData, sbHexa.substring(j, j+2), false);
		    }

		    lbFile=fileData.toArray(new Byte[0]); // Converting ArrayList of Bytes into array of Bytes (wrapper)
		    byte[] primitivebytearray = toPrimitiveByteArray(lbFile); // Converting array of Bytes (wrapper) into array of bytes (primitives)
	    
		    return(primitivebytearray);
      }
      
      public static File createFile(int iterations, int NumberCDRsPerFile, int numberLinesCSV){
  	  
  	  String fileName = "temp_IMM1205.Dat";
  	  File imm_1205_dat = new File(fileName);
  	  return imm_1205_dat;
    }
      
    public static void writeFile(byte[] bFile, File imm_1205_dat, OutputStream os) {	// Creates .DAT file writing bytes in hexadecimal directly to file
  	  DataOutputStream dos=null;
//  	  boolean success=false;
  	  
  	  
		ps("bFile: ");
		psByteHEX(bFile, true);
  	  
//      success = (new File("./RLH_IUM1205")).mkdirs();
//      Path path = Paths.get("./RLH_IUM1205");
//      ps("path: " + path);
//            
//      if (!success) {
//           ps("Directory Creation Failed");
//      }
//      else{
//    	  ps("Directory Creation Success");
//      }
  	  
  	  try{												// Writes R714 RLH file
  		  dos = new DataOutputStream(os); 			// Knows how to write raw data but not to a file
  		  dos.write(bFile);                			// writing byte array into stream and implicitly to the file
  		  
  	  }catch(IOException e){
  		  e.printStackTrace();
  	  }
  	  finally{
  		  try{
  				  dos.close();
  				  //File renamed_imm_1205_dat = new File("./RLH_IUM1205/fileName.DAT");
  				  //imm_1205_dat.renameTo(renamed_imm_1205_dat);	// renames R714 RLH file created
  		  }catch(NullPointerException e){
  		  }catch(IOException e){
  			  e.printStackTrace();
  		  }
  	  }
    }
    
	public static void main(String[] args) {
		strfileName="rlh_mscid.csv";
		int lineCount=0;
		BufferedReader br = null;
		File file = null;
		OutputStream os = null;
   
		String line = "";
		String cvsSplitBy = ",";
		int NumberCDRsPerFile=1;

		  boolean success=false;
  	  
	      success = (new File("./RLH_IUM1205")).mkdirs();
	      Path path = Paths.get("./RLH_IUM1205");
	      ps("path: " + path);
	            
	      if (!success) {
	           ps("Directory Creation Failed");
	      }
	      else{
	    	  ps("Directory Creation Success");
	      }
		
		
		
  	  try {
		  br = new BufferedReader(new FileReader(strfileName));
		  line = br.readLine();          		// Reading first row (header row)We don't care its values because we know the position that corresponds to each field
		  LineNumberReaderImplementationClass ln =  new LineNumberReaderImplementationClass();
    	  numberLinesCSV = ln.count();
    	  mscid = new String[numberLinesCSV][4];

    	  while ((line = br.readLine()) != null) {    //Loading CSV file values in a matrix [][] PLMNID	LAC/TAC	Cell_ID/ECI
			  mscid[lineCount] = line.split(cvsSplitBy);  	// use comma as separator
			  lineCount++;
			  String fileName = "rlh_mscid.csv";
		  	  File ium_1205_dat = new File(fileName);
  		  }
	  } catch (IOException e) {
		  e.printStackTrace();
	  } finally{
		  try{
			  br.close();
		  } catch(IOException e){
			  e.printStackTrace();
		  }
	  }

  	String fileName="";
  	File binary_file= new File(fileName);
  	
  	File[] listOfFiles = listFiles();
  	
  	ps("listOfFiles.length :" + listOfFiles.length);
  	for (int k = 0; k < listOfFiles.length; k++) 
		{
  			ps(listOfFiles[k].getName());
		}
  	
/////////////////////////
  	for (int k = 0; k < listOfFiles.length; k++) 
		{

  		ps("fileLinePointer_1: " + fileLinePointer);
  		
			if (listOfFiles[k].isFile()) 
			{
				fileName = listOfFiles[k].getName();
				if (fileName.endsWith(".DAT"))
				{
					System.out.println(fileName);
					binary_file = new File(fileName);
					ps("fileLinePointer_2: " + fileLinePointer);
					
				/*}*/
			/*}*/
		/*}*/
  	  /////////////////////////
  	  
//  	  if(true){
//  		  ps("fileName: " + fileName);
//  		  ps("binary_file: " + binary_file);
//  		  
//  		  while(true);
//  		  
//  	  }
  	  
					ps("fileLinePointer_33: " + fileLinePointer);
		//File file_out = new File("CDR_output.dat"); //file ascci same as original ascii file read
		File rlh_file_out = new File(fileName);
		//String fileName = "SMOMMS06_FIUM1205_ID000001_T20140729061500_R2.DAT";
//		ps("fileName_1: " + fileName);
		
		//File binary_file = new File(fileName);
		
		//fileName = "CDR_HEX_" + fileName; // Original ASCII file printed into Hex format
		//File file_out_hex = new File(fileName);

		FileInputStream fis = null;

		File input_file = binary_file; // file to be read

		byte[] bFile = new byte[(int)input_file.length()];  // preparing arrays to hold the data
		byte[] bFile2 = new byte[(int)input_file.length()];	// from read from the file
		byte[] hFile = new byte[(int)input_file.length()*2]; // twice to include blank separator
		
		try{
			fis = new FileInputStream(input_file); // initializing file abstract pathname of Production IUM1205 NO RLH ready
			fis.read(bFile); // reading IUM1205 file characters into byte array bFile.
			ps("fileLinePointer_34: " + fileLinePointer);
			byte[] cdrRecord = addRLHFeatureIUM1205(bFile);	// Modify file Prod IUM_1205 file content R614 into R714_RLH  
			
			os = new FileOutputStream("./RLH_IUM1205/" + rlh_file_out);
			
			writeFile(cdrRecord, rlh_file_out, os);	// writes a file no sure which one or where

			fis.close();
			
			//OutputStream outputStream = new FileOutputStream (file_out);	// Knows how to write to a file 
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();		// Knows how to write bytes but no to a file
			
			//OutputStream rlhoutputStream = new FileOutputStream (rlh_file_out);
			//ByteArrayOutputStream rlhbaos = new ByteArrayOutputStream();
			
			//baos.write(bFile);			// writing byte array into stream
			//baos.writeTo(outputStream); // writes steam content into file as characters
			//baos.close();				// remember to close it, or output file will be empty
			
			//rlhbaos.write(bFile);			// writing byte array into stream
			//rlhbaos.writeTo(rlhoutputStream); // writes steam content into file as characters
			//rlhbaos.close();
			
		    ////////////////////////////
			///////CONCEPT REFRESH//////
		    // HEX: 31 32 2E 30 31 33 //
		    // ASCII: 12.013          //
		    ////////////////////////////
			ps("fileLinePointer_35: " + fileLinePointer);
		    StringBuilder hexa = new StringBuilder();	// string builder to hold on hexa-format bytes		    

		    int i=0;

		    for(byte by:bFile){			// converts ascii content of byte array into hexadecimal
		    	hexa.append(String.format("%X ",bFile[i])); 
		    	i++;										
		    }

		    hFile = String.valueOf(hexa).getBytes(); // converts string builder content into byte arrays
		    //OutputStream out = new FileOutputStream(file_out_hex);
		    //out.write(hFile);
		    //out.close();
		    
		    // casting to byte is required because 
		    byte[] tFile = new byte[]{(byte) 0xBF, 0X53, (byte) 0X80, (byte) 0X80, 0X1, 0X53, (byte) 0X82, 0X6, 0X49, 0X4E, 0X56, 0X49, 0X54, 0X45, (byte) 0X83, 0X1, 0X0, (byte) 0XBF, (byte) 0X81, 0X4C};
//		    OutputStream osf = new FileOutputStream(file_imm_1205);
//		    DataOutputStream dos = new DataOutputStream(osf);
//		    //dos.write(tFile); // Writing characters in IMM1205.DAT file. It works!!!!!!
//		    osf.write(tFile);
		    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
//			System.out.println("\nfinally executed");
		}
				}
			}
	} // end of for

	}	// end of main
	
	
	
	
	
	static class LineNumberReaderImplementationClass
	{
		int linenumber = 0;
		
	    public int count()
	    {	
	    	try{
	    		File file = new File(strfileName);
	 
	    		if(file.exists()){
	    		    FileReader fr = new FileReader(file);
	    		    LineNumberReader lnr = new LineNumberReader(fr);
	 
	    	            while ((lnr.readLine()) != null){
	    	            		linenumber++;
	    	            }
	    	            
	    	            lnr.close();
	    	            return linenumber==0 ? 0: linenumber-numberOfHeaderLinesInCSVFile;	// No counting header only data rows
	    		}else{
	    			 System.out.println("File does not exists!");
	    			 return linenumber;
	    		}
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    	return linenumber;
	    }
	}
}


	
