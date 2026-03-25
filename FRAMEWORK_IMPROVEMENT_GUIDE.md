# POM Framework Improvement Guide
## Phased Approach to Industry-Standard Selenium TestNG Framework

---

## CURRENT ISSUES IDENTIFIED

| Issue | Impact | Priority |
|-------|--------|----------|
| Broken screenshots in ExtentReport | Can't debug failures visually | CRITICAL |
| `Thread.sleep()` everywhere | Slow, unreliable, masks real issues | CRITICAL |
| 35-second implicit wait in Base.java | Hides real failures, slows test run | HIGH |
| Reflection to get driver in Listener | Fragile, breaks with inheritance changes | HIGH |
| No ThreadLocal driver management | Parallel tests share one driver instance | HIGH |
| Hardcoded file paths (`C:\Users\admin\...`) | Breaks on any other machine | MEDIUM |
| No retry mechanism | Flaky UI tests fail permanently | MEDIUM |
| No centralized logging | Hard to debug without logs | MEDIUM |
| AutoIt `.exe` files with absolute paths | Machine-specific, not portable | MEDIUM |

---

# PHASE 1 — CRITICAL FIXES
### Goal: Fix broken screenshots and eliminate Thread.sleep

---

## 1.1 — Fix Broken Screenshots in ExtentReport

### WHY this is broken:
`Utilities.captureScreenshot()` saves screenshots to an **absolute path** like:
```
C:\Users\admin\Workspace\PageObjectModel\screenshots\testName.png
```
When `MyListeners.java` calls `extentTest.addScreenCaptureFromPath(screenshotFilePath)`,
it passes this absolute path to ExtentReports. The report HTML is opened in a browser.
**Browsers cannot access absolute file system paths (`C:\...`)**. They only understand
relative paths or Base64 embedded images. That is why you see broken image icons.

### HOW to fix it — Two Options:

**Option A (Recommended): Embed screenshot as Base64 directly in the report**
- No file path dependency
- Image is stored inside the HTML report itself
- Works on any machine, any browser

**Option B: Use a relative path**
- Calculate the relative path from the report file to the screenshot
- Works but fragile if report or screenshot folder moves

### CODE CHANGES:

#### Change 1: Update `Utilities.java` — add Base64 screenshot method

**File:** `src/main/java/util/Utilities.java`

```java
package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

public class Utilities {

    /**
     * WHY: Captures screenshot and saves as PNG file.
     * Timestamp in filename prevents overwriting previous failures with same test name.
     * HOW: TakesScreenshot interface converts browser viewport to byte array,
     * FileUtils copies it to disk.
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotDir + fileName);
            destFile.getParentFile().mkdirs(); // create folder if it doesn't exist
            FileUtils.copyFile(srcFile, destFile);

            // Return RELATIVE path from report location to screenshot
            // Report is at: target/reports/ExtentReport.html
            // Screenshot is at: screenshots/fileName.png
            // Relative path from report to screenshot: ../../screenshots/fileName.png
            return "../../screenshots/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * WHY: Base64 embeds the image directly into the HTML report as a data URI.
     * This eliminates ALL path-related issues — no broken images, ever.
     * The report becomes fully self-contained — one HTML file has everything.
     * HOW: Screenshot bytes are converted to Base64 string. ExtentReports
     * accepts this via addScreenCaptureFromBase64String().
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * WHY: Centralizes properties loading so every class doesn't need its own
     * FileReader logic. Single point of failure = easier to fix.
     */
    public static Properties loadPropertiesFile() {
        Properties prop = new Properties();
        String path = System.getProperty("user.dir") + "/src/test/resources/projectdata.properties";
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    // --- Scroll Utilities (keep as-is, these are fine) ---

    public static void scrollToTop(WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public static void scrollDownByFiveHundred(WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
    }

    public static void scrollDownByFourHundred(WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
    }

    public static void scrollDownByTwoHundred(WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
    }

    public static void scrollToBottom(WebDriver driver) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "window.scrollTo(0, document.body.scrollHeight)");
    }
}
```

---

#### Change 2: Update `MyListeners.java` — use Base64 screenshot, remove Reflection

**File:** `src/main/java/listeners/MyListeners.java`

