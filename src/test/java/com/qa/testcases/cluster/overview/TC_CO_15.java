package com.qa.testcases.cluster.overview;

import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.*;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class TC_CO_15 extends BaseClass {

  /**
   * Test Case to Verify jobs 'Inefficient events' KPI graph filter
   */
  @Test(dataProvider = "clusterid-data-provider")
  public void TC_CO_15_verifyInefficientGraph(String clusterId) {
    WaitExecuter executer = new WaitExecuter(driver);
    executer.sleep(2000);

    test = extent.startTest("TC_CO_15_verifyInefficientGraph: "+clusterId, "Verify jobs 'Inefficient events' KPI graph filter.");
    test.assignCategory("4620 - Cluster Overview");

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);
    executer.sleep(2000);

    // Select this month
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    datePicker.selectLast30Days();

    executer.sleep(2000);

    // Take Screenshot and validate the graph
    OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);

    int scrollY = 300;
    JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);

    scrollY = scrollY + datePicker.getDatePickerYPosition();

    File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.inefficientEventsGraph,scrollY);
    ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
    test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
    GraphUtils graphUtils = new GraphUtils();
    List<String> graphColors = graphUtils.getGraphContentColors(
            overviewGraph.inefficientEventsGraph.findElement(overviewGraph.graphGContents));
    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.MAP_REDUCE_COLOR),
      "Map Reduce graph is not loaded");
    test.log(LogStatus.PASS, "Successfully validated Map Reduce graph is loaded");

    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.HIVE_COLOR),
      "Hive graph is not loaded");
    test.log(LogStatus.PASS, "Successfully validated hive graph is loaded");

    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.SPARK_COLOR),
      "Spark graph is not loaded");
    test.log(LogStatus.PASS, "Successfully validated spark graph is loaded");

    // Unselect the mapreduce checkbox and validate the graph
    overviewGraph.inefficientGraphMRCheckbox.click();
    executer.sleep(1000);

    graphColors = graphUtils.getGraphContentColors(
            overviewGraph.inefficientEventsGraph.findElement(overviewGraph.graphGContents));

    // Take screenshot after unselecting mapreduce checkbox
    screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.inefficientEventsGraph,scrollY);
    ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
    test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
    Assert.assertFalse(graphColors.contains(GraphColorConstants.InefficientEventGraph.MAP_REDUCE_COLOR),
      "Map Reduce graph is loaded when Map Reduce checkbox is not selected.");
    test.log(LogStatus.PASS, "Successfully validated Map Reduce graph is not loaded when Map Reduce checkbox is not selected.");

    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.HIVE_COLOR),
      "Hive graph is not loaded when Map Reduce checkbox is not selected.");
    test.log(LogStatus.PASS, "Successfully validated hive graph is loaded when Map Reduce checkbox is not selected.");

    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.SPARK_COLOR),
      "Spark graph is not loaded when Map Reduce checkbox is not selected.");
    test.log(LogStatus.PASS, "Successfully validated spark graph is loaded when Map Reduce checkbox is not selected.");

    // Unselect the mapreduce checkbox and validate the graph
    overviewGraph.inefficientGraphMRCheckbox.click();
    overviewGraph.inefficientGraphHiveCheckbox.click();
    executer.sleep(1000);

    graphColors = graphUtils.getGraphContentColors(
            overviewGraph.inefficientEventsGraph.findElement(overviewGraph.graphGContents));
    screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.inefficientEventsGraph,scrollY);
    ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
    test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.MAP_REDUCE_COLOR),
      "Map Reduce graph is not loaded when Hive checkbox is not selected.");
    test.log(LogStatus.PASS, "Successfully validated Map Reduce graph is loaded when Hive checkbox is not selected.");

    Assert.assertFalse(graphColors.contains(GraphColorConstants.InefficientEventGraph.HIVE_COLOR),
      "Hive graph is loaded when Hive checkbox is not selected.");
    test.log(LogStatus.PASS, "Successfully validated hive graph is not loaded when Hive checkbox is not selected.");

    Assert.assertTrue(graphColors.contains(GraphColorConstants.InefficientEventGraph.SPARK_COLOR),
      "Spark graph is not loaded when Hive checkbox is not selected.");
    test.log(LogStatus.PASS, "Successfully validated spark graph is loaded when Hive checkbox is not selected.");
  }
}
