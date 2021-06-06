package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.io.ConfigReader;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

/**
 * @author Birender Kumar
 */

@Marker.ClusterOverview
@Marker.All
public class TC_CO_01 extends BaseClass{

    @Test(dataProvider = "clusterid-data-provider",description="")
    public void TC_CO_01_VerifyUIConnectivity(String clusterId) {
        test = extent.startTest("TC_CO_01_VerifyUIConnectivity: "+clusterId, "Verify Default date Range is Last 1 Hour.");
        test.assignCategory(" Cluster Overview");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        Properties prop = ConfigReader.readBaseConfig();
        String url = prop.getProperty("url");
        String expectedUrl = url+"/#/clusters/overview";
        Assert.assertEquals(homePage.getHomePageUrl(),expectedUrl);
        test.log(LogStatus.PASS, "The clusters/overview page is successfully verified.");
        //Check kpi displays
        homePage.kpiDisplays();
        test.log(LogStatus.PASS, "The Nodes, Vcores, Memory kpi verified on clusters/overview page.");

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        System.out.println(datePicker.getDefaultDate());
        Assert.assertEquals(datePicker.getDefaultDate().trim(), "Last 1 Hour","Default date not match.");
        test.log(LogStatus.PASS, "The default date Last 1 Hour verified on clusters/overview page.");

    }
}
