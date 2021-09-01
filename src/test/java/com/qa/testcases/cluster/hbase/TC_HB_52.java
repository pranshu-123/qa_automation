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
public class TC_HB_52 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_52.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the user can write the request count metrics data and graph.")
    public void verifyTablesWriteRequestCountMetrics(String clusterId) {
        test = extent.startTest("TC_HB_52.verifyTablesWriteRequestCountMetrics",
                "Verify writeRequestCount metrics data and graph.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_52.verifyTablesWriteRequestCountMetrics");

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

        //Verify Hbase Tables tab elements tables
        hbase.verifyTablesTabElements();

        //Verify Hbase Tables graph metric
        String expectedHBaseGraphMetrics = "Total Write Request Count";
        String hbaseGraph = "hbaseGraph1";
        hbase.verifyHBaseKPIGraphs(hBasePageObject,expectedHBaseGraphMetrics, hbaseGraph);

        LOGGER.info("HBase Tables writeRequestCount metrics data and graph charts found.");
        test.log(LogStatus.PASS, "Verified writeRequestCount metrics data and graph.");



    }

}

