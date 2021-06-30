package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_03 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the Memory and Query graphs in sync while selecting the same date range")
  public void validateDateRangeOfBothGraph(String clusterId) {
    test = extent.startTest("IM_RES_03.validateDateRangeOfBothGraph (" + clusterId + ")",
      "Verify that the Memory and Query graphs in sync (they display the same date range and granularity)");
    test.assignCategory(" Cluster/Impala Resources");

    WaitExecuter executer = new WaitExecuter(driver);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    Impala impala = new Impala(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

    // Click on Resources tab and select Impala
    impala.selectImpalaResource("Impala");

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);
    executer.waitUntilPageFullyLoaded();

    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    executer.sleep(1000);
    datePicker.selectThisMonth();
    executer.waitUntilPageFullyLoaded();

    List<String> memoryDateLabels = impala.getGraphDateLabel("memory");
    List<String> queriesDateLabels = impala.getGraphDateLabel("queries");
    Assert.assertFalse(memoryDateLabels.isEmpty() || queriesDateLabels.isEmpty(),
      "Either memory or queries graph does not displayed date labels");
    Assert.assertEquals(memoryDateLabels, queriesDateLabels, "Date is not matching between memory and queries graph");
    test.log(LogStatus.PASS, "Date is matching between memory and queries graph");
  }
}
