package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.All
public class MR_058 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_058.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_058_verifyMetricsGraphs(String clusterId) {
        test = extent.startTest("MR_058_verifyMetricsGraphs: " + clusterId,
                "Verify the Metrics Graphs are present \n" + "OS Memory");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_058_verifyMetricsGraphs");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);

        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Tags", logger, false);
        test.log(LogStatus.PASS, "Verified the Tags tab successfully");
    }
}
