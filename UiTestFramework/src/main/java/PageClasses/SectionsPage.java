package PageClasses;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class SectionsPage {
			
	WebDriver driver;
			
			
	public SectionsPage(WebDriver driver){
        this.driver = driver;
	}
		    
	public void clickBrandDropDown() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement brandDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()=\"Brands\"]//parent::label)[1]")));
		brandDropDown.click();    	
	}
	
	public void selectBrand(String BrandName) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement brandSelection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+BrandName+"']//parent::span)[1]")));
		brandSelection.click();    	
	}
	
	public void verifyBrandResults(String BrandName){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> resultBrands = driver.findElements(By.xpath("//h3[contains(text(),'"+BrandName+"')]"));
		
		int resultCount = resultBrands.size();
		if (resultCount>1) {
			System.out.println(resultCount + "Results retrieved for the selected brand");			
		}else {
			System.out.println(resultCount + "Results retrieved for the selected brand");
		}
	}
	
	public void clickPriceSortingDesc() {		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement priceSortingButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Price']//parent::a")));
		priceSortingButton.click(); 
		
		try {
			WebElement indicatorDesc = driver.findElement(By.xpath("//i[@class='sprite-icons cI i-descending-green-active i-descending-green']"));
    	
		}catch(Exception e){
			WebDriverWait waitn = new WebDriverWait(driver,Duration.ofSeconds(30));
			WebElement priceSortingButtonn = waitn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Price']//parent::a")));
			priceSortingButtonn.click();   
			
		}
	}
	
	public void verifyPriceDescSorting() {		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		List<WebElement> results = driver.findElements(By.xpath("//div[@class='db gM ei b hE be f16-360-o ff vb uT ellipsis-1']"));
		ArrayList<Float> priceList = new ArrayList<Float>();
		
		int resultsSize = results.size();
		
		for (int i = 0; i<resultsSize; i=i+1) {		
		   String priceValue = results.get(i).getText();
		   String[] myArray = priceValue.split(" ");
		   String price = myArray[1].replace(",", "");
		   Float priceNew = Float.parseFloat(price);
		   priceList.add(priceNew); 
		}
		
		//remove the prices which belongs to add
		List<WebElement> resultsAd = driver.findElements(By.xpath("//span[@class = 'vj R eA iz vi vk kK E']//following::div[@class='db gM ei b hE be f16-360-o ff vb uT ellipsis-1'][1]"));
		int resultsAdSize = resultsAd.size();		
		
		for (int i = 0; i<resultsAdSize; i=i+1) {		
			   String priceAdValue = resultsAd.get(i).getText();
			   String[] myArrayAdd = priceAdValue.split(" ");
			   String priceAd = myArrayAdd[1].replace(",", "");;	
			   Float priceAdd = Float.parseFloat(priceAd);
			   priceList.remove(priceAdd); 
		}		
		
		for(int i=0; i< priceList.size()-1 ; i++) {
			 if ((priceList.get(i) > priceList.get(i+1)) || (priceList.get(i).equals(priceList.get(i+1)))) {
				 Assert.assertTrue(true, "Price is in descending order, first price:"+priceList.get(i)+" Second price:"+priceList.get(i+1));
	         }else{
	        	 Assert.fail("Price is not in descending order, first price:"+priceList.get(i)+" Second price:"+priceList.get(i+1));	        	
	         }       
	    }	    	 
	
		
	}
	
}
