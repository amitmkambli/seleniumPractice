package Tests;


import java.util.LinkedHashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Listners.ExtentListeners;
import Pages.CatalogPage;
import Pages.OrderSummaryPage;
import Pages.TermsAndConditionsPage;
import Utilities.DataProviders;

public class PlaceOrderTest extends BaseTest {
	
	//Note: excel sheet name of data excel file should be name of the test method 'checkOrderFlow'
	@Test(enabled =  true, dataProviderClass = Utilities.DataProviders.class, dataProvider = "orderItemsFromExcel")	
	public void checkOrderFlow(LinkedHashMap<String, String> map) {
		WebDriver driver = DriverFactory.getDriver();
		
		CatalogPage cp = new CatalogPage(driver);

		ExtentListeners.testReport.get().info("Scenario : "+map.get("scenario"));
		String itemName = map.get("itemName");
		String country = map.get("country");
		
		cp.searchItem(itemName);
		cp.waitForSearchResult();
		cp.clickAddToCart();
		cp.clickOnCart();
		OrderSummaryPage os =  cp.checkout();
		
		os.waitForPlaceOrder();
		TermsAndConditionsPage tp = os.clickPlaceOrder();
		
		tp.waitForProceedButton();
		tp.selectCountry(country);
		
		Boolean ischeckBoxSelected =  tp.checkBox("Terms & Conditions");
		Assert.assertTrue(ischeckBoxSelected,"T&C checkbox is not selected");
		
		tp.clickProceed();
		cp = tp.waitforHomepage();

		Assert.assertTrue(cp.isSearchItemPresent(), "search box is not displayed.");			
	}
	
	@DataProvider(name = "orderItemsfromPlaceOrderTest")
	public Object[][] getData(){
		return new Object[][]  {
			{"Cucumber", "India"},
			{"Brinjal", "Australia"},
			{"Apple", "Canada"},
			{"Mango", "Germany"},
		};
	}

}
