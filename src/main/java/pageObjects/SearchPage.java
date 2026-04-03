package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

	 private WebDriver driver;
	 private WebDriverWait wait;

	 public SearchPage(WebDriver driver) {

			this.driver=driver;
			// Increased from 10s to 20s to handle slower responses
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
		 	// Upgraded to elementToBeClickable — field must be ready for input
	        // searchTextfield = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search library']")));
	        searchTextfield = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search library']")));
	        searchTextfield.sendKeys(contentName);
	    }

	    public void clickOnSearchIcon() {
	    	// Upgraded to elementToBeClickable — icon must be interactive before clicking; added post-wait for results to load
	        // searchIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='search-icon']")));
	        searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='search-icon']")));
	        searchIcon.click();
	        // Post-wait: results loaded — either asset cards or No Data message is visible
	        wait.until(d -> !d.findElements(By.xpath("//div[contains(@class,'asset-card')]")).isEmpty()
	            || !d.findElements(By.xpath("//div[@class='no-data asset']")).isEmpty());
	    }

	    public void clickOnDraftAndPublishedDropdown() {
	    	// Upgraded to elementToBeClickable — dropdown must be interactive before clicking
	        // draftAndPublishedDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@aria-label='select-status']")));
	        draftAndPublishedDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@aria-label='select-status']")));
	        draftAndPublishedDropdown.click();
	    }

	    public void clickOnDraftAndPublishedOption() {
	    	// Upgraded to elementToBeClickable — option must be selectable; added post-wait for results to load
	    	// draftAndPublishedOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='null' and text()='Draft & Published']")));
	    	draftAndPublishedOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='null' and text()='Draft & Published']")));
	        draftAndPublishedOption.click();
	        // Post-wait: results loaded — either asset cards or No Data message is visible
	        wait.until(d -> !d.findElements(By.xpath("//div[contains(@class,'asset-card')]")).isEmpty()
	            || !d.findElements(By.xpath("//div[@class='no-data asset']")).isEmpty());
	    }

	    public void clickOnAllQuickFilter() throws InterruptedException {
	    	AllFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='#' and contains(text(), 'All')])[1]")));
	    	AllFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnBookmarkedFilter() throws InterruptedException {
	    	bookmarkedFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='bookmarked' and contains(text(), 'bookmarked')])[1]")));
	    	bookmarkedFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnMicrositeFilter() throws InterruptedException {
	    	micrositeFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='microsite' and contains(text(), 'Microsite')])[1]")));
	    	micrositeFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnVideoFilter() throws InterruptedException {
	    	videoFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='1' and contains(text(), 'Video')])[1]")));
	    	videoFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnBrochureFilter() throws InterruptedException {
	    	brochureFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='21' and contains(text(), 'Brochure')])[1]")));
	    	brochureFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnBannerFilter() throws InterruptedException {
	    	bannersFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='34' and contains(text(), 'Banners')])[1]")));
	    	bannersFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnSocialPostsFilter() throws InterruptedException {
	    	socialPostsFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='15' and contains(text(), 'Social Posts')])[1]")));
	    	socialPostsFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnEmailQuickFilter() throws InterruptedException {
	    	EmailFilter = wait.until(ExpectedConditions.elementToBeClickable
	    	(By.xpath("(//a[@data-rr-ui-event-key='42' and contains(text(), 'Email')])[1]")));
	    	EmailFilter.click();
	    	// Fixed wait — count-change approach timed out when all cards matched this filter type
	    	Thread.sleep(1500);
	    }

	    public void clickOnDraftButton() {
	    	// Upgraded to elementToBeClickable — option must be selectable after dropdown opens
	    	// Old approach: draftButton = driver.findElement(By.xpath("//option[@value='0' and text()='Draft']"));
	        draftButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='0' and text()='Draft']")));
	        draftButton.click();
	        // Post-wait: results loaded — either asset cards or No Data message is visible
	        wait.until(d -> !d.findElements(By.xpath("//div[contains(@class,'asset-card')]")).isEmpty()
	            || !d.findElements(By.xpath("//div[@class='no-data asset']")).isEmpty());
	    }

	    public void clickOnPublishedButton() {
	    	// Upgraded to elementToBeClickable — option must be selectable after dropdown opens
	    	// Old approach: publishedButton = driver.findElement(By.xpath("//option[@value='1' and text()='Published']"));
	        publishedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='1' and text()='Published']")));
	        publishedButton.click();
	        // Post-wait: results loaded — either asset cards or No Data message is visible
	        wait.until(d -> !d.findElements(By.xpath("//div[contains(@class,'asset-card')]")).isEmpty()
	            || !d.findElements(By.xpath("//div[@class='no-data asset']")).isEmpty());
	    }

	    public List<WebElement> getDropdownOptions() {
	        return driver.findElements(By.xpath("//select[@aria-label='select-status']/option"));
	    }

	    public WebElement getSearchResultElementByText(String actualSearch) {
	        return driver.findElement(By.xpath("//a/p[contains(text(), '" + actualSearch + "')]"));
	    }

	    public void scrollToLoadAllCards() throws InterruptedException {
	    	// Lazy loading — cards only render when scrolled into view.
	    	// Keep scrolling down in steps until the card count stops increasing (all cards loaded).
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	int previousCount = 0;
	    	int currentCount = 0;
	    	int maxAttempts = 20;
	    	int attempt = 0;
	    	do {
	    		previousCount = currentCount;
	    		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    		Thread.sleep(1000); // Wait for newly loaded cards to render
	    		currentCount = driver.findElements(By.xpath("//div[contains(@class,'asset-card')]")).size();
	    		attempt++;
	    	} while (currentCount > previousCount && attempt < maxAttempts);
	    	// Scroll back to top so card elements are accessible without stale references
	    	js.executeScript("window.scrollTo(0, 0)");
	    	Thread.sleep(300);
	    }

	    public List<WebElement> getAssetCards() {
	    	// Fixed text()='Publish' → normalize-space()='Publish' to handle whitespace in button text
	    	// Old: return driver.findElements(By.xpath("//div[@class='card-body'][.//button[text()='Publish']]"));
	        return driver.findElements(By.xpath("//div[contains(@class,'asset-card')][.//button[normalize-space()='Publish']]"));
	    }

	    public List<WebElement> getAssetCardsWithPublishedButtons() {
	    	// Fixed: card-body → asset-card, text()='Published' → normalize-space()='Published'
	    	// Old: return driver.findElements(By.xpath("//div[@class='card-body'][.//button[text()='Published']]"));
	        return driver.findElements(By.xpath("//div[contains(@class,'asset-card')][.//button[normalize-space()='Published']]"));
	    }

	    public List<WebElement> getAssetCardsWithPublishButtons() {
	        return driver.findElements(By.xpath("(//button[@type='button'][normalize-space()='Publish'])"));
	    }


	    public List<WebElement> getPublishButtonsInAsset(WebElement asset) {
	    	// Fixed: was //button (whole-page search) — changed to .//button (relative to this card only)
	    	// Old: return asset.findElements(By.xpath("//button[@type='button' and contains(@class, 'btn-outline-info') and text()='Publish']"));
	        return asset.findElements(By.xpath(".//button[@type='button' and normalize-space()='Publish']"));
	    }

	    public List<WebElement> getPublishedButtonsInAsset(WebElement asset) {
	    	// Fixed: was //button (whole-page search) — changed to .//button (relative to this card only)
	    	// Old: return asset.findElements(By.xpath("//button[@type='button' and @disabled and @class='btn btn-secondary btn-sm' and text()='Published']"));
	        return asset.findElements(By.xpath(".//button[@type='button' and normalize-space()='Published']"));
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
	    	// Upgraded to visibilityOfElementLocated — No Data message may take time to appear after search
	    	// Old approach: noDataElement = driver.findElement(By.xpath("//div[@class='no-data asset']"));
	    	noDataElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='no-data asset']")));
	    	return noDataElement.getText();
	    }

	    public List<WebElement> getBookmarkIconInAsset(WebElement asset) {
	        return asset.findElements(By.xpath(".//*[local-name()='svg' and contains(@class, 'bi-bookmark-check-fill')]"));
	    }

	    public List<WebElement> getBookmarkIconInAssetTwo(WebElement asset) {
	        return asset.findElements(By.xpath(".//*[name()='svg' and contains(@class, 'asset-bookmark')]"));
	    }

	    public List<WebElement> getAllBookmarkIconsOnPage() {
	        return driver.findElements(By.xpath(".//*[name()='svg' and contains(@class, 'asset-bookmark')]"));
	    }



	    public List<WebElement> getMicrositeInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'Microsite')])"));
	    }

        public List<WebElement> getVideoInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'Video')])"));
	    }

       public List<WebElement> getBrochureInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'pdf')])"));
	    }

       public List<WebElement> getBannerInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'Banner')])"));
	    }

        public List<WebElement> getSocialInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'Social')])"));
	    }

        public List<WebElement> getWhatsappInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'Whatsapp')])"));
	    }

        public List<WebElement> getEmailInAsset(WebElement asset){

	    	return asset.findElements(By.xpath("(.//div[contains(text(),'Emailer')])"));
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

        public List<WebElement> getPublishOrPublishedButtonsThree(WebElement card) {
            return card.findElements(By.xpath(
                ".//button[@type='button' and " +
                "((contains(@class, 'btn-outline-info') and normalize-space(text())='Publish') " +
                "or (contains(@class, 'btn-secondary') and @disabled and normalize-space(text())='Published'))]"
            ));
        }







	    public String getNoDataText() {
	        try {
	            WebElement noDataElement = driver.findElement(By.xpath("//div[@class='no-data asset']"));
	            return noDataElement.getText();
	        } catch (NoSuchElementException e) {
	            return "";  // Safely return empty if "No Data" message is not present
	        }
	    }

	    /*public List<WebElement> getAssetCardsWithPublishOrPublishedButtons() {
	        List<WebElement> allCards = driver.findElements(By.xpath("//div[contains(@class,'asset-card')]"));
	        List<WebElement> matchingCards = new ArrayList<>();

	        for (WebElement card : allCards) {
	            boolean hasPublish = !getPublishButtonsInAsset(card).isEmpty();
	            boolean hasPublished = !getPublishedButtonsInAsset(card).isEmpty();

	            if (hasPublish || hasPublished) {
	                matchingCards.add(card);
	            }
	        }

	        return matchingCards;
	    }
	    */
	    /*public List<WebElement> getAssetCardsWithPublishOrPublishedButtons() {
	        List<WebElement> allCards = driver.findElements(By.xpath("//div[contains(@class,'asset-card')]"));
	        List<WebElement> matchingCards = new ArrayList<>();

	        for (WebElement card : allCards) {
	            boolean hasButton = !card.findElements(By.xpath(".//button[text()='Publish' or text()='Published']")).isEmpty();
	            if (hasButton) {
	                matchingCards.add(card);
	            }
	        }
	        return matchingCards;
	    }
	    */


	    public List<WebElement> getAssetCardsWithPublishOrPublishedButtons() {
	        // Directly locate only those asset cards that contain Publish or Published buttons
	        List<WebElement> matchingCards = driver.findElements(By.xpath(
	            "//div[contains(@class,'asset-card')]" +
	            "[.//button[normalize-space()='Publish' or normalize-space()='Published']]"
	        ));
	        return matchingCards;
	    }





















}
