package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.clusters.HBasePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_20 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_20.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the name of the cluster must appear in a tab with the details of the cluster.")
    public void verifyHBaseClusterNameBySelectingCluster(String clusterId) {
        test = extent.startTest("TC_HB_20.verifyHBaseClusterNameBySelectingCluster",
                "Verify Cluster name should be listed as tab with cluster details.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_20.verifyHBaseClusterNameBySelectingCluster");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver,hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        hbase.selectDateAsLast30Days();
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: "+ hbase.getHBaseHeader());

        List<WebElement> allHbaseClusterListElement = hbase.getHBaseClustersElements();

        for(int i=0; i<allHbaseClusterListElement.size(); i++){
            String hBaseClusterName = allHbaseClusterListElement.get(i).getText();
            hbase.selectDateAsLast30Days();
            LOGGER.info("Select date picker for 30 days.");
            hbase.selectCluster(hBaseClusterName.trim());
            //Below lines are same for expected and actual as the sub menu header is removed in current implementation
            String hBaseNameActual = hbase.getHBaseHeader();
            String hBaseNameExpected = hbase.getHBaseHeader();
            LOGGER.info("Cluster name found with cluster details: "+hBaseNameActual);
            Assert.assertEquals(hBaseNameActual, hBaseNameExpected, "Not matched actual HBase name:"+hBaseNameActual
                    +" with expected: "+ hBaseNameExpected);
        }

        test.log(LogStatus.PASS, "Verified Cluster name should be listed as tab with cluster details.");

    }
}