```java
package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.ExtentReporter;
import util.Utilities;

import java.awt.Desktop;
import java.io.File;

public class MyListeners implements ITestListener {

    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReporter.getExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.INFO, "Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed: " + result.getName());
    }

    /**
     * WHY we removed Reflection:
     * Using Field.getDeclaredField("driver") on the test class is fragile.
     * If you rename the field, change inheritance, or use ThreadLocal, it breaks.
     *
     * HOW we fixed it:
     * We use a DriverManager (ThreadLocal pattern — see Phase 2).
     * DriverManager.getDriver() always returns the correct driver for the current thread.
     * No reflection. No casting. Null-safe.
     *
     * WHY Base64 screenshot:
     * addScreenCaptureFromPath() with absolute paths = broken images in browser.
     * addScreenCaptureFromBase64String() embeds image directly in HTML.
     * The report is self-contained. Open it anywhere, images always show.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed: " + result.getName());
        extentTest.log(Status.INFO, result.getThrowable());

        // Get driver from DriverManager (no reflection needed)
        WebDriver driver = base.DriverManager.getDriver();

        if (driver != null) {
            String base64Screenshot = Utilities.captureScreenshotAsBase64(driver);
            if (base64Screenshot != null) {
                extentTest.addScreenCaptureFromBase64String(base64Screenshot,
                    "Failure Screenshot - " + result.getName());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        try {
            File extentReportFile = new File(
                System.getProperty("user.dir") + "/target/reports/ExtentReport.html");
            if (extentReportFile.exists()) {
                Desktop.getDesktop().browse(extentReportFile.toURI());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 1.2 — Fix Thread.sleep: Create a Proper Wait Utility

### WHY Thread.sleep is bad:
- `Thread.sleep(3000)` always waits the full 3 seconds, even if the element is ready in 0.5s.
  Across 100 tests, this wastes minutes.
- It does NOT verify anything. The element might still not be ready after the sleep ends.
- It makes test runs slow and unpredictable — the right sleep time changes with environment.

### WHY WebDriverWait is better:
- It polls every 500ms until condition is true or timeout is reached.
- If element is ready in 500ms, it proceeds immediately. No wasted time.
- It throws a clear `TimeoutException` with a message telling you exactly what it waited for.

### HOW to fix it — Create a `WaitUtils.java`:

**File:** `src/main/java/util/WaitUtils.java` (NEW FILE)

```java
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
```

---

## 1.3 — Fix the Implicit Wait Duration

**File:** `src/test/java/base/Base.java`

### WHY 35 seconds implicit wait is wrong:
- Implicit wait of 35s means every `findElement()` call that fails will wait 35 seconds
  before throwing `NoSuchElementException`. If you have 10 wrong locators in a test,
  that test takes 350 seconds to fail — nearly 6 minutes of wasted time.
- It interacts badly with explicit waits (WebDriverWait). When both are active,
  the behavior is unpredictable (Selenium documentation warns against mixing them).
- Best practice: Set implicit wait to 0, use ONLY explicit waits (WebDriverWait).

```java
// REMOVE this (Base.java lines with implicit wait):
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(35));

// REPLACE WITH:
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));   // Use explicit waits only
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // Keep page load timeout
```

---

# PHASE 2 — ARCHITECTURE: THREAD-SAFE DRIVER MANAGEMENT
### Goal: Fix parallel execution, remove reflection from listeners

---

## 2.1 — Create DriverManager with ThreadLocal

### WHY ThreadLocal is needed:
When testng.xml has `thread-count="5"`, TestNG runs 5 test methods simultaneously on 5 threads.
Currently, `Base.java` has `protected WebDriver driver`. This single field is shared.
Thread 1 might initialize Chrome, Thread 2 opens Firefox and overwrites `driver`,
then Thread 1 tries to click something and crashes because it's now controlling Firefox.

ThreadLocal stores one separate copy of `driver` PER THREAD. Thread 1 gets its own driver,
Thread 2 gets its own. They never interfere.

### WHY this also fixes the Listener reflection hack:
Currently `MyListeners.onTestFailure()` uses `Field.getDeclaredField("driver")` via reflection
to get the driver. This is fragile and breaks if field is renamed or inheritance changes.
With `DriverManager.getDriver()`, any class can get the correct driver for its thread
without reflection, without passing driver as parameter.

**File:** `src/test/java/base/DriverManager.java` (NEW FILE)

```java
package base;

import org.openqa.selenium.WebDriver;

