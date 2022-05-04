package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Marker.DbxRuns
@Marker.All
public class TC_DR_12 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_12.class);

    @Test()
    public void validateAllStatusTab() {
        test = extent.startTest("TC_DR_11.validateAllStatusTab",
                "Verify  All tab - Killed, Failed, Running, Success, Pending, Unknown, Waiting Jobs statuses are present.");
        test.log(LogStatus.INFO, "Login to the application");
        test.assignCategory("Jobs-Runs/All");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxApplicationsPageObject applicationsPageObject = new DbxApplicationsPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToJobsTab("Runs");
        try {
            // Navigate to Runs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            dballApps.inJobsSelectClusterAndLast7Days();
            waitExecuter.sleep(2000);

            /*
             * Validate that status types are --
             * "Killed","Failed","Running","Success","Pending","Unknown", "Waiting"
             */
            test.log(LogStatus.INFO,
                    "Assert status types - 'Killed','Failed','Running','Success','Pending','Unknown', 'Waiting'");
            loggingUtils.info("Assert status types - 'Killed','Failed','Running','Success','Pending','Unknown', 'Waiting'", test);
            List<String> existingStatusTypes = new ArrayList<>(Arrays.asList(PageConstants.RunsStatusType.STATUSTYPE));
            List<WebElement> statusTypes = dbpageObject.getStatusTypes;
            List<String> listOfStatusTypes = new ArrayList<String>();
            waitExecuter.sleep(2000);
            for (int i = 0; i < statusTypes.size(); i++) {
                listOfStatusTypes.add(statusTypes.get(i).getText().trim());
            }
            waitExecuter.sleep(2000);
            loggingUtils.info("List of status type actual - " + listOfStatusTypes, test);
            loggingUtils.info("List of status type expected - " + existingStatusTypes, test);
            Assert.assertTrue(listOfStatusTypes.equals(existingStatusTypes),
                    "Status types displayed does not match the expected status list");
            test.log(LogStatus.PASS, "Status types displayed match the expected status list");
        } catch (NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }
    }
}