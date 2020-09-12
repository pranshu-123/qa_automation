package com.qa.testcases.cluster.yarn.resources;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YR_014 extends BaseClass {

    /**
     * Yarn Resource page Filter tab should contain all the entries populated by default
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YR_014_verifyFilterTabAndEntries(String clusterId) {
        test = extent.startTest("YR_014_verifyFilterTabAndEntries: " + clusterId,
                "Filter tab should contain all the entries populated by default.");
        test.assignCategory("4620 Cluster - Yarn Resources");
        Log.startTestCase("YR_014_verifyFilterTabAndEntries");

        Yarn yarn = new Yarn(driver);
        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        Log.info("ClusterId is selected: " + clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        Log.info("DatePicker is selected for last30 Days");

        yarn.clickOnGroupByDropDown();
        yarn.selectApplicationType();
        System.out.print("All ApplicationType : filter elements: "+yarn.getAllFilterElements());
        Log.info("All ApplicationType : filter elements: "+yarn.getAllFilterElements());
        Assert.assertTrue(yarn.verifyFilterElements(), "Application Type: Filter elements MAPREDUCE, SPARK, TEZ is mismatch.");

        yarn.clickOnGroupByDropDown();
        yarn.selectUser();
        System.out.print("All User: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All User: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());

        yarn.clickOnGroupByDropDown();
        yarn.selectQueue();
        System.out.print("All Queue: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All Queue: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());

        yarn.clickOnGroupByDropDown();
        yarn.selectProject();
        System.out.print("All Project: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All Project: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());

        yarn.clickOnGroupByDropDown();
        yarn.selectDept();
        System.out.print("All Dept: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All Dept: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());

        yarn.clickOnGroupByDropDown();
        yarn.selectRealUser();
        System.out.print("All RealUser: filter elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All RealUser: filter elements: "+yarn.getAllDefaultSelectedFilterElements());

        yarn.clickOnGroupByDropDown();
        yarn.selectDbs();
        System.out.print("All Dbs: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All Dbs: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());

        yarn.clickOnGroupByDropDown();
        yarn.selectInputTables();
        System.out.print("All inputTables: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());
        Log.info("All inputTables: filter default selected elements: "+yarn.getAllDefaultSelectedFilterElements());

        test.log(LogStatus.INFO,"Verified group by element and default filter elements on Yarn Resource Page. ");
        Log.endTestCase("YR_014_verifyFilterTabAndEntries");

    }
}
