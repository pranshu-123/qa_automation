package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_238 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1.Program tab must be loaded on the UI and data should be available
     */
    org.slf4j.Logger logger = LoggerFactory.getLogger(TC_spark_238.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_238_verifyProgramsTab(String clusterId) {
        test = extent.startTest("TC_spark_238_verifyProgramsTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_238_verifyProgramsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Program", logger);
    }
}
