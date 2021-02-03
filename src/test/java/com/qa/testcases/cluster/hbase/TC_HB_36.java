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
public class TC_HB_36 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_36.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyReadRequestCountAndGraph (String clusterId) {
        test = extent.startTest("TC_HB_36.verifyReadRequestCountAndGraph",
                        "Verify readRequestCount metrics and graph.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_36.verifyReadRequestCountAndGraph");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        //verify tab for metrics, region server and tables
        hbase.verifyRegionMetricsChartsAndTables();
        LOGGER.info("HBase Region Server Metrics And Tables found.");

        //Verify hbase region server tab
        hbase.verifyRegionServer();

        //Verify Hbase cluster graph metric
        String expectedHBaseGraphMetrics = "Total Read Request Count";
        String hbaseGraph = "hbaseGraph0";
        hbase.verifyHBaseKPIGraphs(hBasePageObject,expectedHBaseGraphMetrics, hbaseGraph);

        LOGGER.info("HBase Region Server readRequestCount metrics and graph charts found.");
        test.log(LogStatus.PASS, "Verified region server readRequestCount metrics, graph charts and on HBase.");


    }
}

