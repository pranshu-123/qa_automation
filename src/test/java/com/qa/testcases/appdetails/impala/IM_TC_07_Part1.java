package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

/*@author- Ojasvi
 *
 */
@Marker.AppDetailsImpala
@Marker.All
public class IM_TC_07_Part1 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_TC_07_Part1.class);

    /**
     * Verify Impala application fragment count
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyFragmentCount(String clusterId) {
        test = extent.startTest("IM_TC_07_Part1.verifyKPIDisplayed",
                "Verify Impala application fragment count with Query Plan count");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_TC_07_Part1.verifyKPIDisplayed", test);
        AllApps allApps = new AllApps(driver);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);

        try {
            appDetailsPage.navigateToJobsTab();
            allApps.selectCluster(clusterId);
            datePicker.clickOnDatePicker();
            datePicker.selectLast90Days();
            appDetailsPage.selectSuccessfulApplication();
            int appCount = appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            if (appCount > 0) {
                appDetailsPage.clickOnFirstImpalaJob();
                loggingUtils.info("Clicked on the first impala application", test);
                int fragmentsCount = appDetailsPage.getFragmentCount();
                loggingUtils.info("Fragment count actual- " + fragmentsCount, test);
                int fragmentCountQueryPlan = appDetailsPage.getQueryPlanFragmentCount();
                loggingUtils.info("Fragment count expected- " + fragmentCountQueryPlan, test);
                appDetailsPage.close();
                Assert.assertEquals(fragmentsCount, fragmentCountQueryPlan,
                        "Fragment count did not match with expected count.");
                test.log(LogStatus.PASS, "Fragment count matched with expected count.");
            } else {
                loggingUtils.info("There are no successful apps for impala for selected cluster", test);
                test.log(LogStatus.SKIP, "There are no successful apps for impala for selected cluster- " + clusterId);
            }
            appDetailsPage.reset();
        } catch (NoSuchElementException ex) {
            appDetailsPage.reset();
        }

    }
}
