// ============================================================
// PLAYWRIGHT REFERENCE FILE - Document Library Feature
// Converted from: Java + Selenium + TestNG
// Target:         TypeScript + Playwright
// ============================================================
// HOW TO USE:
//   - Copy DocumentLibraryPage class into your page-objects folder
//   - Copy test blocks into your tests folder
//   - Replace BASE_URL and credential values with your own config
// ============================================================

import { test, expect, Page } from '@playwright/test';
import path from 'path';

// ============================================================
// SECTION 1 — CONFIG (replace with your .env or playwright.config values)
// ============================================================

const BASE_URL            = 'https://your-app-url.com';           // replace
const DOC_LIBRARY_URL     = 'https://your-app-url.com/document-library.php';    // replace with docLibraryExpectedURL
const DOC_UPLOAD_URL      = 'https://your-app-url.com/sp-upload-document.php';  // replace with docLibraryExpectedURLTwo
const USERNAME            = 'your-username';                      // replace with validusernamedev
const PASSWORD            = 'your-password';                      // replace with validpassworddev
const DOCUMENT_NAME       = 'TestDocument';                       // replace with documentName property
const DESCRIPTION_TEXT    = 'This is a test description.';        // replace with descriptionText property
const HASHTAG_TEXT        = 'testhashtag';                        // replace with InternalHashtagToEnter property
const SEARCH_VALUE        = 'ewewew test';                        // replace with validValueInSearchBox property

// File paths — Playwright uses direct file paths instead of AutoIt EXE files
const PDF_FILE_PATH       = path.resolve('C:/TestFiles/test.pdf');
const PNG_FILE_PATH       = path.resolve('C:/TestFiles/test.png');
const JPG_FILE_PATH       = path.resolve('C:/TestFiles/test.jpg');
const CSV_FILE_PATH       = path.resolve('C:/TestFiles/test.csv');
const XLSX_FILE_PATH      = path.resolve('C:/TestFiles/test.xlsx');
const MP4_FILE_PATH       = path.resolve('C:/TestFiles/test.mp4');
const GIF_FILE_PATH       = path.resolve('C:/TestFiles/test.gif');
const THUMBNAIL_PNG_PATH  = path.resolve('C:/TestFiles/thumbnail.png');
const THUMBNAIL_GIF_PATH  = path.resolve('C:/TestFiles/thumbnail.gif');
const THUMBNAIL_JPG_PATH  = path.resolve('C:/TestFiles/thumbnail.jpg');


// ============================================================
// SECTION 2 — PAGE OBJECT CLASS
// ============================================================

class DocumentLibraryPage {

  constructor(private page: Page) {}

  // ---- LOCATORS (defined as getters for lazy evaluation) ----

