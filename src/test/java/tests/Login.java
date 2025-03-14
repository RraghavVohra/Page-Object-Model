package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;

import pageObjects.LoginPage;

public class Login extends Base {
	
	LoginPage loginPage;
	
	
	 @BeforeTest
	 public void setUp() {
	     // Not calling it.Since, it was already called by openBrowserAndApplication   
		 // loadPropertiesFile();
	        driver = openBrowserAndApplication(prop.getProperty("browser"));
	        loginPage = new LoginPage(driver);
	    }
	 
	 @AfterTest
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }
	
	
	
	    @Test(priority=1)
	    public void loginWithValidCredentials() {
	    	
	    	
	    	loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	
	    	System.out.println("User Logged in Successfully.Test case passed.");
	    }

}
