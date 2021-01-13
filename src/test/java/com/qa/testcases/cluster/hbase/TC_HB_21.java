package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.clusters.HBasePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_21 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_21.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyToolTipsHBaseClusterMetricsTab(String clusterId) {
        test = extent.startTest("TC_HB_21.verifyToolTipsHBaseClusterMetricsTab",
                "Verify tool tip for all cluster metrics tab.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_21.verifyToolTipsHBaseClusterMetricsTab");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase header");
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        String hBaseClusterName = "HDPab722";
        hbase.selectHBaseCluster(hBaseClusterName);
        Assert.assertTrue(hbase.verifyMetricsToolTips(), "Tooltips on HBase cluster KPI's not found.");
        test.log(LogStatus.PASS, "Verify tool tip presence for HBase cluster metrics tab.");
    }
}
