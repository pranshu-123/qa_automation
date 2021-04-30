package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.AppDetailsMr
@Marker.All
public class MR_062 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_062.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_062_verifyConfigurationTab(String clusterId) {
        test = extent.startTest("MR_062_verifyConfigurationTab: " + clusterId,
                "Verify the Configuration Tab should be populated in the UI");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_062_verifyConfigurationTab");
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Configuratio...", logger, false);
        test.log(LogStatus.PASS, "Verified the Configurations tab successfully");

    }
}
