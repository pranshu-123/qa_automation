package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
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
	Logger logger = LoggerFactory.getLogger(TC_CTX_30.class);

	@Test
	public void TC_CTX_31_validateReportSchedule() {
		test = extent.startTest("TC_CTX_31_validateJsonDownload", "Verify in TopX Json file is downloaded successfully");
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
		waitExecuter.sleep(1000);
		topXPageObject.downloadJsonButton.click();
		waitExecuter.sleep(1000);
		test.log(LogStatus.PASS, "Clicked on Download JSON");
        FileUtils.isFileDownloadedInUUIDFolder();
        test.log(LogStatus.PASS, "Verified Download CSV files present in directory.");

	}
}
