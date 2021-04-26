package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_35 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_35.class.getName());

    @Test
    public void VerifyDateRangeFilter() {
        test = extent.startTest("TC_HIVE_35.VerifyDateRangeFilter",
                "Verify that on selecting different date range option we are able to view apps.");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userActions.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
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
            waitExecuter.sleep(3000);
            hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                    .replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Hive app count: " + hiveAppCount);
            int totalAppCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Total app count: " + totalAppCount);
            // Assert if total count of apps are equal to Hive apps on selected date range
            test.log(LogStatus.INFO, "Assert if total count of apps are equal to Hive apps on selected date range");
            LOGGER.info("Assert if total count of apps are equal to Hive apps on selected date range");
            Assert.assertEquals(hiveAppCount, totalAppCount,
                    "The app count of hive does not match with totalAppCount for date range selected: "
                            + selectedDateRange);
            test.log(LogStatus.PASS,
                    "The app count of hive match with totalAppCount for date range selected." + selectedDateRange);
            datePicker.clickOnDatePicker();
        }
        // Reset set filters
        test.log(LogStatus.INFO, "Reset set filters ");
        LOGGER.info("Reset set filters ");
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);
    }
}
