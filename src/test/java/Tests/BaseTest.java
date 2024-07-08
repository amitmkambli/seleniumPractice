package Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Utilities.ConfigReader;
import io.opentelemetry.sdk.autoconfigure.spi.internal.ConfigurableMetricReaderProvider;

public class BaseTest {
	

	public enum BrowserType{
		chrome,
		firefox
	}
	
	public static WebDriver browserSetUp(BrowserType browser) {
		WebDriver driver  = switch (browser) {
			case firefox ->  new FirefoxDriver();
			case chrome ->  new ChromeDriver();
			default ->  throw new IllegalArgumentException("Invalid browser type: " + browser);
		};
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod
	//@Parameters("browser")
	public void Setup() {		
		//WebDriver driver = browserSetUp("chrome");
		WebDriver driver = browserSetUp(BrowserType.chrome);
		DriverFactory.setDriver(driver);
		DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
	}
	
	@AfterMethod
	public void Teardown() {
		DriverFactory.getDriver().quit();
		DriverFactory.remove();
	}
	
}
