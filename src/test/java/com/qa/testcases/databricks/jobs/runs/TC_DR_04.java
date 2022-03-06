package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

@Marker.DbxRuns
@Marker.All
public class TC_DR_04 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_04.class);

    @Test()
    public void validateSearchIncorrectName() {
        test = extent.startTest("TC_DR_04.validateFilterByClusterName",
                "Verify search must show valid error");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbAllApps allApps = new DbAllApps(driver);
        dballApps.navigateToJobsTab("Runs");
        try {
            // Navigate to Runs tab select cluster and last 7 days
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            allApps.inJobsSelectClusterAndLast7Days();
            // Assert if the application are as per the filter applied on global search
            test.log(LogStatus.INFO, "Assert if the application listed are as per the filter applied on global search");
            loggingUtils.info("Assert if the application are as per the filter applied on search", test);

            loggingUtils.info("Get application type of first application listed in table", test);
            loggingUtils.info("Search for appId", test);
            dballApps.searchByAppID(PageConstants.jobId.appIdForDbxjob);
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}