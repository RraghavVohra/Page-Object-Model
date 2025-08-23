package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	
	// Earlier this method was not static, but once we made it static, then it could be accessed with the help of class name.
	// For that to happen, we will go back to the MyListeners.java
	
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
