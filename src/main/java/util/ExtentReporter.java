package util;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	public static ExtentReports getExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		Properties prop = Utilities.loadPropertiesFile();
		
		String extentReportFilePath = System.getProperty("user.dir")+prop.getProperty("extentreportpath");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFilePath);
		sparkReporter.config().setReportName("Extent Report - 01");
		sparkReporter.config().setDocumentTitle("Extent Report - Salespanda");
		extentReport.attachReporter(sparkReporter);
		
		return extentReport;
		
		
		
	}

}
