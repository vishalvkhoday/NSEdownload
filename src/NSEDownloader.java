import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * initial version
 * 
 */
public class NSEDownloader {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		/*
		 * String startDate = "2021/02/06"; //"2020/10/30" String startDate = args[0];
		 * String startDate = null;
		 * System.out.println("Values in args="+args.toString());
		 * 
		 * if (args[0].equals(null) || args[0].isEmpty() || args[0].length() == 0) {
		 * LocalDate currentDate = LocalDate.now(); startDate =
		 * currentDate.format(DateTimeFormatter.ofPattern("yyyy/mm/dd")); } else {
		 * startDate = args[0]; }
		 */

		String startDate = args[0];
		String dateParts[] = startDate.split("/");

		String year = dateParts[0];
		String month = dateParts[1];
		String day = dateParts[2];

		String convertedMonth = NSEDownloadUtility.getMonthName(month);

		// String fileURL = "https://archives.nseindia.com/content/historical/EQUITIES/"
		// + year + "/" + convertedMonth
		// + "/cm" + day + convertedMonth + year + "bhav.csv.zip";

		String fileURL = "https://www1.nseindia.com/content/historical/EQUITIES/" + year + "/" + convertedMonth + "/cm"
				+ day + convertedMonth + year + "bhav.csv.zip";
		String saveDir = "C:/nse-download";

		String inputZipFileLocationAndName = saveDir + "/cm" + day + convertedMonth + year + "bhav.csv.zip";

		String inputExtractedZipFileLocationAndName = saveDir + "/cm" + day + convertedMonth + year + "bhav.csv";
		String outputFileNameInTextFormat = saveDir + "/" + year + month + day + ".txt";

		try {
			NSEDownloadUtility.downloadFile(fileURL, saveDir);
			NSEDownloadUtility.extractTheFileFromZipFolder(saveDir, inputZipFileLocationAndName);
			NSEDownloadUtility.convertCSVFileToTextFile(inputExtractedZipFileLocationAndName,
					outputFileNameInTextFormat, year, month, day);
			if (!saveDir.isEmpty()) {
				NSEDownloadUtility.deleteFiles(saveDir);
			}

		} catch (FileNotFoundException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

}
