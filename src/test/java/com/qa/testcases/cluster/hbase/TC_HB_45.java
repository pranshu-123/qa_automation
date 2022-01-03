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
public class TC_HB_45 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_45.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify alerts for the regional server on the UI.")
    public void verifyAlertsForRegionSvrUI(String clusterId) {
        test = extent.startTest("TC_HB_45.verifyAlertsForRegionSvrUI",
                "Verify alerts for region server in UI.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_45.verifyAlertsForRegionSvrUI");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);
        UserActions userActions = new UserActions(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        userActions.performActionWithPolling(hBasePageObject.hbaseTab, UserAction.CLICK);
        LOGGER.info("Clicked on HBase Tab");
        LOGGER.info("HBase cluster using : "+ clusterId);
        hbase.selectCluster(clusterId);
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());
        test.log(LogStatus.INFO, "Verified HBase cluster setup with :"+ clusterId);

        //verify and click on region server tab
        hbase.verifyRegionServer();
        hbase.verifyTablesTabElements();
        userActions.performActionWithPolling(hBasePageObject.sortByTableName, UserAction.CLICK);
        userActions.performActionWithPolling(hBasePageObject.sortUp, UserAction.CLICK);
        userActions.performActionWithPolling( hBasePageObject.getTableName, UserAction.CLICK);
        //verify alerts in region server
        hbase.verifyAlertsInRegionServerHealth();
        LOGGER.info("Verified alerts for region server in UI.");
        test.log(LogStatus.PASS, "Verified alerts for region server in UI.");

    }
}
