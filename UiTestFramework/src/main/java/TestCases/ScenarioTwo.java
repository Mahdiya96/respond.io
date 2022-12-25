package TestCases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PageClasses.HomePage;
import PageClasses.SectionsPage;

public class ScenarioTwo {
	
	WebDriver driver;
	
	String SystemFilePath = System.getProperty("user.dir");
    String driverPath = SystemFilePath + "\\drivers\\chromedriver.exe";
	
    @Test
	public void test() {
		
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver.get("https://iprice.my/");
		driver.manage().window().maximize();
		
		String ScenarioOneSection = "Clothing";
		String ScenarioOneSubSection = "Dresses";
		
		HomePage HmPage = new HomePage(driver);		
		HmPage.verifyHeader();		
		HmPage.clickProductSection(ScenarioOneSection, ScenarioOneSubSection);
		
		SectionsPage SecPage = new SectionsPage(driver);
		SecPage.clickPriceSortingDesc();
		SecPage.verifyPriceDescSorting();		
		
		driver.close();		
		
	}
	

}
