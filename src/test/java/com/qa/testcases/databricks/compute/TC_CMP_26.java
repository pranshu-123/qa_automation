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
public class TC_CMP_26 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_26.class.getName());

	@Test
	public void TC_Compute_26_FilterByTagTeam() {
		test = extent.startTest("TC_Compute_26_FilterByTagTeam", "Filter data as per Tags - Team");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_26_FilterByTagTeam");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.selectTags("Tenure");
		compute.selectTagKey();
		compute.returnSelectedTags();
		List<String> status = compute.selectStatus("Success");
		compute.validateJobDetailsAsPerSelectedStatus(status);
		test.log(LogStatus.PASS, "Filtered by Tag Name");
		LOGGER.info("Filtered by Tag Name working as expected");
	}

}

