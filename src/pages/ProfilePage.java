package pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public WebElement getFirstName() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZip() {
		return driver.findElement(By.name("user_zip"));
	}

	public WebElement getCountry() {
		return driver.findElement(By.name("user_country_id"));
	}

	public WebElement getState() {
		return driver.findElement(By.name("user_state_id"));
	}

	public WebElement getCity() {
		return driver.findElement(By.name("user_city"));
	}

	public WebElement getSaveBtn() {
//		return driver.findElement(By.xpath("//*[@name = 'btn_submit'][@value='Save']"));
		return driver.findElement(By.xpath("//*[@type = 'submit']"));
	}

	public WebElement getUpload() {
		return driver.findElement(By.className("ion-camera"));
	}

	public void Upload(String picturePath) throws Exception {
		js.executeScript("arguments[0].click();", this.getUpload());
		WebElement Picture = this.driver.findElement(By.xpath("//input[@name = 'file']"));
		String Path = new File(picturePath).getCanonicalPath();
		Picture.sendKeys(Path);
	}

	public void PictureRemove() {
		WebElement remove = driver.findElement(By.className("remove"));
		js.executeScript("arguments[0].click();", remove);
	}

	public void UpdateProfile(String firstname, String lastname, String address, String phone, String zip,
			String country, String state, String city) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstname);

		this.getLastName().clear();
		this.getLastName().sendKeys(lastname);

		this.getAddress().clear();
		this.getAddress().sendKeys(address);

		this.getPhone().clear();
		this.getPhone().sendKeys(phone);

		this.getZip().clear();
		this.getZip().sendKeys(zip);

		Thread.sleep(2000);

		Select selectCountry = new Select(this.getCountry());
		selectCountry.selectByVisibleText(country);

		Thread.sleep(2000);

		Select selectState = new Select(this.getState());
		selectState.selectByVisibleText(state);
		Thread.sleep(2000);

		Select selectCity = new Select(this.getCity());
		selectCity.selectByVisibleText(city);

		this.getSaveBtn().submit();
	}
}
