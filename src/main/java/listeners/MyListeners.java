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
