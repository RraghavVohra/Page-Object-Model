package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import util.ExtentReporter;
import util.Utilities;
import java.lang.reflect.Field;




public class MyListeners implements ITestListener {
	
	ExtentReports extentReport = null;
	ExtentTest extentTest = null;
	
	

	@Override
	public void onStart (ITestContext context) {
		// TODO Auto-generated method stub
		extentReport = ExtentReporter.getExtentReport();	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.pass(result.getName()+" test got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;

		// Retrieve the test class instance
		Object testInstance = result.getInstance();
		try {
			// Try to get the 'driver' field
			Field field = testInstance.getClass().getSuperclass().getDeclaredField("driver");
			field.setAccessible(true); // Allow access to private fields
			driver = (WebDriver) field.get(testInstance);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

		if (driver == null) {
			System.out.println("Driver is null. Cannot capture screenshot.");
		} else {
			System.out.println("Driver retrieved successfully.");
			// Capture screenshot
	        String screenshotFilePath = Utilities.captureScreenshot(driver, result.getName());
	        extentTest.addScreenCaptureFromPath(screenshotFilePath);
		}
		 // Log the exception and mark as failed
	    extentTest.log(Status.INFO, result.getThrowable());
		extentTest.fail(result.getName() + " test got failed");
	}
	/*public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null; // Making it global
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		String screenshotFilePath = Utilities.captureScreenshot(driver,result.getName());
		
		extentTest.addScreenCaptureFromPath(screenshotFilePath);
		extentTest.fail(result.getName()+" test got failed");
	}
	*/

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.skip(result.getName()+" test got skipped");
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" test execution started");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		try {
			
			Properties prop = Utilities.loadPropertiesFile();
			String extentReportFilePath = System.getProperty("user.dir")+prop.getProperty("extentreportpath");
			File extentReportFile = new File(extentReportFilePath);
			Desktop.getDesktop().browse(extentReportFile.toURI());
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	
	

}
