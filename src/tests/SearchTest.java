package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest{

	@Test
	public void searchTest () throws IOException, InterruptedException  {
	//	SoftAssert sa = new SoftAssert ();
		
		driver.navigate().to("http://demo.yo-meals.com/meals");
		this.location.ClosePopup();
		this.location.SetLocation("City Center - Albany");
		File file = new File("data/FinalProject.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");
		
		for (int i =1; i <sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			
			String location = row.getCell(0).getStringCellValue();
			String url = row.getCell(1).getStringCellValue();
			int numberofResults = (int) row.getCell(2).getNumericCellValue();
			
			driver.navigate().to(url);
			this.location.SetLocation(location);
			Thread.sleep(2000);
			
	//		sa.assertEquals(this.search.getNumberOfResults(), numberofResults, "ERROR");
			driver.navigate().refresh();
	//		sa.assertAll();
			
		}
		
		
	}
}