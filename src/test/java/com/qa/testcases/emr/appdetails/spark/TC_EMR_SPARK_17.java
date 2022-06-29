package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.EMRSpark
public class TC_EMR_SPARK_17 extends BaseClass {
    Logger logger = Logger.getLogger(TC_EMR_SPARK_17.class.getName());
    @Test()
    public void TC_EMR_SPARK_17_verifyLogsTab() {
        test = extent.startTest("TC_EMR_SPARK_17_verifyLogsTab: ",
                "Verify 1. Logs tab must contain One Driver and executor logs listed\\n\" +\n" +
                        "2. They must be collapsible tabs\\n\" +\n" +
                        "3. clicking on each must open logs window and should be able to scroll the logs");
        Log.startTestCase("TC_EMR_SPARK_17_verifyLogsTab");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.assignCategory(" Apps Details-Spark");
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSummarryTabValidation(test, "Logs", logger, false,false);
        test.log(LogStatus.PASS, "Verified the Timings tab successfully");
    }
}
