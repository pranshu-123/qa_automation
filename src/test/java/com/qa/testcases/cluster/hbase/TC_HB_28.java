package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.clusters.HBasePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_28 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_28.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="Verify the user navigate to the regional server UI")
    public void verifyRegionServerUI(String clusterId) {
        test = extent.startTest("TC_HB_28.verifyRegionServerUI",
                "Navigate to Region Server UI.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_28.verifyRegionServerUI");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        hbase.verifyRegionServer();
        LOGGER.info("Verified region server UI.");
        test.log(LogStatus.PASS, "Verified Region Server UI HBase.");


    }
}