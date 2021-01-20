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

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_25 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_25.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
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
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hBase.getHBaseHeader());
        LOGGER.info("HBase Cluster Metrics headers found: " +hBase.verifyHBaseClusterMetricsTitle());
        test.log(LogStatus.INFO, "HBase Cluster Metrics headers found: " +hBase.verifyHBaseClusterMetricsTitle());

        String[] expectedHBaseKPIs = {"Live Region Servers","Dead Region Servers", "Cluster Requests",
                "Average Load", "RIT Count", "RIT Over Threshold","RIT Oldest Age"};
        hBase.verifyHbaseClusterKPIs(hBasePageObject, expectedHBaseKPIs);

        test.log(LogStatus.PASS, "Verified all KPIs information with name and values successfully");
    }
}

