package com.qa.testcases.databricks.compute;

import java.util.List;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_38 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_38.class.getName());

	@Test
	public void TC_Compute_38_verifyRunningTabJobs() {
		test = extent.startTest("TC_Compute_38_verifyRunningTabJobs", "Verify Running Tab contains only running Jobs");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_38_verifyRunningTabJobs");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.returnJobStatustes("running");
		List<String> status = compute.selectStatus("Running");
		compute.validateJobDetailsAsPerSelectedStatus(status);
		LOGGER.info("Only Running job are shown on Running tab");
		test.log(LogStatus.PASS, "Only Running job are shown on Running tab");
	}

}
