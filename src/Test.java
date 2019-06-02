import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	public static void main(String[] args) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File("hello.xlsx"));
			workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch(IOException ioe) {
        	ioe.printStackTrace();
        }
	}
}
