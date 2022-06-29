package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.EMRSpark
public class TC_EMR_SPARK_10 extends BaseClass {
    Logger logger = Logger.getLogger(TC_EMR_SPARK_10.class.getName());

    @Test()
    public void verifyResourcesTab() {
        test = extent.startTest("TC_EMR_SPARK_10.verifyResourcesTab",
                "Verify that on a Clicking on Resources tab:\n" +
                        "1. Should contain all the below graphs\n" +
                        "  a. Task attempt- a doughnut graph- this should have all the attempts (failed, success)\n" +
                        "  b. Containers - bar chart- number of containers used for the app\n" +
                        "  c. Vcores - bar chart - should show the number of vcores used by app\n" +
                        "  d. Memory - Bar chart - should show the memory utilised");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_EMR_SPARK_10_verifyResourcesTab");
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSummarryTabValidation(test, "Resources", logger, false,false);
        test.log(LogStatus.PASS, "Verified the Resources tab successfully");
    }
}
