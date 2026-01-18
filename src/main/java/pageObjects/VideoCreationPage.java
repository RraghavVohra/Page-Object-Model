package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	 private WebElement cropAndSubmitButton;
	 private WebElement saveAndProceedButtonTwo;
	 
	 
	 
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
    }
    
    
    
    public void clickOnNextButton() {
    	
 	    nextButton = wait.until(ExpectedConditions.elementToBeClickable
 	    (By.xpath("//button[@type='button' and contains(@class, 'btn-info') and contains(text(), 'Next')]")));

 	    // Use Actions class to move to the element and click
 	    Actions actions = new Actions(driver);
 	    actions.moveToElement(nextButton).click().perform();
 	    System.out.println("Next Button was clicked");
 	    
        }
    
    public void waitForGlobalAssetDetailsPageToLoad() {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Global Fields']")));
    	
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
    
    public void uploadThumbnailImage(String imagePath) {

        thumbnailInput = driver.findElement(
            By.xpath("//button[normalize-space()='Upload Thumbnail']")
        );

        thumbnailInput.sendKeys(imagePath);

        System.out.println("Thumbnail image uploaded");
    }
    
    public void waitForCropSection() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.presenceOfElementLocated(
        By.cssSelector(".ReactCrop__crop-selection")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
        //    By.xpath("//div[contains(@class,'crop-box')]")));
    }
    
    public void clickOnCropAndSubmit() {

       cropAndSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(
       By.xpath("//button[normalize-space()='Crop & Submit']")));

        cropAndSubmitButton.click();

        System.out.println("Crop & Submit clicked");
    }
    
    public void clickOnSaveAndProceedButton() {
    	
    	saveAndProceedButtonTwo = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
	    // saveAndProceedButtonTwo.click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", saveAndProceedButtonTwo);
	
    }
    
    
    
    
    
    

}
