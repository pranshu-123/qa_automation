package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_43 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_43.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyRunningApps(String clusterId) {
        test = extent.startTest("TC_HIVE_43.VerifyFilterByStatus",
                "Verify that in Running Apps only jobs with Running status are present.");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(1000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();

        applicationsPageObject.runningAppTab.click();
        waitExecuter.sleep(2000);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);

        // De-Select all app types
        test.log(LogStatus.INFO, "De-Select all app types");
        LOGGER.info("De-Select all app types");
        allApps.deselectAllAppTypes();
        int hiveAppCount = 0;
        // Select 'Only' hive type
        test.log(LogStatus.INFO, "Select 'Only' hive from app types");
        LOGGER.info("Select 'Only' hive from app types");
        List<String> appType = allApps.getAllApplicationTypes();
        for (int i = 0; i < appType.size(); i++) {
            if (appType.get(i).trim().toLowerCase().contains(PageConstants.AppTypes.HIVE)) {
                applicationsPageObject.selectOneApplicationType.get(i).click();
                waitExecuter.sleep(2000);
                hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(i).getText()
                        .replaceAll("[^\\dA-Za-z ]", "").trim());
                break;
            }
        }
        if (hiveAppCount > 0) {
            Set<String> status = new HashSet<String>();
            for (WebElement statusFromTable : applicationsPageObject.getStatusColumnFromTable) {
				status.add(statusFromTable.getText().trim().toLowerCase());
            }
            Assert.assertTrue(status.contains(PageConstants.JobsStatusType.RUNNING));
            test.log(LogStatus.PASS, "The status contains only Running apps.");
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.PASS,
                    "The clusterId does not have any application under it and 'No Data Available' is displayed");
        }
    }
}
