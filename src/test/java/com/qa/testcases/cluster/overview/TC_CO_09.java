package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
/**
 * @author Birender Kumar
 */
@Marker.ClusterOverview
@Marker.All
public class TC_CO_09 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the cluster nodes KPI graph should be generated for the selected filters.")
    public void TC_CO_09_VerifyClusterNodesKPIGraphFilter(String clusterId) {
        test = extent.startTest("TC_CO_09_VerifyClusterNodesKPIGraphFilter: "+clusterId , "Verify cluster Nodes KPI graph filter.");
        test.assignCategory(" Cluster Overview");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        System.out.println(homePage.getHomePageUrl());

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(2000);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        //click on ActiveCheckBox to verify node bad graph, Take Screenshot and validate the graph
        homePage.clickActiveChkBox();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck active check box and validate the node graph");
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.nodeGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.NodesGraph.BAD_COLOR),
                "Node bad graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated Node bad graph is loaded");

        //click Active Check Box and uncheck Bad check box
        homePage.clickActiveChkBox();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Check active check box.");
        homePage.clickBadChkBox();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck bad check box and validate the node graph");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.nodeGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.NodesGraph.ACTIVE_COLOR),
                "Node active graph is not loaded after deselecting bad checkbox.");
        test.log(LogStatus.PASS, "Node active graph is loaded after deselecting bad checkbox.");
        homePage.clickBadChkBox();
    }
}
