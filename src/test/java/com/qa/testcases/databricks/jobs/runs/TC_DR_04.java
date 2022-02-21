package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.DbxJobsRuns
@Marker.All
public class TC_DR_04 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_04.class);

    @Test()
    public void validateSearchIncorrectName() {
        test = extent.startTest("TC_DR_04.validateFilterByClusterName",
                "Verify search must be shown valid error");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps allApps = new DbAllApps(driver);

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
        } catch (
                NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }
    }
}