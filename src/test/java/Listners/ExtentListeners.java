package Listners;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Tests.DriverFactory;
import Utilities.ExtentManager;

public class ExtentListeners implements ITestListener {
	
	//static String fileName = ExtentManager.convertDate("Extent_").concat(".html");
	static String fileName = ("Extent_Report").concat(".html");
	
	//private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	private static ExtentReports extent = ExtentManager.createInstance("reports/"+fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//log exception
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		//to log console errors
		LogEntries entry =  DriverFactory.getDriver().manage().logs().get(LogType.BROWSER);
		List<LogEntry> logsList = entry.getAll();
		String log = "";

		for(LogEntry e : logsList) { log = log + e.getMessage(); }

		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Console Log Error:Click to see"
				+ "</font>" + "</b >" + "</summary>" + log +"</details>"+" \n");
		
		//take screenshot
		ExtentManager.takeScreenshot();
		testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
				MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
						.build());
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}
	}

}
