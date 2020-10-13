package com.qa.testcases.cluster.overview;

import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.parameters.Parameter;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.ClusterOverview
@Marker.All
public class TC_CO_04 extends BaseClass {
	
	private WaitExecuter waitExecuter;
	private DatePicker datePicker;
	private static final Logger LOGGER = Logger.getLogger(TC_CO_08.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CO_04_verifyCustomRangeForCurrentDate(String clusterId) {
		test = extent.startTest("TC_CO_04_verifySettingDateInCalendar : "+clusterId, "Verify Custom Date in datepicker calendar ");
		test.assignCategory("4620 - Cluster Overview");		
		waitExecuter = new WaitExecuter(driver);
		datePicker = new DatePicker(driver);
		//Select the cluster
		LOGGER.info("Selecting the cluster");
		HomePage homePage = new HomePage(driver);
	    	homePage.selectMultiClusterId(clusterId);

		// Click on datepicker button
		LOGGER.info("Click on date picker");
		waitExecuter.sleep(3000);
		datePicker.clickOnDatePicker();
		// Click on custom range
		LOGGER.info("Select custom range from date picker");
		datePicker.selectCustomRange();
		waitExecuter.sleep(3000);
		/* Set Start date by substracting days from current date and end date as current date */
		LOGGER.info("Set current date and back date");
		datePicker.setCurrentAndPastDate(-3);
		waitExecuter.sleep(3000);
		// Click on apply button of calendar
		LOGGER.info("Click on apply button of date picker custom range");
		datePicker.clickOnCustomDateApplyBtn();
		waitExecuter.sleep(3000);
		// Get the set start and end date from calendar
		LOGGER.info("Get date from date picker tab");
		String actual_date = datePicker.getSetDateFromCalendar();
		waitExecuter.sleep(1000);
		// Convert the set date format to the output format of calendar
		LOGGER.info("Convert the date in expected format");
		String expected_date = datePicker.convertedSetCurrentAndPastDate(-3);
		waitExecuter.sleep(1000);

		LOGGER.info("Verify calendar tab");
		test.log(LogStatus.INFO, "Check if date is displayed in calendar tab");
		// Check if the date is displayed in calendar
		Assert.assertTrue(datePicker.isDateDisplayedInCalendar(),
				"Calendar Date is not displayed as set " + actual_date);
		test.log(LogStatus.PASS, "Start Date and end date is displayed in calendar tab successfully");

		LOGGER.info("Validate the set date");
		test.log(LogStatus.INFO, "Check if the date set by user is properly set on calendar tab");
		// Check if set date is equal to the get date of UI
		Assert.assertTrue(actual_date.equals(expected_date), "Set Date is not displayed in calendar bar " + actual_date);
		test.log(LogStatus.PASS, "Set start and end date is successfully verified with calendar date displayed on UI");

	}

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CO_04_verifyCustomRangeForDesiredDates(String clusterId) {

		test = extent.startTest("TC_CO_04_verifySettingDateInCalendar : "+clusterId, "Verify Custom Date for desired date ");
		test.assignCategory("4620 - Cluster Overview");
		//Select the cluster
		LOGGER.info("Selecting the cluster");
		HomePage homePage = new HomePage(driver);
	    	homePage.selectMultiClusterId(clusterId);
		// Click on datepicker button
		DatePicker datePicker = new DatePicker(driver);
		LOGGER.info("Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(3000);
		// Click on custom range
		LOGGER.info("Click on custom range from date picker");
		datePicker.selectCustomRange();
		waitExecuter.sleep(3000);
		// Set start date in calendar
		LOGGER.info("Set the start date");
		datePicker.setStartDate(Parameter.startDate);
		waitExecuter.sleep(1000);
		// Set end date in calendar
		LOGGER.info("Set the end date");
		datePicker.setEndDate(Parameter.endDate);
		waitExecuter.sleep(1000);
		// Click apply button
		LOGGER.info("Click on apply button");
		datePicker.clickOnCustomDateApplyBtn();
		// Get the set date from calendar
		LOGGER.info("Get the set date from calendar");
		String actual2 = datePicker.getSetDateFromCalendar();
		waitExecuter.sleep(1000);
		LOGGER.info("Convert dates in desired formats");
		String expected2 = datePicker.convertedSetDate(Parameter.startDate, Parameter.endDate);

		LOGGER.info("Verify date is displayed in calendar");
		test.log(LogStatus.INFO, "Check if date is displayed on calendar tab");
		// Check if the date is displayed in calendar
		Assert.assertTrue(datePicker.isDateDisplayedInCalendar(),
				"Calendar Date is not displayed as set " + actual2);
		test.log(LogStatus.PASS, "Start Date and end date is displayed in calendar tab successfully");

		LOGGER.info("Validate the set date");
		test.log(LogStatus.INFO, "Check if the date set by user is properly set on calendar");
		// Check if set date is equal to the get date of UI
		Assert.assertTrue(actual2.equals(expected2), "Set Date is not displayed in calendar bar " + actual2);
		test.log(LogStatus.PASS, "Set start and end date is successfully verified with calendar date displayed on UI");
	}
}
