package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class  IM_RES_17 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(IM_RES_17.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyDataDisplayedAsFilteredQueue(String clusterId) {
    test = extent.startTest("IM_RES_17.verifyDataDisplayedAsFilteredQueue (" + clusterId + ")", "Validate the \"Group By\" filter for Queue.");
    test.assignCategory(" Cluster/Impala Resources");

    WaitExecuter waitExecuter = new WaitExecuter(driver);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    Impala impala = new Impala(driver);

    //Select impala tab
    test.log(LogStatus.INFO, "Go to resource page");
    LOGGER.info("Select impala from dropdown");
    impala.selectImpalaResource();
    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);

    try {
      DatePicker datePicker = new DatePicker(driver);
      datePicker.clickOnDatePicker();
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
      datePicker.selectThisMonth();
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

      waitExecuter.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
      impalaPageObject.groupByDropdownButton.click();
      impalaPageObject.groupByQueueList.click();
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

      impala.clearFilter();
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

      for (int i = 0; i < impalaPageObject.filterElements.size(); i++) {
        String queueName = impalaPageObject.filterElements.get(i).getText();
        impalaPageObject.filterElements.get(i).click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Selecting the queue: " + queueName + " in filter.");
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        boolean isTagPresent = false;
        for (String graphTag : impala.getQueriesGraphLabels()) {
          if (graphTag.equals(queueName)) {
            isTagPresent = true;
          } else if (graphTag.contains("...") &&
                  queueName.contains(graphTag.split("...")[0])) {
            isTagPresent = true;
          } else {
            isTagPresent = false;
          }
        }
        Assert.assertTrue(isTagPresent, "Filter user not displayed for queue: " + queueName);
        test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + queueName);
        waitExecuter.sleep(2000);
        impalaPageObject.filterInput.click();

      }
      waitExecuter.sleep(2000);
      impalaPageObject.filterInput.click();
    }
    catch (org.openqa.selenium.StaleElementReferenceException ex)
    {
      test.log(LogStatus.INFO, "Selecting the queue: " + ex + " in filter.");
    }
  }
}