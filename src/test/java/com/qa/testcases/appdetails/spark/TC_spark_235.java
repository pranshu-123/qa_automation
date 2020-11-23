package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_235 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1. Logs tab must contain One Driver and executor logs listed
     * 2. They must be collapsible tabs
     * 3. clicking on each must open logs window and should be able to scroll the logs
     */
    Logger logger = Logger.getLogger(TC_spark_235.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_235_verifyLogsTab(String clusterId) {
        test = extent.startTest("TC_spark_235_verifyLogsTab: " + clusterId,
                "Verify 1. Logs tab must contain One Driver and executor logs listed\n" +
                    " 2. They must be collapsible tabs\n" +
                    " 3. clicking on each must open logs window and should be able to scroll the logs");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_235_verifyLogsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Logs", logger, false);
        test.log(LogStatus.PASS, "Verified the Logs tab successfully");
    }
}
