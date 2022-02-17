package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostChargeback
public class DC_21  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_21.class.getName());

	@Test
	public void TC_Cost_CB_21_VerifyDownloadInSVGFormat() {
		test = extent.startTest("TC_Cost_CB_21_VerifyDownloadInSVGFormat", "Verify download in SVG format");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_21_VerifyDownloadInSVGFormat");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.clickOnGroupByDropDown();
		jobs.selectGroupByFilterValue("Workspace");
		chargeBackCluster.selectDownloadOption("dbu", "Download CSV");
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("chart.csv"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Nodes graph as CSV file.");
		LOGGER.info("Successfully downloaded graph as CSV file.");
	}
}
