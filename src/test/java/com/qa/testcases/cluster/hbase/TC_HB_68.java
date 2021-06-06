package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.hbase.HbaseTablesColumn;
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
public class TC_HB_68 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_68.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the namespace and the table names are adequately captured and displayed in the UI")
    public void verifyPatternForNamespace(String clusterId) {
        test = extent.startTest("TC_HB_68.verifyPatternForNamespace",
                "Verify com.unraveldata.hbase.table.name.pattern for namespace (HBASE-108).");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_68.verifyPatternForNamespace");

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
        //Get all names of table
        List<String> tableNames = hbase.getTableNamesFromTablesTab(HbaseTablesColumn.TABLE_NAME);
        //Compare table names with table name having 'namespace', 'table', 'region'
        for(String tableName : tableNames){
            if(tableName.contains(".")|| tableName.contains("table") || tableName.contains("region")){
                Assert.assertTrue(true,"Table name not having any of the name 'namespace', " +
                        " 'table' and 'region'");
                LOGGER.info("Verified com.unraveldata.hbase.table.name.pattern for namespace (HBASE-108).");
                test.log(LogStatus.PASS, "Verified com.unraveldata.hbase.table.name.pattern for namespace (HBASE-108).");
                break;
            }else{
                Assert.assertTrue(false,"Table name not having any of the name 'namespace', " +
                        " 'table' and 'region'");
            }

        }
    }
}