  private get communicationTab()      { return this.page.locator("//span[text()='Communication']"); }
  private get documentLibraryLink()   { return this.page.locator("(//a[@class='dropdown-item'])[5]"); }
  private get actionsButton()         { return this.page.locator("(//*[name()='svg'])[1]"); }
  private get profileIcon()           { return this.page.locator("//i[@class='fa fa-user-circle']"); }
  private get logOutOption()          { return this.page.locator("//a[normalize-space()='Log Out']"); }
  private get logoutButtonPrimary()   { return this.page.locator("//a[normalize-space()='Yes' and contains(@class,'btn-primary')]"); }
  private get logoutButtonYes()       { return this.page.locator("//a[normalize-space()='Yes']"); }
  private get uploadOption()          { return this.page.locator("//a[@href='sp-upload-document.php']"); }
  private get uploadButton()          { return this.page.locator("//input[@id='share_button']"); }
  private get documentNameField()     { return this.page.locator("//input[@id='document_name']"); }
  private get fileInput()             { return this.page.locator("#document_file"); }
  private get thumbnailInput()        { return this.page.locator("#img_validate"); }
  private get croppingHandle()        { return this.page.locator("//div[@class='imgareaselect-border4']"); }
  private get applyButton()           { return this.page.locator("//a[@class='btn yes yellow-gold pull-right']"); }
  private get descriptionField()      { return this.page.locator("//textarea[@class='form-control h150']"); }
  private get documentOptionTwo()     { return this.page.locator("//input[@value='2']"); }
  private get documentOptionThree()   { return this.page.locator("//input[@value='3']"); }
  private get downloadableToggle()    { return this.page.locator("//span[@class='bootstrap-switch-handle-off bootstrap-switch-default']"); }
  private get hashtagField()          { return this.page.locator("//input[@id='tagcsv']"); }
  private get hashtagSuggestion()     { return this.page.locator("//li[@class='ui-menu-item']"); }
  private get searchBox()             { return this.page.locator("//input[@type='search' and @placeholder='Search']"); }
  private get searchResult()          { return this.page.locator("//td[normalize-space()='ewewew test']"); }
  private get deleteOption()          { return this.page.locator("#Delete3"); }
  private get okButton()              { return this.page.locator("//button[@type='button' and @class='btn btn-primary bootbox-accept' and text()='OK']"); }
  private get dialogBox()             { return this.page.locator("//div[@class='bootbox-body']"); }
  private get checkboxOption()        { return this.page.locator("(//input[@id='document_content'])[1]"); }
  private get dynamicElement()        { return this.page.locator("(//td[@class='wBreak d-none d-md-table-cell' and @style='cursor: no-drop;'])[1]"); }
  private get noRecordsElement()      { return this.page.locator("//td[@class='dataTables_empty' and text()='No matching records found']"); }
  private get accessOption()          { return this.page.locator("//a[@id='add_synd']"); }
  private get teamRadioButton()       { return this.page.locator("//input[@id='partners_option']"); }
  private get partnerCategoryButton() { return this.page.locator("//button[@id='btn_ptr_category']"); }
  private get categoryLabel()         { return this.page.locator("//label[@for='ms-opt-40']"); }
  private get updateAccessButton()    { return this.page.locator("//input[@id='synd_update_id']"); }
  private get scheduleCheckbox()      { return this.page.locator("//input[@id='schedule']"); }
  private get scheduleTextbox()       { return this.page.locator("//input[@id='schedule_synd']"); }
  private get contentUpdateDate()     { return this.page.locator("//input[@id='start_date' and @name='start_date' and @type='text']"); }

  // ---- NAVIGATION METHODS ----

  async clickOnCommunicationTab() {
    await this.communicationTab.click();
  }

  async clickOnDocumentLibrary() {
    await this.documentLibraryLink.click();
  }

  async clickOnActionsButton() {
    await this.actionsButton.click();
  }

  // ---- DROPDOWN OPTIONS ----

  /**
   * Returns the text of all four Action menu options.
   * Java original used driver.findElement — Playwright equivalent below.
   */
  async getDocumentLibraryOptions(): Promise<string[]> {
    const uploadText  = await this.page.locator("//a[@href='sp-upload-document.php']").innerText();
    const accessText  = await this.page.locator("#add_synd").innerText();
    const hashtagText = await this.page.locator("#add_hastag").innerText();
    const deleteText  = await this.page.locator("#Delete3").innerText();
    return [uploadText.trim(), accessText.trim(), hashtagText.trim(), deleteText.trim()];
  }

  // ---- LOGIN / LOGOUT METHODS ----

  async clickOnProfileIcon() {
    await this.profileIcon.click();
  }

  /**
   * Java used JavascriptExecutor to click because the element was intercepted.
   * Playwright's force:true is the equivalent.
   */
  async clickOnLogoutOption() {
    await this.logOutOption.click({ force: true });
  }

  async clickOnLogoutButton() {
    await this.logoutButtonPrimary.click({ force: true });
  }

  async clickOnLogoutButtonTwo() {
    await this.logoutButtonYes.click({ force: true });
  }

  // ---- UPLOAD FORM METHODS ----

  async clickOnUploadOption() {
    await this.uploadOption.click();
  }

  async clickOnUploadButton() {
    await this.uploadButton.scrollIntoViewIfNeeded();
    await this.uploadButton.click();
  }

  async enterValueInDocumentNameField(documentName: string) {
    await this.documentNameField.fill(documentName);
  }

  /**
   * KEY DIFFERENCE FROM JAVA VERSION:
   * Java used AutoIt EXE files to handle the native OS file dialog.
   * Playwright can directly set files on the <input type="file"> element
   * without opening any dialog — no AutoIt needed at all!
   *
   * All six upload methods (PDF, PNG, JPG, CSV, XLSX, MP4, GIF) follow the same pattern.
   */
  async uploadDocument(filePath: string) {
    await this.fileInput.setInputFiles(filePath);
  }

