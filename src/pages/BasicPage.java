package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {
	
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	
	public BasicPage (WebDriver driver , WebDriverWait waiter) {
		this.driver = driver;
		this.waiter = new WebDriverWait (driver, 30);
		this.js = (JavascriptExecutor) driver;}
}
