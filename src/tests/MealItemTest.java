package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 5)
	public void addToCart() throws InterruptedException {
//	  	SoftAssert sa = new SoftAssert();
		driver.navigate().to("http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.location.ClosePopup();
		this.meal.AddToCart("2");
		Assert.assertEquals(this.notification.message(), 
				"The Following Errors Occurred:" + "\n" + "Please Select Location"
				, "ERROR");
//		sa.assertEquals(this.notification.message(), 
//				 "The Following Errors Occurred:" + "\n" + "Please Select Location"
//				, "ERROR");

	
		
		this.notification.waitMessage();
		this.location.SetLocation("City Center - Albany");
		Thread.sleep(2000);
		this.meal.AddToCart("3");
		Assert.assertEquals(this.notification.message(), "Meal Added To Cart", "ERROR");
//		sa.assertEquals(this.notification.message(), "Meal Added To Cart", "ERROR");
//		sa.assertAll();
	}
	
	@Test (priority = 10)
	public void addMealToFav () {
		
//		SoftAssert sa = new SoftAssert ();
		driver.navigate().to("http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.location.ClosePopup();
		this.meal.AddToFavourite();
		Assert.assertEquals(this.notification.message(),"Please login first!" , "ERROR");
//	sa.assertEquals(this.notification.message(), "Please login first!", "ERROR");
	driver.navigate().to("http://demo.yo-meals.com/guest-user/login-form");
		this.login.Login(username, password);
		driver.navigate().to("http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.meal.AddToFavourite();
		Assert.assertEquals(this.notification.message(), "Product has been added to your favorites.", "ERROR");
//		sa.assertEquals(this.notification.message(), "Product has been added to your favorites.", "ERROR");
//		sa.assertAll();
		
	}
	
	@Test (priority = 15)
	public void clearCart () throws IOException {
		SoftAssert sa = new SoftAssert ();
		driver.navigate().to("http://demo.yo-meals.com/meals");
		this.location.ClosePopup();
		this.location.SetLocation("City Center - Albany");
		File file = new File("data/FinalProject.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");
		
		for (int i =1; i <sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			
			String url = row.getCell(0).getStringCellValue();
			String quantity = row.getCell(1).getRawValue();
			
			driver.navigate().to(url);
			this.meal.AddToCart(quantity);
			sa.assertEquals(this.notification.message(), "Meal Added To Cart", "ERROR");
		}
		this.cartsumm.ClearAll();
		sa.assertEquals(this.notification.message(), "All meals removed from Cart successfully", "ERROR");
		sa.assertAll();
	}
}
