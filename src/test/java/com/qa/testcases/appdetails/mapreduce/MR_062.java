package com.qa.testcases.appdetails.mapreduce;

import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class MR_062 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_062.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_062_verifyConfigurationTab(String clusterId) {
        test = extent.startTest("MR_062_verifyConfigurationTab: " + clusterId,
                "Verify errors should be populated in the UI");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_062_verifyConfigurationTab");
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Configuration", logger, true);
        test.log(LogStatus.PASS, "Verified the Configuration tab successfully");

    }
}
