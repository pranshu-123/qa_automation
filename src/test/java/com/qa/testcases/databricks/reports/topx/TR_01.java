package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_01 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_01.class.getName());
	@Test
	public void TopX_Reports_01_VerifyReportPageTabs() {
		test = extent.startTest("TopX_Reports_01_VerifyReportPageTabs", "Validate Reports page tabs");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_01_VerifyReportPageTabs");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.validateReportsTab();
		test.log(LogStatus.PASS, "All the 3 tabs are listed and clickable");
		LOGGER.info("All the 3 tabs are listed and clickable");
	}
}
