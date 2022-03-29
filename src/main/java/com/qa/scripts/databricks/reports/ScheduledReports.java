package com.qa.scripts.databricks.reports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.pagefactory.databricks.reports.ReportsScheduledPageObject;
import com.qa.pagefactory.databricks.reports.ReportsTopXPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;

public class ScheduledReports {


	private static final Logger LOGGER = Logger.getLogger(ScheduledReports.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ReportsTopXPageObject reportsTopXPageObject;
	private final DatePicker datePicker;
	private final ReportsScheduledPageObject reportsScheduledPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ScheduledReports(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		reportsTopXPageObject = new ReportsTopXPageObject(driver);
		datePicker = new DatePicker(driver);
		reportsScheduledPageObject = new ReportsScheduledPageObject(driver);
	}

	public String setScheduledReportName(String name) {
		JavaScriptExecuter.scrollOnElement(driver, reportsScheduledPageObject.scheduleReport);
		reportsScheduledPageObject.scheduleName.sendKeys(name);
		LOGGER.info("Set the report name as: "+name);
		return name;
	}

	public void selectScheduleReportBtn() {
		reportsScheduledPageObject.scheduleBtn.click();
	}

	public void scheduleReport() {
		JavaScriptExecuter.scrollOnElement(driver, reportsScheduledPageObject.scheduleReport);
		reportsScheduledPageObject.scheduleReport.click();
		waitExecuter.sleep(10000);
	}

	public String selectScheduledTime(String option) {
		Select select = new Select(reportsScheduledPageObject.scheduleTime);
		select.selectByVisibleText(option);
		LOGGER.info("Set the report time as every: "+option);
		return option;
	}

	public void addEmailNotification(String email, Boolean addMultiple) {
		reportsScheduledPageObject.notificationTextBox.sendKeys(email);
		if(addMultiple) {
			reportsScheduledPageObject.addMoreEmails.click();
		}
	}


	public void validateScheduledReport(List<String> tableHeaders, List<String> tableValues) {
		waitExecuter.sleep(1000);
		List<String> headers = reportsScheduledPageObject.reportTableHeader.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		List<String> values = reportsScheduledPageObject.reportTableValues.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		for(String header : tableHeaders) {
			Assert.assertTrue(headers.contains(header),header + " Header not present");
		}

		for(String value : tableValues) {
			Assert.assertTrue(values.contains(value),value + " Value not present");
		}
	}

	public List<String> scheduledTimeOptions(){
		Select select = new Select(reportsScheduledPageObject.scheduleTime);
		return select.getOptions().stream()
				.map(option -> option.getText()).collect(Collectors.toList());
	}

	public String createNewReportWithDefaultValues(String top) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		waitExecuter.sleep(10000);
		return top;
	}

	public void updateReport() {
		reportsScheduledPageObject.updateBtn.click();
		reportsScheduledPageObject.closeButton.click();
	}

	public void searchScheduledReport(String name) {
		reportsScheduledPageObject.searchBox.sendKeys(name,Keys.ENTER);
	}

	public void selectScheduledReport() {
		driver.navigate().refresh();
		waitExecuter.sleep(1000);
		reportsScheduledPageObject.reportTableValues.get(0).click();
	}

	public void selectMoreInfo() {
		waitExecuter.sleep(1000);
		reportsScheduledPageObject.moreInfo.click();
	}

	public void validateScheduledReportInfo(List<String> tableHeaders, List<String> tableValues) {
		waitExecuter.sleep(3000);
		List<String> headers = reportsScheduledPageObject.inputParameterHeader.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		List<String> values = reportsScheduledPageObject.inputParameterValue.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		for(String header : tableHeaders) {
			Assert.assertTrue(headers.contains(header),header +" header not present");
		}

		for(String value : tableValues) {
			Assert.assertTrue(values.contains(value),value + " value not present");
		}

	}

