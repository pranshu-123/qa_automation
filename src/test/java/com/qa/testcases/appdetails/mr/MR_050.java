package com.qa.testcases.appdetails.mr;

import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.testcases.appdetails.spark.TC_spark_222;
import com.qa.testcases.appdetails.spark.TC_spark_230;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.annotations.Test;

public class MR_050 extends BaseClass {
    Logger logger = Logger.getLogger(MR_050.class.getName());


    @Test(dataProvider = "clusterid-data-provider")
    public void MR_050_verifytheclusterlistedbasedonthefilter(String clusterId) {
        test = extent.startTest("MR_050_verifytheclusterlistedbasedonthefilter: " + clusterId,
                "Verify User must be able to filter by the cluster  and apps must be listed based on the filter");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_050_verifytheclusterlistedbasedonthefilter");

        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Analysis",logger, false);
        test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
    }
}

