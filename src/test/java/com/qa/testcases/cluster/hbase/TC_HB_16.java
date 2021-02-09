package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.clusters.HBasePage;
import com.qa.testcases.migration.servicesversionscompatibility.TC_MP_SC_01;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_16 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_16.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyHBaseTab(String clusterId) {
        test = extent.startTest("TC_HB_16.verifyHBaseTab",
                "Verify HBase tab in Unravel UI.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_16.verifyHBaseTab");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver,hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hbase.selectDateAsLast30Days();
        LOGGER.info("HBase headers found: "+ hbase.getHBaseHeader());
        Assert.assertTrue(hbase.getHBaseHeader().length() > 0, "HBase header not found.");
        test.log(LogStatus.PASS, "Verified HBase tab in Unravel UI.");

    }
}
