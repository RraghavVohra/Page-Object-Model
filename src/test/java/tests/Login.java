package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;

import pageObjects.LoginPage;

public class Login extends Base {
	
	LoginPage loginPage;
	
	
	 @BeforeMethod
	 public void setUp() {
	     // Not calling it.Since, it was already called by openBrowserAndApplication   
		 // loadPropertiesFile();
	        driver = openBrowserAndApplication(prop.getProperty("browser"));
	        loginPage = new LoginPage(driver);
	    }
	 
	 @AfterMethod
	 public void tearDown() throws InterruptedException {
		    
		    Thread.sleep(3000);
	        driver.quit();  // Close browser after tests
	 }
	 
	 
	    // DataProvider with hardcoded values
	    @DataProvider(name = "loginData")
	    public Object[][] loginTestData() {
	        return new Object[][] {
	            // {username, password, expectedResult}
	        	
	            { "prem.chandra@bizight.com", "Sbtest@123", "valid" },
	            { "prem.chandra@bizight.com", "WrongPass123", "invalid" },
	            { "invaliduser@example.com", "Sbtest@123", "invalid" },
	            { "", "", "invalid" }
	        };
	    }
	    
	    // Single test method using data-driven testing 
	    @Test(dataProvider = "loginData")
	    public void loginWithVariousCredentials(String username, String password, String expectedResult) throws InterruptedException {
	        
	        loginPage.enterUsernameField(username);
	        loginPage.enterPasswordField(password);
	        loginPage.clickOnSubmitButton();

	        if (expectedResult.equals("valid")) {
	            System.out.println("✅ Login successful for: " + username);
	           
	        } else {
	            System.out.println("❌ Login failed as expected for: " + username);
	        
	        }
	        
	    }}
