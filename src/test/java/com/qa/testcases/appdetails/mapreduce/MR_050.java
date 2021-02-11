package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.annotations.Test;
@Marker.AppDetailsMr
@Marker.All
public class MR_050 extends BaseClass {
    Logger logger = Logger.getLogger(MR_050.class.getName());


    @Test(dataProvider = "clusterid-data-provider")
    public void MR_050_verifyAnalysisTab(String clusterId) {
        test = extent.startTest("MR_050_verifyClusterFilter: " + clusterId,
                "Verify Collapsable tabs on Recommendations and effeciency must be listed");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_050_verifyClusterFilter");

        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Analysis",logger, false);
        test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
    }
}

