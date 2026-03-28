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
		    loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
		    loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
		    loginPage.clickOnSubmitButton();
		    System.out.println("User Logged in Successfully.");
	
		    pdfCreationPage = new PdfCreationPage(driver);
		    // Wait for Asset Library Page to Load
		    // We Removed wait of 3 seconds, so good one!
		    pdfCreationPage.waitForAssetLibraryPageToLoad();
		   
		    
		    pdfCreationPage.clickOnAddNewAssetButton();
		    pdfCreationPage.clickOnbrochurePostButtonProd();
		    pdfCreationPage.waitForPDFPostPageToLoad();
		    
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollBy(0,300)");
		    // Thread.sleep(2000); — removed: attachFile() handles its own scroll internally via scrollIntoView(true)

		    pdfCreationPage.attachFile();
		    // Thread.sleep(3000); — removed: attachFile() now waits for the Next button to be clickable before returning
		    js.executeScript("window.scrollBy(0,200)");
		    // Thread.sleep(2000); — removed: clickOnNextButton() uses wait.until(elementToBeClickable) internally
		    pdfCreationPage.clickOnNextButton();
		    	
		    
		    // Here we will call a method for waiting for the page to load
		    // And we removed 9 seconds of wait time
		    pdfCreationPage.waitForGlobalAssetDetailsPageToLoad();
		    
		    pdfCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameText"));
		    pdfCreationPage.scrollToElement();
	        // Thread.sleep(2000); — removed: scrollToElement() uses scrollIntoView(true) which is instant; clickOnCategoryField() waits internally

	        js.executeScript("window.scrollBy(0,300)");
	        
		    pdfCreationPage.clickOnCategoryField();
		    pdfCreationPage.clickOnCategoryOptionProd();
		    pdfCreationPage.clickOnCategoriesStaticText();
		    
		    pdfCreationPage.clickOnHashtagField();
		    pdfCreationPage.clickOnHashtagProd();
		    pdfCreationPage.clickOnHashtagStaticText();
		    
		    pdfCreationPage.enterTextIntoDescriptionTextfield(prop.getProperty("DescriptionText"));
		    
		    // Scrolling down more
	        pdfCreationPage.scrollToPageBottom();
	        // Thread.sleep(3000); — removed: clickOnSaveAndProceed() uses wait.until(elementToBeClickable) internally
		    pdfCreationPage.clickOnSaveAndProceed();
		    // Thread.sleep(3000); — removed: uploadImage() waits for the file input via wait.until(presenceOfElementLocated) before proceeding
		    
		    // Page 2
		    pdfCreationPage.uploadImage();
		    js.executeScript("window.scrollBy(0,20000)");
	        
	        pdfCreationPage.clickOnSaveAndProceedButton();
	        // Here we will call a method for waiting for the page to load
		    // And we removed 9 seconds of wait time
	        pdfCreationPage.waitForPublishPageToLoad();
	        
	        
	        // Publish Page - Page 3
	        js.executeScript("window.scrollBy(0,200)");
	        // Thread.sleep(3000); — removed: clickonMobileAppButton() uses wait.until(elementToBeClickable) internally
	        pdfCreationPage.clickonMobileAppButton();
	        // Thread.sleep(3000); — removed: selectPartnersDropdown() uses wait.until(elementToBeClickable) on the dropdown
	        // Scrolling the Page so we go down
	        js.executeScript("window.scrollBy(0,400)");      
	        pdfCreationPage.selectPartnersDropdown();
	        pdfCreationPage.selectPartnerOption();
	        
	        pdfCreationPage.closePartnerOptionDialogBox();
	        // Thread.sleep(2000); — removed: closePartnerOptionDialogBox() now waits for dropdown to become invisible before returning
	        
	        pdfCreationPage.clickOnCobrandingToggle();
	        pdfCreationPage.clickOnPushNotificationToggle();
	        pdfCreationPage.clickOnEmailNotificationToggle();
	        

	        // Thread.sleep(2000); — removed: clickOnPublishButton() now uses wait.until(elementToBeClickable) internally
	        pdfCreationPage.clickOnPublishButton();

	        // Thread.sleep(5000); — removed: clickOnProfileIconAfterPublishing() uses wait.until(elementToBeClickable) on the SVG icon, covering the publish redirect time
	        
	        // Asset Library Page
	        pdfCreationPage.clickOnProfileIconAfterPublishing();
	        pdfCreationPage.clickOnLogoutOption();
	        pdfCreationPage.clickOnLogoutButton();
	        
		    

}}
