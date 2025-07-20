package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 public SearchPage(WebDriver driver) {
			
			this.driver=driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
	 
	 
	 
	 private WebElement searchTextfield;
	 private WebElement searchIcon;
	 private WebElement draftAndPublishedDropdown;
	 private WebElement draftButton;
	 private WebElement publishedButton;
	 private WebElement profileIcon;
	 private WebElement logOutOption;
	 private WebElement logoutButton;
	 private WebElement profileIconTwo;
	
	 
	 public void enterValueIntoSearchTextfield(String contentName) {
	        searchTextfield = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search library']")));
	        searchTextfield.sendKeys(contentName);
	    }

	    public void clickOnSearchIcon() {
	        searchIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='search-icon']")));
	        searchIcon.click();
	    }

	    public void clickOnDraftAndPublishedDropdown() {
	        draftAndPublishedDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@aria-label='select-status']")));
	        draftAndPublishedDropdown.click();
	    }

	    public void clickOnDraftButton() {
	        draftButton = driver.findElement(By.xpath("//option[@value='0' and text()='Draft']"));
	        draftButton.click();
	    }

	    public void clickOnPublishedButton() {
	        publishedButton = driver.findElement(By.xpath("//option[@value='1' and text()='Published']"));
	        publishedButton.click();
	    }

	    public List<WebElement> getDropdownOptions() {
	        return driver.findElements(By.xpath("//select[@aria-label='select-status']/option"));
	    }

	    public WebElement getSearchResultElementByText(String actualSearch) {
	        return driver.findElement(By.xpath("//a/p[contains(text(), '" + actualSearch + "')]"));
	    }

	    public List<WebElement> getAssetCards() {
	        return driver.findElements(By.xpath("//div[@class='card-body'][.//button[text()='Publish']]"));
	    }
	    
	    public List<WebElement> getAssetCardsWithPublishedButtons() {
	        return driver.findElements(By.xpath("//div[@class='card-body'][.//button[text()='Published']]"));
	    }
	    

	    public List<WebElement> getPublishButtonsInAsset(WebElement asset) {
	        return asset.findElements(By.xpath("//button[@type='button' and contains(@class, 'btn-outline-info') and text()='Publish']"));
	    }

	    public List<WebElement> getPublishedButtonsInAsset(WebElement asset) {
	        return asset.findElements(By.xpath("//button[@type='button' and @disabled and @class='btn btn-secondary btn-sm' and text()='Published']"));
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
	    
	    public void clickOnProfileIconAfterSearch() {
	    	
	    	profileIconTwo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[local-name()='svg' and contains(@class, 'bi-person-circle')]")));
	    	profileIconTwo.click();
	    	
	    }
	    
	   
	
	
	
	
	
	 
	 
	
	 
	 
	 
	 
	 
	 
		

}
