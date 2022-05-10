package com.qa.testcases.databricks.compute;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_17 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_17.class.getName());

	@Test
	public void TC_Compute_17_verifyWorkspaceSearchWithInValidParams() {
		test = extent.startTest("TC_Compute_17_verifyWorkspaceSearchWithInValidParams", "Verify workspace search functionality with invalid parameters");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_17_verifyWorkspaceSearchWithInValidParams");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		String errMsg =	compute.invalidSearchParametersValidation("workspace");
		Assert.assertTrue(errMsg.equals("No results found"));
		test.log(LogStatus.PASS, "No results found: status seen for invalid search parameter");
		LOGGER.info("No results found: status seen for invalid search parameter");
	}
}



