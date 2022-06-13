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
public class TC_CMP_28 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_28.class.getName());

	@Test
	public void TC_Compute_28_verifyPagination() {
		test = extent.startTest("TC_Compute_28_verifyPagination", "Verify next/last page navigation");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_28_verifyPagination");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		List<String> status = compute.selectStatus("Success");
		compute.paginateToNextLastPage("next");
		LOGGER.info("Navigated to next page");
		compute.paginateToNextLastPage("next");
		compute.validateJobDetailsAsPerSelectedStatus(status);
		LOGGER.info("Navigated to last page");
		test.log(LogStatus.PASS, "Pagination working as expected");
		
	}

}

