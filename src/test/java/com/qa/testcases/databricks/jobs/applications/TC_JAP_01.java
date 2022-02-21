package com.qa.testcases.databricks.jobs.applications;

import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.testcases.databricks.jobs.runs.TC_DR_01;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TC_JAP_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_DR_01.class.getName());

    @Test()
    public void validateJobsTab() {
        test = extent.startTest("TC_DR_01.validateFilterByClusterName",
                "Verify Jobs page should populate the data as per selected cluster");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DbAllApps allApps = new DbAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectClusterAndLast7Days();

        // Select cluster
        waitExecuter.sleep(1000);
        List<WebElement> applicationsClusterIds = applicationsPageObject.getApplicationClusterId;
        List<String> addClusterIdToList = new ArrayList<>();
        // Itterate through all the application to get the clusterId
        test.log(LogStatus.INFO, "Itterate through all the application to get the clusterId");
        LOGGER.info("Itterate through all the application to get the clusterId");
        for (int i = 0; i < applicationsClusterIds.size(); i++) {
            String appcClusterId = applicationsClusterIds.get(i).getText();
            //String subStringOfAppClusterId = appcClusterId.substring(0, 19);

        }
        waitExecuter.sleep(1000);

    }
}