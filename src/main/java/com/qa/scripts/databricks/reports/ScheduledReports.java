package com.qa.scripts.databricks.reports;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
		waitExecuter.waitUntilElementPresent(reportsTopXPageObject.reportGenerationMsg);
	}

	public String selectScheduledTime(String option) {
		Select select = new Select(reportsScheduledPageObject.scheduleTime);
		select.selectByVisibleText(option);
		LOGGER.info("Set the report time as every: "+option);
		return option;
	}

	public void addEmailNotification(String email) {
		reportsScheduledPageObject.notificationTextBox.sendKeys(email);
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

	public void validateScheduledReport(List<String> tableHeaders, List<String> tableValues) {
		List<String> headers = reportsScheduledPageObject.reportTableHeader.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		List<String> values = reportsScheduledPageObject.reportTableValues.stream()
				.map(el -> el.getText()).collect(Collectors.toList());

		for(String header : tableHeaders) {
			Assert.assertTrue(headers.contains(header),"Header not present");
		}

		for(String value : tableValues) {
			Assert.assertTrue(values.contains(value),"Value not present");
		}
	}
}
