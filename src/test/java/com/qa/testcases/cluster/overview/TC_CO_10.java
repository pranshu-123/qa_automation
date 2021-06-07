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
 * @author Sarbashree Ray
 */
@Marker.ClusterOverview
@Marker.All
public class TC_CO_10  extends BaseClass
{
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the cluster VCores KPI graph filter")
    public void TC_CO_10_VerifyclusterVCoresKPIgraphfilter(String clusterId) {
        test = extent.startTest("TC_CO_10_VerifyclusterVCoresKPIgraphfilter"+clusterId, "Verify " +
                "cluster VCores KPI graph filter");
        test.assignCategory(" Cluster Overview");

        test.log(LogStatus.INFO, "verify Clusterid : " + clusterId);

        WaitExecuter executer = new WaitExecuter(driver);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        executer.sleep(2000);
        executer.waitUntilPageFullyLoaded();


        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        executer.sleep(2000);
        executer.waitUntilPageFullyLoaded();

        // Take Screenshot and validate the graph
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.vcoreGraph,0);
        executer.sleep(4000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        GraphUtils graphUtils = new GraphUtils();
        List<String> graphColors = graphUtils.getGraphContentColors(
                overviewGraph.vcoreGraph.findElement(overviewGraph.graphGContents));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
                "Total Vcores allocated graph is not loaded");
        test.log(LogStatus.INFO, "Successfully validated total Vcores allocated graph is loaded");
        Assert.assertTrue(graphColors.contains(GraphColorConstants.VCoresGraph.TotalAvailable_COLOR),
                "Total available graph is not loaded");
        test.log(LogStatus.INFO, "Successfully validated available graph is loaded");

        JavaScriptExecuter.clickOnElement(driver, overviewGraph.VcoreTotalAllocatedCheckbox);
        test.log(LogStatus.INFO, "Uncheck total allocated and validate the graph");
        executer.sleep(1000);

        graphColors = graphUtils.getGraphContentColors(
                overviewGraph.vcoreGraph.findElement(overviewGraph.graphGContents));
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.vcoreGraph, 0);
        executer.sleep(1000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.VCoresGraph.TotalAvailable_COLOR),
                "Total available Vcore graph is not loaded after deselecting total allocated.");
        executer.sleep(1000);
        test.log(LogStatus.INFO, "Total available Vcore graph is loaded after deselecting total allocated.");
//        Assert.assertFalse(graphColors.contains(GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
//                "Total allocated Vcore graph is loaded after deselecting total allocated.");
//        test.log(LogStatus.INFO, "Total allocated Vcore graph is not loaded after deselecting total allocated.");

        overviewGraph.VcoreTotalAllocatedCheckbox.click();
        executer.sleep(2000);
        overviewGraph.VcoreTotalAvailableCheckbox.click();
        test.log(LogStatus.INFO, "Check running check box and unchecked accepted checkbox.");
        executer.sleep(3000);
        test.log(LogStatus.INFO, "Uncheck accepted check box and validate the running graph.");
        executer.sleep(1000);

        graphColors = graphUtils.getGraphContentColors(
                overviewGraph.vcoreGraph.findElement(overviewGraph.graphGContents));
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.vcoreGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
                "Total available Vcore graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available Vcore graph is not loaded after deselecting total allocated.");
        executer.sleep(1000);
        Assert.assertFalse(graphColors.contains(GraphColorConstants.VCoresGraph.TotalAvailable_COLOR),
                "Total available Vcore graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available Vcore graph is not loaded after deselecting total allocated.");
        executer.sleep(1000);
    }
}
