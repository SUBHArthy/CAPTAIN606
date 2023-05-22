package Campaigns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import POM.CampaignPage;
import POM.HomePage;
import POM.LoginPage;
import POM.ProductPage;
import POM.ValidationandVerification;
import generic_utility.BaseClass;
import generic_utility.Excel_Utility;
import generic_utility.Java_Utility;
import generic_utility.Property_Utility;
import generic_utility.Webdriver_Utility;

public class CreateCampaignWithProductWithPOM extends BaseClass {

	static {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
	}

	@Test(groups= {"smokeTest"})
	public void CreateCampaignWithProduct() throws Throwable {

//		WebDriver driver;
//		Property_Utility pro = new Property_Utility();
//		String BROWSER = pro.getStringKeyAndValue("browser");
//
//		if (BROWSER.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();
//		} else if (BROWSER.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}
//
//		Webdriver_Utility web=new Webdriver_Utility();
//		web.getWindowMax(driver);
//		web.getImplicitWait(driver);
//		
//		Property_Utility plib = new Property_Utility();
//		String url = plib.getStringKeyAndValue("url");
//		String username = plib.getStringKeyAndValue("username");
//		String password = plib.getStringKeyAndValue("password");
//
//		driver.get(url);
//
//		LoginPage login = new LoginPage(driver);
//		login.loginToApp(username, password);

		HomePage home = new HomePage(driver);
		home.clickProductLink();

		Java_Utility jav = new Java_Utility();
		int ranNum = jav.getRandomNum(100);

		Excel_Utility excel = new Excel_Utility();
		String productName = excel.getExcelData("Product", 0, 0) + ranNum;
		String campName = excel.getExcelData("Campaign", 0, 0) + ranNum;

		ProductPage pp = new ProductPage(driver);
		pp.clickProductCreateImage();
		pp.enterProductNameTextBox(productName);
		pp.clickSaveButton();

		home.getMorelink().click();
		home.campaignLinkText();

		CampaignPage campaign = new CampaignPage(driver);
		campaign.clickCampaignCreateImage();

		campaign.enterCampaignNameTextField(campName);
		campaign.clickSelectDropdown();

		Webdriver_Utility web = new Webdriver_Utility();

		web.switchToWindow(driver, "Products&action");

		campaign.enterSearchbox(productName);
		campaign.clickSearchbutton();
		driver.findElement(By.xpath("//a[text()='" + productName + "']")).click();

		web.switchToWindow(driver, "Campaigns&action");

		campaign.clickSaveButton();

		ValidationandVerification  validation=new ValidationandVerification(driver);
		validation.campaignWithProductValidation(campName,productName);
		
		Thread.sleep(2000);

	}

}
