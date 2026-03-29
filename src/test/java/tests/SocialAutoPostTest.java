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
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
    	//socialautopostpage.uploadFileInJPGUsingAutoIt();
    	// Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
    	socialautopostpage.uploadFileInPNGUsingAutoIt(prop.getProperty("imagePathpng"));
    	// Thread.sleep(2000); // Removed — wait moved into uploadFileInPNGUsingAutoIt()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    socialautopostpage.clickOnTwitter();
	    // Thread.sleep(2000); // Removed — clickOnLinkedIn() already waits for element
	    socialautopostpage.clickOnLinkedIn();
	    // Thread.sleep(2000); // Removed — clickOnFacebook() already waits for element
	    socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000); // Removed — ClickOnOpenDateTimePicker() already waits for element
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_01 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();


	}

	@Test(priority=2)
	public void test_TC_PN_02_whenPostedWithJpgImageWithCobranding() throws InterruptedException, IOException {

        driver = openBrowserAndApplication(prop.getProperty("browser"));

        loginPage = new LoginPage(driver);
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        // Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
        socialautopostpage.uploadFileInJPGUsingAutoIt(prop.getProperty("imagePath"));

        // Thread.sleep(2000); // Removed — wait moved into uploadFileInJPGUsingAutoIt()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    socialautopostpage.clickOnTwitter();
	    // Thread.sleep(2000); // Removed — clickOnLinkedIn() already waits for element
	    socialautopostpage.clickOnLinkedIn();
	    // Thread.sleep(2000); // Removed — clickOnFacebook() already waits for element
	    socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000); // Removed — ClickOnOpenDateTimePicker() already waits for element
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_02 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();
	}

	@Test(priority=3)
	public void test_TC_PN_03_whenPostedWithVideo() throws InterruptedException, IOException {

        driver = openBrowserAndApplication(prop.getProperty("browser"));

        loginPage = new LoginPage(driver);
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
    	// Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
    	socialautopostpage.uploadFileInMP4UsingAutoIt(prop.getProperty("videoPath"));
    	// Thread.sleep(3000); // Removed — wait moved into uploadFileInMP4UsingAutoIt()
    	// Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
    	socialautopostpage.uploadFileForMP4FileWithThumbnailInJPGformat(prop.getProperty("thumbnailAttachmentInJPG"));
    	// Thread.sleep(2000); // Removed — wait moved into uploadFileForMP4FileWithThumbnailInJPGformat()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    // socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000);
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("24", "August 2026");
	    socialautopostpage.selectTimeThree("10", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_03 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();


	}

	@Test(priority=4)
	public void test_TC_PN_04_whenPostedWithSpecialCharacterInTitleAndDescription() throws InterruptedException, IOException {

        driver = openBrowserAndApplication(prop.getProperty("browser"));

        loginPage = new LoginPage(driver);
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        // Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
        socialautopostpage.uploadFileInJPGUsingAutoIt(prop.getProperty("imagePath"));

        // Thread.sleep(2000); // Removed — wait moved into uploadFileInJPGUsingAutoIt()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    socialautopostpage.clickOnTwitter();
	    // Thread.sleep(2000); // Removed — clickOnLinkedIn() already waits for element
	    socialautopostpage.clickOnLinkedIn();
	    // Thread.sleep(2000); // Removed — clickOnFacebook() already waits for element
	    socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000); // Removed — ClickOnOpenDateTimePicker() already waits for element
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_04 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();


	}

	@Test(priority=5)
	public void test_TC_PN_05_whenPostedOnAllSocialMediaWithCustomUrl() throws InterruptedException, IOException	{

        driver = openBrowserAndApplication(prop.getProperty("browser"));

        loginPage = new LoginPage(driver);
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        // Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
        socialautopostpage.uploadFileInJPGUsingAutoIt(prop.getProperty("imagePath"));

        // Thread.sleep(2000); // Removed — wait moved into uploadFileInJPGUsingAutoIt()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    socialautopostpage.clickOnCustomURLRadioButton();
	    // Thread.sleep(2000); // Removed — enterCustomURL() already waits for element
	    socialautopostpage.enterCustomURL();
	    // Thread.sleep(2000); // Removed — clickOnTwitter() already waits for element
	    socialautopostpage.clickOnTwitter();
	    // Thread.sleep(2000); // Removed — clickOnLinkedIn() already waits for element
	    socialautopostpage.clickOnLinkedIn();
	    // Thread.sleep(2000); // Removed — clickOnFacebook() already waits for element
	    socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000); // Removed — ClickOnOpenDateTimePicker() already waits for element
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_05 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();



	}

    @Test(priority=6)
    public void test_TC_PN_06_whenPostedOnAllSocialMediaWithNoneOption() throws InterruptedException, IOException	{

        driver = openBrowserAndApplication(prop.getProperty("browser"));

        loginPage = new LoginPage(driver);
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        // Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
        socialautopostpage.uploadFileInJPGUsingAutoIt(prop.getProperty("imagePath"));

        // Thread.sleep(2000); // Removed — wait moved into uploadFileInJPGUsingAutoIt()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    socialautopostpage.clickOnNoneRadioButton();
	    // Thread.sleep(2000); // Removed — clickOnTwitter() already waits for element
	    socialautopostpage.clickOnTwitter();
	    // Thread.sleep(2000); // Removed — clickOnLinkedIn() already waits for element
	    socialautopostpage.clickOnLinkedIn();
	    // Thread.sleep(2000); // Removed — clickOnFacebook() already waits for element
	    socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000); // Removed — ClickOnOpenDateTimePicker() already waits for element
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("24", "August 2025");
	    socialautopostpage.selectTimeThree("10", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_06 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();



	}


    @Test(priority=7)
    public void test_TC_PN_07_whenPostedOnAllSocialMediaWithMicrositeURL() throws InterruptedException, IOException	{

        driver = openBrowserAndApplication(prop.getProperty("browser"));

        loginPage = new LoginPage(driver);
        // Thread.sleep(2000); // Removed — clickOnAutomationTab() already waits for element
		loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
    	loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");

    	socialautopostpage =  new SocialAutoPostPage(driver);
    	socialautopostpage.clickOnAutomationTab();
    	socialautopostpage.clickOnSocialOption();
    	socialautopostpage.clickOnAutoPostTab();

    	// Thread.sleep(2000); // Removed — clickOnActionsButton() already waits for element

    	socialautopostpage.clickOnActionsButton();
    	socialautopostpage.clickOnCreatePostButton();
        // Passing file path from prop — sendKeys approach replaces AutoIt (Chrome 146 blocks .click() on file inputs)
        socialautopostpage.uploadFileInJPGUsingAutoIt(prop.getProperty("imagePath"));

        // Thread.sleep(2000); // Removed — wait moved into uploadFileInJPGUsingAutoIt()
    	Utilities.scrollDownByTwoHundred(driver);
    	socialautopostpage.clickOnEnableCobrandingButton();
    	// For Everytime different Name
    	String baseName = prop.getProperty("Titletextfield");
        String uniqueName = baseName + "_" + System.currentTimeMillis();
        socialautopostpage.enterInTitleTextfield(uniqueName);
    	socialautopostpage.enterValueInDescriptionTextfield(prop.getProperty("Descriptiontextfield"));

    	// Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
    	// Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    // Thread.sleep(2000); // Removed — clickOnPartnerCategoryButton() already waits for element
	    socialautopostpage.clickOnPartnerCategoryButton();
	    socialautopostpage.clickOnSelectPartnerCategory();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnSelectPartnerCategory()
	    socialautopostpage.clickOnStaticText();
	    // Thread.sleep(2000); // Removed — wait moved into clickOnStaticText()
	    socialautopostpage.clickOnTwitter();
	    // Thread.sleep(2000); // Removed — clickOnLinkedIn() already waits for element
	    socialautopostpage.clickOnLinkedIn();
	    // Thread.sleep(2000); // Removed — clickOnFacebook() already waits for element
	    socialautopostpage.clickOnFacebook();
	    // Thread.sleep(2000); // Removed — ClickOnOpenDateTimePicker() already waits for element
	    Utilities.scrollDownByTwoHundred(driver);
	    socialautopostpage.ClickOnOpenDateTimePicker();

	    // Thread.sleep(7000); // Removed — wait moved into ClickOnOpenDateTimePicker()

	    socialautopostpage.selectFutureDateTwo("31", "August 2025");
	    socialautopostpage.selectTimeThree("17", "30");
	    socialautopostpage.verifySelection();

	    socialautopostpage.clickOnSchedulePostButton();

	    // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

        System.out.println("Test Case TC_PN_07 is passed!");

        // Thread.sleep(3000); // Removed — clickOnProfileIcon() already waits for element

	    socialautopostpage.clickOnProfileIcon();
	    socialautopostpage.clickOnLogoutOption();

	    // Thread.sleep(5000); // Removed — wait moved into clickOnLogoutOption()
	    socialautopostpage.clickOnLogoutButton();



	}








}
