package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage {

	public CartSummaryPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}
	
	public WebElement getClearAll () {
		return driver.findElement
				(By.xpath("//*[@onclick = 'clearCartItems()']"));
	}

	public void ClearAll () {
		this.getClearAll().click();
	}
}
