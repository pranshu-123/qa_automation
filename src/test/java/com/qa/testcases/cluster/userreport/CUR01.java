package com.qa.testcases.cluster.userreport;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.Schedule;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.clusters.UserReport;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Sarbashree Ray
 * Validate schedule user report is working fine.
 */
@Marker.All
@Marker.UserReports
public class CUR01 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR01.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR01_Verifyscheduleuserreport(String clusterId) {
        test = extent.startTest("CUR01.Verifyscheduleuserreport"+ clusterId,
                "Verify schedule user report is working fine");
        test.assignCategory("Cluster - User Report");
        Log.startTestCase("CUR01_Verifyscheduleuserreport");
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);
        Log.info("Passed Parameter Is : " + clusterId);
        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);
        Schedule schedule = new Schedule(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        UserReport userReport = new UserReport(driver);
        userReport.selectClusterstab();
        waitExecuter.sleep(1000);

        waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
        waitExecuter.sleep(3000);
        topPanelPageObject.topXTab.click();


        waitExecuter.waitUntilElementClickable(userReportPageObject.scheduleuserreportButton);
        userReport.clicksheduleusereport();
        test.log(LogStatus.PASS, "Verified user click on schedule user report");


        userReport.addscheduler("Cluster");
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Successfully add Schedule Name");

        schedule.clickOnSchedule();
        waitExecuter.sleep(1000);

        schedule.selectDaily();
        waitExecuter.sleep(2000);



        try {
            userReportPageObject.addconfiguration.click();
            test.log(LogStatus.PASS, "Successfully clicked on add configuration.");

        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked on add configuration.");
        }

        userReport.setTopXNumber("30");
        waitExecuter.sleep(1000);
        userReport.selectRealUser();
        userReport.selectQueue();
        waitExecuter.sleep(1000);

        userReport.assignEmail("sray@unraveldata.com");
        waitExecuter.sleep(1000);

        waitExecuter.waitUntilElementPresent(userReportPageObject.addbutton);
        userReport.clickOnaddButton();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Successfully added Topx parameter");

        userReportPageObject.saveschedule.click();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully clicked save sheduele.");

        Log.endTestCase("CUR01_Verifyscheduleuserreport");
    }
}