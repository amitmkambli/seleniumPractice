package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CatalogPage;

public class CatalogTest extends BaseTest {

	
	@Test(enabled =  true)
	public void checkElements() {
		WebDriver driver = DriverFactory.getDriver();

		CatalogPage cp = new CatalogPage(driver);
		Assert.assertTrue(cp.isSearchItemPresent(), "search box is not diaplayed.");
		Assert.assertTrue(cp.getqtyItems().equals("0"),"default price is not 0");
		Assert.assertTrue(cp.getpriceItems().equals("0"),"default price is not 0");

	}
	
	@Test(enabled =  true)
	public void checkPriceItem() {
		WebDriver driver = DriverFactory.getDriver();
		CatalogPage cp = new CatalogPage(driver);
		cp.clickAddToCart();
		//changed equals("0") to equals("1") to fail the test
		Assert.assertTrue(!cp.getqtyItems().equals("0"),"default price is 0 on adding product");
		Assert.assertTrue(!cp.getpriceItems().equals(""),"default price is 0 on adding product");	
	}
	
	@Test(enabled =  true)
	public void searchItem() {
		WebDriver driver = DriverFactory.getDriver();
		CatalogPage cp = new CatalogPage(driver);
		String itemName = "Carrot";

		cp.searchItem(itemName);
		cp.waitForSearchResult();
		cp.clickAddToCart();
				
		//System.out.println(cp.getitemName()+"::"+cp.getimgInfo());
		Assert.assertTrue(cp.getitemName().contains("Carrot"));
		Assert.assertTrue(cp.getimgInfo().contains("Carrot"));
		
		Assert.assertTrue(!cp.getqtyItems().equals("0"),"default price is 0 on adding product");
		Assert.assertTrue(!cp.getpriceItems().equals("0"),"default price is 0 on adding product");
	}
	

}