	public void validateScheduledPageObjects() {
		Assert.assertTrue(reportsScheduledPageObject.deleteScheduleReport.isDisplayed());
		Assert.assertTrue(reportsScheduledPageObject.editScheduleReport.isDisplayed());
		Assert.assertTrue(reportsScheduledPageObject.searchBox.isDisplayed());
		Assert.assertTrue(reportsScheduledPageObject.moreInfo.isDisplayed());
	}

	public String editScheduledReport() {
		String name = driver.getWindowHandle().substring(0, 14);
		waitExecuter.waitUntilElementClickable(	reportsScheduledPageObject.editScheduleReport);
		reportsScheduledPageObject.editScheduleReport.click();
		JavaScriptExecuter.scrollOnElement(driver,reportsScheduledPageObject.notificationTextBox);
		JavaScriptExecuter.clearTextField(driver,reportsScheduledPageObject.scheduleName);
		reportsScheduledPageObject.scheduleName.sendKeys(name);
		return name;
	}

	public List<String> fetchReportName() {
		driver.navigate().refresh();
		waitExecuter.sleep(3000);
		return reportsScheduledPageObject.reportTableValues.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

	}

	public void filterReport(String filterType) {
		waitExecuter.sleep(1000);
		reportsScheduledPageObject.reportFilter.click();
		waitExecuter.sleep(1000);
		if(filterType.equalsIgnoreCase("top x")) {
			reportsScheduledPageObject.reportFilterValues.get(0).click();

		}
		else if(filterType.equalsIgnoreCase("all")) {
			reportsScheduledPageObject.reportFilterValues.get(1).click();
		}
		else {
			Assert.fail("Invalid filter type provided");
		}
	}

	public void validateSorting(String sortBy) {
		Set<String> initialSet = new HashSet<String>();
		Set<String> resultantSet = new HashSet<String>();
		TreeSet<String> sort = null;

		switch(sortBy) {
		case "Name":
			//Fetching data before applying sorting
			initialSet	= reportsScheduledPageObject.scheduledTopXReportNameList
			.stream().map(el -> el.getText())
			.collect(Collectors.toSet());
			reportsScheduledPageObject.sortName.click();

			//Fetching data after applying sorting
			resultantSet = reportsScheduledPageObject.scheduledTopXReportNameList
					.stream().map(el -> el.getText())
					.collect(Collectors.toSet());
			break;

		case "Next Scheduled Run":
			//Fetching data before applying sorting
			initialSet	= reportsScheduledPageObject.scheduledTopXReportNextRunList
			.stream().map(el -> el.getText())
			.collect(Collectors.toSet());
			reportsScheduledPageObject.sortNextScheduledRun.click();

			//Fetching data after applying sorting
			resultantSet = reportsScheduledPageObject.scheduledTopXReportNextRunList
					.stream().map(el -> el.getText())
					.collect(Collectors.toSet());
			break;


		case "Report":
			//Fetching data before applying sorting
			initialSet	= reportsScheduledPageObject.scheduledTopXReportNextRunList
			.stream().map(el -> el.getText())
			.collect(Collectors.toSet());
			reportsScheduledPageObject.sortReportType.click();

			//Fetching data after applying sorting
			resultantSet = reportsScheduledPageObject.scheduledTopXReportNextRunList
					.stream().map(el -> el.getText())
					.collect(Collectors.toSet());
			break;
		}

		sort = new TreeSet<String>(initialSet);
		Assert.assertEquals(resultantSet, sort);

	}

	public Set<String> fetchReportType() {
		
		return reportsScheduledPageObject.scheduledTopXReportTypeList
				.stream().map(el -> el.getText())
				.collect(Collectors.toSet());
	}

	public void deleteScheduledReport() {
		reportsScheduledPageObject.deleteScheduleReport.click();
		reportsScheduledPageObject.deleteYes.click();
	}

	public String fetchErrorMessage() {
			return reportsScheduledPageObject.requiredFieldErrorMessage.getText();
		}
	}

