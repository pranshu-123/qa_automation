package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_234 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1. Spark Properties and values must be listed here
     * 2. Search page must be present here and user should be able to search any property and values
     * 3. These key words must be present as templates and users should be able to select them and list related
     * properties (metadata, memory, limit, resources, cpu, net, yarn, deploy)
     * 4. RESET should reset all the searches to default
     */
    Logger logger = LoggerFactory.getLogger(TC_spark_234.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_234_verifyConfigurationTab(String clusterId) {
        test = extent.startTest("TC_spark_234_verifyConfigurationTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_234_verifyConfigurationTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Configuratio...", logger);
        test.log(LogStatus.PASS, "Verified the Configurations tab successfully");
    }
}
