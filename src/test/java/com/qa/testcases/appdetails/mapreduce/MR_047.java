package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.appdetails.spark.TC_spark_165;
import com.qa.testcases.appdetails.spark.TC_spark_229;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.All
public class MR_047 extends BaseClass {

    org.slf4j.Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_046.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_047_verifyMRJobsSummary(String clusterId) {
        test = extent.startTest("MR_047_verifyMRJobsSummary: " + clusterId,
                "Verify job summary must be defined based the actual state of the apps");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_047_verifyMRJobsSummary");
// Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        test.log(LogStatus.INFO, "Verify that the left pane has map reduce check box and the apps number");
        mrDetailsPage.clickOnlyLink("Map Reduce");
        applicationsPageObject.expandStatus.click();
        int appCount = mrDetailsPage.clickOnlyLink("Success");
        //Verify app details page
        if (appCount > 0) {
            String summaryStatus =  mrDetailsPage.verifyJobsSummary(mrApps);
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Map Reduce Jobs Summary is displayed in the application Header: " + summaryStatus);
            waitExecuter.sleep(4000);
            //Close apps details page
            waitExecuter.waitUntilElementPresent(mrApps.closeAppsPageTab);
            MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            waitExecuter.sleep(3000);

        } else {
            test.log(LogStatus.SKIP, "No Map Reduce Application present");
            logger.error("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }

    }
}