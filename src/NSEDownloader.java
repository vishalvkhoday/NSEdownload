import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * initial version 
 * 
 */
public class NSEDownloader {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String fileURL = "https://www1.nseindia.com/content/historical/EQUITIES/2020/OCT/cm30OCT2020bhav.csv.zip";
        String saveDir = "C:/test";
        try {
        	NSEDownloadUtility.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        byte[] buffer = new byte[2048];
        Path outDir = Paths.get("C:/test/");
        String inputZipFileName = "C:/test/cm30OCT2020bhav.csv.zip";
        
        try (FileInputStream fis = new FileInputStream(inputZipFileName);
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

}
