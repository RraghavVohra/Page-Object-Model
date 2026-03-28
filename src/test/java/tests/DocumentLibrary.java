package tests;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.DocumentLibraryPage;
import pageObjects.LoginPage;
import util.Utilities;


public class DocumentLibrary extends Base {
	
	DocumentLibraryPage docLibraryPage;
	LoginPage loginPage;
	
	
    
	 @AfterMethod
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }
       
   
	
	
	@Test(priority=1)
	public void test_TC_DL_01_takenToDocumentLibraryScreen() throws InterruptedException {
		
		driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	    loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    loginPage.clickOnSubmitButton();
	    System.out.println("User Logged in Successfully.");
		System.out.println("=== TEST CASE TC_DL_01 EXECUTING ===");
		
		docLibraryPage = new DocumentLibraryPage(driver);
		
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	String actualURL = driver.getCurrentUrl();
		String expectedURL = prop.getProperty("docLibraryExpectedURL");
		Assert.assertEquals(actualURL,expectedURL);

		System.out.println("Test Case TC_DL_01 is Passed!");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButtonTwo();
			
		
	}
	
	
	@Test(priority=2)
	public void test_TC_DL_03_actionsMenuButton() throws InterruptedException {
		
		driver = openBrowserAndApplication(prop.getProperty("browser"));
		
	    loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    loginPage.clickOnSubmitButton();
	    System.out.println("User Logged in Successfully.");
		System.out.println("=== TEST CASE TC_DL_03 EXECUTING ===");
		
		docLibraryPage = new DocumentLibraryPage(driver);
	
		docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	
    	List<String> expectedOptions = Arrays.asList("Upload", "Access", "Update Hashtag(s)", "Delete");
    	List<String> actualOptions = docLibraryPage.getDocumentLibraryOptions();
    	
    	System.out.println("Actual Options Fetched:");
    	for (String option : actualOptions) {
    	    System.out.println(option);
    	}

    	Assert.assertEquals(actualOptions, expectedOptions);
        System.out.println("test_TC_DL_03 got passed!");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButtonTwo();
        
    	
	}
	
	
	@Test(priority=3)
    public void test_TC_DL_04_uploadDocumentScreen() throws InterruptedException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
	    loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    loginPage.clickOnSubmitButton();
	    System.out.println("User Logged in Successfully.");
		System.out.println("=== TEST CASE TC_DL_04 EXECUTING ===");
		
		docLibraryPage = new DocumentLibraryPage(driver);
	
		docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        System.out.println("test_TC_DL_04 got passed!");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButtonTwo();
        
      	
	}
	@Test(priority=4)
	public void test_TC_DL_06_fillsNothingInAnyOfTheField() throws InterruptedException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.clickOnUploadButton();
        // As i wanted to scroll to the top, so i defined the method in Utilities class
        Utilities.scrollToTop(driver);// You already have the driver in your test
        
        String validationMsg = docLibraryPage.getValidationMessageForDocumentNameField();
        Assert.assertEquals(validationMsg, "Please fill out this field.");
        // Only printed if the assertion passes
        System.out.println("✅ Document Name field showed the correct validation message.");
        
        
        System.out.println("test_TC_DL_06 got passed!");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButtonTwo();


	}

	@Test(priority=5)
	public void test_TC_DL_17_allMandatoryFields() throws InterruptedException, IOException {
	
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentUsingAutoIt();
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);
        
        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: clickOnProfileIcon() waits for the icon, providing time for redirect to complete
	    Utilities.scrollToTop(driver);

        System.out.println("test_TC_DL_17 got passed!");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();

	}

	@Test(priority=6)
	public void test_TC_DL_18_allMandatoryFieldsExceptDocumentName() throws IOException, InterruptedException {
		
		    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");
	    	
	    	docLibraryPage = new DocumentLibraryPage(driver);
	    	docLibraryPage.clickOnCommunicationTab();
	    	docLibraryPage.clickonDocumentLibrary();
	    	docLibraryPage.clickOnActionsButton();
	    	docLibraryPage.clickOnUploadOption();
	    	
	    	String actualURL = driver.getCurrentUrl();
	        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
	        Assert.assertEquals(actualURL,expectedURL);
	        
	        docLibraryPage.uploadDocumentUsingAutoIt();
	        // docLibraryPage.enterValueInDocumentNameField(prop.getProperty("documentName"));
	        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
	        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
	        docLibraryPage.resizeCroppingArea();
	        docLibraryPage.clickOnApplyButton();
	        
	        // Scroll down by 500 pixels
		    Utilities.scrollDownByFiveHundred(driver);
		    
		    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
		    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
		    // Scroll to Bottom first
		    Utilities.scrollToBottom(driver);
		    docLibraryPage.clickOnUploadButton();
		    // Thread.sleep(3000); — removed: HTML5 validation appears synchronously; no redirect occurs since document name is absent
		    Utilities.scrollToTop(driver);
		    String validationMsg = docLibraryPage.getValidationMessageForDocumentNameField();
	        Assert.assertEquals(validationMsg, "Please fill out this field.");
		    
	        // Only printed if the assertion passes
	        System.out.println("✅ Document Name field showed the correct validation message.");
		    
		    
	        System.out.println("test_TC_DL_18 got passed!");

	        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

	        docLibraryPage.clickOnProfileIcon();
	        docLibraryPage.clickOnLogoutOption();
	        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
	        docLibraryPage.clickOnLogoutButtonTwo();
		
		
	}
	
	
	
	@Test(priority=7)
	public void test_TC_DL_22_UploadingDocumentInPngFormat() throws IOException, InterruptedException {
	
		driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInPngFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, 
	    // confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
		
		
		
		
	}

	@Test(priority=8)
	public void test_TC_DL_22_1_UploadingDocumentInjpgFormat() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInJPGFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22_1 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
		
	}

	@Test(priority=9)
    public void test_TC_DL_22_2_UploadingDocumentInCsvFormat() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInCSVFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22_2 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
	}

	
	 
	@Test(priority=10)
    public void test_TC_DL_22_3_UploadingDocumentInXlsxFormat() throws InterruptedException, IOException {
    	
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInXLSXFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22_3 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
    	
    }

	@Test(priority=11)
	public void test_TC_DL_22_4_UploadingDocumentInMp4Format() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInMP4FormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22_4 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
		
	}

	@Test(priority=12)
	public void test_TC_DL_22_5_UploadingGifImageForThumbnail() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInMP4FormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachmentInGif"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22_5 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();

	}
	
	

	@Test(priority=13)
	public void test_TC_DL_22_6_UploadingJpgImageForThumbnail() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInMP4FormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachmentInJPG"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_22_6 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
	}

	@Test(priority=14)
	public void test_TC_DL_25_fillsAllMandatoryFieldsExceptDescription() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInMP4FormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachmentInJPG"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    // docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: HTML5 validation appears synchronously; no redirect occurs since description is absent
	    Utilities.scrollToTop(driver);

	    String validationMsg = docLibraryPage.getValidationMessageForDescriptionField();
        Assert.assertEquals(validationMsg, "Please fill out this field.");

        // Only printed if the assertion passes
        System.out.println("✅ Description field showed the correct validation message.");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_25 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButtonTwo();
		
	}

	@Test(priority=15)
	public void test_TC_DL_28_fillsAllTheFieldsExceptDocumentOption() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        // docLibraryPage.uploadDocumentInMP4FormatUsingAutoIt();
        // Thread.sleep(2000); — removed: no upload action here; enterValueInDocumentNameField() waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachmentInJPG"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: HTML5 validation appears synchronously; no redirect occurs since no file is attached
	    Utilities.scrollToTop(driver);

	    String validationMsg = docLibraryPage.getValidationMessageForDocumentAttachmentField();
        Assert.assertEquals(validationMsg, "Please select a file.");

        // Only printed if the assertion passes
        System.out.println("✅ Document Attachment field showed the correct validation message.");

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_28 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButtonTwo() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButtonTwo();

	}
	
	


	@Test(priority=16)
	public void test_TC_DL_30_fillsAllTheMandatoryAndDocumentOptions() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInJPGFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnDocumentOptionTwo();
	    // Thread.sleep(2000); — removed: document option radio buttons are static DOM elements, no async rendering
	    docLibraryPage.clickOnDocumentOptionThree();
	    // Thread.sleep(2000); — removed: same as above, static DOM element

	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_30 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
		
	}

	@Test(priority=17)
    public void test_TC_DL_32_fillsAllTheFieldsAndMakesDownloadable() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInJPGFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnDocumentOptionTwo();
	    // Thread.sleep(2000); — removed: document option radio buttons are static DOM elements, no async rendering
	    docLibraryPage.clickOnDocumentOptionThree();
	    // Thread.sleep(2000); — removed: same as above, static DOM element
	    docLibraryPage.clickOnDownloadableOption();
	    // Thread.sleep(2000); — removed: downloadable toggle is a static DOM element, no async rendering

	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_32 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
		
	}

	@Test(priority=18)
	public void test_TC_DL_34_fillsAllTheFieldsWithHashtag() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInJPGFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnDocumentOptionTwo();
	    // Thread.sleep(2000); — removed: document option radio buttons are static DOM elements, no async rendering
	    docLibraryPage.clickOnDocumentOptionThree();
	    // Thread.sleep(2000); — removed: same as above, static DOM element
	    docLibraryPage.clickOnDownloadableOption();
	    // Thread.sleep(2000); — removed: downloadable toggle is a static DOM element, no async rendering
	    docLibraryPage.enterInternalHashtag(prop.getProperty("InternalHashtagToEnter"));
	    // Thread.sleep(2000); — removed: selectInternalHashtag() now waits for the autocomplete dropdown via wait.until(elementToBeClickable)
	    docLibraryPage.selectInternalHashtag();
	    // Thread.sleep(2000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until

	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_34 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
	}

    @Test(priority=19)
	public void test_TC_DL_08_specialCharacterInNameAndFillingAllMandatoryFields() throws InterruptedException, IOException {
		
        driver = openBrowserAndApplication(prop.getProperty("browser"));
		
        loginPage = new LoginPage(driver);
		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
    	loginPage.clickOnSubmitButton();
    	System.out.println("User Logged in Successfully.");
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	docLibraryPage.clickOnActionsButton();
    	docLibraryPage.clickOnUploadOption();
    	
    	String actualURL = driver.getCurrentUrl();
        String expectedURL = prop.getProperty("docLibraryExpectedURLTwo"); 
        Assert.assertEquals(actualURL,expectedURL);
        
        docLibraryPage.uploadDocumentInJPGFormatUsingAutoIt();
        // Thread.sleep(2000); — removed: enterValueInDocumentNameField() now waits via wait.until(elementToBeClickable)
        // For everytime different name
        String baseName = prop.getProperty("documentName");
        String uniqueName = baseName + "_" + System.currentTimeMillis(); // or use formatted timestamp
        docLibraryPage.enterValueInDocumentNameField(uniqueName);

        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        // Thread.sleep(3000); — removed: resizeCroppingArea() already waits for the crop handle via wait.until(presenceOfElementLocated)
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();

        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);

	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    // Thread.sleep(3000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnDocumentOptionTwo();
	    // Thread.sleep(2000); — removed: document option radio buttons are static DOM elements, no async rendering
	    docLibraryPage.clickOnDocumentOptionThree();
	    // Thread.sleep(2000); — removed: same as above, static DOM element
	    docLibraryPage.clickOnDownloadableOption();
	    // Thread.sleep(2000); — removed: downloadable toggle is a static DOM element, no async rendering
	    docLibraryPage.enterInternalHashtag(prop.getProperty("InternalHashtagToEnter"));
	    // Thread.sleep(2000); — removed: selectInternalHashtag() now waits for the autocomplete dropdown via wait.until(elementToBeClickable)
	    docLibraryPage.selectInternalHashtag();
	    // Thread.sleep(2000); — removed: clickOnUploadButton() waits for the button to be clickable via wait.until

	    docLibraryPage.clickOnUploadButton();
	    // Thread.sleep(3000); — removed: waitForDocumentLibraryPageToLoad() waits for the Actions button, confirming redirect is complete
	    docLibraryPage.waitForDocumentLibraryPageToLoad();

	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL");
        Assert.assertEquals(actualURLTwo,expectedURLTwo);

        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_08 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();

	}

    @Test(priority=20)
    public void test_TC_DL_37_searchFunctionality() throws InterruptedException {
    	
    	driver = openBrowserAndApplication(prop.getProperty("browser"));
 		
        loginPage = new LoginPage(driver);
 		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
     	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
     	loginPage.clickOnSubmitButton();
     	System.out.println("User Logged in Successfully.");

     	// Thread.sleep(3000); — removed: clickOnCommunicationTab() already waits via wait.until(elementToBeClickable)

     	docLibraryPage = new DocumentLibraryPage(driver);
     	docLibraryPage.clickOnCommunicationTab();
     	docLibraryPage.clickonDocumentLibrary();

     	docLibraryPage.enterIntoSearchBox(prop.getProperty("validValueInSearchBox"));
     	String searchResultText = docLibraryPage.getSearchResultText();
        System.out.println("Search Result: " + searchResultText);
        Assert.assertEquals(searchResultText, "ewewew test");
        // Thread.sleep(3000); — removed: no async operation follows this assertion

		System.out.println("Test Case TC_DL_37 Passed. The searched content was shown.");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();

    }

    @Test(priority=21)
    public void test_TC_DL_38_clickDeleteWithoutSelectingAnyContent() throws InterruptedException {
    	
        driver = openBrowserAndApplication(prop.getProperty("browser"));
 		
        loginPage = new LoginPage(driver);
 		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
     	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
     	loginPage.clickOnSubmitButton();
     	System.out.println("User Logged in Successfully.");

     	// Thread.sleep(3000); — removed: clickOnCommunicationTab() already waits via wait.until(elementToBeClickable)

     	docLibraryPage = new DocumentLibraryPage(driver);
     	docLibraryPage.clickOnCommunicationTab();
     	docLibraryPage.clickonDocumentLibrary();
     	docLibraryPage.clickOnActionsButton();
     	docLibraryPage.clickOnDeleteOption();
     	// Thread.sleep(2000); — removed: getDialogBoxText() now waits via wait.until(visibilityOfElementLocated)
     	String dialogText = docLibraryPage.getDialogBoxText(); // Call the method
     	Assert.assertEquals(dialogText, "Please select at least one document creative!");
     	// Thread.sleep(2000); — removed: clickOnOkButton() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnOkButton();

     	// Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_38 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();
     	
     	
    	
    }

    @Test(priority=22)
    public void test_TC_DL_39_deleteTheContentAndSearchIt() throws InterruptedException {
    	
        driver = openBrowserAndApplication(prop.getProperty("browser"));
 		
        loginPage = new LoginPage(driver);
 		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
     	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
     	loginPage.clickOnSubmitButton();
     	System.out.println("User Logged in Successfully.");

     	// Thread.sleep(3000); — removed: clickOnCommunicationTab() already waits via wait.until(elementToBeClickable)

     	docLibraryPage = new DocumentLibraryPage(driver);
     	docLibraryPage.clickOnCommunicationTab();
     	docLibraryPage.clickonDocumentLibrary();
     	// Thread.sleep(1000); — removed: clickOnCheckBoxOption() already waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnCheckBoxOption();
     	String dynamicText = docLibraryPage.getDynamicText(); // call the method
     	System.out.println("Fetched Dynamic Text: " + dynamicText);
     	// Thread.sleep(3000); — removed: getDynamicText() now waits via wait.until(visibilityOfElementLocated); clickOnActionsButton() also waits internally

     	docLibraryPage.clickOnActionsButton();
     	// Thread.sleep(2000); — removed: clickOnDeleteOption() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnDeleteOption();
     	// Thread.sleep(2000); — removed: clickOnOkButton() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnOkButton();
     	// Thread.sleep(3000); — removed: enterIntoSearchBox() now waits via wait.until(elementToBeClickable)
        // Now we will search for the deleted content
     	docLibraryPage.enterIntoSearchBox(dynamicText);
     	docLibraryPage.noRecordsElementMethod();

     	// Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_39 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton();


    }

    @Test(priority=23,dependsOnMethods={"test_TC_DL_34_fillsAllTheFieldsWithHashtag"})
	public void test_TC_DL_40_updatingTheAccessOfTheContent() throws InterruptedException {
    	
        driver = openBrowserAndApplication(prop.getProperty("browser"));
 		
        loginPage = new LoginPage(driver);
 		loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
     	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
     	loginPage.clickOnSubmitButton();
     	System.out.println("User Logged in Successfully.");

     	// Thread.sleep(3000); — removed: clickOnCommunicationTab() already waits via wait.until(elementToBeClickable)

     	docLibraryPage = new DocumentLibraryPage(driver);
     	docLibraryPage.clickOnCommunicationTab();
     	docLibraryPage.clickonDocumentLibrary();
     	docLibraryPage.clickOnCheckBoxOption();
     	String dynamicText = docLibraryPage.getDynamicText(); // call the method
     	System.out.println("Fetched Dynamic Text: " + dynamicText);


     	docLibraryPage.clickOnActionsButton();
     	docLibraryPage.clickOnAccessOption();
     	// Thread.sleep(2000); — removed: clickOnTeamRadioButton() now waits via wait.until(presenceOfElementLocated)
     	docLibraryPage.clickOnTeamRadioButton();
     	// Thread.sleep(2000); — removed: clickOnPartnerCategoryButton() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnPartnerCategoryButton();
     	// Thread.sleep(2000); — removed: clickOnCategory() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnCategory();
     	// Thread.sleep(2000); — removed: clickOnPartnerCategoryButton() now waits via wait.until(elementToBeClickable)
        // Again click on the partner category button in order to close it
     	docLibraryPage.clickOnPartnerCategoryButton();
     	// Thread.sleep(2000); — removed: clickOnScheduleCheckbox() now waits via wait.until(elementToBeClickable)
     	// We will click on the Content Date box
     	//docLibraryPage.clickOnContentUpdate();
     	//Thread.sleep(2000);

     	// Click on the Schedule checkbox
     	docLibraryPage.clickOnScheduleCheckbox();
     	// Thread.sleep(2000); — removed: clickOnScheduleCheckbox() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnScheduleCheckbox();
     	// Thread.sleep(2000); — removed: clickOnScheduleTextbox() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnScheduleTextbox();
     	// Thread.sleep(2000); — removed: selectCurrentActiveTimeThree() already waits internally via wait.until

     	docLibraryPage.selectCurrentActiveTimeThree();

     	// We will click on the Date of today
     	// docLibraryPage.selectTodayInCalendar();

     	// If you want to select DATE OF YOUR CHOICE
     	// docLibraryPage.selectDateOfYourChoice(10, 8, 2025);

     	// Thread.sleep(2000); — removed: clickOnUpdateAccessButton() now waits via wait.until(elementToBeClickable)
     	docLibraryPage.clickOnUpdateAccessButton();

     	// Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable
        System.out.println("test_TC_DL_40 got passed!");
        // Thread.sleep(2000); — removed: clickOnProfileIcon() now waits internally via elementToBeClickable

        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        // Thread.sleep(3000); — removed: clickOnLogoutButton() already waits for the Yes button via wait.until
        docLibraryPage.clickOnLogoutButton(); 
     	
     	
    	
    }
   
}

