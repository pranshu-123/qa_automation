package com.qa.testcases.cluster.impala.resources;

import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class IM_RES_20 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateUserCheckbox(String clusterId) {
    test = extent.startTest("IM_RES_20.validateUserCheckbox (" + clusterId + ")", "Validate the \"Group By\" filter for Queue.");
    test.assignCategory("4620 - Cluster/Impala Resources");

    WaitExecuter executer = new WaitExecuter(driver);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

    // Click on Impala tab
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
    JavaScriptExecuter.scrollViewWithYAxis(driver,-100);

    int scrollY = 150;
    JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);

    Impala impala = new Impala(driver);
    String user = impala.getQueriesGraphLabels().get(0);
    test.log(LogStatus.INFO, "Deselecting the user: " + user);
    executer.sleep(5000);
    JavaScriptExecuter.clickOnElement(driver, impalaPageObject.queriesFooterLabels.get(0));
    executer.sleep(2000);

    GraphUtils graphUtils = new GraphUtils();
    List<String> graphColors = graphUtils.getGraphContentColors(impalaPageObject.queryHighChartContainer
            .findElement(impalaPageObject.graphGContents));
    File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,impalaPageObject.queryHighChartContainer, scrollY);
    ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
    test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

    Assert.assertFalse(graphColors.contains(GraphColorConstants.ImpalaQueriesGraph.FIRST_USER_COLOR),
      "User is displayed when user checkbox is deselected.");
    test.log(LogStatus.PASS, "Successfully validated user is not displayed when user checkbox is deselected.");

    JavaScriptExecuter.clickOnElement(driver, impalaPageObject.queriesFooterLabels.get(0));
    executer.sleep(2000);
    test.log(LogStatus.INFO, "Selecting the user: " + user);
    graphColors = graphUtils.getGraphContentColors(impalaPageObject.queryHighChartContainer
            .findElement(impalaPageObject.graphGContents));
    screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,impalaPageObject.queryHighChartContainer, scrollY);
    ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
    test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

    Assert.assertTrue(graphColors.contains(GraphColorConstants.ImpalaQueriesGraph.FIRST_USER_COLOR),
      "User is not displayed when user checkbox is selected.");
    test.log(LogStatus.PASS, "Successfully validated user is displayed when user checkbox is selected.");
  }
}
