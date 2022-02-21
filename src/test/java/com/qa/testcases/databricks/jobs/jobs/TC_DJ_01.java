package com.qa.testcases.databricks.jobs.jobs;

import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_DJ_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_DJ_01.class.getName());

    @Test()
    public void validateFilterByClusterName() {
        test = extent.startTest("TC_DR_01.validateFilterByClusterName",
                "Verify Jobs page should populate the data as per selected cluster");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps allApps = new DbAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();

        String jobsTab=dbpageObject.jobs.getText().trim();
        test.log(LogStatus.INFO, "Jobs tab from header is :"+jobsTab);
    }
}
