package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	// Below line of code was added as in some test cases, Selenium was not able to find the element
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		// Below line of code was added as in some test cases, selenium was not able to find the element
		// Increased from 15s to 30s — slow server days caused the submit-button lambda to time out before login completed
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	private WebElement usernameField;
	private WebElement userIdField;
	private WebElement passwordField;
	private WebElement submitButton;
	
	public void enterUsernameField(String usernameText) {
		// Retry until the value sticks — Angular may re-render the field after it first appears
		wait.until(driver -> {
			try {
				WebElement usernameField = driver.findElement(By.id("username"));
				if (!usernameField.isEnabled()) return false;
				usernameField.clear();
				usernameField.sendKeys(usernameText);
				String value = usernameField.getDomProperty("value");
				return value != null && value.equals(usernameText);
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
				// StaleElementReferenceException: Angular re-rendered the field between find and sendKeys
				// ElementNotInteractableException: field is in DOM but page is still loading — retry until ready
				return false;
			}
		});
	}
	
	public void enterUserIdField(String usernameIdText) {
		// Retry until the value sticks — Angular may re-render the field after it first appears
		wait.until(driver -> {
			try {
				WebElement usernameField = driver.findElement(By.id("username"));
				if (!usernameField.isEnabled()) return false;
				usernameField.clear();
				usernameField.sendKeys(usernameIdText);
				String value = usernameField.getDomProperty("value");
				return value != null && value.equals(usernameIdText);
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
				// StaleElementReferenceException: Angular re-rendered the field between find and sendKeys
				// ElementNotInteractableException: field is in DOM but page is still loading — retry until ready
				return false;
			}
		});
	}

	public void enterPasswordField(String passwordText) {

		// Retry until the value sticks — same pattern as enterUsernameField()
		// Angular may re-render the form after username is entered, clearing the password field too
		// Old approach commented out (no retry — value could be cleared before submit):
		// passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		// passwordField.sendKeys(passwordText);
		wait.until(driver -> {
			try {
				WebElement passwordEl = driver.findElement(By.id("password"));
				if (!passwordEl.isEnabled()) return false;
				passwordEl.clear();
				passwordEl.sendKeys(passwordText);
				String value = passwordEl.getDomProperty("value");
				return value != null && value.equals(passwordText);
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
				return false;
			}
		});
	}
	
	public void clickOnSubmitButton() {

		// Retry loop to handle Angular re-rendering the submit button between find and click (StaleElementReferenceException)
		// Field values are guaranteed by enterUsernameField() and enterPasswordField() retry loops — no need to re-check here
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Removed isEnabled() check — Angular may disable the button temporarily during re-render even when fields are filled,
		// causing the lambda to keep returning false and time out. Just find and JS-click; the 30s urlContains wait below catches failure.
		// Old approach commented out:
		// wait.until(driver -> {
		//     try {
		//         WebElement btn = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		//         if (!btn.isDisplayed() || !btn.isEnabled()) return false;
		//         js.executeScript("arguments[0].scrollIntoView(true);", btn);
		//         js.executeScript("arguments[0].click();", btn);
		//         return true;
		//     } catch (StaleElementReferenceException e) {
		//         return false;
		//     }
		// });
		wait.until(driver -> {
			try {
				WebElement btn = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true);", btn);
				js.executeScript("arguments[0].click();", btn);
				return true;
			} catch (StaleElementReferenceException e) {
				// Angular re-rendered the form between find and click — retry
				return false;
			}
		});

		// Post-wait: wait for URL to contain "AssetLibrary" — confirms login succeeded and redirect completed
		// Login page URL is app.technochimes.com/home — it never contains "login", so urlContains("login") fired instantly and was useless
		// AssetLibrary is the landing page after successful login: app.technochimes.com/home/AssetLibrary
		// Using a separate 30s wait — the 15s form interaction wait is too tight for slow server days
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.urlContains("AssetLibrary"));

	}
	
}

