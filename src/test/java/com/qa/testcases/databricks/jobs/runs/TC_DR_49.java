package com.qa.testcases.databricks.jobs.runs;

import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DR_49 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_17.class);

    @Test()
    public void validateResetFuncInFinishedTab() {
        test = extent.startTest("TC_DR_49.validateResetFuncInFinishedTab",
                "Selection of reset option should reset all the filters which are applied");
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
        try {
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.clickOnDatePicker();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.selectCustomRange();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.setStartAndEndDateFromCurrentDate(-3,-1);
            datePicker.clickOnCustomDateApplyBtn();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            // Get total app counts before applying filter
            waitExecuter.sleep(1000);
            int totalCountBeforeFilter = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            test.log(LogStatus.INFO, "Get total app counts before applying filter " + totalCountBeforeFilter);
            // Select cluster
            /*allApps.selectCluster(clusterId);*/
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            // To apply filter - De-select all application types
            test.log(LogStatus.INFO, "To apply filter - De-select all application types");
            dballApps.deselectAllAppTypes();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            waitExecuter.sleep(7000);
            // After de-selecting app type check the app counts
            int totalCountAfterFilter = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            test.log(LogStatus.INFO, "After de-selecting app type check the app counts " + totalCountAfterFilter);
            Assert.assertEquals(totalCountAfterFilter, totalCountBeforeFilter, "After de-selecting all app types is not equal to " +
                    "the total count of heading.");
            waitExecuter.waitUntilPageFullyLoaded();
            // Click on reset button
            test.log(LogStatus.INFO, "Click on reset button");
            dbpageObject.resetButton.click();
            waitExecuter.sleep(2000);

            // Get total app count after clicking on reset button
            int totalCountAfterReset = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            test.log(LogStatus.INFO, "Get total app count after clicking on reset button " + totalCountAfterReset);
            Assert.assertEquals(totalCountBeforeFilter, totalCountAfterReset,
                    "Total count of application are not same before and after filter ");
            test.log(LogStatus.PASS, "Total count of application are same before and after filter ");
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}