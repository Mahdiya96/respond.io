package PageClasses;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class HomePage {
	
	WebDriver driver;
	
	
	public HomePage(WebDriver driver){

        this.driver = driver;

    }

    public void verifyHeader(){    	
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    	WebElement headerMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'header-message']")));
    	boolean headerStatus = headerMessage.isDisplayed();
    	Assert.assertTrue(headerStatus,"Header is not available");
    }
    
    public void clickProductSection(String mainSection, String subSection) {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    	WebElement seeMoreLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role = 'listitem']//h3[text()='"+mainSection+"']//following::a[normalize-space(text()) = '"+subSection+"' ]//following::span[text()='See More ˅'][1]")));
    	seeMoreLink.click();
    	
    	WebElement sectionLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role = 'listitem']//h3[text()='"+mainSection+"']//following::a[normalize-space(text()) = '"+subSection+"' ]")));
    	sectionLink.click();    	
    }
    
    public void searchProduct(String searchProduct) {    	
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    	WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='term']")));
    	searchField.sendKeys(searchProduct);    	
    }
    
    public void clickSearchButton() {    	
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    	WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id=\"search-btn\"]")));
    	searchButton.click(); 
    }
    
    public void verifySerachResults() {
    	
    }

}
