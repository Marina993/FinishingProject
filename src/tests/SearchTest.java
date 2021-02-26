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

public class SearchTest extends BasicTest {

	@Test
	public void searchTest() throws IOException, InterruptedException {
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(this.baseUrl + "/meals");
		this.location.ClosePopup();
		this.location.SetLocation("City Center - Albany");

		File file = new File("data/FinalProject.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < 7; i++) {
			XSSFRow row = sheet.getRow(i);

			String location = row.getCell(0).getStringCellValue();
			String url = row.getCell(1).getStringCellValue();
			int numberofResults = (int) row.getCell(2).getNumericCellValue();

			driver.navigate().to(url);
			this.location.SetLocation(location);
			Thread.sleep(2000);

			sa.assertEquals(this.search.getNumberOfResults(), numberofResults,
					"[ERROR] - Number of results does not match with expected");

			String name;
			for (int k = 3; k < (this.search.resultNames().size() + 3); k++) {

				name = row.getCell(k).getStringCellValue();

				sa.assertEquals(true, this.search.resultNames().get(k - 3).contains(name),
						"[ERROR] - Name of meal does not match with expected ");

			}
		}

		sa.assertAll();
		wb.close();
		fis.close();
	}
}
