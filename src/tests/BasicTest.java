package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected AuthPage auth;
	protected CartSummaryPage cartsumm;
	protected LocationPopupPage location;
	protected LoginPage login;
	protected MealPage meal;
	protected NotificationSystemPage notification;
	protected ProfilePage profile;
	protected SearchResultPage search;
	protected String baseUrl = "http://demo.yo-meals.com";
	protected String username = "customer@dummyid.com";
	protected String password = "12345678a";
	
	@BeforeClass
	public void setup () {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 30);

		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		this.auth = new AuthPage (driver, waiter);
		this.cartsumm = new CartSummaryPage (driver, waiter);
		this.location = new LocationPopupPage (driver, waiter);
		this.login = new LoginPage (driver, waiter);
		this.meal = new MealPage (driver,waiter);
		this.notification = new NotificationSystemPage (driver, waiter);
		this.profile = new ProfilePage (driver, waiter);	
		this.search = new SearchResultPage (driver, waiter);
	}
	
	@AfterMethod
	public void afterM (ITestResult result) throws IOException {
		
		String pictureDate = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot scr = (TakesScreenshot)driver;
			File scrPic = scr.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrPic, new File("screenshots/"+pictureDate));
		}
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void afterC () {
		driver.close();
	}
}
