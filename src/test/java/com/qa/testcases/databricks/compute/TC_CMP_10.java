package com.qa.testcases.databricks.compute;

import java.util.List;
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
public class TC_CMP_10 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_10.class.getName());

	@Test
	public void TC_Compute_10_verifyAllSettingsOption() {
		test = extent.startTest("TC_Compute_10_verifyAllSettingsOption", "Verify all available column details");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_10_verifyAllSettingsOption");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		compute.selectSettings();
		List<String> actualValues = compute.returnSettingsOption();
		String[] expectedSettingsOptions = {"Status", "Cluster Name", "Cluster Type","User",
				"Workspace","Start Time","Setup Duration","Duration","Cost","Actions"};

        for (String expectedSettingsOption : expectedSettingsOptions) {
            Assert.assertTrue(actualValues.contains(expectedSettingsOption),
                    "Date list does not contain: " + expectedSettingsOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedSettingsOption);
        }
        compute.selectSettings();
        LOGGER.info("Cost Chargeback: Verify all the date pickers.");
        test.log(LogStatus.PASS, "Verified all the date pickers on Cost Chargeback.");

	}
}
