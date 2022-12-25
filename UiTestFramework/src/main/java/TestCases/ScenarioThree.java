package TestCases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PageClasses.HomePage;
import PageClasses.SectionsPage;

public class ScenarioThree {
	
	WebDriver driver;
	
	String SystemFilePath = System.getProperty("user.dir");
    String driverPath = SystemFilePath + "\\drivers\\chromedriver.exe";
	
    @Test
	public void test() {
		
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver.get("https://iprice.my/");
		driver.manage().window().maximize();
		
		String BrandName = "iPhone 14";
		
		HomePage HmPage = new HomePage(driver);		
		HmPage.verifyHeader();
		HmPage.searchProduct(BrandName);
		HmPage.clickSearchButton();
		
		SectionsPage SecPage = new SectionsPage(driver);
		SecPage.verifyBrandResults(BrandName);
		
		
		driver.close();		
		
	}
	

}
