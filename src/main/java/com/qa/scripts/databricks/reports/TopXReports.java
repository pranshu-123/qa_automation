package com.qa.scripts.databricks.reports;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.pagefactory.databricks.reports.ReportsTopXPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;

public class TopXReports {

	private static final Logger LOGGER = Logger.getLogger(TopXReports.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ReportsTopXPageObject reportsTopXPageObject;
	private final DatePicker datePicker;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public TopXReports(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		reportsTopXPageObject = new ReportsTopXPageObject(driver);
		datePicker = new DatePicker(driver);
	}

	public void navigateToDifferentReportsTab(String tab) {
		waitExecuter.sleep(2000);
		waitExecuter.waitUntilPageFullyLoaded();
		reportsTopXPageObject.reports.click();
		waitExecuter.sleep(2000);
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.waitUntilElementClickable(reportsTopXPageObject.archivedTab);
		try {
			if(tab.equalsIgnoreCase("topx")) {
				reportsTopXPageObject.topXTab.click();
			}
			else if(tab.equalsIgnoreCase("archived")) {
				reportsTopXPageObject.archivedTab.click();
			}
			else {
				reportsTopXPageObject.scheduledTab.click();
			}
		}
		catch(ElementClickInterceptedException e) {
			e.printStackTrace();
		}
		LOGGER.info(tab.toUpperCase() + " has been selected");
	}

	public void validateReportsTab() {
		Assert.assertTrue(reportsTopXPageObject.topXTab.isDisplayed(), "Top X Tab not displayed");
		LOGGER.info("Top X tab displayed and clickable");
		Assert.assertTrue(reportsTopXPageObject.archivedTab.isDisplayed(), "Archived Tab not displayed");
		LOGGER.info("Archived tab displayed and clickable");
		Assert.assertTrue(reportsTopXPageObject.scheduledTab.isDisplayed(), "Scheduled Tab not displayed");
		LOGGER.info("Scheduled tab displayed and clickable");
	}

	public void selectRun() {
		reportsTopXPageObject.topXRunBtn.click();
	}

	public void selectSchedule() {
		reportsTopXPageObject.scheduleBtn.click();
	}


	public String createNewReportWithDefaultValues(String top) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(10000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		return top;
	}

	public void validateTopXObjects() {
		Assert.assertTrue(reportsTopXPageObject.topXRunBtn.isDisplayed(), "Top X Run button not displayed");
		LOGGER.info("Top X Run button displayed and clickable");
		Assert.assertTrue(reportsTopXPageObject.scheduleBtn.isDisplayed(), "Top X Schedule button not displayed");
		LOGGER.info("Top X Schedule button displayed and clickable");
		Assert.assertTrue(reportsTopXPageObject.copyUrl.isDisplayed(), "Top X Copy Url button not displayed");
		LOGGER.info("Top X Copy Url button displayed and clickable");
		Assert.assertTrue(reportsTopXPageObject.inputParameterHeaders.stream().iterator().next().isDisplayed());
		Assert.assertTrue(reportsTopXPageObject.inputParameterValues.stream().iterator().next().isDisplayed());
		LOGGER.info("Input parameters are displayed");
	}

	public String createNewReportForUser(String top,String value) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		reportsTopXPageObject.realUserTextArea.sendKeys(value);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(10000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		return top;
	}

	public String createNewReportForCluster(String top,String value) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		reportsTopXPageObject.cluster.click();
		reportsTopXPageObject.clusterTextArea.sendKeys(value);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(10000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		return top;
	}

	public String createNewReportForWorkspace(String top,String value) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		reportsTopXPageObject.workspaceTextArea.sendKeys(value);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(10000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		return top;
	}

	public String createNewReportWithTags(String top,String tagsType, String tagsName) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		driver.findElement(By.xpath(String.format(reportsTopXPageObject.tagsType, tagsType))).click();
		driver.findElement(By.xpath(String.format(reportsTopXPageObject.tagsName, tagsName))).click();
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(10000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		return top;
	}

	public void validateInputParameters(List<String> paramHeader,List<String> paramValue) {
		List<String> headers = reportsTopXPageObject.inputParameterHeaders.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		List<String> values = reportsTopXPageObject.inputParameterValues.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		for(String header : paramHeader) {
			Assert.assertTrue(headers.contains(header),"Header not present");
		}

		for(String value : paramValue) {
			Assert.assertTrue(values.contains(value),"Value not present");
		}
	}

	public void downloadJSON() {
		reportsTopXPageObject.threeDots.click();
		reportsTopXPageObject.downloadJson.click();
	}

	public String copyUrlAndNavigate() {
		reportsTopXPageObject.copyUrl.click();

		//get copied string from clipboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String url = null;
		try {
			url = (String) clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}

		//open in separate tab
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("popup_window = window.open('"+ url+ "');");
		waitExecuter.sleep(3000);
		js.executeScript("popup_window.close()");  
		return url;
	}

	public String createNewReportWithInvalidTopXCount(String value) {
		reportsTopXPageObject.topxTextArea.sendKeys("0.1");
		reportsTopXPageObject.newReportRunBtn.click();
		return reportsTopXPageObject.invalidInputErrorMessage.getText();
	}

	public List<String> createEmptyReport() {
		reportsTopXPageObject.newReportRunBtn.click();
		ArrayList<String> errors = new ArrayList<String>();
		errors.add(reportsTopXPageObject.invalidInputErrorMessage.getText());
		errors.add(reportsTopXPageObject.requiredFieldErrorMessage.getText());
		return errors;
	}



}
