package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.enums.UserAction;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.WaitExecuter;

@Marker.All
@Marker.TopX
public class TC_CTX_30 extends BaseClass {
	Logger logger = LoggerFactory.getLogger(TC_CTX_30.class);

	@Test
	public void TC_CTX_30_validateReportSchedule() {
		test = extent.startTest("TC_CTX_30_validateReportSchedule", "Verify new TopX report is scheduled");
		test.assignCategory(" Cluster - Top X");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
		waitExecuter.waitUntilPageFullyLoaded();
		UserActions actions = new UserActions(driver);
		waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
		actions.performActionWithPolling(topPanelPageObject.topXTab, UserAction.CLICK);
		TopXPageObject topXPageObject = new TopXPageObject(driver);

		TopX topX = new TopX(driver);
		topX.closeConfirmationMessageNotification();

		//Set all properties in schedule report
		actions.performActionWithPolling(topXPageObject.scheduleButton, UserAction.CLICK);
		waitExecuter.waitUntilElementPresent(topXPageObject.usersDropdown);
		topX.setTopXNumber("30");
		topX.selectUserInScheduleReport();
		topX.selectRealUser();
		topX.selectQueue();
		topX.assignScheduleName("ScheduleReport1");
		topX.assignEmail("ojasvi.pandey@unraveldata.com");
		actions.performActionWithPolling(topXPageObject.scheduleButtonInReport, UserAction.CLICK);
		Assert.assertTrue(topXPageObject.scheduleSuccessfulMessage.isDisplayed());
		test.log(LogStatus.PASS, "Verified new TopX report is scheduled");
	}
}
