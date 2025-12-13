package pageObjects;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PdfCreationPage {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 public PdfCreationPage(WebDriver driver) {
		 
		 this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 }
	 
	 private WebElement addNewAssetButton;
	 private WebElement brochurePostButton;
	 private WebElement attachBtn;
	 private String filePathForPdf = "C:\\Users\\admin\\Desktop\\ZOI PRDs\\Zoi Drive PRD 9906.pdf";
	 private WebElement nextButton;
	 private WebElement nameField;
	 private WebElement categoriesField;
	 private WebElement categoryOption;
	 private WebElement categoriesStaticText;
	 private WebElement hashtagField;
	 private WebElement hashtagOption;
	 private WebElement hashtagsStaticText;
	 private WebElement contentTypeDropdown;
	 private WebElement onePagerOption;
	 private WebElement saveAndProceedButton;
	 private WebElement fileInput;
	 private WebElement saveAndProceedButtonTwo;
	 private WebElement mobileAppCheckbox;
	 private WebElement micrositeCheckbox;
	 private WebElement selectPartnersDropdown;
	 private WebElement selectPartnerOption;
	 private WebElement pushNotificationToggle;
	 private WebElement emailNotificationToggle;
	 private WebElement cobrandingToggle;
	 private WebElement publishButton;
	 
	 
     public void clickOnAddNewAssetButton() {
		 
		 addNewAssetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-new-asset-btn btn btn-info']")));	
		 addNewAssetButton.click();
	 }
     
     public void clickOnborchurePostButton() {
		 
		 brochurePostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='card-body'])[2]")));
		 brochurePostButton.click();
	 }
     
     public void attachFile() throws Exception {
		 
	        attachBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Attach']")));

	        // Scroll to element
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", attachBtn);
	        Thread.sleep(2000);

	        // Click attach button
	        attachBtn.click();
	        Thread.sleep(2000);

	        // Use Robot to handle native file upload
	        Robot robot = new Robot();
	        StringSelection selection = new StringSelection(filePathForPdf);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

	        // CTRL+V
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);

	        // ENTER
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        System.out.println("PDF File uploaded successfully!");
	        Thread.sleep(3000);
	    }
     
        public void clickOnNextButton() {
	    	
 	    nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@class, 'btn-info') and contains(text(), 'Next')]")));

 	    // Use Actions class to move to the element and click
 	    Actions actions = new Actions(driver);
 	    actions.moveToElement(nextButton).click().perform();
 	    System.out.println("Next Button was clicked");
 	    
        }
        
        public void enterTextIntoNameTextfield(String nameText) {
	    	
	    	nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Name']")));
			nameField.sendKeys(nameText);
	    }
        
        public void clickOnCategoryField() {
	    	
	    	categoriesField = driver.findElement(By.xpath("(//input[contains(@class, 'searchBox')])[1]"));
			categoriesField.click();
	    }
	    
	    public void clickOnCategoryOption() {
	    	
	    	categoryOption = driver.findElement(By.xpath("//li[normalize-space()='Raghav SDET']"));
			categoryOption.click();
	    }
	    
	    public void clickOnCategoriesStaticText() {
	    	
	    	categoriesStaticText = driver.findElement(By.xpath("//label[normalize-space()='Categories']"));
			categoriesStaticText.click();
	    }
	    
        public void clickOnHashtagField() {
	    	
	    	hashtagField = driver.findElement(By.xpath("//input[@placeholder='Select Hashtags']"));	 
			hashtagField.click();
	    }
	    
	    public void clickOnHashtag() {
	    	
	    	hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
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
	    
	    public void clickOnContentTypeDropdown() {
	    	
	    	contentTypeDropdown = driver.findElement(By.xpath("//select[@class='greeting mb-3 input-form form-select']"));
	    	contentTypeDropdown.click();
	    
	    }
	    
	    public void clickOnOneOptionFromContentTypeDropdown() {
	    	
	    	onePagerOption = driver.findElement(By.xpath("//select[@aria-label='select-type']//option[normalize-space()='Onepager']"));
	    	onePagerOption.click();
	    	
	    }
	    
        public void clickOnSaveAndProceed() {
	    	
	    	saveAndProceedButton = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
			saveAndProceedButton.click();
			System.out.println("Proceed button was clicked");
	    }
        
        public void uploadImage() throws InterruptedException {
	        // Locate the hidden file input
	        fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//button[normalize-space()='Upload Thumbnail']/following-sibling::input[@type='file'][1]")));
	       
	        // (//input[@type='file' and @accept='image/*'])[1]

	        // Provide absolute file path directly
	        fileInput.sendKeys("C:\\Users\\admin\\Downloads\\Untitled design.jpg");

	        System.out.println("Image uploaded successfully!");

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
        	
        	mobileAppCheckbox = driver.findElement(By.xpath("//div[@class='form-check']//label[normalize-space()='Mobile App']/preceding-sibling::input"));
        	mobileAppCheckbox.click();
        }
        
        public void clickOnMicrositeButton() {
        	
        	micrositeCheckbox = driver.findElement(By.xpath("//div[@class='form-check']//label[normalize-space()='Microsite']/preceding-sibling::input"));
        	micrositeCheckbox.click();
        }
        
       public void selectPartnersDropdown() {
        	
        	selectPartnersDropdown = driver.findElement(By.xpath("//span[@class='css-1v99tuv']"));
		    selectPartnersDropdown.click();
        }
        
        public void selectPartnerOption() {
        	
        	selectPartnerOption = driver.findElement(By.xpath("//div[contains(text(), 'Raj2024')]"));
		    selectPartnerOption.click();
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
        
        
        
        
        
        
       
       
	    
	    
        
        
        
        
	    
        
        
     
        
     
     
	 
	 
	 
	 
	 
	 
	 

}
