package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CatalogPage;
//import Pages.OrderSummary;
import Pages.OrderSummaryPage;

public class CheckoutTest extends BaseTest  {
	
	@Test(enabled =  true)
	public void checkOrderSummary() {
//		WebDriver driver = threadDriver.get();
		WebDriver driver = DriverFactory.getDriver();
		CatalogPage cp = new CatalogPage(driver);
		
		String itemName = "Carrot";
		
		cp.searchItem(itemName);
		cp.waitForSearchResult();
		cp.clickAddToCart();
		
		String price = cp.getpriceItems();
		
		cp.clickOnCart();
		
		OrderSummaryPage os = cp.checkout();	 
		os.waitForPlaceOrder();
		
		Assert.assertTrue(os.getTotalAmmount().equals(price),"total price is not matching");			
		Assert.assertTrue(os.gettotalDiscountPercent().equals("0%"),"total discount is not 0%");			
	}
	
	@Test(enabled =  true)
	public void applyPromoCode() {
//		WebDriver driver = threadDriver.get();
		WebDriver driver = DriverFactory.getDriver();
		CatalogPage cp = new CatalogPage(driver);

		String itemName = "Carrot";
		cp.searchItem(itemName);
		
		cp.waitForSearchResult();
		
		cp.clickAddToCart();
		
		cp.clickOnCart();
		
		OrderSummaryPage os = cp.checkout();
		os.waitForPlaceOrder();
		
		os.clickapplyPromoCode();
		Assert.assertTrue(os.getpromocodeMsg().contains("Empty code"),"incorrect msg for empty promo code");			
	}
	

}
