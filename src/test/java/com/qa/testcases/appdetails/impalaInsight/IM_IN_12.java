package com.qa.testcases.appdetails.impalaInsight;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.ImpalaEventTypes;
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

public class IM_IN_12 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_IN_12.class);

    /**
     * Verify Impala NON Columnar Tables Events
     */

    @Test
    public void verifyNonColumnarEvents() {
        test = extent.startTest("IM_IN_12.verifyNonColumnarEvents",
                "Verify Impala Non Columnar Table Events. ");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_IN_12.verifyNonColumnarEvents", test);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);

        try {
            loggingUtils.info("Navigate to inefficient tab and select last 90 days", test);
            appDetailsPage.navigateToJobsTab();
            appDetailsPage.navigateToInefficientApps();
            datePicker.clickOnDatePicker();
            test.log(LogStatus.PASS, "Select last 30 days.");
            datePicker.selectLast30Days();
            loggingUtils.info("Select only impala application and get its count", test);
            int appCount = appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            loggingUtils.info("App count for impala- " + appCount, test);
            if (appCount > 0) {
                loggingUtils.info("Select event filter and navigate to first Job of the page", test);
                appDetailsPage.selectEventFilter(ImpalaEventTypes.ImpalaNonColumnarTablesEvent);
                appDetailsPage.clickOnFirstInefficientJob();
                List<String> titles = appDetailsPage.getEfficiencyTags();
                loggingUtils.info("Titles on page - " + titles, test);
                loggingUtils.info("Expected Titles on page - " + PageConstants.EventTypes.ImpalaNonColumnarTablesEvent, test);
                appDetailsPage.close();
                Assert.assertTrue(titles.contains(PageConstants.EventTypes.ImpalaNonColumnarTablesEvent),
                        "Does not contain title as expected in analysis tab- " + PageConstants.EventTypes.ImpalaNonColumnarTablesEvent);
                test.log(LogStatus.PASS, "ImpalaNonColumnarTablesEvent verified ");
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
