package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	private WebElement usernameField;
	private WebElement passwordField;
	private WebElement submitButton;
	
	public void enterUsernameField(String usernameText) {
		
		usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys(usernameText);
	}
	
	public void enterPasswordField(String passwordText) {
		
		passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys(passwordText);
	}
	
	public void clickOnSubmitButton() {
		
		submitButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		submitButton.click();
		
	}
	
}

