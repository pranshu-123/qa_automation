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

public class TC_HB_56 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_56.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that all metrics and graphics are in the Region tab.")
    public void verifyMetricsAndGraphRegionTab(String clusterId) {
        test = extent.startTest("TC_HB_56.verifyMetricsAndGraphRegionTab",
                "Verify All metrics and graphs under region tab.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_56.verifyMetricsAndGraphRegionTab");

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

        //verify Hbase Tables tab elements tables
        hbase.verifyTablesTabElements();
        //verify Table and Region
        String tableName = hbase.verifyTableAndRegion();

        //verify all the table column
        hbase.verifyRegionTabColumns(tableName);
        LOGGER.info("Verified All metrics and graphs under region tab.");
        test.log(LogStatus.PASS, "Verified All metrics and graphs under region tab.");
    }
}