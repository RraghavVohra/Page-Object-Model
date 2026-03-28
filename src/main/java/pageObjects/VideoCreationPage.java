package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WaitUtils;

public class VideoCreationPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	 
	 public VideoCreationPage(WebDriver driver) {
		 
		 this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 }
	 
	 private WebElement addNewAssetButton;
	 private WebElement VideoPostButton;
	 private WebElement attachBtn;
	 private WebElement attachButtonUI;
	 // private String filePathForVideo = "C:\\Users\\admin\\Desktop\\ZOI PRDs\\Kuch_Nhi_Hai_99_25fps.mp4";
	 private WebElement nextButton;
	 private WebElement nameField;
	 private WebElement categoriesField;
	 private WebElement categoryOption;
	 private WebElement categoriesStaticText;
	 private WebElement hashtagField;
	 private WebElement hashtagOption;
	 private WebElement hashtagsStaticText;
	 private WebElement micrositeURL;
	 private WebElement customURL;
	 private WebElement customURLTextfield;
	 private WebElement descriptionField;
	 private WebElement saveAndProceedButton;
	 private WebElement thumbnailInput;
	 private WebElement saveAndProceedButtonTwo;
	 private WebElement mobileAppCheckbox;
	 private WebElement micrositeCheckbox;
	 private WebElement selectPartnersDropdown;
	 private WebElement selectPartnerOption;
	 private WebElement cobrandingToggle;
	 private WebElement pushNotificationToggle;
	 private WebElement emailNotificationToggle;
	 private WebElement publishButton;
	 // private WebElement overlay;
	 private WebElement profileIcon;
	 private WebElement logOutOption;
	 private WebElement logoutButton;
	 private WebElement profileIconTwo;
	 
	 
	 
     public void clickOnAddNewAssetButton() {
		 
		 addNewAssetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-new-asset-btn btn btn-info']")));	
		 addNewAssetButton.click();
	 }
     
    public void clickOnVideoPostButton() {
		 
    	 // This method is defined at the bottom
    	 // waitForOverlayToDisappear();
		 VideoPostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='card-body'])[1]")));
		 VideoPostButton.click();
	 }
    
    
    // Scroll to the VISIBLE button (OPTIONAL)
    public void scrollToUploadSection() {

        attachButtonUI = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'files-upload-wrapper')]//button")
        ));

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});",
                           attachButtonUI);
    }
    
    public void uploadMp4Video(String filePathForVideo) {

        attachBtn = driver.findElement(
            By.xpath("//div[contains(@class,'files-upload-wrapper')]//input[@type='file']")
        );

        attachBtn.sendKeys(filePathForVideo);
        // Wait for Next button to be clickable — signals video has been processed
        WaitUtils.waitForElementClickable(driver,
            By.xpath("//button[@type='button' and contains(@class,'btn-info') and contains(text(),'Next')]"));
    }
    
    
    
    public void clickOnNextButton() {
    	
 	    nextButton = wait.until(ExpectedConditions.elementToBeClickable
 	    (By.xpath("//button[@type='button' and contains(@class, 'btn-info') and contains(text(), 'Next')]")));

 	    // Use Actions class to move to the element and click
 	    Actions actions = new Actions(driver);
 	    actions.moveToElement(nextButton).click().perform();
 	    System.out.println("Next Button was clicked");
 	    
        }
    
    
    public void enterTextIntoNameTextfield(String nameText) {
    	
    	nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Name']")));
		nameField.sendKeys(nameText);
    }
    
    public void scrollToElement() {
    	
    	nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Name']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nameField);
    }
    
    public void clickOnCategoryField() {

    	// Old: presenceOfElementLocated only checks DOM presence, not interactability — changed to
    	// elementToBeClickable to ensure the field is fully ready before clicking to open the dropdown.
    	// categoriesField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[contains(@class, 'searchBox')])[1]")));
    	// Old: standard click was intermittently not opening the dropdown — switched to JS click for reliability.
    	// categoriesField.click();
    	categoriesField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@class, 'searchBox')])[1]")));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", categoriesField);
    	// Wait for the dropdown to confirm it actually opened before proceeding
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Raghav SDET']")));
    }

    public void clickOnCategoryOption() {

		categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Raghav SDET']")));
		// Old: standard click caused ElementClickInterceptedException — an overlay was intercepting the click.
		// Switched to JS click which bypasses overlay interception.
		// categoryOption.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", categoryOption);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Raghav SDET']")));
    }
    
    public void clickOnCategoriesStaticText() {
    	
    	categoriesStaticText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Categories']")));
    	// categoriesStaticText = driver.findElement(By.xpath("//label[normalize-space()='Categories']"));
		categoriesStaticText.click();
    }
    
    public void clickOnHashtagField() {

    	hashtagField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Hashtags']")));
    	// hashtagField = driver.findElement(By.xpath("//input[@placeholder='Select Hashtags']"));
    	// Old: standard click caused ElementClickInterceptedException — the category dropdown close animation
    	// was still playing and overlaying this field. Switched to JS click to bypass the interception.
    	// hashtagField.click();
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", hashtagField);
    }

    public void clickOnHashtag() {

    	hashtagOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Hello']")));
    	// hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
    	// Old: standard click risks ElementClickInterceptedException on dropdown list items (same as category option).
    	// Switched to JS click for consistency and reliability.
    	// hashtagOption.click();
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", hashtagOption);
    }
    
    public void centering() {
    	
    	hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", hashtagOption);
    }
    
    public void clickOnHashtagStaticText() {

    	// Old: driver.findElement with no wait — fragile if the label isn't ready yet.
    	// Changed to wait.until(elementToBeClickable) for reliability.
    	// hashtagsStaticText = driver.findElement(By.xpath("//label[normalize-space()='Hashtags']"));
    	hashtagsStaticText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Hashtags']")));
		hashtagsStaticText.click();
    }
    
    public void clickOnMicrositeURL() {
    	
    	micrositeURL = driver.findElement(By.xpath("//input[@value='M']"));
    	micrositeURL.click();
    }
    
    public void clickOnCustomURL() {
    	
    	customURL = driver.findElement(By.xpath("//input[@value='C']"));
    	customURL.click();
    }
    
    public void enterValueIntoCustomURL(String customURLText) {
    	
    	customURLTextfield = driver.findElement(By.xpath("//input[@placeholder='Enter Custom URL']"));
    	customURLTextfield.sendKeys(customURLText);
    }
    
    public void enterTextIntoDescriptionTextfield(String textForDescription) {
    	
    	descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@placeholder='Description']")));
    	descriptionField.sendKeys(textForDescription);
    }
    
    public void clickOnSaveAndProceed() {

    	saveAndProceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save & Proceed']")));
    	// Old: standard click caused ElementClickInterceptedException — something was overlaying the button.
    	// Switched to JS click to bypass the interception.
    	// wait.until(ExpectedConditions.elementToBeClickable(saveAndProceedButton)).click();
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", saveAndProceedButton);
		System.out.println("Proceed button was clicked");
		// Wait for page 2 — thumbnail input is the signal that page has loaded
		WaitUtils.waitForElementPresent(driver,
		    By.xpath("(//input[@type='file' and contains(@accept,'image')])[1]"));
    }
    
    public void scrollToPageBottom() {
    	
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
    }
    
    // PAGE 2
    
    public void uploadThumbnailImage() {

        thumbnailInput = driver.findElement(By.xpath("(//input[@type='file' and contains(@accept,'image')])[1]"));
        thumbnailInput.sendKeys("C:\\Users\\admin\\Downloads\\TestingImage.jpg");
        // Wait for Save & Proceed button — signals thumbnail has been processed
        WaitUtils.waitForElementClickable(driver,
            By.xpath("//button[normalize-space()='Save & Proceed']"));
    }
    
   
    
    public void clickOnSaveAndProceedButton() {

    	saveAndProceedButtonTwo = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", saveAndProceedButtonTwo);
	    // Wait for page 3 — mobile app checkbox is the signal that publish page has loaded
	    WaitUtils.waitForElementClickable(driver,
	        By.xpath("(//input[@name='brochure-platform'])[1]"));
    }
    
    public void clickonMobileAppButton() {
    	
    	mobileAppCheckbox = wait.until(ExpectedConditions.elementToBeClickable
        (By.xpath("//label[normalize-space()='Mobile App']/preceding-sibling::input")));       	
    	((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();",mobileAppCheckbox);
    	// mobileAppCheckbox.click();
    }
    
    public void clickonMobileAppButtonTypeTwo() {

        mobileAppCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='brochure-platform'])[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", mobileAppCheckbox);
        // Old: Actions.click() was not reliably toggling the checkbox — same issue as clickonMobileAppButton().
        // Switched to JS click which directly fires the click event on the input element.
        // new Actions(driver).moveToElement(mobileAppCheckbox).pause(Duration.ofMillis(700)).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mobileAppCheckbox);
    }

    public void clickOnMicrositeButton() {

    	// Old: driver.findElement with no wait — fragile if the checkbox isn't ready yet.
    	// Changed to wait.until(elementToBeClickable) + JS click for reliability.
    	// micrositeCheckbox = driver.findElement(By.xpath("//div[@class='form-check']//label[normalize-space()='Microsite']/preceding-sibling::input"));
    	// micrositeCheckbox.click();
    	micrositeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
    	("//div[@class='form-check']//label[normalize-space()='Microsite']/preceding-sibling::input")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", micrositeCheckbox);
    }
    
    public void selectPartnersDropdown() {
    	
 	   
	    // 1️⃣ Locate dropdown button (exists initially)
	    selectPartnersDropdown =
	        driver.findElement(By.xpath("//span[normalize-space()='Select Partners']/ancestor::button"));

	    // 2️⃣ Ensure it is clickable
	    wait.until(ExpectedConditions.elementToBeClickable(selectPartnersDropdown));

	    // 3️⃣ Open dropdown (React-safe)
	    selectPartnersDropdown.sendKeys(Keys.ENTER);

	    // 4️⃣ NOW locate the menu (after open)
	    WebElement dropdownMenuOptions =
	        wait.until(ExpectedConditions.visibilityOf(
	            driver.findElement(
	                By.xpath("//div[contains(@id,'react-select') and contains(@class,'option')]")
	            )
	        ));
	    
    }
    
    public void selectPartnerOption() {

        selectPartnerOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Raj2024']")));
        // Old: standard click caused ElementClickInterceptedException on this React dropdown option.
        // Switched to JS click to bypass the interception.
        // selectPartnerOption.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", selectPartnerOption);
        // Wait until selection is reflected in UI
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Raj2024']")));
    }
    
    public void closePartnerOptionDialogBox() {

        // Use Actions class to perform the close action
        Actions actions = new Actions(driver);
        actions.moveToElement(selectPartnersDropdown).click().perform();
        System.out.println("Dialog box closed using Actions!");
        // Wait for cobranding toggle — signals dialog is fully closed
        WaitUtils.waitForElementClickable(driver,
            By.xpath("(//input[@id='custom-switch'])[1]"));
    }
    
    public void clickOnCobrandingToggle() {

    	// Old: driver.findElement with no wait — fragile if toggles aren't ready yet.
    	// Changed to wait.until(elementToBeClickable) for reliability.
    	// cobrandingToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[1]"));
    	cobrandingToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='custom-switch'])[1]")));
	    cobrandingToggle.click();
    }

    public void clickOnPushNotificationToggle() {

    	// Old: driver.findElement with no wait — fragile.
    	// Changed to wait.until(elementToBeClickable) for reliability.
    	// pushNotificationToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[2]"));
    	pushNotificationToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='custom-switch'])[2]")));
	    pushNotificationToggle.click();
    }

    public void clickOnEmailNotificationToggle() {

    	// Old: driver.findElement with no wait — fragile.
    	// Changed to wait.until(elementToBeClickable) for reliability.
    	// emailNotificationToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[3]"));
    	emailNotificationToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='custom-switch'])[3]")));
	    emailNotificationToggle.click();
    }

    public void clickOnPublishButton() {

    	// Old: driver.findElement with no wait — fragile if button isn't ready.
    	// Changed to wait.until(elementToBeClickable) for reliability.
    	// publishButton = driver.findElement(By.xpath("//button[normalize-space()='Publish']"));
    	publishButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Publish']")));
	    publishButton.click();
	    // Wait for Asset Library page header — signals redirect happened
	    WaitUtils.waitForElementVisible(driver,
	        By.xpath("//h3[normalize-space()='Asset Library']"));
	    // Wait for Add New Asset button — signals the full page including content list is ready
	    WaitUtils.waitForElementClickable(driver,
	        By.xpath("//button[@class='add-new-asset-btn btn btn-info']"));
    }
    
    public void waitForPublishPageToLoad() {

	    // Old: driver.findElement throws NoSuchElementException if overlay is already gone.
	    // Changed to invisibilityOfElementLocated to safely handle overlay absent from DOM.
	    // overlay = driver.findElement(By.xpath("//div[contains(@class,'overlay-bg')]"));
	    // mobileAppCheckbox = driver.findElement(By.xpath("//label[normalize-space()='Mobile App']"));
	    // wait.until(ExpectedConditions.invisibilityOf(overlay));
	    // wait.until(ExpectedConditions.elementToBeClickable(mobileAppCheckbox));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'overlay-bg')]")));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Mobile App']")));
	}

    public void waitForGlobalAssetDetailsPageToLoad() {

   	    // Old: driver.findElement throws NoSuchElementException if overlay is already gone.
   	    // Changed to invisibilityOfElementLocated to safely handle overlay absent from DOM.
   	    // overlay = driver.findElement(By.xpath("//div[contains(@class,'overlay-bg')]"));
   	    // nameField = driver.findElement(By.xpath("//input[@placeholder='Name']"));
   	    // wait.until(ExpectedConditions.invisibilityOf(overlay));
   	    // wait.until(ExpectedConditions.elementToBeClickable(nameField));
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Global Fields']")));
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'overlay-bg')]")));
   	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Name']")));
   	}
    
    public void clickOnProfileIcon() {
        profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='user-profile show dropdown']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profileIcon);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileIcon);
    }
    
    public void clickOnLogoutOption() {
        logOutOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Log Out']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logOutOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logOutOption);
    }
    
    public void clickOnLogoutButton() {
        logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);
    }
    
    public void clickOnProfileIconAfterPublishing() {
    	
    	profileIconTwo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[local-name()='svg' and contains(@class, 'bi-person-circle')]")));
    	profileIconTwo.click();
    	
    }
    
    public void waitForAssetLibraryPageToLoad() {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Asset Library']")));
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-new-asset-btn btn btn-info']")));
    	
    }
    
    public void waitForUploadAssetPage() {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Asset Library']")));
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'files-upload-wrapper')]//button")));
    	
    }
    
   // This method is used as after clicking on the Brochure Tile an overlay used to obstruct our click
    public void waitForOverlayToDisappear() {
 	    // Old: driver.findElement throws NoSuchElementException if the overlay disappears too quickly
 	    // or never appears. invisibilityOfElementLocated handles both cases — returns true immediately
 	    // if the element is absent from the DOM.
 	    // overlay = driver.findElement(By.xpath("//div[contains(@class,'overlay-bg')]"));
 	    // wait.until(ExpectedConditions.invisibilityOf(overlay));
 	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'overlay-bg')]")));
 	}
    
    
    
    
    
    
    
    

}
