package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
@Marker.DbxRuns
@Marker.All
public class TC_DR_58 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_58.class);

    @Test()
    public void validateCostFinishedTab() {
        test = extent.startTest("TC_DR_05.validateCostFinishedTab",
                "Verify All the Cost value are listed on the page");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Runs tab from header
        dballApps.navigateToJobsTab("Runs");
        dballApps.selectTab("Finished");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            // Navigate to Runs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            dballApps.inJobsSelectClusterAndLast7Days();
            waitExecuter.sleep(2000);


            int appCount = dballApps.clickOnlyLink("Success");
            waitExecuter.sleep(2000);
            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            Assert.assertEquals(appCount, totalCount,
                    "The Spark app count of SparkApp is not equal to " + "the total count of heading.");

            if (appCount > 0) {
                String headerAppId = dballApps.verifyCost(dbpageObject,test);
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