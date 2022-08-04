package com.qa.testcases.cluster.overview;

import com.google.gson.JsonObject;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.HomePage;
import com.qa.utils.NetworkManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_07 extends BaseClass {

  @Test(description="P0-Verify that the cluster overview report has to be generated for the selected group.")
  public void TC_CO_07_verifyClusterOverviewForDifferentCluster() {
    test = extent.startTest("TC_CO_07_verifyClusterOverviewForDifferentCluster", "Verify Cluster Overview report for different clusters");
    HomePage homePage = new HomePage(driver);

    long requestTime = 0l;
    List<WebElement> clusterList = homePage.getClusterListFromDropdown();
    for (int i = 0; i < clusterList.size(); i++) {
      if (i != 0) {
        requestTime = System.currentTimeMillis();
      }
      homePage.selectMultiClusterId(clusterList.get(i).getText());
      String clusterId = clusterList.get(i).getAttribute("value");
      List<JsonObject> networkAPICalls = NetworkManager.getLogBasedForURLMatch(driver, "/api/v1/clusters/");
      for (JsonObject networkAPI : networkAPICalls) {
        if (NetworkManager.isNewRequest(networkAPI, requestTime)) {
          String actualCluster = NetworkManager.getParameterValueFrom(networkAPI, "clusterName");
          String requestUrl = networkAPI.get("message").getAsJsonObject().get("params")
                  .getAsJsonObject().get("request").getAsJsonObject()
                  .get("url").getAsString();
          test.log(LogStatus.INFO, "verifying cluster info in network request: "
                  + networkAPI.get("message").getAsJsonObject().get("params")
                  .getAsJsonObject().get("request").getAsJsonObject()
                  .get("url"));
          if (actualCluster != null && actualCluster.trim() != "") {
            Assert.assertEquals(clusterId, actualCluster, "Incorrect clusterId is requests in KPI requests.");
          } else {
            Assert.assertTrue(requestUrl.contains(clusterId), "No or Incorrect clusterId is displayed in request.");
          }
        }
      }
      test.log(LogStatus.PASS, "Successfully verified the cluster details in network tab for cluster: " + clusterId);
    }
    homePage.clickOnClusterDropDown();
  }
}
