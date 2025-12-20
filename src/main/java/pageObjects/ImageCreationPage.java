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


public class ImageCreationPage  {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	
	 public ImageCreationPage(WebDriver driver) {
			
			this.driver=driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
	 
	 
	 private WebElement addNewAssetButton;
	 private WebElement socialPostButton;
	 private WebElement attachBtn;
	 private String filePath = "C:\\Users\\admin\\Downloads\\PushNotificationImage.png";
	 private WebElement nextButton;
	 private WebElement nameField;
	 private WebElement categoriesField;
	 private WebElement categoryOption;
	 private WebElement categoriesStaticText;
	 private WebElement saveAndProceedButton;
	 private WebElement fileInput;
	 private WebElement saveAndProceedButtonTwo;
	 private WebElement publishSetupStaticText;
	 private WebElement whatsAppCheckbox;
	 private WebElement socialCheckbox;
	 private WebElement selectPartnersDropdown;
	 private WebElement selectPartnerOption;
	 private WebElement cobrandingToggle;
	 private WebElement pushNotificationToggle;
	 private WebElement emailNotificationToggle;
	 private WebElement publishButton;
	 private WebElement profileLogoButton;
	 private WebElement hashtagField;
	 private WebElement hashtagOption;
	 private WebElement hashtagsStaticText;
	 private WebElement longTextField;
	 private WebElement profileIcon;
	 private WebElement logOutOption;
	 private WebElement logoutButton;
	 private WebElement profileIconTwo;
	 
	 
	 
	 
	 
	 public void clickOnAddNewAssetButton() {
		 
		 addNewAssetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-new-asset-btn btn btn-info']")));	
		 addNewAssetButton.click();
	 }
	 
	 public void clickOnSocialPostButton() {
		 
		 socialPostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='card-body'])[3]")));
		 socialPostButton.click();
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
	        StringSelection selection = new StringSelection(filePath);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

	        // CTRL+V
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);

	        // ENTER
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        System.out.println("File uploaded successfully!");
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
	    
	    public void scrollToElement() {
	    	
	    	nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Name']")));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nameField);
	    }
	    
	    public void clickOnCategoryField() {
	    	
	    	categoriesField = driver.findElement(By.xpath("(//input[contains(@class, 'searchBox')])[1]"));
			categoriesField.click();
	    }
	    
	    public void clickOnCategoryOption() {
	    	
	    	categoryOption = driver.findElement(By.xpath("//li[normalize-space()='Term']"));
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
	    
	    public void scrollToPageBottom() {
	    	
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
	    }

	    
	    public void clickOnSaveAndProceed() {
	    	
	    	saveAndProceedButton = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
			saveAndProceedButton.click();
			System.out.println("Proceed button was clicked");
	    }
	    
	    public void uploadImage() throws InterruptedException {
	        // Locate the hidden file input
	        fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//input[@type='file' and @accept='image/*']")
	        ));

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
        
        public void clickOnPublishStaticText() {
        	
        	publishSetupStaticText = driver.findElement(By.xpath("//h1[normalize-space()='Publish Setup']"));
		    publishSetupStaticText.click();
        }
        
        public void clickOnWhatsappCheckbox() {
        	
        	whatsAppCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='wh-platform' and @type='checkbox'])[1]")));
        	// whatsAppCheckbox = driver.findElement(By.xpath("(//input[@name='wh-platform' and @type='checkbox'])[1]"));
		    whatsAppCheckbox.click();
        }
        
        public void clickOnSocialCheckbox() {
        	
        	socialCheckbox = driver.findElement(By.xpath("(//input[@name='so-platform' and @class='form-check-input'])[1]"));
		    socialCheckbox.click();
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
        
        public void clickOnProfileLogoButton() {
        	
        	profileLogoButton =driver.findElement(By.xpath("(//button[@id='dropdown-basic'])[1]"));
		    // (//button[@id='dropdown-basic'])[1]
		    profileLogoButton.click();
		     
        }
        

		
        public void clickOnLongTextField() {
        	
        	longTextField = driver.findElement(By.xpath("//textarea[@id='formTextarea']"));
        	longTextField.sendKeys("This is for testing only so let's do it now.");
        	
        	
        }
        
        public void waitForSocialPostPageToLoad() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Next']")
            ));
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
        
        
        
        
        
        
        
        
        
        
        
        }
	 
	 
	 
	 
	 
	 
		 
		 


