package com.qa.testcases.cluster.userreport;

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

public class CUR05 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR03.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR05_VerifytopXparameterpage(String clusterId) {
        test = extent.startTest("CUR05_VerifytopXparameterpage"+clusterId,
                "Verify  the mandatory fields and should add the configirations to the list");
        test.assignCategory(" Cluster - User Report");
        Log.startTestCase("CUR05_VerifytopXparameterpage");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);
        Schedule schedule = new Schedule(driver);

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


        userReport.addscheduler("Cluster#567");
        waitExecuter.sleep(1000);
        //select 'Last 2 Hour'
        schedule.clickOnSchedule();
        waitExecuter.sleep(1000);

        schedule.selectFriday();
        waitExecuter.sleep(2000);

        schedule.clicktimepicker();
        waitExecuter.sleep(2000);

        schedule.clickOndropdown();
        waitExecuter.sleep(2000);

        schedule.clickOnhours();
        waitExecuter.sleep(1000);

        schedule.selecttwentythreehours();
        waitExecuter.sleep(1000);

        schedule.clickOnminutes();
        waitExecuter.sleep(1000);

        schedule.selectFiftynine();
        waitExecuter.sleep(1000);

        waitExecuter.sleep(3000);
        try {
            userReportPageObject.addconfiguration.click();
            waitExecuter.sleep(1000);
            test.log(LogStatus.PASS, "Successfully clicked on add configuration.");

        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked on add configuration.");
        }
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully clicked on add configuration page.");

        userReport.setTopXNumber("30");
        waitExecuter.sleep(1000);
        userReport.selectRealUser();
        userReport.selectQueue();

        userReport.assignEmail("sray@unraveldata.com");
        waitExecuter.sleep(1000);

        waitExecuter.waitUntilElementPresent(userReportPageObject.addbuttom);
        userReport.clickOnaddButton();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Successfully added Topx parameter");

        userReport.clicksaveschedule();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully clicked save sheduele.");


        Log.startTestCase("CUR05_VerifytopXparameterpage");
    }
}
