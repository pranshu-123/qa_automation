package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.ApplicationEnum;
import com.qa.enums.UserAction;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.AppDetailsImpala
@Marker.All
public class IM_TC_01 extends BaseClass {

    private LoggingUtils loggingUtils = new LoggingUtils(IM_TC_01.class);
    /**
     * Verify Impala application is displayed in the Application page with all the KPI's.
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyKPIDisplayed(String clusterId) {
        test = extent.startTest("IM_TC_01.verifyKPIDisplayed", "Verify Impala application is displayed in the Application page with all" +
            " the KPI's.");
        test.assignCategory("App Details - Impala");
        WaitExecuter executer = new WaitExecuter(driver);
        loggingUtils.info("Started test case: IM_TC_01.verifyKPIDisplayed", test);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        UserActions actions = new UserActions(driver);
        actions.performActionWithPolling(topPanelPageObject.jobsTab, UserAction.CLICK);
        loggingUtils.info("Click on Job Tabs", test);
        AllApps allApps = new AllApps(driver);
        allApps.selectCluster(clusterId);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
        Boolean isOtherAppDisplayed = appDetailsPage.IsOtherApplicationTypesInTable(
            AppDetailsApplicationType.IMPALA);
        Assert.assertFalse(isOtherAppDisplayed,
            "All apps displayed applications other than impala");
        appDetailsPage.sortColumnAllAppsTable(ApplicationEnum.TYPE);
        isOtherAppDisplayed = appDetailsPage.IsOtherApplicationTypesInTable(
            AppDetailsApplicationType.IMPALA);
        Assert.assertFalse(isOtherAppDisplayed, "Other app are being displayed.");
        test.log(LogStatus.PASS, "Data are displayed properly");
    }
}
