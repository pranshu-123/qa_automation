package com.qa.testcases.appdetails.impalaInsight;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.utils.LoggingUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*@author- Ojasvi
 *
 */
@Marker.ImpalaInsights
@Marker.All
public class IM_IN_03 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_IN_03.class);

    /**
     * Verify Impala insights SQL NON partitioned table Events
     */

    @Test
    public void verifyNonPartitionedEvents() {
        test = extent.startTest("IM_IN_03.verifyNonPartitionedEvents",
                "Verify Impala application non partitioned title in in-efficient apps. ");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_IN_03.verifyNonPartitionedEvents", test);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);

        try {
            loggingUtils.info("Navigate to inefficient tab and select last 90 days", test);
            appDetailsPage.navigateToJobsTab();
            appDetailsPage.navigateToInefficientApps();
            datePicker.clickOnDatePicker();
            datePicker.selectLast7Days();
            loggingUtils.info("Select only impala application and get its count", test);
            int appCount = appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            loggingUtils.info("App count for impala- " + appCount, test);
            if (appCount > 0) {
                loggingUtils.info("Search for appId", test);
                appDetailsPage.searchByAppID(PageConstants.appId.appIdForImpala);
                List<String> titles = appDetailsPage.getEfficiencyTags();
                loggingUtils.info("Titles on page - " + titles, test);
                loggingUtils.info("Expected title on page - " + PageConstants.EventTypes.SQLNonPartitionedTableEvent, test);
                appDetailsPage.close();
                Assert.assertTrue(titles.contains(PageConstants.EventTypes.SQLNonPartitionedTableEvent),
                        "Does not contain title as expected in analysis tab- "+PageConstants.EventTypes.SQLNonPartitionedTableEvent);
                test.log(LogStatus.PASS, "SQLNonPartitionedTableEvent verified ");
            } else {
                loggingUtils.info("There are no successful apps for impala for selected cluster", test);
                test.log(LogStatus.SKIP, "There are no successful apps for impala ");
            }
        } catch (NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }

    }
}
