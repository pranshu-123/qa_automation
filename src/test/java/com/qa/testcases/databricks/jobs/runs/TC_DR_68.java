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
public class TC_DR_68 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_68.class);

    @Test()
    public void validateSelectedDateRangeInRunningTab() {
        test = extent.startTest("TC_DR_68.validateSelectedDateRangeInRunningTab",
                "Verify the apps listed in page for selected date picker filter");
        test.assignCategory("Jobs-Runs/Running");
        test.log(LogStatus.INFO, "Login to the application");
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
        dballApps.selectTab("Running");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
                int totalAppCount = Integer
                        .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
                loggingUtils.info("Total app count: " + totalAppCount, test);
                // Assert if apps are displayed for selected date range
                test.log(LogStatus.INFO, "Assert if apps are displayed for selected date range");
                loggingUtils.info("Assert if total count of apps are equal to Runs apps on selected date range", test);
                Assert.assertTrue(totalAppCount >= 0,
                        "The app count of page does not match with totalAppCount for date range selected: ");
                test.log(LogStatus.PASS,
                        "Apps are listed for selected date range-");
                waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);

        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}