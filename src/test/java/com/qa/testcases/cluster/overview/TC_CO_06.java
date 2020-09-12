package com.qa.testcases.cluster.overview;

import com.google.gson.JsonObject;
import com.qa.base.BaseClass;
import com.qa.parameters.Parameter;
import com.qa.scripts.HomePage;
import com.qa.utils.NetworkManager;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TC_CO_06 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_06_VerifyclusterfilterinUI(String clusterId) {
        test = extent.startTest("TC_CO_06_VerifyclusterfilterinUI"+clusterId, "Verify cluster filter in UI ");
        test.assignCategory("4620 - Cluster Overview");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        homePage.clickOnClusterDropDown();
        List<WebElement> clusterList = homePage.getClusterListFromDropdown();
        homePage.clickOnClusterDropDown();

        for (int i = 0; i < clusterList.size(); i++) {
            String cluster = clusterList.get(i).getAttribute("value");
            List<JsonObject> networkAPICalls = NetworkManager.getLogBasedForURLMatch(driver, "/api/v1/clusters/");
            for (JsonObject networkAPI : networkAPICalls) {
                String actualCluster = NetworkManager.getParameterValueFrom(networkAPI, "clusterName");
                Assert.assertEquals(cluster,actualCluster, "Incorrect clusterId is requests in KPI requests.");
            }
            test.log(LogStatus.PASS, "Successfully verified clusterId is requests in KPI requests");

        }

    }

}
