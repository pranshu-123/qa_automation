package com.qa.testcases.appdetails.spark;

import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TC_spark_240 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1. Logs tab must contain One Driver and executor logs listed
     * 2. They must be collapsible tabs
     * 3. clicking on each must open logs window and should be able to scroll the logs
     */
    org.slf4j.Logger logger = LoggerFactory.getLogger(TC_spark_240.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_240_verifyTimingsTab(String clusterId) {
        test = extent.startTest("TC_spark_240_verifyTimingsTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_240_verifyTimingsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Timings", logger);
    }
}
