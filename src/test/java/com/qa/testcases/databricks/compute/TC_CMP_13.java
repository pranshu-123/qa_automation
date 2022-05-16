package com.qa.testcases.databricks.compute;

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
public class TC_CMP_13 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_13.class.getName());

	@Test
	public void TC_Compute_13_verifySortingAsPerWorkspace() {
		test = extent.startTest("TC_Compute_13_verifySortingAsPerWorkspace", "Jobs must be sorted as per workspace");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_13_verifySortingAsPerWorkspace");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.validateSorting("Workspace");
		test.log(LogStatus.PASS, "Jobs are sorted and listed as per sorted criteria");
		LOGGER.info("Jobs are sorted and listed as per sorted criteria");
	}

}

