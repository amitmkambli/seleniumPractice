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

public class TermsAndConditionsPage extends BasePage{

	
	public TermsAndConditionsPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//button[text()='Proceed']") private WebElement proceed;
	@FindBy(how = How.XPATH, using = "//select") private WebElement selectCountry;
	@FindBy(how = How.CSS, using = "input.chkAgree") private WebElement tcCheckbox;
	@FindBy(how = How.CSS, using = "div.wrapperThree") private WebElement thankYouMsg;

	
	public void waitForProceedButton() {
		waitForCondition(proceed, "visibilityOf");
	}
	
	public void clickProceed() {
		click(proceed, "proceed");
	}
	
	public void selectCountry(String value) {
		select(selectCountry, value);
	}
	
	public boolean checkBox(String value) {
		return checkCheckBox(tcCheckbox, value);
	}
	
	public CatalogPage waitforHomepage() {
		waitForCondition(thankYouMsg, "invisibilityOf");
		CatalogPage cp = new CatalogPage(driver);
		return cp;
	}
	
}
