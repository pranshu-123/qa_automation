package com.qa.testcases.appdetails.tez;

import com.qa.base.BaseClass;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class TEZ_122 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_122.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_122_verifyHiveappswithclusterIDs(String clusterId) {
        test = extent.startTest("TEZ_122_verifyHiveappswithclusterIDs: " + clusterId,
                "Verify User must be able to filter by the cluster  and apps must be listed based on the filter");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_122_verifyHiveappswithclusterIDs");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);


        logger.info("Select 'Only' Tez from app types and get its jobs count");
        sparkApp.clickOnlyLink("Tez");
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        // Expand status filter on left pane
        test.log(LogStatus.INFO, "Expand status filter on left pane");
        logger.info("Expand status filter on left pane");
        applicationsPageObject.expandStatus.click();
        waitExecuter.sleep(2000);
        // To apply filter - De-select all status types
        test.log(LogStatus.INFO, "To apply status filter - De-select all status types");
        logger.info("To apply status filter - De-select all status types");
        allApps.deselectAllStatusTypes();
        waitExecuter.sleep(2000);


    }
}