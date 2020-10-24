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
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
@Marker.All
@Marker.UserReports
public class CUR08 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR08.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR08_Verifyscheduleuserreport(String clusterId) {
        test = extent.startTest("CUR08_Verifysaveschedule" + clusterId,
                "Verify the mandatory fields and should open a Scheduled Reports page and this report should be listed there");
        test.assignCategory(" Cluster - User Report");
        Log.startTestCase("CUR08_Verifysaveschedule");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);
        Schedule schedule = new Schedule(driver);
        SoftAssert softassert = new SoftAssert();

        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);

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


        userReport.schedule("Testschedule58");
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

        userReport.clicksaveschedule();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully clicked save sheduele.");

        userReport.verifyschedule("Testschedule58");
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verified Scheduled Reports filtered.");

        Log.info("Loging off the app");

        Log.endTestCase("CUR08_Verifysaveschedule");
    }
}
