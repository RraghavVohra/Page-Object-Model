package pageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SocialAutoPostPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	 
	 public SocialAutoPostPage(WebDriver driver) {
			
			this.driver=driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		 
		 actionsButton = driver.findElement(By.xpath("//div[contains(@class, 'btn-group')]//a[@data-bs-toggle='dropdown']//*[name()='svg']"));
		 Actions actions = new Actions(driver);
		 actions.moveToElement(actionsButton).click().perform();
		 
	 }
	 
	 public void clickOnCreatePostButton() {
		 
		 createPostButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Create Post']")));
		 createPostButton.click();
	 }
	 
	 public void uploadFileInPNGUsingAutoIt() throws IOException, InterruptedException {
	       
	        fileInput = driver.findElement(By.id("file-upload"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(fileInput).click().perform();

	        Thread.sleep(3000); // Replace with WebDriverWait for real-world usage

	        File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\Uploadfiletwo.exe");
	        if (!autoItScript.exists()) {
	            System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	        } else {
	            ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	            Process process = processBuilder.start();
	            int exitCode = process.waitFor();
	            System.out.println("AutoIt script exited with code: " + exitCode);
	        }
	    }
	 


	 public void uploadFileInJPGUsingAutoIt() throws IOException, InterruptedException {
	       
	        fileInput = driver.findElement(By.id("file-upload"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(fileInput).click().perform();

	        Thread.sleep(3000); // Replace with WebDriverWait for real-world usage

	        File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pushnotificationjpg.exe");
	        if (!autoItScript.exists()) {
	            System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	        } else {
	            ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	            Process process = processBuilder.start();
	            int exitCode = process.waitFor();
	            System.out.println("AutoIt script exited with code: " + exitCode);
	        }
	    }
	 
	 
	 public void uploadFileInMP4UsingAutoIt() throws IOException, InterruptedException {
	       
	        fileInput = driver.findElement(By.id("file-upload"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(fileInput).click().perform();

	        Thread.sleep(3000); // Replace with WebDriverWait for real-world usage

	        File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pushnotificationvideo.exe");
	        if (!autoItScript.exists()) {
	            System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	        } else {
	            ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	            Process process = processBuilder.start();
	            int exitCode = process.waitFor();
	            System.out.println("AutoIt script exited with code: " + exitCode);
	        }
	    }
	 
	 public void uploadFileForMP4FileWithThumbnailInJPGformat() throws InterruptedException, IOException {
		 
		    uploadThumbnail = driver.findElement(By.xpath("//input[@id='social_tumbnail']"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(uploadThumbnail).click().perform();

	        Thread.sleep(3000); // Replace with WebDriverWait for real-world usage

	        File autoItScript = new File("C:\\Users\\admin\\Desktop\\EXE FILES\\pushnotificationjpg.exe");
	        if (!autoItScript.exists()) {
	            System.out.println("AutoIt script not found: " + autoItScript.getAbsolutePath());
	        } else {
	            ProcessBuilder processBuilder = new ProcessBuilder(autoItScript.getAbsolutePath());
	            Process process = processBuilder.start();
	            int exitCode = process.waitFor();
	            System.out.println("AutoIt script exited with code: " + exitCode);
	        }
		 
	 }
	 
	 
	 
	 
	 
	 
	public void clickOnEnableCobrandingButton() {
		
		enableCoBrandingButton = driver.findElement(By.xpath("//input[@id='videobrand']"));		 
		enableCoBrandingButton.click();
	}
	
	public void enterInTitleTextfield(String Titlename) {
		
		titleTextfield = driver.findElement(By.xpath("//input[@name='title' and @id='title']"));
		titleTextfield.sendKeys(Titlename);
	}
	
	public void enterValueInDescriptionTextfield(String Description) {
		
		descriptionTextfield = driver.findElement(By.xpath("//textarea[@id='description_link']"));
		descriptionTextfield.sendKeys(Description);
	}
	
	public void clickOnPartnerCategoryButton() {
		
		parnterCategoryButton = driver.findElement(By.xpath("//button[contains(@class,'multiselect') and contains(@class,'dropdown-toggle')]"));
		parnterCategoryButton.click();
	}
	
	public void clickOnSelectPartnerCategory() {
		
		selectPartnerCategory = driver.findElement(By.xpath("//label[normalize-space()='Raj2024']"));
		selectPartnerCategory.click();
	}
	
	public void clickOnStaticText() {
		
		staticText = driver.findElement(By.xpath("//span[@class='multiselect-selected-text']"));	
		staticText.click();
	}
	
	public void clickOnTwitter() {
		
		twitterCheckbox = driver.findElement(By.xpath("//label[input[@value='twitter']]"));
		twitterCheckbox.click();
	}
	
	public void clickOnLinkedIn() {
		
		linkedinCheckbox = driver.findElement(By.xpath("//label[input[@value='linkedin']]")); 
		linkedinCheckbox.click();
	}
	
	public void clickOnFacebook() {
		
		FacebookCheckbox = driver.findElement(By.xpath("//label[input[@value='facebook']]")); 
		FacebookCheckbox.click();
	}
	
	
	
	public void ClickOnOpenDateTimePicker() throws InterruptedException {
        dateTimeButton = driver.findElement(By.xpath("//input[contains(@class, 'form_datetime')]"));
        dateTimeButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dateTimeButton);
        js.executeScript("arguments[0].focus();", dateTimeButton);
        Thread.sleep(7000);  // Ideally replace with WebDriverWait
    }
	
	
	// Methods for Date & Time Selection
	public void selectFutureDate(String expectedDay, String expectedMonthYear) {
				    int maxAttempts = 12; // Prevent infinite loop
				    int attempt = 0;
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

				    while (attempt < maxAttempts) { 
				        String displayedMonthYear = driver.findElement(By.xpath("//div[contains(@class,'xdsoft_label')]/span")).getText().trim();
				        System.out.println("Currently displayed month & year: " + displayedMonthYear);

				        if (displayedMonthYear.equals(expectedMonthYear)) {
				            try {
				                // Wait for the correct date to be visible
				                WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@class,'xdsoft_date') and @data-date='" + expectedDay + "']")));
				                
				                // Scroll into view and click the date
				                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateElement);
				                dateElement.click();
				                
				                // Small delay to ensure time picker appears
				                Thread.sleep(2000);
				                
				                break;
				            } catch (Exception e) {
				                throw new RuntimeException("Could not find or select the date: " + expectedDay + " in " + expectedMonthYear);
				            }
				        } else {
				            try {
				                // Click the "Next" button to go to the next month
				                WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'xdsoft_next')]")));
				                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
				                nextButton.click();

				            } catch (Exception e) {
				                System.out.println("Next button not clickable, trying JavaScript click...");
				                WebElement nextButton = driver.findElement(By.xpath("//th[@class='next']"));
				                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
				            }
				        }
				        attempt++; 
				    }

				    if (attempt >= maxAttempts) {
				        throw new RuntimeException("Exceeded max attempts. Could not find the correct month: " + expectedMonthYear);
				    }
				}
	
	
	public void selectFutureDateTwo(String expectedDay, String expectedMonthYear) {
	    int maxAttempts = 24; // allow up to 2 years of navigation
	    int attempt = 0;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    while (attempt < maxAttempts) {
	        // Read current month and year separately
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
	                Thread.sleep(1000);
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
	                Thread.sleep(500);
	            } catch (Exception e) {
	                throw new RuntimeException("Next button not found or not clickable", e);
	            }
	        }
	        attempt++;
	    }
	    throw new RuntimeException("Exceeded max attempts. Could not find the correct month: " + expectedMonthYear);
	}


			 
			 public void selectHour(String expectedHour) {
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

				    try {
				        // Wait for the hour picker to be visible
				        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'datetimepicker-hours') and contains(@style, 'display: block')]")));
				        
				        // Select the correct hour
				        WebElement hourElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hour') and text()='" + expectedHour + "']")));
				        hourElement.click();
				    } catch (Exception e) {
				        throw new RuntimeException("Could not find or select the hour: " + expectedHour);
				    }
				}
			 
			 public void selectHourTwo(String expectedHour) {
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				    try {
				        // Example: expectedHour = "8" or "08"
				        int hourInt = Integer.parseInt(expectedHour);
				        
				        // Wait until the time picker is visible
				        wait.until(ExpectedConditions.visibilityOfElementLocated(
				            By.cssSelector(".xdsoft_timepicker.active .xdsoft_time_variant")
				        ));

				        // Locate element by data-hour
				        WebElement hourElement = wait.until(ExpectedConditions.elementToBeClickable(
				            By.xpath("//div[@class='xdsoft_time' and @data-hour='" + hourInt + "']")
				        ));

				        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hourElement);
				        hourElement.click();
				    } catch (Exception e) {
				        throw new RuntimeException("Could not find or select the hour: " + expectedHour, e);
				    }
				}


			 
			 
			 public void selectMinute(String expectedHour, String expectedMinute) {
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

				    try {
				        // Wait until the time picker is visible
				        WebElement timePicker = wait.until(ExpectedConditions.visibilityOfElementLocated(
				            By.xpath("//div[contains(@class, 'datetimepicker')]")));

				        // Create the full time format (e.g., "8:30")
				        String fullTime = expectedHour + ":" + expectedMinute;

				        // Locate and select the correct time option
				        WebElement timeElement = wait.until(ExpectedConditions.elementToBeClickable(
				            By.xpath("//span[normalize-space()='" + fullTime + "']")));
				        timeElement.click();

				    } catch (Exception e) {
				        throw new RuntimeException("Could not find or select the time: " + expectedHour + ":" + expectedMinute);
				    }
				}
			 
			 public void selectMinuteTwo(String expectedHour, String expectedMinute) {
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				    try {
				        int hourInt = Integer.parseInt(expectedHour);
				        int minuteInt = Integer.parseInt(expectedMinute);

				        wait.until(ExpectedConditions.visibilityOfElementLocated(
				            By.cssSelector(".xdsoft_timepicker.active .xdsoft_time_variant")
				        ));

				        WebElement timeElement = wait.until(ExpectedConditions.elementToBeClickable(
				            By.xpath("//div[@class='xdsoft_time' and @data-hour='" + hourInt + "' and @data-minute='" + minuteInt + "']")
				        ));

				        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", timeElement);
				        timeElement.click();
				    } catch (Exception e) {
				        throw new RuntimeException("Could not find or select the time: " + expectedHour + ":" + expectedMinute, e);
				    }
				}
			 
			 public void selectTimeThree(String hour, String minute) {
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				    JavascriptExecutor js = (JavascriptExecutor) driver;

				    try {
				        // First, wait for ANY time options to appear
				        wait.until(ExpectedConditions.presenceOfElementLocated(
				            By.cssSelector("div.xdsoft_time")
				        ));

				        // Now build the exact XPath for hour + minute
				        String xpath = "//div[contains(@class,'xdsoft_time') and @data-hour='" + hour + "' and @data-minute='" + minute + "']";

				        WebElement timeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

				        // Scroll into view (important if it's outside visible area)
				        js.executeScript("arguments[0].scrollIntoView(true);", timeElement);

				        // Ensure clickable before clicking
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
				 
				 schedulePostButton = driver.findElement(By.xpath("//button[@id='share_button']"));
			     schedulePostButton.click();
			 }
			 
			 
			 public void clickOnProfileIcon() {
				 
				 profileIcon = driver.findElement(By.xpath("//i[@class='fa fa-user-circle']"));
				 profileIcon.click();
			 }
			 
			 public void clickOnLogoutOption() {
				 
				 logOutOption = driver.findElement(By.xpath("//a[normalize-space()='Log Out']"));
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 js.executeScript("arguments[0].click();", logOutOption);
			 }
			 
			 public void clickOnLogoutButton() {
				 
				 logoutButton = driver.findElement(By.xpath("//a[@class='btn btn-primary' and text()='Yes']"));
				 logoutButton.click();
			 }
			 
			 
			 
	
	
	
	
	
	
	
	
	 
	 
	 
	 
	 
	 

}
