package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WHY this class exists:
 * Every page object was creating its own WebDriverWait with different timeouts.
 * Centralizing waits means: one place to change timeout, consistent behavior,
 * and reusable methods that replace Thread.sleep throughout the framework.
 *
 * HOW to use:
 * WaitUtils.waitForElementClickable(driver, By.id("submit"))
 * WaitUtils.waitForElementVisible(driver, By.xpath("//div[@class='toast']"))
 */
public class WaitUtils {

    // Default timeout for most UI interactions
    private static final int DEFAULT_TIMEOUT = 15;

    // Longer timeout for file uploads, page loads, slow operations
    private static final int LONG_TIMEOUT = 30;

    // Short timeout for quick checks (is element present? is toast visible?)
    private static final int SHORT_TIMEOUT = 5;

    private static WebDriverWait getWait(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /**
     * WHY: Use when you want to click something.
     * elementToBeClickable checks it's visible AND enabled AND not covered by overlay.
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        return getWait(driver, timeoutSeconds)
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * WHY: Use when you want to read text or verify an element is shown.
     * visibilityOfElementLocated checks element is in DOM AND has non-zero size.
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        return getWait(driver, timeoutSeconds)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * WHY: Use when you need the element in the DOM but it might be hidden.
     * Example: hidden file input fields for upload.
     */
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
            .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * WHY: Replaces Thread.sleep after file uploads or operations that take time.
     * Waits for a loading spinner/overlay to disappear rather than sleeping blindly.
     * HOW: Pass the locator of your loading spinner. Method returns when it's gone.
     */
    public static void waitForElementToDisappear(WebDriver driver, By locator) {
        try {
            getWait(driver, LONG_TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            // Element never appeared or already gone — both are fine, continue
        }
    }

    /**
     * WHY: Replaces Thread.sleep for page loads.
     * Checks document.readyState == 'complete' before proceeding.
     * HOW: Call this after any navigation or form submission.
     */
    public static void waitForPageLoad(WebDriver driver) {
        getWait(driver, LONG_TIMEOUT).until(webDriver ->
            ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete")
        );
    }

    /**
     * WHY: Replaces Thread.sleep after actions that show toast/alert messages.
     * Waits for specific text to appear somewhere on the page.
     */
    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        try {
            return getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * WHY: Replaces Thread.sleep for URL verification after navigation.
     */
    public static boolean waitForUrlToContain(WebDriver driver, String urlFragment) {
        try {
            return getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.urlContains(urlFragment));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * WHY: Replaces Thread.sleep for list-based assertions.
     * Waits until at least N elements matching a locator are present.
     */
    public static List<WebElement> waitForAllElements(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * WHY: Safe check — returns true/false instead of throwing exception.
     * Use this for conditional logic: if element is present, do X, else do Y.
     * Replaces try-catch around findElement throughout the codebase.
     */
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            getWait(driver, SHORT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * WHY: Replaces Thread.sleep for stale element issues.
     * When Angular/React re-renders a component, the old WebElement reference
     * becomes stale. This waits for the element to be fresh and clickable again.
     */
    public static WebElement waitForElementStalenessAndRefresh(WebDriver driver,
            WebElement staleElement, By locator) {
        getWait(driver, DEFAULT_TIMEOUT)
            .until(ExpectedConditions.stalenessOf(staleElement));
        return waitForElementClickable(driver, locator);
    }
}
