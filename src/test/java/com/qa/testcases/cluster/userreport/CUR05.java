package com.qa.testcases.cluster.userreport;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.Schedule;
import com.qa.scripts.clusters.UserReport;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.All
@Marker.UserReports
public class CUR05 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR03.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR05_VerifyTopXParameterPage(String clusterId) {
        test = extent.startTest("CUR05_VerifyTopXParameterPage" + clusterId,
                "Verify  the mandatory fields and should add the configirations to the list");
        test.assignCategory(" Cluster - User Report");
        Log.startTestCase("CUR05_VerifytopXparameterpage");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);
        Schedule schedule = new Schedule(driver);

        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);

        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        UserReport userReport = new UserReport(driver);
        UserActions userActions = new UserActions(driver);
      /*  TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
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
        waitExecuter.waitUntilPageFullyLoaded();*/
        try {
         /*   userReportPageObject.addconfiguration.click();
            waitExecuter.sleep(1000);
            test.log(LogStatus.PASS, "Successfully clicked on add configuration.");

        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully clicked on add configuration page.");*/
            test.log(LogStatus.INFO, "Navigate to reports Schedule Report page");
            waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.reports);
            MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
            waitExecuter.waitUntilPageFullyLoaded();
            userReport.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
            waitExecuter.waitUntilPageFullyLoaded();
            userReport.setTopXNumber("30");
            waitExecuter.waitUntilPageFullyLoaded();
            HomePage homePage = new HomePage(driver);
            homePage.selectMultiClusterId(clusterId);
            waitExecuter.waitUntilPageFullyLoaded();
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

            userReport.assignEmail("sray@unraveldata.com");
            waitExecuter.waitUntilPageFullyLoaded();
        /*waitExecuter.waitUntilElementPresent(userReportPageObject.addbutton);
        userReport.clickOnaddButton();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully added Topx parameter");
        userReport.clicksaveschedule();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully clicked save sheduele.");
        userActions.performActionWithPolling(userReportPageObject.saveschedule, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();*/
            userActions.performActionWithPolling(userReportPageObject.saveschedule, UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Successfully clicked save sheduele.");
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Unable to clicked on add configuration.");

        }
    }
}
