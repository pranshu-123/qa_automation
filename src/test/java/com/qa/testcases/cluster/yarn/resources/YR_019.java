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

import java.util.List;

@Marker.YarnResources
@Marker.All
public class YR_019 extends BaseClass {

    /**
     * Verify 2 sets of graphs are shown In UI with Vcores and Memory
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the two sets of charts should be displayed inside the vcore and memory UI")
    public void YR_019_verifyVcoresAndMemoryGraph(String clusterId) {
        test = extent.startTest("YR_019_verifyVcoresAndMemoryGraph: " + clusterId,
                "Verify 2 sets of graphs are shown In UI with Vcores and Memory.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_019_verifyVcoresAndMemoryGraph");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Yarn yarn = new Yarn(driver);
        Impala impala = new Impala(driver);
        impala.selectImpalaResource("Impala");
        waitExecuter.sleep(2000);

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

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(yarn.isVCoresGraphPresent(), "The VCores graph is not present with expected conditions");
        // Validate of Query graph is present for selected date range
        Assert.assertTrue(yarn.isMemoryGraphPresent(), "The Memory graph is not present with expected conditions");

        Log.info("Verifying VCores and query graph for last 30 days");
        test.log(LogStatus.PASS, "Vcores and Memory graph is displayed");

        yarn.clickOnGroupByDropDown();
        yarn.selectUser();
        test.log(LogStatus.INFO, "Selected User, from dropdown.");
        List<String> allDefaultFilterElementForUser = yarn.getAllDefaultSelectedFilterElements();
        System.out.println("All User: filter default selected elements: "+allDefaultFilterElementForUser);
        Log.info("All User: filter default selected elements: "+allDefaultFilterElementForUser);
        test.log(LogStatus.INFO, "All User: filter default selected elements: "+allDefaultFilterElementForUser);

        yarn.isVCoresApplicationGraphPresent(allDefaultFilterElementForUser);
        test.log(LogStatus.INFO, "Validated all User from filter as well from vcores application graph.");

        yarn.isMemoryApplicationGraphPresent(allDefaultFilterElementForUser);
        test.log(LogStatus.INFO, "Validated all User from filter as well from memory application graph.");

        test.log(LogStatus.PASS, "Validated all User from filter from vcore and memory application graph.");
    }
}
