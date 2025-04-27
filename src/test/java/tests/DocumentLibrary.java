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
    	
    	docLibraryPage = new DocumentLibraryPage(driver);
    	docLibraryPage.clickOnCommunicationTab();
    	docLibraryPage.clickonDocumentLibrary();
    	String actualURL = driver.getCurrentUrl();
		String expectedURL = prop.getProperty("docLibraryExpectedURL");
		Assert.assertEquals(actualURL,expectedURL);
		
		System.out.println("Test Case TC_DL_01 is Passed!");
		
		Thread.sleep(3000);
		
		docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
        docLibraryPage.clickOnLogoutButton();
		
		
	}
	
	@Test(priority=2)
	public void test_TC_DL_03_actionsMenuButton() throws InterruptedException {
		
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
    	
    	List<String> expectedOptions = Arrays.asList("Upload", "Access", "Update Hashtag(s)", "Delete");
    	List<String> actualOptions = docLibraryPage.getDocumentLibraryOptions();
    	
    	System.out.println("Actual Options Fetched:");
    	for (String option : actualOptions) {
    	    System.out.println(option);
    	}

    	Assert.assertEquals(actualOptions, expectedOptions);
    	
        System.out.println("test_TC_DL_03 got passed!");
        
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
        docLibraryPage.clickOnLogoutButton();
    	
	}
	
	@Test(priority=3)
	public void test_TC_DL_04_uploadDocumentScreen() throws InterruptedException {
		
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
        
        System.out.println("test_TC_DL_04 got passed!");
        
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
        docLibraryPage.clickOnLogoutButton();
        
        
		
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
        
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
        docLibraryPage.clickOnLogoutButton();
		
		
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
        docLibraryPage.enterValueInDocumentNameField(prop.getProperty("documentName"));
        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        Thread.sleep(3000);
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();
        
        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    
	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    Thread.sleep(3000);
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    Thread.sleep(3000);
	    Utilities.scrollToTop(driver);
	    
        System.out.println("test_TC_DL_17 got passed!");
        
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
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
	        Thread.sleep(3000);
	        docLibraryPage.resizeCroppingArea();
	        docLibraryPage.clickOnApplyButton();
	        
	        // Scroll down by 500 pixels
		    Utilities.scrollDownByFiveHundred(driver);
		    
		    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
		    Thread.sleep(3000);
		    // Scroll to Bottom first
		    Utilities.scrollToBottom(driver);
		    docLibraryPage.clickOnUploadButton();
		    Thread.sleep(3000);
		    Utilities.scrollToTop(driver);
		    String validationMsg = docLibraryPage.getValidationMessageForDocumentNameField();
	        Assert.assertEquals(validationMsg, "Please fill out this field.");
		    
	        // Only printed if the assertion passes
	        System.out.println("✅ Document Name field showed the correct validation message.");
		    
		    
	        System.out.println("test_TC_DL_18 got passed!");
	        
	        Thread.sleep(2000);
	        
	        docLibraryPage.clickOnProfileIcon();
	        docLibraryPage.clickOnLogoutOption();
	        Thread.sleep(3000);
	        docLibraryPage.clickOnLogoutButton(); 
		
		
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
        Thread.sleep(2000);
        docLibraryPage.enterValueInDocumentNameField(prop.getProperty("documentName"));
        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        Thread.sleep(3000);
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();
        
        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    
	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    Thread.sleep(3000);
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    Thread.sleep(3000);
	    
	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL"); 
        Assert.assertEquals(actualURLTwo,expectedURLTwo);
        
        Thread.sleep(2000);
        System.out.println("test_TC_DL_22 got passed!");
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
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
        Thread.sleep(2000);
        docLibraryPage.enterValueInDocumentNameField(prop.getProperty("documentName"));
        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        Thread.sleep(3000);
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();
        
        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    
	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    Thread.sleep(3000);
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    Thread.sleep(3000);
	    
	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL"); 
        Assert.assertEquals(actualURLTwo,expectedURLTwo);
        
        Thread.sleep(2000);
        System.out.println("test_TC_DL_22_1 got passed!");
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
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
        Thread.sleep(2000);
        docLibraryPage.enterValueInDocumentNameField(prop.getProperty("documentName"));
        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        Thread.sleep(3000);
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();
        
        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    
	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    Thread.sleep(3000);
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    Thread.sleep(3000);
	    
	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL"); 
        Assert.assertEquals(actualURLTwo,expectedURLTwo);
        
        Thread.sleep(2000);
        System.out.println("test_TC_DL_22_2 got passed!");
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
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
        Thread.sleep(2000);
        docLibraryPage.enterValueInDocumentNameField(prop.getProperty("documentName"));
        docLibraryPage.attachThumbnail(prop.getProperty("thumbnailAttachment"));
        Thread.sleep(3000);
        docLibraryPage.resizeCroppingArea();
        docLibraryPage.clickOnApplyButton();
        
        // Scroll down by 500 pixels
	    Utilities.scrollDownByFiveHundred(driver);
	    
	    docLibraryPage.enterValueInDescriptionField(prop.getProperty("descriptionText"));
	    Thread.sleep(3000);
	    // Scroll to Bottom first
	    Utilities.scrollToBottom(driver);
	    docLibraryPage.clickOnUploadButton();
	    Thread.sleep(3000);
	    
	    String actualURLTwo = driver.getCurrentUrl();
	    String expectedURLTwo = prop.getProperty("docLibraryExpectedURL"); 
        Assert.assertEquals(actualURLTwo,expectedURLTwo);
        
        Thread.sleep(2000);
        System.out.println("test_TC_DL_22_3 got passed!");
        Thread.sleep(2000);
        
        docLibraryPage.clickOnProfileIcon();
        docLibraryPage.clickOnLogoutOption();
        Thread.sleep(3000);
        docLibraryPage.clickOnLogoutButton();
    	
    }

	


}
