package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_43_54 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_43_54.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyRunningApps(String clusterId) {
        test = extent.startTest("TC_HIVE_43_54.VerifyRunningApps",
                "Verify that in Running Apps only jobs with Running status are present.");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(4000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(4000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        applicationsPageObject.runningAppTab.click();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        if (hiveAppCount > 0) {
            Set<String> status = new HashSet<String>();
            for (WebElement statusFromTable : applicationsPageObject.getStatusColumnOfRunningApps) {
                status.add(statusFromTable.getText().trim().toLowerCase());
            }
            LOGGER.info("All apps status in tables are: " + status);
            LOGGER.info("Compare to status: " + PageConstants.JobsStatusType.RUNNING);
            Assert.assertTrue(status.contains(PageConstants.JobsStatusType.RUNNING),
                    "The status should contain only Running apps but it does not");
            test.log(LogStatus.PASS, "The status contains only Running apps.");
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have any running application under it.");
            throw new SkipException("The clusterId does not have any running application under it");
        }
        // Reset set filter to default
        test.log(LogStatus.INFO, "Reset set filter");
        LOGGER.info("Reset set filter");
        waitExecuter.sleep(2000);
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(3000);
    }
}
