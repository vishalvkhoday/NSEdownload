import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * initial version
 * 
 */
public class NSEDownloader {

	public static void main(String[] args) throws FileNotFoundException, IOException {

//		String startDate = args[0]; //"2020/12/29";
		String startDate = "2021/01/01";
		String dateParts[] = startDate.split("/");

		String year = dateParts[0];
		String month = dateParts[1];
		String day = dateParts[2];

		String convertedMonth = NSEDownloadUtility.getMonthName(month);

		String fileURL = "https://archives.nseindia.com/content/historical/EQUITIES/" + year + "/" + convertedMonth + "/cm"
				+ day + convertedMonth + year + "bhav.csv.zip";
		
//		String fileURL = "https://www1.nseindia.com/content/historical/EQUITIES/" + year + "/" + convertedMonth + "/cm"
//				+ day + convertedMonth + year + "bhav.csv.zip";
		String saveDir = "C:/test";

		String inputZipFileLocationAndName = saveDir + "/cm" + day + convertedMonth + year + "bhav.csv.zip";

		String inputExtractedZipFileLocationAndName = saveDir + "/cm" + day + convertedMonth + year + "bhav.csv";
		String outputFileNameInTextFormat = saveDir + "/" + year + month + day + ".txt";

		try {
			NSEDownloadUtility.downloadFile(fileURL, saveDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		NSEDownloadUtility.extractTheFileFromZipFolder(saveDir, inputZipFileLocationAndName);

		NSEDownloadUtility.convertCSVFileToTextFile(inputExtractedZipFileLocationAndName, outputFileNameInTextFormat,
				year, month, day);

		NSEDownloadUtility.deleteFiles(saveDir);

	}

}
