import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * initial version
 * 
 */
public class NSEDownloader {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String startDate = "2020/10/30";
		String endDate = "2020/10/30";

		String fileURL = "https://www1.nseindia.com/content/historical/EQUITIES/2020/OCT/cm30OCT2020bhav.csv.zip";
		String saveDir = "C:/nse-download";
		try {
			NSEDownloadUtility.downloadFile(fileURL, saveDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		NSEDownloadUtility.extractTheFileFromZipFolder(saveDir);

		NSEDownloadUtility.convertCSVFileToTextFile();

	}

}
