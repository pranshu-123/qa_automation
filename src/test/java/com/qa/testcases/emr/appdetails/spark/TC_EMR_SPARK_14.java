package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.EMRSpark
public class TC_EMR_SPARK_14 extends BaseClass {
    Logger logger = Logger.getLogger(TC_EMR_SPARK_12.class.getName());
    @Test()
    public void TC_EMR_SPARK_14_verifyTagsTab() {
        test = extent.startTest("TC_EMR_SPARK_14_verifyTagsTab: ",
                "Verify that the Tags tab contains tags in form of key value pair");
        Log.startTestCase("TC_EMR_SPARK_14_verifyTagsTab");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.assignCategory("Apps Details-Spark");
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSummarryTabValidation(test, "Tags", logger, false,false);
        test.log(LogStatus.PASS, "Verified the Status Unknown in errors tab successfully");
    }
}
