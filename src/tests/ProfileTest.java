package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {

	@Test(priority = 5)
	public void editProfile() throws InterruptedException {
		driver.navigate().to("http://demo.yo-meals.com/guest-user/login-form");
		this.location.ClosePopup();
		this.login.Login(username, password);

		Assert.assertEquals(this.notification.message(), "Login Successfull", "ERROR");

		driver.navigate().to("http://demo.yo-meals.com/member/profile");

		this.profile.UpdateProfile("Marina", "Mladenovic", " RB3/16", "064565545", "18000", "United Kingdom",
				"Cambridge", "Ely");

		Assert.assertEquals(this.notification.message(), "Setup Successful", "ERROR");

		this.auth.Logout();
		Assert.assertEquals(this.notification.message(), "Logout Successfull!", "ERROR");
	}

	@Test(priority = 10)
	public void changeProfileImage() throws Exception {
		driver.navigate().to("http://demo.yo-meals.com/guest-user/login-form");

		this.location.ClosePopup();
		this.login.Login(username, password);
		Assert.assertEquals(this.notification.message(), "Login Successfull", "ERROR");

		driver.navigate().to(" http://demo.yo-meals.com/member/profile");
		this.profile.Upload("images/1.jpg");
		Assert.assertEquals(this.notification.message(), "Profile Image Uploaded Successfully", "ERROR");

		this.notification.waitMessage();
		this.profile.PictureRemove();
		Assert.assertEquals(this.notification.message(), "Profile Image Deleted Successfully", "ERROR");

		this.notification.waitMessage();
		this.auth.Logout();
		Assert.assertEquals(this.notification.message(), "Logout Successfull!", "ERROR");
	}


}
