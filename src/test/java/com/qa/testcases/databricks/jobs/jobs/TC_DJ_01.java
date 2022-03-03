package com.qa.testcases.databricks.jobs.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.testcases.databricks.jobs.runs.TC_DR_11;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DbxJobs
@Marker.All
public class TC_DJ_01 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DJ_01.class);

    @Test()
    public void validateFilterByClusterName() {
        test = extent.startTest("TC_DR_01.validateFilterByClusterName",
                "Verify Jobs tab present on top panel");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps allApps = new DbAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab("Jobs");

        String jobsTab=dbpageObject.jobs.getText().trim();
        test.log(LogStatus.INFO, "Jobs tab from header is :"+jobsTab);
    }
}
