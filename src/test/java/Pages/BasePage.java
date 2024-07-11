package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Listners.ExtentListeners;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	int implicitWait = 20;
	
	public BasePage(WebDriver driver){
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(9));
		js = (JavascriptExecutor)driver;
	}
	
	public void click(WebElement element, String elementName) {
		element.click();
		ExtentListeners.testReport.get().info("Clicking on : "+elementName);
	}
	
	public void type(WebElement element, String elementName, String value) {
		element.sendKeys(value);
		ExtentListeners.testReport.get().info("Typing in : "+elementName+" entered the value as : "+value);
	}
	
	public void waitForCondition(WebElement element, String condition) {
		switch (condition) {
		case ("stalenessOf") -> wait.until(ExpectedConditions.stalenessOf(element));
		case ("visibilityOf") -> wait.until(ExpectedConditions.visibilityOf(element));		
		case ("invisibilityOf") -> wait.until(ExpectedConditions.invisibilityOf(element));
		}
		ExtentListeners.testReport.get().info("Waiting for : "+condition);
	}
	
	public void waitForCondition(String value, String condition) {
		switch (condition) {			
		case ("urlToBe") -> wait.until(ExpectedConditions.urlToBe(condition));
		}
		ExtentListeners.testReport.get().info("Waiting for : "+condition);
	}
	
	public void select(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		ExtentListeners.testReport.get().info("Selecting : "+value);
	}
	
	public boolean checkCheckBox(WebElement element, String value) {
		element.click();
		ExtentListeners.testReport.get().info("Checked checkbox : "+value);
		return element.isSelected();
	}
	
	public boolean checkElementPresent(List<WebElement> list, String value) {
		ExtentListeners.testReport.get().info("Check if element is present : "+value);
		return  !list.isEmpty();
		
	}
	
	

}
