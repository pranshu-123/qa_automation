package com.qa.testcases.cluster.impala.resources;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class IM_RES_05 extends BaseClass {

  @Test
  public void verifyQueryGraphForUserGroup() {
    test = extent.startTest("IM_RES_05.verifyQueryGraphForUserGroup", "Verify Query graph with mouse hover.)");
    test.assignCategory("4620 - Cluster/Impala Resources");

    test.log(LogStatus.INFO, "Login to the application");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    test.log(LogStatus.INFO, "Go to impala page");
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    waitExecuter.waitUntilElementPresent(topPanelPageObject.impalaTab);
    waitExecuter.waitUntilElementClickable(topPanelPageObject.impalaTab);
    JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);

    waitExecuter.waitUntilUrlContains("clusters/impala");
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    waitExecuter.sleep(3000);

    test.log(LogStatus.INFO, "Select this month in date picker");
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);

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
    test.log(LogStatus.PASS,"Validate When the user hovers the mouse over the Query graph" +
      " it should simultaneously display the tool tip for Memory graph at the same data point");
  }
}
