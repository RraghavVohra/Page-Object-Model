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
			// Increased from 10s to 20s — prod server is slower than dev
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

	 public void clickOnSocialPostButtonDev() {

		 socialPostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='card-body'])[3]")));
		 socialPostButton.click();
	 }
	 
	 public void clickOnSocialPostButtonProd() {

		 socialPostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='card-body'])[4]")));
		 socialPostButton.click();
	 }

	 public void attachFile() throws Exception {

	        attachBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Attach']")));

	        // Scroll to element — instant scroll instead of smooth to avoid timing dependency
	        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", attachBtn);
	        // Thread.sleep(2000); // removed — was only needed because smooth scroll takes time
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", attachBtn);

	        // Click attach button
	        attachBtn.click();
	        Thread.sleep(2000); // intentional — waiting for OS native file dialog to open (Robot-based)

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
	        Thread.sleep(3000); // intentional — waiting for OS file dialog to process and close (Robot-based)
	    }

	    public void clickOnNextButton() {

	    nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@class, 'btn-info') and contains(text(), 'Next')]")));

	    // Switched from Actions click to JS click — Actions was unreliable on prod due to overlays
	    // Actions actions = new Actions(driver);
	    // actions.moveToElement(nextButton).click().perform();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
	    System.out.println("Next Button was clicked");

	    // Wait for Asset Details page to load — Name field appearing confirms page is ready
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")));
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

	    	// Switched from driver.findElement to elementToBeClickable — ensures field is ready before clicking
	    	// categoriesField = driver.findElement(By.xpath("(//input[contains(@class, 'searchBox')])[1]"));
	    	// categoriesField.click();
	    	categoriesField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@class, 'searchBox')])[1]")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoriesField);
	    }

	    public void clickOnCategoryOptionDev() {

	    	// Switched from driver.findElement to elementToBeClickable — option may not be immediately visible
	    	// categoryOption = driver.findElement(By.xpath("//li[normalize-space()='Term']"));
	    	// categoryOption.click();
	    	// Switched to JS click — overlay-bg intercepts the regular click
	    	categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Term']")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryOption);

	    	// Wait for overlay to dismiss before next click
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='overlay-bg']")));
	    }
	    
	    public void clickOnCategoryOptionProd() {

	    	// Switched from driver.findElement to elementToBeClickable — option may not be immediately visible
	    	// categoryOption = driver.findElement(By.xpath("//li[normalize-space()='Term']"));
	    	// categoryOption.click();
	    	// Switched to JS click — overlay-bg intercepts the regular click on prod
	    	categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Term Plan']")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryOption);

	    	// Wait for overlay to dismiss before next click — overlay-bg stays visible after option selection
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='overlay-bg']")));
	    }

	    public void clickOnCategoriesStaticText() {

	    	// Switched from driver.findElement to elementToBeClickable
	    	// categoriesStaticText = driver.findElement(By.xpath("//label[normalize-space()='Categories']"));
	    	// categoriesStaticText.click();
	    	// Switched to JS click — overlay-bg can still be present when this runs
	    	categoriesStaticText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Categories']")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoriesStaticText);
	    }

	    public void clickOnHashtagField() {

	    	// Switched from driver.findElement to elementToBeClickable + JS click — field may be intercepted
	    	// hashtagField = driver.findElement(By.xpath("//input[@placeholder='Select Hashtags']"));
	    	// hashtagField.click();
	    	hashtagField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Hashtags']")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", hashtagField);
	    }

	    public void clickOnHashtagDev() {

	    	// Switched from driver.findElement to elementToBeClickable — option appears after dropdown opens
	    	// hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
	    	// hashtagOption.click();
	    	hashtagOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='what']")));
	    	hashtagOption.click();
	    }
	    
	    public void clickOnHashtagProd() {

	    	// Switched from driver.findElement to elementToBeClickable — option appears after dropdown opens
	    	// hashtagOption = driver.findElement(By.xpath("//li[normalize-space()='what']"));
	    	// hashtagOption.click();
	    	hashtagOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Insurance']")));
	    	hashtagOption.click();
	    }

	    public void centeringDev() {

	    	hashtagOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='what']")));
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", hashtagOption);
	    }
	    
	    public void centeringProd() {

	    	hashtagOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Insurance']")));
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", hashtagOption);
	    }

	    public void clickOnHashtagStaticText() {

	    	// Switched from driver.findElement to elementToBeClickable
	    	// hashtagsStaticText = driver.findElement(By.xpath("//label[normalize-space()='Hashtags']"));
	    	// hashtagsStaticText.click();
	    	hashtagsStaticText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Hashtags']")));
	    	hashtagsStaticText.click();
	    }

	    public void scrollToPageBottom() {

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
	    }


	    public void clickOnSaveAndProceed() {

	    	// Switched from driver.findElement to elementToBeClickable + JS click — ElementClickInterceptedException on prod
	    	// saveAndProceedButton = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
	    	// saveAndProceedButton.click();
	    	saveAndProceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save & Proceed']")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveAndProceedButton);
	    	System.out.println("Proceed button was clicked");

	    	// Wait for upload page to load — file input appearing confirms page 2 is ready
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file' and @accept='image/*']")));
	    }

	    public void uploadImage() throws InterruptedException {
	        // Locate the hidden file input
	        fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//input[@type='file' and @accept='image/*']")
	        ));

	        // Provide absolute file path directly
	        fileInput.sendKeys("C:\\Users\\admin\\Downloads\\Untitled design.jpg");

	        System.out.println("Image uploaded successfully!");

	        // Wait for Save & Proceed button to be clickable — confirms upload has been processed
	        // Thread.sleep(3000); // removed — replaced with explicit element wait
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save & Proceed']")));
	    }


        public void clickOnSaveAndProceedButton() {

        	// Switched from driver.findElement to elementToBeClickable — ensures button is ready after upload
        	// saveAndProceedButtonTwo = driver.findElement(By.xpath("//button[normalize-space()='Save & Proceed']"));
        	saveAndProceedButtonTwo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save & Proceed']")));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].click();", saveAndProceedButtonTwo);

		    // Wait for Publish Setup page to load — WhatsApp checkbox appearing confirms page is ready
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='wh-platform' and @type='checkbox'])[1]")));

		    // Wait for overlay to fully dismiss — overlay-bg can still be present after page transition
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='overlay-bg']")));
        }

        public void clickOnPublishStaticText() {

        	publishSetupStaticText = driver.findElement(By.xpath("//h1[normalize-space()='Publish Setup']"));
		    publishSetupStaticText.click();
        }

        public void clickOnWhatsappCheckbox() {

        	whatsAppCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='wh-platform' and @type='checkbox'])[1]")));
        	// whatsAppCheckbox = driver.findElement(By.xpath("(//input[@name='wh-platform' and @type='checkbox'])[1]"));
        	// Switched to JS click — overlay-bg can intercept checkbox clicks on prod
		    // whatsAppCheckbox.click();
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsAppCheckbox);
        }

        public void clickOnSocialCheckbox() {

        	socialCheckbox = driver.findElement(By.xpath("(//input[@name='so-platform' and @class='form-check-input'])[1]"));
		    socialCheckbox.click();
        }

        public void selectPartnersDropdown() {

        	// Switched from driver.findElement to elementToBeClickable — ensures dropdown is ready
        	// selectPartnersDropdown = driver.findElement(By.xpath("//span[@class='css-1v99tuv']"));
        	// selectPartnersDropdown.click();
        	selectPartnersDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='css-1v99tuv']")));
        	selectPartnersDropdown.click();
        }

        public void selectPartnerOption() {

        	// Switched from driver.findElement to elementToBeClickable — option appears after dropdown opens
        	// selectPartnerOption = driver.findElement(By.xpath("//div[contains(text(), 'Raj2024')]"));
        	// selectPartnerOption.click();
        	selectPartnerOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'Raj2024')]")));
        	selectPartnerOption.click();
        }

        public void closePartnerOptionDialogBox() {

            // Use Actions class to perform the close action
            Actions actions = new Actions(driver);
            actions.moveToElement(selectPartnersDropdown).click().perform();
            System.out.println("Dialog box closed using Actions!");

            // Wait for dropdown/dialog to close — confirms it is no longer intercepting clicks
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Raj2024')]")));
        }

        public void clickOnCobrandingToggle() {

        	// Switched from driver.findElement to elementToBeClickable + JS click — toggles may be intercepted
        	// cobrandingToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[1]"));
        	// cobrandingToggle.click();
        	cobrandingToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='custom-switch'])[1]")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", cobrandingToggle);
        }

        public void clickOnPushNotificationToggle() {

        	// Switched from driver.findElement to elementToBeClickable + JS click
        	// pushNotificationToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[2]"));
        	// pushNotificationToggle.click();
        	pushNotificationToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='custom-switch'])[2]")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", pushNotificationToggle);
        }

        public void clickOnEmailNotificationToggle() {

        	// Switched from driver.findElement to elementToBeClickable + JS click
        	// emailNotificationToggle = driver.findElement(By.xpath("(//input[@id='custom-switch'])[3]"));
        	// emailNotificationToggle.click();
        	emailNotificationToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='custom-switch'])[3]")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", emailNotificationToggle);
        }

        public void clickOnPublishButton() {

        	// Switched from driver.findElement to elementToBeClickable + JS click — ElementClickInterceptedException on prod
        	// publishButton = driver.findElement(By.xpath("//button[normalize-space()='Publish']"));
        	// publishButton.click();
        	publishButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Publish']")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", publishButton);

        	// Wait for Asset Library page to load — profile dropdown button appearing confirms redirect is done
        	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='dropdown-basic']")));
        }

        public void clickOnProfileLogoButton() {

        	profileLogoButton =driver.findElement(By.xpath("(//button[@id='dropdown-basic'])[1]"));
		    // (//button[@id='dropdown-basic'])[1]
		    profileLogoButton.click();

        }


        public void clickOnLongTextField() {

        	// Switched from driver.findElement to elementToBeClickable — ensures textarea is ready
        	// longTextField = driver.findElement(By.xpath("//textarea[@id='formTextarea']"));
        	longTextField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='formTextarea']")));
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

	    	// Switched from SVG icon to Bootstrap dropdown button — clicking the SVG did not open the dropdown
	    	// profileIconTwo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[local-name()='svg' and contains(@class, 'bi-person-circle')]")));
	    	// profileIconTwo.click();
	    	profileIconTwo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='dropdown-basic']")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileIconTwo);

	    }




        }
