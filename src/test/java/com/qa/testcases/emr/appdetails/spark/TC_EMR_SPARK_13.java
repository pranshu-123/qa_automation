package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.EMRSpark
public class TC_EMR_SPARK_13 extends BaseClass {
    Logger logger = Logger.getLogger(TC_EMR_SPARK_13.class.getName());
    @Test()
    public void TC_EMR_SPARK_13_verifyConfigurationTab() {
        test = extent.startTest("TC_EMR_SPARK_12_verifyConfigurationTab: ",
                "Verify Spark Properties and values must be listed in the UI, " +
                        "1. Spark Properties and values must be listed here\n" +
                        "2. Search page must be present here and user should be able to search any property and values\n" +
                        "3. These key words must be present as templates and users should be able to select them and list related\n" +
                        "    properties (metadata, memory, limit, resources, cpu, net, yarn, deploy)\n" +
                        "4. RESET should reset all the searches to default");
        Log.startTestCase("TC_EMR_SPARK_13_verifyConfigurationTab");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.assignCategory(" Apps Details-Spark");
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSummarryTabValidation(test, "Configuratio...", logger, false,false);
        test.log(LogStatus.PASS, "Verified the Configurations tab successfully");
    }
}