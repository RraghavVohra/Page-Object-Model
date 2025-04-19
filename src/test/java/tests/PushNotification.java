package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import org.testng.Assert;

import pageObjects.LoginPage;
import pageObjects.PushNotificationPage;
import util.Utilities;


public class PushNotification extends Base {
	
	 PushNotificationPage pushNotifyPage;
	 // private PushNotificationPage pushNotifyPage;
	 LoginPage loginPage;
	 
	 
	 @AfterMethod
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }

	
	@Test(priority=1) 
	public void test_TC_PN_01_takenToPushNotificationsScreen() throws InterruptedException {
		
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
    	
		String expectedpageHeadingText = "PUSH NOTIFICATION";
		String actualPageHeadingText = pushNotifyPage.getPageHeading();
		Assert.assertEquals(expectedpageHeadingText,actualPageHeadingText);
		
		String actualURL = driver.getCurrentUrl();
		String expectedURL = "https://app.spdevmfp.com/framework/AgencyCommunication/list";
		Assert.assertEquals(actualURL,expectedURL);
		
		
		System.out.println("TC_PN_001 has Passed!");
		
	}
	
	@Test(priority=2)
	public void test_TC_PN_04_takentoCreateAppNotificationScreen() throws InterruptedException {
		
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
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://app.spdevmfp.com/framework/AgencyCommunication/create";
        Assert.assertEquals(actualURL,expectedURL);
        
        System.out.println("TC_PN_002 has Passed!");
		
		
		
	}
	
	@Test(priority=3)
	public void test_TC_PN_07_fillsAllTheFieldsExceptNotificationName() throws InterruptedException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	pushNotifyPage = new PushNotificationPage(driver);
    	pushNotifyPage.clickOnCommunicationTab();
    	pushNotifyPage.clickOnNotifications();
    	pushNotifyPage.clickOnActionsButton();
    	pushNotifyPage.clickOnCreateAppNotification();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://app.spdevmfp.com/framework/AgencyCommunication/create";
        Assert.assertEquals(actualURL,expectedURL);
        
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        pushNotifyPage.clickOnCategoryDropdown();
        pushNotifyPage.scrollToCategoryDropdown();
        pushNotifyPage.enterIntoSearchTextfield();
        Thread.sleep(2000);      
        pushNotifyPage.clickOnTargetCategory();
        // Clicking on Blank space after selecting the target category so that the dialog box closes
        pushNotifyPage.clickOnBlankSpace();
        Thread.sleep(2000);
       
        pushNotifyPage.attachPhoto(prop.getProperty("imagePath"));
        Thread.sleep(2000);
        pushNotifyPage.clickOnCropButton();
        pushNotifyPage.clickOnCustomLinkButton();
        pushNotifyPage.enterValueLinkTextfield(prop.getProperty("customlinkfield"));
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        
        pushNotifyPage.clickOnSubmitButton();
        
        // As i wanted to scroll to the top, so i defined the method in Utilities class
        Utilities.scrollToTop(driver);// You already have the driver in your test
        
        String validationMsg = pushNotifyPage.getValidationMessageForNotificationName();
        Assert.assertEquals(validationMsg, "Please fill out this field.");
        // Only printed if the assertion passes
        System.out.println("✅ Notification Name field showed the correct validation message.");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
        
        
        
        
		
	}

	
	
}
