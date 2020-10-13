package com.qa.testcases.cluster.impala.resources;

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

/**
 * @author Ankur Jaiswal
 */
public class IM_RES_09 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyDataDisplayedAsFilteredUser(String clusterId) {
    test = extent.startTest("IM_RES_09.verifyQueryGraphForUserGroup ("+clusterId+")", "Validate the \"Group By\" filter for user.");
    test.assignCategory("4620 - Cluster/Impala Resources");
    WaitExecuter executer = new WaitExecuter(driver);

    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

    // Click on Chargeback tab
    executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
    JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
    executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);
    executer.waitUntilPageFullyLoaded();

    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    executer.sleep(1000);
    datePicker.selectThisMonth();
    executer.waitUntilPageFullyLoaded();

    Impala impala = new Impala(driver);
    impala.clearFilter();
    executer.waitUntilPageFullyLoaded();

    MouseActions.clickOnElement(driver, impalaPageObject.filterInput);
    for(int i=0; i<impalaPageObject.filterElements.size(); i++) {
      String userName = impalaPageObject.filterElements.get(i).getText();
      impalaPageObject.filterElements.get(i).click();
      executer.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Selecting the user: " + userName + " in filter.");
      executer.sleep(2000);
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
      impalaPageObject.filterInput.click();
    }
    impalaPageObject.filterInput.click();
  }
}
