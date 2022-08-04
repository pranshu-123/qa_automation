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
public class TC_spark_233 extends BaseClass {
    /**
     * 1. Errors must be listed under this tab
     * 2. Tabs must be collapsable
     * 3. error related to drivers, executors and rm diagnostics are some kind of error catagories
     */
    Logger logger = Logger.getLogger(TC_spark_233.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_233_verifyErrorsTab(String clusterId) {
        test = extent.startTest("TC_spark_233_verifyErrorsTab: " + clusterId,
                "Verify  1. Errors must be listed under this tab\n" +
                    " 2. Tabs must be collapsable\n" +
                    " 3. error related to drivers, executors and rm diagnostics are some kind of error catagories");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_233_verifyErrorsTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Errors", logger, true);
        test.log(LogStatus.PASS, "Verified the Errors tab successfully");
    }
}
