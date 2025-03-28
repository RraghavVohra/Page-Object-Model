package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
	
	
	
	    @Test(priority=1)
	    public void loginWithValidCredentials() throws InterruptedException {
	    	
	    	
	    	loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	
	    	System.out.println("User Logged in Successfully.Test case loginWithValidCredentials passed.");
	    }
	    
	    
	    @Test(priority=2)
	    public void loginWithValidUsernameInvalidPassword() throws InterruptedException {
	    	
	    	loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("invalidpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	
	    	System.out.println("User not able to Login.Test Case loginWithValidUsernameInvalidPassword Passed");
	    	
	    }
	    
	    @Test(priority=3)
	    public void loginWithInvalidUsernameValidPassword() throws InterruptedException {
	    	
	    	loginPage.enterUsernameField(prop.getProperty("invalidusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	
	    	System.out.println("User not able to Login.Test Case loginWithInvalidUsernameValidPassword Passed");
	    	
	    }
	    
	    @Test(priority=4)
	    public void loginByEnteringNothinginBothTheFields() {
	    	
            loginPage.clickOnSubmitButton();
	    	System.out.println("User not able to Login.Test Case loginByEnteringNothinginBothTheFields Passed");
	    	
	    	
	    }
	    

}
