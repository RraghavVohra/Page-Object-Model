package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.LoginPage;
import pageObjects.PdfCreationPage;

public class PdfCreationTest extends Base {
	
	PdfCreationPage pdfCreationPage;
	
	 LoginPage loginPage;
		
		@AfterMethod
		 public void tearDown() {
		        driver.quit();  // Close browser after tests
		 }
		
		
		@Test(priority=1)
		public void test_TC_DL_01_pdfCreationThroughAssetLibrary() throws Exception {
			
			driver = openBrowserAndApplication(prop.getProperty("browser"));
			
		    loginPage = new LoginPage(driver);
		    loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
		    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
		    loginPage.clickOnSubmitButton();
		    System.out.println("User Logged in Successfully.");
	
		    pdfCreationPage = new PdfCreationPage(driver);
		    Thread.sleep(3000);
		    
		    pdfCreationPage.clickOnAddNewAssetButton();
		    pdfCreationPage.clickOnborchurePostButton();
		    pdfCreationPage.waitForPDFPostPageToLoad();
		    
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollBy(0,300)");
		    Thread.sleep(2000);
		    
		    pdfCreationPage.attachFile();
		    Thread.sleep(3000);
		    js.executeScript("window.scrollBy(0,200)");
		    Thread.sleep(2000);
		    pdfCreationPage.clickOnNextButton();
		    Thread.sleep(9000);	
		    
		    pdfCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameText"));
		    pdfCreationPage.scrollToElement();
	        Thread.sleep(2000);
	        
	        js.executeScript("window.scrollBy(0,300)");
	        
		    pdfCreationPage.clickOnCategoryField();
		    Thread.sleep(2000);
		    pdfCreationPage.clickOnCategoryOption();
		    Thread.sleep(2000);
		    pdfCreationPage.clickOnCategoriesStaticText();
		    
		    pdfCreationPage.clickOnHashtagField();
		    pdfCreationPage.clickOnHashtag();
		    pdfCreationPage.clickOnHashtagStaticText();
		    
		    pdfCreationPage.enterTextIntoDescriptionTextfield(prop.getProperty("DescriptionText"));
		    
		    // Scrolling down more
	        pdfCreationPage.scrollToPageBottom();
		    pdfCreationPage.clickOnSaveAndProceed();
		    Thread.sleep(3000);
		    
		    // Page 2
		    pdfCreationPage.uploadImage();
		    js.executeScript("window.scrollBy(0,20000)");
	        
	        pdfCreationPage.clickOnSaveAndProceedButton();
	        Thread.sleep(8000);
	        // NEED TO FIND SOLUTION AS PAGE LOADING TIME IS QUITE LONG
	        
	        // Publish Page - Page 3
	        
	        pdfCreationPage.clickonMobileAppButton();
	        // Scrolling the Page so we go down
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);        
	        pdfCreationPage.selectPartnersDropdown();
	        Thread.sleep(5000);
	        pdfCreationPage.selectPartnerOption();
	        Thread.sleep(5000);
	        
	        pdfCreationPage.closePartnerOptionDialogBox();
	        Thread.sleep(2000);
	        
	        pdfCreationPage.clickOnCobrandingToggle();
	        pdfCreationPage.clickOnPushNotificationToggle();
	        pdfCreationPage.clickOnEmailNotificationToggle();
	        
	        Thread.sleep(2000);
	        pdfCreationPage.clickOnPublishButton();
		    
	        Thread.sleep(5000);
		    

}}
