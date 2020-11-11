package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_37 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_37.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyHiveAppsInAppTable(String clusterId) {
        test = extent.startTest("TC_HIVE_37.verifyHiveAppsInAppTable",
                "Verify that In the UI Hive apps should be visible along with other apps on UI, in App list page");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
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
        // Search for hive in global search box
        test.log(LogStatus.INFO, "Search for hive in global search box");
        LOGGER.info("Search for hive in global search box");
        applicationsPageObject.globalSearchBox.click();
        applicationsPageObject.globalSearchBox.clear();
        applicationsPageObject.globalSearchBox.sendKeys("hive");
        applicationsPageObject.searchIcon.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
        waitExecuter.sleep(3000);
        List<WebElement> typesInPage = applicationsPageObject.getTypesColumnFromTable;
        List<String> nameOfTypesInPage = new ArrayList<>();
        int appCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        if(appCount>0) {
            for (int j = 0; j < 2; j++) {
                // Sort By Asc and Desc order of App Type in table
                test.log(LogStatus.INFO, "Sort By Asc and Desc order of App Type in table");
                LOGGER.info("Sort By Asc and Desc order of App Type in table");
                applicationsPageObject.sortByType.click();
                for (int i = 0; i < typesInPage.size(); i++) {
                    nameOfTypesInPage.add(typesInPage.get(i).getText().trim().toLowerCase());
                }
                waitExecuter.sleep(1000);
            }
            // Assert that after searching on global search with hive, hive type apps are
            // listed
            test.log(LogStatus.INFO, "Assert that after searching on global search with hive, hive type apps are listed");
            LOGGER.info("Assert that after searching on global search with hive, hive type apps are listed");
            Assert.assertTrue(nameOfTypesInPage.contains(PageConstants.AppTypes.HIVE), "Table does not contain app type 'Hive'.");
            test.log(LogStatus.PASS, "Table contains app type 'Hive'.");
            //Reset set filters
            test.log(LogStatus.INFO, "Reset set filters ");
            LOGGER.info("Reset set filters ");
            //tnode6
            waitExecuter.sleep(2000);
            driver.navigate().back();
            //applicationsPageObject.resetButton.click();
            waitExecuter.sleep(2000);
        }
        else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.sleep(1000);
            driver.navigate().refresh();
            // Reset the application filter
            test.log(LogStatus.INFO, "Reset the application filter");
            allApps.reset();
            throw new SkipException("The clusterId does not have any application under it");
        }
    }
}
