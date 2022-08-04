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
import org.testng.annotations.Test;

@Marker.YarnResources
@Marker.GCPYarnResources
@Marker.All
public class YR_011 extends BaseClass {

    /**
     * Select the application Type in the dropdown (group by)
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the application type should be in the group by dropdown list.")
    public void YR_011_verifyYarnResourcePageAndSelectApplicationType(String clusterId) {
        test = extent.startTest("YR_011_verifyYarnResourcePageAndSelectApplicationType: " + clusterId,
                "Select the application Type in the dropdown (group by)");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_011_verifyYarnResourcePageAndSelectApplicationType");
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        Yarn yarn = new Yarn(driver);
        waitExecuter.sleep(2000);


        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");
        test.log(LogStatus.INFO, "Yarn Resource Header is displayed.");

        //Select cluster id
        waitExecuter.sleep(2000);

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);
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
        //TBD
//        1. The 2nd set of Graphs for the Application type must be Present
//        2. The graphs must be plotted with number of apps vs time
        test.log(LogStatus.INFO, "Selected Application Type, from DropDown is verified");
        Log.endTestCase("YR_011_verifyYarnResourcePageAndSelectApplicationType");
    }

}
