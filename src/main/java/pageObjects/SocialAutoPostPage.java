package pageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SocialAutoPostPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public SocialAutoPostPage(WebDriver driver) {

		this.driver = driver;
		// Increased from 10s to 20s to handle slower responses
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private WebElement automationTab;
	private WebElement socialOption;
	private WebElement autoPostTab;
	private WebElement actionsButton;
	private WebElement createPostButton;
	private WebElement fileInput;
	private WebElement enableCoBrandingButton;
	private WebElement titleTextfield;
	private WebElement descriptionTextfield;
	private WebElement parnterCategoryButton;
	private WebElement selectPartnerCategory;
	private WebElement staticText;
	private WebElement dateTimeButton;
	private WebElement dateInput;
	private WebElement schedulePostButton;
	private WebElement profileIcon;
	private WebElement logOutOption;
	private WebElement logoutButton;
	private WebElement uploadThumbnail;
	private WebElement twitterCheckbox;
	private WebElement linkedinCheckbox;
	private WebElement FacebookCheckbox;
	private WebElement customUrl;
	private WebElement customlink;


	public void clickOnAutomationTab() {

		automationTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Automation']")));
		automationTab.click();
	}

	public void clickOnSocialOption() {

		socialOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Social']")));
		socialOption.click();
	}

	public void clickOnAutoPostTab() {

		autoPostTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Auto Post']")));
		autoPostTab.click();
	}

	public void clickOnActionsButton() {

		// Targeting parent <a> instead of SVG child — elementToBeClickable on SVG can hang
		actionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'btn-group')]//a[@data-bs-toggle='dropdown']")));
		Actions actions = new Actions(driver);
		actions.moveToElement(actionsButton).click().perform();
	}

	public void clickOnCreatePostButton() {

		createPostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Create Post']")));
		createPostButton.click();
		// No post-wait — original code had no wait here and file upload worked correctly
	}

	public void clickOnCustomURLRadioButton() {

		// Upgraded to elementToBeClickable — radio button may not be interactive immediately
		customUrl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @id='C']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", customUrl);
	}

	public void enterCustomURL() {

		// Upgraded to elementToBeClickable — input only appears after selecting Custom URL radio
		customlink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='custom_url']")));
		customlink.sendKeys("https://www.salespanda.com");
	}

	public void uploadFileInPNGUsingAutoIt(String filePath) throws IOException {

		// Switched from AutoIt + .click() to sendKeys — Chrome 146 blocks programmatic .click() on file inputs (InvalidArgumentException)
		// sendKeys sets the file path directly on the hidden input without needing the OS dialog
		fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
		fileInput.sendKeys(filePath);

		// Old approach commented out — AutoIt + .click() no longer works on Chrome 146
		// fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].style.display='block'; arguments[0].style.opacity='1'; arguments[0].style.visibility='visible';", fileInput);
		// fileInput.click();
		// Thread.sleep(3000); // OS dialog
		// File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\Uploadfiletwoo.exe");
		// if (!autoItScript.exists()) {
		//     System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
		// } else {
		//     ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
		//     Process process = processBuilder.start();
		//     int exitCode = process.waitFor();
		//     System.out.println("AutoIt script exited with code: " + exitCode);
		// }

		// No post-wait here — clickOnEnableCobrandingButton() already waits for videobrand
	}

	public void uploadFileInJPGUsingAutoIt(String filePath) throws IOException {

		// Switched from AutoIt + .click() to sendKeys — Chrome 146 blocks programmatic .click() on file inputs (InvalidArgumentException)
		// sendKeys sets the file path directly on the hidden input without needing the OS dialog
		fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
		fileInput.sendKeys(filePath);

		// Old approach commented out — AutoIt + .click() no longer works on Chrome 146
		// fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].style.display='block'; arguments[0].style.opacity='1'; arguments[0].style.visibility='visible';", fileInput);
		// fileInput.click();
		// Thread.sleep(3000); // OS dialog
		// File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pushnotificationjpg.exe");
		// if (!autoItScript.exists()) {
		//     System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
		// } else {
		//     ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
		//     Process process = processBuilder.start();
		//     int exitCode = process.waitFor();
		//     System.out.println("AutoIt script exited with code: " + exitCode);
		// }

		// No post-wait here — clickOnEnableCobrandingButton() already waits for videobrand
	}

	public void uploadFileInMP4UsingAutoIt(String filePath) throws IOException {

		// Switched from AutoIt + .click() to sendKeys — Chrome 146 blocks programmatic .click() on file inputs (InvalidArgumentException)
		// sendKeys sets the file path directly on the hidden input without needing the OS dialog
		fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
		fileInput.sendKeys(filePath);

		// Old approach commented out — AutoIt + .click() no longer works on Chrome 146
		// fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].style.display='block'; arguments[0].style.opacity='1'; arguments[0].style.visibility='visible';", fileInput);
		// fileInput.click();
		// Thread.sleep(3000); // OS dialog
		// File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pushnotificationvideo.exe");
		// if (!autoItScript.exists()) {
		//     System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
		// } else {
		//     ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
		//     Process process = processBuilder.start();
		//     int exitCode = process.waitFor();
		//     System.out.println("AutoIt script exited with code: " + exitCode);
		// }

		// No post-wait here — uploadFileForMP4FileWithThumbnailInJPGformat() uses presenceOfElementLocated for social_tumbnail
	}

	public void uploadFileForMP4FileWithThumbnailInJPGformat(String filePath) throws IOException {

		// Switched from AutoIt + .click() to sendKeys — Chrome 146 blocks programmatic .click() on file inputs (InvalidArgumentException)
		// sendKeys sets the file path directly on the hidden input without needing the OS dialog
		uploadThumbnail = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='social_tumbnail']")));
		uploadThumbnail.sendKeys(filePath);

		// Old approach commented out — AutoIt + .click() no longer works on Chrome 146
		// uploadThumbnail = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='social_tumbnail']")));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].style.display='block'; arguments[0].style.opacity='1'; arguments[0].style.visibility='visible';", uploadThumbnail);
		// uploadThumbnail.click();
		// Thread.sleep(3000); // OS dialog
		// File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pushnotificationjpg.exe");
		// if (!autoItScript.exists()) {
		//     System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
		// } else {
		//     ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
		//     Process process = processBuilder.start();
		//     int exitCode = process.waitFor();
		//     System.out.println("AutoIt script exited with code: " + exitCode);
		// }

		// No post-wait here — clickOnEnableCobrandingButton() already waits for videobrand
	}


	public void clickOnEnableCobrandingButton() {

		// Upgraded to elementToBeClickable + JS click — button needs to be interactive after upload
		enableCoBrandingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='videobrand']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", enableCoBrandingButton);
	}

	public void enterInTitleTextfield(String Titlename) {

		// Upgraded to elementToBeClickable — field must be ready for input
		titleTextfield = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='title' and @id='title']")));
		titleTextfield.sendKeys(Titlename);
	}

	public void enterValueInDescriptionTextfield(String Description) {

		// Upgraded to elementToBeClickable — textarea must be ready for input
		descriptionTextfield = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='description_link']")));
		descriptionTextfield.sendKeys(Description);
	}

	public void clickOnPartnerCategoryButton() {

		// Upgraded to elementToBeClickable — multiselect may not be ready immediately
		parnterCategoryButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='multiselect-wrapper']//div[contains(@class,'multiselect')])[1]")));
		parnterCategoryButton.click();
		// Post-wait: confirm dropdown options appeared before selecting
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Raj2024']")));
	}

	public void clickOnSelectPartnerCategory() {

		// Upgraded to elementToBeClickable — option must be visible in dropdown
		selectPartnerCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Raj2024']")));
		// Actions click — fires full event chain (mousedown + mouseup + click)
		new Actions(driver).click(selectPartnerCategory).perform();
		// Removed post-wait for multiselect-selected-text span — span is hidden while dropdown stays open (multi-select does not auto-close)
		// clickOnStaticText() handles closing the dropdown
	}

	public void clickOnStaticText() {

		// Switched to Escape key to close the multiselect dropdown — document.body.click() did not trigger the dropdown's outside-click handler
		// Old approach commented out: js.executeScript("document.body.click()");
		// Old approach commented out: staticText = wait.until(visibilityOfElementLocated(//span[@class='multiselect-selected-text'])); js.executeScript("arguments[0].click()", staticText);
		new Actions(driver).sendKeys(Keys.ESCAPE).perform();
		// Post-wait: Twitter checkbox clickable — confirms dropdown is closed and form is ready
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[input[@value='twitter']]")));
	}

	public void clickOnTwitter() {

		// Upgraded to elementToBeClickable
		twitterCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[input[@value='twitter']]")));
		twitterCheckbox.click();
	}

	public void clickOnLinkedIn() {

		// Upgraded to elementToBeClickable
		linkedinCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[input[@value='linkedin']]")));
		linkedinCheckbox.click();
	}

	public void clickOnFacebook() {

		// Upgraded to elementToBeClickable
		FacebookCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[input[@value='facebook']]")));
		FacebookCheckbox.click();
	}

	public void clickOnNoneRadioButton() {

		// Upgraded to elementToBeClickable
		customUrl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @id='N']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", customUrl);
	}


	public void ClickOnOpenDateTimePicker() throws InterruptedException {

		// Upgraded to elementToBeClickable
		dateTimeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class, 'form_datetime')]")));
		dateTimeButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", dateTimeButton);
		js.executeScript("arguments[0].focus();", dateTimeButton);
		// Replaced Thread.sleep(7000) — wait for the date picker calendar to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.xdsoft_datepicker")));
	}


	public void selectFutureDateTwo(String expectedDay, String expectedMonthYear) {
		int maxAttempts = 24; // allow up to 2 years of navigation
		int attempt = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		while (attempt < maxAttempts) {
			String displayedMonth = driver.findElement(
				By.xpath("//div[@class='xdsoft_label xdsoft_month']/span")
			).getText().trim();
			String displayedYear = driver.findElement(
				By.xpath("//div[@class='xdsoft_label xdsoft_year']/span")
			).getText().trim();
			String displayedMonthYear = displayedMonth + " " + displayedYear;
			System.out.println("Currently displayed month & year: " + displayedMonthYear);

			if (displayedMonthYear.equals(expectedMonthYear)) {
				try {
					WebElement dateElement = wait.until(
						ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//td[contains(@class,'xdsoft_date') and @data-date='" + expectedDay + "' and not(contains(@class,'xdsoft_disabled'))]")
						)
					);
					js.executeScript("arguments[0].scrollIntoView(true);", dateElement);
					dateElement.click();
					// Replaced Thread.sleep(1000) — wait for datetime input to be populated after date click
					wait.until(d -> {
						String val = d.findElement(By.xpath("//input[contains(@class, 'form_datetime')]")).getDomProperty("value");
						return val != null && !val.isEmpty();
					});
					return; // done
				} catch (Exception e) {
					throw new RuntimeException("Could not find or select the date: " + expectedDay + " in " + expectedMonthYear, e);
				}
			} else {
				try {
					WebElement nextButton = wait.until(
						ExpectedConditions.elementToBeClickable(
							By.xpath("//button[contains(@class,'xdsoft_next')]")
						)
					);
					js.executeScript("arguments[0].scrollIntoView(true);", nextButton);
					nextButton.click();
					Thread.sleep(500); // Calendar month-change animation — cannot use WebDriverWait
				} catch (Exception e) {
					throw new RuntimeException("Next button not found or not clickable", e);
				}
			}
			attempt++;
		}
		throw new RuntimeException("Exceeded max attempts. Could not find the correct month: " + expectedMonthYear);
	}


	public void selectTimeThree(String hour, String minute) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("div.xdsoft_time")
			));

			String xpath = "//div[contains(@class,'xdsoft_time') and @data-hour='" + hour + "' and @data-minute='" + minute + "']";

			WebElement timeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

			js.executeScript("arguments[0].scrollIntoView(true);", timeElement);

			wait.until(ExpectedConditions.elementToBeClickable(timeElement)).click();

		} catch (Exception e) {
			throw new RuntimeException("Could not find or select the time: " + hour + ":" + minute, e);
		}
	}


	public void verifySelection() {
		dateInput = driver.findElement(By.xpath("//input[contains(@class, 'form_datetime')]"));
		System.out.println("Selected Date & Time: " + dateInput.getDomProperty("value"));
	}


	// 🚀 Call the date/time selection methods
	//   socialautopost.selectFutureDate("27", "March 2025");
	//   socialautopost.selectHour("8");
	//   socialautopost.selectMinute("8", "30");
	//   socialautopost.verifySelection();


	public void clickOnSchedulePostButton() {

		// Upgraded to elementToBeClickable — button must be ready before submission
		schedulePostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='share_button']")));
		schedulePostButton.click();
	}


	public void clickOnProfileIcon() {

		// Upgraded to elementToBeClickable — wait for page to be stable after scheduling
		profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-user-circle']")));
		profileIcon.click();
	}

	public void clickOnLogoutOption() {

		// Upgraded to elementToBeClickable — dropdown menu must appear before clicking
		logOutOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Log Out']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", logOutOption);
	}

	public void clickOnLogoutButton() {

		// Upgraded to elementToBeClickable — confirmation modal must appear
		logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-primary' and text()='Yes']")));
		logoutButton.click();
	}


}
