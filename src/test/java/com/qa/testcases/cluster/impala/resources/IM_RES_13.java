package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_13 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(IM_RES_13.class.getName());

  @Test(description ="P0-Verify that the user hovers the mouse over the Memory graph it should display the tooltip for the Query graph at the same data point.")
  public void verifyQueryGraphForQueueGroup() {
    test = extent.startTest("IM_RES_13.verifyQueryGraphForQueueGroup", "Verify Query graph with mouse hover.)");
    test.assignCategory(" Cluster/Impala Resources");

    test.log(LogStatus.INFO, "Login to the application");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    Impala impala = new Impala(driver);
    //Select impala tab
    test.log(LogStatus.INFO, "Go to resource page");
    LOGGER.info("Select impala from dropdown");
    impala.selectImpalaResource("Impala");
    waitExecuter.sleep(2000);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
    impalaPageObject.groupByDropdownButton.click();
    waitExecuter.waitUntilElementPresent(impalaPageObject.groupByQueueList);
    impalaPageObject.groupByQueueList.click();
    test.log(LogStatus.INFO, "Select Queue in Group by option.");

    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    test.log(LogStatus.INFO, "Select this month in date picker");

    waitExecuter.sleep(2000);
    test.log(LogStatus.INFO, "Navigate different section in memory graph");
    GraphUtils graphUtils = new GraphUtils();
    graphUtils.navigateDifferentPointOnGraph(driver,
      impalaPageObject.memoryHighChartContainer);
    List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
    List<String> queriesTooltipValues = graphUtils.getQueriesTooltipValues();

    Assert.assertTrue(memoryTooltipValues.size() > 0, "memory tooltips are not displayed");
    Assert.assertTrue(queriesTooltipValues.size() > 0, "queries tooltips are not displayed");
    for (int i=0; i<memoryTooltipValues.size(); i++) {
      Assert.assertNotNull(memoryTooltipValues.get(i), "Tooltip value displayed null value for memory graph");
      Assert.assertNotNull(queriesTooltipValues.get(i), "Tooltip value displayed null value for query graph");
    }

    List<String> nonBlankMemoriesTooltips = memoryTooltipValues.stream().filter(
            memoryTooltip -> !memoryTooltip.equals("")).collect(Collectors.toList());
    List<String> nonBlankQueriesTooltips = queriesTooltipValues.stream().filter(
            queryTooltip -> !queryTooltip.equals("")).collect(Collectors.toList());

    Assert.assertTrue(nonBlankMemoriesTooltips.size() > 0, "All memories tooltips are blank.");
    Assert.assertTrue(nonBlankQueriesTooltips.size() > 0, "All queries tooltips are blank.");

    test.log(LogStatus.PASS,"Validate When the user hovers the mouse over the Query graph" +
      " it should simultaneously display the tool tip for Memory graph at the same data point");
  }
}
