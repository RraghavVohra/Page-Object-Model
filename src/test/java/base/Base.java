package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import util.Utilities;

public class Base {
	
	protected WebDriver driver;      // Accessible by child classes
	// EARLIER WE WROTE -> Properties prop;
	public Properties prop;
	
	public Base() {
	
		prop = Utilities.loadPropertiesFile();
	}
	
	public WebDriver openBrowserAndApplication(String browserName) {
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}else if(browserName.equalsIgnoreCase("ie")) {
			driver= new InternetExplorerDriver();
		}else if(browserName.equalsIgnoreCase("safari")) {
			driver= new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(35));
		driver.get(prop.getProperty("urldev"));
		
		return driver;
	}
	
	// New Code as on 27/03/2025
	public WebDriver getDriver() {
		return driver;
	}
	
	
}



























































































