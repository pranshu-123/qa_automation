package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.databricks.jobs.jobs.TC_DJ_01;
import com.qa.testcases.databricks.jobs.runs.TC_DR_01;
import com.qa.utils.Log;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Marker.DbxAppDetails
public class TC_JAP_01 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DJ_01.class);

    @Test()
    public void validateAppListedUI() {
        test = extent.startTest("TC_DR_06.validateFailedStatus",
                "Verify All the Failed jobs are listed on the page");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject=new DbxSummaryPageObject(driver);
        DbxApplicationsPageObject applicationsPageObject = new DbxApplicationsPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage  summaryPage=new SummaryDetailsPage(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToRunsTab();
        try {
            // Navigate to Runs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            dballApps.inJobsSelectClusterAndLast7Days();
            waitExecuter.sleep(2000);


            int appCount = dballApps.clickOnlyLink("Success");

            if (appCount > 0) {
                String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
                test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
                //Close apps details page
                MouseActions.clickOnElement(driver, summaryPageObject.closeIcon);
                waitExecuter.sleep(3000);

            } else {
                test.log(LogStatus.SKIP, "No Application present ");
                loggingUtils.error("No Application present in the Runs page",test);
            }
        } catch (NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }
    }
}

