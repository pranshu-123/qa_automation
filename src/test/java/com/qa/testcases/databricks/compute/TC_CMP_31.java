package com.qa.testcases.databricks.compute;

import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_31 extends BaseClass{
	
	private static final Logger LOGGER = Logger.getLogger(TC_CMP_31.class.getName());
	
	@Test
	public void TC_Compute_31_validateJobsAsPerSelectedDate() {
		test = extent.startTest("TC_Compute_31_validateJobsAsPerSelectedDate", "Verify applications are listed for different date ranges");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_31_validateJobsAsPerSelectedDate");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		ChargebackClusterPageObject chargebackClusterPageObject = new ChargebackClusterPageObject(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectCustomRange();
		date.setCurrentAndPastDate(-20);
		date.clickOnCustomDateApplyBtn();
		String dateRange = date.getDate(20);
		String selectedDate = chargebackClusterPageObject.selectedDates.getText();
		Assert.assertTrue(selectedDate.contains(dateRange));
		LOGGER.info("Selected date is displayed");
		date.clickOnDatePicker();
		date.selectCustomRange();
		date.setCurrentAndPastDate(-2);
		date.clickOnCustomDateApplyBtn();
		dateRange = date.getDate(2);
		selectedDate = chargebackClusterPageObject.selectedDates.getText();
		Assert.assertTrue(selectedDate.contains(dateRange));
		LOGGER.info("Selected date is displayed");
		test.log(LogStatus.PASS, "Jobs are listed as per selected status");
		LOGGER.info("Jobs are listed as per selected status.");
	}
}
