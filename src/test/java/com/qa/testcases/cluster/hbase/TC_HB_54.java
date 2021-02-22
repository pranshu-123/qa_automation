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
public class TC_HB_54 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_54.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyTableAndRegionTab(String clusterId) {
        test = extent.startTest("TC_HB_54.verifyTableAndRegionTab",
                "Table and region tab should be on table details page.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_54.verifyTableAndRegionTab");

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
        hbase.verifyTableAndRegion();
        LOGGER.info("Verified Table and region on table details page.");
        test.log(LogStatus.PASS, "Verified Table and region on table details page.");


    }
}