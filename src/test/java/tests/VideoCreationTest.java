package tests;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.LoginPage;
import pageObjects.VideoCreationPage;

public class VideoCreationTest extends Base {
	
	VideoCreationPage videoCreationPage;
	
	 LoginPage loginPage;
		
		@AfterMethod
		 public void tearDown() {
		        driver.quit();  // Close browser after tests
		 }
		
		
		@Test(priority=1)
		public void test_TC_DL_01_videoCreationThroughAssetLibrary() {
			
			driver = openBrowserAndApplication(prop.getProperty("browser"));
			
		    loginPage = new LoginPage(driver);
		    loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
		    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
		    loginPage.clickOnSubmitButton();
		    System.out.println("User Logged in Successfully.");
		    
		    videoCreationPage = new VideoCreationPage(driver);
		    // Wait for Asset Library Page to Load
		    // We Removed wait of 3 seconds, so good one!
		    videoCreationPage.waitForAssetLibraryPageToLoad();
		    
		    videoCreationPage.clickOnAddNewAssetButton();
		    videoCreationPage.clickOnVideoPostButton();
		    videoCreationPage.waitForOverlayToDisappear();
		    videoCreationPage.waitForUploadAssetPage();
		   
		    // Upload Asset Page
		    videoCreationPage.scrollToUploadSection();
		    videoCreationPage.uploadMp4Video("C:\\Users\\admin\\Desktop\\ZOI PRDs\\Kuch_Nhi_Hai_99_25fps.mp4");
		    videoCreationPage.clickOnNextButton();
		    
		    // NOW WE ARE ON GLOBAL ASSET DETAILS PAGE
		    // Remember we have to wait and see that the page is loaded, so we need to have some signals
		    videoCreationPage.waitForGlobalAssetDetailsPageToLoad();
		    videoCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameTextForVideo"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,300)");
	        
	        videoCreationPage.clickOnCategoryField();
		    videoCreationPage.clickOnCategoryOption();
		    videoCreationPage.clickOnCategoriesStaticText();
		    videoCreationPage.clickOnHashtagField();
		    videoCreationPage.clickOnHashtag();
		    videoCreationPage.clickOnHashtagStaticText();
		    videoCreationPage.enterTextIntoDescriptionTextfield(prop.getProperty("DescriptionText"));
		    
		    // Scrolling down more
	        videoCreationPage.scrollToPageBottom();
		    videoCreationPage.clickOnSaveAndProceed();
		    
		    // PAGE 2
		    
		    // js.executeScript("window.scrollBy(0,200)");
		    videoCreationPage.uploadThumbnailImage();
		    js.executeScript("window.scrollBy(0,200)");
            js.executeScript("window.scrollBy(0,7000)");
            videoCreationPage.clickOnSaveAndProceedButton();
            
            // PAGE 3 : PUBLISH PAGE
            
            js.executeScript("window.scrollBy(0,200)");
	        videoCreationPage.clickonMobileAppButtonTypeTwo();
	        
	        // Scrolling the Page so we go down
	        js.executeScript("window.scrollBy(0,400)");      
	        videoCreationPage.selectPartnersDropdown();
	        videoCreationPage.selectPartnerOption();
	        
	        videoCreationPage.closePartnerOptionDialogBox();
	        
	        videoCreationPage.clickOnCobrandingToggle();
	        videoCreationPage.clickOnPushNotificationToggle();
	        videoCreationPage.clickOnEmailNotificationToggle();
	        
	       
	        videoCreationPage.clickOnPublishButton();
	        
	        // Asset Library Page
	        videoCreationPage.clickOnProfileIconAfterPublishing();
	        videoCreationPage.clickOnLogoutOption();
	        videoCreationPage.clickOnLogoutButton();
            
		    
		    
		   }}
