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
public class YR_014 extends BaseClass {

    /**
     * Yarn Resource page Filter tab should contain all the entries populated by default
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the filter tab should contain all the entries populated by default")
    public void YR_014_verifyFilterTabAndEntries(String clusterId) {
        test = extent.startTest("YR_014_verifyFilterTabAndEntries: " + clusterId,
                "Filter tab should contain all the entries populated by default.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_014_verifyFilterTabAndEntries");

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

        yarn.clickOnGroupByDropDown();
        yarn.selectApplicationType();
        test.log(LogStatus.INFO, "Selected Application Type, from dropdown.");
        List<String> allFilterElements = yarn.getAllFilterElements();
        System.out.println("All ApplicationType : filter elements: "+allFilterElements);
        Log.info("All ApplicationType : filter elements: "+ allFilterElements);
        test.log(LogStatus.INFO, "All ApplicationType : filter elements: "+ allFilterElements);
        Assert.assertTrue(yarn.verifyFilterElements(), "Application Type: Filter elements MAPREDUCE, SPARK, TEZ is mismatch.");

        yarn.clickOnGroupByDropDown();
        yarn.selectUser();
        test.log(LogStatus.INFO, "Selected User, from dropdown.");
        List<String> allDefaultFilterElementForUser = yarn.getAllDefaultSelectedFilterElements();
        System.out.println("All User: filter default selected elements: "+allDefaultFilterElementForUser);
        Log.info("All User: filter default selected elements: "+allDefaultFilterElementForUser);
        test.log(LogStatus.INFO, "All User: filter default selected elements: "+allDefaultFilterElementForUser);

        yarn.clickOnGroupByDropDown();
        yarn.selectQueue();
        test.log(LogStatus.INFO, "Selected Queue, from dropdown.");
        List<String> allDefaultFilterElementForQueue = yarn.getAllDefaultSelectedFilterElements();
        System.out.println("All Queue: filter default selected elements: "+ allDefaultFilterElementForQueue);
        Log.info("All Queue: filter default selected elements: "+ allDefaultFilterElementForQueue);
        test.log(LogStatus.INFO, "All Queue: filter default selected elements: "+ allDefaultFilterElementForQueue);

        test.log(LogStatus.PASS,"Verified group by element and default filter elements on Yarn Resource Page. ");
        Log.endTestCase("YR_014_verifyFilterTabAndEntries");

    }
}
