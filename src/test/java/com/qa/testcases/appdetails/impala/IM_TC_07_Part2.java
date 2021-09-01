package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.pagefactory.appsDetailsPage.AppDetailsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

/*@author- Ojasvi
 *
 */
@Marker.AppDetailsImpala
@Marker.All
public class IM_TC_07_Part2 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_TC_07_Part2.class);

    /**
     * Verify Impala application Operators count
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyOperatorCount(String clusterId) {
        test = extent.startTest("IM_TC_07_Part2.verifyOperatorCount",
                "Verify Impala application Operators count with Query Plan count");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_TC_07_Part2.verifyOperatorCount", test);
        AllApps allApps = new AllApps(driver);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AppDetailsPageObject appPageObject = new AppDetailsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AppDetailsPageObject appDetailsPageObject = new AppDetailsPageObject(driver);
        try {
            appDetailsPage.navigateToJobsTab();
            allApps.selectCluster(clusterId);
            datePicker.clickOnDatePicker();
            datePicker.selectLast90Days();
            waitExecuter.waitUntilElementClickable(appDetailsPageObject.resetButton);
            int appCount = appDetailsPage.clickOnlyLink("Impala");
            waitExecuter.waitUntilElementClickable(appDetailsPageObject.resetButton);
            appDetailsPage.selectSuccessfulApplication();
            parentCondition:
            if (appCount > 0) {
                appDetailsPage.zoomOut("50");
                appDetailsPage.clickOnFirstImpalaJob();
                loggingUtils.info("Clicked on the first impala application", test);
                if (appPageObject.noFragments.size() == 0) {
                    int operatorsCount = appDetailsPage.getQueryPlanOperatorViewCount();
                    loggingUtils.info("Operator count actual- " + operatorsCount, test);
                    int operatorCountQueryPlan = appDetailsPage.getQueryPlanOperatorCount();
                    loggingUtils.info("Operator count expected- " + operatorCountQueryPlan, test);
                    appDetailsPage.close();
                    Assert.assertEquals(operatorsCount, operatorCountQueryPlan,
                            "Operator count did not match with expected count.");
                    test.log(LogStatus.PASS, "Operator count matched with expected count.");
                } else {
                    Assert.assertEquals(appPageObject.noFragments.size(), 1);
                    waitExecuter.waitUntilPageFullyLoaded();
                    test.log(LogStatus.SKIP, "No fragments present");
                    break parentCondition;
                }
            } else {
                loggingUtils.info("There are no successful apps for impala for selected cluster", test);
                test.log(LogStatus.SKIP, "There are no successful apps for impala for selected cluster- " + clusterId);
                appDetailsPage.reset();
            }
        } catch (NoSuchElementException ex) {
            appDetailsPage.reset();
        }

    }
}
