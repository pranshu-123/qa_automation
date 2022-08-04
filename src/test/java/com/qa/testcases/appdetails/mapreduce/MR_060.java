package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.EMRMapReduce
@Marker.GCPAppDetailsMr
@Marker.All
public class MR_060 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_060.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_060_verifyErrorsTab(String clusterId) {
        test = extent.startTest("MR_060_verifyErrorsTab: " + clusterId,
                "Verify errors should be populated in the UI");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_060_verifyErrorsTab");
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Errors", logger, true);
        waitExecuter.sleep(2000);
        test.log(LogStatus.PASS, "Verified the Errors tab successfully");
    }
}
