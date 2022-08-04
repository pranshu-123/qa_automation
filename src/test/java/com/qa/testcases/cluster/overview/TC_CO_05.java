package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.HomePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Birender Kumar
 */
@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_05 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the cluster overview report for the Custom Range")
    public void TC_CO_05_VerifyCluster0verviewReportCustomRange(String clusterId) {
        test = extent.startTest("TC_CO_05_VerifyCluster0verviewReportCustomRange: "+clusterId, "Verify Cluster 0verview report for Custom Range.");
        test.assignCategory(" Cluster Overview");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        HomePageObject homePageObject = new HomePageObject(driver);


        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        // Click on custom range
        datePicker.selectCustomRange();
        datePicker.datePickerPageObject.customRangeStartDate.sendKeys("08/07/2020");
        datePicker.datePickerPageObject.customRangeEndDate.sendKeys("08/08/2020");
        datePicker.clickOnCustomDateApplyBtn();
        test.log(LogStatus.PASS, "Successfully clicked on custom date range Apply button.");

        //Check kpi displays
        Assert.assertTrue(homePageObject.kpiNodes.isDisplayed(), "Kpi Nodes not displayed on overview page.");
        Assert.assertTrue(homePageObject.kpiVcores.isDisplayed(), "Kpi Vcores not displayed on overview page.");
        Assert.assertTrue(homePageObject.kpiMemory.isDisplayed(), "Kpi Memory not displayed on overview page.");

        test.log(LogStatus.PASS, "The Nodes, Vcores, Memory kpi verified on clusters/overview page.");
    }
}
