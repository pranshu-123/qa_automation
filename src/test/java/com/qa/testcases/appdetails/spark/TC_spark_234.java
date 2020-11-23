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
public class TC_spark_234 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1. Spark Properties and values must be listed here
     * 2. Search page must be present here and user should be able to search any property and values
     * 3. These key words must be present as templates and users should be able to select them and list related
     * properties (metadata, memory, limit, resources, cpu, net, yarn, deploy)
     * 4. RESET should reset all the searches to default
     */
    Logger logger = Logger.getLogger(TC_spark_234.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_234_verifyConfigurationTab(String clusterId) {
        test = extent.startTest("TC_spark_234_verifyConfigurationTab: " + clusterId,
                "Verify Spark Properties and values must be listed in the UI, " +
                    "1. Spark Properties and values must be listed here\n" +
                    "2. Search page must be present here and user should be able to search any property and values\n" +
                    "3. These key words must be present as templates and users should be able to select them and list related\n" +
                    "    properties (metadata, memory, limit, resources, cpu, net, yarn, deploy)\n" +
                    "4. RESET should reset all the searches to default");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_234_verifyConfigurationTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Configuratio...", logger, false);
        test.log(LogStatus.PASS, "Verified the Configurations tab successfully");
    }
}
