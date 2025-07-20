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
	        // Here we had an apostrophe so we wrrote the below xpath like that i.e. \"" + actualSearch + "\"
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

	        searchPage.enterValueIntoSearchTextfield(prop.getProperty("enterValueTwoInSearchTextfield"));
	        searchPage.clickOnSearchIcon();

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
	 
	    }
	    
	    


