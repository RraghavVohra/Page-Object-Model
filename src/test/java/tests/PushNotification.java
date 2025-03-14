package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import org.testng.Assert;

import pageObjects.LoginPage;
import pageObjects.PushNotificationPage;

public class PushNotification extends Base {
	
	 PushNotificationPage pushNotifyPage;
	 // private PushNotificationPage pushNotifyPage;
	 LoginPage loginPage;
	 
	 
	 @AfterMethod
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }

	
	@Test(priority=1) 
	public void test_TC_PN_001_takenToPushNotificationsScreen() throws InterruptedException {
		
		// Not calling it.Since, it was already called by openBrowserAndApplication   
		// loadPropertiesFile();
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
		
    	pushNotifyPage = new PushNotificationPage(driver);
    	pushNotifyPage.clickOnCommunicationTab();
    	Thread.sleep(5000);
    	pushNotifyPage.clickOnNotifications();
    	pushNotifyPage.clickOnActionsButton();
		pushNotifyPage.clickOnCreateAppNotification();
		String expectedpageHeadingText = "PUSH NOTIFICATION";
		String actualPageHeadingText = pushNotifyPage.getPageHeading();
		Assert.assertEquals(expectedpageHeadingText,actualPageHeadingText);
		
	}
	 
	 
	 
	
}
