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
    	socialautopostpage.clickOnEnableCobrandingButton();
    	socialautopostpage.enterInTitleTextfield(prop.getProperty("Titletextfield"));
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    	
    	Thread.sleep(2000);
    	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    socialautopostpage.clickOnStaticText();
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDate("8", "May 2025");
	    socialautopostpage.selectHour("8");
	    socialautopostpage.selectMinute("8", "30");
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
    	socialautopostpage.clickOnEnableCobrandingButton();
    	socialautopostpage.enterInTitleTextfield(prop.getProperty("Titletextfield"));
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    	
    	Thread.sleep(2000);
    	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    socialautopostpage.clickOnStaticText();
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDate("8", "May 2025");
	    socialautopostpage.selectHour("8");
	    socialautopostpage.selectMinute("8", "30");
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
    	socialautopostpage.clickOnEnableCobrandingButton();
    	socialautopostpage.enterInTitleTextfield(prop.getProperty("Titletextfield"));
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    	
    	Thread.sleep(2000);
    	
    	// Scroll down by 400 pixels
	    Utilities.scrollDownByFourHundred(driver);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    socialautopostpage.clickOnStaticText();
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDate("10", "May 2025");
	    socialautopostpage.selectHour("8");
	    socialautopostpage.selectMinute("8", "30");
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
    	socialautopostpage.clickOnEnableCobrandingButton();
    	socialautopostpage.enterInTitleTextfield(prop.getProperty("Titletextfield"));
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));
    	
    	Thread.sleep(2000);
    	
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    socialautopostpage.clickOnStaticText();
	    socialautopostpage.ClickOnOpenDateTimePicker();
	    
	    Thread.sleep(7000);
	    
	    socialautopostpage.selectFutureDate("8", "May 2025");
	    socialautopostpage.selectHour("8");
	    socialautopostpage.selectMinute("8", "30");
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
	public void test_TC_PN_05_whenPostedOnAllSocialMedia() {
		
		
		
	}
}
