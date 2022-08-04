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
public class TC_spark_229 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1.The Analysis tab should list the Insights and the recommendations
     * 2. The tabs must be collapsable
     */
    Logger logger = Logger.getLogger(TC_spark_229.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_229_verifyAnalysisTab(String clusterId) {
        test = extent.startTest("TC_spark_229_verifyAnalysisTab: " + clusterId,
                "Verify 1.The Analysis tab should list the Insights and the recommendations\n" +
                    "2. The tabs must be collapsable");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_229_verifyAnalysisTab");
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Analysis", logger, false);
        test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
    }
}
