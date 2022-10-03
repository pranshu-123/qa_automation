package com.qa.scripts.databricks.reports;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qa.pagefactory.databricks.reports.ReportsTopXPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.TestUtils;
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
		waitExecuter.waitUntilElementClickable(reportsTopXPageObject.reports);
		try{reportsTopXPageObject.reports.click();}
		catch (Exception e ){
			reportsTopXPageObject.reports.click();
		}
		waitExecuter.sleep(1000);
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
		waitExecuter.sleep(1000);
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
		waitExecuter.sleep(12000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.topXRunBtn);
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
		waitExecuter.sleep(2000);
		reportsTopXPageObject.realUserTextArea.sendKeys(value,Keys.ENTER);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(12000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		driver.navigate().refresh();
		return top;
	}

	public String[] createNewReportForCluster(String top) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		waitExecuter.sleep(2000);
		reportsTopXPageObject.cluster.click();
		waitExecuter.sleep(1000);
		reportsTopXPageObject.clusterTextArea.click();
		waitExecuter.sleep(3000);
		reportsTopXPageObject.clusterTextArea.sendKeys("a"); //random value entered, so to open up the cluster list
		reportsTopXPageObject.clusterList.get(1).click();
		String cluster = reportsTopXPageObject.addedCluster.getText();
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(12000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		driver.navigate().refresh();
		String[] val = {top,cluster};
		return val;
	}

	public String createNewReportForWorkspace(String top,String value) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		waitExecuter.sleep(2000);
		try {
		reportsTopXPageObject.workspaceTextArea.sendKeys(value,Keys.ENTER);}
		catch (Exception e ){
		}
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(12000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		driver.navigate().refresh();
		return top;
	}

	public ArrayList<String> createNewReportWithTags(String top) {
		ArrayList<String> al = new ArrayList<String>();
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		JavaScriptExecuter.scrollViewWithYAxis(driver, 600);
		al.add(top);
		waitExecuter.sleep(2000);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(12000);
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
		return al;
	}

	public void validateInputParameters(List<String> paramHeader,List<String> paramValue) {
		waitExecuter.sleep(8000);
		List<String> headers = reportsTopXPageObject.inputParameterHeaders.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		List<String> values = reportsTopXPageObject.inputParameterValues.stream()
				.map(el -> el.getText().toLowerCase()).collect(Collectors.toList());

		if(reportsTopXPageObject.inputParameterTagsValues.size()>0) {
			int size = reportsTopXPageObject.inputParameterTagsValues.size();
			while(size!=0) {
				values.add(reportsTopXPageObject.inputParameterTagsValues.get(size-1).getText().toLowerCase());
				size--;
			}
		}
		for(String header : paramHeader) {
			Assert.assertTrue(headers.contains(header),header + " header not present");
		}

		for(String value : paramValue) {

			Assert.assertTrue(values.contains(value.toLowerCase()),value+ " value not present");


		}
	}

	public void downloadJSON() {
		reportsTopXPageObject.threeDots.click();
		reportsTopXPageObject.downloadJson.click();
	}

	public String copyUrlAndNavigate() {
		waitExecuter.sleep(4000);

		try {reportsTopXPageObject.copyUrl.click();}
		catch(Exception e){
			reportsTopXPageObject.copyUrl.click();

		}

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

	public void validateSortingByAppCount() {
		Set<String> initialSet = new HashSet<String>();
		Set<String> resultantSet = new HashSet<String>();
		TreeSet<String> sort = null;

		JavaScriptExecuter.scrollOnElement(driver, reportsTopXPageObject.sortIcon);
		//Fetching data before applying sorting
		for(int i =1; i< reportsTopXPageObject.appCount.size();i=i+2) {
			initialSet.add(reportsTopXPageObject.appCount.get(i).getText());
		}
		reportsTopXPageObject.sortIcon.click();

		//Fetching data after applying sorting

		for(int i =1; i< reportsTopXPageObject.appCount.size();i=i+2) {
			resultantSet.add(reportsTopXPageObject.appCount.get(i).getText());
		}
		sort = new TreeSet<String>(initialSet);
		Assert.assertEquals(resultantSet, sort);
	}

	public ArrayList<String> createNewReportWithInvalidTopXCount(String value) {
		reportsTopXPageObject.topxTextArea.sendKeys(value);
		reportsTopXPageObject.newReportRunBtn.click();
		ArrayList<String> errors = new ArrayList<String>();
		errors.add(reportsTopXPageObject.invalidInputErrorMessage.getText());
		errors.add(reportsTopXPageObject.invalidTopXNumberErrorMessage.getText());
		return errors;
	}

	public ArrayList<String> createEmptyReport() {
		reportsTopXPageObject.newReportRunBtn.click();
		ArrayList<String> errors = new ArrayList<String>();
		errors.add(reportsTopXPageObject.invalidInputErrorMessage.getText());
		errors.add(reportsTopXPageObject.requiredFieldErrorMessage.getText());
		return errors;
	}

	public void navigateToApplicationFilterTabs(String tabName) {
		waitExecuter.sleep(4000);
		driver.findElement(By.xpath(String.format(reportsTopXPageObject.applicationStatusStates, tabName))).click();
		LOGGER.info(tabName+ " selected");
	}


	public void validateApplicationHeaders(String[] paramHeaders) {
		List<String> headers = reportsTopXPageObject.applicationHeaders.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		for(String header : paramHeaders) {
			Assert.assertTrue(headers.contains(header),header+" header not present");
		}
	}

	public void validateApplicationDataSetCount() {
		List<String> values = reportsTopXPageObject.applicationHeadersValue.stream()
				.map(el -> el.getText()).collect(Collectors.toList());
		Assert.assertTrue(values.size()>0);
	}

	public ArrayList<String> calculateAppCount() {
		double floorSum =0.00;
		double ceilSum =0.00;
		ArrayList<String> list = new ArrayList<String>();
		int size = reportsTopXPageObject.appCount.size();
		for(int i = 1; i< size;i=i+2) {
			floorSum = floorSum + Double.parseDouble(reportsTopXPageObject.appCount.get(i).getText());
		}
		if(floorSum<1000) {
			String value = String.valueOf(floorSum);
			list.add(value.substring(0, value.indexOf('.')));
		}
		else {
			ceilSum = floorSum;
			floorSum = Math.floor(floorSum / 10);
			String floorValue;
			String ceilValue;

			floorValue = String.valueOf(floorSum/10);
			list.add(floorValue + "K");
			ceilSum = Math.ceil(ceilSum / 100);
			ceilValue = String.valueOf(ceilSum/10);
			list.add(ceilValue + "K");
		}
		return list;
	}

	public String returnAppCount() {
		return reportsTopXPageObject.totalAppCount.getText();
	}

	public void validateSortingByWorkspace() {
		Set<String> initialSet = new HashSet<String>();
		Set<String> resultantSet = new HashSet<String>();
		TreeSet<String> sort = null;

		JavaScriptExecuter.scrollOnElement(driver, reportsTopXPageObject.sortIcon);
		//Fetching data before applying sorting
		for(int i =0; i< reportsTopXPageObject.appCount.size();i=i+2) {
			initialSet.add(reportsTopXPageObject.appCount.get(i).getText());
		}
		reportsTopXPageObject.sortIcon.click();

		//Fetching data after applying sorting

		for(int i =0; i< reportsTopXPageObject.appCount.size();i=i+2) {
			resultantSet.add(reportsTopXPageObject.appCount.get(i).getText());
		}
		sort = new TreeSet<String>(initialSet);
		Assert.assertEquals(resultantSet, sort);
	}

	public LinkedHashMap<String, String> populateSparkDetailValues() {
		LinkedHashMap<String, String> resultTable = new LinkedHashMap<String, String>();

		List<String> values;
		List<String> headers = reportsTopXPageObject.successfulJobRunHeaders.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		if(TestUtils.isElementDisplayed(reportsTopXPageObject.successfulJobRunRow)) {
			values = reportsTopXPageObject.successfulJobRunData.stream()
					.map(el -> el.getText()).collect(Collectors.toList());
		}
		else {
			values = reportsTopXPageObject.runningJobRunData.stream()
					.map(el -> el.getText()).collect(Collectors.toList());
		}
		Iterator<String> i1 = headers.iterator();
		Iterator<String> i2 = values.iterator();
		while (i1.hasNext() && i2.hasNext()) {
			resultTable.put(i1.next(), i2.next());
		}
		return resultTable;
	}

	public void openSparkDetailsPage() {
		if(TestUtils.isElementDisplayed(reportsTopXPageObject.successfulJobRunRow)) {
			reportsTopXPageObject.successfulJobRunData.get(0).click();
		}
		else {
			reportsTopXPageObject.runningJobRunData.get(0).click();
		}

	}
	
	public void downloadReport() {
		reportsTopXPageObject.download.click();
	}
}
