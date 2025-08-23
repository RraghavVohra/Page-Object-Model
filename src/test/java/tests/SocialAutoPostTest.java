package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.LoginPage;
import pageObjects.SocialAutoPostPage;
import util.Utilities;

public class SocialAutoPostTest extends Base {
	
	SocialAutoPostPage socialautopostpage;
    LoginPage loginPage;
    
    
	
	@AfterMethod
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }
	
	
	@Test(priority=1)
	public void test_TC_PN_01_whenPostedWithPngImageWithCobranding() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();
    	
    	Thread.sleep(2000);
    	
    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
    	socialautopostpage.uploadFileInPNGUsingAutoIt();
    	Thread.sleep(2000); 
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    
    	Thread.sleep(2000);   	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    Thread.sleep(2000);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnStaticText();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnTwitter();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnLinkedIn();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnFacebook();
	    Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30"); 
	    socialautopostpage.verifySelection();
	    
	    socialautopostpage.clickOnSchedulePostButton();
	    
	    Thread.sleep(3000);
        
        System.out.println("Test Case TC_PN_01 is passed!");
        
        Thread.sleep(3000);
	      
	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();
	      
	    Thread.sleep(5000);
	    socialautopostpage.clickOnLogoutButton();
	   	
		
	}

	@Test(priority=2)
	public void test_TC_PN_02_whenPostedWithJpgImageWithCobranding() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();
    	
    	Thread.sleep(2000);
    	
    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        socialautopostpage.uploadFileInJPGUsingAutoIt();
        
        Thread.sleep(2000); 
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    
    	Thread.sleep(2000);   	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    Thread.sleep(2000);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnStaticText();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnTwitter();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnLinkedIn();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnFacebook();
	    Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30"); 
	    socialautopostpage.verifySelection();
	    
	    socialautopostpage.clickOnSchedulePostButton();
	    
	    Thread.sleep(3000);
        
        System.out.println("Test Case TC_PN_02 is passed!");
        
        Thread.sleep(3000);
	      
	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();
	      
	    Thread.sleep(5000);
	    socialautopostpage.clickOnLogoutButton();
	}
	
	@Test(priority=3)
	public void test_TC_PN_03_whenPostedWithVideo() throws InterruptedException, IOException {
	
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();
    	
    	Thread.sleep(2000);
    	
    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
    	socialautopostpage.uploadFileInMP4UsingAutoIt();
    	Thread.sleep(3000);
    	socialautopostpage.uploadFileForMP4FileWithThumbnailInJPGformat();
    	Thread.sleep(2000); 
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    
    	Thread.sleep(2000);   	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    Thread.sleep(2000);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnStaticText();
	    Thread.sleep(2000);
	    // socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30"); 
	    socialautopostpage.verifySelection();
	    
	    socialautopostpage.clickOnSchedulePostButton();
	    
	    Thread.sleep(3000);
        
        System.out.println("Test Case TC_PN_03 is passed!");
        
        Thread.sleep(3000);
	      
	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();
	      
	    Thread.sleep(5000);
	    socialautopostpage.clickOnLogoutButton();
		
		
	}
	
	@Test(priority=4)
	public void test_TC_PN_04_whenPostedWithSpecialCharacterInTitleAndDescription() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();
    	
    	Thread.sleep(2000);
    	
    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        socialautopostpage.uploadFileInJPGUsingAutoIt();
        
        Thread.sleep(2000); 
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    
    	Thread.sleep(2000);   	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    Thread.sleep(2000);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnStaticText();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnTwitter();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnLinkedIn();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnFacebook();
	    Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30"); 
	    socialautopostpage.verifySelection();
	    
	    socialautopostpage.clickOnSchedulePostButton();
	    
	    Thread.sleep(3000);
        
        System.out.println("Test Case TC_PN_04 is passed!");
        
        Thread.sleep(3000);
	      
	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();
	      
	    Thread.sleep(5000);
	    socialautopostpage.clickOnLogoutButton();    
		
		
	}

	@Test(priority=5)
	public void test_TC_PN_05_whenPostedOnAllSocialMediaWithCustomUrl() throws InterruptedException, IOException	{
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();
    	
    	Thread.sleep(2000);
    	
    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        socialautopostpage.uploadFileInJPGUsingAutoIt();
        
        Thread.sleep(2000); 
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    
    	Thread.sleep(2000);   	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    Thread.sleep(2000);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnStaticText();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnCustomURLRadioButton();
	    Thread.sleep(2000);
	    socialautopostpage.enterCustomURL();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnTwitter();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnLinkedIn();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnFacebook();
	    Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30"); 
	    socialautopostpage.verifySelection();
	    
	    socialautopostpage.clickOnSchedulePostButton();
	    
	    Thread.sleep(3000);
        
        System.out.println("Test Case TC_PN_05 is passed!");
        
        Thread.sleep(3000);
	      
	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();
	      
	    Thread.sleep(5000);
	    socialautopostpage.clickOnLogoutButton();
		
		
		
	}
	
    @Test(priority=6)	
    public void test_TC_PN_06_whenPostedOnAllSocialMediaWithNoneOption() throws InterruptedException, IOException	{
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();
    	
    	Thread.sleep(2000);
    	
    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        socialautopostpage.uploadFileInJPGUsingAutoIt();
        
        Thread.sleep(2000); 
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    
    	Thread.sleep(2000);   	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    Thread.sleep(2000);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnStaticText();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnNoneRadioButton();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnTwitter();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnLinkedIn();
	    Thread.sleep(2000);
	    socialautopostpage.clickOnFacebook();
	    Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30"); 
	    socialautopostpage.verifySelection();
	    
	    socialautopostpage.clickOnSchedulePostButton();
	    
	    Thread.sleep(3000);
        
        System.out.println("Test Case TC_PN_06 is passed!");
        
        Thread.sleep(3000);
	      
	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();
	      
	    Thread.sleep(5000);
	    socialautopostpage.clickOnLogoutButton();
		
		
		
	}
	
	

    



}
