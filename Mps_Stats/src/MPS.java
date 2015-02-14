import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MPS {

	static String[] matches = new String[] {"was started on", "with status CO", "with status AF", "with status AE", "AF", "AE"};
	SimpleDateFormat minTime = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	SimpleDateFormat maxTime = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	ArrayList<ArrayList<Integer>> array = new ArrayList<>();
	static List<String> sublist = new ArrayList<String>();
	List formatList = new ArrayList<String>(maxColumn);
	List recordList = new ArrayList<Double>(maxColumn);
	List durationList = new ArrayList<Double>(maxColumn);
	List thrusList = new ArrayList<Double>(maxColumn);
	List thrumtList = new ArrayList<Double>(maxColumn);
	List thruhList = new ArrayList<Double>(maxColumn);
	List abortedList = new ArrayList<String>(maxColumn);
	List abortedStatusList = new ArrayList<String>(maxColumn);
	List abortedRecordList = new ArrayList<Double>(maxColumn);
	
	static int FORMAT = 0;	
	static int RECORDS = 1;
	static int DURATION = 2;
	static int STATUS = 2;
	static int THRUS = 3;	// Throughput per seconds
	static int THRUM = 4;	// Throughput per minutes
	static int THRUH = 5;	// Throughput per hour
	static int maxRow = 40;
	static int maxColumn = 40;	//since maxColumn is static it can be defined after used in a non-static List

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
	      
	      public static void main(String[] args) throws ParseException {
			MPS mps = new MPS();
//			mps.parseFileMAF();
//			mps.parseFileGUIDE();
			mps.parseFileRATE();
		}
	      
	      public void populateArrayListStatsMAF(String line1, String line2) throws ParseException{
	    	  String format="";
	    	  double recordCount=0;
	    	  double duration=0;
	    	  long d1=0;
	    	  long d2=0;
	    	  int index=0;
	    	  String strStartTime="";
	    	  String strEndTime="";
	    	  SimpleDateFormat startTime = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	    	  SimpleDateFormat endTime = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	    	  Pattern pdatetime;// = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"); //date and time pattern: 08/16/2014 20:39:51
	          Matcher mdatetimestart;// = pdatetime.matcher(line1);

	    	  ////// Capturing File Format ////////////
	          Pattern pf = Pattern.compile("_F(.+)_I"); // group 0 Whole Regex, group 1 First content in parenthesis from left to right
	          Matcher mf = pf.matcher(line1);
	          
	          if(mf.find())
	          {
		          format = mf.group(1);
	          }
	    	  ////// Capturing Number of Records ////////////
	          Pattern pr = Pattern.compile("_R(\\d+)"); // group 0 Whole Regex, group 1 First content in parenthesis from left to right
	          Matcher mr = pr.matcher(line1);
	          
	          if(mr.find())
	          {
	        	  recordCount = Integer.parseInt(mr.group(1));
	          }	          
	    	  ////// Capturing Start Time  and converting it into milliseconds ////////////
	           pdatetime = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"); //date and time pattern: 08/16/2014 20:39:51
	           mdatetimestart = pdatetime.matcher(line1);
	          
	          if(mdatetimestart.find())
	          {
	        	  strStartTime = mdatetimestart.group(0);
		          d1=startTime.parse(strStartTime).getTime();
	          }
	          ////// Capturing End Time  and converting it into milliseconds ////////////
	          mdatetimestart = pdatetime.matcher(line2);
	          
	          if(mdatetimestart.find())
	          {
	        	  strEndTime = mdatetimestart.group(0);
		    	  d2=endTime.parse(strEndTime).getTime();
	          }
	          
	          duration=(int)((d2-d1)/1000);
	          
    		  if(duration == 0){
    			  duration = 1;
    		  }
	          ///////////////Populating stats in ArrayLists based on format ////////////////////////////////
	          
	          if(formatList.contains(format)){

	    		  index = formatList.indexOf(format);
	    		  
	    		  recordList.get(index);
	    		  
	    		  recordCount += (double)recordList.get(index);
	    		  
	    		  duration += (double)durationList.get(index);

	    		  recordList.set(index, recordCount);
	    		  durationList.set(index, duration);
	        	  thrusList.set(index, (recordCount/duration));
	        	  thrumtList.set(index, (recordCount/(duration)*60));
	        	  thruhList.set(index, (recordCount/duration)*3600);
	        	  
	    	  }
	          else{	// If it's new format in arraylist
	        	  formatList.add(format);
	        	  recordList.add(recordCount);
	        	  durationList.add(duration);
	        	  thrusList.add(recordCount/duration);
	        	  thrumtList.add(recordCount/(duration*60));
	        	  thruhList.add(recordCount/(duration*3600));
	          }
	      }

	      public void populateArrayListStatsGUIDE(String line1, String line2, String line3) throws ParseException{
	    	  String format="";
	    	  double recordCount=0;
	    	  double duration=0;
	    	  long d1=0;
	    	  long d2=0;
	    	  int index=0;
	    	  String strStartTime="";
	    	  String strEndTime="";
	    	  SimpleDateFormat startTime = new SimpleDateFormat("MMM dd HH:mm:ss yyyy"); // Aug 17 20:05:55 2014
	    	  SimpleDateFormat endTime = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");
	    	  Pattern pdatetime;// = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"); //date and time pattern: 08/16/2014 20:39:51
	          Matcher mdatetimestart;// = pdatetime.matcher(line1);
	          ps("_ _");
	    	  ////// Capturing File Format ////////////
	          Pattern pf = Pattern.compile("_F(.+)_I"); // getting format
	          Matcher mf = pf.matcher(line1);
	          ps("_ _");
	          if(mf.find())
	          {
		          format = mf.group(1);
		          ps("format: " + format);
	          }
	          ps("_ _");
	    	  ////// Capturing Start Time  and converting it into milliseconds ////////////
	          pdatetime = Pattern.compile("(Aug \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4})"); // Getting Start time
	          mdatetimestart = pdatetime.matcher(line1);
	          ps("_ 1_");
	          if(mdatetimestart.find())
	          {
	        	  ps("_ 2_");
	        	  strStartTime = mdatetimestart.group(0);
	        	  ps("strStartTime: " + strStartTime);
	        	  ps("_ 2.1_");
	        	  d1=startTime.parse(strStartTime).getTime();
	        	  ps("_ 2.2_");
	        	  ps("strStartTime: " + strStartTime);
	        	  ps("_ 2.3_");
	          }	          
	          ps("_3 _");
	    	  ////// Capturing Number of Records ////////////
	          ps("line2: " + line2);
	          Pattern pr = Pattern.compile("MPSGUIDE_CAN.*Alias: USAGE   No. of Records (\\d+)"); // getting the records
	          Matcher mr = pr.matcher(line2);
	          
	          if(mr.find())
	          {
	        	  ps(mr.group(0));
	        	  ps(mr.group(1));
	        	  recordCount = Integer.parseInt(mr.group(1));
	        	  ps("recordCount: " + recordCount);
	          }	

	          ////// Capturing End Time  and converting it into milliseconds ////////////
	          mdatetimestart = pdatetime.matcher(line3);
	          
	          if(mdatetimestart.find())
	          {
	        	  strEndTime = mdatetimestart.group(0);
		    	  d2=endTime.parse(strEndTime).getTime();
	          }
	          
	          duration=(int)((d2-d1)/1000);
	          
    		  if(duration == 0){
    			  duration = 1;
    		  }
    		  ps("line1: " + line1);
    		  ps("line2: " + line2);
    		  ps("line3: " + line3);
    		  ps("format: " + format);
    		  ps("recordCount: " + recordCount);
    		  ps("strStartTime: " + strStartTime);
    		  ps("strEndTime: " + strEndTime);
    		  ps("duration: " + duration);
    		  
//    		  System.exit(0);
	          ///////////////Populating stats in ArrayLists based on format ////////////////////////////////
	          
	          if(formatList.contains(format)){

	    		  index = formatList.indexOf(format);
	    		  
	    		  recordList.get(index);
	    		  
	    		  recordCount += (double)recordList.get(index);
	    		  
	    		  duration += (double)durationList.get(index);

	    		  recordList.set(index, recordCount);
	    		  durationList.set(index, duration);
	        	  thrusList.set(index, (recordCount/duration));
	        	  thrumtList.set(index, (recordCount/(duration)*60));
	        	  thruhList.set(index, (recordCount/duration)*3600);
	        	  
	    	  }
	          else{	// If it's new format in arraylist
	        	  formatList.add(format);
	        	  recordList.add(recordCount);
	        	  durationList.add(duration);
	        	  thrusList.add(recordCount/duration);
	        	  thrumtList.add(recordCount/(duration*60));
	        	  thruhList.add(recordCount/(duration*3600));
	          }
	      }

	      public void populateArrayListStatsRATE(String line1, String line2, String line3) throws ParseException{
	    	  String format="";
	    	  double recordCount=0;
	    	  double duration=0;
	    	  long d1=0;
	    	  long d2=0;
	    	  int index=0;
	    	  String strStartTime="";
	    	  String strEndTime="";
	    	  SimpleDateFormat startTime = new SimpleDateFormat("MMM dd HH:mm:ss yyyy"); // Aug 17 20:05:55 2014
	    	  SimpleDateFormat endTime = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");
	    	  Pattern pdatetime;// = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"); //date and time pattern: 08/16/2014 20:39:51
	          Matcher mdatetimestart;// = pdatetime.matcher(line1);
	          ps("_ _");
	    	  ////// Capturing File Format ////////////
	          Pattern pf = Pattern.compile("_F(.+)_I"); // getting format
	          Matcher mf = pf.matcher(line1);
	          ps("_ _");
	          if(mf.find())
	          {
		          format = mf.group(1);
		          ps("format: " + format);
	          }
	          ps("_ _");
	    	  ////// Capturing Start Time  and converting it into milliseconds ////////////
	          pdatetime = Pattern.compile("(Aug \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4})"); // Getting Start time
	          mdatetimestart = pdatetime.matcher(line1);
	          ps("_ 1_");
	          if(mdatetimestart.find())
	          {
	        	  ps("_ 2_");
	        	  strStartTime = mdatetimestart.group(0);
	        	  ps("strStartTime: " + strStartTime);
	        	  ps("_ 2.1_");
	        	  d1=startTime.parse(strStartTime).getTime();
	        	  ps("_ 2.2_");
	        	  ps("strStartTime: " + strStartTime);
	        	  ps("_ 2.3_");
	          }	          
	          ps("_3 _");
	    	  ////// Capturing Number of Records ////////////
	          ps("line2: " + line2);


//	          ./MPSRATE08_CAN_1901_20140817_200559.log:Program mpup_100mn has started at Sun Aug 17 20:05:59 2014
//	  	  	./MPSRATE08_CAN_1901_20140817_200559.log:Open input file Id: 894027244 ,in : /usg/TLG_VAR/can/projs/up/data/MD_IMS1205/SMOIMS1HSN_FIMS1205_ID0390_T20140816160825_R195/SMOIMS1HSN at Sun Aug 17 20:06:00 2014
//	  	  	./MPSRATE08_CAN_1901_20140817_200559.log:   File name: ugd_CAN_CAN08R51.dat   Alias: USAGE   No. of Records 1 at Sun Aug 17 20:06:00 2014
//	  	  	./MPSRATE08_CAN_1901_20140817_200559.log:Program mpup_100mn has completed successfuly at Sun Aug 17 20:06:00 2014
	          
	          Pattern pr = Pattern.compile("MPSRATE.+_CAN.+Alias: USAGE   No. of Records (\\d+)"); // getting the records
	          Matcher mr = pr.matcher(line2);
	          
	          if(mr.find())
	          {
	        	  ps(mr.group(0));
	        	  ps(mr.group(1));
	        	  recordCount = Integer.parseInt(mr.group(1));
	        	  ps("recordCount: " + recordCount);
	          }	

	          ////// Capturing End Time  and converting it into milliseconds ////////////
	          mdatetimestart = pdatetime.matcher(line3);
	          
	          if(mdatetimestart.find())
	          {
	        	  strEndTime = mdatetimestart.group(0);
		    	  d2=endTime.parse(strEndTime).getTime();
	          }
	          
	          duration=(int)((d2-d1)/1000);
	          
    		  if(duration == 0){
    			  duration = 1;
    		  }
    		  ps("line1: " + line1);
    		  ps("line2: " + line2);
    		  ps("line3: " + line3);
    		  ps("format: " + format);
    		  ps("recordCount: " + recordCount);
    		  ps("strStartTime: " + strStartTime);
    		  ps("strEndTime: " + strEndTime);
    		  ps("duration: " + duration);
    		  
//    		  System.exit(0);
	          ///////////////Populating stats in ArrayLists based on format ////////////////////////////////
	          
	          if(formatList.contains(format)){

	    		  index = formatList.indexOf(format);
	    		  
	    		  recordList.get(index);
	    		  
	    		  recordCount += (double)recordList.get(index);
	    		  
	    		  duration += (double)durationList.get(index);

	    		  recordList.set(index, recordCount);
	    		  durationList.set(index, duration);
	        	  thrusList.set(index, (recordCount/duration));
	        	  thrumtList.set(index, (recordCount/(duration)*60));
	        	  thruhList.set(index, (recordCount/duration)*3600);
	        	  
	    	  }
	          else{	// If it's new format in arraylist
	        	  formatList.add(format);
	        	  recordList.add(recordCount);
	        	  durationList.add(duration);
	        	  thrusList.add(recordCount/duration);
	        	  thrumtList.add(recordCount/(duration*60));
	        	  thruhList.add(recordCount/(duration*3600));
	          }
	      }
	      
	      public void printReport(){
	    	  
	    	  String[][] resultsTable = new String[maxRow][70];
	    	  String[][] abortedResultsTable = new String[maxRow][70];
	    	  
	    	  double recSum = 0;
	    	  double secSum = 0;
	    	  
	    	  ///////////////////// PRINTING COMPLETED FILES ///////////////////////

	    	  resultsTable[0][FORMAT]="|File_Format|";
	    	  resultsTable[0][RECORDS]="|No_Records|";
	    	  resultsTable[0][DURATION]="|Duration(secs)|";
	    	  resultsTable[0][THRUS]="|Recs/Secs|";
	    	  resultsTable[0][THRUM]="|Recs/Min|";
	    	  resultsTable[0][THRUH]="|Recs/Hr|";
	    	  
	    	  for(int i=1 ; i < formatList.size()+1;i++){

	    		  	  resultsTable[i][FORMAT]=(String) formatList.get(i-1);
	    			  resultsTable[i][RECORDS]= recordList.get(i-1).toString();
	    			  resultsTable[i][DURATION]= durationList.get(i-1).toString();
	    			  resultsTable[i][THRUS]= thrusList.get(i-1).toString();
	    			  resultsTable[i][THRUM]= thrumtList.get(i-1).toString();
	    			  resultsTable[i][THRUH]= thruhList.get(i-1).toString();
	    	  }

	    	  for(int i=0; i<formatList.size(); i++){
	    		  recSum += (double)recordList.get(i);//Double.parseDouble(recordList.get(i).toString());
	    		  secSum += (double)durationList.get(i);//Double.parseDouble(durationList.get(i).toString());
	    	  }
	    	  
	    	  resultsTable[formatList.size()+1][FORMAT]="Total";
			  resultsTable[formatList.size()+1][RECORDS]=String.valueOf(recSum);//recordList.get(i-1).toString();
			  resultsTable[formatList.size()+1][DURATION]=String.valueOf(secSum);//recordList.get(i-1).toString();
			  resultsTable[formatList.size()+1][THRUS]=String.valueOf((int)(recSum/secSum));
			  resultsTable[formatList.size()+1][THRUM]=String.valueOf((int)((recSum/secSum)*60));
			  resultsTable[formatList.size()+1][THRUH]=String.valueOf((int)((recSum/secSum)*3600));
			  
	    	  ps("");
	    	  
	    	  for(int i =0; i < formatList.size()+2; i++){
	    		  for(int j =0; j < 6; j++){
	    			  if(i==0){
	    				  psn(resultsTable[i][j] + "\t");
	    			  }
	    			  else{
	    				  if(j==0){
	    				  	psn(resultsTable[i][j] + "\t");
	    				  }
	    				  else{
	    					 psn(NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(resultsTable[i][j]))); psn("\t");
	    				  }
	    			  }
	    		  }
	    		  ps("");
	    	  }
	      }
	      
	      private void populateAbortedListStats(String line){

			 /* if ( line.contains(matches[2]) || line.contains(matches[3]) )
			  {
				  ps("line: " + line);
				  
				  String format="";
				  int recordCount=0;
				  
				  ////// Capturing Number of Records ////////////
		          Pattern pr = Pattern.compile("_R(\\d+)"); // group 0 Whole Regex, group 1 First content in parenthesis from left to right
		          Matcher mr = pr.matcher(line);
		          
		          if(mr.find())
		          {
		        	  recordCount = Integer.parseInt(mr.group(1));
			          System.out.println("recordCount: " + recordCount);
			          System.out.println(line);
		          }	          
		          ///////////////////////////////////////////////
		          
		          //abortedList with status AF or AE
		          String abortedStatus="";
		          Pattern pa = Pattern.compile(" with status (A[FE])"); // group 0 Whole Regex, group 1 First content in parenthesis from left to right
		          Matcher ma = pa.matcher(line);
		          
				  if(ma.find())
				  {
					  abortedStatus = ma.group(1);
					  System.out.println("abortedStatus: " + abortedStatus);
				  }
				  
				  if(abortedList.contains(abortedStatus)){
		    		  ps("it contains " + abortedStatus);
		    		  int index = abortedList.indexOf(abortedStatus);
		    		  
		    		  recordCount += (double)abortedRecordList.get(index);
		    		  ps("recordCount: " + recordCount);
		    		  
		    		  abortedRecordList.set(index, recordCount);
		    	  }
		          else{	// If it's new format in arraylist
		        	  abortedList.add(abortedStatus);
		        	  abortedRecordList.add(recordCount);
		          }
				  
				  ps("abortedList: " + abortedList);
	        	  ps("abortedRecordList: " + abortedRecordList);
			  }*/
	      }
	      
	private void parseFileMAF() throws ParseException{	// Generic function to retrieve field values from CDR-CSV file
	  	
  	  File file = null;
  	  OutputStream os = null;
  	  BufferedReader br = null;
  	  String line1 = "";
  	  String line2 = "";
  	  String cvsSplitBy = ",";
  	  int NumberCDRsPerFile=1;
//  	  String strfileName="MD_R714_16th_Aug.txt";
  	  String strfileName="MD_R714_17th_Aug.txt";
  	  
  	
  	  String inputFileNameL1="";
  	  String inputFileNameL2="";
  	  String format="";
	  
  	  try {
  		  br = new BufferedReader(new FileReader(strfileName));
            
    	  while ((line1 = br.readLine()) != null) {    //Within this loop CDRs are written in a file an therefore files are being created.        	// Reading second, third, and so on.. rows (data rows). 
    		  
//    		  ps("line1: " + line1);
    		  
			  if (line1.contains(matches[0]))
			  {
				  ///////////////////////////////////////////////////////////////////
				  
					/////////////////////////////////////////////////////////////////
					//inputFileName: SVA2BC03_FGSM1205_ID5592_T20140816160825_R4242//
					/////////////////////////////////////////////////////////////////
				  
				  Pattern pifnl1 = Pattern.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
				  Matcher mifnl1 = pifnl1.matcher(line1);
		          
		          if(mifnl1.find())
		          {
		        	  inputFileNameL1 = mifnl1.group(0);
//			          System.out.println("inputFileNameL1: " + inputFileNameL1);
		          }
		          else{
		        	  
		          	  line1 = "";
		          	  line2 = "";
		        	  continue;
		          }
		          
				  line2 = br.readLine();
				  
				  Pattern pifnl2 = Pattern.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // checking if for second line. if it referees to the same file from line 1
				  Matcher mifnl2 = pifnl2.matcher(line2);
				  
		          if(mifnl2.find())
		          {
		        	  inputFileNameL2 = mifnl2.group(0);
//			          System.out.println("inputFileNameL2: " + inputFileNameL2);
		          }
		          else{
		          	  line1 = "";
		          	  line2 = "";
		        	  continue;
		          }
		          
				  if(inputFileNameL1.equals(inputFileNameL2) != true){
		          	  line1 = "";
		          	  line2 = "";
					  continue;
				  }

				  if (line2.contains(matches[1]))
    			  {
					  populateArrayListStatsMAF(line1, line2);
    			  }
				  else{
					  populateAbortedListStats(line2);
				  }
				  ///////////////////////////////////////////////////////////////////
			  }
			  else{	// capture AF and AE status
				  // FSGW1215_ID08934_T20140816160825_R68885.DAT has been finished on 08/16/2014 20:44:36 with status AF.
				  
				  populateAbortedListStats(line1);
				  
				  /*if (line1.contains(matches[2]) || line1.contains(matches[3]) )
				  {
					  int recordCount=0;
					  
					  /////////////////////////////////////////////////////////////////
					  //inputFileName: SVA2BC03_FGSM1205_ID5592_T20140816160825_R4242//
					  /////////////////////////////////////////////////////////////////
					
//					  Pattern pifnl1 = Pattern.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
//					  Matcher mifnl1 = pifnl1.matcher(line1);
					
					  ////// Capturing Number of Records ////////////
			          Pattern pr = Pattern.compile("_R(\\d+)"); // group 0 Whole Regex, group 1 First content in parenthesis from left to right
			          Matcher mr = pr.matcher(line1);
			          
			          if(mr.find())
			          {
			        	  recordCount = Integer.parseInt(mr.group(1));
				          System.out.println("recordCount: " + recordCount);
				          System.out.println(line1);
			          }	          
			          ///////////////////////////////////////////////
			          
			          //abortedList with status AF or AE
			          String abortedStatus="";
			          Pattern pa = Pattern.compile(" with status (A[FE])"); // group 0 Whole Regex, group 1 First content in parenthesis from left to right
			          Matcher ma = pa.matcher(line1);
			          
					  if(ma.find())
					  {
						  abortedStatus = ma.group(1);
						  System.out.println("abortedStatus: " + abortedStatus);
					  }
					  
					  if(abortedList.contains(abortedStatus)){
			    		  ps("it contains " + abortedStatus);
			    		  int index = abortedList.indexOf(format);
			    		  
			    		  recordCount += (double)abortedRecordList.get(index);
//			    		  ps("recordCount: " + recordCount);
			    		  
			    		  abortedRecordList.set(index, recordCount);
			    	  }
			          else{	// If it's new format in arraylist
			        	  abortedList.add(abortedStatus);
			        	  abortedRecordList.add(recordCount);
			          }
				  }*/
				  
				  continue;
			  }
    	  }
  	  } catch (IOException e) {
  		  e.printStackTrace();
  	  } finally{

  		try{
			  br.close();
		  } catch(IOException e){
			  e.printStackTrace();
		  }
  		
  		printReport();
  	  }
    
	}
	
	private void parseFileGUIDE() throws ParseException{	// Generic function to retrieve field values from CDR-CSV file
	  	
	  	  File file = null;
	  	  OutputStream os = null;
	  	  BufferedReader br = null;
	  	  String line1 = "";
	  	  String line2 = "";
	  	  String line3 = "";
	  	  String cvsSplitBy = ",";
	  	  int NumberCDRsPerFile=1;
	  	  String strfileName="GD_R714_17th_Aug.txt";
	  	  
//	  	./MPSGUIDE_CAN_13004_20140817_200800.log:Program mpgd_100mn.cbl has started at Sun Aug 17 20:09:21 2014
//	  	./MPSGUIDE_CAN_13004_20140817_200800.log:Open input file Id: 894027580 ,in : js/up/data/ERIC147/STOGMSC0_FERIC147_ID32846_T20140816160825_R2331/STOGMSC0_D140 at Sun Aug 17 20:09:21 2014
//	  	./MPSGUIDE_CAN_13004_20140817_200800.log:   File name: USAGE_CAN   Alias: USAGE   No. of Records 221 at Sun Aug 17 20:09:21 2014
//	  	./MPSGUIDE_CAN_13004_20140817_200800.log:Open input file Id: 894027580 ,in : js/up/data/ERIC147/STOGMSC0_FERIC147_ID32846_T20140816160825_R2331/STOGMSC0_D140 at Sun Aug 17 20:09:21 2014
//	  	./MPSGUIDE_CAN_13004_20140817_200800.log:Program mpgd_100mn.cbl has completed successfuly at Sun Aug 17 20:09:29 2014
	  	  
	  	
	  	  String inputFileNameL1="";
	  	  String inputFileNameL2="";
	  	  String format="";
		  ps("_1_");
	  	  try {
	  		  br = new BufferedReader(new FileReader(strfileName));
	  		ps("_2_");
	    	  while ((line1 = br.readLine()) != null) {    //Within this loop CDRs are written in a file an therefore files are being created.        	// Reading second, third, and so on.. rows (data rows). 
	    		  
	    		  ps("line1: " + line1);
	    		  ps("_3_"); 
//				  if (line1.contains("MPSGUIDE_CAN_.+Id: (\\d+).+_F(.+)_.*"))// Checking for Format
	    		  if (line1.contains("Open input file Id:"))// Checking for Format
				  {
					  ps("_4_");
					  if((line2 = br.readLine()) == null){
						  continue;
					  }
					  ps("_5_");
//					  if (line2.contains("MPSGUIDE_CAN.*Alias: USAGE   No. of Records (\\d+)")){//Checking for No. Records
					  if (line2.contains("File name: USAGE_CAN   Alias: USAGE   No. of Records")){//Checking for No. Records
						  ps("_6_");
						  if(br.readLine() == null){// skipping next line
							  ps("_7_");
							  continue;
						  }
						  ps("_8_");
						  if((line3 = br.readLine()) == null){
							  ps("_9_");
							  continue;
						  }
						  ps("_10_");
						  if (line3.contains("Program mpgd_100mn.cbl has completed successfuly")){//Checking end time
							  ps("_11_");
							  populateArrayListStatsGUIDE(line1, line2, line3);
						  }
						  ps("_12_");
					  }
					  else{
						  ps("_13_");
						  continue;
					  }
					  ps("_14_");
				  }
				  else{	// capture AF and AE status
					  ps("_15_");
					  continue;
				  }
	    	  }
	  	  } catch (IOException e) {
	  		  e.printStackTrace();
	  	  } finally{

	  		try{
				  br.close();
			  } catch(IOException e){
				  e.printStackTrace();
			  }
	  		ps("_16_");
	  		printReport();
	  	  }
	    
		}
	
	private void parseFileRATE() throws ParseException{	// Generic function to retrieve field values from CDR-CSV file
	  	
	  	  File file = null;
	  	  OutputStream os = null;
	  	  BufferedReader br = null;
	  	  String line1 = "";
	  	  String line2 = "";
	  	  String line3 = "";
	  	  String cvsSplitBy = ",";
	  	  int NumberCDRsPerFile=1;
	  	  String strfileName="RT_R714_17th_Aug_w_file_name.txt";
	  	
//	  	./MPSRATE08_CAN_1901_20140817_200559.log:Program mpup_100mn has started at Sun Aug 17 20:05:59 2014
//	  	./MPSRATE08_CAN_1901_20140817_200559.log:Open input file Id: 894027244 ,in : /usg/TLG_VAR/can/projs/up/data/MD_IMS1205/SMOIMS1HSN_FIMS1205_ID0390_T20140816160825_R195/SMOIMS1HSN at Sun Aug 17 20:06:00 2014
//	  	./MPSRATE08_CAN_1901_20140817_200559.log:   File name: ugd_CAN_CAN08R51.dat   Alias: USAGE   No. of Records 1 at Sun Aug 17 20:06:00 2014
//	  	./MPSRATE08_CAN_1901_20140817_200559.log:Program mpup_100mn has completed successfuly at Sun Aug 17 20:06:00 2014
	  	
	  	  String inputFileNameL1="";
	  	  String inputFileNameL2="";
	  	  String format="";
		  ps("_1_");
	  	  try {
	  		  br = new BufferedReader(new FileReader(strfileName));
	  		ps("_2_");
	    	  while ((line1 = br.readLine()) != null) {    //Within this loop CDRs are written in a file an therefore files are being created.        	// Reading second, third, and so on.. rows (data rows). 
	    		  
	    		  ps("line1: " + line1);
	    		  ps("_3_"); 

	    		  if (line1.contains("Open input file Id:"))// Checking for Format
				  {
					  ps("_4_");
					  if((line2 = br.readLine()) == null){
						  continue;
					  }
					  ps("_5_");
					  ps("line2: " + line2);
					  if (line2.contains("Alias: USAGE   No. of Records")){//Checking for No. Records
						  ps("_6_");
//						  if(br.readLine() == null){// skipping next line
//							  ps("_7_");
//							  continue;
//						  }
						  ps("_8_");
						  if((line3 = br.readLine()) == null){
							  ps("_9_");
							  continue;
						  }
						  ps("_10_");
						  ps("line3: " + line3);
						  if (line3.contains("Program mpup_100mn has completed successfuly")){//Checking end time
							  ps("_11_");
							  populateArrayListStatsRATE(line1, line2, line3);
						  }
						  ps("_12_");
					  }
					  else{
						  ps("_13_");
						  continue;
					  }
					  ps("_14_");
				  }
				  else{	// capture AF and AE status
					  ps("_15_");
					  continue;
				  }
	    	  }
	  	  } catch (IOException e) {
	  		  e.printStackTrace();
	  	  } finally{

	  		try{
				  br.close();
			  } catch(IOException e){
				  e.printStackTrace();
			  }
	  		ps("_16_");
	  		printReport();
	  	  }
	    
		}
	
}