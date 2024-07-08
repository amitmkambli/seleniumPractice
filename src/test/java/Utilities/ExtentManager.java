package Utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Tests.BaseTest;
import Tests.DriverFactory;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter spartReporter = new ExtentSparkReporter(fileName);
		
		spartReporter.config().setTheme(Theme.STANDARD);
		spartReporter.config().setDocumentTitle(fileName);
		spartReporter.config().setEncoding("utf-8");
		spartReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        extent.attachReporter(spartReporter);
        extent.setSystemInfo("Automation Tester", "Amit Kambli");
        extent.setSystemInfo("Organization", "Testers Inc.");
        extent.setSystemInfo("Build no", "JUN-001");
        
        return extent;
	}
	
	public static String convertDate(String prefix) {
		Date date = new Date();
		String newDate = date.toString().replaceAll("\\s|:", "_");
		return prefix.concat(newDate);
	}
	
	public static String screenshotName;
	
	public static void takeScreenshot() {
		
		//screenshotName = System.getProperty("user.dir")+"\\reports\\" + convertDate("").concat(".png");
		screenshotName = convertDate("").concat(".png");

		File directory = new File("reports");

		//better to use OutputType.BYTES or Base64 instead of OutputType.File
		byte[] file = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
		try {
			//FileUtils.copyFile(file, new File(screenshotName));
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(file));
			ImageIO.write(img, "png", new File(directory, screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
