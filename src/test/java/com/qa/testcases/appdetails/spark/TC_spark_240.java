package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.GCPAppDetailsSpark
@Marker.All
public class TC_spark_240 extends BaseClass {
    /**
     * Verify if "Stage timing Distribution " is available
     * 2. Pie graphs should be available
     * 3. IO. metrics must be available - Bar graphs
     * 4. Time Metrics must be available - Bargraphs
     */
    Logger logger = Logger.getLogger(TC_spark_240.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_240_verifyTimingsTab(String clusterId) {
        test = extent.startTest("TC_spark_240_verifyTimingsTab: " + clusterId,
                "Verify if \"Stage timing Distribution \" is available \n" +
                    "2. Pie graphs should be available\n" +
                    "3. IO. metrics must be available - Bar graphs\n" +
                    "4. Time Metrics must be available - Bargraphs");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_240_verifyTimingsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Timings", logger, false);
        test.log(LogStatus.PASS, "Verified the Timings tab successfully");
    }
}
