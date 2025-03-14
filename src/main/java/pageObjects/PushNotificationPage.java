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
	
	
	public void clickOnCommunicationTab() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    communicationTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Communication']")));
	    communicationTab.click();
	   	 

}
	
	public void clickOnNotifications() {
		
		// Dev Server : //a[normalize-space()='New Push Notification']
		// Preprod Server : //a[normalize-space()='Notification']
		Notifications = driver.findElement(By.xpath("//a[normalize-space()='New Push Notification']"));
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
		
		addPhotoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='image_url']")));
        addPhotoButton.sendKeys(imagePath);
	}
	
	public void clickOnTargetCategory() {
		
		targetCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='mt-checkbox mt-checkbox-single mt-checkbox-outline']/input[@title='Raj2024']")));
        // Using JavaScript to Click the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetCategory);
        
        
        // Use Actions to click at an offset position (blank space)
        Actions actions = new Actions(driver);
        actions.moveByOffset(50,150).click().perform();
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
		// Scroll to the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", schedulingDateTime);
        
        // Write the code in order to select the date and then time
        // Use JavaScript to bring the element into focus and remove any interfering elements if necessary. 
        // Instead of click(), focus the element using JavaScript.
        // Below line of code was written to remove ClickInterceptionException
        js.executeScript("arguments[0].focus();", schedulingDateTime);
        schedulingDateTime.sendKeys(date);

        // Simulate pressing the Tab key to move to the time input
        schedulingDateTime.sendKeys(Keys.TAB);

        // Enter the time
        schedulingDateTime.sendKeys(time);
        
        schedulingDateTime.sendKeys(Keys.ARROW_UP);
        
	}
	
	public void clickOnSubmitButton() {
		
		submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitButton.click();
	}
	
	
	
	
	
	
	

}
