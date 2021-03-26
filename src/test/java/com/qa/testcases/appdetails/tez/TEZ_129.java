package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Marker.AppDetailsTez
@Marker.All
public class TEZ_129 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_129.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_129_verifyTheQuery(String clusterId) {
        test = extent.startTest("TEZ_129_verifyTheQuery: " + clusterId,
                "Verify Query that was used to run the app must be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_129_verifyTheQuery");
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        tezDetailsPage.commonTabValidation(test, clusterId, "Query", logger,false);
        waitExecuter.sleep(2000);
        test.log(LogStatus.SKIP, "Verified the Query tab successfully");

    }
}
