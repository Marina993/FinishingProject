package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public List<WebElement> getSearchResults() {
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public ArrayList<String> resultNames() {
		ArrayList<String> search = new ArrayList<String>();
		for (int i = 0; i < this.getSearchResults().size(); i++) {
			String name = this.getSearchResults().get(i).getText();

			search.add(name);
		}

		return search;
	}
	
	public int getNumberOfResults () {
		return this.getSearchResults().size();
	}
}
