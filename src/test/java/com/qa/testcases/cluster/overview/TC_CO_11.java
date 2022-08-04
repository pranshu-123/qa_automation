package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
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
 *
 */
@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_11 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the cluster node's KPI graph should be generated for the selected filters.")
    public void TC_CO_11_verifyMemoryGraph(String clusterId) {
        // Select this month
        test = extent.startTest("TC_CO_11_verifyMemoryGraph: "+clusterId,"Verify cluster Memory KPI graph filter");
        test.assignCategory(" Cluster Overview");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(20000);

        // Take Screenshot and validate the graph
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.memoryGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        GraphUtils graphUtils = new GraphUtils();
        List<String> graphColors = graphUtils.getGraphContentColors(
                overviewGraph.memoryGraph.findElement(overviewGraph.graphGContents));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.MemoryGraph.TOTAL_ALLOCATED_COLOR),
                "Total allocated graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated total allocated graph is loaded");
        Assert.assertTrue(graphColors.contains(GraphColorConstants.MemoryGraph.TOTAL_AVAILABLE_COLOR),
                "Total available graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated available graph is loaded");

        JavaScriptExecuter.clickOnElement(driver, overviewGraph.memoryTotalAllocatedCheckbox);
        test.log(LogStatus.INFO, "Uncheck total allocated and validate the graph");
        executer.sleep(1000);

        graphColors = graphUtils.getGraphContentColors(
                overviewGraph.memoryGraph.findElement(overviewGraph.graphGContents));
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.memoryGraph, 0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.MemoryGraph.TOTAL_AVAILABLE_COLOR),
                "Total available graph is not loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available graph is loaded after deselecting total allocated.");
//        Assert.assertFalse(graphColors.contains(GraphColorConstants.MemoryGraph.TOTAL_ALLOCATED_COLOR),
//                "Total allocated graph is loaded after deselecting total allocated.");
//        test.log(LogStatus.PASS, "Total allocated graph is not loaded after deselecting total allocated.");

        JavaScriptExecuter.clickOnElement(driver, overviewGraph.memoryTotalAllocatedCheckbox);
        JavaScriptExecuter.clickOnElement(driver, overviewGraph.memoryTotalAvailableCheckbox);
        test.log(LogStatus.INFO, "Uncheck total allocated and validate the graph");
        executer.sleep(1000);

        graphColors = graphUtils.getGraphContentColors(
                overviewGraph.memoryGraph.findElement(overviewGraph.graphGContents));
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.memoryGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.MemoryGraph.TOTAL_ALLOCATED_COLOR),
                "Total allocated graph is not loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total allocated graph is loaded after deselecting total allocated.");
        Assert.assertFalse(graphColors.contains(GraphColorConstants.MemoryGraph.TOTAL_AVAILABLE_COLOR),
                "Total available graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available graph is not loaded after deselecting total allocated.");
    }
}

