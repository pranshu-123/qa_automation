package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_230 extends BaseClass {
    /**
     * Verify that on a Clicking on Resources tab:
     * 1. Should contain all the below graphs
     * a. Task attempt- a doughnut graph- this should have all the attempts (failed, success)
     * b. Containers - bar chart- number of containers used for the app
     * c. Vcores - bar chart - should show the number of vcores used by app
     * d. Memory - Bar chart - should show the memory utilised
     */
    Logger logger = LoggerFactory.getLogger(TC_spark_230.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_230_verifyResourcesTab(String clusterId) {
        test = extent.startTest("TC_spark_230_verifyResourcesTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_230_verifyResourcesTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Resources", logger);
    }
}
