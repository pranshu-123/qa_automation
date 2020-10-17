package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
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
		
		waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
		waitExecuter.sleep(3000);
		topPanelPageObject.topXTab.click();

		TopXPageObject topXPageObject = new TopXPageObject(driver);

		TopX topX = new TopX(driver);
		topX.closeConfirmationMessageNotification();

		//Set all properties in schedule report
		topXPageObject.scheduleButton.click();
		waitExecuter.sleep(1000);
		waitExecuter.waitUntilElementPresent(topXPageObject.usersDropdown);
		waitExecuter.sleep(1000);
		topX.selectUserInScheduleReport();
		topX.selectRealUser();
		topX.selectQueue();
		topX.assignScheduleName("ScheduleReport1");
		topX.assignEmail("ojasvi.pandey@unraveldata.com");
		waitExecuter.sleep(1000);
		topXPageObject.scheduleButtonInReport.click();
		
		waitExecuter.sleep(1000);
		Assert.assertTrue(topXPageObject.scheduleSuccessfulMessage.isDisplayed());
		
	}
}
