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
		public void test_TC_DL_01_videoCreationThroughAssetLibrary() throws Exception {
			
			driver = openBrowserAndApplication(prop.getProperty("browser"));
			
		    loginPage = new LoginPage(driver);
		    loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
		    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
		    loginPage.clickOnSubmitButton();
		    System.out.println("User Logged in Successfully.");
		    
		    videoCreationPage = new VideoCreationPage(driver);
		   
		    Thread.sleep(5000);
		    
		    videoCreationPage.clickOnAddNewAssetButton();
		    Thread.sleep(3000);
		    videoCreationPage.clickOnVideoPostButton();
		   
		    // Upload Asset Page
		    videoCreationPage.scrollToUploadSection();
		    videoCreationPage.uploadMp4Video("C:\\Users\\admin\\Desktop\\ZOI PRDs\\Kuch_Nhi_Hai_99_25fps.mp4");
	
		    Thread.sleep(2000);
		    videoCreationPage.clickOnNextButton();
		    
		    // NOW WE ARE ON GLOBAL ASSET DETAILS PAGE
		    // Remember we have to wait and see that the page is loaded, so we need to have some signals
		    videoCreationPage.waitForGlobalAssetDetailsPageToLoad();
		    videoCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameTextForVideo"));
		    videoCreationPage.scrollToElement();
		    
            Thread.sleep(2000); 
            JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,300)");
	        
	        videoCreationPage.clickOnCategoryField();
	        Thread.sleep(2000);
		    videoCreationPage.clickOnCategoryOption();
		    Thread.sleep(2000);
		    videoCreationPage.clickOnCategoriesStaticText();
		    
		    videoCreationPage.clickOnHashtagField();
		    Thread.sleep(2000);
		    videoCreationPage.clickOnHashtag();
		    Thread.sleep(2000);
		    videoCreationPage.clickOnHashtagStaticText();
		    
		    videoCreationPage.enterTextIntoDescriptionTextfield(prop.getProperty("DescriptionText"));
		    
		    // Scrolling down more
	        videoCreationPage.scrollToPageBottom();
	        Thread.sleep(3000);
		    videoCreationPage.clickOnSaveAndProceed();
		    Thread.sleep(3000);
		    
		    // PAGE 2
		    
		    // js.executeScript("window.scrollBy(0,200)");
		    videoCreationPage.uploadThumbnailImage("C:\\Users\\admin\\Downloads\\TestingImage.jpg");
		    Thread.sleep(2000);
		    js.executeScript("window.scrollBy(0,200)");
            videoCreationPage.waitForCropSection();
            Thread.sleep(2000);
            videoCreationPage.clickOnCropAndSubmit();
            
            js.executeScript("window.scrollBy(0,20000)");
            
            videoCreationPage.clickOnSaveAndProceedButton();
            Thread.sleep(5000);
            
		    
		    
		    
		    
		    
		    
		    
		    
		    
		
		

		}}
