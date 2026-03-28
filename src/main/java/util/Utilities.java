package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	
	// Earlier this method was not static, but once we made it static, then it could be accessed with the help of class name.
	// For that to happen, we will go back to the MyListeners.java
	/*
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		
		try {
			FileHandler.copy(srcFile,new File(screenshotFilePath));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return screenshotFilePath;
		}
	*/	
	
	// WHY: Captures screenshot and saves as PNG file.
    // Timestamp in filename prevents overwriting previous failures with same test name.
    // HOW: TakesScreenshot interface converts browser viewport to byte array,
    // FileUtils copies it to disk.
   
   public static String captureScreenshot(WebDriver driver, String testName) {
       String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       String fileName = testName + "_" + timestamp + ".png";
       String screenshotDir = System.getProperty("user.dir") + "/screenshots/";

       try {
           File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
           File destFile = new File(screenshotDir + fileName);
           destFile.getParentFile().mkdirs(); // create folder if it doesn't exist
           FileHandler.copy(srcFile, destFile);

           // Return RELATIVE path from report location to screenshot
           // Report is at: target/reports/ExtentReport.html
           // Screenshot is at: screenshots/fileName.png
           // Relative path from report to screenshot: ../../screenshots/fileName.png
           return "../../screenshots/" + fileName;

       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
   }
	
   /*	
   public static Properties loadPropertiesFile() {
		
		Properties prop = null;
		try {
			prop = new Properties();
			File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\projectdata.properties");
			FileReader fr = new FileReader(propFile);
			prop.load(fr);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e ) {
			e.printStackTrace();
		}
		
		return prop;
	}
	*/
	// WHY: Centralizes properties loading so every class doesn't need its own
    // FileReader logic. Single point of failure = easier to fix.
   
   public static Properties loadPropertiesFile() {
       Properties prop = new Properties();
       String path = System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties";
       try {
           prop.load(new FileInputStream(path));
       } catch (IOException e) {
           e.printStackTrace();
       }
       return prop;
   }
	
	

	public static void scrollToTop(WebDriver driver) {
		// TODO Auto-generated method stub
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollTo(0, 0);");
	}
	
	public static void scrollDownByFiveHundred(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500);");
		
	}
	
    public static void scrollDownByFourHundred(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400);");
		
	}
    
    public static void scrollDownByTwoHundred(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200);");
		
	}
    
    
	
	public static void scrollToBottom(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	

}
