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
public class TC_spark_238 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1.Program tab must be loaded on the UI and data should be available
     */
    Logger logger = Logger.getLogger(TC_spark_238.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_238_verifyProgramsTab(String clusterId) {
        test = extent.startTest("TC_spark_238_verifyProgramsTab: " + clusterId,
                "Verify Program tab must be loaded on the UI and data should be available");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_238_verifyProgramsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Program", logger);
        test.log(LogStatus.PASS, "Verified the Program tab successfully");
    }
}
