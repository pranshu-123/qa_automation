package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.testcases.databricks.jobs.runs.TC_DR_08;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EMRAllApps
public class TC_EMR_JAL_15 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_08.class);

    @Test()
    public void validateSparkWithSuccessStatus() {
        test = extent.startTest("TC_DR_08.validateSuccessStatus",
                "Verify All the Success jobs are listed on the page with different application Type");
        test.assignCategory("Jobs-Runs/All");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSubTopPanelModulePageObject EmrSubTopPanel=new EmrSubTopPanelModulePageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");

        try {
            // Navigate to Runs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            allApps.inJobsSelectLast7Days();
            waitExecuter.sleep(2000);

            allApps.clickOnlyLink("Spark");
            waitExecuter.waitUntilPageFullyLoaded();
            applicationsPageObject.expandStatus.click();
            int appCount = allApps.clickOnlyLink("Success");
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            Assert.assertEquals(appCount, totalCount,
                    "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
            waitExecuter.sleep(2000);

            if (appCount > 0) {
                String headerAppId = allApps.verifyStatus(EmrSubTopPanel);
                test.log(LogStatus.PASS, "Application Id is displayed in the Header: " + headerAppId);
                waitExecuter.waitUntilPageFullyLoaded();
                //Close apps details page
                MouseActions.clickOnElement(driver, EmrSubTopPanel.closeAppsPageTab);
                waitExecuter.sleep(3000);

            } else {
                test.log(LogStatus.SKIP, "No Application present ");
                loggingUtils.error("No Application present in the Job Application page", test);
            }
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}
