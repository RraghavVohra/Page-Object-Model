# Selenium Test Stability — Interview Notes

## Why do Selenium tests pass one day and fail the next?

This is one of the most common real-world problems in test automation. Tests are flaky when
they pass and fail for reasons unrelated to actual application bugs. Here are the main causes:

---

### 1. Browser Auto-Updates (e.g., Chrome)

Chrome updates silently in the background. A new browser version changes the JavaScript engine
timing, which shifts when exactly Angular's (or React's) re-render cycle fires relative to
Selenium's actions. Tests that were stable overnight suddenly break with zero changes to the code.

**How to handle it:** Keep ChromeDriver version in sync with Chrome. Use a WebDriverManager
library to auto-match versions. Avoid hardcoding ChromeDriver paths.

---

### 2. Angular / React Re-Render Cycles

Modern SPAs (Single Page Applications) re-render DOM elements after they first appear.
Selenium finds the element, types into it, and Angular re-renders — wiping the field clean.
On a fast day, Selenium's sendKeys lands after the re-render and sticks. On a slow day,
Angular re-renders after sendKeys, clearing the value. Same code, different outcome.

**How to handle it:** Use a retry lambda that checks the value actually stuck:
- Find element → clear → sendKeys → read back the value → if it matches, proceed; else retry.
- This is more reliable than a fixed Thread.sleep before typing.

---

### 3. Server Response Speed / Network Latency

If the application server is under load, pages take longer to load. Waits that were comfortable
on a fast day will time out on a slow day. A momentary network spike adds a few extra seconds
to a redirect, pushing a 15-second wait over the edge.

**How to handle it:**
- Use generous timeouts (20–30 seconds) for critical waits like post-login redirects.
- Never use Thread.sleep — it wastes time on fast days and still fails on slow days.
- Always wait for a specific element that signals the page is ready.

---

### 4. Overlay / Modal / Toast Timing

Bootstrap dropdowns, loading spinners, toast messages, and overlay backgrounds appear and
disappear at slightly different speeds depending on server load and browser rendering speed.
A click that lands cleanly on a fast day hits an invisible overlay on a slow day, causing
ElementClickInterceptedException.

**How to handle it:**
- After closing a modal or overlay, wait for it to become invisible before proceeding:
  `wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator))`
- Use JS click for elements known to be intercepted by overlays.

---

### 5. Session / Cookie State from Previous Test Run

If a previous test fails mid-way and tearDown doesn't fully execute, the browser may still be
logged in. The next test run lands on a different page than expected, causing immediate element
not found failures that look like the new test is broken — but it's actually leftover state.

**How to handle it:**
- Always quit the driver in an @AfterMethod tearDown, even if the test fails.
- Optionally clear cookies and localStorage in tearDown before quitting.

---

### 6. isEnabled() / isDisplayed() Checks That Are Too Strict

Checking `isEnabled()` before clicking sounds safe, but Angular may temporarily disable a
button during its re-render cycle even when the form is filled correctly. If your wait lambda
keeps checking `isEnabled()` and it keeps returning false, the wait times out after 15–30
seconds — not because login failed, but because you were too strict about when to click.

**How to handle it:**
- Skip the `isEnabled()` guard for form submit buttons in Angular apps.
- Just find the element and JS-click it.
- Rely on the post-action wait (e.g., urlContains) to detect actual failure.

---

## Key Principles to Mention in Interviews

| Principle | Why it matters |
|---|---|
| Replace Thread.sleep with explicit waits | Fixed sleeps waste time on fast days, still fail on slow days |
| Wait for specific elements, not page load | `document.readyState` is always 'complete' in SPAs — useless |
| Keep waits in Page Object, not test layer | Test layer should be clean and readable |
| Use JS click for intercepted elements | Overlays and Angular intercept native Selenium clicks |
| Retry-until-value-sticks for form fields | Angular re-render can clear fields after sendKeys |
| Use invisibilityOfElementLocated for overlays | driver.findElement throws if overlay is already gone |
| Generous timeouts (20–30s) for redirects | Absorbs slow server days without failing unnecessarily |

---

## Quick Interview Answer (30 seconds)

"Selenium tests can be flaky for several reasons: browser auto-updates change JS timing,
Angular or React re-renders DOM elements after Selenium interacts with them, server response
times vary under load, overlays intercept clicks unexpectedly, and leftover session state from
a previous failed run can put the browser in an unexpected state. The fix is to replace all
Thread.sleep calls with explicit element-based waits, use retry loops for form fields to ensure
values actually stick, and use JS click for elements prone to interception."
