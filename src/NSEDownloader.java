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

		String fileURL = "https://www1.nseindia.com/content/historical/EQUITIES/2020/OCT/cm30OCT2020bhav.csv.zip";
		String saveDir = "C:/nse-download";
		try {
			NSEDownloadUtility.downloadFile(fileURL, saveDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		NSEDownloadUtility.extractTheFileFromZipFolder(saveDir);

		File inputCSVFileDirPath = new File("C:/nse-download/cm30OCT2020bhav.csv");
		File outputTxtFileDirPath = new File("C:/nse-download/cm30OCT2020bhav.txt");
		BufferedReader brReadCSVFile = null;// For Read CSV File
		BufferedWriter brWriteCSVDataToTextFile = null;// For Write a file in which you want to write
		String words = "";
		String separator = "\\,";// Here you can use , or space according to your csv file

		try {

			brReadCSVFile = new BufferedReader(new FileReader(inputCSVFileDirPath));
			brWriteCSVDataToTextFile = new BufferedWriter(new FileWriter(outputTxtFileDirPath));
			while ((words = brReadCSVFile.readLine()) != null) {
				String[] code = words.split(separator);
				brWriteCSVDataToTextFile.write(code[0]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[1]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[2]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[3]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[4]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[5]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[6]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[7]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[8]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[9]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[10]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[11]);
				brWriteCSVDataToTextFile.write(",");
				brWriteCSVDataToTextFile.write(code[12]);
				brWriteCSVDataToTextFile.write("\n");
				System.out.println("code1= " + code[0] + " , code2=" + code[1]);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (brReadCSVFile != null) {
				try {
					brReadCSVFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			brReadCSVFile.close();
			brWriteCSVDataToTextFile.close();
		}

	}

}
