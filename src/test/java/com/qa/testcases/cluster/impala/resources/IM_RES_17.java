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

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class  IM_RES_17 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyDataDisplayedAsFilteredQueue(String clusterId) {
    test = extent.startTest("IM_RES_17.verifyDataDisplayedAsFilteredQueue (" + clusterId + ")", "Validate the \"Group By\" filter for Queue.");
    test.assignCategory(" Cluster/Impala Resources");

    WaitExecuter executer = new WaitExecuter(driver);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

    // Click on Impala tab
    executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
    JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
    executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);
    executer.sleep(5000);
    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);
    executer.waitUntilPageFullyLoaded();
    try {
      DatePicker datePicker = new DatePicker(driver);
      datePicker.clickOnDatePicker();
      executer.sleep(1000);
      datePicker.selectThisMonth();
      executer.waitUntilPageFullyLoaded();

      executer.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
      executer.sleep(3000);
      impalaPageObject.groupByDropdownButton.click();
      impalaPageObject.groupByQueueList.click();
      executer.waitUntilPageFullyLoaded();

      Impala impala = new Impala(driver);
      impala.clearFilter();
      executer.waitUntilPageFullyLoaded();

      for (int i = 0; i < impalaPageObject.filterElements.size(); i++) {
        String queueName = impalaPageObject.filterElements.get(i).getText();
        impalaPageObject.filterElements.get(i).click();
        executer.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Selecting the queue: " + queueName + " in filter.");
        executer.sleep(2000);
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
        impalaPageObject.filterInput.click();
      }
      impalaPageObject.filterInput.click();
    }
    catch (org.openqa.selenium.StaleElementReferenceException ex)
    {
      test.log(LogStatus.INFO, "Selecting the queue: " + ex + " in filter.");
    }
  }
}