/**
 * WHY ThreadLocal<WebDriver>:
 * Each test thread gets its own isolated WebDriver instance.
 * No more shared state between parallel tests.
 * DriverManager.getDriver() always returns the driver for the CURRENT thread.
 *
 * HOW it works:
 * ThreadLocal is like a HashMap where the key is the Thread ID.
 * setDriver(driver) stores driver under current thread's ID.
 * getDriver() retrieves it. removeDriver() cleans it up after the test.
 *
 * USAGE:
 * - In Base.java @BeforeMethod: DriverManager.setDriver(driver)
 * - In MyListeners.java: DriverManager.getDriver() — no reflection needed
 * - In any page object or utility: DriverManager.getDriver() — no passing driver around
 */
public class DriverManager {

    // ThreadLocal stores one value per thread
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * WHY removeDriver() is critical:
     * ThreadLocal values survive thread reuse in thread pools.
     * TestNG reuses threads across tests. If you don't call removeDriver()
     * in @AfterMethod, the next test on that thread gets the previous test's
     * driver — causing weird failures.
     * Always call this in @AfterMethod AFTER driver.quit().
     */
    public static void removeDriver() {
        driverThreadLocal.remove();
    }
}
```

---

## 2.2 — Update Base.java to use DriverManager

**File:** `src/test/java/base/Base.java`

```java
package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.Utilities;

import java.time.Duration;
import java.util.Properties;

public class Base {

    protected Properties prop;

    public Base() {
        prop = Utilities.loadPropertiesFile();
    }

    /**
     * WHY @BeforeMethod and not @BeforeClass:
     * Each test method gets a FRESH browser. No state leaks from previous test.
     * The previous test's cookies, localStorage, popup state are gone.
     * Tests are independent and can run in any order.
     */
    @BeforeMethod(alwaysRun = true)
    public void openBrowserAndApplication() {
        String browserName = prop.getProperty("browser", "chrome");
        WebDriver driver = createDriver(browserName);

        // Store in ThreadLocal so all threads have their own driver
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();

        // WHY 0 implicit wait: Forces all waits to be explicit.
        // Explicit waits give better error messages and predictable behavior.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.get(prop.getProperty("urldev"));
    }

    private WebDriver createDriver(String browserName) {
        switch (browserName.toLowerCase().trim()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                // WHY these options:
                // --disable-notifications: prevents browser permission popups blocking tests
                // --disable-popup-blocking: prevents popups from interrupting automation
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                return new ChromeDriver(options);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
        // WHY removeDriver(): Clears ThreadLocal to prevent memory leaks
        // and stale driver references in the next test on this thread.
        DriverManager.removeDriver();
    }
}
```

**NOTE on `@BeforeMethod` in test classes:**
Once Base.java has `@BeforeMethod openBrowserAndApplication()`, remove the `@BeforeMethod`
from individual test classes (Login.java, Search.java, etc.) to avoid duplicate browser launches.
Keep only test-specific setup if needed.

---

## 2.3 — Add WebDriverManager dependency to pom.xml

### WHY WebDriverManager:
Currently the framework may require manually downloading ChromeDriver. WebDriverManager
auto-downloads the correct driver version matching your installed browser. No manual
driver management needed.

**File:** `pom.xml` — add inside `<dependencies>`:

```xml
<!-- WHY: Auto-manages ChromeDriver/GeckoDriver binary downloads.
     Detects your browser version and downloads the matching driver.
     No more "ChromeDriver version mismatch" errors after browser updates. -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.9.2</version>
</dependency>

<!-- WHY: Required by Utilities.java for FileUtils.copyFile() -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
</dependency>
```

---

# PHASE 3 — RELIABILITY: RETRY, LOGGING, BETTER WAITS
### Goal: Handle flaky tests, add proper logging, improve wait strategy

---

## 3.1 — Add Retry Mechanism for Flaky Tests

### WHY:
Web UIs are inherently flaky — network delays, animations, React re-renders.
A test that fails due to a transient `StaleElementReferenceException` or `ElementClickInterceptedException`
should retry once before being marked as failed. Humans debugging failures should
investigate logic failures, not transient timing issues.

**File:** `src/main/java/util/RetryAnalyzer.java` (NEW FILE)

```java
package util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * WHY IRetryAnalyzer:
 * Allows TestNG to automatically retry a failed test N times before marking it FAILED.
 * Catches transient failures: StaleElementReferenceException, network timeouts,
 * brief UI overlays that block clicks.
 *
 * HOW to use — two ways:
 * 1. On a specific test: @Test(retryAnalyzer = RetryAnalyzer.class)
 * 2. On all tests: Use RetryListener (see RetryListener.java below)
 *
 * HOW it works:
 * TestNG calls retry(result) after each failure.
 * Return true = retry the test. Return false = mark it failed for real.
 * retryCount tracks attempts. MAX_RETRY_COUNT = maximum retries allowed.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    // Retry once (2 total attempts: original + 1 retry)
    // WHY only 1 retry: More retries hide real bugs. One retry handles genuine flakiness.
    private static final int MAX_RETRY_COUNT = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("Retrying test [" + result.getName() + "] - Attempt " + (retryCount + 1));
            return true;
        }
        return false;
    }
}
```

**File:** `src/main/java/util/RetryListener.java` (NEW FILE)

```java
package util;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * WHY IAnnotationTransformer:
 * Instead of adding retryAnalyzer = RetryAnalyzer.class to every @Test annotation
 * (which would be 50+ changes), this listener automatically applies RetryAnalyzer
 * to ALL @Test methods at runtime.
 *
 * HOW to activate: Add to testng.xml:
 * <listener class-name="util.RetryListener"/>
 */
