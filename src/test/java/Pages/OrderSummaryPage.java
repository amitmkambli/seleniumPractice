package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSummaryPage extends BasePage{

	
	public OrderSummaryPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//button[text()='Place Order']") WebElement placeOrder;
	@FindBy(how = How.CSS, using = "span.totAmt") WebElement totalAmmount;
	@FindBy(how = How.CSS, using = "span.discountPerc") WebElement totalDiscountPercent;
	@FindBy(how = How.CSS, using = "button.promoBtn") WebElement applyPromoCode;
	@FindBy(how = How.CSS, using = "span.promoInfo") WebElement promocodeMsg;
	
	
		
	By byPlaceOrder = By.xpath("//button[text()='Place Order']");	
	public By byPlaceOrder() {
		return byPlaceOrder;
	}
	
	public void waitForPlaceOrder() {//By by
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
		waitForCondition(driver, wait, byPlaceOrder, "visibilityOf");
	}
	
	public String getTotalAmmount() {
		return totalAmmount.getText();
	}
	
	public String gettotalDiscountPercent() {
		return totalDiscountPercent.getText();
	}
	
	public TermsAndConditionsPage clickPlaceOrder() {
		click(placeOrder, "placeOrder");
		TermsAndConditionsPage tp = new TermsAndConditionsPage(driver);
		return tp;
	}
	
	public void clickapplyPromoCode() {
		click(applyPromoCode, "applyPromoCode");
	}
	
	public String getpromocodeMsg() {
		return promocodeMsg.getText();
	}
	
}
