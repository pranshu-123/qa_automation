package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.clusters.HBasePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_71  extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_71.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description = "P0-Verify Multi cluster setup with one CDH cluster.")
    public void verifyHBaseMultiClusterWithCDH(String clusterId) {
        test = extent.startTest("TC_HB_71.verifyHBaseMultiClusterWithCDH",
                "Verify Multicluster setup with one CDH cluster.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_71.verifyHBaseMultiClusterWithCDH");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);
        UserActions userActions = new UserActions(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        List<String> hBaseClusters = hbase.getAllHBaseClusters();
        LOGGER.info("HBase clusters found are: " + hBaseClusters);
        Assert.assertFalse(hBaseClusters.isEmpty(), "HBase clusters not available");

        for (String clusterName : hBaseClusters) {
            if (clusterName.contains("HDP")) {
                LOGGER.info("Verified Multicluster setup with one CDH cluster." + clusterName);
                waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
                userActions.performActionWithPolling(hBasePageObject.hBaseClusterDropDown, UserAction.CLICK);
                hbase.verifyClusterList(hBaseClusters);
                LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());
                test.log(LogStatus.PASS, "Verified Multicluster setup with one CDH cluster.");
            } else if (clusterName.contains("CDH")) {
                LOGGER.info("Verified Multicluster setup with one CDH cluster." + clusterName);
                waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
                userActions.performActionWithPolling(hBasePageObject.hBaseClusterDropDown, UserAction.CLICK);
                hbase.verifyClusterList(hBaseClusters);
                LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());
                test.log(LogStatus.PASS, "Verified Multicluster setup with one CDH cluster.");
            } else {
                Assert.assertFalse(false, "Multicluster setup with CDH cluster not found.");
            }
        }
    }
}
