package Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Utilities.ConfigReader;

public class BaseTest {
	

	public enum BrowserType{
		chrome,
		firefox
	}
	
	public static WebDriver browserSetUp(String browser) {
		WebDriver driver  = switch (BrowserType.valueOf(browser)) {
			case firefox ->  new FirefoxDriver();
			case chrome ->  new ChromeDriver();
			default ->  throw new IllegalArgumentException("Invalid browser type: " + browser);
		};
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod
	public void Setup() {		
		WebDriver driver = browserSetUp(System.getProperty("browser"));
		DriverFactory.setDriver(driver);
		DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
	}
	
	@AfterMethod
	public void Teardown() {
		DriverFactory.getDriver().quit();
		DriverFactory.remove();
	}
	
}
