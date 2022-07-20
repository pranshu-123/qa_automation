package com.qa.testcases.emr.cluster.chargeback;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackEmrPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_06 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_06.class);

	@Test
	public void TC_EMR_06_verifyEmrWithNoExistingDateRange() {
		test = extent.startTest("TC_EMR_06_verifyEmrWithNoExistingDateRange", "Verify that the date range where the UI should display “No data available.”");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectCustomRange();
		logger.info("Select custom date range", test);
		datePicker.setStartDate("01/01/2000");
		logger.info("Select start date: 01/01/2000", test);
		datePicker.setEndDate("01/01/2001");
		logger.info("Select end date: 01/01/2001", test);
		datePicker.clickOnCustomDateApplyBtn();
		Assert.assertTrue(emrChargeback.retreiveNoDataMsg().contains("No data to display"),
				"Some charts or table are displaying data.");
		test.log(LogStatus.PASS,"'No Data Available' displayed.");
	}

}
