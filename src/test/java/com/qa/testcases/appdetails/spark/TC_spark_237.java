package com.qa.testcases.appdetails.spark;

import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_spark_237 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1.The Analysis tab should list the Insights and the recommendations
     * 2. The tabs must be collapsable
     */
    org.slf4j.Logger logger = LoggerFactory.getLogger(TC_spark_237.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_237_verifyTagsTab(String clusterId) {
        test = extent.startTest("TC_spark_237_verifyTagsTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory("4620 Apps Details-Spark");
        Log.startTestCase("TC_spark_237_verifyTagsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Tags", logger);
    }
}
