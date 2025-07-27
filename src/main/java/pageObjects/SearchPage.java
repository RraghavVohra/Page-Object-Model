package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
	 private WebElement noDataElement;
	 private WebElement bookmarkedFilter;
	 private WebElement micrositeFilter;
	 private WebElement videoFilter;
	 private WebElement brochureFilter;
	 private WebElement bannersFilter;
	 private WebElement socialPostsFilter;
	 private WebElement EmailFilter;
	 private WebElement AllFilter;
	 private WebElement draftAndPublishedOption;
	
	 
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
	    
	    public void clickOnDraftAndPublishedOption() {
	    	
	    	draftAndPublishedOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='null' and text()='Draft & Published']")));
	        draftAndPublishedOption.click();
	    	
	    }
	    
	    public void clickOnAllQuickFilter() {
	    	
	    	AllFilter = wait.until(ExpectedConditions.elementToBeClickable
			(By.xpath("(//a[@data-rr-ui-event-key='#' and contains(text(), 'All')])[1]")));
			AllFilter.click();
	    }
	    
	    public void clickOnBookmarkedFilter() {
	    	
	    	bookmarkedFilter = wait.until(ExpectedConditions.elementToBeClickable
			(By.xpath("(//a[@data-rr-ui-event-key='bookmarked' and contains(text(), 'bookmarked')])[1]")));
			bookmarkedFilter.click();
	    }
	    
	    public void clickOnMicrositeFilter() {
	    	
	    	micrositeFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='microsite' and contains(text(), 'Microsite')])[1]")));
	    	micrositeFilter.click();
	    }
	    
	    public void clickOnVideoFilter() {
	    	
	    	videoFilter = wait.until(ExpectedConditions.elementToBeClickable
			(By.xpath("(//a[@data-rr-ui-event-key='1' and contains(text(), 'Video')])[1]")));
			videoFilter.click();
	    }
	    
	    public void clickOnBrochureFilter() {
	    	
	    	 brochureFilter = wait.until(ExpectedConditions.elementToBeClickable
	 		 (By.xpath("(//a[@data-rr-ui-event-key='21' and contains(text(), 'Brochure')])[1]")));
	 		 brochureFilter.click();
	    }
	    
	    public void clickOnBannerFilter() {
	    	
	    	bannersFilter = wait.until(ExpectedConditions.elementToBeClickable
		    (By.xpath("(//a[@data-rr-ui-event-key='34' and contains(text(), 'Banners')])[1]")));
			bannersFilter.click();
	    }
	    
	    public void clickOnSocialPostsFilter() {
	    	
	    	socialPostsFilter = wait.until(ExpectedConditions.elementToBeClickable
			(By.xpath("(//a[@data-rr-ui-event-key='15' and contains(text(), 'Social Posts')])[1]")));
			socialPostsFilter.click();
	    }
	    
	    public void clickOnEmailQuickFilter() {
	    	
	    	EmailFilter = wait.until(ExpectedConditions.elementToBeClickable
			(By.xpath("(//a[@data-rr-ui-event-key='42' and contains(text(), 'Email')])[1]")));
			EmailFilter.click();
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
	    
	    public List<WebElement> getAssetCardsWithPublishButtons() {
	        return driver.findElements(By.xpath("(//button[@type='button'][normalize-space()='Publish'])"));
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
	    
	    public String getTextFromNoDataElement() {
	    	
	    	noDataElement = driver.findElement(By.xpath("//div[@class='no-data asset']"));
	    	return noDataElement.getText();
		    
	    }
	    
	    public List<WebElement> getBookmarkIconInAsset(WebElement asset) {
	        return asset.findElements(By.xpath(".//*[local-name()='svg' and contains(@class, 'bi-bookmark-check-fill')]"));
	    }
	    
	    public List<WebElement> getBookmarkIconInAssetTwo(WebElement asset) {
	        return asset.findElements(By.xpath(".//*[name()='svg' and contains(@class, 'asset-bookmark')]"));
	    }
	    
	    
	    
	    public List<WebElement> getMicrositeInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'Microsite')])"));
	    }
	    
        public List<WebElement> getVideoInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'Video')])"));
	    }
        
       public List<WebElement> getBrochureInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'pdf')])"));
	    }
       
       public List<WebElement> getBannerInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'Banner')])"));
	    }
       
        public List<WebElement> getSocialInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'Social')])"));
	    }
        
        public List<WebElement> getWhatsappInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'Whatsapp')])"));
	    }
       
        public List<WebElement> getEmailInAsset(WebElement asset){
	    	
	    	return asset.findElements(By.xpath("(//div[contains(text(),'Emailer')])"));
	    }
        
        public List<WebElement> getPublishOrPublishedButtons(WebElement card) {
            List<WebElement> buttons = new ArrayList<>();
            try {
                buttons.add(card.findElement(By.xpath("//button[@type='button' and contains(@class, 'btn-outline-info') and text()='Publish']")));
            } catch (NoSuchElementException ignored) {}
            try {
                buttons.add(card.findElement(By.xpath("//button[@type='button' and @disabled and @class='btn btn-secondary btn-sm' and text()='Published']")));
            } catch (NoSuchElementException ignored) {}
            return buttons;
        }
        
        public List<WebElement> getPublishOrPublishedButtonsTwo(WebElement card) {
            List<WebElement> buttons = new ArrayList<>();

            // Match 'Publish' button
            buttons.addAll(card.findElements(
                By.xpath(".//button[@type='button' and contains(@class, 'btn-outline-info') and text()='Publish']")));

            // Match 'Published' button
            buttons.addAll(card.findElements(
                By.xpath(".//button[@type='button' and @disabled and contains(@class, 'btn-secondary') and text()='Published']")));

            return buttons;
        }

        
        
	    
	    
	    
	    
	    public String getNoDataText() {
	        try {
	            WebElement noDataElement = driver.findElement(By.xpath("//div[@class='no-data asset']"));
	            return noDataElement.getText();
	        } catch (NoSuchElementException e) {
	            return "";  // Safely return empty if "No Data" message is not present
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	   
	
	
	
	
	
	 
	 
	
	 
	 
	 
	 
	 
	 
		

}
