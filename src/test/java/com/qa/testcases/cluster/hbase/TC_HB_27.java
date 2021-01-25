package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.DatePicker;
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
public class TC_HB_27 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_27.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyMetricsAndChartsFromHBaseTab(String clusterId) {
        test = extent.startTest("TC_HB_27.verifyMetricsAndChartsFromHBaseTab",
                "Verify Region Server metrics, Region Server Charts, Table metrics.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_27.verifyMetricsAndChartsFromHBaseTab");

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

        hbase.verifyRegionMetricsChartsAndTables();
        LOGGER.info("HBase Region Metrics Charts And Tables found.");
        test.log(LogStatus.PASS, "Verified metrics, region server and tables on HBase.");


    }
}

