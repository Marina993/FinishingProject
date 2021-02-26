package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public WebElement getFavourite() {
		return driver.findElement
				(By.xpath("//*[@class = 'favourite  itemfav link']"));
	}

	public WebElement getAddToCart() {
		return driver.findElement
				(By.xpath("//*[@class = 'btn btn--primary btn--large js-proceedtoAddInCart ']"));
	}

	public void AddToCart(String q) {

		WebElement Q = driver.findElement(By.name("product_qty"));
		Q.click();
		Q.sendKeys(Keys.CONTROL+"a");
		Q.sendKeys(Keys.DELETE);
		Q.sendKeys(q);
		this.getAddToCart().click();
	}

	public void AddToFavourite() {
		this.getFavourite().click();
	}

}
