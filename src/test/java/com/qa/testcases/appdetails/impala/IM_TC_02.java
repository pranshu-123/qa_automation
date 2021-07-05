package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.UserAction;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.All
@Marker.AppDetailsImpala
public class IM_TC_02 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_TC_02.class);

    /**
     * Verify that all KPIs are displayed in Unravel UI for impala
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyImpalaAppUI(String clusterId) {
        test = extent.startTest("IM_TC_02.verifyImpalaAppUI",
                "Verify that all KPIs are displayed in Unravel UI for " + "impala");
        test.assignCategory("App Details - Impala");
        WaitExecuter executer = new WaitExecuter(driver);
        loggingUtils.info("Started test case: IM_TC_02.verifyImpalaAppUI", test);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        UserActions actions = new UserActions(driver);
        actions.performActionWithPolling(topPanelPageObject.jobsTab, UserAction.CLICK);
        loggingUtils.info("Click on Job Tabs", test);
        AllApps allApps = new AllApps(driver);
        allApps.selectCluster(clusterId);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        try {
            appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            appDetailsPage.selectSuccessfulApplication();
            actions.performActionWithPolling(applicationsPageObject.clickOnAppId, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
            Assert.assertNotNull(appDetailsPage.getApplicationType(), "App type is null");
            Assert.assertNotEquals(appDetailsPage.getApplicationType(),
                    AppDetailsApplicationType.IMPALA.toString().toUpperCase(),
                    "Validate whether valid application type " + "is displayed.");
            loggingUtils.pass("Validate whether valid application type is displayed.", test);
            Assert.assertNotNull(appDetailsPage.getJobStatus(), "App Status is null");
            loggingUtils.pass("Validate whether valid application status is displayed.", test);
            Assert.assertNotNull(appDetailsPage.getApplicationUUID(), "App UUID is not displayed");
            loggingUtils.pass("Validate whether application uuid is displayed.", test);
            Assert.assertNotNull(appDetailsPage.getJobDuration(), "App Duration is not displayed.");
            loggingUtils.pass("Validate whether application duration is displayed.", test);
            Assert.assertNotNull(appDetailsPage.getJobStartTime(), "App Start Time is not displayed");
            loggingUtils.pass("Validate whether application start time is displayed.", test);
            Assert.assertNotNull(appDetailsPage.getJobEndTime(), "App End Time is not displayed.");
            loggingUtils.pass("Validate whether application end time is displayed.", test);
            Assert.assertNotNull(appDetailsPage.getApplicationDataIO(), "App Data IO is not displayed.");
            loggingUtils.pass("Validate whether Data IO is displayed.", test);
            appDetailsPage.closeModalbutton();
            waitExecuter.sleep(2000);
        } catch (Throwable ex) {
            appDetailsPage.closeModalbutton();
            throw ex;
        }
    }
}
