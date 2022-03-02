package com.qa.testcases.databricks.jobs.runs;


import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.DbxJobsRuns
@Marker.All
public class TC_DR_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_DR_01.class.getName());

    @Test()
    public void validateFilterByClusterName() {
        test = extent.startTest("TC_DR_01.validateFilterByClusterName",
                "Verify the options available in date picker filter");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps allApps = new DbAllApps(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab("Runs");

        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectClusterAndLast7Days();

        List<WebElement> clusterList = allApps.getClusterListFromDropdown();
        Log.info("Verify the clusterList"+clusterList);
        waitExecuter.sleep(3000);
        allApps.clickOnClusterDropDown();
        waitExecuter.sleep(3000);

        for (int i = 0; i < clusterList.size(); i++) {
            String clustervalues = clusterList.get(i).getText();
            Log.info("The list of cluster filter in UI"+clustervalues);
            test.log(LogStatus.PASS, "The list of cluster filter in UI." +clustervalues);
        }
    }
}