package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.base.TestBase;

public class TestUtils extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;


	/**
	 * This method is used to take screen shot
	 *
	 * @author Pranshu
	 *
	 */
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}

	public static void waitThreadSleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getWindowHandle(){
		return driver.getWindowHandle();
	}

	public static void switchToNewTab() {
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();

		// Here we will check if child window has other child windows and will fetch the heading of the child window
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
			}
		}

	}

	public static void switchToMainWindow() {
		driver.close();
		driver.switchTo().window(getWindowHandle());
	}

	/**
	 * This method is used to get web element by providing locators type and
	 * value
	 *
	 * @param locatorType
	 *            , locatorValue
	 * @author Pranshu
	 * @return WebElement
	 */
	public static WebElement getWebElement(String locatorType, String locatorValue) {
		WebElement we = null;

		if ("ID".equals(locatorType)) {
			we = driver.findElement(By.id(locatorValue));
			System.out.println("Element found with id: " + locatorValue);
		} else if ("NAME".equals(locatorType)) {
			we = driver.findElement(By.name(locatorValue));
			System.out.println("Element found with name: " + locatorValue);
		} else if ("CLASS".equals(locatorType)) {
			we = driver.findElement(By.className(locatorValue));
			System.out.println("Element found with class name: " + locatorValue);
		} else if ("CSS".equals(locatorType)) {
			we = driver.findElement(By.cssSelector(locatorValue));
			System.out.println("Element found with css: " + locatorValue);
		} else if ("XPATH".equals(locatorType)) {
			we = driver.findElement(By.xpath(locatorValue));
			System.out.println("Element found with xpath: " + locatorValue);
		} else {
			System.err.println("Invalid Locator Type");
		}
		return we;
	}


	public static List<WebElement> getWebElements(String type, String locator) {

		type = type.toLowerCase();
		List<WebElement> we_list = new ArrayList<WebElement>();

		if (type.equals("id")) {
			we_list = driver.findElements(By.id(locator));
		} else if (type.equals("xpath")) {
			we_list = driver.findElements(By.xpath(locator));
		} else if (type.equals("css")) {
			we_list = driver.findElements(By.cssSelector(locator));
		} else if (type.equals("classname")) {
			we_list = driver.findElements(By.className(locator));
		} else if (type.equals("tagname")) {
			we_list = driver.findElements(By.tagName(locator));
		} else {
			System.out.println("Locator type not supported");
		}

		if (we_list.isEmpty()) {
			System.out.println("Element not found with :" + type + ":" + locator);
		} else {
			System.out.println("Element found with :" + type + ":" + locator);
		}
		return we_list;
	}

	public static List<WebElement> getWebElements(WebDriver driver, String type, String locator) {

		type = type.toLowerCase();
		List<WebElement> we_list = new ArrayList<WebElement>();

		if (type.equals("id")) {
			we_list = driver.findElements(By.id(locator));
		} else if (type.equals("xpath")) {
			we_list = driver.findElements(By.xpath(locator));
		} else if (type.equals("css")) {
			we_list = driver.findElements(By.cssSelector(locator));
		} else if (type.equals("classname")) {
			we_list = driver.findElements(By.className(locator));
		} else if (type.equals("tagname")) {
			we_list = driver.findElements(By.tagName(locator));
		} else {
			System.out.println("Locator type not supported");
		}

		if (we_list.isEmpty()) {
			System.out.println("Element not found with :" + type + ":" + locator);
		} else {
			System.out.println("Element found with :" + type + ":" + locator);
		}
		return we_list;
	}

	/**
	 * This method is used to click the web element
	 *
	 * @param locatorType
	 *            , locatorValue
	 * @author Pranshu
	 * @return void
	 */
	public static void clickOnElement(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Unable to click on element");
			e.printStackTrace();
		}
	}

	public static boolean isElementPresent(String type, String locator) {
		List<WebElement> we_list = getWebElements(type, locator);
		int size = we_list.size();
		if (size > 0) {
			System.out.println("Size of WebElement in dropdownmenu: " + size);
			return true;
		} else {
			return false;
		}
	}

	public static boolean isElementDisplayed(WebElement element) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			flag = true;
		} catch (NoSuchElementException e) {

		}
		return flag;
	}

	public static boolean compareStringArrays(String[] actual, String[] expected) {
		for (int i = 0; i < expected.length; i++) {
			if (!Arrays.asList(actual).contains(expected[i])) {
				return false;
			}
		}
		return true;
	}


	public static void clickWhenReady(String type, String locator){

		try{
			WebElement el = null;
			boolean bool=false;
			System.out.println("Waiting for max: "+TestUtils.IMPLICIT_WAIT+"seconds to see the element.");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if(type.equals("xpath")){
				el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
				bool=true;
			}else if (type.equals("css")) {
				el = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
				bool=true;
			}else{
				System.out.println("Locator type not supported!");
			}

			if(bool){
				System.out.println("Element clickable on webpage!");
				el.click();
			}else{
				System.out.println("Element not clickable on webpage");
			}

		}catch(Exception e){
			System.out.println("Element not clickable on webpage");
		}

	}

	public static WebElement waitAndGetWebElementByXpath(String xpath) throws Exception{
		return (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public static WebElement waitAndGetWebElementByXpath(int seconds, String xpath) throws Exception{
		return (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public static void scrollIntoView(WebElement element) throws Exception{
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public static void clickTextInTable(String type, String locator, String search_text){

		WebElement search_table = null;

		if(type.equals("ID")){
			// Grab the table
			search_table = TestUtils.getWebElement("ID", locator);
		}else if (type.equals("XPATH")) {
			// Grab the table
			search_table = TestUtils.getWebElement("XPATH", locator);
		}else{
			System.out.println("Locator type not supported");
		}

		// Now get all the TR elements from the table
		List<WebElement> search_allRows = search_table.findElements(By.tagName("tr"));

		// And iterate over them, getting the cells
		for (WebElement row : search_allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Print the contents of each cell
			for (WebElement cell : cells) {
				//System.out.println(cell.getText());
				if(cell.getText().equals(search_text)){
					cell.click();
				}
			}
		}
	}

}