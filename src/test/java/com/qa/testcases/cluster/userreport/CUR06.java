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
public class CUR06 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR06.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR06_VerifyMailIdCombination(String clusterId) {
        test = extent.startTest("CUR06_VerifyMailIdCombination" + clusterId, "Verify after select add option this should pass the email validation");
        test.assignCategory("Cluster - User Report");
        Log.startTestCase("CUR06_Verifymailidcombination");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);
        Schedule schedule = new Schedule(driver);
        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        UserReport userReport = new UserReport(driver);
        userReport.selectClusterstab();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
        waitExecuter.waitUntilPageFullyLoaded();
        topPanelPageObject.topXTab.click();


        waitExecuter.waitUntilElementClickable(userReportPageObject.scheduleuserreportButton);
        userReport.clicksheduleusereport();
        test.log(LogStatus.PASS, "Verified user click on schedule user report");

        userReport.addschedule();
        waitExecuter.waitUntilPageFullyLoaded();
        //select 'schedule-days  '
        schedule.scheduletorun(schedule);

        try {
            userReportPageObject.addconfiguration.click();
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Successfully clicked on add configuration.");

        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked on add configuration.");
        }

        try {
            userReport.setTopXNumber("30");
            waitExecuter.waitUntilPageFullyLoaded();
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked on setTopXNumber.");
        }
        if (!userReport.selectRealUser()) {
            test.log(LogStatus.PASS, "Verify select dropdown in Group by RealUser");
        } else {
            test.log(LogStatus.FAIL, "Test Failed select dropdown in Group by RealUser");
        }
        waitExecuter.waitUntilPageFullyLoaded();
        if (!userReport.selectQueue()) {
            test.log(LogStatus.PASS, "Verify select dropdown in Group by Queue");
        } else {
            test.log(LogStatus.FAIL, "Test Failed select dropdown in Group by Queue");
        }
        waitExecuter.waitUntilPageFullyLoaded();

        userReport.assignEmail("Sray@unraveldata.com");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully clicked on schedule user report page.");

        waitExecuter.waitUntilElementPresent(userReportPageObject.addbutton);
        userReport.clickOnaddButton();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully added Topx parameter");

        try {
            userReport.clicksaveschedule();
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Successfully clicked save sheduele.");

        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked save sheduele button");
        }
        Log.info("Loging off the app");
        Log.endTestCase("CUR06_Verifymailidcombination");
    }
}

