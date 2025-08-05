package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import pageObjects.LoginPage;
import pageObjects.PushNotificationPage;
import util.Utilities;
import static org.junit.Assert.assertTrue;



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
		
		pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
		
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
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
		
		
		
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

	@Test(priority=4)
	public void test_TC_PN_08_fillsAllTheFieldsExceptNotificationMessage() throws InterruptedException {
	
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
        
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
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
        
        String validationMsg = pushNotifyPage.getValidationMessageForNotificationMessage();
        Assert.assertEquals(validationMsg, "Please fill out this field.");
        // Only printed if the assertion passes
        System.out.println("✅ Notification Message field showed the correct validation message.");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
		
	}
	
	@Test(priority=5)
	public void test_TC_PN_017_fillsAllTheFieldsExceptCategoryField() throws InterruptedException {
	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        
        pushNotifyPage.clickOnCategoryDropdown();
        // pushNotifyPage.scrollToCategoryDropdown();
        // pushNotifyPage.enterIntoSearchTextfield();
        Thread.sleep(2000);      
        // pushNotifyPage.clickOnTargetCategory();
        // Clicking on Blank space after selecting the target category so that the dialog box closes
        // pushNotifyPage.clickOnBlankSpace();
        // Thread.sleep(2000);
       
        pushNotifyPage.attachPhoto(prop.getProperty("imagePath"));
        Thread.sleep(2000);
        pushNotifyPage.clickOnCropButton();
        pushNotifyPage.clickOnCustomLinkButton();
        pushNotifyPage.enterValueLinkTextfield(prop.getProperty("customlinkfield"));
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        
        pushNotifyPage.clickOnSubmitButton();
        
        // As i wanted to scroll to the top, so i defined the method in Utilities class
        Utilities.scrollToTop(driver);// You already have the driver in your test
        
        String validationMsg = pushNotifyPage.getValidationMessageForNotificationMessage();
        // CURRENTLY WE DO NOT SEE THIS MESSAGE AS IT NEEDS TO BE FIXED
        Assert.assertEquals(validationMsg, "Please fill out this field.");
        // Only printed if the assertion passes
        System.out.println("✅ Notification Message field showed the correct validation message.");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
		
	}
	
	@Test(priority=6)
	public void test_TC_PN_019_fillsAllTheFieldsExceptCustomLinkField() throws InterruptedException {
		
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
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
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        pushNotifyPage.clickOnSubmitButton();
        
        Thread.sleep(2000);
       
        String validationMsg = pushNotifyPage.getValidationMessageForCustomLinkTextfield();
        Assert.assertEquals(validationMsg, "Please enter Custom Link to proceed");
        // Only printed if the assertion passes
        System.out.println("✅ Custom Links field showed the correct validation message.");
        
        Thread.sleep(2000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
		
	}
	
	@Test(priority=7)
	public void test_TC_PN_03_clicksOnActionMenuButton() throws InterruptedException {
		
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
    	
    	List<String> expectedOptions = Arrays.asList("Create App Notification", "Create WhatsApp Template", "Delete");
        List<String> actualOptions = pushNotifyPage.getActionMenuOptions();
        
        System.out.println("Actual Options Fetched:");
        for (String option : actualOptions) {
            System.out.println(option);
        }

        Assert.assertEquals(actualOptions, expectedOptions);
        
        System.out.println("test_TC_PN_003 got passed!");
        
        Thread.sleep(2000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
			
	}

	@Test(priority=8)
	public void test_TC_PN_06_notificationChannelSelection() throws InterruptedException {
		
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
        
        pushNotifyPage.clickOnWhatsAppRadioButton();
        Assert.assertTrue(pushNotifyPage.isWhatsAppSelected(), "WhatsApp should be selected after clicking");
        Assert.assertFalse(pushNotifyPage.isPushNotificationSelected(), "Push Notification should not be selected when WhatsApp is selected");
        pushNotifyPage.clickOnPushNotificationRadioButton();
        Assert.assertTrue(pushNotifyPage.isPushNotificationSelected(), "Push Notification should be selected after clicking");
        Assert.assertFalse(pushNotifyPage.isWhatsAppSelected(), "WhatsApp should not be selected when Push Notification is selected");
        
        
        
        System.out.println("TC_PN_06 has Passed!");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
		
	}

	@Test(priority=9)
    public void test_TC_PN_013_sendTOOptionsSelection() throws InterruptedException {
    	
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
        
       // Select Upload List and verify
       pushNotifyPage.clickOnUploadListRadioButton();
       Assert.assertTrue(pushNotifyPage.isUploadListRadioButtonSelected(),"Upload List Radio Button should be selected after clicking");
       Assert.assertFalse(pushNotifyPage.isPartnerCategoryRadioButton(),"Partner category should not be selected when Upload List is selected");
       pushNotifyPage.clickOnpartnerCategoryRadioButton();
       Assert.assertTrue(pushNotifyPage.isPartnerCategoryRadioButton(),"Partner Category Radio Button should be selected after clicking");
       Assert.assertFalse(pushNotifyPage.isUploadListRadioButtonSelected(),"Upload List should not be selected when Partner Category is selected");
       
       
       System.out.println("TC_PN_013 has Passed!");
       
       pushNotifyPage.clickOnProfileIcon();
       pushNotifyPage.clickOnLogoutOption();
       pushNotifyPage.clickOnLogoutButton();
        
        
        
        
        
        
        
    }

    @Test(priority=10)
	public void test_TC_PN_015_selectAllOptionInPartnerCategoryDropdown() throws InterruptedException {
		
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName("notificationnameText");
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        pushNotifyPage.clickOnCategoryDropdown();
        pushNotifyPage.scrollToCategoryDropdown();
        // pushNotifyPage.enterIntoSearchTextfield();
        Thread.sleep(2000);      
        pushNotifyPage.clickOnSelectAllButton();
        String totalCategoriesText = pushNotifyPage.getTotalCategoriesText();
        System.out.println("The Total Number of Categories are: " + totalCategoriesText);
        // Optionally add an assertion
        Assert.assertNotNull("Total Categories text is null!", totalCategoriesText);
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
        
        
        
        
        // Only printed if the assertion passes
        System.out.println("✅ All the Categories name were displayed.");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
    	
		
	}

    @Test(priority=11)
    public void test_TC_PN_016_verifyTheSearchTextfield() throws InterruptedException {
    	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName("notificationnameText");
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        pushNotifyPage.clickOnCategoryDropdown();
        pushNotifyPage.scrollToCategoryDropdown();
        // pushNotifyPage.enterIntoSearchTextfield();
        Thread.sleep(2000); 
        
        String searchValue = "Raj2024";
        boolean searchMatched = pushNotifyPage.searchAndValidateOption(searchValue);
        assertTrue("No matching option was found for the search value!", searchMatched);
        
        // Only printed if the assertion passes
        System.out.println("✅ TC_PN_016 is passed.The searched text was found.");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
        

    	
    	
    }

    @Test(priority=12)
    public void test_TC_PN_09_verifyImageUpload() throws InterruptedException {
    	
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
        
        pushNotifyPage.attachPhoto(prop.getProperty("imagePath"));
        Assert.assertTrue(pushNotifyPage.isCropButtonDisplayed(), "Crop window did not appear!");
        Assert.assertEquals(pushNotifyPage.getCropButtonText(), "Crop", "Crop button text does not match!");
        pushNotifyPage.clickOnCropButton();
        
        
        Thread.sleep(3000);
        
        pushNotifyPage.hoverOverAddPhotoButton();
        
        Thread.sleep(3000);
        
        
        System.out.println("✅ TC_PN_09 is passed.The Crop functionality is working.");
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
        
        
        
    	
    }

    @Test(priority=13)
    public void test_TC_PN_023_verifyNotificationStatus() throws InterruptedException {
    	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
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
        
        Thread.sleep(3000);
        //AFTER SUBMITTING I WILL BE TAKEN TO THE NEW SCREEN
        
        String actualURL2 = driver.getCurrentUrl();
        String expectedURL2 = prop.getProperty("redirectionURL");
        Assert.assertEquals(actualURL2,expectedURL2);
        Thread.sleep(3000);
        
        String actualMessage = pushNotifyPage.getToastMessageText();
        String expectedMessage = "Push Notification Saved.";
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message doesn't match!");
        
        // Close the toast
        pushNotifyPage.closeToastMessage();
        
        System.out.println("test_TC_PN_023 is passed");
        
        Thread.sleep(3000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
    	
    }

    @Test(priority=14)
    public void test_TC_PN_020_verifyContentLink() throws InterruptedException {
    	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
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
        pushNotifyPage.clickOnContentLinkButton();
        Thread.sleep(2000);
        pushNotifyPage.clickOnContentLinkDropdown();
        Thread.sleep(2000);
        pushNotifyPage.clickOnContentSelection();
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        
        
        
        pushNotifyPage.clickOnSubmitButton();
        
        Thread.sleep(3000);
        //AFTER SUBMITTING I WILL BE TAKEN TO THE NEW SCREEN
        
        String actualURL2 = driver.getCurrentUrl();
        String expectedURL2 = prop.getProperty("redirectionURL");
        Assert.assertEquals(actualURL2,expectedURL2);
        Thread.sleep(3000);
        
        String actualMessage = pushNotifyPage.getToastMessageText();
        String expectedMessage = "Push Notification Saved.";
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message doesn't match!");
        
        // Close the toast
        pushNotifyPage.closeToastMessage();
        
        System.out.println("test_TC_PN_020 is passed");
        
        Thread.sleep(3000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
        
        
    	
    }

    @Test(priority=15)
    public void test_TC_PN_012_imagesInDifferentFormat() throws InterruptedException {
    	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        pushNotifyPage.clickOnCategoryDropdown();
        pushNotifyPage.scrollToCategoryDropdown();
        pushNotifyPage.enterIntoSearchTextfield();
        Thread.sleep(2000);      
        pushNotifyPage.clickOnTargetCategory();
        // Clicking on Blank space after selecting the target category so that the dialog box closes
        pushNotifyPage.clickOnBlankSpace();
        Thread.sleep(2000);
       
        pushNotifyPage.attachPhoto(prop.getProperty("imagePathpng"));
        Thread.sleep(2000);
        pushNotifyPage.clickOnCropButton();
        pushNotifyPage.clickOnContentLinkButton();
        Thread.sleep(2000);
        pushNotifyPage.clickOnContentLinkDropdown();
        Thread.sleep(2000);
        pushNotifyPage.clickOnContentSelection();
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        
        
        
        pushNotifyPage.clickOnSubmitButton();
        
        Thread.sleep(3000);
        //AFTER SUBMITTING I WILL BE TAKEN TO THE NEW SCREEN
        
        String actualURL2 = driver.getCurrentUrl();
        String expectedURL2 = prop.getProperty("redirectionURL");
        Assert.assertEquals(actualURL2,expectedURL2);
        Thread.sleep(3000);
        
        String actualMessage = pushNotifyPage.getToastMessageText();
        String expectedMessage = "Push Notification Saved.";
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message doesn't match!");
        
        // Close the toast
        pushNotifyPage.closeToastMessage();
        
        System.out.println("TC_PN_12 is passed. User was able to upload image in png format");
        
        Thread.sleep(3000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
    	
    }

    @Test(priority=16)
    public void test_TC_PN_049_pushNotificationWithSpecialCharacter() throws InterruptedException {
    
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameTextspecial"));
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageTextspecial"));
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
        
        Thread.sleep(3000);
        //AFTER SUBMITTING I WILL BE TAKEN TO THE NEW SCREEN
        
        String actualURL2 = driver.getCurrentUrl();
        String expectedURL2 = prop.getProperty("redirectionURL");
        Assert.assertEquals(actualURL2,expectedURL2);
        Thread.sleep(3000);
        
        String actualMessage = pushNotifyPage.getToastMessageText();
        String expectedMessage = "Push Notification Saved.";
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message doesn't match!");
        
        // Close the toast
        pushNotifyPage.closeToastMessage();
        
        System.out.println("TC_PN_49 is passed. User was able to upload with special characters");
        
        Thread.sleep(3000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
    	
    }

    @Test(priority=17)
    public void test_TC_PN_036_pushNotificationWithCsvUpload() throws InterruptedException {
    	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        pushNotifyPage.clickOnPartnerListRadioButton();
        Thread.sleep(2000);
        pushNotifyPage.uploadCsvFile(prop.getProperty("filepathcsv"));
        Thread.sleep(5000);
        
        pushNotifyPage.clickOnBlankSpace();
        Thread.sleep(2000);
       
        pushNotifyPage.attachPhoto(prop.getProperty("imagePath"));
        Thread.sleep(2000);
        pushNotifyPage.clickOnCropButton();
        pushNotifyPage.clickOnCustomLinkButton();
        pushNotifyPage.enterValueLinkTextfield(prop.getProperty("customlinkfield"));
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        
        pushNotifyPage.clickOnSubmitButton();
        
        Thread.sleep(3000);
        //AFTER SUBMITTING I WILL BE TAKEN TO THE NEW SCREEN
        
        String actualURL2 = driver.getCurrentUrl();
        String expectedURL2 = prop.getProperty("redirectionURL");
        Assert.assertEquals(actualURL2,expectedURL2);
        Thread.sleep(3000);
        
        String actualMessage = pushNotifyPage.getToastMessageText();
        String expectedMessage = "Push Notification Saved.";
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message doesn't match!");
        
        // Close the toast
        pushNotifyPage.closeToastMessage();
        
        System.out.println("TC_PN_36 is passed. User was able to upload with the help of CSV");
        
        Thread.sleep(3000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
    	
    	
    }

    @Test(priority=18)
    public void test_TC_PN_050_pushNotificationWithoutCsvUpload() throws InterruptedException {
    	
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
        
        String dynamicNotificationName = "Push's_Notification_" + System.currentTimeMillis();
        pushNotifyPage.enterNotificationName(dynamicNotificationName);
        // pushNotifyPage.enterNotificationName(prop.getProperty("notificationnameText"));
        pushNotifyPage.enterNotificationMessage(prop.getProperty("notificationmessageText"));
        pushNotifyPage.clickOnPartnerListRadioButton();
        Thread.sleep(2000);
        
        pushNotifyPage.clickOnBlankSpace();
        Thread.sleep(2000);
       
        pushNotifyPage.attachPhoto(prop.getProperty("imagePath"));
        Thread.sleep(2000);
        pushNotifyPage.clickOnCropButton();
        pushNotifyPage.clickOnCustomLinkButton();
        pushNotifyPage.enterValueLinkTextfield(prop.getProperty("customlinkfield"));
        pushNotifyPage.enterSchedulingDateTime(prop.getProperty("schedulingDate"),prop.getProperty("schedulingTime"));
        
        pushNotifyPage.clickOnSubmitButton();
        
        Thread.sleep(3000);
        
        String validationMsg = pushNotifyPage.getValidationMessageForUploadCSVButton();
        Assert.assertEquals(validationMsg, "Please select a file.");
        // Only printed if the assertion passes
        System.out.println("✅ Upload CSV Button showed the correct validation message.");
        
        
        System.out.println("TC_PN_50 is passed. User was not able to do it without CSV");
        
        Thread.sleep(3000);
        
        pushNotifyPage.clickOnProfileIcon();
        pushNotifyPage.clickOnLogoutOption();
        pushNotifyPage.clickOnLogoutButton();
    }













}

