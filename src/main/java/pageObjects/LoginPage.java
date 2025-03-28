package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public void enterUsernameField(String usernameText) throws InterruptedException {
		
		Thread.sleep(3000);
		usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		usernameField.sendKeys(usernameText);
	}
	
	public void enterPasswordField(String passwordText) {
		
		passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
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