  async uploadDocumentPDF()  { await this.uploadDocument(PDF_FILE_PATH); }
  async uploadDocumentPNG()  { await this.uploadDocument(PNG_FILE_PATH); }
  async uploadDocumentJPG()  { await this.uploadDocument(JPG_FILE_PATH); }
  async uploadDocumentCSV()  { await this.uploadDocument(CSV_FILE_PATH); }
  async uploadDocumentXLSX() { await this.uploadDocument(XLSX_FILE_PATH); }
  async uploadDocumentMP4()  { await this.uploadDocument(MP4_FILE_PATH); }
  async uploadDocumentGIF()  { await this.uploadDocument(GIF_FILE_PATH); }

  /**
   * Thumbnail: Java used sendKeys with a file path on the input element.
   * Playwright uses setInputFiles — same clean approach.
   */
  async attachThumbnail(thumbnailPath: string) {
    await this.thumbnailInput.setInputFiles(thumbnailPath);
  }

  /**
   * Resize the cropping area by dragging the handle 50px right and 50px down.
   * Java used Actions.clickAndHold → moveByOffset → release.
   * Playwright uses dragTo or mouse events.
   */
  async resizeCroppingArea() {
    const handle = this.croppingHandle;
    await handle.waitFor({ state: 'visible' });
    const box = await handle.boundingBox();
    if (box) {
      await this.page.mouse.move(box.x + box.width / 2, box.y + box.height / 2);
      await this.page.mouse.down();
      await this.page.mouse.move(box.x + box.width / 2 + 50, box.y + box.height / 2 + 50);
      await this.page.mouse.up();
    }
  }

  async clickOnApplyButton() {
    await this.applyButton.click();
  }

  async enterValueInDescriptionField(text: string) {
    await this.descriptionField.scrollIntoViewIfNeeded();
    await this.descriptionField.fill(text);
  }

  // ---- VALIDATION MESSAGE METHODS ----
  /**
   * Java used JavascriptExecutor: js.executeScript("return arguments[0].validationMessage", el)
   * Playwright equivalent: locator.evaluate(el => el.validationMessage)
   */
  async getValidationMessageForDocumentNameField(): Promise<string> {
    return await this.documentNameField.evaluate(
      (el: HTMLInputElement) => el.validationMessage
    );
  }

  async getValidationMessageForDescriptionField(): Promise<string> {
    return await this.descriptionField.evaluate(
      (el: HTMLTextAreaElement) => el.validationMessage
    );
  }

  async getValidationMessageForDocumentAttachmentField(): Promise<string> {
    return await this.fileInput.evaluate(
      (el: HTMLInputElement) => el.validationMessage
    );
  }

  // ---- DOCUMENT OPTIONS ----

  async clickOnDocumentOptionTwo() {
    await this.documentOptionTwo.click();
  }

  async clickOnDocumentOptionThree() {
    await this.documentOptionThree.click();
  }

  async clickOnDownloadableOption() {
    await this.downloadableToggle.click();
  }

  // ---- HASHTAG METHODS ----

  async enterInternalHashtag(text: string) {
    await this.hashtagField.fill(text);
  }

  async selectInternalHashtag() {
    await this.hashtagSuggestion.click();
  }

  // ---- SEARCH & RESULTS ----

  async enterIntoSearchBox(text: string) {
    await this.searchBox.fill(text);
  }

  async getSearchResultText(): Promise<string> {
    return await this.searchResult.innerText();
  }

  // ---- DELETE FLOW ----

  async clickOnDeleteOption() {
    await this.deleteOption.click();
  }

  async clickOnOkButton() {
    await this.okButton.click();
  }

  async getDialogBoxText(): Promise<string> {
    return await this.dialogBox.innerText();
  }

  // ---- CHECKBOX & DYNAMIC TEXT ----

  async clickOnCheckBoxOption() {
    await this.checkboxOption.click();
  }

  async getDynamicText(): Promise<string> {
    return await this.dynamicElement.innerText();
  }

  async noRecordsElementMethod(): Promise<string> {
    await this.noRecordsElement.waitFor({ state: 'visible' });
    return await this.noRecordsElement.innerText();
  }

  // ---- ACCESS CONTROL METHODS ----

