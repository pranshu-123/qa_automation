package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.EMRSpark
public class TC_EMR_SPARK_15 extends BaseClass {
    Logger logger = Logger.getLogger(TC_EMR_SPARK_15.class.getName());

    @Test()
    public void TC_EMR_SPARK_15_verifySuccessInErrorsTab() {
        test = extent.startTest("TC_EMR_SPARK_15_verifySuccessInErrorsTab: ",
                "Verify  1. Errors must be listed under this tab +\n" +
                        "2. Tabs must be collapsable\\n\" +\n" +
                        "3. error related to drivers, executors and rm diagnostics are some kind of error catagories\"");
        test.assignCategory("Apps Details-Spark");
        Log.startTestCase("TC_EMR_SPARK_12_verifySuccessInErrorsTab");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.assignCategory(" Apps Details-Spark");
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSummarryTabValidation(test, "Error", logger, false, false);
        test.log(LogStatus.PASS, "Verified the Status Success under the errors tab successfully");
    }
}
