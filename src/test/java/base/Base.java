package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
			ChromeOptions options = new ChromeOptions();

			// Fix: Windows display scaling shrinks the effective viewport, hiding the right side of the header.
			// --force-device-scale-factor=0.8 renders everything at 80% scale, equivalent to Ctrl+- zoom out,
			// so the full navigation bar including profile icon fits within the viewport.
			options.addArguments("--force-device-scale-factor=0.8");

			// Fix: "Timed out receiving message from renderer" — Chrome renderer process hangs on some page loads.
			// --disable-gpu prevents GPU-related renderer crashes on Windows.
			// --no-sandbox removes the sandbox restriction that can cause renderer timeouts in some environments.
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");

			// Fix: Chrome shows two password-related dialogs after login:
			// (a) the "Save password?" bubble — disabled by credentials_enable_service + password_manager_enabled
			// (b) the "Check your saved passwords — found in a data breach" modal — disabled by leak_detection pref + feature flag
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-features=PasswordLeakDetection");

			driver = new ChromeDriver(options);
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
		// Removed 35s implicit wait — mixing implicit + explicit waits causes unpredictable behavior.
		// All page objects already use explicit WebDriverWait, so implicit wait is unnecessary.
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		// Increased from 35s to 60s — renderer timeout was hitting at ~33s, leaving no margin
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(prop.getProperty("urlpreprod"));
		
		return driver;
	}
	
	// New Code as on 27/03/2025
	public WebDriver getDriver() {
		return driver;
	}
	
	

	
}



























































































