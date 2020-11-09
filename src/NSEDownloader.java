import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * initial version
 * 
 */
public class NSEDownloader {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String startDate = "2020/10/30";

		String dateParts[] = startDate.split("/");

		String year = dateParts[0];
		String month = dateParts[1];
		String day = dateParts[2];

		String convertedMonth = getMonthName(month);

		String fileURL = "https://www1.nseindia.com/content/historical/EQUITIES/" + year + "/" + convertedMonth + "/cm"
				+ day + convertedMonth + year + "bhav.csv.zip";
		String saveDir = "C:/nse-download";

		String inputZipFileLocationAndName = saveDir + "/" + "/cm" + day + convertedMonth + year + "bhav.csv.zip";

		String inputExtractedZipFileLocationAndName = saveDir + "/" + "/cm" + day + convertedMonth + year + "bhav.csv";
		String outputFileNameInTextFormat = saveDir + "/" + year + convertedMonth + day + ".txt";

		try {
			NSEDownloadUtility.downloadFile(fileURL, saveDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		NSEDownloadUtility.extractTheFileFromZipFolder(saveDir, inputZipFileLocationAndName);

		NSEDownloadUtility.convertCSVFileToTextFile(inputExtractedZipFileLocationAndName, outputFileNameInTextFormat);

	}

	private static String getMonthName(String month) {

		String monthName = null;

		if (month.equals("1")) {
			return monthName = "JAN";
		} else if (month.equals("2")) {
			return monthName = "FEB";
		} else if (month.equals("3")) {
			return monthName = "MAR";
		} else if (month.equals("4")) {
			return monthName = "APR";
		} else if (month.equals("5")) {
			return monthName = "MAY";
		} else if (month.equals("6")) {
			return monthName = "JUN";
		} else if (month.equals("7")) {
			return monthName = "JUL";
		} else if (month.equals("8")) {
			return monthName = "AUG";
		} else if (month.equals("9")) {
			return monthName = "SEP";
		} else if (month.equals("10")) {
			return monthName = "OCT";
		} else if (month.equals("11")) {
			return monthName = "NOV";
		} else if (month.equals("12")) {
			return monthName = "DEC";
		}
		return monthName;

	}

}
