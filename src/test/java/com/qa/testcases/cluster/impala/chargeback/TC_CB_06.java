package com.qa.testcases.cluster.impala.chargeback;

import com.google.gson.JsonObject;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.DateUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.NetworkManager;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_06 extends BaseClass {

  /**
   * Verify the user can select custom date ranges
   */
  @Test(dataProvider = "clusterid-data-provider")
  public void TC_CB_06_VerifyCustomDateRange(String clusterId) {
    test = extent.startTest("TC_CB_06_VerifyCustomDateRange: "+clusterId, "Verify the user can select custom date ranges");
    test.assignCategory("4620 Cluster - Impala Chargeback");

    WaitExecuter waitExecuter = new WaitExecuter(driver);
    ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
    chargeBackImpala.selectImpalaChargeback();

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);

    CommonPageObject commonPageObject = new CommonPageObject(driver);
    commonPageObject.clusterDropdown.click();
    waitExecuter.sleep(2000);

    long requestTime = System.currentTimeMillis();
    waitExecuter.sleep(2000);
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    datePicker.selectCustomRange();
    datePicker.setStartDate(DateUtils.getFirstDateOfYear());
    datePicker.setEndDate(DateUtils.getCurrentDate());
    datePicker.clickOnCustomDateApplyBtn();
    MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);

    List<JsonObject> networkAPICalls = NetworkManager.getLogBasedForURLMatch(driver, "/api/v1/search/");
    for (JsonObject networkAPI : networkAPICalls) {
      if (NetworkManager.isNewRequest(networkAPI, requestTime)) {
        String actualStartDate = NetworkManager.getParameterValueFrom(networkAPI,"gte");
        String actualEndDate = NetworkManager.getParameterValueFrom(networkAPI,"lte");

        test.log(LogStatus.INFO, "verifying cluster info in network request: "
          + networkAPI.get("message").getAsJsonObject().get("params")
          .getAsJsonObject().get("request").getAsJsonObject()
          .get("url"));

        Assert.assertEquals(DateUtils.convertISOToUSDateFormat(actualStartDate), DateUtils.getFirstDateOfYear());
        Assert.assertEquals(DateUtils.convertISOToUSDateFormat(actualEndDate), DateUtils.getCurrentDate());
      }
    }
    test.log(LogStatus.PASS, "Data is displayed for selected date range for cluster: " + clusterId);
  }
}
