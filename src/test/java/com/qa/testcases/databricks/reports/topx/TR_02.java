package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_02 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_02.class.getName());
	@Test
	public void TopX_Reports_02_VerifyTopXPageObjects() {
		test = extent.startTest("TopX_Reports_02_VerifyTopXPageObjects", "Validate TopX page objects");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_02_VerifyTopXPageObjects");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.validateTopXObjects();
		test.log(LogStatus.PASS, "All the top x page elements are listed and clickable");
		LOGGER.info("All the top x page elements are listed and clickable");
	}
}
