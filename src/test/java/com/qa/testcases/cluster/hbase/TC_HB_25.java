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
public class TC_HB_25 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_25.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the user can view the HBase cluster metrics.")
    public void verifyHBaseClusterKPIs(String clusterId) {
        test = extent.startTest("TC_HB_25.verifyHBaseClusterKPIs",
                "Verify the HBase cluster metrics.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_25.verifyHBaseClusterKPIs");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hBase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hBase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hBase.getHBaseHeader());
        LOGGER.info("HBase Cluster Metrics headers found: " +hBase.verifyHBaseClusterMetricsTitle());
        test.log(LogStatus.INFO, "HBase Cluster Metrics headers found: " +hBase.verifyHBaseClusterMetricsTitle());

        //Verify all Hbase cluster KPIs
        String[] expectedHBaseKPIs = {"Live Region Servers","Dead Region Servers", "Cluster Requests",
                "Average Load", "RIT Count", "RIT Over Threshold","RIT Oldest Age"};
        hBase.verifyHbaseClusterKPIs(hBasePageObject, expectedHBaseKPIs);

        //Verify all Hbase cluster graph metric
        String[] expectedHBaseGraphMetrics = {"Total Read Request Count","Total Write Request Count","Total Store File Size",
        "Total Percent Files Local"};
        String[] hbaseGraph = {"hbaseGraph0","hbaseGraph1","hbaseGraph2","hbaseGraph3"};
        for(int i=0 ; i<4; i++){
            hBase.verifyHBaseKPIGraphs(hBasePageObject,expectedHBaseGraphMetrics[i], hbaseGraph[i]);
        }

        test.log(LogStatus.PASS, "Verified all KPIs information with name and values successfully");
    }
}

