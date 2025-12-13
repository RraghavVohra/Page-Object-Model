package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.ImageCreationPage;
import pageObjects.LoginPage;

public class ImageCreationTest extends Base {
	
	ImageCreationPage imageCreationPage;
	
    LoginPage loginPage;
	
	@AfterMethod
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }
	
	
	@Test(priority=1)
	public void test_TC_DL_01_imageCreationThroughAssetLibrary() throws Exception {
		
		driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	    loginPage = new LoginPage(driver);
	    loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    loginPage.clickOnSubmitButton();
	    System.out.println("User Logged in Successfully.");
	    
	    imageCreationPage = new ImageCreationPage(driver);
	    Thread.sleep(3000);
	    imageCreationPage.clickOnAddNewAssetButton();
	    imageCreationPage.clickOnSocialPostButton();
	    // Wait for Social Post Page to Appear
	    imageCreationPage.waitForSocialPostPageToLoad();
	    // Thread.sleep(7000);
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,300)");
	    Thread.sleep(2000);	
	    
	    imageCreationPage.attachFile();
	    Thread.sleep(3000);
	    js.executeScript("window.scrollBy(0,200)");
	    Thread.sleep(2000);
	    imageCreationPage.clickOnNextButton();
	    Thread.sleep(9000);	
	    
	    imageCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameText"));
        imageCreationPage.scrollToElement();
        Thread.sleep(2000);
        //imageCreationPage.clickOnCategoryField();
        //imageCreationPage.clickOnCategoryOption();
        //imageCreationPage.clickOnCategoriesStaticText();
        //Thread.sleep(2000);
        
        imageCreationPage.clickOnHashtagField();
        imageCreationPage.clickOnHashtag();
        Thread.sleep(2000);
        imageCreationPage.centering();
        imageCreationPage.clickOnHashtagStaticText();
        Thread.sleep(2000);
        // Scrolling down more
        imageCreationPage.scrollToPageBottom();
        Thread.sleep(2000);
        
        imageCreationPage.clickOnLongTextField();
        Thread.sleep(3000);
        
        imageCreationPage.clickOnSaveAndProceed();
        Thread.sleep(2000);
        
        // Now we come to the Page number 2
        
        
        imageCreationPage.uploadImage();
        Thread.sleep(5000);
        
        js.executeScript("window.scrollBy(0,20000)");
        
        imageCreationPage.clickOnSaveAndProceedButton();
        Thread.sleep(5000);
        
        // Now we are on the Publish Page
        imageCreationPage.clickOnWhatsappCheckbox();
        Thread.sleep(8000);
        
        // Scrolling the Page so we go down
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);        
        imageCreationPage.selectPartnersDropdown();
        Thread.sleep(5000);
        imageCreationPage.selectPartnerOption();
        Thread.sleep(5000);
        
        imageCreationPage.closePartnerOptionDialogBox();
        Thread.sleep(2000);
        
        imageCreationPage.clickOnCobrandingToggle();
        imageCreationPage.clickOnPushNotificationToggle();
        imageCreationPage.clickOnEmailNotificationToggle();
        
        Thread.sleep(2000);
        imageCreationPage.clickOnPublishButton();
        
        Thread.sleep(5000);
        
        
        
        
        
        
        
	   
	    
	    	
		
		
	}

}
