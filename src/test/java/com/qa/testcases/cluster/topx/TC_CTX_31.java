package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.actions.UserActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.FileUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.All
@Marker.TopX
public class TC_CTX_31 extends BaseClass {
	Logger logger = LoggerFactory.getLogger(TC_CTX_31.class);

	@Test
	public void TC_CTX_31_validateReportSchedule() {
		test = extent.startTest("TC_CTX_31_validateJsonDownload", "Verify in TopX Json file is downloaded successfully");
		test.assignCategory(" Cluster - Top X");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		waitExecuter.waitUntilPageFullyLoaded();
		TopXPageObject topXPageObject = new TopXPageObject(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

		SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
		MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
		waitExecuter.sleep(3000);
		ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
		ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
		logger.info("Click on + button", test);
		reportsPage.clickOnLatestReport(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

		waitExecuter.waitUntilPageFullyLoaded();
		UserActions actions = new UserActions(driver);

		actions.performActionWithPolling(topXPageObject.downloadJsonButton, UserAction.CLICK);
		test.log(LogStatus.PASS, "Clicked on Download JSON");
        FileUtils.isFileDownloadedInUUIDFolder();
        test.log(LogStatus.PASS, "Verified Download CSV files present in directory.");
	}
}
