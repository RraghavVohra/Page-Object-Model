package tests;

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
	    imageCreationPage.clickOnAddNewAssetButton();
	    imageCreationPage.clickOnSocialPostButton();
	    Thread.sleep(7000);
	    
	    imageCreationPage.attachFile();
	    Thread.sleep(3000);	
	    imageCreationPage.clickOnNextButton();
	    Thread.sleep(9000);	
	    
	    imageCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameText"));
        imageCreationPage.scrollToElement();
        Thread.sleep(2000);
        imageCreationPage.clickOnCategoryField();
        imageCreationPage.clickOnCategoryOption();
        imageCreationPage.clickOnCategoriesStaticText();
        Thread.sleep(2000);
        
        imageCreationPage.clickOnHashtagField();
        imageCreationPage.clickOnHashtag();
        Thread.sleep(2000);
        imageCreationPage.centering();
        imageCreationPage.clickOnHashtagStaticText();
        Thread.sleep(2000);
        // Scrolling down more
        imageCreationPage.scrollToPageBottom();
        Thread.sleep(2000);
        
        
        
	   
	    
	    	
		
		
	}

}
