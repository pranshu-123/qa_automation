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

import java.util.List;

/*@author- Ojasvi
 *
 */
@Marker.AppDetailsImpala
@Marker.All
public class IM_TC_18_Part1 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_TC_18_Part1.class);

    /**
     * Verify Impala application hover over title names
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyTitlesInImpalaApp(String clusterId) {
        test = extent.startTest("IM_TC_18_Part1.verifyTitlesInImpalaApp",
                "Verify Impala application, on hovering the value is being displayed. ");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_TC_18_Part1.verifyTitlesInImpalaApp", test);
        AllApps allApps = new AllApps(driver);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);

        try {
            appDetailsPage.navigateToJobsTab();
            allApps.selectCluster(clusterId);
            datePicker.clickOnDatePicker();
            datePicker.selectLast30Days();
            appDetailsPage.selectSuccessfulApplication();
            int appCount = appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            loggingUtils.info("App count for impala- " + appCount, test);
            if (appCount > 0) {
                List<String> titleValues = appDetailsPage.getApplicationDetails();
                Assert.assertNotNull(titleValues, "Title values are not displayed for impala app.");
                test.log(LogStatus.PASS, "Title values are displayed for impala app.");
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
