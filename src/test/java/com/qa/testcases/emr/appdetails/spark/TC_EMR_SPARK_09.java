package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.EMRSpark
public class TC_EMR_SPARK_09 extends BaseClass {
    Logger logger = Logger.getLogger(TC_EMR_SPARK_09.class.getName());

    @Test()
    public void verifyAnalysisTab() {
        test = extent.startTest("TC_EMR_SPARK_09.verifyAnalysisTab",
                "Verify 1.The Analysis tab should list the Insights and the recommendations" +
                        "                    \"2. The tabs must be collapsable");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_EMR_SPARK_10_verifyResourcesTab");
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSummarryTabValidation(test, "Analysis", logger, false,false);
        test.log(LogStatus.PASS, "Verified the Analysis tab successfully");

    }
}
