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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_40_PART2 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_40_PART2.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySearchByTags(String clusterId) {
        test = extent.startTest("TC_HIVE_40_PART2.VerifyFilterByTags",
                "Verify that User is able to see the apps sorted based on tags.");
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
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(1000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(3000);
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
        // Scroll to view Tags filter
        test.log(LogStatus.INFO, "Scroll to view Tags filter.");
        LOGGER.info("Scroll to view Tags filter.");
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        waitExecuter.sleep(2000);
        List<WebElement> checkBoxTags = applicationsPageObject.tagsCheckboxes;
        List<WebElement> tagsType = applicationsPageObject.getTagTypes;
        List<String> selectedTagType = new ArrayList<String>();
        List<String> selectedFilterUnderTag = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            checkBoxTags.get(i).click();
            waitExecuter.sleep(2000);
            selectedTagType.add(tagsType.get(i).getText().trim().toLowerCase());
            LOGGER.info("Selected type: " + tagsType.get(i).getText().trim().toLowerCase());
            applicationsPageObject.tagTypeSearchbox.get(i).click();
            waitExecuter.sleep(2000);
            selectedFilterUnderTag
                    .add(applicationsPageObject.select1stOptionInTagsSearchBox.get(i).getText().trim().toLowerCase());
            LOGGER.info("Name of selected tag: "
                    + applicationsPageObject.select1stOptionInTagsSearchBox.get(i).getText().trim().toLowerCase());
            applicationsPageObject.select1stOptionInTagsSearchBox.get(i).click();
            waitExecuter.sleep(2000);

        }
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Click on the first app in table to get efficiency");
        LOGGER.info("Click on the first app in table to get efficiency");
        applicationsPageObject.getDurationFromTable.click();
        waitExecuter.sleep(5000);
        driver.getWindowHandle();
        applicationsPageObject.tagsTab.click();
        waitExecuter.sleep(1000);
        List<WebElement> getTagNamesFromTagTable = applicationsPageObject.tagsInTable;
        List<WebElement> getTagsDescriptionFromTagTable = applicationsPageObject.descriptionInTable;
        HashMap<String, String> map = new HashMap<>();
        for (int j = 0; j < getTagNamesFromTagTable.size(); j++) {
            map.put(getTagNamesFromTagTable.get(j).getText().trim().toLowerCase(),
                    getTagsDescriptionFromTagTable.get(j).getText().trim().toLowerCase());
        }
        waitExecuter.sleep(1000);
        driver.navigate().back();
        waitExecuter.sleep(5000);
        for (String tag : selectedTagType) {

            Assert.assertTrue(map.containsKey(tag),
                    "Table does not contain the tag selected " + map.keySet() + " ---" + selectedTagType);
        }
        for (String tagFilter : selectedFilterUnderTag) {
            Assert.assertTrue(map.containsValue(tagFilter),
                    "Table does not contain the tag filter selected " + map.values());
            test.log(LogStatus.PASS, "Table does contains the tag filter selected.");
        }
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
        // Reset tags filter to default
        test.log(LogStatus.INFO, "Reset tags filter");
        LOGGER.info("Reset tags filter");
        waitExecuter.sleep(2000);
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(3000);
    }
}
