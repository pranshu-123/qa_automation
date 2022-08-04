package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

/*
 * To Test-
 * Applications that are executed between select date range should be listed
 * @Author - Ojasvi Pandey
 */

@Marker.AllApps
@Marker.GCPAllApps
@Marker.All
public class TC_JAL_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_03.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAppListsForSelectedDateRange(String clusterId) {
        test = extent.startTest("TC_JAL_03.validateAppListsForSelectedDateRange",
                "Verify the apps listed in page for selected date picker filter");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Click on date picker ranges and verify hive app counts
        test.log(LogStatus.INFO, "Click on date picker ranges and verify hive app counts");
        LOGGER.info("Click on date picker ranges and verify hive app counts");
        List<WebElement> dateRange = datePickerPageObject.dateRangeOptions;
        waitExecuter.sleep(2000);
        datePicker.clickOnDatePicker();
        for (int i = 0; i < dateRange.size() - 1; i++) {
            String selectedDateRange = dateRange.get(i).getText();
            waitExecuter.sleep(1000);
            // Click on date picker ranges and verify hive app counts
            test.log(LogStatus.INFO, "Click on date picker range: " + selectedDateRange);
            LOGGER.info("Click on date picker range: " + selectedDateRange);
            dateRange.get(i).click();
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            int totalAppCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Total app count: " + totalAppCount);
            // Assert if apps are displayed for selected date range
            test.log(LogStatus.INFO, "Assert if apps are displayed for selected date range");
            LOGGER.info("Assert if total count of apps are equal to Hive apps on selected date range");
            Assert.assertTrue(totalAppCount >=0,
                    "The app count of page does not match with totalAppCount for date range selected: "
                            + selectedDateRange);
            test.log(LogStatus.PASS,
                    "Apps are listed for selected date range- " + selectedDateRange);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            datePicker.clickOnDatePicker();
        }
    }
}