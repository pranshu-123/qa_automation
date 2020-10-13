package com.qa.testcases.jobs.applications.all;

import java.util.List;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.constants.DatePickerConstants;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

/*
 * @author - Ojasvi Pandey
 */
/* This test is to validate filters in date picker dropdown */

public class TC_JAL_01 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_JAL_01.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void validateDatePickerFilters(String clusterId) {
		test = extent.startTest("TC_JAL_01.validateDatePickerFilters",
				"Verify the options available in date picker filter");
		test.assignCategory("4620 Jobs - Applications");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
		ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		DatePicker datePicker = new DatePicker(driver);
		DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
		AllApps allApps = new AllApps(driver);

		// Navigate to Jobs tab from header
		test.log(LogStatus.INFO, "Navigate to jobs tab from header");
		LOGGER.info("Navigate to jobs tab from header");
		waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
		waitExecuter.sleep(1000);
		topPanelComponentPageObject.jobs.click();
		waitExecuter.sleep(3000);
		waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
		waitExecuter.waitUntilPageFullyLoaded();

		//Select cluster
		test.log(LogStatus.INFO, "Select Cluster: "+clusterId);
		LOGGER.info("Select Cluster: "+clusterId);
		allApps.selectCluster(clusterId);
		waitExecuter.sleep(3000);
		
		// Click on date picker and get list of calendar ranges
		test.log(LogStatus.INFO, "Click on date picker and list of calendar ranges");
		LOGGER.info("Click on date picker and list of calendar ranges");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		List<String> calendarRanges = allApps.getCalendarRanges();
		waitExecuter.sleep(1000);

		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_1_HOUR),
				"Last 1 Hour is not presemt in datepicker filter ");
		test.log(LogStatus.PASS, "Verified 'Last 1 hour' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_2_HOUR),
				"Last 2 Hour is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last 2 hour' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_6_HOUR),
				"Last 6 Hour is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last 6 hour' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_12_HOUR),
				"Last 12 Hour is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last 12 hour' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.TODAY),
				"Today is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Today' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.YESTERDAY),
				"Yesterday is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Yesterday' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_7_DAYS),
				"Last 7 Day is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last 7 days' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_30_DAYS),
				"Last 30 Days is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last 30 days' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_90_DAYS),
				"Last 90 days is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last 90 days' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_MONTH),
				"Last Month is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Last Month' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.THIS_MONTH),
				"This Month is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'This Month' option in date picker filter.");
		Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.CUSTOM_RANGE),
				"Custom Range is not presemt in datepicker filter");
		test.log(LogStatus.PASS, "Verified 'Custom Range' option in date picker filter.");
	}

}
