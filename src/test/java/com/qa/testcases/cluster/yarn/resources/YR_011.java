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
        yarn.selectImpalaType();
        waitExecuter.sleep(2000);


        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();
        Log.info("ClusterId is selected: "+clusterId);
        test.log(LogStatus.INFO, "Cluster Id selected"+clusterId);



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
