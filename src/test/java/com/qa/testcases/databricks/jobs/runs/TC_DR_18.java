package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
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

@Marker.DbxRuns
@Marker.All
public class TC_DR_18 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_18.class);

    @Test()
    public void validateCustomDateFunctionality() {
        test = extent.startTest("TC_DR_18.validateCustomDateFunctionality",
                "Verify the user can select custom date ranges");
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
            // Navigate to Runs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select custom date ranges");
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.clickOnDatePicker();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.selectCustomRange();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.setStartAndEndDateFromCurrentDate(-6,-2);
            datePicker.clickOnCustomDateApplyBtn();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            waitExecuter.sleep(2000);


            int appCount = dballApps.clickOnlyLink("Killed");
            waitExecuter.sleep(2000);
            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            Assert.assertEquals(appCount, totalCount,
                    "The Spark app count of SparkApp is not equal to " + "the total count of heading.");

            if (appCount > 0) {
                String headerAppId = dballApps.verifyStatus(dbpageObject);
                test.log(LogStatus.PASS, "Application Id is displayed in the Header: " + headerAppId);
                waitExecuter.waitUntilPageFullyLoaded();
                //Close apps details page
                MouseActions.clickOnElement(driver, dbpageObject.closeAppsPageTab);
                waitExecuter.sleep(3000);

            } else {
                test.log(LogStatus.SKIP, "No Application present ");
                loggingUtils.error("No Application present in the Runs page", test);
            }
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}