public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        // Apply RetryAnalyzer to every test method automatically
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
```

**Add to testng.xml:**
```xml
<listeners>
    <listener class-name="listeners.MyListeners"/>
    <listener class-name="util.RetryListener"/>
</listeners>
```

---

## 3.2 — Add Log4j2 Logging

### WHY Thread.sleep for debugging context is replaced by logging:
Currently there are Thread.sleep calls that exist just to "wait and see what happens".
Proper logging makes the test execution story visible without adding waits.

**File:** `src/test/resources/log4j2.xml` (NEW FILE)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <!-- WHY Console appender: See logs in real-time while tests run -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>

        <!-- WHY File appender: Persistent log for CI/CD and post-run analysis -->
        <RollingFile name="FileAppender"
                     fileName="logs/test-execution.log"
                     filePattern="logs/test-execution-%d{yyyy-MM-dd}.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger - %msg%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
            </Policies>
        </RollingFile>
    </Appenders>

    <Loggers>
        <Root level="INFO">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="FileAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

**Add to pom.xml:**
```xml
<!-- WHY Log4j2: Industry standard logging. Replaces System.out.println.
     Configurable log levels (DEBUG, INFO, WARN, ERROR).
     Async logging — doesn't slow down tests. -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.23.1</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.23.1</version>
</dependency>
```

**Usage in any class:**
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public void enterUsernameField(String username) {
        log.info("Entering username: {}", username);
        // Instead of Thread.sleep(4000) here, just log and wait properly:
        WebElement field = WaitUtils.waitForElementVisible(driver, usernameField);
        field.clear();
        field.sendKeys(username);
    }
}
```

---

## 3.3 — Replace Thread.sleep in LoginPage

### WHY Thread.sleep(4000) before username field is wrong:
After clicking Submit on a previous page or navigating to login, the page loads.
Thread.sleep(4000) guesses it takes 4 seconds. Instead, wait FOR the specific element.

**File:** `src/main/java/pageObjects/LoginPage.java`

```java
// BEFORE (bad):
Thread.sleep(4000);
WebElement emailElement = wait.until(
    ExpectedConditions.visibilityOfElementLocated(usernameField));

// AFTER (good):
// WaitUtils already handles the wait. No sleep needed.
// It polls every 500ms and proceeds the INSTANT the element appears.
WebElement emailElement = WaitUtils.waitForElementVisible(driver, usernameField);
emailElement.clear();
emailElement.sendKeys(username);
```

---

# PHASE 4 — MAINTAINABILITY: CONSTANTS, CONFIG, PAGE FACTORY
### Goal: Remove hardcoded values, make framework environment-agnostic

---

## 4.1 — Create Constants Class

### WHY:
File paths like `C:\Users\admin\Desktop\EXE FILES\UploadPDF.exe` appear in multiple files.
When the machine changes, you must hunt through all Java files to update paths.
A single Constants class means one change updates everywhere.

**File:** `src/main/java/util/Constants.java` (NEW FILE)

