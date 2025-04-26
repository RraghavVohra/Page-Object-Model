package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PushNotificationPage {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 
	
	public PushNotificationPage(WebDriver driver) {
		
		this.driver = driver;
		// BELOW CODE WAS JUST ADDED AFTERWARDS. SINCE I DID NOT INTIALIZED IT EARLIER
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize wait
	}
	
	private WebElement communicationTab;
	private WebElement Notifications;
	private WebElement pageHeading;
	private WebElement actionsButton;
	private WebElement createAppNotification;
	private WebElement notificationNameTextfield;
	private WebElement notificationMessageTextfield;
	private WebElement partnerListRadioButton;
	private WebElement partnerCategoryRadioButton;
	private WebElement categoryDropdown;
	private WebElement addPhotoButton;
	private WebElement targetCategory;
	private WebElement customLinkButton;
	private WebElement enterLinkTextfield;
	private WebElement schedulingDateTime;
	private WebElement submitButton;
	private WebElement cropButton;
	// Dev Server has Crop button. But Preprod does not have
	private WebElement searchTextfield;
	private WebElement profileIcon;
	private WebElement logOutOption;
	private WebElement logoutButton;
	private WebElement pushNotificationRadioButton;
	private WebElement whatsAppRadioButton;
	private WebElement uploadListRadioButton;
	private WebElement selectAllButton;
	private WebElement toastMessageLocator; 
	private WebElement closeToastButtonLocator;
	private WebElement contentLinkButton;
	private WebElement contentLinkDropdown;
	private WebElement contentSelection;
	private WebElement uploadCsvButtonLocator;
	
	
	
	
	
	
	
	public void clickOnCommunicationTab() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    communicationTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Communication']")));
	    communicationTab.click();
	   	 

}
	
	public void clickOnNotifications() {
		
		
		// Dev Server : //a[normalize-space()='New Push Notification']
		// Preprod Server : //a[normalize-space()='Notification']
		// Notifications = driver.findElement(By.xpath("//a[normalize-space()='New Push Notification']"));
		Notifications = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='New Push Notification']")));
		Notifications.click();
	}
	
	
	public String getPageHeading() {
		
		pageHeading = driver.findElement(By.xpath("//span[@class='fs-2 fw-bolder']"));
		return pageHeading.getText();
	}
	
	public void clickOnActionsButton() {
		
		actionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[name()='svg'])[1]")));
		actionsButton.click();
	}
	
	public void clickOnCreateAppNotification() {
		
		createAppNotification = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='Create App Notification'][1]")));
		createAppNotification.click();
	}
	
	public void enterNotificationName(String notificationnameText) {
		
		notificationNameTextfield = wait.until(ExpectedConditions.elementToBeClickable((By.id("pushnotify_name"))));
		notificationNameTextfield.sendKeys(notificationnameText);
	}
	
	public void enterNotificationMessage(String notificationmessageText) {
		
		notificationMessageTextfield = wait.until(ExpectedConditions.elementToBeClickable(By.id("pushnotify_msg")));
		notificationMessageTextfield.sendKeys(notificationmessageText);
	}
	
	public void clickOnPartnerListRadioButton() {
		
		partnerListRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("upload_list")));
        partnerListRadioButton.click();
	}
	
	public void clickOnpartnerCategoryRadioButton() {
		
		partnerCategoryRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("partner_category")));
        partnerCategoryRadioButton.click();
	}
	
	public void clickOnCategoryDropdown() {
		
		categoryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btn_ptr_category']")));
        categoryDropdown.click();
	}
	
	public void attachPhoto(String imagePath) {
		
		addPhotoButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='image_url']")));
        addPhotoButton.sendKeys(imagePath);
        
	}
	
	public void scrollToCategoryDropdown() {
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView(true);",categoryDropdown);
	}
	
	public void clickOnTargetCategory() {
		
		
		targetCategory = wait.until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath("//label[normalize-space()='Raj2024']")));                            
		//label[contains(@class, 'mt-checkbox')]/input[@title='Raj2024']")));
        // Using JavaScript to Click the element
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",targetCategory);
		//targetCategory.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetCategory);
        
	}
	
	// Clicking on Blank space after selecting the target category so that the dialog box closes
	public void clickOnBlankSpace() {
	    Actions actions = new Actions(driver);
	    actions.moveByOffset(50, 150).click().perform();
	}

	
	public void clickOnCustomLinkButton() {
		
		customLinkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='switch-field']/label[@for='custom-link']")));
        customLinkButton.click();
	}
	
	public void enterValueLinkTextfield(String customLinkText) {
		
		enterLinkTextfield = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter link']")));
        enterLinkTextfield.sendKeys(customLinkText);
	}
	
	public void enterSchedulingDateTime(String date, String time) {
		
		schedulingDateTime = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-md-4']/input[@name='pushnotify_time']")));
		// Convert to proper format
	    String formattedDateTime = formatToDateTimeLocal(date, time);

	    // Set the value using JavaScript
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", schedulingDateTime);
	    js.executeScript("arguments[0].setAttribute('value', arguments[1])", schedulingDateTime, formattedDateTime);
        
        
	}
	
	
	// HELPER METHOD
	private String formatToDateTimeLocal(String date, String time) {
	    // Input: "22/04/2025", "11:30"
	    String[] parts = date.split("/");
	    String day = parts[0];
	    String month = parts[1];
	    String year = parts[2];
	    return year + "-" + month + "-" + day + "T" + time;
	}

	
	public void clickOnSubmitButton() {
		
		submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitButton.click();
	}
	
	public void clickOnCropButton() {
		
		cropButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button'][text()='Crop']")));
        cropButton.click();
		
	}
	
	public void enterIntoSearchTextfield() {
		
		searchTextfield = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchTextfield.sendKeys("Raj2024");
	}
	
	public void clickOnProfileIcon() {
		
		 profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span/i[@class='text-primary fas fa-user-circle']")));
         profileIcon.click();
	}
	
	public void clickOnLogoutOption() {
		
		logOutOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='ms-2'])[3]")));
        logOutOption.click();
	}
	
	public void clickOnLogoutButton() {
		
		logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
        logoutButton.click();
	}
	
	
	// This is a really important method. Since i am grabbing the message from the tool tip
	public String getValidationMessageForNotificationName() {
	    WebElement inputField = driver.findElement(By.id("pushnotify_name"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return arguments[0].validationMessage;", inputField);
	}
	
	// This is a really important method. Since i am grabbing the message from the tool tip
	public String getValidationMessageForNotificationMessage() {
		
		WebElement inputField = driver.findElement(By.id("pushnotify_msg"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return arguments[0].validationMessage;", inputField);
		
	}
	
	public String getValidationMessageForParnterCategoryNotSelected() {
		
		// CURRENTLY WE DO NOT SEE THIS MESSAGE AS IT NEEDS TO BE FIXED
		WebElement inputField = driver.findElement(By.id("pushnotify_msg"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return arguments[0].validationMessage;", inputField);
	}
	
	public String getValidationMessageForCustomLinkTextfield() {
		
		WebElement belowcustomLinkTextfield = wait.until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath("//span[@id='customlink_error']")));
		 return belowcustomLinkTextfield.getText().trim(); 
		// (By.xpath("//span[@id='customlink_error' and text()='Please enter Custom Link to proceed']")));
	    // JavascriptExecutor js = (JavascriptExecutor) driver;
	   //  return (String) js.executeScript("return arguments[0].validationMessage;", belowcustomLinkTextfield);
		
	}
	
	public List<String> getActionMenuOptions() {
	    List<String> optionsText = new ArrayList<>();
	    // List<WebElement> options = driver.findElements(By.xpath("//a[@class='menu-link px-3']"));
	    
	    for (int i = 1; i <= 3; i++) {
	        WebElement option = driver.findElement(By.xpath("(//a[@class='menu-link px-3'])[" + i + "]"));
	        optionsText.add(option.getText().trim());
	    }

	    return optionsText;
	}
	
	public void clickOnPushNotificationRadioButton() {
		
		pushNotificationRadioButton = driver.findElement(By.xpath("//input[@name='channel'][@value='1']")); 
		pushNotificationRadioButton.click();
		// Wait until the Push Notification radio button is selected
	    new WebDriverWait(driver, Duration.ofSeconds(5))
	    .until(ExpectedConditions.elementToBeSelected(pushNotificationRadioButton));
	}
	
	public void clickOnWhatsAppRadioButton() {
		
		whatsAppRadioButton = driver.findElement(By.xpath("//input[@name='channel'][@value='2']"));
		whatsAppRadioButton.click();
		// Wait until the WhatsApp radio button is selected
	    new WebDriverWait(driver, Duration.ofSeconds(5))
	    .until(ExpectedConditions.elementToBeSelected(whatsAppRadioButton));
	}
	
	public boolean isPushNotificationSelected() {
        return driver.findElement(By.xpath("//input[@name='channel'][@value='1']")).isSelected();
    }

    public boolean isWhatsAppSelected() {
        return driver.findElement(By.xpath("//input[@name='channel'][@value='2']")).isSelected();
    }
    
    public void clickOnUploadListRadioButton() {
    	
    	uploadListRadioButton = driver.findElement(By.xpath("//input[@name='send_to'][@value='upload_list']"));
    	uploadListRadioButton.click();
    	new WebDriverWait(driver, Duration.ofSeconds(5))
	    .until(ExpectedConditions.elementToBeSelected(uploadListRadioButton));
    	
    }
    
    public boolean isUploadListRadioButtonSelected() {
    	
    	return driver.findElement(By.xpath("//input[@name='send_to'][@value='upload_list']")).isSelected();
    		
    }
    
    public boolean isPartnerCategoryRadioButton() {
    	
    	return driver.findElement(By.id("partner_category")).isSelected();
    	
    }
    
    public void clickOnSelectAllButton() {
    	
    	selectAllButton = driver.findElement(By.xpath("//a[@class='ms-selectall global']"));
        selectAllButton.click();
    }
    
    // Method to get total categories text
    public String getTotalCategoriesText() {
        return driver.findElement(By.xpath("//button[@id='btn_ptr_category']")).getText();
    }
    
    
    public boolean searchAndValidateOption(String searchValue) {
    	
        WebElement searchTextfield = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchTextfield.clear(); // good practice
        searchTextfield.sendKeys(searchValue);

        List<WebElement> displayedCheckboxes = wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='checkbox']")));

        for (WebElement checkbox : displayedCheckboxes) {
            String optionText = checkbox.getDomAttribute("title");
            System.out.println("Displayed Option: " + optionText);
            if (optionText.equals(searchValue)) {
                return true;
            }
        }

        return false;
    }
    
    public void hoverOverAddPhotoButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(addPhotoButton).perform();
    }
    
 // Returns true if Crop button is displayed
    public boolean isCropButtonDisplayed() {
    	
    	cropButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button'][text()='Crop']")));
        return cropButton.isDisplayed();
    }

    // Returns the text of Crop button
    public String getCropButtonText() {
        return cropButton.getText();
    }
    
    
    // Method to get toast message text
    public String getToastMessageText() {
        
    	toastMessageLocator = driver.findElement(By.xpath("//span[@class='mssg_content']"));
        return toastMessageLocator.getText();
    }
    
    
    // Method to close the toast
    public void closeToastMessage() {
       
    	closeToastButtonLocator = driver.findElement(By.xpath(("//span[@onclick='close_success_mssg()']")));
    	closeToastButtonLocator.click();
    }
    
    public void clickOnContentLinkButton() {
    	
    	contentLinkButton = driver.findElement(By.xpath("//label[@for='content-link']"));
    	contentLinkButton.click();
    	
    }
    
    public void clickOnContentLinkDropdown() {
    	
    	contentLinkDropdown = driver.findElement(By.id("select2-contentLinkDropdown-container"));
    	contentLinkDropdown.click();
    }
    
    
    public void clickOnContentSelection() {
    	
    	contentSelection = driver.findElement(By.xpath("(//li[@class='select2-results__option select2-results__option--selectable'])[1]"));
    	contentSelection.click();
    }
    
    public void uploadCsvFile(String filePath) {
        
    	uploadCsvButtonLocator = driver.findElement(By.xpath("//input[@id='upload_csv']"));
    	uploadCsvButtonLocator.sendKeys(filePath);
    }
    
    
    // This is a really important method. Since i am grabbing the message from the tool tip
 	public String getValidationMessageForUploadCSVButton() {
 		
 		uploadCsvButtonLocator = driver.findElement(By.xpath("//input[@id='upload_csv']"));
 	    JavascriptExecutor js = (JavascriptExecutor) driver;
 	    return (String) js.executeScript("return arguments[0].validationMessage;", uploadCsvButtonLocator);
 		
 	}
    
    
    
    
   
    
    
    
    
    
    
    
}


	
	

	
	
	
	
	
	
	
	
	

