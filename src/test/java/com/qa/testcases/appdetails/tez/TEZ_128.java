package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.AppDetailsTez
@Marker.All
public class TEZ_128 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_128.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_128_verifyTheAnalysisTab(String clusterId) {
        test = extent.startTest("TEZ_128_verifyTheAnalysisTab: " + clusterId,
                "Verify App details should have the Analysis tab and the app events must be captured");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_128_verifyTheAnalysisTab");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);

        tezDetailsPage.commonTabValidation(test, clusterId, "Analysis", logger,false);
        waitExecuter.sleep(2000);
        test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
        }
}