  async clickOnAccessOption() {
    await this.accessOption.click();
  }

  /**
   * Java used JavascriptExecutor to click the radio button.
   * Playwright auto-handles this — a plain click() works.
   */
  async clickOnTeamRadioButton() {
    await this.teamRadioButton.click();
  }

  async clickOnPartnerCategoryButton() {
    await this.partnerCategoryButton.click();
  }

  async clickOnCategory() {
    await this.categoryLabel.click();
  }

  async clickOnUpdateAccessButton() {
    await this.updateAccessButton.click();
  }

  // ---- SCHEDULE METHODS ----

  async clickOnScheduleCheckbox() {
    await this.scheduleCheckbox.click();
  }

  async clickOnScheduleTextbox() {
    await this.scheduleTextbox.click();
  }

  async clickOnContentUpdate() {
    await this.contentUpdateDate.click();
  }

  /**
   * Selects today's date in the xdsoft_datetimepicker calendar popup.
   * Java used explicit WebDriverWait + try/catch with JS fallback.
   * Playwright auto-waits, so we just waitFor visible and click.
   */
  async selectTodayInCalendar() {
    const calendarPopup = this.page.locator(
      "//div[contains(@class, 'xdsoft_datetimepicker') and contains(@style, 'display: block')]"
    );
    await calendarPopup.waitFor({ state: 'visible' });

    const todayElement = this.page.locator(
      "//td[contains(@class, 'xdsoft_date') and contains(@class, 'xdsoft_today')]"
    );
    await todayElement.waitFor({ state: 'visible' });
    await todayElement.click();
  }

  /**
   * Selects a specific date (day/month/year) from the xdsoft calendar.
   * month is 1-based (January = 1), same as the Java version.
   */
  async selectDateOfYourChoice(day: number, month: number, year: number) {
    const calendarPopup = this.page.locator(
      "//div[contains(@class, 'xdsoft_datetimepicker') and contains(@style, 'display: block')]"
    );
    await calendarPopup.waitFor({ state: 'visible' });

    // Year
    const yearLabel = this.page.locator(
      "//div[contains(@class,'xdsoft_label') and contains(@class,'xdsoft_year')]/span"
    );
    await yearLabel.click();
    await this.page.locator(`//div[contains(@class,'xdsoft_yearselect')]//div[@data-value='${year}']`).click();

    // Month (xdsoft uses 0-based months internally)
    const monthLabel = this.page.locator(
      "//div[contains(@class,'xdsoft_label') and contains(@class,'xdsoft_month')]/span"
    );
    await monthLabel.click();
    await this.page.locator(
      `//div[contains(@class,'xdsoft_monthselect')]//div[@data-value='${month - 1}']`
    ).click();

    // Day
    await this.page.locator(
      `//td[contains(@class,'xdsoft_date') and not(contains(@class,'xdsoft_disabled')) and @data-date='${day}']`
    ).click();

    // Time — try active/highlighted time first, fall back to first available
    const activeTime = this.page.locator(
      "//div[contains(@class,'xdsoft_datetimepicker') and contains(@style,'display: block')]//div[contains(@class,'xdsoft_time') and contains(@class,'xdsoft_current')]"
    );
    const firstTime = this.page.locator(
      "(//div[contains(@class,'xdsoft_datetimepicker') and contains(@style,'display: block')]//div[contains(@class,'xdsoft_time')])[1]"
    );

    const activeVisible = await activeTime.isVisible().catch(() => false);
    if (activeVisible) {
      await activeTime.scrollIntoViewIfNeeded();
      await activeTime.click();
    } else {
      await firstTime.scrollIntoViewIfNeeded();
      await firstTime.click();
    }
  }

  async selectCurrentActiveTime() {
    const calendarPopup = this.page.locator(
      "//div[contains(@class, 'xdsoft_datetimepicker') and contains(@style,'display: block')]"
    );
    await calendarPopup.waitFor({ state: 'visible' });

    const activeTime = this.page.locator(
      "//div[contains(@class,'xdsoft_datetimepicker') and contains(@style,'display: block')]//div[contains(@class,'xdsoft_time') and contains(@class,'xdsoft_current')]"
    );

    const isActiveVisible = await activeTime.isVisible().catch(() => false);
    if (isActiveVisible) {
      await activeTime.scrollIntoViewIfNeeded();
      await activeTime.click();
    } else {
      const firstTime = this.page.locator(
        "(//div[contains(@class,'xdsoft_datetimepicker') and contains(@style,'display: block')]//div[contains(@class,'xdsoft_time')])[1]"
      );
      await firstTime.scrollIntoViewIfNeeded();
      await firstTime.click();
    }
  }

