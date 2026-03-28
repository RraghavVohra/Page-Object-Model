package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private WebElement usernameField;
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
			} catch (StaleElementReferenceException e) {
				return false;
			}
		});
	}

	public void enterPasswordField(String passwordText) {

		passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		passwordField.sendKeys(passwordText);
	}
	
	public void clickOnSubmitButton() {
		
		// submitButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='submit'])[1]")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll into view to ensure no overlays are blocking it
		js.executeScript("arguments[0].scrollIntoView(true);", submitButton);

		submitButton.click();
		
	}
	
}

