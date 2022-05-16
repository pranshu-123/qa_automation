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
public class TC_CMP_06 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_06.class.getName());

	@Test
	public void TC_Compute_06_verifyPendingJobStatus() {
		test = extent.startTest("TC_Compute_06_verifyPendingJobStatus", "Verify All the pending jobs are listed on the page");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_06_verifyPendingJobStatus");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		List<String> status = compute.selectStatus("Pending");
		compute.validateJobDetailsAsPerSelectedStatus(status);
		test.log(LogStatus.PASS, "Jobs are listed as per selected status");
		LOGGER.info("Jobs are listed as per selected status.");
	}

}

