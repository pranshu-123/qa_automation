package com.qa.testcases.cluster.yarn.resources;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class YR_011 extends BaseClass {

    /**
     * Select the application Type in the dropdown (group by)
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YR_011_verifyYarnResourcePageAndSelectApplicationType(String clusterId) {
        test = extent.startTest("YR_011_verifyYarnResourcePageAndSelectApplicationType: " + clusterId,
                "Select the application Type in the dropdown (group by)");
        test.assignCategory("4620 Cluster - Yarn Resources");
        Log.startTestCase("YR_011_verifyYarnResourcePageAndSelectApplicationType");

        Yarn yarn = new Yarn(driver);
        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        Log.info("ClusterId is selected: "+clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        Log.info("DatePicker is selected for last30 Days");

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
