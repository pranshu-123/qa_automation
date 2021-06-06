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
public class TC_HB_42 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_42.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify all column values in the table metrics.")
    public void verifyTablesInARegionServer (String clusterId) {
        test = extent.startTest("TC_HB_42.verifyTablesInARegionServer",
                "Verify all column values in table metrics.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_42.verifyTablesInARegionServer");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        //Click on 'Region Server' and verify hbase region server tab
        hbase.verifyRegionServer();

        //Verify region server names
        hbase.verifyRegionSeverNames();
        LOGGER.info("Verified HBase region server names.");
        test.log(LogStatus.PASS, "Verified HBase region server names.");

        //Verify Tables in a region server
        hbase.verifyTableNamesInRegionServer();
        LOGGER.info("Verified all table name of a HBase region server.");
        test.log(LogStatus.PASS, "Verified table names of a HBase region server.");


    }
}