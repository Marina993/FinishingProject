package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public WebElement getLogoutBtn() {
		return driver.findElement(By.className("filled"));
	}

	public void Logout() {
		this.getLogoutBtn().click();
		driver.findElement(By.linkText("Logout")).click();
	}

}
