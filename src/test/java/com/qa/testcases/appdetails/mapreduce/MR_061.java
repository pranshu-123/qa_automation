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
public class MR_061 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_061.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_061_verifyMetricsTab(String clusterId) {
        test = extent.startTest("MR_061_verifyMetricsTab: " + clusterId,
                "Verify metrics tab is populated");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_061_verifyMetricsTab");
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Metrics", logger, true);
        test.log(LogStatus.PASS, "Verified the Metrics tab successfully");

    }
}