  // ---- SCROLL HELPERS ----
  // Java used a Utilities class for scrolling. Playwright provides built-in scroll support.

  async scrollToTop() {
    await this.page.evaluate(() => window.scrollTo(0, 0));
  }

  async scrollToBottom() {
    await this.page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
  }

  async scrollDownByFiveHundred() {
    await this.page.evaluate(() => window.scrollBy(0, 500));
  }
}


// ============================================================
// SECTION 3 — HELPER: LOGIN REUSABLE FUNCTION
// ============================================================

async function loginToApp(page: Page) {
  await page.goto(BASE_URL);
  await page.locator("//input[@name='username']").fill(USERNAME);  // adjust selector as needed
  await page.locator("//input[@name='password']").fill(PASSWORD);  // adjust selector as needed
  await page.locator("//input[@type='submit']").click();           // adjust selector as needed
}


// ============================================================
// SECTION 4 — TEST CASES
// ============================================================

test.describe('Document Library', () => {

  // ---------------------------------------------------------
  // TC_DL_01 — Navigate to Document Library screen
  // ---------------------------------------------------------
  test('TC_DL_01 - User is taken to Document Library screen', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    // Logout
    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButtonTwo();
  });


  // ---------------------------------------------------------
  // TC_DL_03 — Actions menu shows correct options
  // ---------------------------------------------------------
  test('TC_DL_03 - Actions menu button shows all options', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();

    const expectedOptions = ['Upload', 'Access', 'Update Hashtag(s)', 'Delete'];
    const actualOptions   = await docLibPage.getDocumentLibraryOptions();

    expect(actualOptions).toEqual(expectedOptions);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButtonTwo();
  });


  // ---------------------------------------------------------
  // TC_DL_04 — Clicking Upload navigates to upload screen
  // ---------------------------------------------------------
  test('TC_DL_04 - Upload option navigates to upload screen', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButtonTwo();
  });


  // ---------------------------------------------------------
  // TC_DL_17 — Upload with all mandatory fields filled (PDF)
  // ---------------------------------------------------------
  test('TC_DL_17 - Upload document with all mandatory fields', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    // File upload — Playwright directly sets files; no AutoIt needed
    await docLibPage.uploadDocumentPDF();

    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);

    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000); // wait for cropping UI to appear
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);
    await docLibPage.scrollToTop();

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_18 — Upload without Document Name shows validation
  // ---------------------------------------------------------
  test('TC_DL_18 - Missing Document Name shows validation message', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentPDF();
    // Document Name intentionally skipped
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);
    await docLibPage.scrollToTop();

    const validationMsg = await docLibPage.getValidationMessageForDocumentNameField();
    expect(validationMsg).toBe('Please fill out this field.');

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButtonTwo();
  });


  // ---------------------------------------------------------
  // TC_DL_22 — Upload document in PNG format
  // ---------------------------------------------------------
  test('TC_DL_22 - Upload document in PNG format', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentPNG();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_22_1 — Upload document in JPG format
  // ---------------------------------------------------------
  test('TC_DL_22_1 - Upload document in JPG format', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentJPG();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_JPG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_22_2 — Upload document in CSV format
  // ---------------------------------------------------------
  test('TC_DL_22_2 - Upload document in CSV format', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentCSV();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_22_3 — Upload document in XLSX format
  // ---------------------------------------------------------
  test('TC_DL_22_3 - Upload document in XLSX format', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentXLSX();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_22_4 — Upload document in MP4 format
  // ---------------------------------------------------------
  test('TC_DL_22_4 - Upload document in MP4 format', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentMP4();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_22_5 — Upload with GIF thumbnail
  // ---------------------------------------------------------
  test('TC_DL_22_5 - Upload document with GIF thumbnail', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentMP4();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_GIF_PATH);  // GIF thumbnail
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_22_6 — Upload with JPG thumbnail
  // ---------------------------------------------------------
  test('TC_DL_22_6 - Upload document with JPG thumbnail', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentMP4();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_JPG_PATH);  // JPG thumbnail
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_25 — Missing Description shows validation message
  // ---------------------------------------------------------
  test('TC_DL_25 - Missing Description shows validation message', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentMP4();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_JPG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    // Description intentionally skipped
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);
    await docLibPage.scrollToTop();

    const validationMsg = await docLibPage.getValidationMessageForDescriptionField();
    expect(validationMsg).toBe('Please fill out this field.');

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButtonTwo();
  });


  // ---------------------------------------------------------
  // TC_DL_28 — Missing Document Attachment shows validation
  // ---------------------------------------------------------
  test('TC_DL_28 - Missing Document Attachment shows validation message', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    // File upload intentionally skipped
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_JPG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);
    await docLibPage.scrollToTop();

    const validationMsg = await docLibPage.getValidationMessageForDocumentAttachmentField();
    expect(validationMsg).toBe('Please select a file.');

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButtonTwo();
  });


  // ---------------------------------------------------------
  // TC_DL_30 — Upload with all mandatory fields + document options
  // ---------------------------------------------------------
  test('TC_DL_30 - Upload with mandatory fields and document options selected', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentJPG();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnDocumentOptionTwo();
    await docLibPage.clickOnDocumentOptionThree();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_32 — Upload with all fields + downloadable enabled
  // ---------------------------------------------------------
  test('TC_DL_32 - Upload document and enable Downloadable option', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentJPG();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnDocumentOptionTwo();
    await docLibPage.clickOnDocumentOptionThree();
    await docLibPage.clickOnDownloadableOption();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_34 — Upload with all fields + internal hashtag
  // ---------------------------------------------------------
  test('TC_DL_34 - Upload document with internal hashtag', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnUploadOption();

    await expect(page).toHaveURL(DOC_UPLOAD_URL);

    await docLibPage.uploadDocumentJPG();
    const uniqueName = `${DOCUMENT_NAME}_${Date.now()}`;
    await docLibPage.enterValueInDocumentNameField(uniqueName);
    await docLibPage.attachThumbnail(THUMBNAIL_PNG_PATH);
    await page.waitForTimeout(3000);
    await docLibPage.resizeCroppingArea();
    await docLibPage.clickOnApplyButton();

    await docLibPage.scrollDownByFiveHundred();
    await docLibPage.enterValueInDescriptionField(DESCRIPTION_TEXT);
    await docLibPage.scrollToBottom();
    await docLibPage.clickOnDocumentOptionTwo();
    await docLibPage.clickOnDocumentOptionThree();
    await docLibPage.clickOnDownloadableOption();
    await docLibPage.enterInternalHashtag(HASHTAG_TEXT);
    await page.waitForTimeout(2000);
    await docLibPage.selectInternalHashtag();
    await docLibPage.clickOnUploadButton();
    await page.waitForTimeout(3000);

    await expect(page).toHaveURL(DOC_LIBRARY_URL);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_37 — Search functionality
  // ---------------------------------------------------------
  test('TC_DL_37 - Search functionality returns correct result', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();

    await docLibPage.enterIntoSearchBox(SEARCH_VALUE);

    const searchResultText = await docLibPage.getSearchResultText();
    expect(searchResultText).toBe('ewewew test');

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_38 — Delete without selecting any content shows dialog
  // ---------------------------------------------------------
  test('TC_DL_38 - Delete without selection shows error dialog', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnDeleteOption();
    await page.waitForTimeout(2000);

    const dialogText = await docLibPage.getDialogBoxText();
    expect(dialogText).toBe('Please select at least one document creative!');

    await docLibPage.clickOnOkButton();

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_39 — Delete content and verify it no longer appears in search
  // ---------------------------------------------------------
  test('TC_DL_39 - Delete content and verify it is gone from search', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();
    await page.waitForTimeout(1000);

    await docLibPage.clickOnCheckBoxOption();
    const dynamicText = await docLibPage.getDynamicText();
    console.log('Fetched Dynamic Text:', dynamicText);
    await page.waitForTimeout(3000);

    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnDeleteOption();
    await page.waitForTimeout(2000);
    await docLibPage.clickOnOkButton();
    await page.waitForTimeout(3000);

    // Search for the deleted item — it should show No Records
    await docLibPage.enterIntoSearchBox(dynamicText);
    await docLibPage.noRecordsElementMethod();

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });


  // ---------------------------------------------------------
  // TC_DL_40 — Update Access of a document
  // NOTE: Java marked this test as dependsOnMethods TC_DL_34.
  //       In Playwright, you manage ordering via test.describe order or fixtures.
  // ---------------------------------------------------------
  test('TC_DL_40 - Update access control for a document', async ({ page }) => {
    await loginToApp(page);

    const docLibPage = new DocumentLibraryPage(page);
    await docLibPage.clickOnCommunicationTab();
    await docLibPage.clickOnDocumentLibrary();

    await docLibPage.clickOnCheckBoxOption();
    const dynamicText = await docLibPage.getDynamicText();
    console.log('Fetched Dynamic Text:', dynamicText);

    await docLibPage.clickOnActionsButton();
    await docLibPage.clickOnAccessOption();
    await page.waitForTimeout(2000);

    await docLibPage.clickOnTeamRadioButton();
    await page.waitForTimeout(2000);

    await docLibPage.clickOnPartnerCategoryButton();
    await page.waitForTimeout(2000);

    await docLibPage.clickOnCategory();
    await page.waitForTimeout(2000);

    // Close the category dropdown
    await docLibPage.clickOnPartnerCategoryButton();
    await page.waitForTimeout(2000);

    // Toggle schedule checkbox on then off
    await docLibPage.clickOnScheduleCheckbox();
    await page.waitForTimeout(2000);
    await docLibPage.clickOnScheduleCheckbox();
    await page.waitForTimeout(2000);

    // Open schedule textbox / calendar
    await docLibPage.clickOnScheduleTextbox();
    await page.waitForTimeout(2000);

    await docLibPage.selectCurrentActiveTime();

    // Uncomment one of the below if you want to select a specific date:
    // await docLibPage.selectTodayInCalendar();
    // await docLibPage.selectDateOfYourChoice(10, 8, 2025);

    await page.waitForTimeout(2000);
    await docLibPage.clickOnUpdateAccessButton();

    await page.waitForTimeout(2000);

    await docLibPage.clickOnProfileIcon();
    await docLibPage.clickOnLogoutOption();
    await docLibPage.clickOnLogoutButton();
  });

}); // end test.describe


// ============================================================
// SECTION 5 — KEY DIFFERENCES SUMMARY (for quick reference)
// ============================================================
/*
  ┌─────────────────────────────────────┬──────────────────────────────────────────────────────────┐
  │ Java / Selenium                     │ Playwright (TypeScript)                                  │
  ├─────────────────────────────────────┼──────────────────────────────────────────────────────────┤
  │ new WebDriverWait(driver, 10s)      │ Built-in auto-waiting — no explicit waits needed         │
  │ ExpectedConditions.elementToBeClick │ locator.click() — auto-waits for clickable               │
  │ driver.findElement(By.xpath(...))   │ page.locator('xpath=...')                                │
  │ element.sendKeys("text")            │ locator.fill("text")                                     │
  │ driver.getCurrentUrl()              │ page.url()                                               │
  │ Assert.assertEquals(a, b)           │ expect(a).toBe(b)                                        │
  │ JavascriptExecutor.executeScript    │ page.evaluate() or locator.evaluate()                    │
  │ js.executeScript("return           │                                                           │
  │   arguments[0].validationMessage") │ locator.evaluate(el => el.validationMessage)              │
  │ js.executeScript("arguments[0]     │                                                           │
  │   .click()")                        │ locator.click({ force: true })                           │
  │ js.executeScript("scrollIntoView") │ locator.scrollIntoViewIfNeeded()                          │
  │ AutoIt EXE for file upload dialog  │ locator.setInputFiles('path/to/file') — no dialog needed │
  │ Actions.clickAndHold + moveByOffset│ page.mouse.move/down/up                                  │
  │ Thread.sleep(3000)                  │ await page.waitForTimeout(3000) — use sparingly          │
  │ @Test(priority=N)                   │ Tests run in order defined inside test.describe           │
  │ @AfterMethod driver.quit()          │ Playwright automatically closes browser after each test  │
  └─────────────────────────────────────┴──────────────────────────────────────────────────────────┘
*/
