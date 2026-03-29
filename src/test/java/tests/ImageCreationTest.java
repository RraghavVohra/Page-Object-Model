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
		    // Clear cookies and storage before quitting — prevents stale session tokens
		    // from triggering an auto-login on the next run, reducing login attempts counted by the server
		    driver.manage().deleteAllCookies();
		    ((JavascriptExecutor) driver).executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
	        driver.quit();  // Close browser after tests
	 }


	@Test(priority=1)
	public void test_TC_DL_01_imageCreationThroughAssetLibrary() throws Exception {

		driver = openBrowserAndApplication(prop.getProperty("browser"));

	    loginPage = new LoginPage(driver);
	    loginPage.enterUsernameField(prop.getProperty("validusernameprod"));
	    loginPage.enterPasswordField(prop.getProperty("validpasswordprod"));
	    loginPage.clickOnSubmitButton();
	    System.out.println("User Logged in Successfully.");

	    imageCreationPage = new ImageCreationPage(driver);
	    // Thread.sleep(3000); // removed — clickOnAddNewAssetButton waits for button to be clickable
	    imageCreationPage.clickOnAddNewAssetButton();
	    imageCreationPage.clickOnSocialPostButtonProd();
	    // Wait for Social Post Page to Appear
	    imageCreationPage.waitForSocialPostPageToLoad();
	    // Thread.sleep(7000);

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,300)");
	    // Thread.sleep(2000); // removed — attachFile waits for Attach button to be clickable

	    imageCreationPage.attachFile();
	    // Thread.sleep(3000); // removed — attachFile handles its own Robot/OS dialog waits internally
	    js.executeScript("window.scrollBy(0,200)");
	    // Thread.sleep(2000); // removed — clickOnNextButton waits for Next button to be clickable
	    imageCreationPage.clickOnNextButton();
	    // Thread.sleep(9000); // removed — clickOnNextButton waits for Asset Details page to load

	    imageCreationPage.enterTextIntoNameTextfield(prop.getProperty("NameText"));
        imageCreationPage.scrollToElement();
        // Thread.sleep(2000); // removed — clickOnHashtagField waits for element to be clickable
        imageCreationPage.clickOnCategoryField();
        imageCreationPage.clickOnCategoryOptionProd();
        imageCreationPage.clickOnCategoriesStaticText();
        //Thread.sleep(2000);

        imageCreationPage.clickOnHashtagField();
        imageCreationPage.clickOnHashtagProd();
        // Thread.sleep(2000); // removed — centering uses existing element reference, no wait needed
        imageCreationPage.centeringProd();
        imageCreationPage.clickOnHashtagStaticText();
        // Thread.sleep(2000); // removed — scrollToPageBottom is a JS scroll with no timing dependency
        // Scrolling down more
        imageCreationPage.scrollToPageBottom();
        // Thread.sleep(2000); // removed — clickOnLongTextField waits for element to be clickable

        imageCreationPage.clickOnLongTextField();
        // Thread.sleep(3000); // removed — clickOnSaveAndProceed waits for button to be clickable

        imageCreationPage.clickOnSaveAndProceed();
        // Thread.sleep(2000); // removed — clickOnSaveAndProceed waits for upload page to load

        // Now we come to the Page number 2


        imageCreationPage.uploadImage();
        // Thread.sleep(5000); // removed — uploadImage waits for Save & Proceed button to be clickable

        js.executeScript("window.scrollBy(0,20000)");

        imageCreationPage.clickOnSaveAndProceedButton();
        // Thread.sleep(5000); // removed — clickOnSaveAndProceedButton waits for Publish Setup page to load

        // Now we are on the Publish Page
        imageCreationPage.clickOnWhatsappCheckbox();
        // Thread.sleep(8000); // removed — selectPartnersDropdown waits for element to be clickable

        // Scrolling the Page so we go down
        js.executeScript("window.scrollBy(0,400)");
        // Thread.sleep(2000); // removed — selectPartnersDropdown waits for element to be clickable
        imageCreationPage.selectPartnersDropdown();
        // Thread.sleep(5000); // removed — selectPartnerOption waits for option to be clickable
        imageCreationPage.selectPartnerOption();
        // Thread.sleep(5000); // removed — closePartnerOptionDialogBox waits for dialog to close

        imageCreationPage.closePartnerOptionDialogBox();
        // Thread.sleep(2000); // removed — toggles wait for elements to be clickable

        imageCreationPage.clickOnCobrandingToggle();
        imageCreationPage.clickOnPushNotificationToggle();
        imageCreationPage.clickOnEmailNotificationToggle();

        // Thread.sleep(2000); // removed — clickOnPublishButton waits for button to be clickable
        imageCreationPage.clickOnPublishButton();

        // Thread.sleep(5000); // removed — clickOnPublishButton waits for Asset Library page to load

        // Asset Library Page
        imageCreationPage.clickOnProfileIconAfterPublishing();
        imageCreationPage.clickOnLogoutOption();
        imageCreationPage.clickOnLogoutButton();

	}

}
