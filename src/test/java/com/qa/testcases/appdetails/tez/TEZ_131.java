package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.AppDetailsTez
@Marker.All
public class TEZ_131 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_131.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_131_VerifyTheTags(String clusterId) {
        test = extent.startTest("TEZ_131_VerifyTheTags: " + clusterId,
                "Verify Tags needs to be populated randomly");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_131_VerifyTheTags");
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage appsDetailsPage = new TezAppsDetailsPage(driver);
        appsDetailsPage.commonTabValidation(test, clusterId, "Tags", logger);
        test.log(LogStatus.PASS, "Verified the Tags tab successfully");

        //Close apps details page

        MouseActions.clickOnElement(driver, tezApps.homeTab);
    }
}
