package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.DbxRuns
@Marker.All
public class TC_DR_25 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_25.class);

    @Test()
    public void validateCustomRange() {
        test = extent.startTest("TC_DR_25.validateCustomRange",
                "Verify the apps listed in page for selected date picker filter");
        test.log(LogStatus.INFO, "Login to the application");
        test.assignCategory("Jobs-Runs/All");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to Runs tab from header");
        dballApps.navigateToJobsTab("Runs");
        try {
            // Click on date picker and select custom range
            test.log(LogStatus.INFO, "Click on date picker and select custom range");
            loggingUtils.info("Click on date picker and select custom range", test);
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectCustomRange();
            waitExecuter.sleep(1000);
            Assert.assertTrue(dbpageObject.datepickerCalendar.isDisplayed());
            test.log(LogStatus.PASS, "Verified custom range opens date-time calendar.");

          } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}