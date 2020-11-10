package com.qa.testcases.cluster.yarn.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
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
    @Test(dataProvider = "clusterid-data-provider")
    public void YR_012_verifyYarnResourcePageShowOnlyYarnApp(String clusterId) {
        test = extent.startTest("YR_012_verifyYarnResourcePageShowOnlyYarnApp: " + clusterId,
                "Yarn Resource page should Shown Only yarn applications.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_012_verifyYarnResourcePageShowOnlyYarnApp");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Yarn yarn = new Yarn(driver);
        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");
        test.log(LogStatus.INFO, "Yarn Resource Header is displayed.");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        Log.info("ClusterId is selected: " + clusterId);
        test.log(LogStatus.INFO, "ClusterId is selected: " + clusterId);

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
