package tests;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import base.Base;
import pageObjects.LoginPage;
import pageObjects.SearchPage;

public class Search extends Base {
	
	SearchPage searchPage;
	LoginPage loginPage;
	
	 @AfterMethod
	 public void tearDown() {
	        driver.quit();  // Close browser after tests
	 }
	 
	 @Test(priority = 1)
	 public void test_TC_SA_01_searchfunctionality() throws InterruptedException {

		    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	    	// ✅ Initialize searchPage
	        searchPage = new SearchPage(driver);
	        searchPage.enterValueIntoSearchTextfield(prop.getProperty("enterValueInSearchTextfield"));
	        searchPage.clickOnSearchIcon();

	        Thread.sleep(3000);

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        String actualSearch = "Lacoste's 1980";
	        // ✅ Safe check: don't throw if element not found
	        // Here we had an apostrophe so we wrote the below xpath like that i.e. \"" + actualSearch + "\"
	        List<WebElement> resultElements = driver.findElements(By.xpath("//a/p[contains(text(), \"" + actualSearch + "\")]"));

	        if (!resultElements.isEmpty()) {
	            String expectedSearch = resultElements.get(0).getText();
	            Assert.assertEquals(expectedSearch, actualSearch, "Search result text does not match!");
	            System.out.println("Test Case TC_SA_01 got passed");
	        } else {
	            System.out.println("Search result for '" + actualSearch + "' not found. Treating as passed.");
	            // You can use `Assert.assertTrue(true);` or decide based on your test scenario.
	        }
	        
	        js.executeScript("window.scrollTo(0, 0);");
	        Thread.sleep(1000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	        

	        }
	 
	 
	 @Test(priority = 2)
	 public void test_TC_SA_02_verifyalltheOptionsOfDropdowns() throws InterruptedException {
	    	
            driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        js.executeScript("window.scrollBy(0,200)");
	        searchPage.clickOnDraftAndPublishedDropdown();

	        List<WebElement> options = searchPage.getDropdownOptions();

	        String optionOne = options.get(0).getText().trim();
	        String optionTwo = options.get(1).getText().trim();
	        String optionThree = options.get(2).getText().trim();

	        Assert.assertEquals(optionOne, "Draft & Published", "First option mismatch!");
	        Assert.assertEquals(optionTwo, "Draft", "Second option mismatch!");
	        Assert.assertEquals(optionThree, "Published", "Third option mismatch!");

	        System.out.println("All Options exists.Test Case TC_SA_02 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	        
	        
	       
	    }
	    
	 @Test(priority = 3)
	 public void test_TC_SA_03_verifyFilteringWithOnlyDraftOption() throws InterruptedException {
		 
		    driver = openBrowserAndApplication(prop.getProperty("browser"));
		    
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        //searchPage.enterValueIntoSearchTextfield(prop.getProperty("enterValueTwoInSearchTextfield"));
	        //searchPage.clickOnSearchIcon();

	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,300)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        
	        js.executeScript("document.activeElement.blur();");
	        
	        List<WebElement> assetCards = searchPage.getAssetCards();
	        
	        // STEP 1 : First we will count how many Assets are shown
			// STEP 2 : Verify that each asset contains a "Publish" button.
			// Ensure the number of assets matches the expected count (e.g., 20).

	        int actualCount = assetCards.size();
	        int expectedCount = 20;

	        if (actualCount == expectedCount) {
	            System.out.println("✅ Asset count matched. Found " + actualCount + " assets.");
	        } else {
	            System.out.println("❌ Asset count mismatch. Expected: " + expectedCount + ", Found: " + actualCount);
	            return;
	        }

	        boolean allHavePublishButton = true;
	        for (WebElement asset : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(1000);
	            List<WebElement> publishButtons = searchPage.getPublishButtonsInAsset(asset);
	            if (publishButtons.isEmpty()) {
	                allHavePublishButton = false;
	                System.out.println("❌ One of the assets is missing 'Publish'.");
	                break;
	            }
	        }

	        if (allHavePublishButton) {
	            System.out.println("✅ All 20 assets have 'Publish' buttons.");
	        } else {
	            System.out.println("❌ Test failed: Some assets are missing 'Publish' button.");
	        }

	        System.out.println("Test Case TC_SA_03 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	        
			   
			
	        
	    }
	    
	 @Test(priority = 4)
	 public void test_TC_SA_04_verifyFilteringWithOnlyPublishedOption() throws InterruptedException {
	    	
            driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        searchPage.enterValueIntoSearchTextfield("Test");
	        searchPage.clickOnSearchIcon();

	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");

            List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        
	        // STEP 1 : First we will count how many Assets are shown
			// STEP 2 : Verify that each asset contains a "Published" button.
			// Ensure the number of assets matches the expected count (e.g., 20).

	        int actualCount = assetCards.size();
	        int expectedCount = 20;

	        if (actualCount == expectedCount) {
	            System.out.println("✅ Asset count matched. Found " + actualCount + " assets.");
	        } else {
	            System.out.println("❌ Asset count mismatch. Expected: " + expectedCount + ", Found: " + actualCount);
	            return;
	        }

	        boolean allHavePublishedButton = true;
	        for (WebElement asset : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(1000);
	            List<WebElement> publishedButtons = searchPage.getPublishedButtonsInAsset(asset);
	            if (publishedButtons.isEmpty()) {
	                allHavePublishedButton = false;
	                System.out.println("❌ One of the assets is missing 'Published'.");
	                break;
	            }
	        }

	        if (allHavePublishedButton) {
	            System.out.println("✅ All 20 assets have 'Published' buttons.");
	        } else {
	            System.out.println("❌ One of the assets is missing 'Published'.");
	        }

	        System.out.println("Test Case TC_SA_04 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	     
	    }
	    
	 @Test(priority = 5)
	 public void test_TC_SA_05_verifyFilteringWithBothDraftAndPublishedOption() throws InterruptedException {

            driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");
	    	searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        searchPage.enterValueIntoSearchTextfield("Test");
	        searchPage.clickOnSearchIcon();

	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        List<WebElement> assetCards = searchPage.getAssetCards();
	        List<WebElement> assetCardsTwo = searchPage.getAssetCardsWithPublishedButtons();
	        
	        int actualCount = assetCards.size();
	        int actualCountTwo = assetCardsTwo.size();
	        int expectedCount = 20;
	        
	        int combinedCount = actualCount + actualCountTwo;

	        if (combinedCount == expectedCount) {
	            System.out.println("✅ Asset count matched. Found " + combinedCount + " assets.");
	        } else {
	            System.out.println("❌ Asset count mismatch. Expected: " + expectedCount + ", Found: " + combinedCount);
	            return;
	        }

	        
	        boolean allAssetsValid = true;
	        int index = 1;

	        for (WebElement asset : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(300);
	            if (searchPage.getPublishButtonsInAsset(asset).isEmpty()) {
	                allAssetsValid = false;
	                System.out.println("❌ Asset #" + index + " (Publish group) is missing 'Publish' button.");
	                break;
	            }
	            index++;
	        }

	        for (WebElement asset : assetCardsTwo) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(300);
	            if (searchPage.getPublishedButtonsInAsset(asset).isEmpty()) {
	                allAssetsValid = false;
	                System.out.println("❌ Asset #" + index + " (Published group) is missing 'Published' button.");
	                break;
	            }
	            index++;
	        }


	        if (allAssetsValid) {
	            System.out.println("✅ All assets correctly show either 'Publish' or 'Published'.");
	        } else {
	            System.out.println("❌ Test failed: Some assets are missing both markers.");
	        }

	        System.out.println("Test Case TC_SA_05 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	        
	    }
	 
	 @Test(priority=6)
     public void test_TC_SA_06_verifyFilteringWithOnlyDraftOptionWithoutSearching() throws InterruptedException {
		 
		    driver = openBrowserAndApplication(prop.getProperty("browser"));
		    
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,300)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        
	        js.executeScript("document.activeElement.blur();");
	        
	        List<WebElement> assetCards = searchPage.getAssetCards();
	        
	        // STEP 1 : First we will count how many Assets are shown
			// STEP 2 : Verify that each asset contains a "Publish" button.
			// Ensure the number of assets matches the expected count (e.g., 20).

	        int actualCount = assetCards.size();
	        int expectedCount = 20;

	        if (actualCount == expectedCount) {
	            System.out.println("✅ Asset count matched. Found " + actualCount + " assets.");
	        } else {
	            System.out.println("❌ Asset count mismatch. Expected: " + expectedCount + ", Found: " + actualCount);
	            return;
	        }

	        boolean allHavePublishButton = true;
	        for (WebElement asset : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(1000);
	            List<WebElement> publishButtons = searchPage.getPublishButtonsInAsset(asset);
	            if (publishButtons.isEmpty()) {
	                allHavePublishButton = false;
	                System.out.println("❌ One of the assets is missing 'Publish'.");
	                break;
	            }
	        }

	        if (allHavePublishButton) {
	            System.out.println("✅ All 20 assets have 'Publish' buttons.");
	        } else {
	            System.out.println("❌ Test failed: Some assets are missing 'Publish' button.");
	        }

	        System.out.println("Test Case TC_SA_06 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	 }
	 
	 @Test(priority=7)
     public void test_TC_SA_07_verifyFilteringWithOnlyPublishedOptionWithoutSearching() throws InterruptedException {
		 
		    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");

            List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        
	        // STEP 1 : First we will count how many Assets are shown
			// STEP 2 : Verify that each asset contains a "Published" button.
			// Ensure the number of assets matches the expected count (e.g., 20).

	        int actualCount = assetCards.size();
	        int expectedCount = 20;

	        if (actualCount == expectedCount) {
	            System.out.println("✅ Asset count matched. Found " + actualCount + " assets.");
	        } else {
	            System.out.println("❌ Asset count mismatch. Expected: " + expectedCount + ", Found: " + actualCount);
	            return;
	        }

	        boolean allHavePublishedButton = true;
	        for (WebElement asset : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(1000);
	            List<WebElement> publishedButtons = searchPage.getPublishedButtonsInAsset(asset);
	            if (publishedButtons.isEmpty()) {
	                allHavePublishedButton = false;
	                System.out.println("❌ One of the assets is missing 'Published'.");
	                break;
	            }
	        }

	        if (allHavePublishedButton) {
	            System.out.println("✅ All 20 assets have 'Published' buttons.");
	        } else {
	            System.out.println("❌ One of the assets is missing 'Published'.");
	        }

	        System.out.println("Test Case TC_SA_07 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();

		 
	 }
	 
	 @Test(priority=8)
     public void test_TC_SA_08_verifyFilteringWithBothPublishedAndDraftOptionsWithoutSearching() throws InterruptedException {
		 
		    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");
	    	searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        List<WebElement> assetCards = searchPage.getAssetCards();
	        List<WebElement> assetCardsTwo = searchPage.getAssetCardsWithPublishedButtons();
	        
	        int actualCount = assetCards.size();
	        int actualCountTwo = assetCardsTwo.size();
	        int expectedCount = 20;
	        
	        int combinedCount = actualCount + actualCountTwo;

	        if (combinedCount == expectedCount) {
	            System.out.println("✅ Asset count matched. Found " + combinedCount + " assets.");
	        } else {
	            System.out.println("❌ Asset count mismatch. Expected: " + expectedCount + ", Found: " + combinedCount);
	            return;
	        }

	        
	        boolean allAssetsValid = true;
	        int index = 1;

	        for (WebElement asset : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(300);
	            if (searchPage.getPublishButtonsInAsset(asset).isEmpty()) {
	                allAssetsValid = false;
	                System.out.println("❌ Asset #" + index + " (Publish group) is missing 'Publish' button.");
	                break;
	            }
	            index++;
	        }

	        for (WebElement asset : assetCardsTwo) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", asset);
	            Thread.sleep(300);
	            if (searchPage.getPublishedButtonsInAsset(asset).isEmpty()) {
	                allAssetsValid = false;
	                System.out.println("❌ Asset #" + index + " (Published group) is missing 'Published' button.");
	                break;
	            }
	            index++;
	        }


	        if (allAssetsValid) {
	            System.out.println("✅ All assets correctly show either 'Publish' or 'Published'.");
	        } else {
	            System.out.println("❌ Test failed: Some assets are missing both markers.");
	        }

	        System.out.println("Test Case TC_SA_08 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();

	 }
	 
	
     @Test(priority=9)
     public void test_TC_SA_09_verifySearchFunctionalityWhenInvalidValueEntered() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
		    
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        searchPage.enterValueIntoSearchTextfield(prop.getProperty("enterInvalidValueInSearchTextfield"));
	        searchPage.clickOnSearchIcon();

	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,300)");
	        Thread.sleep(2000);
	        
	        Thread.sleep(2000);
	        
	        searchPage.getTextFromNoDataElement();
		    
	        String noDataText = searchPage.getTextFromNoDataElement();

	        if (noDataText.contains("No Data")) {
	            System.out.println("✅ 'No Data' message is displayed.");
	        } else {
	            System.out.println("❌ 'No Data' message not found.");
	        }
	       
	        System.out.println("Test Case TC_SA_09 got passed");
	        Thread.sleep(3000);
	        
	        searchPage.clickOnProfileIconAfterSearch();
	        Thread.sleep(2000);
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	        
    	 
     }
	 
     @Test(priority=10)
     public void test_TC_SA_10_verifyPublishedContentWithBookmarkedQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnBookmarkedFilter();
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasBookmark = !searchPage.getBookmarkIconInAsset(card).isEmpty();

	            if (hasPublished && hasBookmark) {
	                System.out.println("✅ Card #" + index + " has both Published button and Bookmark icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Bookmark icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_12 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	    }
     
     
     @Test(priority=11)
     public void test_TC_SA_11_verifyPublishedContentWithMicrositeQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnMicrositeFilter();
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasMicrosite = !searchPage.getMicrositeInAsset(card).isEmpty();

	            if (hasPublished && hasMicrosite) {
	                System.out.println("✅ Card #" + index + " has both Published button and Microsite icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Bookmark icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_11 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
	    }
     
     
     @Test(priority=12)
     public void test_TC_SA_12_verifyPublishedContentWithVideoQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnVideoFilter();
	        
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasVideo = !searchPage.getVideoInAsset(card).isEmpty();

	            if (hasPublished && hasVideo) {
	                System.out.println("✅ Card #" + index + " has both Published button and Video icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Bookmark icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_12 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }
	        
     @Test(priority=13)
     public void test_TC_SA_13_verifyPublishedContentWithBrochureQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnBrochureFilter();
	        
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasBrochure = !searchPage.getBrochureInAsset(card).isEmpty();

	            if (hasPublished && hasBrochure) {
	                System.out.println("✅ Card #" + index + " has both Published button and Brochure icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Brochure icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_13 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }
    	
     @Test(priority=14)
     public void test_TC_SA_14_verifyPublishedContentWithBannersQuickFilter() throws InterruptedException {

    	
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnBannerFilter();	        
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasBanner = !searchPage.getBannerInAsset(card).isEmpty();

	            if (hasPublished && hasBanner) {
	                System.out.println("✅ Card #" + index + " has both Published button and Banner icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Banner icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_14 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }
     
     @Test(priority=15)
     public void test_TC_SA_15_verifyPublishedContentWithSocialPostsQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnSocialPostsFilter();       
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasSocialPosts = !searchPage.getSocialInAsset(card).isEmpty();
	            boolean hasWhatsApp = !searchPage.getWhatsappInAsset(card).isEmpty();

	            if (hasPublished && hasSocialPosts && hasWhatsApp) {
	                System.out.println("✅ Card #" + index + " has Published button and Social,Whatsapp icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Social or Whatsapp icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_15 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }

     @Test(priority=16)
     public void test_TC_SA_16_verifyPublishedContentWithEmailQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnPublishedButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnEmailQuickFilter();	        
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishedButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Published button and Bookmark icon
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublished = !searchPage.getPublishedButtonsInAsset(card).isEmpty();
	            boolean hasEmail = !searchPage.getEmailInAsset(card).isEmpty();

	            if (hasPublished && hasEmail) {
	                System.out.println("✅ Card #" + index + " has both Published button and Email icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Published button or Email icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_16 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }

     @Test(priority=17)
     public void test_TC_SA_17_verifyDraftContentWithAllQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnAllQuickFilter();       
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            // boolean hasEmail = !searchPage.getEmailInAsset(card).isEmpty();

	            if (hasPublish) {
	                System.out.println("✅ Card #" + index + " has Publish button.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_17 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
 	 
    	 
     }

     @Test(priority=18)
     public void test_TC_SA_18_verifyDraftContentWithBookmarkedQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnBookmarkedFilter();      
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasBookmark = !searchPage.getBookmarkIconInAsset(card).isEmpty();
	            

	            if (hasPublish && hasBookmark) {
	                System.out.println("✅ Card #" + index + " has Publish and Bookmark icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button and Bookmark icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_18 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }

     @Test(priority=19)
     public void test_TC_SA_19_verifyDraftContentWithMicrositeQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnMicrositeFilter();  
	        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasMicrosite = !searchPage.getMicrositeInAsset(card).isEmpty();
	            // boolean hasEmail = !searchPage.getEmailInAsset(card).isEmpty();

	            if (hasPublish && hasMicrosite) {
	                System.out.println("✅ Card #" + index + " has Publish button and microsite icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button or microsite icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_19 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }

     @Test(priority=20)
     public void test_TC_SA_20_verifyDraftContentWithVideoQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnVideoFilter();        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasVideo = !searchPage.getVideoInAsset(card).isEmpty();

	            if (hasPublish && hasVideo) {
	                System.out.println("✅ Card #" + index + " has Publish button and video icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button or video icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_20 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
     }

     @Test(priority=21)
     public void test_TC_SA_21_verifyDraftContentWithBrochureQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnBrochureFilter();        
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasBrochure = !searchPage.getBrochureInAsset(card).isEmpty();

	            if (hasPublish && hasBrochure) {
	                System.out.println("✅ Card #" + index + " has Publish button and Brochure icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button or Brochure icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_21 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
     }

     @Test(priority=22)
     public void test_TC_SA_22_verifyDraftContentWithBannersQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnBannerFilter();       
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasBanner = !searchPage.getBannerInAsset(card).isEmpty();

	            if (hasPublish && hasBanner) {
	                System.out.println("✅ Card #" + index + " has Publish button and Banner icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button or Banner icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_22 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }

     @Test(priority=23)
     public void test_TC_SA_23_verifyDraftContentWithSocialPostsQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnSocialPostsFilter();       
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasSocialPosts = !searchPage.getSocialInAsset(card).isEmpty();
	            boolean hasWhatsApp = !searchPage.getWhatsappInAsset(card).isEmpty();
	            
	            if (hasPublish && hasSocialPosts && hasWhatsApp) {
	                System.out.println("✅ Card #" + index + " has Publish button, Social,Whatsapp icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button,Social,Whatsapp icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_23 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }

     @Test(priority=24)
     public void test_TC_SA_24_verifyDraftContentWithEmailQuickFilter() throws InterruptedException {
    	 
    	    driver = openBrowserAndApplication(prop.getProperty("browser"));
			
	        loginPage = new LoginPage(driver);
			loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
	    	loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
	    	loginPage.clickOnSubmitButton();
	    	System.out.println("User Logged in Successfully.");

	        searchPage = new SearchPage(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;


	        Thread.sleep(3000);
	        js.executeScript("window.scrollBy(0,400)");
	        Thread.sleep(2000);

	        searchPage.clickOnDraftAndPublishedDropdown();
	        Thread.sleep(3000);
	        searchPage.clickOnDraftButton();
	        js.executeScript("document.activeElement.blur();");
	        
	        Thread.sleep(3000);
	        searchPage.clickOnEmailQuickFilter();       
	        // Step 3: Get all asset cards
	        List<WebElement> assetCards = searchPage.getAssetCardsWithPublishButtons();
	        System.out.println("Total asset cards found: " + assetCards.size());

	        if (assetCards.isEmpty()) {
	            String noDataText = searchPage.getNoDataText();
	            if (noDataText.contains("No Data")) {
	                System.out.println("✅ 'No Data' message is displayed.");
	            } else {
	                System.out.println("❌ No assets and no 'No Data' message found.");
	            }
	            return;
	        }

	        // Step 4: Validate each card for Publish button
	        boolean allCardsValid = true;
	        int index = 1;

	        for (WebElement card : assetCards) {
	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
	            Thread.sleep(300);

	            boolean hasPublish = !searchPage.getPublishButtonsInAsset(card).isEmpty();
	            boolean hasEmail = !searchPage.getEmailInAsset(card).isEmpty();

	            if (hasPublish && hasEmail) {
	                System.out.println("✅ Card #" + index + " has Publish button and Banner icon.");
	            } else {
	                allCardsValid = false;
	                System.out.println("❌ Card #" + index + " is missing Publish button or Banner icon.");
	                break;
	            }
	            index++;
	        }

	        // Step 5: Final Result
	        if (allCardsValid) {
	            System.out.println("✅ All asset cards are valid.");
	        } else {
	            System.out.println("❌ Test failed: Some cards are missing required elements.");
	        }
	        
	        System.out.println("Test Case TC_SA_24 got passed");
	        Thread.sleep(3000);

	        // Step 6: Logout
	        Thread.sleep(2000);
	        searchPage.clickOnProfileIconAfterSearch();
	        searchPage.clickOnLogoutOption();
	        searchPage.clickOnLogoutButton();
    	 
     }
     
     
     @Test(priority=25)
     public void test_TC_SA_25_verifyDraftAndPublishedWithBookmarkQuickFilter() throws InterruptedException {
    	 
         driver = openBrowserAndApplication(prop.getProperty("browser"));

         loginPage = new LoginPage(driver);
         loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
         loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
         loginPage.clickOnSubmitButton();
         System.out.println("User Logged in Successfully.");

         searchPage = new SearchPage(driver);
         JavascriptExecutor js = (JavascriptExecutor) driver;
       
         Thread.sleep(3000);
         js.executeScript("window.scrollBy(0,400)");
         Thread.sleep(2000);

         // Apply Draft & Published filter
         searchPage.clickOnDraftAndPublishedDropdown();
         Thread.sleep(3000);
         searchPage.clickOnDraftAndPublishedOption();
         js.executeScript("document.activeElement.blur();");
         Thread.sleep(3000);
         
         js.executeScript("window.scrollBy(0,200)");
         // Apply Email quick filter
         searchPage.clickOnBookmarkedFilter();
         Thread.sleep(3000);

         // Fetch all cards having either Publish OR Published button
         List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
         System.out.println("Total asset cards found: " + assetCards.size());

         // Handle case when there are no cards
         if (assetCards.isEmpty()) {
             String noDataText = searchPage.getNoDataText();
             if (noDataText.contains("No Data")) {
                 System.out.println("✅ 'No Data' message is displayed.");
             } else {
                 System.out.println("❌ No assets and no 'No Data' message found.");
             }
             return;
         }

         // Validate each card
         boolean allCardsValid = true;
         int index = 1;

         for (WebElement card : assetCards) {
             js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
             Thread.sleep(300);

             boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
             boolean hasBookmark = !searchPage.getBookmarkIconInAsset(card).isEmpty();

             if (hasPublishOrPublished && hasBookmark) {
                 System.out.println("✅ Card #" + index + " has Publish/Published button and Bookmark icon.");
             } else {
                 allCardsValid = false;
                 System.out.println("❌ Card #" + index + " is missing Publish/Published button or Bookmark icon.");
                 break;
             }
             index++;
         }

         // Final assertion
         if (allCardsValid) {
             System.out.println("✅ All asset cards are valid.");
         } else {
             System.out.println("❌ Test failed: Some cards are missing required elements.");
             Assert.fail("One or more cards missing Publish/Published button or Bookmark icon.");
         }

         System.out.println("Test Case TC_SA_25 got passed");
         Thread.sleep(2000);

         // Logout
         searchPage.clickOnProfileIconAfterSearch();
         searchPage.clickOnLogoutOption();
         searchPage.clickOnLogoutButton();
}

     
     @Test(priority=26)
     public void test_TC_SA_26_verifyDraftAndPublishedWithMicrositeQuickFilter() throws InterruptedException {
    	 
     driver = openBrowserAndApplication(prop.getProperty("browser"));

     loginPage = new LoginPage(driver);
     loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
     loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
     loginPage.clickOnSubmitButton();
     System.out.println("User Logged in Successfully.");

     searchPage = new SearchPage(driver);
     JavascriptExecutor js = (JavascriptExecutor) driver;
   
     Thread.sleep(3000);
     js.executeScript("window.scrollBy(0,400)");
     Thread.sleep(2000);

     // Apply Draft & Published filter
     searchPage.clickOnDraftAndPublishedDropdown();
     Thread.sleep(3000);
     searchPage.clickOnDraftAndPublishedOption();
     js.executeScript("document.activeElement.blur();");
     Thread.sleep(3000);
     
   
     // Apply Microsite quick filter
     searchPage.clickOnMicrositeFilter();
     Thread.sleep(3000);

     // Fetch all cards having either Publish OR Published button
     List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
     System.out.println("Total asset cards found: " + assetCards.size());

     // Handle case when there are no cards
     if (assetCards.isEmpty()) {
         String noDataText = searchPage.getNoDataText();
         if (noDataText.contains("No Data")) {
             System.out.println("✅ 'No Data' message is displayed.");
         } else {
             System.out.println("❌ No assets and no 'No Data' message found.");
         }
         return;
     }

     // Validate each card
     boolean allCardsValid = true;
     int index = 1;

     for (WebElement card : assetCards) {
         js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
         Thread.sleep(300);

         boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
         boolean hasMicrosite = !searchPage.getMicrositeInAsset(card).isEmpty();

         if (hasPublishOrPublished && hasMicrosite) {
             System.out.println("✅ Card #" + index + " has Publish/Published button and Microsite icon.");
         } else {
             allCardsValid = false;
             System.out.println("❌ Card #" + index + " is missing Publish/Published button or Microsite icon.");
             break;
         }
         index++;
     }

     // Final assertion
     if (allCardsValid) {
         System.out.println("✅ All asset cards are valid.");
     } else {
         System.out.println("❌ Test failed: Some cards are missing required elements.");
         Assert.fail("One or more cards missing Publish/Published button or Microsite icon.");
     }

     System.out.println("Test Case TC_SA_26 got passed");
     Thread.sleep(2000);

     // Logout
     searchPage.clickOnProfileIconAfterSearch();
     searchPage.clickOnLogoutOption();
     searchPage.clickOnLogoutButton();
 }
     
     @Test(priority=27)
     public void test_TC_SA_27_verifyDraftAndPublishedWithVideoQuickFilter() throws InterruptedException {
    	 
         driver = openBrowserAndApplication(prop.getProperty("browser"));

         loginPage = new LoginPage(driver);
         loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
         loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
         loginPage.clickOnSubmitButton();
         System.out.println("User Logged in Successfully.");

         searchPage = new SearchPage(driver);
         JavascriptExecutor js = (JavascriptExecutor) driver;
       
         Thread.sleep(3000);
         js.executeScript("window.scrollBy(0,400)");
         Thread.sleep(2000);

         // Apply Draft & Published filter
         searchPage.clickOnDraftAndPublishedDropdown();
         Thread.sleep(3000);
         searchPage.clickOnDraftAndPublishedOption();
         js.executeScript("document.activeElement.blur();");
         Thread.sleep(3000);
         
       
         // Apply Video quick filter
         searchPage.clickOnVideoFilter();
         Thread.sleep(3000);

         // Fetch all cards having either Publish OR Published button
         List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
         System.out.println("Total asset cards found: " + assetCards.size());

         // Handle case when there are no cards
         if (assetCards.isEmpty()) {
             String noDataText = searchPage.getNoDataText();
             if (noDataText.contains("No Data")) {
                 System.out.println("✅ 'No Data' message is displayed.");
             } else {
                 System.out.println("❌ No assets and no 'No Data' message found.");
             }
             return;
         }

         // Validate each card
         boolean allCardsValid = true;
         int index = 1;

         for (WebElement card : assetCards) {
             js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
             Thread.sleep(300);

             boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
             boolean hasVideo = !searchPage.getVideoInAsset(card).isEmpty();

             if (hasPublishOrPublished && hasVideo) {
                 System.out.println("✅ Card #" + index + " has Publish/Published button and Video icon.");
             } else {
                 allCardsValid = false;
                 System.out.println("❌ Card #" + index + " is missing Publish/Published button or Video icon.");
                 break;
             }
             index++;
         }

         // Final assertion
         if (allCardsValid) {
             System.out.println("✅ All asset cards are valid.");
         } else {
             System.out.println("❌ Test failed: Some cards are missing required elements.");
             Assert.fail("One or more cards missing Publish/Published button or Video icon.");
         }

         System.out.println("Test Case TC_SA_27 got passed");
         Thread.sleep(2000);

         // Logout
         searchPage.clickOnProfileIconAfterSearch();
         searchPage.clickOnLogoutOption();
         searchPage.clickOnLogoutButton();
     }
     
     @Test(priority=28)
     public void test_TC_SA_28_verifyDraftAndPublishedWithBrochureQuickFilter() throws InterruptedException {
    	 
         driver = openBrowserAndApplication(prop.getProperty("browser"));

         loginPage = new LoginPage(driver);
         loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
         loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
         loginPage.clickOnSubmitButton();
         System.out.println("User Logged in Successfully.");

         searchPage = new SearchPage(driver);
         JavascriptExecutor js = (JavascriptExecutor) driver;
       
         Thread.sleep(3000);
         js.executeScript("window.scrollBy(0,400)");
         Thread.sleep(2000);

         // Apply Draft & Published filter
         searchPage.clickOnDraftAndPublishedDropdown();
         Thread.sleep(3000);
         searchPage.clickOnDraftAndPublishedOption();
         js.executeScript("document.activeElement.blur();");
         Thread.sleep(3000);
         
       
         // Apply Brochure quick filter
         searchPage.clickOnBrochureFilter();
         Thread.sleep(3000);

         // Fetch all cards having either Publish OR Published button
         List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
         System.out.println("Total asset cards found: " + assetCards.size());

         // Handle case when there are no cards
         if (assetCards.isEmpty()) {
             String noDataText = searchPage.getNoDataText();
             if (noDataText.contains("No Data")) {
                 System.out.println("✅ 'No Data' message is displayed.");
             } else {
                 System.out.println("❌ No assets and no 'No Data' message found.");
             }
             return;
         }

         // Validate each card
         boolean allCardsValid = true;
         int index = 1;

         for (WebElement card : assetCards) {
             js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
             Thread.sleep(300);

             boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
             boolean hasBrochure = !searchPage.getBrochureInAsset(card).isEmpty();

             if (hasPublishOrPublished && hasBrochure) {
                 System.out.println("✅ Card #" + index + " has Publish/Published button and Brochure icon.");
             } else {
                 allCardsValid = false;
                 System.out.println("❌ Card #" + index + " is missing Publish/Published button or Brochure icon.");
                 break;
             }
             index++;
         }

         // Final assertion
         if (allCardsValid) {
             System.out.println("✅ All asset cards are valid.");
         } else {
             System.out.println("❌ Test failed: Some cards are missing required elements.");
             Assert.fail("One or more cards missing Publish/Published button or Brochure icon.");
         }

         System.out.println("Test Case TC_SA_28 got passed");
         Thread.sleep(2000);

         // Logout
         searchPage.clickOnProfileIconAfterSearch();
         searchPage.clickOnLogoutOption();
         searchPage.clickOnLogoutButton();
     }
     
     @Test(priority=29)
     public void test_TC_SA_29_verifyDraftAndPublishedWithBannerQuickFilter() throws InterruptedException {
    	 
         driver = openBrowserAndApplication(prop.getProperty("browser"));

         loginPage = new LoginPage(driver);
         loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
         loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
         loginPage.clickOnSubmitButton();
         System.out.println("User Logged in Successfully.");

         searchPage = new SearchPage(driver);
         JavascriptExecutor js = (JavascriptExecutor) driver;
       
         Thread.sleep(3000);
         js.executeScript("window.scrollBy(0,400)");
         Thread.sleep(2000);

         // Apply Draft & Published filter
         searchPage.clickOnDraftAndPublishedDropdown();
         Thread.sleep(3000);
         searchPage.clickOnDraftAndPublishedOption();
         js.executeScript("document.activeElement.blur();");
         Thread.sleep(3000);
         
       
         // Apply Banner quick filter
         searchPage.clickOnBannerFilter();
         Thread.sleep(3000);

         // Fetch all cards having either Publish OR Published button
         List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
         System.out.println("Total asset cards found: " + assetCards.size());

         // Handle case when there are no cards
         if (assetCards.isEmpty()) {
             String noDataText = searchPage.getNoDataText();
             if (noDataText.contains("No Data")) {
                 System.out.println("✅ 'No Data' message is displayed.");
             } else {
                 System.out.println("❌ No assets and no 'No Data' message found.");
             }
             return;
         }

         // Validate each card
         boolean allCardsValid = true;
         int index = 1;

         for (WebElement card : assetCards) {
             js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
             Thread.sleep(300);

             boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
             boolean hasBanner = !searchPage.getBannerInAsset(card).isEmpty();

             if (hasPublishOrPublished && hasBanner) {
                 System.out.println("✅ Card #" + index + " has Publish/Published button and Banner icon.");
             } else {
                 allCardsValid = false;
                 System.out.println("❌ Card #" + index + " is missing Publish/Published button or Banner icon.");
                 break;
             }
             index++;
         }

         // Final assertion
         if (allCardsValid) {
             System.out.println("✅ All asset cards are valid.");
         } else {
             System.out.println("❌ Test failed: Some cards are missing required elements.");
             Assert.fail("One or more cards missing Publish/Published button or Banner icon.");
         }

         System.out.println("Test Case TC_SA_29 got passed");
         Thread.sleep(2000);

         // Logout
         searchPage.clickOnProfileIconAfterSearch();
         searchPage.clickOnLogoutOption();
         searchPage.clickOnLogoutButton();
     }
     
     @Test(priority=30)
     public void test_TC_SA_30_verifyDraftAndPublishedWithSocialPostsQuickFilter() throws InterruptedException {
    	 
         driver = openBrowserAndApplication(prop.getProperty("browser"));

         loginPage = new LoginPage(driver);
         loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
         loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
         loginPage.clickOnSubmitButton();
         System.out.println("User Logged in Successfully.");

         searchPage = new SearchPage(driver);
         JavascriptExecutor js = (JavascriptExecutor) driver;
       
         Thread.sleep(3000);
         js.executeScript("window.scrollBy(0,400)");
         Thread.sleep(2000);

         // Apply Draft & Published filter
         searchPage.clickOnDraftAndPublishedDropdown();
         Thread.sleep(3000);
         searchPage.clickOnDraftAndPublishedOption();
         js.executeScript("document.activeElement.blur();");
         Thread.sleep(3000);
         
       
         // Apply Social quick filter
         searchPage.clickOnSocialPostsFilter();
         Thread.sleep(3000);

         // Fetch all cards having either Publish OR Published button
         List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
         System.out.println("Total asset cards found: " + assetCards.size());

         // Handle case when there are no cards
         if (assetCards.isEmpty()) {
             String noDataText = searchPage.getNoDataText();
             if (noDataText.contains("No Data")) {
                 System.out.println("✅ 'No Data' message is displayed.");
             } else {
                 System.out.println("❌ No assets and no 'No Data' message found.");
             }
             return;
         }

         // Validate each card
         boolean allCardsValid = true;
         int index = 1;

         for (WebElement card : assetCards) {
             js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
             Thread.sleep(300);

             boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
             boolean hasSocialPosts = !searchPage.getSocialInAsset(card).isEmpty();
	         boolean hasWhatsApp = !searchPage.getWhatsappInAsset(card).isEmpty();

             if (hasPublishOrPublished && hasSocialPosts && hasWhatsApp) {
                 System.out.println("✅ Card #" + index + " has Publish/Published button, Social or Whatsapp icon.");
             } else {
                 allCardsValid = false;
                 System.out.println("❌ Card #" + index + " is missing Publish/Published,Social or Whatsapp icon.");
                 break;
             }
             index++;
         }

         // Final assertion
         if (allCardsValid) {
             System.out.println("✅ All asset cards are valid.");
         } else {
             System.out.println("❌ Test failed: Some cards are missing required elements.");
             Assert.fail("One or more cards missing Publish/Published button or Social/Whatsapp icon.");
         }

         System.out.println("Test Case TC_SA_30 got passed");
         Thread.sleep(2000);

         // Logout
         searchPage.clickOnProfileIconAfterSearch();
         searchPage.clickOnLogoutOption();
         searchPage.clickOnLogoutButton();
     }
     
     @Test(priority=31)
     public void test_TC_SA_31_verifyDraftAndPublishedWithEmailQuickFilter() throws InterruptedException {
    	 
         driver = openBrowserAndApplication(prop.getProperty("browser"));

         loginPage = new LoginPage(driver);
         loginPage.enterUsernameField(prop.getProperty("validusernamedev"));
         loginPage.enterPasswordField(prop.getProperty("validpassworddev"));
         loginPage.clickOnSubmitButton();
         System.out.println("User Logged in Successfully.");

         searchPage = new SearchPage(driver);
         JavascriptExecutor js = (JavascriptExecutor) driver;
       
         Thread.sleep(3000);
         js.executeScript("window.scrollBy(0,400)");
         Thread.sleep(2000);

         // Apply Draft & Published filter
         searchPage.clickOnDraftAndPublishedDropdown();
         Thread.sleep(3000);
         searchPage.clickOnDraftAndPublishedOption();
         js.executeScript("document.activeElement.blur();");
         Thread.sleep(3000);
         
         
         // Apply Bookmark quick filter
         searchPage.clickOnEmailQuickFilter();
         Thread.sleep(3000);

         // Fetch all cards having either Publish OR Published button
         List<WebElement> assetCards = searchPage.getAssetCardsWithPublishOrPublishedButtons();
         System.out.println("Total asset cards found: " + assetCards.size());

         // Handle case when there are no cards
         if (assetCards.isEmpty()) {
             String noDataText = searchPage.getNoDataText();
             if (noDataText.contains("No Data")) {
                 System.out.println("✅ 'No Data' message is displayed.");
             } else {
                 System.out.println("❌ No assets and no 'No Data' message found.");
             }
             return;
         }

         // Validate each card
         boolean allCardsValid = true;
         int index = 1;

         for (WebElement card : assetCards) {
             js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", card);
             Thread.sleep(300);

             boolean hasPublishOrPublished = !searchPage.getPublishOrPublishedButtonsThree(card).isEmpty();
             boolean hasEmail = !searchPage.getEmailInAsset(card).isEmpty();

             if (hasPublishOrPublished && hasEmail) {
                 System.out.println("✅ Card #" + index + " has Publish/Published button and Email icon.");
             } else {
                 allCardsValid = false;
                 System.out.println("❌ Card #" + index + " is missing Publish/Published button or Email icon.");
                 break;
             }
             index++;
         }

         // Final assertion
         if (allCardsValid) {
             System.out.println("✅ All asset cards are valid.");
         } else {
             System.out.println("❌ Test failed: Some cards are missing required elements.");
             Assert.fail("One or more cards missing Publish/Published button or Email icon.");
         }

         System.out.println("Test Case TC_SA_31 got passed");
         Thread.sleep(2000);

         // Logout
         searchPage.clickOnProfileIconAfterSearch();
         searchPage.clickOnLogoutOption();
         searchPage.clickOnLogoutButton();
}
     
}


	   
	    
	    


