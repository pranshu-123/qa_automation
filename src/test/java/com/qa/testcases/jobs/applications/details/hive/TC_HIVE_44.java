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
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_44 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_44.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyCopyAppIdFunctionality(String clusterId) {
        test = extent.startTest("TC_HIVE_44.VerifyCopyAppIdFunctionality",
                "Verify that on hovering over app, we can copy the application id by clicking on '+' icon.");
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
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        if (hiveAppCount > 0) {
            // Hive on to the first row
            test.log(LogStatus.INFO, "Hive on to the first row");
            LOGGER.info("Hive on to the first row");
            WebElement name = applicationsPageObject.getAppNameFromTable;
            actions.moveToElement(name).perform();
            waitExecuter.sleep(1000);
            actions.moveToElement(applicationsPageObject.copyAppName).perform();
            waitExecuter.sleep(1000);
            applicationsPageObject.copyAppName.click();
            Assert.assertTrue(applicationsPageObject.successBanner.isDisplayed(), "App id is not copied");
            test.log(LogStatus.PASS, "On clicking on + app id got copied");
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.click();
            waitExecuter.sleep(1000);
            try {
                String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);
                String[] getCopiedText = data.split(":");
                LOGGER.info("Search by ID - " + getCopiedText[2]);
                applicationsPageObject.globalSearchBox.sendKeys(getCopiedText[2]);
                waitExecuter.sleep(1000);
                applicationsPageObject.searchIcon.click();
                waitExecuter.sleep(1000);
            } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            waitExecuter.sleep(2000);
            /*
             * Validate that on copying the ID on globalsearch, only that row appears in
             * table
             */
            test.log(LogStatus.INFO, "Validate that on copying the ID on globalsearch, only that row appears in table");
            LOGGER.info("Validate that on copying the ID on globalsearch, only that row appears in table");
            String value = applicationsPageObject.globalSearchBox.getAttribute("value");
            Assert.assertTrue(applicationsPageObject.getStatusColumnFromTable.size() == 1,
                    "On searching by ID the table contains more than 1 row. " + value);
            test.log(LogStatus.PASS, "On searching by ID the table contains 1 row.");
            waitExecuter.sleep(1000);
            driver.navigate().back();
            waitExecuter.sleep(1000);
            driver.navigate().refresh();
            // Reset the application filter
            test.log(LogStatus.INFO, "Reset the application filter");
            allApps.reset();
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.sleep(1000);
            driver.navigate().back();
            waitExecuter.sleep(1000);
            driver.navigate().refresh();
            // Reset the application filter
            test.log(LogStatus.INFO, "Reset the application filter");
            allApps.reset();
            throw new SkipException("The clusterId does not have any application under it");
        }

    }
}