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

import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_09 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(IM_RES_09.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the UI should display the Usage only the selected group of user")
  public void verifyDataDisplayedAsFilteredUser(String clusterId) {
    test = extent.startTest("IM_RES_09.verifyQueryGraphForUserGroup ("+clusterId+")", "Validate the \"Group By\" filter for user.");
    test.assignCategory(" Cluster/Impala Resources");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    Impala impala = new Impala(driver);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    HomePage homePage = new HomePage(driver);
    DatePicker datePicker = new DatePicker(driver);

    //Select impala tab
    test.log(LogStatus.INFO, "Go to resource page");
    LOGGER.info("Select impala from dropdown");
    impala.selectImpalaResource();

    //Select the cluster
    test.log(LogStatus.INFO, "This testcase is running for cluster: "+clusterId);
    LOGGER.info("Selecting cluster from the list " +clusterId);
    LOGGER.info("Selecting the cluster");
    homePage.selectMultiClusterId(clusterId);
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);



    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
    datePicker.selectThisMonth();
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
    impala.clearFilter();
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

    MouseActions.clickOnElement(driver, impalaPageObject.filterInput);
    for(int i=0; i<impalaPageObject.filterElements.size(); i++) {
      String userName = impalaPageObject.filterElements.get(i).getText();
      impalaPageObject.filterElements.get(i).click();
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Selecting the user: " + userName + " in filter.");
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
      boolean isTagPresent = false;
      for (String graphTag : impala.getQueriesGraphLabels()) {
        if (graphTag.equals(userName)) {
          isTagPresent = true;
        } else if (graphTag.contains("...") &&
          userName.contains(graphTag.split("...")[0])) {
          isTagPresent = true;
        } else {
          isTagPresent = false;
        }
      }
      Assert.assertTrue(isTagPresent, "Filter user not displayed for user: " + userName);
      test.log(LogStatus.PASS, "Graph displayed the user based on filter for user : " + userName);
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
      waitExecuter.sleep(2000);
      impalaPageObject.filterInput.click();
      waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
    }
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
    waitExecuter.sleep(2000);
    impalaPageObject.filterInput.click();
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
  }
}