```java
package util;

/**
 * WHY centralize constants:
 * Hardcoded strings scattered in 8 page objects = maintenance nightmare.
 * Change machine? Update one file, not eight.
 * New team member? They find all important paths in one place.
 *
 * HOW to use:
 * Replace "C:\\Users\\admin\\Desktop\\EXE FILES\\UploadPDF.exe"
 * with Constants.AUTOIT_UPLOAD_PDF
 */
public class Constants {

    // WHY System.getProperty("user.dir") instead of C:\Users\admin\...:
    // user.dir returns the project root regardless of who runs it or where it's installed.
    // The framework works on any machine without code changes.
    private static final String PROJECT_ROOT = System.getProperty("user.dir");

    // AutoIt executable paths — store exes inside the project under resources/autoit/
    // WHY: Keep project self-contained. Don't rely on files on the Desktop.
    public static final String AUTOIT_DIR = PROJECT_ROOT + "/src/test/resources/autoit/";
    public static final String AUTOIT_UPLOAD_PDF    = AUTOIT_DIR + "UploadPDF.exe";
    public static final String AUTOIT_UPLOAD_PNG    = AUTOIT_DIR + "UploadPNG.exe";
    public static final String AUTOIT_UPLOAD_JPG    = AUTOIT_DIR + "UploadJPG.exe";
    public static final String AUTOIT_UPLOAD_CSV    = AUTOIT_DIR + "UploadCSV.exe";
    public static final String AUTOIT_UPLOAD_XLSX   = AUTOIT_DIR + "UploadXLSX.exe";
    public static final String AUTOIT_UPLOAD_MP4    = AUTOIT_DIR + "UploadMP4.exe";
    public static final String AUTOIT_UPLOAD_GIF    = AUTOIT_DIR + "UploadGIF.exe";

    // Screenshot directory
    public static final String SCREENSHOT_DIR = PROJECT_ROOT + "/screenshots/";

    // Report path
    public static final String EXTENT_REPORT_PATH = PROJECT_ROOT + "/target/reports/ExtentReport.html";

    // Timeouts (seconds) — change here to affect the whole framework
    public static final int DEFAULT_WAIT    = 15;
    public static final int LONG_WAIT       = 30;
    public static final int SHORT_WAIT      = 5;
    public static final int FILE_UPLOAD_WAIT = 10; // Wait for AutoIt to complete
}
```

---

## 4.2 — Environment Configuration (Multi-environment support)

### WHY:
Currently `projectdata.properties` has `url`, `urldev`. To run against staging or production,
you edit the file. This causes accidental commits of changed URLs. Industry standard is
to pass environment as a system property at runtime.

**File:** `src/main/java/util/ConfigManager.java` (NEW FILE)

```java
package util;

import java.util.Properties;

/**
 * WHY ConfigManager vs direct Properties access:
 * Base.java currently loads properties and stores in `prop`. Every class
 * that needs config must either receive `prop` as a parameter or load it again.
 *
 * ConfigManager is a singleton — loads once, accessible anywhere without parameters.
 *
 * WHY environment system property:
 * Run tests with: mvn test -Denv=staging
 * No code change needed to switch environments.
 * CI/CD pipelines can parameterize: mvn test -Denv=prod
 */
public class ConfigManager {

    private static Properties prop;

    // Environment-to-URL mapping
    // Add more environments here as needed
    private static final java.util.Map<String, String> ENV_URLS = new java.util.HashMap<>();
    static {
        ENV_URLS.put("dev",     "https://app.spdevmfp.com/home/");
        ENV_URLS.put("staging", "https://app.sppreprod.in/home/");
        ENV_URLS.put("prod",    "https://app.salespanda.com/home/");
    }

    private ConfigManager() {} // Prevent instantiation

    public static synchronized Properties getConfig() {
        if (prop == null) {
            prop = Utilities.loadPropertiesFile();
        }
        return prop;
    }

    /**
     * WHY: System property overrides file property.
     * -Denv=staging on command line takes precedence over file config.
     * HOW: mvn test -Denv=staging runs all tests against staging URL.
     */
    public static String getUrl() {
        String env = System.getProperty("env", "dev"); // default to dev
        return ENV_URLS.getOrDefault(env, ENV_URLS.get("dev"));
    }

    public static String get(String key) {
        return getConfig().getProperty(key);
    }

    public static String getBrowser() {
        // Command line -Dbrowser=firefox overrides properties file
        return System.getProperty("browser", getConfig().getProperty("browser", "chrome"));
    }
}
```

