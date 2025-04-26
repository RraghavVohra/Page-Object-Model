package pageObjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
   
   
    public void attachThumbnail() {
    	
    	thumbnail_button = driver.findElement(By.id("img_validate"));
		String filePathTwo = Paths.get("C:\\Users\\admin\\Downloads\\PushNotificationImage.png").toString();
		thumbnail_button.sendKeys(filePathTwo);
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

   
    

   
   
   
   
   
   

   
   
    
    
	
    
	
	
	
	
	
	
	
	

}
