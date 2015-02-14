import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bsh.EvalError;
import bsh.Interpreter;
import static com.carlos.PSLog.*;

public class Stats {

	
/* { "Group Name", "Start Time", "End Time", "Duration", "Seconds", "Ban Read",
	 "Ban Succeeded", "CTN Succeeded", "Reject BANs", "BANs/Secs", "CTNs/Secs", 
	 "Secs / 1K BANs", "Secs / 1K CTNs" };*/
	
	
	static String[] matches = new String[] { 
			"Operational Script Job Activation",				// 0	MATCH
			"Operational Job ended successfuly on",				// 1
			"",													// 2
			"",													// 3
			"BANs                    read",						// 4
			"BANs                    succeeded",				// 5
			"CTNs                    succeeded",				// 6
			"BANs                    rejected",					// 7
			"CTN records             successfully rerated",		// 8
			"BAN records             successfully rerated",		// 9
			"BANs                    succeeded",				// 10
			"CTNs                    succeeded",				// 11
			"BAN records             read .",					// 12
			"CTN records             read",						// 13
			"Start Processing|MpCreateRLHSurcharge|Ban|"		// 14
			};
	
	
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); 
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BAN records             successfully rerated .");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTN records             successfully rerated .");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Job ended successfuly on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    succeeded . ");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    rejected . ");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTNs                    succeeded . ");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BAN records             read .");
	//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTN records             read .");

	
	SimpleDateFormat minTime = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	SimpleDateFormat maxTime = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	ArrayList<ArrayList<Integer>> array = new ArrayList<>();
	static List<String> sublist = new ArrayList<String>();
	List formatList = new ArrayList<String>(maxColumn);
	List recordList = new ArrayList<Double>(maxColumn);
	List<Date> formatStoredStartDateList = new ArrayList<Date>(maxColumn);
	List<Date> formatStoredEndDateList = new ArrayList<Date>(maxColumn);
	List durationList = new ArrayList<Double>(maxColumn);
	List thrusList = new ArrayList<Double>(maxColumn);
	List thrumtList = new ArrayList<Double>(maxColumn);
	List thruhList = new ArrayList<Double>(maxColumn);
	List abortedList = new ArrayList<String>(maxColumn);
	List abortedStatusList = new ArrayList<String>(maxColumn);
	List abortedRecordList = new ArrayList<Double>(maxColumn);

	Date storedStartDate;
	Date storedEndDate;
	// SimpleDateFormat storedStartTime = new
	// SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	// SimpleDateFormat storedEndTime = new
	// SimpleDateFormat("MM/d/yyyy HH:mm:ss");

	final static int FORMAT = 0;
	final static int RECORDS = 1;
	final static int DURATION = 2;
	final static int STATUS = 2;
	final static int THRUS = 3; // Throughput per seconds
	final static int THRUM = 4; // Throughput per minutes
	final static int THRUH = 5; // Throughput per hour
	final static int maxRow = 40;
	final static int maxColumn = 40; // since maxColumn is static it can be
										// defined after used in a non-static
										// List

	public static void main(String[] args) throws ParseException, EvalError {

		Stats mps = new Stats();
		//mps.parseMPSURCHRGCRELogs("MPSURCHRGCRE_C05M01Y2015_PROD.txt");
		mps.parseFileBilling("BLCONFIRM_C04M01Y2015_PET.txt");
		
//		SimpleTableExample mainFrame = new SimpleTableExample();
//		mainFrame.setVisible( true );
	}
	
	private void parseFileBilling(String strfileName) throws ParseException, EvalError {

		File file = null;
		OutputStream os = null;
		BufferedReader br = null;
		String line1 = "";
		String line2 = "";
		String cvsSplitBy = ",";
		int NumberCDRsPerFile = 1;

		storedStartDate = null;
		storedEndDate = null;

		String inputFileNameL1 = "";
		String inputFileNameL2 = "";
		String format = "";
		
		SimpleTableExample table = new SimpleTableExample();

		try {
			
			for(int i = 0; i < matches.length; i++){
				
				if(matches[i].equals("")) {
					continue;
				}
				
				br = new BufferedReader(new FileReader(strfileName));	
				//ps("i: " + i);

			int lineNumber = 0;			// Counter of current line read within the log file. Don't modify this line.
			int rowCounter = 0;			// Counter of current row inthe dataTable being populated.
			Pattern pifnl1 = null;
			
			while ((line1 = br.readLine()) != null) { // Reading a new line Billing log files

				if (lineNumber == 0){		//Printing the line you are interested in one time. Modify this with the line number of the first occurrence in the log file Ex: Line number 6 for CTN Succeede in BLPREP log file. Take this number form the comment line of interest below Pattern pifnl1 = Pattern statement.
					//ps("line1: " + line1);
				}
				
				lineNumber++;		// Incrementing the line counter because a new line was successfully read.
				if(i == 4) {
					/*ps("before matching: ");
					ps("matches[i]: " + matches[i]);
					ps("line1: " + line1);*/
				}
				if (line1.contains(matches[i])) {	//Modify this line to match the string you are interested from the array matches. Ex 8 is for "CTNs                    succeeded". Take this number form the comment line of interest below Pattern pifnl1 = Pattern statement.
					if(i == 0) {
						//ps("after matching: ");
					}
					//if(true){while(true);};
					
					//.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
					
					//Pattern pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:<< JOB STARTED [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
					
					
					switch(i){
					case 0:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
						ps("Activation i: " + i);
						break;
					case 1:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Job ended successfuly on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
						ps("ended i: " + i);
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    read");
						ps("BANsread  i: " + i);
						break;
					case 5:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    succeeded");
						ps("BANsrejected i: " + i);
						break;
					case 6:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTNs                    succeeded");
						break;
					case 7:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    rejected");
						break;
					/*case 8:
						break;
					case 9:
						break;
					case 10:
						break;
					case 11:
						break;
					case 12:
						break;*/
					default:
						pifnl1 = Pattern.compile("[A-Z]+_([A-Z0-9]+)_.*.log:<< JOB STARTED [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
						ps("default i: " + i);
						break;
					}
					
							//Uncomment the pattern you want to match in the read line. Ex: .compile("[A-Z]+_([A-Z0-9]+)_.*.log:Start Processing.*"); this is the missing par of the Pattern after the equal sign (=) 
							// LINE=0 MATCH=0 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							// LINE=0 MATCH=0 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:<< JOB STARTED [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							//./BLPREP_B04012015A11_CAN_20150108_104738.log:<< JOB STARTED Thu Jan 8 10:47:38 EST 2015 >>
							// LINE=7 MATCH=4 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Job ended successfuly on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							// LINE=5 MATCH=5 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    succeeded . "); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							// LINE=4 MATCH=6 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BANs                    rejected . "); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							//LINE=0 MATCH=0 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
							//LINE=7 MATCH=4 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Job ended successfuly on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}");
							//LINE=7 MATCH=7 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:Start Processing.*");
							//LINE=6 MATCH=8 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTNs                    succeeded . "); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							//LINE=35 MATCH=9 BLCYRRT //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BAN records             read ."); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							//LINE=44 MATCH=10 BLCYRRT //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTN records             read ."); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							//LINE=36 MATCH=1 BLCYRRT //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) BAN records             successfully rerated ."); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							//LINE=45 MATCH=3 BLCYRRT //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTN records             successfully rerated ."); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
					
					//Start Processing|MpCreateRLHSurcharge|Ban|

							Matcher mifnl1 = pifnl1.matcher(line1);
					if (mifnl1.find()) {
						inputFileNameL1 = mifnl1.group(1);
						//System.out.println(inputFileNameL1);
							
						if(i == 0) {	// adding a row and populating the primary key
							//ps("# of Rows of table:" + table.table.getRowCount());
							//ps("# of Columns of table:" + table.table.getColumnCount());
						    if(table.table.getRowCount() < rowCounter + 1) {
						    	table.defTableModel.addRow(table.newRowData);
						    }
							table.defTableModel.setValueAt(inputFileNameL1, rowCounter, i);
						}
						 
						inputFileNameL2 = mifnl1.group(2);
						
						if(table.defTableModel.getValueAt(rowCounter, 0).equals(inputFileNameL1)) {
							table.defTableModel.setValueAt(inputFileNameL2, rowCounter, i + 1);
						}
						
						//System.out.println("inputFileNameL2: " + inputFileNameL2);
						//ps("line1: " + line1);
						//mainFrame.data[rowCounter][2] = inputFileNameL2;
							//ps(inputFileNameL1 + " " + inputFileNameL2);
							rowCounter++;
					} else {
						line1 = "";
						line2 = "";
						continue;
					}
				} else { 
					continue;
				}
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

//			ps("\nPrinting List:\n");
//			Collections.sort(groupName);
//			for(String element: groupName) {
//				ps(element);
//			}
			table.table.getRowSorter().toggleSortOrder(0);
			table.setVisible(true);

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//printReport();
		}

	}
	
	/*public void printReport() throws ParseException {

		String[][] resultsTable = new String[maxRow][70];
		String[][] abortedResultsTable = new String[maxRow][70];

		long d1 = 0;
		long d2 = 0;
		double duration = 0;
		double recSum = 0;
		double secSum = 0;

		// /////////////////// PRINTING COMPLETED FILES ///////////////////////
		SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");

		// ps("storedStartDate: " + sdf.format(storedStartDate));
		// ps("storedEndDate: " + sdf.format(storedEndDate));

		d1 = sdf.parse(sdf.format(storedStartDate)).getTime();
		d2 = sdf.parse(sdf.format(storedEndDate)).getTime();

		duration = (int) ((d2 - d1) / 1000);

		if (duration == 0) {
			duration = 1;
		}
		// /////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////

		resultsTable[0][FORMAT] = "|File_Format|";
		resultsTable[0][RECORDS] = "|No_Records|";
		resultsTable[0][DURATION] = "|Duration(secs)|";
		resultsTable[0][THRUS] = "|Recs/Secs|";
		resultsTable[0][THRUM] = "|Recs/Min|";
		resultsTable[0][THRUH] = "|Recs/Hr|";

		for (int i = 1; i < formatList.size() + 1; i++) {

			resultsTable[i][FORMAT] = (String) formatList.get(i - 1);
			resultsTable[i][RECORDS] = recordList.get(i - 1).toString();
			resultsTable[i][DURATION] = durationList.get(i - 1).toString();
			resultsTable[i][THRUS] = thrusList.get(i - 1).toString();
			resultsTable[i][THRUM] = thrumtList.get(i - 1).toString();
			resultsTable[i][THRUH] = thruhList.get(i - 1).toString();
		}

		for (int i = 0; i < formatList.size(); i++) {
			recSum += (double) recordList.get(i);// Double.parseDouble(recordList.get(i).toString());
		}

		resultsTable[formatList.size() + 1][FORMAT] = "Total";
		resultsTable[formatList.size() + 1][RECORDS] = String.valueOf(recSum);// recordList.get(i-1).toString();
		resultsTable[formatList.size() + 1][DURATION] = String
				.valueOf(duration);// recordList.get(i-1).toString();
		resultsTable[formatList.size() + 1][THRUS] = String
				.valueOf((int) (recSum / duration));
		resultsTable[formatList.size() + 1][THRUM] = String
				.valueOf((int) ((recSum / duration) * 60));
		resultsTable[formatList.size() + 1][THRUH] = String
				.valueOf((int) ((recSum / duration) * 3600));

		ps("");

		for (int i = 0; i < formatList.size() + 2; i++) {
			for (int j = 0; j < 6; j++) {
				if (i == 0) {
					psn(resultsTable[i][j] + "\t");
				} else {
					if (j == 0) {
						psn(resultsTable[i][j] + "\t");
					} else {
						psn(NumberFormat.getNumberInstance(Locale.US).format(
								Double.parseDouble(resultsTable[i][j])));
						psn("\t");
					}
				}
			}
			ps("");
		}
	}*/
	
}
//  CURRENT:
//	MAIN DRIVER STATS: MD_R714_20th_ON_Aug.txt
//
//	|File_Format|	|No_Records|	|Duration(secs)|	|Recs/Secs|	|Recs/Min|	|Recs/Hr|	
//	GSM1205	144,097	281	512.801	30,768.043	1,846,082.562	
//	IUM1205	541,700	273	1,984.249	119,054.945	7,143,296.703	
//	SGW1215	222,896	153	1,456.837	87,410.196	5,244,611.765	
//	IMS1205	302,347	150	2,015.647	120,938.8	7,256,328	
//	IUM1215	318,570	174	1,830.862	109,851.724	6,591,103.448	
//	RTILD	9,056	9	1,006.222	60,373.333	3,622,400	
//	DPSAMA	237,073	325	729.455	43,767.323	2,626,039.385	
//	PGW1215	25,967	124	209.411	12,564.677	753,880.645	
//	ERIC147	9,271	21	441.476	26,488.571	1,589,314.286	
//	GSM1215	21,837	20	1,091.85	65,511	3,930,660	
//	Total	1,832,814	596	3,075	184,511	11,070,688	
// 	########################################################################################
//	########################################################################################

//	TARGET:
//	MAIN DRIVER STATS: MD_R714_20th_ON_Aug.txt
//
//	BLCYRRT-Group A										
//	Group Name	Start Time (EDT)	End Time (EDT)	Duration	Seconds	BAN 	CTN 	BANs Processed/Seconds	CTNs Processed/Seconds	Processing Time for 1000 BANs	Processing Time for 1000 CTNs
//	B04112014A01 (1 TAB)	11/7/2014 14:02:44	11/8/2014 4:47:29	14:44:45	53085	0	1988	0	0.037449374	#DIV/0!	26702.7163
//	B04112014A02 (1 TAB)	11/7/2014 14:00:21	11/8/2014 2:54:05	12:53:44	46424	0	1827	0	0.039354644	#DIV/0!	25409.96169
//	Total (3 TABS)			278:17:57	1001877	1,621	3815	0.001617963	0.003807853	618061.0734	262615.2031

	

class SimpleTableExample extends 	JFrame
{
	// Instance attributes used in this example
	private	JPanel		topPanel;
	DefaultTableModel defTableModel;
	public	JTable		table;
	private	JScrollPane scrollPane;

	
	//Create a two dimensional array to hold the data for the JTable. 
	//Object[][] data = {{1,1,1},{2,2,2},{3,3,3},{4,4,4}};  
	
	//A string array containing the column names for the JTable. 
	String columnNames[] = { "Group Name", "Start Time", "End Time", "Duration", "Seconds", "Ban Read",
							 "Ban Succeeded", "CTN Succeeded", "Reject BANs", "BANs/Secs", "CTNs/Secs", 
							 "Secs / 1K BANs", "Secs / 1K CTNs" };

	
	// Create an empty row
	Object[] newRowData = { "", "", "", "", "", "", "", "", "", "", "", ""}; 
	
	// Create a two dimensional array to hold the data for the JTable.
	Object[][] data ={{}};
	/*{
	{ "1", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	{ "", "", "", "", "", "", "", "", "", "", "", ""},
	};*/
	
	//Constructor of main frame
	public SimpleTableExample()
	{
		// Set the frame characteristics
		setTitle( "Cycle 4 Month 01 Year 2015 P_20-BLPREP-Group B" );
		setSize( 1300, 150 );
		setBackground( Color.gray );
		
		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		
		//Create a DeafultTableModel object for another JTable 
		defTableModel = new DefaultTableModel(data,columnNames); 
		//JTable anotherJTable = new JTable(defTableModel);
		
		//Create a new table instance
		table = new JTable(defTableModel);
		//table = new JTable(dataValues,columnNames);
		
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setCellSelectionEnabled(true);
	
		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}
}
