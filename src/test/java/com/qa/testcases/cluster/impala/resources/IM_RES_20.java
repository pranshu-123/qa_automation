package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
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
import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_20 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(IM_RES_20.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the Group By User Memory and Query graphs should generated for selected and deselected node")
  public void validateUserCheckbox(String clusterId) {
    test = extent.startTest("IM_RES_20.validateUserCheckbox (" + clusterId + ")", "Validate the \"Group By\" filter for Queue.");
    test.assignCategory(" Cluster/Impala Resources");

    WaitExecuter waitExecuter = new WaitExecuter(driver);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    Impala impala = new Impala(driver);

    //Select impala tab
    test.log(LogStatus.INFO, "Go to resource page");
    LOGGER.info("Select impala from dropdown");
    impala.selectImpalaResource();
    waitExecuter.sleep(2000);

    HomePage homePage = new HomePage(driver);
    impala.selectMultiClusterId(clusterId);
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

    impala.selectImpalaType("Impala");
    waitExecuter.sleep(3000);


    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
    datePicker.selectThisMonth();
    waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
    JavaScriptExecuter.scrollViewWithYAxis(driver,-100);

    int scrollY = 150;
    JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);

    String user = impala.getQueriesGraphLabels().get(0);
    test.log(LogStatus.INFO, "Deselecting the user: " + user);
    waitExecuter.sleep(5000);
    JavaScriptExecuter.clickOnElement(driver, impalaPageObject.queriesFooterLabels.get(0));
    waitExecuter.sleep(2000);

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
    waitExecuter.sleep(2000);
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