**Usage in Base.java:**
```java
// BEFORE:
driver.get(prop.getProperty("urldev"));

// AFTER:
driver.get(ConfigManager.getUrl());
// Run with: mvn test -Denv=staging -Dbrowser=firefox
```

---

# PHASE 5 — REPORTING & TEST DATA MANAGEMENT
### Goal: Professional reports, data-driven from Excel, screenshot improvements

---

## 5.1 — Improved ExtentReporter with System Info

**File:** `src/main/java/util/ExtentReporter.java`

```java
package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReporter {

    private static ExtentReports extentReports;

    /**
     * WHY synchronized singleton:
     * Parallel tests can call getExtentReport() simultaneously.
     * Without synchronized, two threads could create two instances,
     * and some test results would be written to the wrong one.
     */
    public static synchronized ExtentReports getExtentReport() {
        if (extentReports == null) {
            String reportPath = Constants.EXTENT_REPORT_PATH;
            new File(reportPath).getParentFile().mkdirs();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report - Salespanda");
            sparkReporter.config().setTheme(Theme.DARK);

            // WHY: Encodes screenshots in report as Base64 — eliminates broken images
            sparkReporter.config().setEncoding("utf-8");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            // WHY system info: Report shows environment context.
            // When you share the report, viewers know which environment it ran against.
            extentReports.setSystemInfo("Environment", ConfigManager.getUrl());
            extentReports.setSystemInfo("Browser",     ConfigManager.getBrowser());
            extentReports.setSystemInfo("OS",          System.getProperty("os.name"));
            extentReports.setSystemInfo("Java",        System.getProperty("java.version"));
            extentReports.setSystemInfo("Tester",      System.getProperty("user.name"));
        }
        return extentReports;
    }

    /**
     * WHY reset for fresh runs:
     * Without this, parallel test suites append to old ExtentReports instance.
     */
    public static synchronized void reset() {
        extentReports = null;
    }
}
```

---

## 5.2 — Excel-Based Test Data (Replacing hardcoded @DataProvider)

### WHY:
Currently `Login.java` has credentials hardcoded in `@DataProvider`. Non-technical team
members cannot add test cases without Java knowledge.

**Add dependency in pom.xml:**
```xml
<!-- WHY Apache POI: Read Excel files for data-driven testing.
     Test data managed by non-developers in Excel, not in Java. -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.3.0</version>
</dependency>
```

**File:** `src/main/java/util/ExcelUtils.java` (NEW FILE)

```java
package util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * WHY Excel for test data:
 * @DataProvider in Java requires recompilation when test data changes.
 * Excel allows QA/business teams to add/modify test cases without touching code.
 * Bulk test data (100 login combos) is unreadable in Java but fine in Excel.
 *
 * HOW to use:
 * Object[][] data = ExcelUtils.getTestData("TestData.xlsx", "LoginData");
 * Use in @DataProvider method.
 */
public class ExcelUtils {

    public static Object[][] getTestData(String fileName, String sheetName) {
        String filePath = System.getProperty("user.dir") +
            "/src/test/resources/testdata/" + fileName;
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();
            data = new Object[rows][cols];

            for (int i = 1; i <= rows; i++) { // Start from 1 to skip header row
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
```

---

# PHASE 6 — CLEANUP & BEST PRACTICES
### Goal: Code quality, naming consistency, remove duplications

---

## 6.1 — Base Page Object Class

### WHY:
All 8 page objects have their own `WebDriver driver` and `WebDriverWait wait` initialized
in each constructor separately. If you change the wait duration, you change 8 files.
A `BasePage` centralizes this.

**File:** `src/main/java/pageObjects/BasePage.java` (NEW FILE)

```java
package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WaitUtils;

import java.time.Duration;

/**
 * WHY BasePage:
 * All page objects share common driver and wait. Defining them once here
 * means changing timeout in one place affects all pages.
 * PageFactory.initElements() initializes @FindBy annotated fields.
 *
 * HOW: Each page object extends BasePage and calls super(driver) in its constructor.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;

        // WHY PageFactory.initElements:
        // Initializes all @FindBy annotated WebElement fields in the subclass.
        // Lazy initialization — elements are looked up only when accessed (no stale elements).
        PageFactory.initElements(driver, this);
    }

    /**
     * Shared utility methods all pages can use
     */
    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    protected void waitForPageReady() {
        WaitUtils.waitForPageLoad(driver);
    }
}
```

