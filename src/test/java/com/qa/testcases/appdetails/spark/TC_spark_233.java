package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_233 extends BaseClass {
    /**
     * 1. Errors must be listed under this tab
     * 2. Tabs must be collapsable
     * 3. error related to drivers, executors and rm diagnostics are some kind of error catagories
     */
    org.slf4j.Logger logger = LoggerFactory.getLogger(TC_spark_233.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_233_verifyErrorsTab(String clusterId) {
        test = extent.startTest("TC_spark_233_verifyErrorsTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_233_verifyErrorsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Errors", logger);
    }
}
