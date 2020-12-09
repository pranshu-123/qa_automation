package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.UserAction;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.AppDetailsImpala
@Marker.All

public class IM_TC_06 extends BaseClass {
    private LoggingUtils loggingUtils = new LoggingUtils(IM_TC_05.class);

    /**
     * Verify Gantt Chart details for impala app details."
     * @param clusterId - ClusterId to run test cases on
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyGanttChartDetails(String clusterId) {
        test = extent.startTest("IM_TC_06.verifyGanttChartDetails", "Verify Gantt Chart details.");
        test.assignCategory("App Details - Impala");
        WaitExecuter executer = new WaitExecuter(driver);
        loggingUtils.info("Started test case: IM_TC_06.verifyGanttChartDetails", test);
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
        try {
            appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            actions.performActionWithPolling(appDetailsPage.getAppsDetailsAsPage().get(1), UserAction.CLICK);
            appDetailsPage.clickOnImpalaGanttChartTab();
            List<Integer> expectedIds = appDetailsPage.getGanttChartIdsInNumeric();
            List<Integer> actualIds = appDetailsPage.getGanttChartIdsInNumeric();
            Collections.sort(expectedIds);
            Assert.assertEquals(actualIds, expectedIds, "Fragment Ids are not sorted");
            loggingUtils.pass("Validated whether ids are sorted in gantt chart", test);
            appDetailsPage.closeModalbutton();
        } catch (Throwable ex) {
            appDetailsPage.closeModalbutton();
            throw ex;
        }
    }
}
