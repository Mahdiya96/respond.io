package TestCases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PageClasses.HomePage;
import PageClasses.SectionsPage;

public class ScenarioOne {
	
	WebDriver driver;
	
	String SystemFilePath = System.getProperty("user.dir");
    String driverPath = SystemFilePath + "\\drivers\\chromedriver.exe";
	
    @Test
	public void test() {
		
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver.get("https://iprice.my/");
		driver.manage().window().maximize();
		
		String ScenarioOneSection = "Computing";
		String ScenarioOneSubSection = "Laptops";
		String BrandName = "Dell";
		
		HomePage HmPage = new HomePage(driver);		
		HmPage.verifyHeader();
		HmPage.clickProductSection(ScenarioOneSection, ScenarioOneSubSection);
		
		SectionsPage SecPage = new SectionsPage(driver);
		SecPage.clickBrandDropDown();
		SecPage.selectBrand(BrandName);
		SecPage.verifyBrandResults(BrandName);
		
		driver.close();		
		
	}
	

}
