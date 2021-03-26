package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;

import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.scripts.appdetails.TezAppsDetailsPage;

import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.AppDetailsTez
@Marker.All
public class TEZ_135 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TEZ_135.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_135_verifytheDags(String clusterId) {
        test = extent.startTest("TEZ_135_verifytheDags: " + clusterId,
                "Verify if the dags are present in the left pane and value should be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_135_verifytheDags");
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        tezDetailsPage.verifyDagsComponent(tezApps, false, true, false);
        waitExecuter.sleep(2000);
        test.log(LogStatus.SKIP, "Verified the Dags tab successfully");
    }
}
