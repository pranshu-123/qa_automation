package com.qa.testcases.emr.clusters;

import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.EmrDateTimePicker;
import com.qa.scripts.EmrDatePicker;
import com.qa.scripts.emr.clusters.EMRCluster;
import com.qa.utils.DateUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_CL_02 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_02.class.getName());

	@Test
	public void TC_CL_02_VerifyDatePickerForEMR() {
		test = extent.startTest("TC_CL_02_VerifyDatePickerForEMR", "Verify datepicker list");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_02_VerifyDatePickerForEMR");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDateTimePicker emrDateTimePicker = new EmrDateTimePicker(driver);
		EmrDatePicker emrPicker = new EmrDatePicker(driver);
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		Assert.assertTrue(emrDateTimePicker.last14Days.isDisplayed());
		Assert.assertTrue(emrDateTimePicker.last1M.isDisplayed());
		Assert.assertTrue(emrDateTimePicker.last1Y.isDisplayed());
		Assert.assertTrue(emrDateTimePicker.last3M.isDisplayed());
		Assert.assertTrue(emrDateTimePicker.last6M.isDisplayed());
		Assert.assertTrue(emrDateTimePicker.last7Days.isDisplayed());
		Assert.assertTrue(emrDateTimePicker.today.isDisplayed());
		emrDateTimePicker.last14Days.click();
		String last14DaysDate = DateUtils.getPastDate(-14);
		String dateSeenInPortal = emrPicker.retrievePreviousDate();
		Assert.assertTrue(dateSeenInPortal.contains(last14DaysDate));
		test.log(LogStatus.PASS, "All expected dates are visible and selecting a sample date yielded valid result");
		LOGGER.info("All expected dates are visible and selecting a sample date yielded valid result");
	}

}
