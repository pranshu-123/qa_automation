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
public class IM_TC_18_Part2 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_TC_18_Part2.class);

    /**
     * Verify Impala application count to impala chargeback app counts
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyImpalaAppToChargeback(String clusterId) {
        test = extent.startTest("IM_TC_18_Part2.verifyImpalaAppToChargeback",
                "Verify Impala application count to impala chargeback app counts. ");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_TC_18_Part2.verifyImpalaAppToChargeback", test);
        AllApps allApps = new AllApps(driver);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AppDetailsPageObject appDetailsPageObject = new AppDetailsPageObject(driver);

        try {
            appDetailsPage.navigateToJobsTab();
            allApps.selectCluster(clusterId);
            datePicker.clickOnDatePicker();
            datePicker.selectLast30Days();
            int appCount = appDetailsPage.clickOnlyLink("Impala");
            waitExecuter.waitUntilElementClickable(appDetailsPageObject.resetButton);
            loggingUtils.info("App count for impala- " + appCount, test);
            if (appCount > 0) {
                String clusterName = appDetailsPage.getClusterName();
                loggingUtils.info("Impala chargeback clusterName - " + clusterName, test);
                int impalaCBcount = appDetailsPage.getAppCountImpalaChargeback(clusterName);
                loggingUtils.info("Impala chargeback job count - " + impalaCBcount, test);
                int aggregatedCount = appCount - impalaCBcount;
                loggingUtils.info("Difference of job count - " + aggregatedCount, test);
                Assert.assertTrue(aggregatedCount < 10 || aggregatedCount > -10,
                        "Aggregated count is more than 10 in count");
                test.log(LogStatus.PASS, "The count of impala apps and chargeback impala apps matches.");
            } else {
                loggingUtils.info("There are no successful apps for impala for selected cluster", test);
                test.log(LogStatus.SKIP, "There are no successful apps for impala for selected cluster- " + clusterId);
            }
        } catch (NoSuchElementException ex) {
            throw ex;
        }

    }
}
