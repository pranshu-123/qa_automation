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
public class TC_HB_39 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_39.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify percent Files Local metrics and graphs.")
    public void verifyPercentFilesLocalAndGraph (String clusterId) {
        test = extent.startTest("TC_HB_39.verifyPercentFilesLocalAndGraph",
                "Verify percentFilesLocal metrics and graph.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_39.verifyPercentFilesLocalAndGraph");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        //verify tab for metrics, region server and tables
        hbase.verifyRegionMetricsChartsAndTables();
        LOGGER.info("HBase Region Server Metrics And Tables found.");

        //Click on 'Region Server' and verify hbase region server tab
        hbase.verifyRegionServer();

        //Verify Hbase cluster graph metric
        String expectedHBaseGraphMetrics = "Total Percent Files Local";
        String hbaseGraph = "hbaseGraph0";
        hbase.verifyHBaseKPIGraphs(hBasePageObject,expectedHBaseGraphMetrics, hbaseGraph);

        LOGGER.info("HBase percentFilesLocal metrics and graph found.");
        test.log(LogStatus.PASS, "Verified region server percentFilesLocal metrics and graph on HBase.");


    }
}


