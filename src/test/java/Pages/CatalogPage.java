package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage extends BasePage {
	
	public CatalogPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(how = How.CSS, using = "input.search-keyword") private List<WebElement> searchItems;
	@FindBy(how = How.CSS, using = "div.cart-info tr:nth-child(1) td:nth-child(3)") private WebElement qtyItems;
	@FindBy(how = How.CSS, using = "div.cart-info tr:nth-child(2) td:nth-child(3)") private WebElement priceItems;
	@FindBy(how = How.CSS, using = "div.product:nth-child(1) button") private WebElement AddToCart;
	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'Brocolli')]") private WebElement brocolli;
	@FindBy(how = How.CSS, using = "div.product:nth-child(1) h4") private WebElement itemName;
	@FindBy(how = How.CSS, using = "div.product:nth-child(1) img") private WebElement imgInfo;
	@FindBy(how = How.CSS, using = "a.cart-icon") private WebElement cart;
	@FindBy(how = How.CSS, using = "div.cart-preview button") private WebElement checkout;
	
	
	public boolean isSearchItemPresent() {
		//return  !searchItems.isEmpty();
		return  checkElementPresent(searchItems, "search item text box");
	}
	
	public void searchItem(String name) {
		type(searchItems.get(0), "search item", name + Keys.ENTER);
	}
	
	public String getqtyItems() {
		return qtyItems.getText();
	}
	
	public String getpriceItems() {
		return priceItems.getText();
	}
	
	public void clickAddToCart() {
		click(AddToCart, "Add to Cart");
	}
	
	public String getitemName() {
		return itemName.getText();
	}
	
	public String getimgInfo() {
		return imgInfo.getAttribute("alt");
	}

	public void waitForSearchResult() {
		//wait.until(ExpectedConditions.invisibilityOf(brocolli));
		waitForCondition(brocolli, "invisibilityOf");
	}
	
	public void clickOnCart() {
		click(cart, "Cart");
	}
	
	public OrderSummaryPage checkout() {
		click(checkout, "Checkout");
		OrderSummaryPage oSummary = new OrderSummaryPage(driver);
		return oSummary;
	}
	
}
