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
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.AppDetailsTez
@Marker.All
public class TEZ_130 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_130.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_130_verifytheTablesTab(String clusterId) {
        test = extent.startTest("TEZ_130_verifytheTablesTab(: " + clusterId,
                "Verify Tables accessed by the query must be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_130_verifytheTablesTab");

        //TBD Tables tab not poulated in apps detail page

        test.log(LogStatus.SKIP, "Verified the Tables tab not poulated in apps detail page");

    }

}
