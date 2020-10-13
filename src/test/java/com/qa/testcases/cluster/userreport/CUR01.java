package com.qa.testcases.cluster.userreport;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.clusters.UserReport;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Sarbashree Ray
 */

@Marker.UserReports
@Marker.All
public class CUR01 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR01.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR01_Verifyscheduleuserreport(String clusterId) {
        test = extent.startTest("CUR01.Verifyscheduleuserreport", "Verify schedule user report is working fine");
        test.assignCategory(" Cluster - User Report");
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
        waitExecuter.sleep(3000);
        topPanelPageObject.topXTab.click();

        UserReport userReport = new UserReport(driver);
        waitExecuter.waitUntilElementPresent(userReportPageObject.scheduleuserreportButton);
        userReport.HeaderMessage();
        Log.info("User Report Header is displayed.");
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully verified open new schedule user report page.");

        waitExecuter.sleep(1000);
        Log.info("Closing About window");
        userReportPageObject.closeWindow.click();
        waitExecuter.sleep(1000);
        Log.info("Loging off the app");
        test.log(LogStatus.PASS, "Successfully completed new schedule user report page.");
        waitExecuter.sleep(2000);

    }
}