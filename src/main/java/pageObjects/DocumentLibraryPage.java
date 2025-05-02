package pageObjects;

import java.io.File;
import java.io.IOException;
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

public class DocumentLibraryPage {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	
	
	public DocumentLibraryPage(WebDriver driver) {
		
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	
	private WebElement communicationTab;
	private WebElement documentLibrary;
	private WebElement actionsButton;
	private WebElement profileIcon;
	private WebElement logOutOption;
	private WebElement logoutButton;
	private WebElement uploadOption;
	private WebElement uploadButton;
	private WebElement doc_name;
	private WebElement thumbnail_button;
	private WebElement apply_button;
	private WebElement descriptionTextField;
	private WebElement documentOptionTwo;
	private WebElement documentOptionThree;
	private WebElement downloadableOn;
	private WebElement internalHashtagField;
	private WebElement selectTheOptionHashtag;
	private WebElement searchBox;
	private WebElement searchResult;
	private WebElement deleteOption;
	private WebElement clickOnOkButton;
	private WebElement grabTheText;
	private WebElement checkboxOption;
	private WebElement dynamicElement;
	private WebElement noRecordsElement;
	private WebElement accessOption;
	private WebElement teamRadioButton;
	private WebElement partnerCategoryButton;
	private WebElement clickOnTheCategory;
	private WebElement updateAccessButton;
	
	
    public void clickOnCommunicationTab() {
	
	    communicationTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Communication']")));
	    communicationTab.click();
	   	
   }
    
   public void clickonDocumentLibrary() {
	   
	   documentLibrary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='dropdown-item'])[4]")));
	   documentLibrary.click();
   }
   
   public void clickOnActionsButton() {
	   
	   actionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[name()='svg'])[1]")));
	   actionsButton.click();
   }
   
   public List<String> getDocumentLibraryOptions() {
	    List<String> optionsText = new ArrayList<>();
	    
	    WebElement createOption = driver.findElement(By.xpath("//a[@href='sp-upload-document.php']"));
	    optionsText.add(createOption.getText().trim());
	    
	    WebElement accessOption = driver.findElement(By.id("add_synd"));
	    optionsText.add(accessOption.getText().trim());
	    
	    WebElement updateHashtags = driver.findElement(By.id("add_hastag"));
	    optionsText.add(updateHashtags.getText().trim());
	    
	    WebElement deleteOption = driver.findElement(By.id("Delete3"));
	    optionsText.add(deleteOption.getText().trim());
	    
	    return optionsText;
	}
   
   public void clickOnProfileIcon() {
	   
	   profileIcon = driver.findElement(By.xpath("//i[@class='fa fa-user-circle']"));
	   profileIcon.click();
   }
   
   public void clickOnLogoutOption() {
	   
	   logOutOption = driver.findElement(By.xpath("//a[normalize-space()='Log Out']"));
	   JavascriptExecutor js = (JavascriptExecutor) driver;
	   js.executeScript("arguments[0].click();", logOutOption);
   }
   
   public void clickOnLogoutButton() {
	   
	   logoutButton = driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
	   logoutButton.click();
   }
   
   public void clickOnUploadOption() {
	   
	   uploadOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='sp-upload-document.php']")));
	   uploadOption.click();
   }
   
   public void clickOnUploadButton() {
	   
	   uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='share_button']")));
	   uploadButton.click();
   }
   
   public void enterValueInDocumentNameField(String documentName) {
	   
	   doc_name = driver.findElement(By.xpath("//input[@id='document_name']"));  
	   doc_name.sendKeys(documentName);
   }
   
   // This is a really important method. Since i am grabbing the message from the tool tip
   public String getValidationMessageForDocumentNameField() {
	   
	    doc_name = driver.findElement(By.xpath("//input[@id='document_name']"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return arguments[0].validationMessage;",doc_name);
   }
   
   public void uploadDocumentUsingAutoIt() throws IOException, InterruptedException {
	    WebElement fileInput = driver.findElement(By.id("document_file"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(fileInput).click().perform();
	    Thread.sleep(3000);

	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\uploadpdfdoclibrary.exe");
	    if (!autoItScript.exists()) {
	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	    } else {
	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();
	        System.out.println("AutoIt script exited with code: " + exitCode);
	    }

	    Thread.sleep(2000);

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
	    js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

	    actions.moveByOffset(100, 100).click().perform();
	    Thread.sleep(2000);

	    String uploadedFileName = fileInput.getDomProperty("value");
	    System.out.println("Uploaded File: " + uploadedFileName);
	}
   
   
   public void uploadDocumentInPngFormatUsingAutoIt() throws IOException, InterruptedException {
	    WebElement fileInput = driver.findElement(By.id("document_file"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(fileInput).click().perform();
	    Thread.sleep(3000);

	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pngimagefortest.exe");
	    if (!autoItScript.exists()) {
	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	    } else {
	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();
	        System.out.println("AutoIt script exited with code: " + exitCode);
	    }

	    Thread.sleep(2000);

	    // THE BELOW LINE OF CODE WORKED FOR PDF TYPE FILES BUT NOT FOR PNG/JPEG FILES
	    //JavascriptExecutor js = (JavascriptExecutor) driver;
	    //js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
	    //js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

	    //actions.moveByOffset(100, 100).click().perform();
	    //Thread.sleep(2000);

	    String uploadedFileName = fileInput.getDomProperty("value");
	    System.out.println("Uploaded File: " + uploadedFileName);
	}
   
    public void uploadDocumentInJPGFormatUsingAutoIt() throws InterruptedException, IOException {
    	
    	 WebElement fileInput = driver.findElement(By.id("document_file"));
 	    Actions actions = new Actions(driver);
 	    actions.moveToElement(fileInput).click().perform();
 	    Thread.sleep(3000);

 	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\jpgimagefortest.exe");
 	    if (!autoItScript.exists()) {
 	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
 	    } else {
 	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
 	        Process process = processBuilder.start();
 	        int exitCode = process.waitFor();
 	        System.out.println("AutoIt script exited with code: " + exitCode);
 	    }

 	    Thread.sleep(2000);
 	    
 	    // THE BELOW LINE OF CODE WORKED FOR PDF TYPE FILES BUT NOT FOR PNG/JPEG FILES
	    //JavascriptExecutor js = (JavascriptExecutor) driver;
	    //js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
	    //js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

	    //actions.moveByOffset(100, 100).click().perform();
	    //Thread.sleep(2000);
 	    
 	   String uploadedFileName = fileInput.getDomProperty("value");
	   System.out.println("Uploaded File: " + uploadedFileName);
    	
    }
    
    public void uploadDocumentInCSVFormatUsingAutoIt() throws InterruptedException, IOException {
    	
   	 WebElement fileInput = driver.findElement(By.id("document_file"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(fileInput).click().perform();
	    Thread.sleep(3000);

	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\csvfilefortest.exe");
	    if (!autoItScript.exists()) {
	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	    } else {
	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();
	        System.out.println("AutoIt script exited with code: " + exitCode);
	    }

	    Thread.sleep(2000);
	    
	    // THE BELOW LINE OF CODE WORKED FOR PDF TYPE FILES BUT NOT FOR PNG/JPEG/CSV FILES
	    //JavascriptExecutor js = (JavascriptExecutor) driver;
	    //js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
	    //js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

	    //actions.moveByOffset(100, 100).click().perform();
	    //Thread.sleep(2000);
	    
	   String uploadedFileName = fileInput.getDomProperty("value");
	   System.out.println("Uploaded File: " + uploadedFileName);
   	
   }
    
    public void uploadDocumentInXLSXFormatUsingAutoIt() throws InterruptedException, IOException {
    	
      	 WebElement fileInput = driver.findElement(By.id("document_file"));
   	    Actions actions = new Actions(driver);
   	    actions.moveToElement(fileInput).click().perform();
   	    Thread.sleep(3000);

   	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\xlsxfilefortest.exe");
   	    if (!autoItScript.exists()) {
   	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
   	    } else {
   	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
   	        Process process = processBuilder.start();
   	        int exitCode = process.waitFor();
   	        System.out.println("AutoIt script exited with code: " + exitCode);
   	    }

   	    Thread.sleep(2000);
   	    
   	    // THE BELOW LINE OF CODE WORKED FOR PDF TYPE FILES BUT NOT FOR PNG/JPEG/CSV/XLSX FILES
   	    //JavascriptExecutor js = (JavascriptExecutor) driver;
   	    //js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
   	    //js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

   	    //actions.moveByOffset(100, 100).click().perform();
   	    //Thread.sleep(2000);
   	    
   	   String uploadedFileName = fileInput.getDomProperty("value");
   	   System.out.println("Uploaded File: " + uploadedFileName);
      	
      }
    
    public void uploadDocumentInMP4FormatUsingAutoIt() throws InterruptedException, IOException {
    	
     	 WebElement fileInput = driver.findElement(By.id("document_file"));
  	    Actions actions = new Actions(driver);
  	    actions.moveToElement(fileInput).click().perform();
  	    Thread.sleep(3000);

  	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\mpfourfielfortest.exe");
  	    if (!autoItScript.exists()) {
  	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
  	    } else {
  	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
  	        Process process = processBuilder.start();
  	        int exitCode = process.waitFor();
  	        System.out.println("AutoIt script exited with code: " + exitCode);
  	    }

  	    Thread.sleep(2000);
  	    
  	    // THE BELOW LINE OF CODE WORKED FOR PDF TYPE FILES BUT NOT FOR PNG/JPEG/CSV/XLSX/MP4/GIF FILES
  	    //JavascriptExecutor js = (JavascriptExecutor) driver;
  	    //js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
  	    //js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

  	    //actions.moveByOffset(100, 100).click().perform();
  	    //Thread.sleep(2000);
  	    
  	   String uploadedFileName = fileInput.getDomProperty("value");
  	   System.out.println("Uploaded File: " + uploadedFileName);
     	
     }
    
    public void uploadDocumentInGIFFormatUsingAutoIt() throws InterruptedException, IOException {
    	
    	WebElement fileInput = driver.findElement(By.id("document_file"));
 	    Actions actions = new Actions(driver);
 	    actions.moveToElement(fileInput).click().perform();
 	    Thread.sleep(3000);

 	    File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\giffilefortest.exe");
 	    if (!autoItScript.exists()) {
 	        System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
 	    } else {
 	        ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
 	        Process process = processBuilder.start();
 	        int exitCode = process.waitFor();
 	        System.out.println("AutoIt script exited with code: " + exitCode);
 	    }

 	    Thread.sleep(2000);
 	    
 	    // THE BELOW LINE OF CODE WORKED FOR PDF TYPE FILES BUT NOT FOR PNG/JPEG/CSV/XLSX/MP4/GIF FILES
 	    //JavascriptExecutor js = (JavascriptExecutor) driver;
 	    //js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", fileInput);
 	    //js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }))", fileInput);

 	    //actions.moveByOffset(100, 100).click().perform();
 	    //Thread.sleep(2000);
 	    
 	   String uploadedFileName = fileInput.getDomProperty("value");
 	   System.out.println("Uploaded File: " + uploadedFileName);
    	
    }
   
   
    public void attachThumbnail(String thumbnailAttach) {
    	
    	thumbnail_button = driver.findElement(By.id("img_validate"));
		thumbnail_button.sendKeys(thumbnailAttach);
    }
    
    public void resizeCroppingArea() {
        WebElement croppingHandle = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@class='imgareaselect-border4']")));
        
        Actions actions = new Actions(driver);
        actions.clickAndHold(croppingHandle)
               .moveByOffset(50, 50) // Move 50 pixels right and 50 pixels down
               .release()
               .perform();
    }
    
    public void clickOnApplyButton() {
    	
    	apply_button = driver.findElement(By.xpath("//a[@class='btn yes yellow-gold pull-right']"));
		apply_button.click();
    }
    
    public void enterValueInDescriptionField(String descriptionText) {
    	
    	descriptionTextField = driver.findElement(By.xpath("//textarea[@class='form-control h150']"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", descriptionTextField);
	    descriptionTextField.sendKeys(descriptionText);
    }
    
    public void scrollToElementToUploadButton() {
    	
    	uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='share_button']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",uploadButton);
        
    }
    
    // This is a really important method. Since i am grabbing the message from the tool tip
    public String getValidationMessageForDescriptionField() {
 	   
    	descriptionTextField = driver.findElement(By.xpath("//textarea[@class='form-control h150']"));
 	    JavascriptExecutor js = (JavascriptExecutor) driver;
 	    return (String) js.executeScript("return arguments[0].validationMessage;",descriptionTextField);
    }
    
    // This is a really important method. Since i am grabbing the message from the tool tip
    public String getValidationMessageForDocumentAttachmentField() {
    	
    	WebElement fileInput = driver.findElement(By.id("document_file"));
 	    JavascriptExecutor js = (JavascriptExecutor) driver;
 	    return (String) js.executeScript("return arguments[0].validationMessage;",fileInput);
    }

    public void clickOnDocumentOptionTwo() {
    	
    	documentOptionTwo = driver.findElement(By.xpath("//input[@value='2']"));
	    documentOptionTwo.click();
    }
    
    public void clickOnDocumentOptionThree() {
    	
    	documentOptionThree = driver.findElement(By.xpath("//input[@value='3']"));
	    documentOptionThree.click();
    }
 	
    public void clickOnDownloadableOption() {
    	
    	downloadableOn = driver.findElement(By.xpath("//span[@class='bootstrap-switch-handle-off bootstrap-switch-default']"));
	    downloadableOn.click();
    }

    public void enterInternalHashtag(String text) {
    	
    	internalHashtagField = driver.findElement(By.xpath("//input[@id='tagcsv']"));
	    internalHashtagField.sendKeys(text);
    }
    
    public void selectInternalHashtag() {
    	
    	selectTheOptionHashtag = driver.findElement(By.xpath("//li[@class='ui-menu-item']"));
	    selectTheOptionHashtag.click();
    }
    
    public void enterIntoSearchBox(String texts) {
    	
    	searchBox = driver.findElement(By.xpath("//input[@id='keywords']"));
        searchBox.sendKeys(texts);
    }
   
    // Method to get the search result text and THIS WILL DIFFER FOR ALL THE SERVERS
    public String getSearchResultText() {
    	
    	searchResult = driver.findElement(By.xpath("//td[normalize-space()='Life SWAG Pension']"));
    	return searchResult.getText();
    	
    	
    }
    
    public void clickOnDeleteOption() {
    	
    	deleteOption = driver.findElement(By.id("Delete3"));
    	deleteOption.click();
    }
    
    public void clickOnOkButton() {
    	
    	clickOnOkButton = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
		clickOnOkButton.click();
    }
    
    // Method to grab and return the dialog text
    public String getDialogBoxText() {
        grabTheText = driver.findElement(By.xpath("//div[@class='bootbox-body']"));
        String textPresentInDialogBox = grabTheText.getText();
        System.out.println(textPresentInDialogBox); // Optional: you can print it here if you want
        return textPresentInDialogBox;
    }
    
    
    public void clickOnCheckBoxOption() {
    	
    	checkboxOption = driver.findElement(By.xpath("(//label[contains(@class, 'mt-checkbox')]//span)[2]"));       
        checkboxOption.click();
    }
    
    
    public String getDynamicText() {
    	
    	dynamicElement = driver.findElement(By.xpath("(//tr/td[@class='wBreak hidden-xs hidden-sm'])[1]"));
        String dynamicText = dynamicElement.getText();
        System.out.println("Dynamic Text: " + dynamicText); // Optional
        return dynamicText;
    }
    
    
    public String noRecordsElementMethod() {
    	
    	noRecordsElement = driver.findElement(By.xpath("//tr[@class='no-records-found']/td"));
        String noRecordsText = noRecordsElement.getText();
        return noRecordsText;
    }
    
    public void clickOnAccessOption() {
    	
    	accessOption = driver.findElement(By.id("add_synd"));
		accessOption.click();
    }
    
    // Method to click the radio button using JavaScript Executor
    public void clickOnTeamRadioButton() {
    	
    	teamRadioButton = driver.findElement(By.xpath("//input[@id='partners_option']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", teamRadioButton);
    }
    
    public void clickOnPartnerCategoryButton() {
    	
    	partnerCategoryButton = driver.findElement(By.xpath("//button[@id='btn_ptr_category']"));
		partnerCategoryButton.click();
    }
    
    public void clickOnCategory() {
    	
    	clickOnTheCategory = driver.findElement(By.xpath("//label[@for='ms-opt-40']"));
		clickOnTheCategory.click();
    }
    
    public void clickOnUpdateAccessButton() {
    	
    	updateAccessButton = driver.findElement(By.xpath("//input[@id='synd_update_id']"));
		updateAccessButton.click();
    }
    

   
   
   
   
   
   

   
   
    
    
	
    
	
	
	
	
	
	
	
	

}
