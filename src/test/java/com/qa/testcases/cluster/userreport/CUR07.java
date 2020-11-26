package com.qa.testcases.cluster.userreport;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.scripts.Schedule;
import com.qa.scripts.clusters.UserReport;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.All
@Marker.UserReports
public class CUR07 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR07.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR07_VerifyMultipleConfigurations(String clusterId) {
        test = extent.startTest("CUR07_VerifyMultipleConfigurations"+ clusterId, "Verify multiple configurations should be added successfully");
        test.assignCategory("Cluster - User Report");
        Log.endTestCase("CUR07_Verifymultipleconfigurations");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);
        Schedule schedule = new Schedule(driver);

        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        UserReport userReport = new UserReport(driver);
        userReport.selectClusterstab();
        waitExecuter.sleep(1000);

        try {
        waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
        waitExecuter.sleep(3000);
        topPanelPageObject.topXTab.click();
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked topXTab");
        }
        
        waitExecuter.waitUntilElementClickable(userReportPageObject.scheduleuserreportButton);
        userReport.clicksheduleusereport();
        test.log(LogStatus.PASS, "Verified user click on schedule user report");

        userReport.addschedule();
        waitExecuter.sleep(1000);
        //select 'schedule-days  '
        schedule.scheduletorun(schedule);

        try {
            userReportPageObject.addconfiguration.click();
            waitExecuter.sleep(1000);
            test.log(LogStatus.PASS, "Successfully clicked on add configuration.");

        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked on add configuration.");
        }

        userReport.setTopXNumber("30");
        waitExecuter.sleep(1000);

        userReport.selectRealUser();
        userReport.selectQueue();

        userReport.assignEmail("sray@unraveldata.com");
        waitExecuter.sleep(1000);

        waitExecuter.waitUntilElementPresent(userReportPageObject.addbutton);
        userReport.clickOnaddButton();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Successfully added Topx parameter");

        userReportPageObject.addconfiguration.click();

        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully clicked on add configuration page.");

        userReport.setTopXNumber("30");
        waitExecuter.sleep(1000);


        userReport.selectRealUser();
        userReport.selectQueue();

        userReport.assignEmail("sray@unraveldata.com");
        waitExecuter.sleep(1000);

        waitExecuter.waitUntilElementPresent(userReportPageObject.addbutton);
        userReport.clickOnaddButton();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Successfully clicked on add configuration page.");


        userReport.clicksaveschedule();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully clicked save sheduele.");
        Log.info("Loging off the app");

        Log.endTestCase("CUR07_Verifymultipleconfigurations");
    }
}