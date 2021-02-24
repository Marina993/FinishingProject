package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public WebElement getMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success') "
				+ "or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String message() {
		return this.getMessage().getText();
	}

	public void waitMessage() {
		waiter.until(ExpectedConditions.attributeToBe(
				driver.findElement(By.xpath("//*[contains(@class, 'system_message')]")), "style", "display: none;"));
	}

}
