package com.qa.testcases.cluster.yarn.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.YarnResources
@Marker.All
public class YR_012 extends BaseClass {

    /**
     * Yarn Resource page should Shown Only yarn applications
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the yarn resource page should be shown only yarn applications")
    public void YR_012_verifyYarnResourcePageShowOnlyYarnApp(String clusterId) {
        test = extent.startTest("YR_012_verifyYarnResourcePageShowOnlyYarnApp: " + clusterId,
                "Yarn Resource page should Shown Only yarn applications.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_012_verifyYarnResourcePageShowOnlyYarnApp");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Yarn yarn = new Yarn(driver);
        Impala impala = new Impala(driver);

        waitExecuter.sleep(2000);

        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");
        test.log(LogStatus.INFO, "Yarn Resource Header is displayed.");

        waitExecuter.sleep(2000);
        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);
        //Select cluster id
        yarn.selectResourceType("Yarn");
        waitExecuter.sleep(2000);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        Log.info("DatePicker is selected for last30 Days");
        test.log(LogStatus.INFO, "Date is selected from DatePicker");

        yarn.clickOnGroupByDropDown();
        Log.info("Click on GroupByDropDown.");
        yarn.selectApplicationType();
        Log.info("Selected Application Type, from dropdown.");
        test.log(LogStatus.INFO, "Selected Application Type, from dropdown.");

        Log.info("Verify Filter elements.");
        Assert.assertTrue(yarn.verifyFilterElements(),"Filter Elements do not have Yarn applications.");
        test.log(LogStatus.INFO,"Verified Filter Elements on Yarn Resource Page. ");
        Log.endTestCase("YR_012_verifyYarnResourcePageShowOnlyYarnApp");

    }
}