**HOW to update each page object:**
```java
// BEFORE:
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}

// AFTER:
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver); // BasePage initializes driver, wait, js
    }
}
```

---

## 6.2 — Proper testng.xml Structure

**File:** `testng.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="Salespanda Test Suite" verbose="1" parallel="methods" thread-count="3">

    <listeners>
        <listener class-name="listeners.MyListeners"/>
        <listener class-name="util.RetryListener"/>
    </listeners>

    <!-- WHY parallel="methods" thread-count="3":
         Runs 3 test methods simultaneously. Each gets its own DriverManager driver.
         "methods" level is safest for parallel — each test is fully isolated.
         thread-count="3" is conservative — increases if machine has more cores.
         Do NOT use parallel="tests" without DriverManager in place (Phase 2). -->

    <test name="Login Tests">
        <classes>
            <class name="tests.Login"/>
        </classes>
    </test>

    <test name="Search Tests">
        <classes>
            <class name="tests.Search"/>
        </classes>
    </test>

    <test name="Push Notification Tests">
        <classes>
            <class name="tests.PushNotification"/>
        </classes>
    </test>

    <test name="Document Library Tests">
        <classes>
            <class name="tests.DocumentLibrary"/>
        </classes>
    </test>

    <test name="Asset Creation Tests">
        <classes>
            <class name="tests.VideoCreationTest"/>
            <class name="tests.ImageCreationTest"/>
            <class name="tests.PdfCreationTest"/>
        </classes>
    </test>

    <test name="Social Auto Post Tests">
        <classes>
            <class name="tests.SocialAutoPostTest"/>
        </classes>
    </test>

</suite>
```

---

# SUMMARY TABLE — PHASE-WISE IMPLEMENTATION ORDER

| Phase | What | Files Changed | Impact |
|-------|------|---------------|--------|
| **1** | Fix broken screenshots (Base64) | Utilities.java, MyListeners.java | CRITICAL — visible immediately |
| **1** | Replace Thread.sleep → WaitUtils | New WaitUtils.java + all page objects | CRITICAL — speed + reliability |
| **1** | Fix 35s implicit wait | Base.java | HIGH — faster failure detection |
| **2** | ThreadLocal DriverManager | New DriverManager.java, Base.java | HIGH — parallel execution works |
| **2** | Remove reflection in Listener | MyListeners.java | HIGH — stable listener |
| **3** | Retry mechanism | New RetryAnalyzer.java, RetryListener.java | MEDIUM — handles flakiness |
| **3** | Log4j2 logging | New log4j2.xml + pom.xml | MEDIUM — better debugging |
| **4** | Constants class | New Constants.java + page objects | MEDIUM — portability |
| **4** | ConfigManager + multi-env | New ConfigManager.java | MEDIUM — CI/CD ready |
| **5** | Better ExtentReporter | ExtentReporter.java | LOW — report quality |
| **5** | Excel test data | New ExcelUtils.java + pom.xml | LOW — team scalability |
| **6** | BasePage class | New BasePage.java + all page objects | LOW — code quality |
| **6** | Proper testng.xml | testng.xml | LOW — suite organization |

---

# QUICK START — DO PHASE 1 RIGHT NOW

**Step 1:** Copy the new `WaitUtils.java` content into `src/main/java/util/WaitUtils.java`

**Step 2:** Update `Utilities.java` with the new `captureScreenshotAsBase64()` method

**Step 3:** Update `MyListeners.java` to use Base64 screenshot
(Temporarily keep reflection for driver — remove it in Phase 2 after DriverManager is done)

**Step 4:** In `Base.java`, change `implicitlyWait(35 seconds)` → `implicitlyWait(0 seconds)`

**Step 5:** In `LoginPage.java`, remove `Thread.sleep(4000)` and replace with:
```java
WebElement emailElement = WaitUtils.waitForElementVisible(driver, usernameField);
```

**Step 6:** Run one test suite. Screenshots in ExtentReport will now show correctly.
Tests will run faster. Failures will report immediately instead of after 35 seconds.

---

*Document created: 2026-03-25 | Framework: Selenium 4 + TestNG + ExtentReports 5 | Java 17*
