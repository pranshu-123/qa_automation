package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_62 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_62.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyAppDetailsRightHeader(String clusterId) {
        test = extent.startTest("TC_HIVE_62.VerifyAppDetailsRightHeader",
                "Verify that when Hive app should contain KPIs Owner, Cluster and Quene Name.");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
        Actions actions = new Actions(driver);
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
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        // Select only Hive apps and check if app counts is 0 or greater than that
        test.log(LogStatus.INFO, "Select only hive apps and check if app counts is 0 or greater than that");
        LOGGER.info("Select only hive apps and check if app counts is 0 or greater than that");
        sparkApp.clickOnlyLink("Hive");

        applicationsPageObject.expandStatus.click();
        int appCount = sparkApp.clickOnlyLink("Success");

        if (appCount > 0) {
            // Get Hive app id from the first row
            test.log(LogStatus.INFO, "Get Hive app id from the first row");
            LOGGER.info("Get Hive app id from the first row");
            WebElement name = applicationsPageObject.getAppNameFromTable;
            actions.moveToElement(name).perform();
            waitExecuter.sleep(1000);
            actions.moveToElement(applicationsPageObject.copyAppName).perform();
            waitExecuter.sleep(1000);
            applicationsPageObject.copyAppName.click();
            String appId = null;
            try {
                String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);
                String[] getCopiedText = data.split(":");
                // Click on first app in table to navigate to app details page
                test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
                LOGGER.info("Click on first app in table to navigate to app details page");
                applicationsPageObject.getStatusFromTable.click();
                waitExecuter.waitUntilElementPresent(applicationsPageObject.loader);
                appId = getCopiedText[2];
            } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
                driver.navigate().back();
                waitExecuter.sleep(5000);
                allApps.reset();
                e.printStackTrace();
            }
            // Assert that Owner, Queue and Cluster is displayed
            test.log(LogStatus.INFO, "Assert that Owner, Queue and Cluster is displayed");
            LOGGER.info("Assert that Owner, Queue and Cluster is displayed");
            List<String> ownerClusterQueueName = new ArrayList<>();
            for (int i = 0; i < applicationsPageObject.getOwnerClusterQueueName.size(); i++) {
                ownerClusterQueueName.add(applicationsPageObject.getOwnerClusterQueueName.get(i).getText().trim());
                LOGGER.info("Right header for hive app loaded successfully- "
                        + ownerClusterQueueName);
            }
            waitExecuter.sleep(3000);
            Assert.assertNotNull(ownerClusterQueueName,
                    "Right header didn't load for app id- " + appId);
            test.log(LogStatus.PASS, "Owner, cluster and Queue Name loaded successfully for app id- " + appId);
            waitExecuter.sleep(1000);
            // Navigate back to parent page and click on reset
            test.log(LogStatus.INFO, "Navigate back to parent page and click on reset");
            LOGGER.info("Navigate back to parent page and click on reset");
            driver.navigate().back();
            waitExecuter.sleep(5000);
            allApps.reset();
            driver.navigate().refresh();
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have application as per the filter applied under it.");
            waitExecuter.sleep(1000);
            // Click on reset if there are no hive apps
            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
            LOGGER.info("Click on reset if there are no hive apps");
            allApps.reset();
            throw new SkipException("The clusterId does not have any application under it.");
        }
    }
}