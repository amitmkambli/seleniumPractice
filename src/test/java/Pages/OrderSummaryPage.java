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
	
	@FindBy(how = How.XPATH, using = "//button[text()='Place Order']") private WebElement placeOrder;
	@FindBy(how = How.CSS, using = "span.totAmt") private WebElement totalAmmount;
	@FindBy(how = How.CSS, using = "span.discountPerc") private WebElement totalDiscountPercent;
	@FindBy(how = How.CSS, using = "button.promoBtn") private WebElement applyPromoCode;
	@FindBy(how = How.CSS, using = "span.promoInfo") private WebElement promocodeMsg;
	
	public void waitForPlaceOrder() {
		waitForCondition(placeOrder, "visibilityOf");
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
