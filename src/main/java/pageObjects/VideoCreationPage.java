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
	 private WebElement overlay;
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
    
    public void uploadMp4Video(String filePathForVideo) throws InterruptedException {

        attachBtn = driver.findElement(
            By.xpath("//div[contains(@class,'files-upload-wrapper')]//input[@type='file']")
        );

        attachBtn.sendKeys(filePathForVideo);
        
        Thread.sleep(3000);
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
    	
    	categoriesField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[contains(@class, 'searchBox')])[1]")));
		categoriesField.click();
    }
    
    public void clickOnCategoryOption() {
    	
		categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Raghav SDET']")));
		// 2️⃣ Click the option
	    categoryOption.click();
	    // 3️⃣ FINAL: wait until selection is reflected in UI
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
		hashtagField.click();
    }
    
    public void clickOnHashtag() {
    	
    	hashtagOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Hello']")));
    	// hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
		hashtagOption.click();
    }
    
    public void centering() {
    	
    	hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", hashtagOption);
    }
    
    public void clickOnHashtagStaticText() {
    	
    	 hashtagsStaticText = driver.findElement(By.xpath("//label[normalize-space()='Hashtags']"));
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
    	
    	saveAndProceedButton = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
    	wait.until(ExpectedConditions.elementToBeClickable(saveAndProceedButton)).click();
		System.out.println("Proceed button was clicked");
    }
    
    public void scrollToPageBottom() {
    	
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
    }
    
    // PAGE 2
    
    public void uploadThumbnailImage() throws InterruptedException {

        thumbnailInput = driver.findElement(By.xpath("(//input[@type='file' and contains(@accept,'image')])[1]"));
        thumbnailInput.sendKeys("C:\\Users\\admin\\Downloads\\TestingImage.jpg");
        // Wait (if required for upload processing)
        Thread.sleep(3000);
    }
    
   
    
    public void clickOnSaveAndProceedButton() {
    	
    	saveAndProceedButtonTwo = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
	    // saveAndProceedButtonTwo.click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", saveAndProceedButtonTwo);
	
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

        ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", mobileAppCheckbox);

         new Actions(driver)
     	            .moveToElement(mobileAppCheckbox)
     	            .pause(Duration.ofMillis(700))
     	            .click()
     	            .perform();
     	}
    
    public void clickOnMicrositeButton() {
    	
    	micrositeCheckbox = driver.findElement(By.xpath
    	("//div[@class='form-check']//label[normalize-space()='Microsite']/preceding-sibling::input"));
    	micrositeCheckbox.click();
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
    	
    	// 1️⃣ Click the option
        selectPartnerOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Raj2024']")));
        selectPartnerOption.click();

        // 2️⃣ FINAL: wait until selection is reflected in UI
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Raj2024']")));
    }
    
    public void closePartnerOptionDialogBox() {
   
        // Use Actions class to perform the close action
        Actions actions = new Actions(driver);
        actions.moveToElement(selectPartnersDropdown).click().perform();
        System.out.println("Dialog box closed using Actions!");
    }
    
    public void clickOnCobrandingToggle() {
    	
    	cobrandingToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[1]"));
	    cobrandingToggle.click();
    }
    
    public void clickOnPushNotificationToggle() {
    	
    	pushNotificationToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[2]"));
	    pushNotificationToggle.click();
    }
    
    public void clickOnEmailNotificationToggle() {
    	
    	emailNotificationToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[3]"));
	    emailNotificationToggle.click();
    }
    
    public void clickOnPublishButton() {
    	
    	publishButton = driver.findElement(By.xpath("//button[normalize-space()='Publish']"));
	    publishButton.click();
    }
    
    public void waitForPublishPageToLoad() {

	    overlay = driver.findElement(By.xpath("//div[contains(@class,'overlay-bg')]"));
	    mobileAppCheckbox = driver.findElement(By.xpath("//label[normalize-space()='Mobile App']"));

	    wait.until(ExpectedConditions.invisibilityOf(overlay));
	    wait.until(ExpectedConditions.elementToBeClickable(mobileAppCheckbox));
	}
    
    public void waitForGlobalAssetDetailsPageToLoad() {

   	    overlay = driver.findElement(By.xpath("//div[contains(@class,'overlay-bg')]"));
   	    nameField = driver.findElement(By.xpath("//input[@placeholder='Name']"));
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Global Fields']")));

   	    wait.until(ExpectedConditions.invisibilityOf(overlay));
   	    wait.until(ExpectedConditions.elementToBeClickable(nameField));
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
 	    overlay = driver.findElement(By.xpath("//div[contains(@class,'overlay-bg')]"));
 	    wait.until(ExpectedConditions.invisibilityOf(overlay));
 	}
    
    
    
    
    
    
    
    

}
