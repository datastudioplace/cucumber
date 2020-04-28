package pages;

import com.cucumber.listener.Reporter;
import com.google.common.collect.Ordering;
import com.mysql.cj.core.Constants;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.internal.ArrayComparisonFailure;
import org.junit.internal.ExactComparisonCriteria;
import org.junit.internal.InexactComparisonCriteria;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BasePage extends DriverFactory {
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;
	private JavascriptExecutor js;
	private static String screenshotName;
	public static HashMap<String, String> resultMap = new HashMap<String, String>();
	private static String PASS = "PASS";
	private static String FAIL = "FAIL";


	public BasePage() throws IOException {
		this.wait = new WebDriverWait(driver, 10);
		jsExecutor = ((JavascriptExecutor) driver);
	}

	/**********************************************************************************
	 **CLICK METHODS
	 * @throws IOException 
	 **********************************************************************************/
	public void waitAndClickElement(WebElement element) throws InterruptedException, IOException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				log.info("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				log.error("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
	}

	public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
				log.info("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				log.error("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<"+ by.toString() + ">");
			}
			attempts++;
		}
	}

	public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
		try {
			tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
			list.sendKeys(textToSearchFor);
			list.sendKeys(Keys.ENTER);
			log.info("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
		} catch (Exception e) {
			log.error("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
			Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
		}
	}


	public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
		try {
			final WebDriverWait customWait = new WebDriverWait(driver, timeout);
			customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
			locator.click();
			log.info("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"+ ", using a custom Timeout of: " + timeout);
		} catch (Exception e) {
			log.error("Unable to click on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
			Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
		}
	}
	
	/**********************************************************************************/
	/**********************************************************************************/
	
	
	 /**********************************************************************************
	 **ACTION METHODS
	 **********************************************************************************/

	public void actionMoveAndClick(WebElement element) throws Exception {
		Actions ob = new Actions(driver);
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			ob.moveToElement(element).click().build().perform();
			log.info("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
			if (elementPresent == true) {
				ob.moveToElement(elementToClick).click().build().perform();
				log.info("(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
		} catch (Exception e) {
			log.error("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void actionMoveAndClickByLocator(By element) throws Exception {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (elementPresent == true) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).click().build().perform();
				log.info("Action moved and clicked on the following element, using By locator: " + "<" + element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).click().build().perform();
			log.info("(Stale Exception) - Action moved and clicked on the following element, using By locator: "+ "<" + element.toString() + ">");
		} catch (Exception e) {
			log.error("Unable to Action Move and Click on the WebElement using by locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	
	/**********************************************************************************
	 **SEND KEYS METHODS /
	 **********************************************************************************/
	public void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
		try {
			this.WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			log.info("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"+ element.toString() + ">");
		} catch (Exception e) {
			log.error("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	
	/**********************************************************************************
	 **JS METHODS & JS SCROLL
	 **********************************************************************************/
	public void scrollToElementByWebElementLocator(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
			log.info("Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			log.error("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
		}
	}

	public void jsPageScroll(int numb1, int numb2) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
			log.info("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
		} catch (Exception e) {
			log.error("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
			Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
		}
	}

	public void waitAndclickElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			log.info("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				js.executeScript("arguments[0].click();", elementPresent);
				log.info("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
			}
		} catch (NoSuchElementException e) {
			log.error("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
			Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**********************************************************************************
	 **WAIT METHODS
	 **********************************************************************************/
	public boolean WaitUntilWebElementIsVisible(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element));
			log.info("WebElement is visible using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			log.error("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			log.info("Element is visible using By locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			log.error("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementClickable(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element));
			log.info("WebElement is clickable using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			log.error("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
			return false;
		}
	}


	public boolean waitUntilPreLoadElementDissapears(By element) {
		return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**********************************************************************************
	 **PAGE METHODS
	 **********************************************************************************/
	public BasePage loadUrl(String url) throws Exception {
		driver.get(url);
		return new BasePage();
	}


	public String getCurrentURL() {
		try {
			String url = driver.getCurrentUrl();
			log.info("Found(Got) the following URL: " + url);
			return url;
		} catch (Exception e) {
			log.error("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	public String waitForSpecificPage(String urlToWaitFor) {
		try {
			String url = driver.getCurrentUrl();
			this.wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
			log.info("The current URL was: " + url + ", " + "navigated and waited for the following URL: "+ urlToWaitFor);
			return urlToWaitFor;
		} catch (Exception e) {
			log.error("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	/**********************************************************************************
	 **ALERT & POPUPS METHODS
	 **********************************************************************************/
	public void closePopups(By locator) throws InterruptedException {
		try {
			List<WebElement> elements = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			for (WebElement element : elements) {
				if (element.isDisplayed()) {
					element.click();
					this.wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
					log.info("The popup has been closed Successfully!");
				}
			}
		} catch (Exception e) {
			log.error("Exception! - could not close the popup!, Exception: " + e.toString());
			throw (e);
		}
	}

	public boolean checkPopupIsVisible() {
		try {
			@SuppressWarnings("unused")
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			log.info("A popup has been found!");
			return true;
		} catch (Exception e) {
			log.error("Error came while waiting for the alert popup to appear. " + e.getMessage());
			System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
		}
		return false;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void closeAlertPopupBox() throws AWTException, InterruptedException {
		try {
			Alert alert = this.wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (UnhandledAlertException f) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			log.error("Unable to close the popup");
			Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
    //selectUsingVisibleText/ selectUsingIndex
	/**********************************************************************************/
	public void selectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	public void selectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	
	public void deSelectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	
	public void deSelectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		select.deselectByIndex(index);
	}
	
	public void deSelectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		select.deselectByVisibleText(visibleText);
	}
	
	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement ele: elementList){
			valueList.add(ele.getText());
		}
		return valueList;
	}

	/**********************************************************************************/
	// switchToWindow//
	/**********************************************************************************/
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**********************************************************************************/
	   //isDisplayed methods
	/**********************************************************************************/

	public boolean isDisplayed(WebElement element){
		try{
			element.isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean isNotDisplayed(WebElement element){
		try{
			element.isDisplayed();
			return false;
		}
		catch(Exception e){
			return true;
		}
	}
	
	public String readValueFromElement(WebElement element){
		if(null == element){
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			return element.getText();
		}
		else{
			return null;
		}
	}
	public String getText(WebElement element){
		if(null == element){
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			return element.getText();
		}
		else{
			return null;
		}
	}

	public String getTitle(){
		return driver.getTitle();

	}

	/**
	 * This method will make sure elementToBeClickable
	 *
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will make sure invisibilityOf element
	 *
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 *
	 * @param element
	 * @param timeOutInSeconds
	 */
	/**********************************************************************************/
	// FrameToBeAvailableAndSwitchToIt
	/**********************************************************************************/
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}
	public void pageLoadTime(long timeout, TimeUnit unit){
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);

		}

	/**********************************************************************************/
	// A set of assertion methods useful for writing tests
	/**********************************************************************************/
	static public void assertTrue(String message, boolean condition) {
		if (!condition) {
			fail(message);
		}
	}

	/**
	 * Asserts that a condition is true. If it isn't it throws an
	 * {@link AssertionError} without a message.
	 *
	 * @param condition condition to be checked
	 */
	static public void assertTrue(boolean condition) {
		assertTrue(null, condition);
	}

	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param condition condition to be checked
	 */
	static public void assertFalse(String message, boolean condition) {
		assertTrue(message, !condition);
	}

	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * {@link AssertionError} without a message.
	 *
	 * @param condition condition to be checked
	 */
	static public void assertFalse(boolean condition) {
		assertFalse(null, condition);
	}

	/**
	 * Fails a test with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @see AssertionError
	 */
	static public void fail(String message) {
		if (message == null) {
			throw new AssertionError();
		}
		throw new AssertionError(message);
	}

	/**
	 * Fails a test with no message.
	 */
	static public void fail() {
		fail(null);
	}

	/**
	 * Asserts that two objects are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expected expected value
	 * @param actual actual value
	 */
	static public void assertEquals(String message, Object expected,
									Object actual) {
		if (equalsRegardingNull(expected, actual)) {
			return;
		} else if (expected instanceof String && actual instanceof String) {
			String cleanMessage = message == null ? "" : message;
			throw new ComparisonFailure(cleanMessage, (String) expected,
					(String) actual);
		} else {
			failNotEquals(message, expected, actual);
		}
	}

	private static boolean equalsRegardingNull(Object expected, Object actual) {
		if (expected == null) {
			return actual == null;
		}

		return isEquals(expected, actual);
	}

	private static boolean isEquals(Object expected, Object actual) {
		return expected.equals(actual);
	}

	/**
	 * Asserts that two objects are equal. If they are not, an
	 * {@link AssertionError} without a message is thrown. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param expected expected value
	 * @param actual the value to check against <code>expected</code>
	 */
	static public void assertEquals(Object expected, Object actual) {
		assertEquals(null, expected, actual);
	}

	/**
	 * Asserts that two objects are <b>not</b> equals. If they are, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>unexpected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param unexpected unexpected value to check
	 * @param actual the value to check against <code>unexpected</code>
	 */
	static public void assertNotEquals(String message, Object unexpected,
									   Object actual) {
		if (equalsRegardingNull(unexpected, actual)) {
			failEquals(message, actual);
		}
	}

	/**
	 * Asserts that two objects are <b>not</b> equals. If they are, an
	 * {@link AssertionError} without a message is thrown. If
	 * <code>unexpected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param unexpected unexpected value to check
	 * @param actual the value to check against <code>unexpected</code>
	 */
	static public void assertNotEquals(Object unexpected, Object actual) {
		assertNotEquals(null, unexpected, actual);
	}

	private static void failEquals(String message, Object actual) {
		String formatted = "Values should be different. ";
		if (message != null) {
			formatted = message + ". ";
		}

		formatted += "Actual: " + actual;
		fail(formatted);
	}

	/**
	 * Asserts that two longs are <b>not</b> equals. If they are, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param unexpected unexpected value to check
	 * @param actual the value to check against <code>unexpected</code>
	 */
	static public void assertNotEquals(String message, long unexpected, long actual) {
		if (unexpected == actual) {
			failEquals(message, Long.valueOf(actual));
		}
	}

	/**
	 * Asserts that two longs are <b>not</b> equals. If they are, an
	 * {@link AssertionError} without a message is thrown.
	 *
	 * @param unexpected unexpected value to check
	 * @param actual the value to check against <code>unexpected</code>
	 */
	static public void assertNotEquals(long unexpected, long actual) {
		assertNotEquals(null, unexpected, actual);
	}

	/**
	 * Asserts that two doubles are <b>not</b> equal to within a positive delta.
	 * If they are, an {@link AssertionError} is thrown with the given
	 * message. If the unexpected value is infinity then the delta value is
	 * ignored. NaNs are considered equal:
	 * <code>assertNotEquals(Double.NaN, Double.NaN, *)</code> fails
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param unexpected unexpected value
	 * @param actual the value to check against <code>unexpected</code>
	 * @param delta the maximum delta between <code>unexpected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertNotEquals(String message, double unexpected,
									   double actual, double delta) {
		if (!doubleIsDifferent(unexpected, actual, delta)) {
			failEquals(message, Double.valueOf(actual));
		}
	}

	/**
	 * Asserts that two doubles are <b>not</b> equal to within a positive delta.
	 * If they are, an {@link AssertionError} is thrown. If the unexpected
	 * value is infinity then the delta value is ignored.NaNs are considered
	 * equal: <code>assertNotEquals(Double.NaN, Double.NaN, *)</code> fails
	 *
	 * @param unexpected unexpected value
	 * @param actual the value to check against <code>unexpected</code>
	 * @param delta the maximum delta between <code>unexpected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertNotEquals(double unexpected, double actual, double delta) {
		assertNotEquals(null, unexpected, actual, delta);
	}

	/**
	 * Asserts that two floats are <b>not</b> equal to within a positive delta.
	 * If they are, an {@link AssertionError} is thrown. If the unexpected
	 * value is infinity then the delta value is ignored.NaNs are considered
	 * equal: <code>assertNotEquals(Float.NaN, Float.NaN, *)</code> fails
	 *
	 * @param unexpected unexpected value
	 * @param actual the value to check against <code>unexpected</code>
	 * @param delta the maximum delta between <code>unexpected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertNotEquals(float unexpected, float actual, float delta) {
		assertNotEquals(null, unexpected, actual, delta);
	}

	/**
	 * Asserts that two object arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expecteds</code> and <code>actuals</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds Object array or array of arrays (multi-dimensional array) with
	 * expected values.
	 * @param actuals Object array or array of arrays (multi-dimensional array) with
	 * actual values
	 */
	public static void assertArrayEquals(String message, Object[] expecteds,
										 Object[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two object arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown. If <code>expected</code> and
	 * <code>actual</code> are <code>null</code>, they are considered
	 * equal.
	 *
	 * @param expecteds Object array or array of arrays (multi-dimensional array) with
	 * expected values
	 * @param actuals Object array or array of arrays (multi-dimensional array) with
	 * actual values
	 */
	public static void assertArrayEquals(Object[] expecteds, Object[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two boolean arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expecteds</code> and <code>actuals</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds boolean array with expected values.
	 * @param actuals boolean array with expected values.
	 */
	public static void assertArrayEquals(String message, boolean[] expecteds,
										 boolean[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two boolean arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown. If <code>expected</code> and
	 * <code>actual</code> are <code>null</code>, they are considered
	 * equal.
	 *
	 * @param expecteds boolean array with expected values.
	 * @param actuals boolean array with expected values.
	 */
	public static void assertArrayEquals(boolean[] expecteds, boolean[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two byte arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds byte array with expected values.
	 * @param actuals byte array with actual values
	 */
	public static void assertArrayEquals(String message, byte[] expecteds,
										 byte[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two byte arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds byte array with expected values.
	 * @param actuals byte array with actual values
	 */
	public static void assertArrayEquals(byte[] expecteds, byte[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two char arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds char array with expected values.
	 * @param actuals char array with actual values
	 */
	public static void assertArrayEquals(String message, char[] expecteds,
										 char[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two char arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds char array with expected values.
	 * @param actuals char array with actual values
	 */
	public static void assertArrayEquals(char[] expecteds, char[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two short arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds short array with expected values.
	 * @param actuals short array with actual values
	 */
	public static void assertArrayEquals(String message, short[] expecteds,
										 short[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two short arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds short array with expected values.
	 * @param actuals short array with actual values
	 */
	public static void assertArrayEquals(short[] expecteds, short[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two int arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds int array with expected values.
	 * @param actuals int array with actual values
	 */
	public static void assertArrayEquals(String message, int[] expecteds,
										 int[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two int arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds int array with expected values.
	 * @param actuals int array with actual values
	 */
	public static void assertArrayEquals(int[] expecteds, int[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two long arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds long array with expected values.
	 * @param actuals long array with actual values
	 */
	public static void assertArrayEquals(String message, long[] expecteds,
										 long[] actuals) throws ArrayComparisonFailure {
		internalArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two long arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds long array with expected values.
	 * @param actuals long array with actual values
	 */
	public static void assertArrayEquals(long[] expecteds, long[] actuals) {
		assertArrayEquals(null, expecteds, actuals);
	}

	/**
	 * Asserts that two double arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds double array with expected values.
	 * @param actuals double array with actual values
	 * @param delta the maximum delta between <code>expecteds[i]</code> and
	 * <code>actuals[i]</code> for which both numbers are still
	 * considered equal.
	 */
	public static void assertArrayEquals(String message, double[] expecteds,
										 double[] actuals, double delta) throws ArrayComparisonFailure {
		new InexactComparisonCriteria(delta).arrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two double arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds double array with expected values.
	 * @param actuals double array with actual values
	 * @param delta the maximum delta between <code>expecteds[i]</code> and
	 * <code>actuals[i]</code> for which both numbers are still
	 * considered equal.
	 */
	public static void assertArrayEquals(double[] expecteds, double[] actuals, double delta) {
		assertArrayEquals(null, expecteds, actuals, delta);
	}

	/**
	 * Asserts that two float arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds float array with expected values.
	 * @param actuals float array with actual values
	 * @param delta the maximum delta between <code>expecteds[i]</code> and
	 * <code>actuals[i]</code> for which both numbers are still
	 * considered equal.
	 */
	public static void assertArrayEquals(String message, float[] expecteds,
										 float[] actuals, float delta) throws ArrayComparisonFailure {
		new InexactComparisonCriteria(delta).arrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two float arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expecteds float array with expected values.
	 * @param actuals float array with actual values
	 * @param delta the maximum delta between <code>expecteds[i]</code> and
	 * <code>actuals[i]</code> for which both numbers are still
	 * considered equal.
	 */
	public static void assertArrayEquals(float[] expecteds, float[] actuals, float delta) {
		assertArrayEquals(null, expecteds, actuals, delta);
	}

	/**
	 * Asserts that two object arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expecteds</code> and <code>actuals</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds Object array or array of arrays (multi-dimensional array) with
	 * expected values.
	 * @param actuals Object array or array of arrays (multi-dimensional array) with
	 * actual values
	 */
	private static void internalArrayEquals(String message, Object expecteds,
											Object actuals) throws ArrayComparisonFailure {
		new ExactComparisonCriteria().arrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two doubles are equal to within a positive delta.
	 * If they are not, an {@link AssertionError} is thrown with the given
	 * message. If the expected value is infinity then the delta value is
	 * ignored. NaNs are considered equal:
	 * <code>assertEquals(Double.NaN, Double.NaN, *)</code> passes
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expected expected value
	 * @param actual the value to check against <code>expected</code>
	 * @param delta the maximum delta between <code>expected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertEquals(String message, double expected,
									double actual, double delta) {
		if (doubleIsDifferent(expected, actual, delta)) {
			failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
		}
	}

	/**
	 * Asserts that two floats are equal to within a positive delta.
	 * If they are not, an {@link AssertionError} is thrown with the given
	 * message. If the expected value is infinity then the delta value is
	 * ignored. NaNs are considered equal:
	 * <code>assertEquals(Float.NaN, Float.NaN, *)</code> passes
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expected expected value
	 * @param actual the value to check against <code>expected</code>
	 * @param delta the maximum delta between <code>expected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertEquals(String message, float expected,
									float actual, float delta) {
		if (floatIsDifferent(expected, actual, delta)) {
			failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
		}
	}

	/**
	 * Asserts that two floats are <b>not</b> equal to within a positive delta.
	 * If they are, an {@link AssertionError} is thrown with the given
	 * message. If the unexpected value is infinity then the delta value is
	 * ignored. NaNs are considered equal:
	 * <code>assertNotEquals(Float.NaN, Float.NaN, *)</code> fails
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param unexpected unexpected value
	 * @param actual the value to check against <code>unexpected</code>
	 * @param delta the maximum delta between <code>unexpected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertNotEquals(String message, float unexpected,
									   float actual, float delta) {
		if (!floatIsDifferent(unexpected, actual, delta)) {
			failEquals(message, Float.valueOf(actual));
		}
	}

	static private boolean doubleIsDifferent(double d1, double d2, double delta) {
		if (Double.compare(d1, d2) == 0) {
			return false;
		}
		if ((Math.abs(d1 - d2) <= delta)) {
			return false;
		}

		return true;
	}

	static private boolean floatIsDifferent(float f1, float f2, float delta) {
		if (Float.compare(f1, f2) == 0) {
			return false;
		}
		if ((Math.abs(f1 - f2) <= delta)) {
			return false;
		}

		return true;
	}

	/**
	 * Asserts that two longs are equal. If they are not, an
	 * {@link AssertionError} is thrown.
	 *
	 * @param expected expected long value.
	 * @param actual actual long value
	 */
	static public void assertEquals(long expected, long actual) {
		assertEquals(null, expected, actual);
	}

	/**
	 * Asserts that two longs are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expected long expected value.
	 * @param actual long actual value
	 */
	static public void assertEquals(String message, long expected, long actual) {
		if (expected != actual) {
			failNotEquals(message, Long.valueOf(expected), Long.valueOf(actual));
		}
	}

	/**
	 * @deprecated Use
	 *             <code>assertEquals(double expected, double actual, double delta)</code>
	 *             instead
	 */
	@Deprecated
	static public void assertEquals(double expected, double actual) {
		assertEquals(null, expected, actual);
	}

	/**
	 * @deprecated Use
	 *             <code>assertEquals(String message, double expected, double actual, double delta)</code>
	 *             instead
	 */
	@Deprecated
	static public void assertEquals(String message, double expected,
									double actual) {
		fail("Use assertEquals(expected, actual, delta) to compare floating-point numbers");
	}

	/**
	 * Asserts that two doubles are equal to within a positive delta.
	 * If they are not, an {@link AssertionError} is thrown. If the expected
	 * value is infinity then the delta value is ignored.NaNs are considered
	 * equal: <code>assertEquals(Double.NaN, Double.NaN, *)</code> passes
	 *
	 * @param expected expected value
	 * @param actual the value to check against <code>expected</code>
	 * @param delta the maximum delta between <code>expected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */
	static public void assertEquals(double expected, double actual, double delta) {
		assertEquals(null, expected, actual, delta);
	}

	/**
	 * Asserts that two floats are equal to within a positive delta.
	 * If they are not, an {@link AssertionError} is thrown. If the expected
	 * value is infinity then the delta value is ignored. NaNs are considered
	 * equal: <code>assertEquals(Float.NaN, Float.NaN, *)</code> passes
	 *
	 * @param expected expected value
	 * @param actual the value to check against <code>expected</code>
	 * @param delta the maximum delta between <code>expected</code> and
	 * <code>actual</code> for which both numbers are still
	 * considered equal.
	 */

	static public void assertEquals(float expected, float actual, float delta) {
		assertEquals(null, expected, actual, delta);
	}

	/**
	 * Asserts that an object isn't null. If it is an {@link AssertionError} is
	 * thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param object Object to check or <code>null</code>
	 */
	static public void assertNotNull(String message, Object object) {
		assertTrue(message, object != null);
	}

	/**
	 * Asserts that an object isn't null. If it is an {@link AssertionError} is
	 * thrown.
	 *
	 * @param object Object to check or <code>null</code>
	 */
	static public void assertNotNull(Object object) {
		assertNotNull(null, object);
	}

	/**
	 * Asserts that an object is null. If it is not, an {@link AssertionError}
	 * is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param object Object to check or <code>null</code>
	 */
	static public void assertNull(String message, Object object) {
		if (object == null) {
			return;
		}
		failNotNull(message, object);
	}

	/**
	 * Asserts that an object is null. If it isn't an {@link AssertionError} is
	 * thrown.
	 *
	 * @param object Object to check or <code>null</code>
	 */
	static public void assertNull(Object object) {
		assertNull(null, object);
	}

	static private void failNotNull(String message, Object actual) {
		String formatted = "";
		if (message != null) {
			formatted = message + " ";
		}
		fail(formatted + "expected null, but was:<" + actual + ">");
	}

	/**
	 * Asserts that two objects refer to the same object. If they are not, an
	 * {@link AssertionError} is thrown with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expected the expected object
	 * @param actual the object to compare to <code>expected</code>
	 */
	static public void assertSame(String message, Object expected, Object actual) {
		if (expected == actual) {
			return;
		}
		failNotSame(message, expected, actual);
	}

	/**
	 * Asserts that two objects refer to the same object. If they are not the
	 * same, an {@link AssertionError} without a message is thrown.
	 *
	 * @param expected the expected object
	 * @param actual the object to compare to <code>expected</code>
	 */
	static public void assertSame(Object expected, Object actual) {
		assertSame(null, expected, actual);
	}

	/**
	 * Asserts that two objects do not refer to the same object. If they do
	 * refer to the same object, an {@link AssertionError} is thrown with the
	 * given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param unexpected the object you don't expect
	 * @param actual the object to compare to <code>unexpected</code>
	 */
	static public void assertNotSame(String message, Object unexpected,
									 Object actual) {
		if (unexpected == actual) {
			failSame(message);
		}
	}

	/**
	 * Asserts that two objects do not refer to the same object. If they do
	 * refer to the same object, an {@link AssertionError} without a message is
	 * thrown.
	 *
	 * @param unexpected the object you don't expect
	 * @param actual the object to compare to <code>unexpected</code>
	 */
	static public void assertNotSame(Object unexpected, Object actual) {
		assertNotSame(null, unexpected, actual);
	}

	static private void failSame(String message) {
		String formatted = "";
		if (message != null) {
			formatted = message + " ";
		}
		fail(formatted + "expected not same");
	}

	static private void failNotSame(String message, Object expected,
									Object actual) {
		String formatted = "";
		if (message != null) {
			formatted = message + " ";
		}
		fail(formatted + "expected same:<" + expected + "> was not:<" + actual
				+ ">");
	}

	static private void failNotEquals(String message, Object expected,
									  Object actual) {
		fail(format(message, expected, actual));
	}

	static String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null && !message.equals("")) {
			formatted = message + " ";
		}
		String expectedString = String.valueOf(expected);
		String actualString = String.valueOf(actual);
		if (expectedString.equals(actualString)) {
			return formatted + "expected: "
					+ formatClassAndValue(expected, expectedString)
					+ " but was: " + formatClassAndValue(actual, actualString);
		} else {
			return formatted + "expected:<" + expectedString + "> but was:<"
					+ actualString + ">";
		}
	}

	private static String formatClassAndValue(Object value, String valueString) {
		String className = value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}

	/**
	 * Asserts that two object arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expecteds</code> and <code>actuals</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expecteds Object array or array of arrays (multi-dimensional array) with
	 * expected values.
	 * @param actuals Object array or array of arrays (multi-dimensional array) with
	 * actual values
	 * @deprecated use assertArrayEquals
	 */
	@Deprecated
	public static void assertEquals(String message, Object[] expecteds,
									Object[] actuals) {
		assertArrayEquals(message, expecteds, actuals);
	}

	/**
	 * Asserts that two object arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown. If <code>expected</code> and
	 * <code>actual</code> are <code>null</code>, they are considered
	 * equal.
	 *
	 * @param expecteds Object array or array of arrays (multi-dimensional array) with
	 * expected values
	 * @param actuals Object array or array of arrays (multi-dimensional array) with
	 * actual values
	 * @deprecated use assertArrayEquals
	 */
	@Deprecated
	public static void assertEquals(Object[] expecteds, Object[] actuals) {
		assertArrayEquals(expecteds, actuals);
	}

	/**
	 * Asserts that <code>actual</code> satisfies the condition specified by
	 * <code>matcher</code>. If not, an {@link AssertionError} is thrown with
	 * information about the matcher and failing value. Example:
	 *
	 * <pre>
	 *   assertThat(0, is(1)); // fails:
	 *     // failure message:
	 *     // expected: is &lt;1&gt;
	 *     // got value: &lt;0&gt;
	 *   assertThat(0, is(not(1))) // passes
	 * </pre>
	 *
	 * <code>org.hamcrest.Matcher</code> does not currently document the meaning
	 * of its type parameter <code>T</code>.  This method assumes that a matcher
	 * typed as <code>Matcher&lt;T&gt;</code> can be meaningfully applied only
	 * to values that could be assigned to a variable of type <code>T</code>.
	 *
	 * @param <T> the static type accepted by the matcher (this can flag obvious
	 * compile-time problems such as {@code assertThat(1, is("a"))}
	 * @param actual the computed value being compared
	 * @param matcher an expression, built of {@link Matcher}s, specifying allowed
	 * values
	 * @see org.hamcrest.CoreMatchers
	 * @see org.hamcrest.MatcherAssert
	 */
	public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
		assertThat("", actual, matcher);
	}

	/**
	 * Asserts that <code>actual</code> satisfies the condition specified by
	 * <code>matcher</code>. If not, an {@link AssertionError} is thrown with
	 * the reason and information about the matcher and failing value. Example:
	 *
	 * <pre>
	 *   assertThat(&quot;Help! Integers don't work&quot;, 0, is(1)); // fails:
	 *     // failure message:
	 *     // Help! Integers don't work
	 *     // expected: is &lt;1&gt;
	 *     // got value: &lt;0&gt;
	 *   assertThat(&quot;Zero is one&quot;, 0, is(not(1))) // passes
	 * </pre>
	 *
	 * <code>org.hamcrest.Matcher</code> does not currently document the meaning
	 * of its type parameter <code>T</code>.  This method assumes that a matcher
	 * typed as <code>Matcher&lt;T&gt;</code> can be meaningfully applied only
	 * to values that could be assigned to a variable of type <code>T</code>.
	 *
	 * @param reason additional information about the error
	 * @param <T> the static type accepted by the matcher (this can flag obvious
	 * compile-time problems such as {@code assertThat(1, is("a"))}
	 * @param actual the computed value being compared
	 * @param matcher an expression, built of {@link Matcher}s, specifying allowed
	 * values
	 * @see org.hamcrest.CoreMatchers
	 * @see org.hamcrest.MatcherAssert
	 */
	public static <T> void assertThat(String reason, T actual,
									  Matcher<? super T> matcher) {
		MatcherAssert.assertThat(reason, actual, matcher);
	}




	/**********************************************************************************/
	// EXTENT REPORT
	/**********************************************************************************/
	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}

	public static void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		screenshotName = returnDateStamp(".jpg");

		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName));

		Reporter.addStepLog("Taking a screenshot!");
		Reporter.addStepLog("<br>");
		Reporter.addStepLog("<a target=\"_blank\", href="+ returnScreenshotName() + "><img src="+ returnScreenshotName()+ " height=200 width=300></img></a>");
	}

	public static String returnScreenshotName() {
		return (System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName).toString();
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;

			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

		} finally {
			is.close();
			os.close();
		}
	}

	public static void copyLatestExtentReport() throws IOException {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
		File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
		copyFileUsingStream(source, dest);
	}

	/**********************************************************************************/
	//Bhanu Generic wait
	/**********************************************************************************/
	public String readValueFromInput(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		String value = element.getAttribute("value");
		log.info("weblement valus is.."+value);
		return value;
	}

	protected String getDisplayText(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		return element.getText();
	}

	public static synchronized String getElementText( WebElement element) {
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			log.info("Element not found " + ex);

		}
		return elementText;
	}
	/**********************************************************************************/

	/**********************************************************************************/
	public Boolean isSelected(WebElement element, String info) {
		Boolean selected = false;
		if (element != null) {
			selected = element.isSelected();
			if (selected)
				log.info("Element :: " + info + " is selected");
			else
				log.error("Element :: " + info + " is already selected");
		}
		return selected;
	}

	/**********************************************************************************/
	public Boolean isEnabled(WebElement element, String info) {
		Boolean enabled = false;
		if (element != null) {
			enabled = element.isEnabled();
			if (enabled)
				log.info("Element :: " + info + " is Enabled");
			else
				log.error("Element :: " + info + " is Disabled");
		}
		return enabled;
	}



	/**********************************************************************************/
	public By getByType(String locator) {
		By by = null;
		String locatorType = locator.split("=>")[0];
		locator = locator.split("=>")[1];
		try {
			if (locatorType.contains("id")) {
				by = By.id(locator);
			} else if (locatorType.contains("name")) {
				by = By.name(locator);
			} else if (locatorType.contains("xpath")) {
				by = By.xpath(locator);
			} else if (locatorType.contains("css")) {
				by = By.cssSelector(locator);
			} else if (locatorType.contains("class")) {
				by = By.className(locator);
			} else if (locatorType.contains("tag")) {
				by = By.tagName(locator);
			} else if (locatorType.contains("link")) {
				by = By.linkText(locator);
			} else if (locatorType.contains("partiallink")) {
				by = By.partialLinkText(locator);
			} else {
				log.info("Locator type not supported");
			}
		} catch (Exception e) {
			log.error("By type not found with: " + locatorType);
		}
		return by;
	}
	/**********************************************************************************/
	public void refresh() {
		driver.navigate().refresh();
		log.info("The Current Browser location was Refreshed...");
		//Util.sleep(Constant.explicitWait00, "The Current Browser location was Refreshed...");
	}

	public String getURL() {
		String url = driver.getCurrentUrl();
		log.info("Current URL is :: " + url);
		return url;
	}

	public void navigateBrowserBack() {
		driver.navigate().back();
		log.info("Navigate back");
	}

	public void navigateBrowserForward() {
		driver.navigate().back();
		log.info("Navigate back");
	}

	/**********************************************************************************/
	/***
	 * Sleep for specified number of milliseconds
	 * @param msec
	 * @param info
	 */
	public static void sleep(long msec, String info) {
		if (info != null) {
			log.info("Waiting " + (msec * .001) + " seconds :: " + info);
		}
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Sleep for specified number of milliseconds
	 * @param msec
	 */
	public static void sleep(long msec) {
		sleep(msec, null);
	}

	/***
	 *
	 * @param methodName
	 * @param browserName
	 * @return
	 */
	public static String getScreenshotName(String methodName, String browserName) {
		String localDateTime = getCurrentDateTime();
		StringBuilder name = new StringBuilder().append(browserName)
				.append("_")
				.append(methodName)
				.append("_")
				.append(localDateTime)
				.append(".png");
		return name.toString();
	}

	/***
	 * Get Random number within specified range
	 * @param min
	 * @param max
	 * @return a random number
	 */
	public static int getRandomNumber(int min, int max) {
		int diff = max - min;
		int randomNum = (int)(min + Math.random() * diff);
		log.info("Random Number :: " + randomNum +
				" within range :: " + min + " and :: " + max);
		return randomNum;
	}

	/***
	 * Get Random number within specified range
	 * @param number
	 * @return a random number
	 */
	public static int getRandomNumber(int number) {
		return getRandomNumber(1, number);
	}

	/***
	 * Get random unique string with specified length
	 * @param length
	 * @return a unique string
	 */
	public static String getRandomString(int length) {
		StringBuilder sbuilder = new StringBuilder();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i<length; i++) {
			int index = (int) (Math.random() * chars.length());
			sbuilder.append(chars.charAt(index));
		}
		String randomString = sbuilder.toString();
		log.info("Random string with length :: "
				+ length + " is :: " + randomString);
		return randomString;
	}

	/***
	 * Get random unique string with 10 characters length
	 * @return a unique string
	 */
	public static String getRandomString() {
		return getRandomString(10);
	}

	/***
	 * Get simple date as string in the specified format
	 * @param format MMddyy MMddyyyy
	 * @return date as string type
	 */
	public static String getSimpleDateFormat(String format){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formattedDate = formatter.format(date);
		log.info("Date with format :: " + format + " :: " + formattedDate);
		return formattedDate;
	}

	/***
	 * Get simple date time as string
	 * @return date time as string type
	 */
	public static String getCurrentDateTime(){
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		String date = formatter.format(currentDate.getTime()).replace("/", "_");
		date = date.replace(":", "_");
		log.info("Date and Time :: " + date);
		return date;
	}

	/**
	 * Checks whether actual String contains expected string and prints both in log
	 * @param actualText - actual Text picked up from application under Test
	 * @param expText - expected Text to be checked with actual text
	 * @return boolean result
	 */
	public static boolean verifyTextContains(String actualText, String expText) {
		if (actualText.toLowerCase().contains(expText.toLowerCase())){
			log.info("Actual Text From Web Application UI   --> : "+ actualText);
			log.info("Expected Text From Web Application UI --> : "+ expText);
			log.info("### Verification Contains !!!");
			return true;
		}
		else{
			log.error("Actual Text From Web Application UI   --> : "+ actualText);
			log.error("Expected Text From Web Application UI --> : "+ expText);
			log.error("### Verification DOES NOT Contains !!!");
			return false;
		}

	}

	/**
	 * Checks whether actual string matches with expected string and prints both in log
	 * @param actualText - actual Text picked up from application under Test
	 * @param expText - expected Text to be matched with actual text
	 * @return
	 */
	public static boolean verifyTextMatch(String actualText, String expText) {
		if (actualText.equals(expText)){
			log.info("Actual Text From Web Application UI   --> : "+ actualText);
			log.info("Expected Text From Web Application UI --> : "+ expText);
			log.info("### Verification MATCHED !!!");
			return true;
		}else{
			log.error("Actual Text From Web Application UI   --> : "+ actualText);
			log.error("Expected Text From Web Application UI --> : "+ expText);
			log.error("### Verification DOES NOT MATCH !!!");
			return false;
		}
	}

	/***
	 * Verify List is not empty
	 * @param actualList - actual list that needs to be verified
	 * @return
	 */
	public static Boolean verifyListNotEmpty(List actualList) {
		int listSize = actualList.size();
		log.info("Size of list :: " + listSize);
		if (listSize > 0) {
			log.info("List is not empty");
			return true;
		} else {
			log.error("List is empty");
			return false;
		}
	}

	/**
	 * Verify actual list contains items of the expected list
	 *
	 * @param actList
	 * @param expList
	 * @return
	 */
	public static Boolean verifyListContains(List<String> actList, List<String> expList) {
		int expListSize = expList.size();
		for (int i = 0; i < expListSize; i++) {
			if (!actList.contains(expList.get(i))) {
				return false;
			}
		}
		log.info("Actual List Contains Expected List !!!");
		return true;
	}

	/***
	 * Verify actual list matches expected list
	 * @param actList
	 * @param expList
	 * @return
	 */
	public static Boolean verifyListMatch(List<String> actList, List<String> expList) {
		boolean found = false;
		int actListSize = actList.size();
		int expListSize = expList.size();
		if (actListSize != expListSize) {
			return false;
		}

		for (int i = 0; i < actListSize; i++) {
			found = false;
			for (int j = 0; j < expListSize; j++) {
				if (verifyTextMatch(actList.get(i), expList.get(j))) {
					found = true;
					break;
				}
			}
		}
		if (found) {
			log.info("Actual List Matches Expected List !!!");
			return true;
		}
		else {
			log.error("Actual List DOES NOT Match Expected List !!!");
			return false;
		}
	}

	/**
	 * Verifies item is present in the list
	 * @param actList
	 * @param item
	 * @return boolean result
	 */
	public static Boolean verifyItemPresentInList(List<String> actList, String item){
		int actListSize = actList.size();
		for (int i = 0; i < actListSize; i++) {
			if (!actList.contains(item)) {
				log.error("Item is NOT present in List !!!");
				return false;
			}
		}
		log.info("Item is present in List !!!");
		return true;
	}

	/**
	 * Verify if list is in ascending order
	 * @param list
	 * @return boolean result
	 */
	public static boolean isListAscendingOrder(List<Long> list){
		boolean sorted = Ordering.natural().isOrdered(list);
		return sorted;
	}
	/**********************************************************************************/

	/**********************************************************************************/
	public List<WebElement> getElementList(String locator, String info) {
		List<WebElement> elementList = new ArrayList<WebElement>();
		By byType = getByType(locator);
		try {
			elementList = driver.findElements(byType);
			if (elementList.size() > 0) {
				log.info("Element List found with: " + locator);
			} else {
				log.info("Element List not found with: " + locator);
			}
		} catch (Exception e) {
			log.error("Element List not found with: " + locator);
			e.printStackTrace();
		}
		return elementList;
	}

	/***
	 * Check if element is present
	 * @param locator locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @return boolean if element is present or not
	 */
	public boolean isElementPresent(String locator, String info) {
		List<WebElement> elementList = getElementList(locator, info);
		int size = elementList.size();
		if (size > 0) {
			log.info("Element " + info + " Present with locator " + locator);
			return true;
		} else {
			log.info("Element " + info + " Not Present with locator " + locator);
			return false;
		}
	}

	/***
	 * Click element using JavaScript
	 * @param element - WebElement to perform Click operation
	 * @param info - Information about element
	 */
	public void javascriptClick(WebElement element, String info) {
		try {
			js.executeScript("arguments[0].click();", element);
			log.info("Clicked on :: " + info);
		} catch (Exception e) {
			log.error("Cannot click on :: " + info);
		}
	}

	/***
	 * Click element when element is clickable
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param timeout - Duration to try before timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebElement element = null;
			log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

			WebDriverWait wait = new WebDriverWait(driver, 15);
			element = wait.until(
					ExpectedConditions.elementToBeClickable(locator));
			element.click();
			log.info("Element clicked on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
	}

	/***
	 * Send Keys to element
	 * @param element - WebElement to send data
	 * @param data - Data to send
	 * @param info - Information about element
	 * @param clear - True if you want to clear the field before sending data
	 */
	public void sendData(WebElement element, String data, String info, Boolean clear) {
		try {
			if (clear) {
				element.clear();
			}
			//Util.sleep(1000, "Waiting Before Entering Data");
			element.sendKeys(data);
			log.info("Send Keys on element :: " + info + " with data :: " + data);
		} catch (Exception e) {
			log.error("Cannot send keys on element :: " + info + " with data :: " + data);
		}
	}

	/**
	 * Get text of a web element
	 *
	 * @param element - WebElement to perform click action
	 * @param info    - Information about element
	 */
	public String getText(WebElement element, String info) {
		System.out.println("Getting Text on element :: " + info);
		String text = null;
		text = element.getText();
		if (text.length() == 0) {
			text = element.getAttribute("innerText");
		}
		if (!text.isEmpty()) {
			log.info("The text is : " + text);
		} else {
			log.error("Text Not Found");
		}
		return text.trim();
	}

	/**
	 * Check if element is enabled
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean isEnabledOne(WebElement element, String info) {
		Boolean enabled = false;
		if (element != null) {
			enabled = element.isEnabled();
			if (enabled)
				log.info("Element :: " + info + " is Enabled");
			else
				log.info("Element :: " + info + " is Disabled");
		}
		return enabled;
	}

	/**
	 * Check if element is displayed
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean isDisplayed(WebElement element, String info) {
		Boolean displayed = false;
		if (element != null) {
			displayed = element.isDisplayed();
			if (displayed)
				log.info("Element :: " + info + " is displayed");
			else
				log.info("Element :: " + info + " is NOT displayed");
		}
		return displayed;
	}

	/**
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean isSelectedOne(WebElement element, String info) {
		Boolean selected = false;
		if (element != null) {
			selected = element.isSelected();
			if (selected)
				log.info("Element :: " + info + " is selected");
			else
				log.info("Element :: " + info + " is already selected");
		}
		return selected;
	}

	/**
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean Submit(WebElement element, String info) {
		if (element != null) {
			element.submit();
			log.info("Element :: " + info + " is submitted");
			return true;
		} else
			return false;
	}
	/**
	 * @param element
	 * @param attribute
	 * @return
	 */
	public String getElementAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElement(String locator, int timeout) {
		By byType = getByType(locator);
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(
					ExpectedConditions.visibilityOfElementLocated(byType));
			log.info("Element appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return element;
	}

	/***
	 * Wait for element to be clickable
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param timeout - Duration to try before timeout
	 */
	public WebElement waitForElementToBeClickable(String locator, int timeout) {
		By byType = getByType(locator);
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

			WebDriverWait wait = new WebDriverWait(driver, 15);
			element = wait.until(
					ExpectedConditions.elementToBeClickable(byType));
			log.info("Element is clickable on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return element;
	}

	/**
	 *
	 */
	public boolean waitForLoading(String locator, long timeout) {
		By byType = getByType(locator);
		boolean elementInvisible = false;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			elementInvisible = wait.until(
					ExpectedConditions.invisibilityOfElementLocated(byType));
			log.info("Element appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return elementInvisible;
	}

	/**
	 * @param element
	 * @param optionToSelect
	 */
	public void selectOption(WebElement element, String optionToSelect) {
		Select sel = new Select(element);
		sel.selectByVisibleText(optionToSelect);
		log.info("Selected option : " + optionToSelect);
	}

	/**
	 * get Selected drop down value
	 *
	 * @param element
	 * @return
	 */
	public String getSelectDropDownValue(WebElement element) {
		Select sel = new Select(element);
		return sel.getFirstSelectedOption().getText();
	}

	/**
	 * @param element
	 * @param optionToVerify
	 */
	public boolean isOptionExists(WebElement element, String optionToVerify) {
		Select sel = new Select(element);
		boolean exists = false;
		List<WebElement> optList = sel.getOptions();
		for (int i = 0; i < optList.size(); i++) {
			String text = getText(optList.get(i), "Option Text");
			if (text.matches(optionToVerify)) {
				exists = true;
				break;
			}
		}
		if (exists) {
			log.info("Selected Option : " + optionToVerify + " exist");
		} else {
			log.info("Selected Option : " + optionToVerify + " does not exist");
		}
		return exists;
	}

	public void DoubleClick(WebElement element, String info) {
		Actions action = new Actions(driver);
		action.doubleClick(element);
		log.info("Double Clicked on :: " + info);
		action.perform();
	}

	/**
	 * @param key
	 */
	public void keyPress(Keys key, String info) {
		Actions action = new Actions(driver);
		action.keyDown(key).build().perform();
		log.info("Key Pressed :: " + info);
	}

	/**********************************************************************************/
	//markFinal
	/**********************************************************************************/
	public static void markFinal(String testName, boolean result, String resultMessage) {
		testName = testName.toLowerCase();
		String mapKey = testName + "." + resultMessage;
		try {
			if (result) {
				setStatus(mapKey, PASS);
			} else {
				setStatus(mapKey, FAIL);
			}
		} catch (Exception e) {
			log.error("Exception Occurred...");
			setStatus(mapKey, FAIL);
			e.printStackTrace();
		}

		ArrayList<String> resultList = new ArrayList<String>();

		for (String key: resultMap.keySet()) {
			resultList.add(resultMap.get(key));
		}

		for (int i = 0; i < resultList.size(); i++) {
			if (resultList.contains(FAIL)) {
				log.error("Test Method Failed");
				Assert.assertTrue(false);
			} else {
				log.info("Test Method Successful");
				Assert.assertTrue(true);
			}

		}

	}

	private static void setStatus(String mapKey, String fail) {
	}
}





