package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.AllApps
@Marker.All
public class TC_JAL_06 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_06.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateApplicationsAreOfSelectedClusterId(String clusterId) {
        test = extent.startTest("TC_JAL_06.validateApplicationsAreOfSelectedClusterId",
                "Verify the apps listed in page are of selected cluster id only");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(1000);
        List<WebElement> applicationsClusterIds = applicationsPageObject.getApplicationClusterId;
        List<String> addClusterIdToList = new ArrayList<>();
        // Itterate through all the application to get the clusterId
        test.log(LogStatus.INFO, "Itterate through all the application to get the clusterId");
        LOGGER.info("Itterate through all the application to get the clusterId");
        for (int i = 0; i < applicationsClusterIds.size(); i++) {
            String appcClusterId = applicationsClusterIds.get(i).getText();
            String subStringOfAppClusterId = appcClusterId.substring(0, 19);
            addClusterIdToList.add(subStringOfAppClusterId);
        }
        waitExecuter.sleep(1000);
        // Assert if the application are of selected clusterIds
        test.log(LogStatus.INFO, "Assert if the application are of selected clusterIds");
        LOGGER.info("Assert if the application are of selected clusterIds");
        if (applicationsClusterIds.size() > 0)
            for (String appCluster : addClusterIdToList) {
                Assert.assertTrue(appCluster.contains(clusterId.substring(0, 19)),
                        "Listed applications are not of selected clusterId " + clusterId);
                test.log(LogStatus.PASS, "Listed applications are of selected clusterId  ");
            }
        else
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
    }
}
