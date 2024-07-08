package Tests;

import org.openqa.selenium.WebDriver;

public final class DriverFactory {
	private DriverFactory() {}
	
public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	
	public static void setDriver(WebDriver driver) {
		threadDriver.set(driver);
	}
	
	public static void remove() {
		threadDriver.remove();
	}

}
