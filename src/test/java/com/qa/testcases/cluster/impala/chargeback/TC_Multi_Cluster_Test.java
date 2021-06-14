package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.utils.WaitExecuter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.scripts.HomePage;
import com.relevantcodes.extentreports.LogStatus;


public class TC_Multi_Cluster_Test extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the Multi Cluster test using Data Provider")
    public void verifyTC_Multi_Cluster_Test(String clusterId) {
        test = extent.startTest("TC_Multi_Cluster_Test", "Validate the Multi Cluster test using Data Provider.");
        //test.assignCategory(" Cluster - Impala Chargeback - Multi-Cluster");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        System.out.println("Passed Parameter Is : " + clusterId);
        //Check kpi displays
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        homePage.kpiDisplays();
        test.log(LogStatus.PASS, "The Nodes, Vcores, Memory kpi verified on clusters/overview page.");

    }

}
