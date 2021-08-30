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

import java.util.List;
import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_19 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_19.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify all the cluster names should be available in the drop-down.")
    public void verifyHBaseAllClusters(String clusterId) {
        test = extent.startTest("TC_HB_19.verifyHBaseAllClusters",
                "Verify all the cluster names are available in drop down.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_19.verifyHBaseAllClusters");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver,hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase header");
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: "+ hbase.getHBaseHeader());

        List<String> hBaseClusters = hbase.getAllHBaseClusters();
        LOGGER.info("HBase clusters found are: "+ hBaseClusters);
        Assert.assertFalse(hBaseClusters.isEmpty(), "HBase clusters not available");
        test.log(LogStatus.PASS, "Verified All cluster names from the drop down.");

    }
}
