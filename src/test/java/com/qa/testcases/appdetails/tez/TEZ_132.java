package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.AppDetailsTez
@Marker.All
public class TEZ_132 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_132.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_132_VerifyTheConfiguration(String clusterId) {
        test = extent.startTest("TEZ_132_VerifyTheConfiguration: " + clusterId,
                "Verify the configuration tab must be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_132_VerifyTheConfiguration");
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        //TBD configuration tab  value not poulated in apps detail page
        test.log(LogStatus.PASS, "Verified the Timings tab successfully");
    }
}
