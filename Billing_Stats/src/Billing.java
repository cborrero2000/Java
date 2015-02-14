import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Billing {

	static String[] matches = new String[] { "Operational Script Job Activation, on",	// 0	MATCH
			"BAN records             successfully rerated",								// 1
			"SIMPLE BAN records      successfully rerated",								// 2
			"CTN records             successfully rerated",								// 3
			"Operational Job ended successfuly on",										// 4
			"BANs                    succeeded",										// 5
			"BANs                    rejected",											// 6
			"Start Processing|MpCreateRLHSurcharge|Ban|",								// 7
			"CTNs                    succeeded",										// 8
			"BAN records             read .",											// 9
			"CTN records             read",												// 10
			"Start Processing|MpCreateRLHSurcharge|Ban|"								// 11
			};
	
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

	// Custom Print methods
	public static void ps(Object obj) { // print screen function. Just as
										// System.out.print
		System.out.println(obj);
	}

	public static void psf(Object obj1, Object obj2) { // print screen function.
														// Just as
														// System.out.printf
		System.out.printf(obj1.toString(), obj2);
	}

	public static void psn(Object obj) { // print screen NO new line function.
											// Just as System.out.println
		System.out.print(obj);
	}

	public static void ps(Object obj1, Object obj2) { // print screen new line
														// function.Just as
														// System.out.printf
														// plus "\n" ln
		System.out.printf(obj1.toString() + "\n", obj2);
	}

	public static void main(String[] args) throws ParseException {

		Billing mps = new Billing();
		//mps.parseMPSURCHRGCRELogs("MPSURCHRGCRE_C05M01Y2015_PROD.txt");
		mps.parseFileBilling("bill_05_JAN_2015.txt");

	}

	public void populateArrayListsStatsMAF(String line1, String line2)
			throws ParseException {
		String format = "";
		double recordCount = 0;
		double duration = 0;
		long d1 = 0;
		long d2 = 0;
		int index = 0;
		String strStartTime = "";
		String strEndTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
		Pattern pdatetime;// =
							// Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
							// //date and time pattern: 08/16/2014 20:39:51
		Matcher mdatetime;// = pdatetime.matcher(line1);'
		Date currentStartDate;
		Date currentEndDate;
		Date formatStoredStartDate;
		Date formatStoredEndDate;

		// //// Capturing File Format ////////////
		Pattern pf = Pattern.compile("_F(.+)_I"); // group 0 Whole Regex, group
													// 1 First content in
													// parenthesis from left to
													// right
		Matcher mf = pf.matcher(line1);

		if (mf.find()) {
			format = mf.group(1);

			index = formatList.indexOf(format);

			if (format.equals("IMM1205")) {
				return;
			}

		} else {
			return;
		}
		// //// Capturing Number of Records ////////////
		Pattern pr = Pattern.compile("_R(\\d+)"); // group 0 Whole Regex, group
													// 1 First content in
													// parenthesis from left to
													// right
		Matcher mr = pr.matcher(line1);

		if (mr.find()) {
			recordCount = Integer.parseInt(mr.group(1));
		} else {
			return;
		}
		// //// Capturing Start Time and converting it into milliseconds
		// ////////////
		pdatetime = Pattern
				.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"); // date
																		// and
																		// time
																		// pattern:
																		// 08/16/2014
																		// 20:39:51
		mdatetime = pdatetime.matcher(line1);

		if (mdatetime.find()) {
			strStartTime = mdatetime.group(0);
			currentStartDate = sdf.parse(strStartTime);

			if (storedStartDate == null) {
				storedStartDate = sdf.parse(strStartTime);
			}

			if (storedStartDate.after(currentStartDate)) {
				storedStartDate = currentStartDate;
			}

			if (formatList.contains(format)) {
				formatStoredStartDate = formatStoredStartDateList.get(index);
				if (formatStoredStartDate.after(currentStartDate)) {
					formatStoredStartDate = currentStartDate;
				}
			} else {
				formatStoredStartDate = currentStartDate;
			}
		} else {
			return;
		}

		// //// Capturing End Time and converting it into milliseconds
		// ////////////
		mdatetime = pdatetime.matcher(line2);

		if (mdatetime.find()) {
			strEndTime = mdatetime.group(0);
			currentEndDate = sdf.parse(strEndTime);

			if (storedEndDate == null) {
				storedEndDate = sdf.parse(strEndTime);
			}

			if (storedEndDate.before(currentEndDate)) {
				storedEndDate = currentEndDate;
			}

			if (formatList.contains(format)) {

				formatStoredEndDate = formatStoredEndDateList.get(index);

				if (formatStoredEndDate.before(currentEndDate)) {

					formatStoredEndDate = currentEndDate;
				}
			} else {

				formatStoredEndDate = currentEndDate;
			}

		} else {
			return;
		}

		// //// Capturing duration from the earliest start time and latest end
		// time //////////////////////////
		d1 = sdf.parse(sdf.format(formatStoredStartDate)).getTime();
		d2 = sdf.parse(sdf.format(formatStoredEndDate)).getTime();

		duration = (int) ((d2 - d1) / 1000);

		if (duration == 0) {
			duration = 1;
		}

		// /////////////Populating stats in ArrayLists based on format
		// ////////////////////////////////

		if (formatList.contains(format)) {

			recordList.get(index);
			recordCount += (double) recordList.get(index);

			recordList.set(index, recordCount);
			durationList.set(index, duration);
			thrusList.set(index, (recordCount / duration));
			thrumtList.set(index, (recordCount / (duration) * 60));
			thruhList.set(index, (recordCount / duration) * 3600);

			formatStoredStartDateList.set(index, formatStoredStartDate);
			formatStoredEndDateList.set(index, formatStoredEndDate);
		} else { // If it's new format in arraylist
			formatList.add(format);
			recordList.add(recordCount);
			durationList.add(duration);
			thrusList.add(recordCount / duration);
			thrumtList.add(recordCount / (duration * 60));
			thruhList.add(recordCount / (duration * 3600));
			formatStoredStartDateList.add(formatStoredStartDate);
			formatStoredEndDateList.add(formatStoredEndDate);
		}
	}

	public void populateArrayListStatsGUIDE(String line1, String line2,
			String line3, String line4) throws ParseException {
		String format = "";
		double errorRecordCount = 0;
		double recordCount = 0;
		double duration = 0;
		long d1 = 0;
		long d2 = 0;
		int index = 0;
		String strStartTime = "";
		String strEndTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss yyyy"); // Aug
																				// 17
																				// 20:05:55
																				// 2014
		Pattern pdatetime;// =
							// Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
							// //date and time pattern: 08/16/2014 20:39:51
		Matcher mdatetime;// = pdatetime.matcher(line1);
		Date currentStartDate;
		Date currentEndDate;
		Date formatStoredStartDate;
		Date formatStoredEndDate;

		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 17 20:09:21 2014
		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Open input file Id:
		// 894027580 ,in :
		// js/up/data/ERIC147/STOGMSC0_FERIC147_ID32846_T20140816160825_R2331/STOGMSC0_D140
		// at Sun Aug 17 20:09:21 2014
		// line2 ./MPSGUIDE_CAN_13004_20140817_200800.log: File name: USAGE_CAN
		// Alias: USAGE No. of Records 221 at Sun Aug 17 20:09:21 2014
		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Open input file Id:
		// 894027580 ,in :
		// js/up/data/ERIC147/STOGMSC0_FERIC147_ID32846_T20140816160825_R2331/STOGMSC0_D140
		// at Sun Aug 17 20:09:21 2014
		// line3: ./MPSGUIDE_CAN_13004_20140817_200800.log:Program
		// mpgd_100mn.cbl has completed successfuly at Sun Aug 17 20:09:29 2014

		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 24 13:10:55 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Open input file Id: 894189721
		// ,in :
		// js/up/data/GSM1215/SVAGSN03_FGSM1215_ID1021_T20140821112417_R7879/SVAGSN03_D1408
		// at Sun Aug 24 13:10:56 2014
		// line2 ./MPSGUIDE_CAN_7373_20140824_131055.log: File name: USAGE_CAN
		// Alias: USAGE No. of Records 5366 at Sun Aug 24 13:10:56 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Open input file Id: 894189721
		// ,in :
		// js/up/data/GSM1215/SVAGSN03_FGSM1215_ID1021_T20140821112417_R7879/SVAGSN03_D1408
		// at Sun Aug 24 13:10:56 2014
		// line3 ./MPSGUIDE_CAN_7373_20140824_131055.log: File name:
		// egd_CAN_ERRORS_0.dat Alias: ERROR No. of Records 2 at Sun Aug 24
		// 13:11:27 2014
		// line4 ./MPSGUIDE_CAN_7373_20140824_131055.log:Program mpgd_100mn.cbl
		// has completed with error(s) at Sun Aug 24 13:11:27 2014

		// //// Capturing error records Format ////////////
		if (line3.contains("Alias: ERROR   No. of Records")) {
			Pattern per = Pattern.compile("No. of Records (\\d+) at"); // getting
																		// format
			Matcher mer = per.matcher(line3);

			if (mer.find()) {

				errorRecordCount = Integer.parseInt(mer.group(1));

			} else {
				return;
			}
		} else {
			errorRecordCount = 0;
		}

		// //// Capturing File Format ////////////
		Pattern pf = Pattern.compile("_F(.+)_I"); // getting format
		Matcher mf = pf.matcher(line1);

		if (mf.find()) {

			format = mf.group(1);

			index = formatList.indexOf(format);
		} else {
			return;
		}
		// //// Capturing Number of Records ////////////
		// /MPSGUIDE_CAN_2251_20140817_201029.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 90619 at Sun Aug 17 20:10:30 2014
		// ./MPSGUIDE_CAN_7341_20140824_131054.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 8 at Sun Aug 24 13:10:54 2014
		Pattern pr = Pattern
				.compile("MPSGUIDE_CAN.*Alias: USAGE   No. of Records (\\d+)"); // getting
																				// the
																				// records
		Matcher mr = pr.matcher(line2);

		if (mr.find()) {
			recordCount = Integer.parseInt(mr.group(1));
			recordCount -= errorRecordCount;
		} else {
			return;
		}
		// //// Capturing Start Time and converting it into milliseconds
		// ////////////
		pdatetime = Pattern.compile("(Aug \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4})"); // Getting
																					// Start
																					// time
		mdatetime = pdatetime.matcher(line1);

		if (mdatetime.find()) {
			strStartTime = mdatetime.group(0);
			currentStartDate = sdf.parse(strStartTime);

			if (storedStartDate == null) {
				storedStartDate = sdf.parse(strStartTime);
			}

			if (storedStartDate.after(currentStartDate)) {
				storedStartDate = currentStartDate;
			}

			if (formatList.contains(format)) {
				formatStoredStartDate = formatStoredStartDateList.get(index);
				if (formatStoredStartDate.after(currentStartDate)) {
					formatStoredStartDate = currentStartDate;
				}
			} else {
				formatStoredStartDate = currentStartDate;
			}
		} else {
			return;
		}

		// //// Capturing End Time and converting it into milliseconds
		// ////////////
		mdatetime = pdatetime.matcher(line3);

		if (mdatetime.find()) {
			strEndTime = mdatetime.group(0);
			currentEndDate = sdf.parse(strEndTime);

			if (storedEndDate == null) {
				storedEndDate = sdf.parse(strEndTime);
			}

			if (storedEndDate.before(currentEndDate)) {
				storedEndDate = currentEndDate;
			}

			if (formatList.contains(format)) {

				formatStoredEndDate = formatStoredEndDateList.get(index);

				if (formatStoredEndDate.before(currentEndDate)) {

					formatStoredEndDate = currentEndDate;
				}
			} else {

				formatStoredEndDate = currentEndDate;
			}

		} else {
			return;
		}

		duration = (int) ((d2 - d1) / 1000);

		if (duration == 0) {
			duration = 1;
		}

		// //// Capturing duration from the earliest start time and latest end
		// time //////////////////////////
		d1 = sdf.parse(sdf.format(formatStoredStartDate)).getTime();
		d2 = sdf.parse(sdf.format(formatStoredEndDate)).getTime();

		duration = (int) ((d2 - d1) / 1000);

		if (duration == 0) {
			duration = 1;
		}

		// /////////////Populating stats in ArrayLists based on format
		// ////////////////////////////////

		if (formatList.contains(format)) {

			recordList.get(index);
			recordCount += (double) recordList.get(index);

			recordList.set(index, recordCount);
			durationList.set(index, duration);
			thrusList.set(index, (recordCount / duration));
			thrumtList.set(index, (recordCount / (duration) * 60));
			thruhList.set(index, (recordCount / duration) * 3600);

			formatStoredStartDateList.set(index, formatStoredStartDate);
			formatStoredEndDateList.set(index, formatStoredEndDate);
		} else { // If it's new format in arraylist
			formatList.add(format);
			recordList.add(recordCount);
			durationList.add(duration);
			thrusList.add(recordCount / duration);
			thrumtList.add(recordCount / (duration * 60));
			thruhList.add(recordCount / (duration * 3600));
			formatStoredStartDateList.add(formatStoredStartDate);
			formatStoredEndDateList.add(formatStoredEndDate);
		}
	}

	public void populateArrayListStatsRATE(String line1, String line2,
			String line3) throws ParseException {
		String format = "";
		double recordCount = 0;
		double duration = 0;
		long d1 = 0;
		long d2 = 0;
		int index = 0;
		String strStartTime = "";
		String strEndTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss yyyy"); // Aug
																				// 17
																				// 20:05:55
																				// 2014
		Pattern pdatetime;// =
							// Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
							// //date and time pattern: 08/16/2014 20:39:51
		Matcher mdatetime;// = pdatetime.matcher(line1);
		Date currentStartDate;
		Date currentEndDate;
		Date formatStoredStartDate;
		Date formatStoredEndDate;

		// //// Capturing File Format ////////////
		Pattern pf = Pattern.compile("_F(.+)_I"); // getting format
		Matcher mf = pf.matcher(line1);

		if (mf.find()) {
			format = mf.group(1);

			index = formatList.indexOf(format);
		} else {
			return;
		}

		// //// Capturing Number of Records ////////////
		Pattern pr = Pattern
				.compile("MPSRATE.+_CAN.+Alias: USAGE   No. of Records (\\d+)"); // getting
																					// the
																					// records
		Matcher mr = pr.matcher(line2);

		if (mr.find()) {
			recordCount = Integer.parseInt(mr.group(1));
		} else {
			return;
		}
		// //// Capturing Start Time and converting it into milliseconds
		// ////////////
		pdatetime = Pattern
				.compile("([A-Za-z]{3} \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4})"); // Getting
																				// Start
																				// time

		mdatetime = pdatetime.matcher(line1);

		if (mdatetime.find()) {
			strStartTime = mdatetime.group(0);
			currentStartDate = sdf.parse(strStartTime);

			if (storedStartDate == null) {
				storedStartDate = sdf.parse(strStartTime);
			}

			if (storedStartDate.after(currentStartDate)) {
				storedStartDate = currentStartDate;
			}

			if (formatList.contains(format)) {
				formatStoredStartDate = formatStoredStartDateList.get(index);
				if (formatStoredStartDate.after(currentStartDate)) {
					formatStoredStartDate = currentStartDate;
				}
			} else {
				formatStoredStartDate = currentStartDate;
			}
		} else {
			return;
		}

		// //// Capturing End Time and converting it into milliseconds
		// ////////////
		mdatetime = pdatetime.matcher(line3);

		if (mdatetime.find()) {
			strEndTime = mdatetime.group(0);
			currentEndDate = sdf.parse(strEndTime);

			if (storedEndDate == null) {
				storedEndDate = sdf.parse(strEndTime);
			}

			if (storedEndDate.before(currentEndDate)) {
				storedEndDate = currentEndDate;
			}

			if (formatList.contains(format)) {

				formatStoredEndDate = formatStoredEndDateList.get(index);

				if (formatStoredEndDate.before(currentEndDate)) {

					formatStoredEndDate = currentEndDate;
				}
			} else {

				formatStoredEndDate = currentEndDate;
			}
		} else {
			return;
		}
		// //// Capturing duration from the earliest start time and latest end
		// time //////////////////////////
		d1 = sdf.parse(sdf.format(formatStoredStartDate)).getTime();
		d2 = sdf.parse(sdf.format(formatStoredEndDate)).getTime();

		duration = (int) ((d2 - d1) / 1000);

		if (duration == 0) {
			duration = 1;
		}

		// /////////////Populating stats in ArrayLists based on format
		// ////////////////////////////////

		if (formatList.contains(format)) {

			recordList.get(index);
			recordCount += (double) recordList.get(index);

			recordList.set(index, recordCount);
			durationList.set(index, duration);
			thrusList.set(index, (recordCount / duration));
			thrumtList.set(index, (recordCount / (duration) * 60));
			thruhList.set(index, (recordCount / duration) * 3600);

			formatStoredStartDateList.set(index, formatStoredStartDate);
			formatStoredEndDateList.set(index, formatStoredEndDate);
		} else { // If it's new format in arraylist
			formatList.add(format);
			recordList.add(recordCount);
			durationList.add(duration);
			thrusList.add(recordCount / duration);
			thrumtList.add(recordCount / (duration * 60));
			thruhList.add(recordCount / (duration * 3600));

			formatStoredStartDateList.add(formatStoredStartDate);
			formatStoredEndDateList.add(formatStoredEndDate);
		}
	}

	public void printReport() throws ParseException {

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
	}

	private void populateAbortedListStats(String line) {

		/*
		 * if ( line.contains(matches[2]) || line.contains(matches[3]) ) {
		 * ps("line: " + line);
		 * 
		 * String format=""; int recordCount=0;
		 * 
		 * ////// Capturing Number of Records //////////// Pattern pr =
		 * Pattern.compile("_R(\\d+)"); // group 0 Whole Regex, group 1 First
		 * content in parenthesis from left to right Matcher mr =
		 * pr.matcher(line);
		 * 
		 * if(mr.find()) { recordCount = Integer.parseInt(mr.group(1));
		 * System.out.println("recordCount: " + recordCount);
		 * System.out.println(line); }
		 * ///////////////////////////////////////////////
		 * 
		 * //abortedList with status AF or AE String abortedStatus=""; Pattern
		 * pa = Pattern.compile(" with status (A[FE])"); // group 0 Whole Regex,
		 * group 1 First content in parenthesis from left to right Matcher ma =
		 * pa.matcher(line);
		 * 
		 * if(ma.find()) { abortedStatus = ma.group(1);
		 * System.out.println("abortedStatus: " + abortedStatus); }
		 * 
		 * if(abortedList.contains(abortedStatus)){ ps("it contains " +
		 * abortedStatus); int index = abortedList.indexOf(abortedStatus);
		 * 
		 * recordCount += (double)abortedRecordList.get(index);
		 * ps("recordCount: " + recordCount);
		 * 
		 * abortedRecordList.set(index, recordCount); } else{ // If it's new
		 * format in arraylist abortedList.add(abortedStatus);
		 * abortedRecordList.add(recordCount); }
		 * 
		 * ps("abortedList: " + abortedList); ps("abortedRecordList: " +
		 * abortedRecordList); }
		 */
	}

	
	private void parseMPSURCHRGCRELogs(String strfileName) throws ParseException { 

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
		//int B04112014B01 = 0;
		int B05012015A01 =0;
		int B05012015A02 =0;
		int B05012015A03 =0;
		int B05012015A04 =0;
		int B05012015A05 =0;
		int B05012015A06 =0;
		int B05012015A07 =0;
		int B05012015A08 =0;
		int B05012015A09 =0;
		int B05012015A10 =0;
		int B05012015A11 =0;
		int B05012015A12 =0;
		int B05012015A13 =0;
		int B05012015A14 =0;
		int B05012015A15 =0;
		int B05012015A16 =0;
		int B05012015A17 =0;
		int B05012015A18 =0;
		int B05012015A19 =0;
		int B05012015A20 =0;
		int B05012015B01 =0;
		int B05012015B02 =0;
		int B05012015B03 =0;
		int B05012015B04 =0;
		int B05012015B05 =0;
		int B05012015B06 =0;
		int B05012015B07 =0;
		int B05012015B08 =0;
		int B05012015B09 =0;
		int B05012015B10 =0;
		int B05012015B11 =0;
		int B05012015B12 =0;
		int B05012015B13 =0;
		int B05012015B14 =0;
		int B05012015B15 =0;
		int B05012015B16 =0;
		int B05012015B17 =0;
		int B05012015B18 =0;
		int B05012015B19 =0;
		int B05012015B20 =0;


		try {
			br = new BufferedReader(new FileReader(strfileName));

			int lineNumber = 0;			// Counter of current line read within the log file. Don't modify this line.

			while ((line1 = br.readLine()) != null) { // Reading a new line Billing log files

				/*if (lineNumber == 15300){		//Printing the line you are interested in one time. Modify this with the line number of the first occurrence in the log file Ex: Line number 6 for CTN Succeede in BLPREP log file. Take this number form the comment line of interest below Pattern pifnl1 = Pattern statement.
					ps("line1: " + line1);
				}*/

				lineNumber++;		// Incrementing the line counter because a new line was successfully read.

				if (line1.contains(matches[11])) {	//Modify this line to match the string you are interested from the array matches. Ex 8 is for "CTNs                    succeeded". Take this number form the comment line of interest below Pattern pifnl1 = Pattern statement.

					Pattern pifnl1 = Pattern	//Uncomment the pattern you want to match in the read line. Ex: .compile("[A-Z]+_([A-Z0-9]+)_.*.log:Start Processing.*"); this is the missing par of the Pattern after the equal sign (=) 
					// LINE=0 MATCH=0 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
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
					/*LINE=45 MATCH=11 BLCYRRT */.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\Start Processing");//|MpCreateRLHSurcharge|Ban|(\\d+)|BillSeq|(\\d+)|AU_tbl_name|AU(\\d+)|"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right									
					             //./MPSURCHRGCRE_B05012015B04_CAN_20150109_134423.log:Start Processing|MpCreateRLHSurcharge|Ban|720580968|BillSeq|3|AU_tbl_name|AU0501|
					//LINE=45 MATCH=11 Start Processing|MpCreateRLHSurcharge|Ban|
					
					Matcher mifnl1 = pifnl1.matcher(line1);
					/*ps("Before Matching");
					ps("mifnl1: " + mifnl1);
					ps("line1: " + line1);*/
					if (mifnl1.find()) {
						//ps("Inside Matching");
						inputFileNameL1 = mifnl1.group(1);
						//System.out.println("inputFileNameL1: " + inputFileNameL1);

						switch(inputFileNameL1){

						case "B05012015A01":
							//ps("incrementing B05012015A01");
							B05012015A01++;
							break;

						case "B05012015A02":
							B05012015A02++;
							break;

						case "B05012015A03":
							B05012015A03++;
							break;

						case "B05012015A04":
							B05012015A04++;
							break;

						case "B05012015A05":
							B05012015A05++;
							break;

						case "B05012015A06":
							B05012015A06++;
							break;

						case "B05012015A07":
							B05012015A07++;
							break;

						case "B05012015A08":
							B05012015A08++;
							break;

						case "B05012015A09":
							B05012015A09++;
							break;

						case "B05012015A10":
							B05012015A10++;
							break;

						case "B05012015A11":
							B05012015A11++;
							break;

						case "B05012015A12":
							B05012015A12++;
							break;

						case "B05012015A13":
							B05012015A13++;
							break;

						case "B05012015A14":
						B05012015A14++;
						break;

						case "B05012015A15":
						B05012015A15++;
						break;
						
						case "B05012015A16":
						B05012015A16++;
						break;
						
						case "B05012015A17":
						B05012015A17++;
						break;
						
						case "B05012015A18":
						B05012015A18++;
						break;
						
						case "B05012015A19":
						B05012015A19++;
						break;
						
						case "B05012015A20":
						B05012015A20++;
						break;

						case "B05012015B01":
						B05012015B01++;
						break;
						
						case "B05012015B02":
						B05012015B02++;
						break;
						
						case "B05012015B03":
						B05012015B03++;
						break;
						
						case "B05012015B04":
							B05012015B04++;
							//ps("incrementing B05012015B04");
						break;
						
						case "B05012015B05":
						B05012015B05++;
						break;
						
						case "B05012015B06":
						B05012015B06++;
						break;
						
						case "B05012015B07":
						B05012015B07++;
						break;
						
						case "B05012015B08":
						B05012015B08++;
						break;
						
						case "B05012015B09":
						B05012015B09++;
						break;
						
						case "B05012015B10":
						B05012015B10++;
						break;
						
						case "B05012015B11":
						B05012015B11++;
						break;
						
						case "B05012015B12":
						B05012015B12++;
						break;
						
						case "B05012015B13":
						B05012015B13++;
						break;
						
						case "B05012015B14":
						B05012015B14++;
						break;
						
						case "B05012015B15":
						B05012015B15++;
						break;
						
						case "B05012015B16":
						B05012015B16++;
						break;
						
						case "B05012015B17":
						B05012015B17++;
						break;
						
						case "B05012015B18":
						B05012015B18++;
						break;
						
						case "B05012015B19":
						B05012015B19++;
						break;
						
						case "B05012015B20":
						B05012015B20++;
						break;

						default:
							ps("nothing incremented");

						}

					} else {
						line1 = "";
						line2 = "";
						continue;
					}

					line2 = br.readLine();

					Pattern pifnl2 = Pattern
							.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // checking
					// if  for second line. if it referees/ to// the// same// file// from// line// 1
					Matcher mifnl2 = pifnl2.matcher(line2);

					if (mifnl2.find()) {
						inputFileNameL2 = mifnl2.group(0);
						// 	System.out.println("inputFileNameL2: " +
						// inputFileNameL2);
					} else {
						line1 = "";
						line2 = "";
						continue;
					}

					if (inputFileNameL1.equals(inputFileNameL2) != true) {
						line1 = "";
						line2 = "";
						continue;
					}

					if (line2.contains(matches[1])) {
						populateArrayListsStatsMAF(line1, line2);
					} else {
						populateAbortedListStats(line2);
					}
					// /////////////////////////////////////////////////////////////////
				} else { // capture AF and AE status
					// 	FSGW1215_ID08934_T20140816160825_R68885.DAT has
					// been finished on 08/16/2014 20:44:36 with status
					// AF.

					populateAbortedListStats(line1);

					/*
					* if (line1.contains(matches[2]) ||
					* line1.contains(matches[3]) ) { int recordCount=0;
					* 
					* //////////////////////////////////////////////////////////
					* /////// //inputFileName:
					* SVA2BC03_FGSM1205_ID5592_T20140816160825_R4242//
					* /////////
					* ////////////////////////////////////////////////////////
					* 
					* // Pattern pifnl1 =
					* Pattern.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // File
					* name pattern: group 0 Whole Regex, group 1 First content
					* in parenthesis from left to right // Matcher mifnl1 =
					* pifnl1.matcher(line1);
					* 
					* ////// Capturing Number of Records //////////// Pattern
					* pr = Pattern.compile("_R(\\d+)"); // group 0 Whole Regex,
					* group 1 First content in parenthesis from left to right
					* Matcher mr = pr.matcher(line1);
					* 
					* if(mr.find()) { recordCount =
					* Integer.parseInt(mr.group(1));
					* System.out.println("recordCount: " + recordCount);
					* System.out.println(line1); }
					* ///////////////////////////////////////////////
					* 
					* //abortedList with status AF or AE String
					* abortedStatus=""; Pattern pa =
					* Pattern.compile(" with status (A[FE])"); // group 0 Whole
					* Regex, group 1 First content in parenthesis from left to
					* right Matcher ma = pa.matcher(line1);
					* 
					* if(ma.find()) { abortedStatus = ma.group(1);
					* System.out.println("abortedStatus: " + abortedStatus); }
					* 
					* if(abortedList.contains(abortedStatus)){
					* ps("it contains " + abortedStatus); int index =
					* abortedList.indexOf(format);
					* 
					* recordCount += (double)abortedRecordList.get(index); //
					* ps("recordCount: " + recordCount);
					* 
					* abortedRecordList.set(index, recordCount); } else{ // If
					* it's new format in arraylist
					* abortedList.add(abortedStatus);
					* abortedRecordList.add(recordCount); } }
					*/

					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ps("\nMAIN DRIVER STATS: " + strfileName);
			ps("B05012015A01 " + B05012015A01);
			ps("B05012015A02 " + B05012015A02);
			ps("B05012015A03 " + B05012015A03);
			ps("B05012015A04 " + B05012015A04);
			ps("B05012015A05 " + B05012015A05);
			ps("B05012015A06 " + B05012015A06);
			ps("B05012015A07 " + B05012015A07);
			ps("B05012015A08 " + B05012015A08);
			ps("B05012015A09 " + B05012015A09);
			ps("B05012015A10 " + B05012015A10);
			ps("B05012015A11 " + B05012015A11);
			ps("B05012015A12 " + B05012015A12);
			ps("B05012015A13 " + B05012015A13);
			ps("B05012015A14 " + B05012015A14);
			ps("B05012015A15 " + B05012015A15);
			ps("B05012015A16 " + B05012015A16);
			ps("B05012015A17 " + B05012015A17);
			ps("B05012015A18 " + B05012015A18);
			ps("B05012015A19 " + B05012015A19);
			ps("B05012015A20 " + B05012015A20);
			ps("B05012015B01 " + B05012015B01);
			ps("B05012015B02 " + B05012015B02);
			ps("B05012015B03 " + B05012015B03);
			ps("B05012015B04 " + B05012015B04);
			ps("B05012015B05 " + B05012015B05);
			ps("B05012015B06 " + B05012015B06);
			ps("B05012015B07 " + B05012015B07);
			ps("B05012015B08 " + B05012015B08);
			ps("B05012015B09 " + B05012015B09);
			ps("B05012015B10 " + B05012015B10);
			ps("B05012015B11 " + B05012015B11);
			ps("B05012015B12 " + B05012015B12);
			ps("B05012015B13 " + B05012015B13);
			ps("B05012015B14 " + B05012015B14);
			ps("B05012015B15 " + B05012015B15);
			ps("B05012015B16 " + B05012015B16);
			ps("B05012015B17 " + B05012015B17);
			ps("B05012015B18 " + B05012015B18);
			ps("B05012015B19 " + B05012015B19);
			ps("B05012015B20 " + B05012015B20);

			//printReport();
		}

	}

	
	private void parseFileBilling(String strfileName) throws ParseException { // Generic
																				// function
																				// to
																				// retrieve
																				// field
																				// values
																				// from
																				// CDR-CSV
																				// file

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
//		int B04112014B01 = 0;
		int B05112014A01 =0;
		int B05112014A02 =0;
		int B05112014A03 =0;
		int B05112014A04 =0;
		int B05112014A05 =0;
		int B05112014A06 =0;
		int B05112014A07 =0;
		int B05112014A08 =0;
		int B05112014A09 =0;
		int B05112014A10 =0;
		int B05112014A11 =0;
		int B05112014A12 =0;
		int B05112014A13 =0;
		int B05112014A14 =0;
		int B05112014A15 =0;
		int B05112014A16 =0;
		int B05112014A17 =0;
		int B05112014A18 =0;
		int B05112014A19 =0;
		int B05112014A20 =0;
		int B05112014B01 =0;
		int B05112014B02 =0;
		int B05112014B03 =0;
		int B05112014B04 =0;
		int B05112014B05 =0;
		int B05112014B06 =0;
		int B05112014B07 =0;
		int B05112014B08 =0;
		int B05112014B09 =0;
		int B05112014B10 =0;
		int B05112014B11 =0;
		int B05112014B12 =0;
		int B05112014B13 =0;
		int B05112014B14 =0;
		int B05112014B15 =0;
		int B05112014B16 =0;
		int B05112014B17 =0;
		int B05112014B18 =0;
		int B05112014B19 =0;
		int B05112014B20 =0;


		try {
			br = new BufferedReader(new FileReader(strfileName));
			
			int lineNumber = 0;			// Counter of current line read within the log file. Don't modify this line.
			
			while ((line1 = br.readLine()) != null) { // Reading a new line Billing log files

				if (lineNumber == 7){		//Printing the line you are interested in one time. Modify this with the line number of the first occurrence in the log file Ex: Line number 6 for CTN Succeede in BLPREP log file. Take this number form the comment line of interest below Pattern pifnl1 = Pattern statement.
					ps("line1: " + line1);
				}
				
				lineNumber++;		// Incrementing the line counter because a new line was successfully read.

				if (line1.contains(matches[4])) {	//Modify this line to match the string you are interested from the array matches. Ex 8 is for "CTNs                    succeeded". Take this number form the comment line of interest below Pattern pifnl1 = Pattern statement.

					Pattern pifnl1 = Pattern	//Uncomment the pattern you want to match in the read line. Ex: .compile("[A-Z]+_([A-Z0-9]+)_.*.log:Start Processing.*"); this is the missing par of the Pattern after the equal sign (=) 
							// LINE=0 MATCH=0 GROUP= 1 & 2 //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Script Job Activation, on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
							/* LINE=7 MATCH=4 GROUP= 1 & 2 */.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*  Operational Job ended successfuly on [A-Za-z]{3} ([A-Za-z]{3}\\s+\\d+ \\d{2}:\\d{2}:\\d{2}) EST \\d{4}"); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right
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
							//LINE=45 MATCH=3 BLCYRRT //.compile("[A-Z]+_([A-Z0-9]+)_.*.log:\\*{3}  (\\d+) CTN records             successfully rerated ."); // File name pattern: group 0 Whole Regex, group 1 First content in parenthesis from left to right									
					
					//Start Processing|MpCreateRLHSurcharge|Ban|

							Matcher mifnl1 = pifnl1.matcher(line1);
					if (mifnl1.find()) {
						inputFileNameL1 = mifnl1.group(1);
						 System.out.println(inputFileNameL1);
						 
							inputFileNameL2 = mifnl1.group(2);
//							 System.out.println("inputFileNameL2: " + inputFileNameL2);
//							ps("line1: " + line1);
							 ps(inputFileNameL1 + " " + inputFileNameL2);
							 
//							 if (line1.contains("B04112014B01")) {
//								 B04112014B01++;
//								 
//							 }
						
							 switch(inputFileNameL1){
							 
							 case "B05112014A01":
								 B05112014A01++;
								 break;
							 
							 case "B05112014A02":
								 B05112014A02++;
								 break;
								 
							 case "B05112014A03":
								 B05112014A03++;
								 break;
								 
							 case "B05112014A04":
								 B05112014A04++;
								 break;
								 
							 case "B05112014A05":
								 B05112014A05++;
								 break;
							
							 case "B05112014A06":
								 B05112014A06++;
								 break;
							
							 case "B05112014A07":
								 B05112014A07++;
								 break;
							
							 case "B05112014A08":
								 B05112014A08++;
								 break;
							
							 case "B05112014A09":
								 B05112014A09++;
								 break;
							
							 case "B05112014A10":
								 B05112014A10++;
								 break;
							
							 case "B05112014A11":
								 B05112014A11++;
								 break;
							
							 case "B05112014A12":
								 B05112014A12++;
								 break;
							
							 case "B05112014A13":
								 B05112014A13++;
								 break;
							
							 case "B05112014A14":
								 B05112014A14++;
								 break;
							
							 case "B05112014A15":
								 B05112014A15++;
								 break;
							
							 case "B05112014A16":
								 B05112014A16++;
								 break;
							
							 case "B05112014A17":
								 B05112014A17++;
								 break;
							
							 case "B05112014A18":
								 B05112014A18++;
								 break;
							
							 case "B05112014A19":
								 B05112014A19++;
								 break;
							
							 case "B05112014A20":
								 B05112014A20++;
								 break;
							
							 case "B05112014B01":
								 B05112014B01++;
								 break;
							
							 case "B05112014B02":
								 B05112014B02++;
								 break;
							
							 case "B05112014B03":
								 B05112014B03++;
								 break;
							
							 case "B05112014B04":
								 B05112014B04++;
								 break;
							
							 case "B05112014B05":
								 B05112014B05++;
								 break;
							
							 case "B05112014B06":
								 B05112014B06++;
								 break;
							
							 case "B05112014B07":
								 B05112014B07++;
								 break;
							
							 case "B05112014B08":
								 B05112014B08++;
								 break;
							
							 case "B05112014B09":
								 B05112014B09++;
								 break;
							
							 case "B05112014B10":
								 B05112014B10++;
								 break;
							
							 case "B05112014B11":
								 B05112014B11++;
								 break;
							
							 case "B05112014B12":
								 B05112014B12++;
								 break;
							
							 case "B05112014B13":
								 B05112014B13++;
								 break;
							
							 case "B05112014B14":
								 B05112014B14++;
								 break;
							
							 case "B05112014B15":
								 B05112014B15++;
								 break;
							
							 case "B05112014B16":
								 B05112014B16++;
								 break;
							
							 case "B05112014B17":
								 B05112014B17++;
								 break;
							
							 case "B05112014B18":
								 B05112014B18++;
								 break;
							
							 case "B05112014B19":
								 B05112014B19++;
								 break;
							
							 case "B05112014B20":
								 B05112014B20++;
								 break;
							
								 
							 }
							 
					} else {
						line1 = "";
						line2 = "";
						continue;
					}

					line2 = br.readLine();

					Pattern pifnl2 = Pattern
							.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // checking
																		// if
																		// for
																		// second
																		// line.
																		// if it
																		// referees
																		// to
																		// the
																		// same
																		// file
																		// from
																		// line
																		// 1
					Matcher mifnl2 = pifnl2.matcher(line2);

					if (mifnl2.find()) {
						inputFileNameL2 = mifnl2.group(0);
						// System.out.println("inputFileNameL2: " +
						// inputFileNameL2);
					} else {
						line1 = "";
						line2 = "";
						continue;
					}

					if (inputFileNameL1.equals(inputFileNameL2) != true) {
						line1 = "";
						line2 = "";
						continue;
					}

					if (line2.contains(matches[1])) {
						populateArrayListsStatsMAF(line1, line2);
					} else {
						populateAbortedListStats(line2);
					}
					// /////////////////////////////////////////////////////////////////
				} else { // capture AF and AE status
							// FSGW1215_ID08934_T20140816160825_R68885.DAT has
							// been finished on 08/16/2014 20:44:36 with status
							// AF.

					populateAbortedListStats(line1);

					/*
					 * if (line1.contains(matches[2]) ||
					 * line1.contains(matches[3]) ) { int recordCount=0;
					 * 
					 * //////////////////////////////////////////////////////////
					 * /////// //inputFileName:
					 * SVA2BC03_FGSM1205_ID5592_T20140816160825_R4242//
					 * /////////
					 * ////////////////////////////////////////////////////////
					 * 
					 * // Pattern pifnl1 =
					 * Pattern.compile("S.+_F.+_ID\\d+_T\\d{14}_R\\d+"); // File
					 * name pattern: group 0 Whole Regex, group 1 First content
					 * in parenthesis from left to right // Matcher mifnl1 =
					 * pifnl1.matcher(line1);
					 * 
					 * ////// Capturing Number of Records //////////// Pattern
					 * pr = Pattern.compile("_R(\\d+)"); // group 0 Whole Regex,
					 * group 1 First content in parenthesis from left to right
					 * Matcher mr = pr.matcher(line1);
					 * 
					 * if(mr.find()) { recordCount =
					 * Integer.parseInt(mr.group(1));
					 * System.out.println("recordCount: " + recordCount);
					 * System.out.println(line1); }
					 * ///////////////////////////////////////////////
					 * 
					 * //abortedList with status AF or AE String
					 * abortedStatus=""; Pattern pa =
					 * Pattern.compile(" with status (A[FE])"); // group 0 Whole
					 * Regex, group 1 First content in parenthesis from left to
					 * right Matcher ma = pa.matcher(line1);
					 * 
					 * if(ma.find()) { abortedStatus = ma.group(1);
					 * System.out.println("abortedStatus: " + abortedStatus); }
					 * 
					 * if(abortedList.contains(abortedStatus)){
					 * ps("it contains " + abortedStatus); int index =
					 * abortedList.indexOf(format);
					 * 
					 * recordCount += (double)abortedRecordList.get(index); //
					 * ps("recordCount: " + recordCount);
					 * 
					 * abortedRecordList.set(index, recordCount); } else{ // If
					 * it's new format in arraylist
					 * abortedList.add(abortedStatus);
					 * abortedRecordList.add(recordCount); } }
					 */

					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ps("\nMAIN DRIVER STATS: " + strfileName);
			ps("B05112014A01 " + B05112014A01);
			ps("B05112014A02 " + B05112014A02);
			ps("B05112014A03 " + B05112014A03);
			ps("B05112014A04 " + B05112014A04);
			ps("B05112014A05 " + B05112014A05);
			ps("B05112014A06 " + B05112014A06);
			ps("B05112014A07 " + B05112014A07);
			ps("B05112014A08 " + B05112014A08);
			ps("B05112014A09 " + B05112014A09);
			ps("B05112014A10 " + B05112014A10);
			ps("B05112014A11 " + B05112014A11);
			ps("B05112014A12 " + B05112014A12);
			ps("B05112014A13 " + B05112014A13);
			ps("B05112014A14 " + B05112014A14);
			ps("B05112014A15 " + B05112014A15);
			ps("B05112014A16 " + B05112014A16);
			ps("B05112014A17 " + B05112014A17);
			ps("B05112014A18 " + B05112014A18);
			ps("B05112014A19 " + B05112014A19);
			ps("B05112014A20 " + B05112014A20);
			ps("B05112014B01 " + B05112014B01);
			ps("B05112014B02 " + B05112014B02);
			ps("B05112014B03 " + B05112014B03);
			ps("B05112014B04 " + B05112014B04);
			ps("B05112014B05 " + B05112014B05);
			ps("B05112014B06 " + B05112014B06);
			ps("B05112014B07 " + B05112014B07);
			ps("B05112014B08 " + B05112014B08);
			ps("B05112014B09 " + B05112014B09);
			ps("B05112014B10 " + B05112014B10);
			ps("B05112014B11 " + B05112014B11);
			ps("B05112014B12 " + B05112014B12);
			ps("B05112014B13 " + B05112014B13);
			ps("B05112014B14 " + B05112014B14);
			ps("B05112014B15 " + B05112014B15);
			ps("B05112014B16 " + B05112014B16);
			ps("B05112014B17 " + B05112014B17);
			ps("B05112014B18 " + B05112014B18);
			ps("B05112014B19 " + B05112014B19);
			ps("B05112014B20 " + B05112014B20);
			
			printReport();
		}

	}

	private void parseFileGUIDE(String strfileName) throws ParseException { // Generic
																			// function
																			// to
																			// retrieve
																			// field
																			// values
																			// from
																			// CDR-CSV
																			// file

		File file = null;
		OutputStream os = null;
		BufferedReader br = null;
		String line1 = "";
		String line2 = "";
		String line3 = "";
		String line4 = "";
		String cvsSplitBy = ",";
		int NumberCDRsPerFile = 1;
		// String strfileName="GD_R714_17th_Aug.txt";
		storedStartDate = null;
		storedEndDate = null;

		// missing DPSAMA + IUM1215 + GSM1215

		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 17 20:09:21 2014
		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Open input file Id:
		// 894027580 ,in :
		// js/up/data/ERIC147/STOGMSC0_FERIC147_ID32846_T20140816160825_R2331/STOGMSC0_D140
		// at Sun Aug 17 20:09:21 2014
		// ./MPSGUIDE_CAN_13004_20140817_200800.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 221 at Sun Aug 17 20:09:21 2014
		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Open input file Id:
		// 894027580 ,in :
		// js/up/data/ERIC147/STOGMSC0_FERIC147_ID32846_T20140816160825_R2331/STOGMSC0_D140
		// at Sun Aug 17 20:09:21 2014
		// ./MPSGUIDE_CAN_13004_20140817_200800.log:Program mpgd_100mn.cbl has
		// completed successfuly at Sun Aug 17 20:09:29 2014

		// ./MPSGUIDE_CAN_19287_20140824_130843.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 24 13:08:44 2014
		// ./MPSGUIDE_CAN_19287_20140824_130843.log:Open input file Id:
		// 894189325 ,in :
		// js/up/data/MD_IUM1205/SMOSMS00_FIUM1205_ID002099_T20140821112417_R34695/SMOSMS00
		// at Sun Aug 24 13:08:44 2014
		// ./MPSGUIDE_CAN_19287_20140824_130843.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 32819 at Sun Aug 24 13:08:44 2014
		// ./MPSGUIDE_CAN_19287_20140824_130843.log:Open input file Id:
		// 894189325 ,in :
		// js/up/data/MD_IUM1205/SMOSMS00_FIUM1205_ID002099_T20140821112417_R34695/SMOSMS00
		// at Sun Aug 24 13:08:45 2014
		// ./MPSGUIDE_CAN_19287_20140824_130843.log:Program mpgd_100mn.cbl has
		// completed successfuly at Sun Aug 24 13:14:03 2014

		// ./MPSGUIDE_CAN_19470_20140824_131052.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 24 13:13:17 2014
		// ./MPSGUIDE_CAN_19470_20140824_131052.log:Open input file Id:
		// 894191949 ,in :
		// js/up/data/IUM1215/SMOCSG00_FIUM1215_ID000600_T20140821112417_R90642/SMOCSG00_D1
		// at Sun Aug 24 13:13:17 2014
		// ./MPSGUIDE_CAN_19470_20140824_131052.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 90619 at Sun Aug 24 13:13:17 2014
		// ./MPSGUIDE_CAN_19470_20140824_131052.log:Open input file Id:
		// 894191949 ,in :
		// js/up/data/IUM1215/SMOCSG00_FIUM1215_ID000600_T20140821112417_R90642/SMOCSG00_D1
		// at Sun Aug 24 13:13:20 2014
		// ./MPSGUIDE_CAN_19470_20140824_131052.log: File name:
		// egd_CAN_ERRORS_0.dat Alias: ERROR No. of Records 5 at Sun Aug 24
		// 13:36:34 2014
		// ./MPSGUIDE_CAN_19470_20140824_131052.log:Program mpgd_100mn.cbl has
		// completed with error(s) at Sun Aug 24 13:36:34 2014

		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 24 13:11:27 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Open input file Id: 894190393
		// ,in :
		// js/up/data/DPSAMA/SDPS01_FDPSAMA_ID47412_T20140821112417_R19174/SDPS01_D140824_T
		// at Sun Aug 24 13:11:27 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 4656 at Sun Aug 24 13:11:27 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Open input file Id: 894190393
		// ,in :
		// js/up/data/DPSAMA/SDPS01_FDPSAMA_ID47412_T20140821112417_R19174/SDPS01_D140824_T
		// at Sun Aug 24 13:11:27 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log: File name:
		// egd_CAN_ERRORS_0.dat Alias: ERROR No. of Records 10 at Sun Aug 24
		// 13:12:12 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Program mpgd_100mn.cbl has
		// completed with error(s) at Sun Aug 24 13:12:13 2014

		// ./MPSGUIDE_CAN_20728_20140824_131255.log:Program m

		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Program mpgd_100mn.cbl has
		// started at Sun Aug 24 13:10:55 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Open input file Id: 894189721
		// ,in :
		// js/up/data/GSM1215/SVAGSN03_FGSM1215_ID1021_T20140821112417_R7879/SVAGSN03_D1408
		// at Sun Aug 24 13:10:56 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log: File name: USAGE_CAN Alias:
		// USAGE No. of Records 5366 at Sun Aug 24 13:10:56 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Open input file Id: 894189721
		// ,in :
		// js/up/data/GSM1215/SVAGSN03_FGSM1215_ID1021_T20140821112417_R7879/SVAGSN03_D1408
		// at Sun Aug 24 13:10:56 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log: File name:
		// egd_CAN_ERRORS_0.dat Alias: ERROR No. of Records 2 at Sun Aug 24
		// 13:11:27 2014
		// ./MPSGUIDE_CAN_7373_20140824_131055.log:Program mpgd_100mn.cbl has
		// completed with error(s) at Sun Aug 24 13:11:27 2014

		String inputFileNameL1 = "";
		String inputFileNameL2 = "";
		String format = "";

		try {
			br = new BufferedReader(new FileReader(strfileName));

			while ((line1 = br.readLine()) != null) { // Within this loop CDRs
														// are written in a file
														// an therefore files
														// are being created. //
														// Reading second,
														// third, and so on..
														// rows (data rows).

				// if (line1.contains("MPSGUIDE_CAN_.+Id: (\\d+).+_F(.+)_.*"))//
				// Checking for Format
				if (line1.contains("Open input file Id:"))// Checking for Format
				{
					if ((line2 = br.readLine()) == null) {
						continue;
					}

					// if
					// (line2.contains("MPSGUIDE_CAN.*Alias: USAGE   No. of Records (\\d+)")){//Checking
					// for No. Records
					if (line2
							.contains("File name: USAGE_CAN   Alias: USAGE   No. of Records")) {// Checking
																								// for
																								// No.
																								// Records

						if (br.readLine() == null) {// skipping repeated line
							continue;
						}

						if ((line3 = br.readLine()) == null) {
							continue;
						}

						// ./MPSGUIDE_CAN_19287_20140824_130843.log:Program
						// mpgd_100mn.cbl has completed successfuly at Sun Aug
						// 24 13:14:03 2014
						// ./MPSGUIDE_CAN_7373_20140824_131055.log: File name:
						// egd_CAN_ERRORS_0.dat Alias: ERROR No. of Records 10
						// at Sun Aug 24 13:12:12 2014
						// ./MPSGUIDE_CAN_7373_20140824_131055.log:Program
						// mpgd_100mn.cbl has completed with error(s) at Sun Aug
						// 24 13:12:13 2014

						if (line3
								.contains("Program mpgd_100mn.cbl has completed successfuly")) {// Checking
																								// end
																								// time
							populateArrayListStatsGUIDE(line1, line2, line3, "");
						} else if (line3
								.contains("Alias: ERROR   No. of Records")) {

							if ((line4 = br.readLine()) == null) {
								continue;
							}

							if (line4
									.contains("Program mpgd_100mn.cbl has completed with error(s)")) {// Checking
																										// end
																										// time
								populateArrayListStatsGUIDE(line1, line2,
										line3, line4);
							}

						} else {
							continue;
						}
					} else {
						continue;
					}
				} else { // capture AF and AE status
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ps("\nGUIDING STATS: " + strfileName);
			printReport();
		}

	}

	private void parseFileRATE(String strfileName) throws ParseException { // Generic
																			// function
																			// to
																			// retrieve
																			// field
																			// values
																			// from
																			// CDR-CSV
																			// file

		File file = null;
		OutputStream os = null;
		BufferedReader br = null;
		String line1 = "";
		String line2 = "";
		String line3 = "";
		String cvsSplitBy = ",";
		int NumberCDRsPerFile = 1;
		// String strfileName="RT_R714_17th_Aug_w_file_name.txt";
		storedStartDate = null;
		storedEndDate = null;

		// missing SGW1215

		// ./MPSRATE08_CAN_1901_20140817_200559.log:Program mpup_100mn has
		// started at Sun Aug 17 20:05:59 2014
		// ./MPSRATE08_CAN_1901_20140817_200559.log:Open input file Id:
		// 894027244 ,in :
		// /usg/TLG_VAR/can/projs/up/data/MD_IMS1205/SMOIMS1HSN_FIMS1205_ID0390_T20140816160825_R195/SMOIMS1HSN
		// at Sun Aug 17 20:06:00 2014
		// ./MPSRATE08_CAN_1901_20140817_200559.log: File name:
		// ugd_CAN_CAN08R51.dat Alias: USAGE No. of Records 1 at Sun Aug 17
		// 20:06:00 2014
		// ./MPSRATE08_CAN_1901_20140817_200559.log:Program mpup_100mn has
		// completed successfuly at Sun Aug 17 20:06:00 2014

		String inputFileNameL1 = "";
		String inputFileNameL2 = "";
		String format = "";

		try {
			br = new BufferedReader(new FileReader(strfileName));

			while ((line1 = br.readLine()) != null) { // Within this loop CDRs
														// are written in a file
														// an therefore files
														// are being created. //
														// Reading second,
														// third, and so on..
														// rows (data rows).

				if (line1.contains("Open input file Id:"))// Checking for Format
				{

					if ((line2 = br.readLine()) == null) {
						continue;
					}

					if (line2.contains("Alias: USAGE   No. of Records")) {// Checking
																			// for
																			// No.
																			// Records

						if ((line3 = br.readLine()) == null) {
							continue;
						}

						if (line3
								.contains("Program mpup_100mn has completed successfuly")) {// Checking
																							// end
																							// time
							populateArrayListStatsRATE(line1, line2, line3);
						}
					} else {
						continue;
					}
				} else { // capture AF and AE status
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ps("\nRATING STATS: " + strfileName);
			printReport();
		}

	}

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

	
