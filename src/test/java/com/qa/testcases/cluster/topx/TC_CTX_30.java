package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.RandomGenerator;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.WaitExecuter;

@Marker.All
@Marker.TopX
public class TC_CTX_30 extends BaseClass {
	private final LoggingUtils LOGGER = new LoggingUtils(TC_CTX_30.class);

	@Test
	public void TC_CTX_30_validateReportSchedule() {
		test = extent.startTest("TC_CTX_30_validateReportSchedule", "Verify new TopX report is scheduled");
		test.assignCategory(" Cluster - Top X");
		WaitExecuter waitExecuter = new WaitExecuter(driver);

		UserActions actions = new UserActions(driver);
		TopXPageObject topXPageObject = new TopXPageObject(driver);

		TopX topX = new TopX(driver);

		SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
		MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
		waitExecuter.sleep(3000);

		ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
		ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
		LOGGER.info("Click on + button", test);
		topX.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.sleep(3000);
		topX.setTopXNumber("30");
		topX.selectUserInScheduleReport();
		topX.selectRealUser();
		topX.selectQueue();
		String email = RandomGenerator.generateRandomEmail();
		String name = RandomGenerator.generateRandomName();
		topX.assignScheduleName(name);
		topX.assignEmail(email);
		actions.performActionWithPolling(topXPageObject.scheduleButtonInReport, UserAction.CLICK);
		test.log(LogStatus.PASS, "Verified new TopX report is scheduled");
		waitExecuter.sleep(2000);
		topX.clickOnScheduleButton();
		Boolean isReportListedInScheduleReport = topX.validateReport(name);
		Assert.assertTrue(isReportListedInScheduleReport, "Schedule report is not displayed in schedule report table.");
		LOGGER.pass("Schedule report is displayed in schedule report table.", test);
	}
}
