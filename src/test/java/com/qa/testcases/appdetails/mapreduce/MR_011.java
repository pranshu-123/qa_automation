package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.AppDetailsMr
@Marker.All
public class MR_011 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_006.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_011_verifydataIO(String clusterId) {
        test = extent.startTest("MR_011_verifydataIO: " + clusterId,
                "Verify if data IO have values");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_011_verifydataIO");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");
        int appCount = mrDetailsPage.clickOnlyLink("MapReduce");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of TezApp is not equal to " +
                "the total count of heading.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        if (appCount > 0) {
            String readIO = mrApps.getRead.getText();
            mrDetailsPage.checkAppsJobTableData(readIO, "Read");
            test.log(LogStatus.PASS, "Read IO is displayed in the Map Reduce Table: " + readIO);

            String writeIO = mrApps.getWrite.getText();
            mrDetailsPage.checkAppsJobTableData(writeIO, "Write");
            test.log(LogStatus.PASS, "Write IO is displayed in the Map Reduce Table: " + writeIO);

        } else {
            test.log(LogStatus.SKIP, "No map reduce Application present");
            logger.error("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
    }
}

