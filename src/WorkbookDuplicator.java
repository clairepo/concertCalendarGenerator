import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookDuplicator {
	private static String originalstring;
	private static String targetstring;
	
	public WorkbookDuplicator(String originalstring, String targetstring) {
		this.originalstring = originalstring;
		this.targetstring = targetstring;
	}
	
	public static void copy() {
		
       
        try {
        	 FileSystem system = FileSystems.getDefault();
             
        	 XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(new File(targetstring));
             workbook.write(fileOut);
             fileOut.close();
             workbook.close();
             
             Path target = system.getPath(targetstring);
             Path original = system.getPath(originalstring);
            // Throws an exception if the original file is not found.
            Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            System.out.println("ioe in duplicator: " + ioe.getMessage());
        }
    }
	
}
