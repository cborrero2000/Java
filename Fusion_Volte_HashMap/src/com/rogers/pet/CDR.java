package com.rogers.pet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CDR {	// this class creates a IMM1205 CDR by reading the data from a csv file
	static String strfileName;
	static int inFileSeqNumber=0;
	static int numberOfCdrsWrittenInFile=0;
	final static int numberOfHeaderLinesInCSVFile = 1;
	static String[] columnValue = null;
	static int inFileId = 0; // FILE_ID = Incremental number (4 digits) set by EMM, from 0000 to 9999
	final static int FILE_ID_MIN = 0;
	final static int FILE_ID_MAX = 9999;
	final static int FILE_ID_STEP = 500;
	enum LGs{
		 
	RSOBDCPSPET01 (FILE_ID_MIN),
	RSOBDCPSPET02 (FILE_ID_STEP),
	RSOBDCPSPET03 (FILE_ID_STEP*2),
	RSOBDCPSPET04 (FILE_ID_STEP*3),
	RSOESNPETLS01 (FILE_ID_STEP*4),
	RSOESNPETLS02 (FILE_ID_STEP*5),
	RSOESNPETLS03 (FILE_ID_STEP*6),
	RSOESNPETLS04 (FILE_ID_STEP*7),
	RSOESNPETLS05 (FILE_ID_STEP*8),
	RSOESNPETLS06 (FILE_ID_STEP*9),
	INJPERF01 (FILE_ID_STEP*10),
	INJPERF02 (FILE_ID_STEP*11),
	DRSSBRA3111 (FILE_ID_STEP*12);
	
	private final int startRange;
	
	LGs(int startRange){
		this.startRange = startRange;
	}
	
	private int startRange() {return startRange; }
	
	} 

	
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
	
	public static void psByteHEX(byte[] byteArray)
	{
		 for(byte by: byteArray){
 			  psn(String.format("%X ",by));
 		  }
		 ps("");
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
      
      static void AsnINT(List<Byte> list, String str){  // compress two characters in a string into a hex byte ("AB" -> 0xAB) (compress rate 2:1) therefore length gets divided by 2
    	  
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
      
      static void AsnHEX(List<Byte> list, String str){  // compress or convert two hex characters in a string into a hex byte ("AB" -> 0xAB) (compress rate 2:1) therefore length gets divided by 2. Input should already come in hex format so no need to do zero appending like we did in AsnINT method 
    	  if("".equals(str))
    		  return;

    	  list.add((byte)(str.length() / 2)); 	// Add only half of the string length due to the ascii to byte compression
       	  
    	  for(int i=0; i<str.length()-1; i+=2){	// convert a pair of characters in the string into one byte at the time
    		  String tempStr;
	    	  tempStr = str.substring(i,i+2);
	    	  list.add((byte)Integer.parseInt(tempStr,16));	// Important to use base 16 otherwise hexadecimal representations like "A" will throw an NumberFormatException because by default will be waiting a decimal number (0...10) no letters
    	  }
      }
      
      public static void main(String[] args) {
    	  CDR cdr = new CDR();
    	  strfileName="CDRFile.txt";	//MO PS Audio and MO CS Audio
    	  cdr.initRowHashMap();
    	  
    	  String computerName = null;
    	  
		try {	// Assigns starting Range for •	FILE_ID sequence based on the the computer name 
			computerName = InetAddress.getLocalHost().getHostName();
			computerName= "RSOBDCPSPET01";
//			System.out.println("computerName: " + computerName);
			
			for(LGs lg: LGs.values()){
//				ps(lg.toString());
				if(lg.toString().equals(computerName)){
					inFileId = lg.startRange();
//					ps("inFileId: " + inFileId);
					break;
				}
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
//    	  try {
//			ps("InetAddress.getLocalHost()2: " + InetAddress.getLocalHost());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	  //CSV file compariosn
    	  //STOIMM01_FIMM1205_ID0042_T20140212161518_R1_MO_CS_AUDIO.txt
    	  //STOIMM01_FIMM1205_ID0063_T20140212161518_R1_MO_PS_AUDIO.txt
    	  //STOIMM01_FIMM1205_ID0090_T20140212161518_R1_MT_CS_AUDIO.txt
    	  //STOIMM01_FIMM1205_ID0077_T20140212161518_R1_MT_PS_AUDIO.txt
    	  //STOIMM01_FIMM1205_ID0073_T20140212161518_R1_MO_PS_VIDEO.txt
    	  //STOIMM01_FIMM1205_ID0136_T20140212161518_R1_MO_CS_SMS.txt
    	  //STOIMM01_FIMM1205_ID0108_T20140212161518_R1_MO_PS_SMS.txt
    	  //STOIMM01_FIMM1205_ID0126_T20140212161518_R1_MT_CS_SMS.txt
    	  //STOIMM01_FIMM1205_ID0118_T20140212161518_R1_MT_PS_SMS.txt
    	  //STOIMM01_FIMM1205_ID0171_T20140212161518_R1_VIDEO_TO_AUDIO.txt
    	  //STOIMM01_FIMM1205_ID0155_T20140212161518_R1_AUDIO_TO_VIDEO.txt
       }
      
      public boolean binaryFileComparison(List lst1, List lst2){

    	  if(lst1.size() != lst2.size())
    		  return false;
    	  
        	  for(int i=0; i < lst1.size(); i++){
        		  if(lst1.get(i)!= lst2.get(i))
        			  return false;
        	  }

    	return true;  
      }
      
      private static void initRowHashMap(){	// Generic function to retrieve field values from CDR-CSV file
  	
    	  File file = null;
    	  OutputStream os = null;
        
    	  BufferedReader br = null;
    	  String line = "";
    	  String cvsSplitBy = ",";
    	  int NumberCDRsPerFile=1;
    	  Map<Integer, String> rowMap = new LinkedHashMap<>();
        
    	  LineNumberReaderImplementationClass ln =  new LineNumberReaderImplementationClass();
    	  int numberLinesCSV = ln.count();
  	  
    	  Random r = new Random();
    	  int iterations=0;
  	  
    	  try {
    		  br = new BufferedReader(new FileReader(strfileName));
               
    		  br.readLine();          		// Reading first row (header row)We don't care its values because we know the position that corresponds to each field
      	  
      	  while ((line = br.readLine()) != null) {    //Within this loop CDRs are written in a file an therefore files are being created.        	// Reading second, third, and so on.. rows (data rows). 
      		  columnValue=null;
      		  columnValue = line.split(cvsSplitBy);  	// use comma as separator
      		  
      		  {	// Initializer to avoid dragging values from first CDR into the next the next CDR when building multiple CDR DAT file 
      			rowMap.clear();
      			
      		  }
      		  

      		  for(int i=0; i < columnValue.length; i++){	// Population of LinkedHashMap with present fields from current line from CSV file
      			  if(columnValue[i].length() > 0){
      				  
      				  
      				  if(   ((i+1 == 17)||(i+1 == 18)|(i+1 == 26)) && (GetValueAsString("LOANI_accessType", columnValue[26-1]).equals("2"))){
      					  
      					  if(GetValueAsString("LOANI_accessType", columnValue[26-1]).equals("2")){
//      						  ps("Skiping put field:[" +(i+1) +"]");
      					  }
      				  }
      				  else{
      					  rowMap.put(i+1, columnValue[i]);
      				  }
     			  }
      		  }

      		  iterations ++;	// keep count of number of records parsed from CSV file

      		  if((iterations == 1)||(iterations % NumberCDRsPerFile == 1)||(NumberCDRsPerFile==1)){// Create a new file on the first iteration on the CSV file or when the number of CDRs per file is 1 or when the iteration number is 1 more than the multiple of the number of CDRs meaning one file has been completed and we are starting to write the cdr number one in a new file. 
      			
      			  switch(r.nextInt(4)){	// Random # Generator
      			  case 0:
      				  NumberCDRsPerFile = r.nextInt(70_000) + 1;	// Random number generator from 1 to 70K both inclusive
      				  break;
      			  case 1:
      				  NumberCDRsPerFile = r.nextInt(12_000 - 4_000 + 1) + 4_000;	// Random number generator from 4K to 12K both inclusive
      				  break;
      			  case 2:
      				  NumberCDRsPerFile = r.nextInt(10) + 1;	// Random number generator from 1 to 10 both inclusive
      				  break;
      			  case 3:
      				  NumberCDRsPerFile = r.nextInt(350_000) + 1; // Random number generator from 1 to 200K both inclusive
      				  break;
      			  }

      			  file = createFile(iterations, NumberCDRsPerFile, numberLinesCSV); // This section is executed only once as per file created
      			  os = new FileOutputStream (file);
      		  }
      		  
      		  byte[] cdrRecord = BuildIMM1205Record(rowMap);
	    	  writeFile(cdrRecord, NumberCDRsPerFile, iterations, numberLinesCSV, file, os);
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
      }
      
//      S[SWITCH_NAME]_F[FORMAT_NAME]_ID[FILE_ID]_T[FILE_DATE_TIME_STAMP]_R[NO_OF_RECORDS].DAT
//      Where:
//      •	SWITCH_NAME = MOIMM01, TOIMM01
//      •	FORMAT_NAME = IMM1205
//      •	FILE_ID = Incremental number (4 digits) set by EMM, from 0000 to 9999
//      •	FILE_DATE_TIME_STAMP = latest start time stamp (see section 4.3.1) of all the CDRs inside the file with the following format: YYYYMMDDHHMMSS (e.g. 20130628110309)
//      •	NO_OF_RECORDS = number of records per file (5 digits)

      
      
      public static File createFile(int iterations, int NumberCDRsPerFile, int numberLinesCSV){

    	  int numberRecords = 0;
    	  numberOfCdrsWrittenInFile=0;
    	  
    	  Calendar cal = Calendar.getInstance();
    	  cal.getTime();
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdHHmmss");
    	  inFileSeqNumber ++;
    	  
    	  String fileTime = "STOIMM01_FIMM1205_ID" + String.format("%04d",	inFileSeqNumber);
    	  fileTime += "_T"+sdf.format(cal.getTime());
    	  
    	  File imm_1205_dat = new File(fileTime);
    	  return imm_1205_dat;
      }
      
      public static void writeFile(byte[] bFile, int NumberCDRsPerFile, int iterations, int numberLinesCSV, File imm_1205_dat, OutputStream os) {	// Creates .DAT file writing bytes in hexadecimal directly to file
    	  DataOutputStream dos=null;
    	  numberOfCdrsWrittenInFile++;
    	  try{												// Write file
    		  dos = new DataOutputStream(os); 			// Knows how to write raw data but not to a file
    		  dos.write(bFile);                			// writing byte array into stream and implicitly to the file
    		  
    	  }catch(IOException e){
    		  e.printStackTrace();
    	  }
    	  finally{
    		  try{
    			  if((iterations % NumberCDRsPerFile == 0)|| iterations == numberLinesCSV) // close the file if any of the maxs is reached
    			  {
    				  dos.close();
    				  File renamed_imm_1205_dat = new File(imm_1205_dat.toString()+"_R" + String.format("%d", numberOfCdrsWrittenInFile) + ".DAT");
    				  imm_1205_dat.renameTo(renamed_imm_1205_dat);
    			  }
    		  }catch(NullPointerException e){
    		  }catch(IOException e){
    			  e.printStackTrace();
    		  }
    	  }
      }

      private static String GetValueAsString(String field, String columString) {	// Generic function to retrieve field values from CDR-CSV file

    	  BufferedReader br = null;
          String cvsSplitBy = ",";
          String tempField= field;
          
          switch(field){		// switch for subfields separated with pipes '|' to substitute actual field title in csv file 

          case "changeTime":	//20140212161518
          case "changeTimeNormalized":	//20140212191518
          case "LOMD-type":  //0          
          case "LOMD-codec": // ARM/8000
          case "LOMD-video_to_audio-type":
          case "LOMD-video_to_audio-codec":
          case "LOMD-video_to_audio-bandwidth":
        	  
          case "LOMD-bandwidth": // 30d = 1FHex
          case "mediaInitiatorFlag":
        	  field="list-Of-Normalized-Media-Components1";	// Actual field name in CSV file
          		break;
          	
          case "changeTime2":	//20140212161518
          case "changeTimeNormalized2":	//20140212191518
          case "LOMD-type2":  //0          
          case "LOMD-type3":  //0
          case "LOMD-codec2": // ARM/8000
          case "LOMD-codec3": // ARM/8000
          case "LOMD-bandwidth2": // 30d = 1FHex
          case "LOMD-bandwidth3": // 30d = 1FHex
          case "mediaInitiatorFlag2":
        	  field="list-Of-Normalized-Media-Components2";	// Actual field name in CSV file
          		break;
          	
          case "LOANI_accessNetworkInformation":
          case "LOANI_accessDomain":
          case "LOANI_accessType":
          case "LOANI_locationInfoType":
          case "LOANI_changeTime":
          case "LOANI_changeTimeNormalized":
        	  field="listOfAccessNetworkInformation1";	// Actual field name in CSV file
        	  break;

          case "LOANI_accessNetworkInformation2":	// 302-720-EAD6-54DD
          case "LOANI_accessDomain2":				// 0
          case "LOANI_accessType2":					// 0
          case "LOANI_locationInfoType2":			// 0
          case "LOANI_changeTime2":					// 20140212161652
          case "LOANI_changeTimeNormalized2":		// 20140212191652
        	  field="listOfAccessNetworkInformation2";	// Actual field name in CSV file
        	  break;
          
          case "LOSID_CTN_TYPE":
          case "LOSID_CTN_DATA":
        	  field="list-of-subscription-ID1";
        	  break;
        	  
          case "LOSID_IMSI_TYPE":
          case "LOSID_IMSI_DATA":
        	  field="list-of-subscription-ID2";
        	  break;
        	  
          case "LOSID_IMEI_TYPE":
          case "LOSID_IMEI_DATA":
        	  field="list-of-subscription-ID3";
        	  break;
          }
          
          if(field.equalsIgnoreCase("causeCodeEstablishedSession"))
        		  return "0";
          
    	  if(field.equalsIgnoreCase("mTASIdentifier")||field.equalsIgnoreCase("sCCASIdentifier")) // Hard coded literal exchange. Find out if this is OK with Engineering People or Jason
    		  field="nodeAddress";
    	  
    	  int columnNo=0;
    	  
    	  String[] returnValue = new String[1000];
        				  
    	  returnValue = columString.split("\\|");
        				  
    	  switch(tempField){
    	  case "changeTime":		//20140212161518"
    	  case "changeTime2":
    		  return returnValue[0];
        		          
    	  case "changeTimeNormalized":	//20140212191518
    	  case "changeTimeNormalized2":
    		  return returnValue[1];
        		          
    	  case "LOMD-type":  // 0=Audion    
    	  case "LOMD-type2":
    		  return returnValue[2];
        		        	  
    	  case "LOMD-type3":
    	  case "LOMD-video_to_audio-type":
    		  return returnValue[5];
        		        	  
    	  case "LOMD-video_to_audio-codec":
    		  return returnValue[6];
        		        	  
    	  case "LOMD-video_to_audio-bandwidth":
    		  return returnValue[7];
        		        	  
    	  case "LOMD-codec":  // AMR/8000  
    	  case "LOMD-codec2":
    		  return returnValue[3];     
        		        	  
    	  case "LOMD-codec3":
    		  int iii=0;
    		  for(String str: returnValue){
    			  iii++;
    		  }
    		  return returnValue[6];
        		        	  
    	  case "LOMD-bandwidth":  // 30  
    	  case "LOMD-bandwidth2":
    		  return returnValue[4];     
        		        	  
    	  case "LOMD-bandwidth3":
    		  return returnValue[7];
        		        	  
    	  case "mediaInitiatorFlag":  // 1  
    	  case "mediaInitiatorFlag2":
    		  return returnValue[11];   
        		        	  
    	  case "LOANI_accessNetworkInformation":
    	  case "LOANI_accessNetworkInformation2":
    		  return returnValue[0].replaceAll("-", ""); 	// Purging '-' in the string
        		        	  
    	  case "LOANI_accessDomain":
    	  case "LOANI_accessDomain2":
    		  return returnValue[1];

    	  case "LOANI_accessType":
    	  case "LOANI_accessType2":
    		  return returnValue[2];

    	  case "LOANI_locationInfoType":
    	  case "LOANI_locationInfoType2":
    		  return returnValue[3];
        		        	  
    	  case "LOANI_changeTime":
    	  case "LOANI_changeTime2":
    		  return returnValue[4];
        		        	  
    	  case "LOANI_changeTimeNormalized":
    	  case "LOANI_changeTimeNormalized2":
    		  return returnValue[5];

    	  case "LOSID_CTN_TYPE":		//region TAG: H'80 subscription-id-type
    		  return returnValue[0];

    	  case "LOSID_CTN_DATA":
    		  return returnValue[1];
        		        	  
    	  case "LOSID_IMSI_TYPE":
    		  return returnValue[0];

    	  case "LOSID_IMSI_DATA":
    		  return returnValue[1];
        		        	  
    	  case "LOSID_IMEI_TYPE":
    		  return returnValue[0];

    	  case "LOSID_IMEI_DATA":
    		  return returnValue[2];  	// data IMEI is the third element separated by pipe '|'
        		        	  							//end region TAG: H'80 subscription-id-type
    	  }
        			  
    	  return columString;											// Returning field value found in csv file

        }

        private static byte[] BuildIMM1205Record(Map<Integer, String> rowMap) {	// TLV ASN.1 BER format
        	Map<Integer, Command> methodMap = new LinkedHashMap<Integer, Command>();
  			byte[] bFile = new byte[50000];
	        Byte[] lbFile = new Byte[50000];
	        final List<Byte> fileData = new ArrayList<Byte>();
	        final List<Byte> structureData = new ArrayList<Byte>();;
  			
	        // Get a set of the entries
	        Set set = rowMap.entrySet();
	        // Get an iterator
	        Iterator iArray = set.iterator();
	        
	        final List<Integer> listKey= new ArrayList<>();
	        
	        while(iArray.hasNext()) {		// Creates an Array with the Map keys for special condition checking for some fields which content varies on the presence or absent of specific fields
		           Map.Entry meCount = (Map.Entry)iArray.next();
		           listKey.add((Integer)meCount.getKey());
		        }
	        
	        //region ===== Calculate and update record length H'BF 53 BER Tag for MMTelServiceRecord::MMTelRecord
	        fileData.add((byte)0xBF);
	        fileData.add((byte)0x53);
	        fileData.add((byte)0x80);	// Indefinite length
	        
	        methodMap.put(1, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	
  		        	//region TAG: H'80 recordType ASN TAG: 0
  		            //83 is the encoded type for MMTel records.
  		            fileData.add((byte)0x80);	//	ASN Tag
  		            fileData.add((byte) 0x1);	// 	Length
  		            fileData.add((byte) 0x53);	// 	Data
  		        }});
  		    
	        methodMap.put(3, new Command() {
	        	public void runCommand(String fieldValue) { 
  		        	
  		          //region TAG: H'82 sIP-Method ASN TAG: 2
	        		if(fieldValue.length()>0){
  		          		fileData.add((byte)0x82);	
  		          		AsnASCII(fileData, fieldValue);
  		          	}
  		        	}});

  		    	methodMap.put(4, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	//region TAG: H'83 role-of-node ASN TAG: 3
  		        	if(fieldValue.length()>0){
  		        		fileData.add((byte)0x83);	
  		        		AsnINT(fileData, fieldValue);
  		        	}
  		        }});

  		    	methodMap.put(5, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	//region TAG: H'BF 81 4C nodeAddress ASN TAG: 204
  		        	structureData.clear();// = new ArrayList<Byte>();

    		        if(GetValueAsString("LOANI_accessType", columnValue[26-1]).equals("2")){	// Do no attach Same value as nodeAddress is CDR is SMS type
    		        	//region TAG: H'84 sCCASIdentifier ASN TAG: 4
    		        	if(fieldValue.length() > 0) {
    		        		structureData.add((byte) 0x84);
    			          	AsnASCII(structureData, fieldValue);
    		        	}
    		        }
    		        else{
    		        	//region TAG: H'80 mTASIdentifier ASN TAG: 0
    		        	if(fieldValue.length() > 0) {
    		        		structureData.add((byte) 0x80);
    		        		AsnASCII(structureData, fieldValue);
    		        	}

    		        	//region TAG: H'81 sCCASIdentifier ASN TAG: 1
    		        	if(fieldValue.length() > 0) {
    		        		structureData.add((byte) 0x81);
    		        		AsnASCII(structureData, fieldValue);
    		        	}
    		        }

    		        if(structureData.size() > 0) {
    		        	fileData.add((byte)0xBF);
    		        	fileData.add((byte)0x81);
    		        	fileData.add((byte)0x4C);
    		        	fileData.add((byte)0x80); // Indefinite len
    		        	fileData.addAll(structureData); // Adding data prepare above
    		        	fileData.add((byte)0x00); //EOC (End Of Context)
    		        	fileData.add((byte)0x00); //EOC  There were two previous indefinite lengths, therefore there are two EOC here
    		        }
  		        }});

  		    	methodMap.put(6, new Command() {
  		    		public void runCommand(String fieldValue) { 
  		    			//region TAG: H'85 session-Id ASN TAG: 5
  		    			if(fieldValue.length() > 0) {
  		    				fileData.add((byte)0x85);
  		    				AsnASCII(fileData, fieldValue);
  		    			}
  		    		}});

  		    	methodMap.put(7, new Command() {
  		    		public void runCommand(String fieldValue) { 
  		    			//region TAG: H'A6 calling-Party-Address ASN TAG: 6
  		    			structureData.clear();
  		    			if(fieldValue.length() > 0) {
  		    				if(fieldValue.toLowerCase().startsWith("sip:")) //Add ASN.1 TAG: H'80 for SIP
  		    					structureData.add((byte) 0x80);
  		    				else if(fieldValue.toLowerCase().startsWith("tel:")) //Add ASN.1 TAG: H'81 for TEL
  		    					structureData.add((byte) 0x81);	
  		    				AsnASCII(structureData, fieldValue);
  		    			}

  		    				if(structureData.size() > 0) {
  		    					fileData.add((byte) 0xA6);
  		    					fileData.add((byte) 0x80); // Infinit len
  		    					fileData.addAll(structureData);
  		    					fileData.add((byte) 0x00);
  		    					fileData.add((byte) 0x00);
  		    				}
  		    		}});

  		    	methodMap.put(8, new Command() {
  		    		public void runCommand(String fieldValue) { 
  		        //region TAG: H'A7 called-Party-Address ASN TAG: 7
  		    			structureData.clear();
  		    			if(fieldValue.length() > 0) {
  		    				if(fieldValue.toLowerCase().startsWith("sip:")) //Add ASN.1 TAG: H'80 for SIP
  		    					structureData.add((byte)0x80);
  		    				else if(fieldValue.toLowerCase().startsWith("tel:")) //Add ASN.1 TAG: H'81 for TEL
  		    					structureData.add((byte)0x81);
  		    				AsnASCII(structureData, fieldValue);
  		    			}

  		    			if(structureData.size() > 0) {
  		    				fileData.add((byte)0xA7);
  		    				fileData.add((byte)0x80); // Infinit len
  		    				fileData.addAll(structureData);
  		    				fileData.add((byte)0x00);
  		    				fileData.add((byte)0x00);
  		    			}
  		        }});

  		    	methodMap.put(9, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        //region TAG: H'89 serviceRequestTimeStamp ASN TAG: 9
  		          if(fieldValue.length() > 0) {
  		          	fileData.add((byte)0x89);
  		          	AsnASCII(fileData, fieldValue);
  		          }
  		        }});

  		    	methodMap.put(10, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        //region TAG: H'8A serviceDeliveryStartTimeStamp ASN TAG: 10
  		        if(fieldValue.length() > 0) {
  		          	fileData.add((byte)0x8A);
  		          	AsnASCII(fileData, fieldValue);
  		        }
  		        }});

  		    	methodMap.put(11, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'8B serviceDeliveryEndTimeStamp ASN TAG: 11
  		        	if(fieldValue.length() > 0) {
  		          	fileData.add((byte)0x8B);
  		          	AsnASCII(fileData, fieldValue);
  		          }
  		        }});

  		    	methodMap.put(12, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'8C recordOpeningTime ASN TAG: 12
  		          if(fieldValue.length() > 0) {
  		          	fileData.add((byte)0x8C);
  		          	AsnASCII(fileData, fieldValue);
  		          }
  		        }});

  		    	methodMap.put(13, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'8D recordClosureTime ASN TAG: 13
  		          if(fieldValue.length() > 0) {
  		          	fileData.add((byte)0x8D);
  		          	AsnASCII(fileData, fieldValue);
  		          }
  		        }});

  		    	methodMap.put(14, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'9F 6D serviceRequestTimeStampNormalized ASN TAG: 109
  		          if(fieldValue.length() > 0) {
  		          	fileData.add((byte)0x9F);
  		          	fileData.add((byte)0x6D);
  		          	AsnASCII(fileData, fieldValue);
  		          }
  		        }});

  		    	methodMap.put(15, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	//region TAG: H'9F 6E serviceDeliveryStartTimeStampNormalized ASN TAG: 110
  		        	if(fieldValue.length() > 0) {
  		        		fileData.add((byte)0x9F);
  		        		fileData.add((byte)0x6E);
  		        		AsnASCII(fileData, fieldValue);
  		        	}
  		        }});

  		    	methodMap.put(16, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'9F 6F serviceDeliveryEndTimeStampNormalized ASN TAG: 111
  		        	if(fieldValue.length() > 0) {
  		        		fileData.add((byte)0x9F);
  		        		fileData.add((byte)0x6F);
  		        		AsnASCII(fileData, fieldValue);
  		        	}
  		        }});

  		    	methodMap.put(17, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'9F 81 4E chargableDuration ASN TAG: 206	// Not displaying write data
  		        	if(fieldValue.length() > 0) {
  		        		fileData.add((byte)0x9F);
  		        		fileData.add((byte)0x81);
  		        		fileData.add((byte)0x4E);
  		        		AsnINT(fileData, fieldValue);
  		        	}
  		        }});

  		    	methodMap.put(18, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	//region TAG: H'9F 81 4F timeFromSipRequestToStartOfCharging ASN TAG: 207	
  		        	if(fieldValue.length() > 0) {
  		        		fileData.add((byte)0x9F);
  		        		fileData.add((byte)0x81);
  		        		fileData.add((byte)0x4F);
  		        		AsnINT(fileData, fieldValue);
  		        	}
  		        }});

  		    	methodMap.put(19, new Command() {	// Not in use for IMM1205 CDR but may be needed for future CDR implementations
  		    		public void runCommand(String fieldValue) { 
		        }});

		    	methodMap.put(20, new Command() {	// Not in use for IMM1205 CDR but may be needed for future CDR implementations
		    		public void runCommand(String fieldValue) { 
		        }});

  		    	methodMap.put(21, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'90 partialOutputRecordNumber ASN TAG: 16
  		          //This was renamed from recordSequenceNumber as per email from E///
  		          // 9/6/2013 -> From: Veronique Pelchat 
  		          if(fieldValue.length() > 0) {
  		          fileData.add((byte)0x90);
  		          AsnINT(fileData, fieldValue);
  		          }
  		        }});

  		    	methodMap.put(22, new Command() {
  		    		public void runCommand(String fieldValue) { 
  		    			//region TAG: H'B1 causeForRecordClosing ASN TAG: 17	
  		    			structureData.clear();// = new ArrayList<Byte>();
  		    			//region TAG: H'82 causeCodeEstablishedSession
  		    			if(fieldValue.length() > 0) {
  		    				structureData.add((byte)0x30); // Universail 16
  		    				structureData.add((byte)0x80); // Infinite len
  		    				structureData.add((byte)0x82); // Tag[2]
  		    				AsnINT(structureData, "0"); // '0'  I am hardcoding it to '0' as Jason did
  		    				structureData.add((byte)0x0);
  		    				structureData.add((byte)0x0);
  		    			}

  		    			if(structureData.size() > 0) {
  		    				fileData.add((byte)0xB1); // Tag[17]
  		    				fileData.add((byte)0x80); // Infinit len
  		    				fileData.addAll(structureData);
  		    				fileData.add((byte)0x00);
  		    				fileData.add((byte)0x00);
  		          }
  		        }});

  		    	methodMap.put(23, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	//region TAG: H'92 Incomplete-CDR-Indication OPTIONAL ASN TAG: 18	// This field is not being displayed right
  		        	if(fieldValue.trim().length() > 0) {
  		        		byte R0 = 0, R1 = 0, R2 = 0, R200 = 0, R201 = 0, R202 = 0, R203 = 0;
  		        		if(fieldValue == "0") R0 = 1;
  		        		else if(fieldValue == "1") R1 = 1;
  		        		else if(fieldValue == "2") R2 = 1;
  		        		else if(fieldValue == "200") R200 = 1;
  		        		else if(fieldValue == "201") R201 = 1;
  		        		else if(fieldValue == "202") R202 = 1;
  		        		else if(fieldValue == "203") R203 = 1;
  		        		fileData.add((byte)0xB2); // Tag code
	  		          fileData.add((byte)0x80); // Infinit len
	  		          fileData.add((byte)0x80); // Tag [0]
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R0); // boolean (0/1) val
	  		          fileData.add((byte)0x81); // Tag [1]
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R1); // boolean (0/1) val
	  		          fileData.add((byte)0x82); // Tag [2]
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R2); // boolean (0/1) val
	  		          fileData.add((byte)0x9F); // Tag [200]
	  		          fileData.add((byte)0x81);
	  		          fileData.add((byte)0x48);
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R200); // boolean (0/1) val
	  		          fileData.add((byte)0x9F); // Tag [201]
	  		          fileData.add((byte)0x81);
	  		          fileData.add((byte)0x49);
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R201); // boolean (0/1) val
	  		          fileData.add((byte)0x9F); // Tag [202]
	  		          fileData.add((byte)0x81);
	  		          fileData.add((byte)0x4A);
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R202); // boolean (0/1) val
	  		          fileData.add((byte)0x9F); // Tag [203]
	  		          fileData.add((byte)0x81);
	  		          fileData.add((byte)0x4B);
	  		          fileData.add((byte)0x01); // 1 Len
	  		          fileData.add((byte)R203); // boolean (0/1) val
	  		          fileData.add((byte)0x00); // End of [18]
	  		          fileData.add((byte)0x00);
  		        	}
  		        }});

  		    	methodMap.put(24, new Command() {
  		    		public void runCommand(String fieldValue) { 
  		    			//region TAG: H'93 iMS-Charging-Identifier ASN TAG: 19	//PRINTING SUCCESSFULLY UNTIL HERE
  		    			if(fieldValue.length() > 0) {
  		    				fileData.add((byte)0x93);
  		    				AsnASCII(fileData, fieldValue);
  		    			}
  		    		}});

  		    	methodMap.put(25, new Command() {				// No used by IMM1205 scenarios CDRs created for Fusion Project. Nevertheless is created for future implementaion
  		    		public void runCommand(String fieldValue) { 

  		    		}});
		        
  		    	final List<Byte> CodecAudio = new ArrayList<Byte>();
		        final List<Byte> CodecVideo = new ArrayList<Byte>();	// I need an CSV and CDR example to implement this code. Currently this code is adding nothing to the .DAT file
		        
		        methodMap.put(26, new Command() {
		        	public void runCommand(String fieldValue) { 
		        		CodecAudio.clear();
		        		CodecVideo.clear();
		        		String strNormalizedMediaComponent1="";
		        		//region TAG: H'BF 70 list-Of-normalized-media-components ASN TAG: 112
		        		//region CodecAudio
		        		strNormalizedMediaComponent1 = GetValueAsString("LOMD-type", fieldValue);
  		          
		        		if(strNormalizedMediaComponent1.length() > 0) {
		        			//region TAG: H'80 type [112][16][2][16][0]
		        			CodecAudio.add((byte)0x30); // [112][16][2][16]
		        			CodecAudio.add((byte)0x80); // Inf len
		        			CodecAudio.add((byte)0x80); // [112][16][2][16][0]
		        			AsnINT_LOMD(CodecAudio, strNormalizedMediaComponent1);	
		        			//region TAG: H'81 codec [112][16][2][16][1]
		        			strNormalizedMediaComponent1 = GetValueAsString("LOMD-codec", fieldValue);

		        			if(strNormalizedMediaComponent1.length() > 0) {
		        				CodecAudio.add((byte)0x81); // [112][16][2][16][1]
		        				AsnASCII(CodecAudio, strNormalizedMediaComponent1);
		        			}
  		          
		        			//region TAG: H'82 bandwidth [112][16][2][16][2]
		        			strNormalizedMediaComponent1 = GetValueAsString("LOMD-bandwidth", fieldValue);
  		          
		        			if(strNormalizedMediaComponent1.length() > 0) {
		        				CodecAudio.add((byte)0x82); // [112][16][2][16][2]
		        				AsnINT_LOMD(CodecAudio, strNormalizedMediaComponent1);	// We use AsnINT and Jason uses AsnHEX
		        			}
  		          }
		        		
		        		if(
		        				((GetValueAsString("LOMD-type", columnValue[26-1]).equals("1")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("0"))) &&
		        				(    (GetValueAsString("LOMD-video_to_audio-type", columnValue[26-1]).length() > 0) &&                                                                                
		        						(GetValueAsString("LOMD-video_to_audio-codec", columnValue[26-1]).length() > 0) &&
		        								(GetValueAsString("LOMD-video_to_audio-bandwidth", columnValue[26-1]).length() > 0 ))
		        				)
		        				{	// If list-Of-Normalized-Media-Components1[2]==1 (Video) and list-Of-Normalized-Media-Components2[2]==0 (Audio)  means Audio to Video case and we need to add this field

	        			//CodecVideo.add((byte)0x00); // End [112][16][2][16]
	        			//CodecVideo.add((byte)0x00); // End [112][16][2][16]
  		        		CodecVideo.add((byte)(0x30)); // [112][16]
  		        		CodecVideo.add((byte)(0x80)); // Inf len
  		        		String codecVideoString_LOMD_video_to_audio_type="";
  		        		codecVideoString_LOMD_video_to_audio_type = GetValueAsString("LOMD-video_to_audio-type", fieldValue);

		  		        if(codecVideoString_LOMD_video_to_audio_type.length() > 0) {
		  		        	CodecVideo.add((byte)(0x80));
		  		        	AsnINT_LOMD(CodecVideo, codecVideoString_LOMD_video_to_audio_type);
		  		        }

		  		        String codecVideoString_LOMD_video_to_audio_codec="";
		  		        codecVideoString_LOMD_video_to_audio_codec = GetValueAsString("LOMD-video_to_audio-codec", fieldValue);

		  		        if(codecVideoString_LOMD_video_to_audio_codec.length() > 0) {
		  		        	CodecVideo.add((byte)(0x81));
  		  		        	AsnASCII(CodecVideo, codecVideoString_LOMD_video_to_audio_codec);
		  		        }
	  		        	
		  		        String codecVideoString_LOMD_video_to_audio_bandwidth="";
		  		        codecVideoString_LOMD_video_to_audio_bandwidth = GetValueAsString("LOMD-video_to_audio-bandwidth", fieldValue);

  		  		        if(codecVideoString_LOMD_video_to_audio_bandwidth.length() > 0) {
  		  		        	CodecVideo.add((byte)(0x82));
  		  		        	AsnINT_LOMD(CodecVideo, codecVideoString_LOMD_video_to_audio_bandwidth);
  		  		        }
  		  		        	
  		  		        //String codecVideoString_LOMD_video_to_audio_mediaInitiatorFlag=""; // Maybe this field is putting garbage on the second CDR CHECK THAT OUT BUT PUTTING A FIX VALUE
  		  		        //codecVideoString_LOMD_video_to_audio_mediaInitiatorFlag = GetValueAsString("LOMD-video_to_audio-mediaInitiatorFlag", fieldValue);
	 		        	CodecVideo.add((byte)0x00); // End [112][16][2][16]	It should not be appended here if field 28 is present
	  		        	CodecVideo.add((byte)0x00); // End [112][16][2][16]
		        		}
  		          
		        		if((listKey.contains(27) == false) && (listKey.contains(28) == false)){	// If there are not other list_Of_Normalized_Media_Components fields pending I will finish the field here. If not I need to finish it after the other fields are processed since they are part of one whole field.
  		        	
		        			CodecAudio.add((byte)0x00); // End [112][16][2][16]
		        			CodecAudio.add((byte)0x00); // End [112][16][2][16]
		        			String mediaInitiatorFlag ="";
		        			mediaInitiatorFlag = GetValueAsString("mediaInitiatorFlag", fieldValue);
  	  		    	
		        			//region Normalized-Media-Components 1
		        			List<Byte> list_Of_Normalized_Media_Components = new ArrayList<Byte>();
		        			String ChangTime ="";
		        			ChangTime = GetValueAsString("changeTime", fieldValue);
  	  		    	
		        			if(CodecAudio.size() > 0){
		        				// TAG: H'80 changeTime [112][16][0]
		        				list_Of_Normalized_Media_Components.add((byte)(0x30)); // [112][16]
		        				list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
		        				list_Of_Normalized_Media_Components.add((byte)(0x80)); // [112][16][0] changeTime Tag
		        				AsnASCII(list_Of_Normalized_Media_Components, ChangTime); // changeTime value same as StartTime

		        				// TAG: H'81 changeTimeNormalized [112][16][1]
		        				list_Of_Normalized_Media_Components.add((byte)(0x81)); // [112][16][1]
		        				AsnASCII(list_Of_Normalized_Media_Components, GetValueAsString("changeTimeNormalized", fieldValue));
		  	  		           // TAG: H'A2 list-of-media-description [112][16][2]
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0xA2)); // [112][16][2]
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
		  	  		           list_Of_Normalized_Media_Components.addAll(GetValueAsString("RecDesc", fieldValue).indexOf("Media Video") > -1 ? CodecVideo : CodecAudio);
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16][2]
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16][2]
  	  		           
		  	  		           // TAG: H'83 mediaInitiatorFlag [112][16][3]
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0x83)); // [112][16][3]
		  	  		           AsnINT(list_Of_Normalized_Media_Components, mediaInitiatorFlag);
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16]
		  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16]
		        			}
  	  		           
		        			//region Normalized-Media-Components 2		// No need to implement changeTime2 here (I need a cdr file and respective csv file)
		        			List<Byte> list_Of_Normalized_Media_Components2 = new ArrayList<Byte>();

		        			//region Normalized-Media-Components 3	// No need to implement changeTime3 here (I need a cdr file and respective csv file)
		        			List<Byte> list_Of_Normalized_Media_Components3 = new ArrayList<Byte>();
  	  		    	
		        			// Insert main tag [112]
		        			if(list_Of_Normalized_Media_Components.size() > 0 || list_Of_Normalized_Media_Components2.size() > 0) {
		        				fileData.add((byte)0xBF); // [112]
		        				fileData.add((byte)0x70); // [112]
		        				fileData.add((byte)0x80); // Inf len
		        				if(list_Of_Normalized_Media_Components.size() > 0)
		        					fileData.addAll(list_Of_Normalized_Media_Components);
		        				if(list_Of_Normalized_Media_Components2.size() > 0)
		        					fileData.addAll(list_Of_Normalized_Media_Components2);
		        				if(list_Of_Normalized_Media_Components3.size() > 0)
		        					fileData.addAll(list_Of_Normalized_Media_Components3);
		        				fileData.add((byte)0x00); // End [112]
		        				fileData.add((byte)0x00); // End [112]
		        			}
		        		}
		        	}});

		        methodMap.put(27, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	

  		        	//region CodecVideo
  		        	
  		        	String changeTime2 ="";
  		        	changeTime2 = GetValueAsString("changeTime2", fieldValue);
  		        	String changeTimeNormalized2="";
  		        	changeTimeNormalized2 = GetValueAsString("changeTimeNormalized2", fieldValue);
	  	  		    
  		        	String strNormalizedMediaComponent2_LOMD_type2 = "";
  		        	strNormalizedMediaComponent2_LOMD_type2 = GetValueAsString("LOMD-type2", fieldValue);
  		        	
	  	  		    if(strNormalizedMediaComponent2_LOMD_type2.length() > 0) {
	  	  		    	//if((GetValueAsString("LOMD-type", columnValue[26-1]).equals("1")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("0"))){	// If list-Of-Normalized-Media-Components1[2]==0 (Audio) and list-Of-Normalized-Media-Components2[2]==1 (Video)  means Audio to Video type and we need to add this field
		        		if(
		        				((GetValueAsString("LOMD-type", columnValue[26-1]).equals("1")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("0"))) &&
		        				(    (GetValueAsString("LOMD-video_to_audio-type", columnValue[26-1]).length() > 0) &&                                                                                
		        						(GetValueAsString("LOMD-video_to_audio-codec", columnValue[26-1]).length() > 0) &&
		        								(GetValueAsString("LOMD-video_to_audio-bandwidth", columnValue[26-1]).length() > 0 ))
		        				)
		        				{
	  	  		    		CodecVideo.add((byte)0x00); // End [112][16][2][16]
	  	  		    		CodecVideo.add((byte)0x00); // End [112][16][2][16]
	  		        		//CodecVideo.add((byte)0x83); // End [112][16][2][16]
	  		        		
	  	  		    		//TAG: H'83 mediaInitiatorFlag [112][16][3]
	  	  		    		CodecVideo.add((byte)(0x83)); // [112][16][3]
	   	  		           	AsnINT(CodecVideo, GetValueAsString("mediaInitiatorFlag", columnValue[26-1]));
	   	  		           	CodecVideo.add((byte)(0x00)); // End [112][16]
	   	  		           	CodecVideo.add((byte)(0x00)); // End [112][16]

	  		        		CodecVideo.add((byte)(0x30)); // [112][16][2][16]
	  		        		CodecVideo.add((byte)(0x80)); // Inf len

	  		        		// TAG: H'80 changeTime [112][16][0]
	  		        		CodecVideo.add((byte)(0x80)); // [112][16][0] changeTime Tag
   	  	  		           	AsnASCII(CodecVideo, changeTime2); // changeTime value same as StartTime

   	  	  		           // TAG: H'81 changeTimeNormalized [112][16][1]
   	  	  		           CodecVideo.add((byte)(0x81)); // [112][16][1]
   	  	  		           AsnASCII(CodecVideo, GetValueAsString("changeTimeNormalized", fieldValue));

   	  	  		           // TAG: H'A2 list-of-media-description [112][16][2]
   	  	  		           CodecVideo.add((byte)(0xA2)); // [112][16][2]
   	  	  		           CodecVideo.add((byte)(0x80)); // Inf len
 		        		}
  		        		
	  	  		    	//region TAG: H'80 type [112][16][2][16][0]
  		        		CodecVideo.add((byte)(0x30)); // [112][16][2][16]
  		        		CodecVideo.add((byte)(0x80)); // Inf len
  		        		CodecVideo.add((byte)(0x80)); // [112][16][2][16][0]
  		        		AsnINT_LOMD(CodecVideo, strNormalizedMediaComponent2_LOMD_type2);
  		        		
  		        		//region TAG: H'81 codec [112][16][2][16][1]
  		        		String strNormalizedMediaComponent2_LOMD_codec2="";
  		        		strNormalizedMediaComponent2_LOMD_codec2 = GetValueAsString("LOMD-codec2", fieldValue);
  		        		
  		        		if(strNormalizedMediaComponent2_LOMD_codec2.length() > 0) {
  		        			CodecVideo.add((byte)(0x81)); // [112][16][2][16][1]
  		        			AsnASCII(CodecVideo, strNormalizedMediaComponent2_LOMD_codec2);
  		        		}

  		        		//region TAG: H'82 bandwidth [112][16][2][16][2]
  		        		String strNormalizedMediaComponent2_LOMD_bandwidth2="";
  		        		strNormalizedMediaComponent2_LOMD_bandwidth2 = GetValueAsString("LOMD-bandwidth2", fieldValue);

  		        		if(strNormalizedMediaComponent2_LOMD_bandwidth2.length() > 0) {
  		        			CodecVideo.add((byte)(0x82)); // [112][16][2][16][2]
  		        			AsnINT_LOMD(CodecVideo, strNormalizedMediaComponent2_LOMD_bandwidth2);	
  		        		}
  		        		
  		        		if((GetValueAsString("LOMD-type", columnValue[26-1]).equals("0")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("1"))){	// If list-Of-Normalized-Media-Components1[2]==0 (Audio) and list-Of-Normalized-Media-Components2[2]==1 (Video)  means Audio to Video type and we need to add this field
  		        			String codecVideoString_LOMD_type3 ="";
  		        			CodecVideo.add((byte)0x00); // End [112][16][2][16]
  		        			CodecVideo.add((byte)0x00); // End [112][16][2][16]
	  		        		CodecVideo.add((byte)(0x30)); // [112][16]
	  		        		CodecVideo.add((byte)(0x80)); // Inf len
	  		        		codecVideoString_LOMD_type3 = GetValueAsString("LOMD-type3", fieldValue);

  		  		        	if(codecVideoString_LOMD_type3.length() > 0) {
  		  		        		CodecVideo.add((byte)(0x80));
  		  		        		AsnINT_LOMD(CodecVideo, codecVideoString_LOMD_type3);
  		  		        	}
	  		        		
  		  		        	String codecVideoString_LOMD_codec3="";
  		  		        	codecVideoString_LOMD_codec3 = GetValueAsString("LOMD-codec3", fieldValue);

  		  		        	if(codecVideoString_LOMD_codec3.length() > 0) {
  		  		        		CodecVideo.add((byte)(0x81));
	  		  		        	AsnASCII(CodecVideo, codecVideoString_LOMD_codec3);
  		  		        	}
		  		        		
  		  		        	String codecVideoString_LOMD_bandwidth3="";
  		  		        codecVideoString_LOMD_bandwidth3 = GetValueAsString("LOMD-bandwidth3", fieldValue);

	  		  		        if(codecVideoString_LOMD_bandwidth3.length() > 0) {
	  		  		        	CodecVideo.add((byte)(0x82));
	  		  		        	AsnINT_LOMD(CodecVideo, codecVideoString_LOMD_bandwidth3);
	  		  		        }
	  		  		        	
	  		  		        CodecVideo.add((byte)0x00); // End [112][16][2][16]	It should not be appended here if field 28 is present
		  		        	CodecVideo.add((byte)0x00); // End [112][16][2][16]
  		        		}
  		        		//CodecVideo.add((byte)0x00); // End [112][16][2][16]	It should not be appended here if field 28 is present
  		        		//CodecVideo.add((byte)0x00); // End [112][16][2][16]
  		        	}
  		        	
	  	  		    if(listKey.contains(28) == false){
	  	  		    	if((GetValueAsString("LOMD-type", columnValue[26-1]).equals("0")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("1"))==false){	// If list-Of-Normalized-Media-Components1[2]==0 (Audio) and list-Of-Normalized-Media-Components2[2]==1 (Video)  means Audio to Video type and we need to add this field
	  	  		    		CodecAudio.add((byte)0x00); // End [112][16][2][16]
	  		        		CodecAudio.add((byte)0x00); // End [112][16][2][16]
  		        		}

	  	  		    	String mediaInitiatorFlag2 = GetValueAsString("mediaInitiatorFlag2", fieldValue);
  	  	  		    	
  	  	  		    	//region Normalized-Media-Components 1
	  	  		    	List<Byte> list_Of_Normalized_Media_Components = new ArrayList<Byte>();
 	  	  		        
	  	  		    	if(CodecAudio.size() > 0){
	  	  		    		// TAG: H'80 changeTime [112][16][0]
  	  	  		           	list_Of_Normalized_Media_Components.add((byte)(0x30)); // [112][16]
  	  	  		           	list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
  	  	  		           	list_Of_Normalized_Media_Components.add((byte)(0x80)); // [112][16][0] changeTime Tag
  	  	  		    	 	AsnASCII(list_Of_Normalized_Media_Components, GetValueAsString("changeTime", columnValue[26-1])); // changeTime value same as StartTime from Field_26. Please note we are on Filed_27 right now

  	  	  		    	 	// TAG: H'81 changeTimeNormalized [112][16][1]
  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x81)); // [112][16][1]
  	  	  		           AsnASCII(list_Of_Normalized_Media_Components, GetValueAsString("changeTimeNormalized", columnValue[26-1]));
  	  	  		           
  	  	  		           // TAG: H'A2 list-of-media-description [112][16][2]
  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0xA2)); // [112][16][2]
  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
  	  	  		           list_Of_Normalized_Media_Components.addAll(GetValueAsString("RecDesc", fieldValue).indexOf("Media Video") > -1 ? CodecVideo : CodecAudio);
  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16][2]
  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16][2]
  	  	  		     
  	  	  		           if((GetValueAsString("LOMD-type", columnValue[26-1]).equals("0")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("1"))){	// If list-Of-Normalized-Media-Components1[2]==0 (Audio) and list-Of-Normalized-Media-Components2[2]==1 (Video)  means Audio to Video type and we need to add this field 
  	  	  		        	   // TAG: H'83 mediaInitiatorFlag [112][16][3]
  	  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00));
  	  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x00));
  	  	  		        	   list_Of_Normalized_Media_Components.add((byte)(0x83)); // [112][16][3] 	// commented for now, ERS code shows that it appends it, actual CDR this field is not there
  	  	  		        	   AsnINT(list_Of_Normalized_Media_Components, GetValueAsString("mediaInitiatorFlag", columnValue[26-1]));			// commented for now, ERS code shows that it appends it, actual CDR this field is not there
  	  	  		        	   list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16]
  	  	  		        	   list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16]

	  	  		        	   // TAG: H'80 changeTime2 [112][16][0]
	  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x30)); // [112][16]
	  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
	  	  	  		           list_Of_Normalized_Media_Components.add((byte)(0x80)); // [112][16][0]
  	  	  		           }
	  	  		    	}

	  	  		    	//region Normalized-Media-Components 2
	  	  		    	List<Byte> list_Of_Normalized_Media_Components2 = new ArrayList<Byte>();
 	  	  		           
	  	  		    	if(CodecVideo.size() > 0){
	  	  		    		if((GetValueAsString("LOMD-type", columnValue[26-1]).equals("0")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("1"))){	// If list-Of-Normalized-Media-Components1[2]==0 (Audio) and list-Of-Normalized-Media-Components2[2]==1 (Video)  means Audio to Video type and we need to add this field
	  	  		    			AsnASCII(list_Of_Normalized_Media_Components2, changeTime2);	// commented for now, ERS code shows that it appends it, actual CDR this field is not there
  	  	  		        		list_Of_Normalized_Media_Components2.add((byte)(0x81)); // [112][16][1]
  	  	  		        		AsnASCII(list_Of_Normalized_Media_Components2, changeTime2);
  	  	  		        		list_Of_Normalized_Media_Components2.add((byte)(0xA2)); // [112][16][2] In ERS this sub-fields are not being appened so for now we comment them to match our CDRs with ERS
  	  	  		        		list_Of_Normalized_Media_Components2.add((byte)(0x80)); // Inf len
	  	  		    		}
  	   	  	  		       	  	  
	  	  		    		list_Of_Normalized_Media_Components2.addAll(GetValueAsString("RecDesc2", fieldValue).indexOf("Media Video") > -1 && CodecAudio.size() > 0 ? CodecAudio : CodecVideo);
  	  	  		     		list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16][2]
  	  	  		     		list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16][2]
  	  	  		     
   	  	  		           //if((GetValueAsString("LOMD-type", columnValue[26-1]).equals("1")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("0"))){	// If list-Of-Normalized-Media-Components1[2]==0 (Audio) and list-Of-Normalized-Media-Components2[2]==1 (Video)  means Audio to Video type and we need to add this field
  			        		if(	// Need to let in MO PS Video as well
  			        				((GetValueAsString("LOMD-type", columnValue[26-1]).equals("1")) && (GetValueAsString("LOMD-type2", columnValue[27-1]).equals("0"))) /*&&
  			        				(    (GetValueAsString("LOMD-video_to_audio-type", columnValue[26-1]).length() > 0) &&                                                                                
  			        						(GetValueAsString("LOMD-video_to_audio-codec", columnValue[26-1]).length() > 0) &&
  			        								(GetValueAsString("LOMD-video_to_audio-bandwidth", columnValue[26-1]).length() > 0 ))*/
  			        				)
  			        				{
  	  	  		        	   list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16]	patch here no so good
  	  	  		        	   list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16]	patch here no so good
  	  	  		           }    
  	  	  		     		
  	  	  		           list_Of_Normalized_Media_Components2.add((byte)(0x83)); // [112][16][3]
  	  	  		           AsnINT(list_Of_Normalized_Media_Components2, mediaInitiatorFlag2);
  	  	  		           list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16]
  	  	  		           list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16]
	  	  		    	}
  	  	  		           
	  	  		    	//region Normalized-Media-Components 3	// No need to implement changeTime3 here
	  	  		    	List<Byte> list_Of_Normalized_Media_Components3 = new ArrayList<Byte>();

  	  	  		        // Insert main tag [112]
  	  	  		        if(list_Of_Normalized_Media_Components.size() > 0 || list_Of_Normalized_Media_Components2.size() > 0) {
  	  	  		        	fileData.add((byte)0xBF); // [112]
  	  	  		        	fileData.add((byte)0x70); // [112]
  	  	  		        	fileData.add((byte)0x80); // Inf len
  	  	  		        	if(list_Of_Normalized_Media_Components.size() > 0)
  	  	  		        		fileData.addAll(list_Of_Normalized_Media_Components);
  	  	  		        	if(list_Of_Normalized_Media_Components2.size() > 0)
  	  	  		        		fileData.addAll(list_Of_Normalized_Media_Components2);
  	  	  		        	if(list_Of_Normalized_Media_Components3.size() > 0)
  	  	  		        		fileData.addAll(list_Of_Normalized_Media_Components3);
  	  	  		        	fileData.add((byte)0x00); // End [112]
  	  	  		        	fileData.add((byte)0x00); // End [112]
  	  	  		        }
  	  		        }
  		        }});
		          
  		    	methodMap.put(28, new Command() {
  		        public void runCommand(String fieldValue) { 
  		        	String tempData = GetValueAsString("LOMD-type2", fieldValue);
  		        	if(tempData != "") {
  		        		// Codec 3
  		        		CodecVideo.add((byte)(0x30)); // [112][16][2][16]
  		        		CodecVideo.add((byte)(0x80)); // Inf len
  		        		CodecVideo.add((byte)(0x80)); // [112][16][2][16][0]
  		        		AsnINT(CodecVideo, GetValueAsString("LOMD-type3", fieldValue));
  		        		CodecVideo.add((byte)(0x81)); // [112][16][2][16][1]
  		        		AsnASCII(CodecVideo, GetValueAsString("LOMD-codec3", fieldValue));
  		        		CodecVideo.add((byte)(0x82)); // [112][16][2][16][2]
  		        		AsnINT(CodecVideo, GetValueAsString("LOMD-bandwidth3", fieldValue));
  		        		CodecVideo.add((byte)(0x00)); // End [112][16][2][16]
  		        		CodecVideo.add((byte)(0x00)); // End [112][16][2][16]
  		        	}

  		        	String mediaInitiatorFlag = GetValueAsString("mediaInitiatorFlag", fieldValue);
  		        	//region Normalized-Media-Components 1
  		        	List<Byte> list_Of_Normalized_Media_Components = new ArrayList<Byte>();
  		        	String ChangTime = GetValueAsString("changeTime", fieldValue);

  		        	if(CodecAudio.size() > 0){
  		        		//TAG: H'80 changeTime [112][16][0]
  		        		list_Of_Normalized_Media_Components.add((byte)(0x30)); // [112][16]
  		        		list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
  		        		list_Of_Normalized_Media_Components.add((byte)(0x80)); // [112][16][0] changeTime Tag
  		        		AsnASCII(list_Of_Normalized_Media_Components, ChangTime); // changeTime value same as StartTime

  		        		// TAG: H'81 changeTimeNormalized [112][16][1]
  		        		list_Of_Normalized_Media_Components.add((byte)(0x81)); // [112][16][1]
  		        		AsnASCII(list_Of_Normalized_Media_Components, GetValueAsString("changeTimeNormalized", fieldValue));
  		        		// TAG: H'A2 list-of-media-description [112][16][2]
  		        		list_Of_Normalized_Media_Components.add((byte)(0xA2)); // [112][16][2]
  		        		list_Of_Normalized_Media_Components.add((byte)(0x80)); // Inf len
  		        		list_Of_Normalized_Media_Components.addAll(GetValueAsString("RecDesc", fieldValue).indexOf("Media Video") > -1 ? CodecVideo : CodecAudio);
  		        		list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16][2]
  		        		list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16][2]

  		        		// TAG: H'83 mediaInitiatorFlag [112][16][3]
  		        		//list_Of_Normalized_Media_Components.add((byte)(0x83)); // [112][16][3] 	// commented for now, ERS code shows that it appends it, actual CDR this field is not there
  		        		//AsnINT(list_Of_Normalized_Media_Components, mediaInitiatorFlag);			// commented for now, ERS code shows that it appends it, actual CDR this field is not there
  		        		//list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16]
  		        		//list_Of_Normalized_Media_Components.add((byte)(0x00)); // End [112][16]
  		        	}

  		        	//region Normalized-Media-Components 2
  		        	List<Byte> list_Of_Normalized_Media_Components2 = new ArrayList<Byte>();
  		        	if(CodecVideo.size() > 0){
  		        		// TAG: H'80 changeTime [112][16][0]
  		        		//list_Of_Normalized_Media_Components2.add((byte)(0x30)); // [112][16]
  		        		//list_Of_Normalized_Media_Components2.add((byte)(0x80)); // Inf len
  		        		//list_Of_Normalized_Media_Components2.add((byte)(0x80)); // [112][16][0]
  		        		//String changeTime2 = CodecAudio.size() == 0 ? ChangTime: addOrSubtractMinutes(ChangTime, -2); // Subtract 2 minute to from ChangeTimed
  		        		//AsnASCII(list_Of_Normalized_Media_Components2, changeTime2);	// commented for now, ERS code shows that it appends it, actual CDR this field is not there
  		        		//list_Of_Normalized_Media_Components2.add((byte)(0x81)); // [112][16][1]
  		        		//AsnASCII(list_Of_Normalized_Media_Components2, changeTime2);
  		        		//list_Of_Normalized_Media_Components2.add((byte)(0xA2)); // [112][16][2] In ERS this sub-fields are not being appened so for now we comment them to match our CDRs with ERS
  		        		//list_Of_Normalized_Media_Components2.add((byte)(0x80)); // Inf len
  		        		list_Of_Normalized_Media_Components2.addAll(GetValueAsString("RecDesc2", fieldValue).indexOf("Media Video") > -1 && CodecAudio.size() > 0 ? CodecAudio : CodecVideo);
  		        		list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16][2]
  		        		list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16][2]
  		        		list_Of_Normalized_Media_Components2.add((byte)(0x83)); // [112][16][3]
  		        		AsnINT(list_Of_Normalized_Media_Components2, mediaInitiatorFlag);
  		        		list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16]
  		        		list_Of_Normalized_Media_Components2.add((byte)(0x00)); // End [112][16]
  		        	}
  	  	  		           
  		        	//region Normalized-Media-Components 3	// Need to implement changeTime3 here (I need a cdr file and respective csv file)
  		        	List<Byte> list_Of_Normalized_Media_Components3 = new ArrayList<Byte>();
  		        	if(GetValueAsString("RecDesc", fieldValue).indexOf("-to-") > -1) {
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x30)); // [112][16]
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x80)); // Inf len
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x80)); // [112][16][0]
  		        		String changeTime3 = addOrSubtractMinutes(ChangTime, -1); // Subtract 1 minute to from ChangeTimed
  		        		AsnASCII(list_Of_Normalized_Media_Components3, changeTime3);
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x81)); // [112][16][1]
  		        		AsnASCII(list_Of_Normalized_Media_Components3, changeTime3);
  		        		list_Of_Normalized_Media_Components3.add((byte)(0xA2)); // [112][16][2]
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x80)); // Inf len
  		        		list_Of_Normalized_Media_Components3.addAll(GetValueAsString("RecDesc", fieldValue).indexOf("Media Video") > -1 ? CodecVideo : CodecAudio);
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x00)); // End [112][16][2]
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x00)); // End [112][16][2]
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x83)); // [112][16][3]
  		        		AsnINT(list_Of_Normalized_Media_Components3, mediaInitiatorFlag);
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x00)); // End [112][16]
  		        		list_Of_Normalized_Media_Components3.add((byte)(0x00)); // End [112][16]
  		        	}
  	  	  		    	
  		        	// Insert main tag [112]
  		        	if(list_Of_Normalized_Media_Components.size() > 0 || list_Of_Normalized_Media_Components2.size() > 0) {
  		        		fileData.add((byte)0xBF); // [112]
  		        		fileData.add((byte)0x70); // [112]
  		        		fileData.add((byte)0x80); // Inf len
  		        		if(list_Of_Normalized_Media_Components.size() > 0)
  		        			fileData.addAll(list_Of_Normalized_Media_Components);
  	  	  		        		if(list_Of_Normalized_Media_Components2.size() > 0)
  	  	  		        			fileData.addAll(list_Of_Normalized_Media_Components2);
  	  	  		        		if(list_Of_Normalized_Media_Components3.size() > 0)
  	  	  		        			fileData.addAll(list_Of_Normalized_Media_Components3);
  	  	  		        		fileData.add((byte)0x00); // End [112]
  	  	  		        		fileData.add((byte)0x00); // End [112]
  	  	  		        	}
  		        }});

  		    	methodMap.put(82, new Command() {
  		        public void runCommand(String fieldValue) { 
  		          //region TAG: H'BF 1D listOfAccessNetworkInformation ASN TAG: 29
  		        	structureData.clear();

  		         //region // SET 1
  		         //region TAG: H'80 accessNetworkInformation
  		          String tempData_LOANI_accessNetworkInformation = ""; 
  		          tempData_LOANI_accessNetworkInformation = GetValueAsString("LOANI_accessNetworkInformation", fieldValue);
  		          if(tempData_LOANI_accessNetworkInformation.length() > 0) {
  		         	 structureData.add((byte)(0x30)); // TAG[16]
  		         	 structureData.add((byte)(0x80)); // Infinite len
 		         	 
  		         	 if(tempData_LOANI_accessNetworkInformation.length() > 0) {
  		         		 structureData.add((byte)(0x80));
  		         		 AsnHEX(structureData, tempData_LOANI_accessNetworkInformation);
  		         }
  		         
  		         //region TAG: H'81 accessDomain
  		         String tempData_LOANI_accessDomain = GetValueAsString("LOANI_accessDomain", fieldValue);
 		         if(tempData_LOANI_accessDomain.length() > 0) {
  		         	structureData.add((byte)(0x81));
  		         	AsnINT(structureData, tempData_LOANI_accessDomain);
  		         }

  		         //region TAG: H'82 accessType
  		         String tempData_LOANI_accessType="";
  		         tempData_LOANI_accessType = GetValueAsString("LOANI_accessType", fieldValue);
  		         if(tempData_LOANI_accessType.length() > 0) {
  		         	structureData.add((byte)(0x82));
  		         	AsnINT(structureData, tempData_LOANI_accessType);
  		         }

  		         //region TAG: H'83 locationInfoType
  		         String tempData_LOANI_locationInfoType = GetValueAsString("LOANI_locationInfoType", fieldValue);
  		         
  		         if(tempData_LOANI_locationInfoType.length() > 0) {
  		         	structureData.add((byte)(0x83));
  		         	AsnINT(structureData, tempData_LOANI_locationInfoType);
  		         }

  		         //region TAG: H'84 changeTime
  		         String tempData_LOANI_changeTime = GetValueAsString("LOANI_changeTime", fieldValue);
  		         
  		         if(tempData_LOANI_changeTime.length() > 0) {
  		         	structureData.add((byte)(0x84));
  		         	AsnASCII(structureData, tempData_LOANI_changeTime);
  		         }

  		         //region TAG: H'85 changeTimeNormalized
  		         String tempData_LOANI_changeTimeNormalized = GetValueAsString("LOANI_changeTimeNormalized", fieldValue);
  		         
  		         if(tempData_LOANI_changeTimeNormalized.length() > 0) {
  		         	structureData.add((byte)(0x85));
  		         	AsnASCII(structureData, tempData_LOANI_changeTimeNormalized);
  		         }

  		         	structureData.add((byte)(0x00));
  		         	structureData.add((byte)(0x00));
  		         }
  		          // End of set 1
  		          
  		        if(listKey.contains(83) == false){
		            if(structureData.size() > 0) {
		            	fileData.add((byte)0xBD);
		            	fileData.add((byte)0x80); // Infinit len
		            	fileData.addAll(structureData);
		            	fileData.add((byte)0x00);
		            	fileData.add((byte)0x00);
		            }
  		        	}
  		        }});

		    	methodMap.put(83, new Command() {
		        public void runCommand(String fieldValue) { 
		    		//region // SET 2
		            // TAG: H'80 accessNetworkInformation2
		            String tempData_LOANI_accessNetworkInformation2 = GetValueAsString("LOANI_accessNetworkInformation2", fieldValue);
		            if(tempData_LOANI_accessNetworkInformation2.length() > 0) {
		            	structureData.add((byte)(0x30)); // TAG[16]
		            	structureData.add((byte)(0x80)); // Infinite len
		            	structureData.add((byte)(0x80)); // Tag [0]
		            	AsnHEX(structureData, tempData_LOANI_accessNetworkInformation2);

		            //region TAG: H'81 accessDomain2
		            String tempData_LOANI_accessDomain2 = GetValueAsString("LOANI_accessDomain2", fieldValue);
		            if(tempData_LOANI_accessDomain2.length() > 0) {
		            	structureData.add((byte)(0x81));
		            	AsnINT(structureData, tempData_LOANI_accessDomain2);
		            }

		            //region TAG: H'82 accessType2
		            String tempData_LOANI_accessType2 = GetValueAsString("LOANI_accessType2", fieldValue);
		            if(tempData_LOANI_accessType2.length() > 0) {
		            	structureData.add((byte)(0x82));
		            	AsnINT(structureData, tempData_LOANI_accessType2);
		            }

		            //region TAG: H'83 locationInfoType2
		            String tempData_LOANI_locationInfoType2 = GetValueAsString("LOANI_locationInfoType2", fieldValue);
		            if(tempData_LOANI_locationInfoType2.length() > 0) {
		            	structureData.add((byte)(0x83));
		            	AsnINT(structureData, tempData_LOANI_locationInfoType2);
		            }

		            //region TAG: H'84 changeTime2
		            String tempData_LOANI_changeTime2 = GetValueAsString("LOANI_changeTime2", fieldValue);
		            if(tempData_LOANI_changeTime2.length() > 0) {
		            	structureData.add((byte)(0x84));
		            	AsnASCII(structureData, tempData_LOANI_changeTime2);
		            }

		            //region TAG: H'85 changeTimeNormalized2
		            String tempData_LOANI_changeTimeNormalized2 = GetValueAsString("LOANI_changeTimeNormalized2", fieldValue);
		            if(tempData_LOANI_changeTimeNormalized2.length() > 0) {
		            	structureData.add((byte)(0x85));
		            	AsnASCII(structureData, tempData_LOANI_changeTimeNormalized2);
		            }
		            
		            structureData.add((byte)(0x00)); // End of Tag [16]
		            structureData.add((byte)(0x00));
		            // End of set 2
		            }
           
		            if(structureData.size() > 0) {
		            fileData.add((byte)0xBD);
		            fileData.add((byte)0x80); // Infinit len
		            fileData.addAll(structureData);
		            fileData.add((byte)0x00);
		            fileData.add((byte)0x00);
		            }
		        }});
		    	
	            final List<Byte> listOfSubscriptionData = new ArrayList<Byte>();
	            
		    	methodMap.put(92, new Command() {
		        public void runCommand(String fieldValue) { 
		            //region TAG: H'?? serviceContextID ASN TAG: 30?
		            //region TAG: H'BF 1F list-of-subscription-id ASN TAG: 31
		            structureData.clear();	// = new ArrayList<Byte>();
		            //region CTN
		            //region TAG: H'80 subscription-id-type
		            String tempData_LOSID_CTN_TYPE = GetValueAsString("LOSID_CTN_TYPE", fieldValue);
		            if(tempData_LOSID_CTN_TYPE.length() > 0) {
		            	structureData.add((byte)(0x80));
		            	AsnINT(structureData, tempData_LOSID_CTN_TYPE);
		            }

		            //region TAG: H'81 subscription-id-data
		            String tempData_LOSID_CTN_DATA = GetValueAsString("LOSID_CTN_DATA", fieldValue);
		            if(tempData_LOSID_CTN_DATA.length() > 0) {
		            	structureData.add((byte)(0x81));
		            	AsnASCII(structureData, tempData_LOSID_CTN_DATA);
		            }

		            if(structureData.size() > 0) {
		            listOfSubscriptionData.add((byte)(0x31)); // Loc 0 (same as insert)
		            listOfSubscriptionData.add((byte)(0x80)); // 
		            listOfSubscriptionData.addAll(structureData);
		            listOfSubscriptionData.add((byte)(0x00));
		            listOfSubscriptionData.add((byte)(0x00));
		            }
		        }});

		    	methodMap.put(93, new Command() {
		        public void runCommand(String fieldValue) { 
		            structureData.clear();

		            //region IMSI
		            //region TAG: H'80 subscription-id-type
		            String tempData_LOSID_IMSI_TYPE = GetValueAsString("LOSID_IMSI_TYPE", fieldValue);
		            if(tempData_LOSID_IMSI_TYPE.length() > 0) {
		            	structureData.add((byte)(0x80));
		            	AsnINT(structureData, tempData_LOSID_IMSI_TYPE);
		            }

		            //region TAG: H'81 subscription-id-data
		            String tempData_LOSID_IMSI_DATA = GetValueAsString("LOSID_IMSI_DATA", fieldValue);
		            if(tempData_LOSID_IMSI_DATA.length() > 0) {
		            	structureData.add((byte)(0x81));
		            	AsnASCII(structureData, tempData_LOSID_IMSI_DATA);
		            }

		            if(structureData.size() > 0) {
		            	listOfSubscriptionData.add((byte)(0x31)); // Loc 0 (same as insert)
		            	listOfSubscriptionData.add((byte)(0x80)); // 
		            	listOfSubscriptionData.addAll(structureData);
		            	listOfSubscriptionData.add((byte)(0x00));
		            	listOfSubscriptionData.add((byte)(0x00));
		            }
		        }});

		    	methodMap.put(94, new Command() {
		        public void runCommand(String fieldValue) { 
		            structureData.clear();
		            //region IMEI
		            //region TAG: H'80 subscription-id-type
		            String tempData_LOSID_IMEI_TYPE = GetValueAsString("LOSID_IMEI_TYPE", fieldValue);
		            if(tempData_LOSID_IMEI_TYPE.length() > 0) {
		            structureData.add((byte)(0x80));
		            AsnINT(structureData, tempData_LOSID_IMEI_TYPE);	// Jason uses AsnHEX but that doesn't output for me the value in hexa
		            }

		            //region TAG: H'81 subscription-id-data
		            String tempData_LOSID_IMEI_DATA=""; 
		            tempData_LOSID_IMEI_DATA = GetValueAsString("LOSID_IMEI_DATA", fieldValue);
		            if(tempData_LOSID_IMEI_DATA.length() > 0) {
		            structureData.add((byte)(0x81));
		            AsnASCII(structureData, tempData_LOSID_IMEI_DATA);
		            }
		            
		            if(structureData.size() > 0) {
		            listOfSubscriptionData.add((byte)(0x31)); // Loc 0 (same as insert)
		            listOfSubscriptionData.add((byte)(0x80)); // 
		            listOfSubscriptionData.addAll(structureData);
		            listOfSubscriptionData.add((byte)(0x00));
		            listOfSubscriptionData.add((byte)(0x00));
		            }
		            
		            if(listOfSubscriptionData.size() > 0) {
		                fileData.add((byte)0xBF);
		                fileData.add((byte)0x1F);
		                fileData.add((byte)0x80); // Infinit len
		                fileData.addAll(listOfSubscriptionData);
		                fileData.add((byte)0x00);
		                fileData.add((byte)0x00);
		                }
		        }});

		    	methodMap.put(104, new Command() {
		        public void runCommand(String fieldValue) { 
		            //region TAG: H'9F 65 requested-Party-Address ASN TAG: 101
		            if(fieldValue.length() > 0) {
		            fileData.add((byte)0xBF); // Tag
		            fileData.add((byte)0x65); // Tag
		            fileData.add((byte)0x80); // Infinit len
		            if(fieldValue.toLowerCase().startsWith("sip:")) //Add ASN.1 TAG: H'80 for SIP
		            fileData.add((byte)0x80);
		            else if(fieldValue.toLowerCase().startsWith("tel:")) //Add ASN.1 TAG: H'81 for TEL
		            fileData.add((byte)0x81);
		            AsnASCII(fileData, fieldValue);
		            fileData.add((byte)0x00); // End
		            fileData.add((byte)0x00); // End
		            }
		        }});

	            //region TAG: H'BF 71 mMTelInformation ASN TAG: 113
		    	methodMap.put(108, new Command() {
		        public void runCommand(String fieldValue) { 

		            structureData.clear();	// = new ArrayList<Byte>();

		            //region TAG: H'82 calling-party-address-presentation-status
		            
		            if(fieldValue.length() > 0) {
		            structureData.add((byte)(0x82));
		            AsnINT(structureData, fieldValue);
		            }
		            
		            if((listKey.contains(111) == false) && (listKey.contains(112) == false)){
		                if(structureData.size() > 0) {
		                    fileData.add((byte) 0xBF);
		                    fileData.add((byte) 0x71);
		                    fileData.add((byte) 0x80); // Infinit len
		                    fileData.addAll(structureData);
		                    fileData.add((byte) 0x00);
		                    fileData.add((byte) 0x00);
		                    }
		            }

		        }});

		    	methodMap.put(111, new Command() {
		        public void runCommand(String fieldValue) { 
		            if(fieldValue.length() > 0) {
		            structureData.add((byte)(0x85));
		            AsnASCII(structureData, fieldValue);
		            }
		            
		            if(listKey.contains(112) == false){
		                if(structureData.size() > 0) {
		                    fileData.add((byte) 0xBF);
		                    fileData.add((byte) 0x71);
		                    fileData.add((byte) 0x80); // Infinit len
		                    fileData.addAll(structureData);
		                    fileData.add((byte) 0x00);
		                    fileData.add((byte) 0x00);
		                    }
		            }
		        }});

		    	methodMap.put(112, new Command() {
		        public void runCommand(String fieldValue) { 
		            String tempData_supplementary_Service_Identity = GetValueAsString("supplementary-Service-Identity", fieldValue); // [6][U17][0]
		            if(tempData_supplementary_Service_Identity.length() > 0) {
		            // supplementary-Service-Information [
		            structureData.add((byte)(0xA6)); // [6]
		            structureData.add((byte)(0x80)); // Infinit len
		            structureData.add((byte)(0x31)); // [Universal 17]
		            structureData.add((byte)(0x80)); // Infinit len
		            structureData.add((byte)(0x80));
		            AsnINT(structureData, tempData_supplementary_Service_Identity); // [6][U17][0] (same as above)
		            
		            String tempData_supplementary_Service_Action = GetValueAsString("supplementary-Service-Action", fieldValue); // [6][U17][1]
		            
		            if(tempData_supplementary_Service_Action.length() > 0) {
		            	structureData.add((byte)(0x81));
		            	AsnINT(structureData, tempData_supplementary_Service_Action);
		            }
		            
		            String tempData_redirecting_Party_Address = GetValueAsString("redirecting-Party-Address", fieldValue); // [6][U17][2]
		            if(tempData_redirecting_Party_Address.length() > 0) {
		            structureData.add((byte)(0x82));
		            AsnASCII(structureData, tempData_redirecting_Party_Address);
		            }
		            	structureData.add((byte)(0x00)); // End of [17]
		            	structureData.add((byte)(0x00));
		            	structureData.add((byte)(0x00)); // End of [6]
		            	structureData.add((byte)(0x00));
		            }
		            
		            if(structureData.size() > 0) {
		                fileData.add((byte) 0xBF);
		                fileData.add((byte) 0x71);
		                fileData.add((byte) 0x80); // Infinit len
		                fileData.addAll(structureData);
		                fileData.add((byte) 0x00);
		                fileData.add((byte) 0x00);
		                }
		        }});

		    	methodMap.put(124, new Command() {
		        public void runCommand(String fieldValue) { 
		            //region TAG: H'9F 81 4B tariffClass ASN TAG: 203
		            if(fieldValue.length() > 0) {
		            	fileData.add((byte) 0x9F);
		            	fileData.add((byte) 0x81);
		            	fileData.add((byte) 0x4B);
		            	//AsnASCII(tempData, ref fileData);
		            	AsnINT(fileData, fieldValue);
		            }
		        }});

		    	// Get an iterator
		    	Iterator i = set.iterator();
	        
		    	while(i.hasNext()) {	// Iterates through hashMap to build the CDR output based on the present keys 
		    		Map.Entry me = (Map.Entry)i.next();

		    		methodMap.get(me.getKey()).runCommand((String) me.getValue());  // second field
	           
		    		if((int)me.getKey() == 124)	// Just to break while constructing the 126 fields, once they are all built erase this 
		    			break;					// conditional break
		    	}
	        
		    	fileData.add((byte) 0x00);
		    	fileData.add((byte) 0x00);

		    	lbFile=fileData.toArray(new Byte[0]); // Converting ArrayList of Bytes into array of Bytes (wrapper)
		    	byte[] primitivebytearray = toPrimitiveByteArray(lbFile); // Converting array of Bytes (wrapper) into array of bytes (primitives)
		    	return(primitivebytearray);
  			}
  		
        static String addOrSubtractMinutes(String time, int minutes){
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
  			Date date=null;
  			try {
  				date = sdf.parse(time);
  				Calendar calendar = new GregorianCalendar();	
  				calendar.setTime(date);
  				//subtract 2 minutes
  				calendar.add(Calendar.MINUTE, minutes);
  				return(sdf.format(calendar.getTime()));
  			} catch (ParseException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  				return"";
  			}
  		}
}

interface Command{
	void runCommand(String fieldValue);
}