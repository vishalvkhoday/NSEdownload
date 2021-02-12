import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NSEDownloadUtility {

	private static final int BUFFER_SIZE = 4096;

	/**
	 * Downloads a file from a URL
	 * 
	 * @param fileURL HTTP URL of the file to be downloaded
	 * @param saveDir path of the directory to save the file
	 * @throws IOException
	 */
	/*
	 * initial version..
	 * 
	 */

	public static void downloadFile(String fileURL, String saveDir) throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			}

			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName;

			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			System.out.println("Files downloaded succesfully...");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}

	public static void extractTheFileFromZipFolder(String saveDir, String inputZipFileLocationAndName)
			throws IOException, FileNotFoundException {
		byte[] buffer = new byte[2048];
		Path outDir = Paths.get(saveDir);

		try (FileInputStream fis = new FileInputStream(inputZipFileLocationAndName);
				BufferedInputStream bis = new BufferedInputStream(fis);
				ZipInputStream stream = new ZipInputStream(bis)) {

			ZipEntry entry;
			while ((entry = stream.getNextEntry()) != null) {

				Path filePath = outDir.resolve(entry.getName());

				try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
						BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length)) {

					int len;
					while ((len = stream.read(buffer)) > 0) {
						bos.write(buffer, 0, len);
					}
				}
			}
		}
	}

	public static void convertCSVFileToTextFile(String inputExtractedZipFileLocationAndName,
			String outputFileNameInTextFormat, String year, String month, String day) throws IOException {
		File inputCSVFileDirPath = new File(inputExtractedZipFileLocationAndName);
		File outputTxtFileDirPath = new File(outputFileNameInTextFormat);
		BufferedReader brReadCSVFile = null;// For Read CSV File
		BufferedWriter brWriteCSVDataToTextFile = null;// For Write a file in which you want to write
		String words = "";
		String separator = "\\,";// Here you can use , or space according to your csv file

		try {

			brReadCSVFile = new BufferedReader(new FileReader(inputCSVFileDirPath));
			if (brReadCSVFile != null) {
				brWriteCSVDataToTextFile = new BufferedWriter(new FileWriter(outputTxtFileDirPath));
				boolean skip = true;
				while ((words = brReadCSVFile.readLine()) != null) {
					if (skip) {
						skip = false;
						continue;
					}
					String[] code = words.split(separator);
					if (code[1].equals("EQ") || code[1].equals("BE")) {
						brWriteCSVDataToTextFile.write(code[0]);
						brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[10]);
						brWriteCSVDataToTextFile.append(year + month + day);
						brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[1]);
						// brWriteCSVDataToTextFile.write(",");
						brWriteCSVDataToTextFile.write(code[2]);
						brWriteCSVDataToTextFile.write(",");
						brWriteCSVDataToTextFile.write(code[3]);
						brWriteCSVDataToTextFile.write(",");
						brWriteCSVDataToTextFile.write(code[4]);
						brWriteCSVDataToTextFile.write(",");
						brWriteCSVDataToTextFile.write(code[5]);
						brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[6]);
						// brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[7]);
						// brWriteCSVDataToTextFile.write(",");
						brWriteCSVDataToTextFile.write(code[8]);
						brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[9]);
						// brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[11]);
						// brWriteCSVDataToTextFile.write(",");
						// brWriteCSVDataToTextFile.write(code[12]);
						brWriteCSVDataToTextFile.append('0');
						brWriteCSVDataToTextFile.write("\n");
						// System.out.println("code1= " + code[0] + " , code2=" + code[1]);
					}

				}
			}

		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			if (brReadCSVFile != null) {
				try {
					brReadCSVFile.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
			brReadCSVFile.close();
			brWriteCSVDataToTextFile.close();
		}
	}

	public static String getMonthName(String month) {

		String monthName = null;

		if (month.equals("01")) {
			return monthName = "JAN";
		} else if (month.equals("02")) {
			return monthName = "FEB";
		} else if (month.equals("03")) {
			return monthName = "MAR";
		} else if (month.equals("04")) {
			return monthName = "APR";
		} else if (month.equals("05")) {
			return monthName = "MAY";
		} else if (month.equals("06")) {
			return monthName = "JUN";
		} else if (month.equals("07")) {
			return monthName = "JUL";
		} else if (month.equals("08")) {
			return monthName = "AUG";
		} else if (month.equals("09")) {
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

	// delete .csv and .zip files
	public static void deleteFiles(String saveDir) {

		File folder = new File(saveDir);

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.getName().contains(".csv") || file.getName().contains(".zip")) {
				file.delete();
			}
		}
		System.out.println("Files Deleted Succesfully...");
	}

}
