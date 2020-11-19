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
public class TC_CO_14 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_14_VerifyJobsByStatusKPIGraphFilter(String clusterId) {
        // Select this month
        test = extent.startTest("TC_CO_14_VerifyJobsByStatusKPIGraphFilter"+clusterId, "Verify cluster by status KPI graph filter");
        test.assignCategory(" Cluster Overview");
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(3000);

        // Take Screenshot and validate the graph
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        int scrollY = 370;
        JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);
        scrollY = scrollY + datePicker.getDatePickerYPosition();
        executer.sleep(3000);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph,scrollY);
        executer.sleep(4000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Failed_COLOR),
                "Total Jobs By status graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated By Status graph is loaded");
        executer.sleep(2000);

        GraphUtils graphUtils = new GraphUtils();
        List<String> graphColors = graphUtils.getGraphContentColors(
                overviewGraph.statusGraph.findElement(overviewGraph.graphGContents));
        Assert.assertTrue(graphColors.contains(GraphColorConstants.ByStatusGraph.Success_COLOR),
                "Failed graph is not loaded");
        test.log(LogStatus.PASS, "Successfully loaded Failed graph");

        overviewGraph.FailedChkBox.click();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck failed check box.");
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck failed check box and validate the Success graph");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph, scrollY);
        executer.sleep(4000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        List<String> graphColors_success = graphUtils.getGraphContentColors(
                overviewGraph.statusGraph.findElement(overviewGraph.graphGContents));
        Assert.assertTrue(graphColors_success.contains(GraphColorConstants.ByStatusGraph.Success_COLOR),
                "Success Graph is loaded when Success checkbox is not selected");
        test.log(LogStatus.PASS, "Successfully validated Success graph is loaded when Success checkbox is not selected.");

        overviewGraph.SuccessChkBox.click();
        executer.sleep(2000);
        overviewGraph.FailedChkBox.click();
        test.log(LogStatus.INFO, "Check Failed check box and unchecked success checkbox.");
        executer.sleep(3000);
        test.log(LogStatus.INFO, "Uncheck success check box and validate the failed graph.");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph,scrollY);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        List<String> graphColors_failed = graphUtils.getGraphContentColors(
                overviewGraph.statusGraph.findElement(overviewGraph.graphGContents));
        Assert.assertTrue(graphColors_failed.contains(GraphColorConstants.ByStatusGraph.Failed_COLOR),
                "Failed Graph is loaded when Failed checkbox is not selected.");
        executer.sleep(1000);
        test.log(LogStatus.PASS, "Successfully validated Failed graph is loaded when Failed checkbox is not selected.");

        Assert.assertTrue(graphColors_failed.contains(GraphColorConstants.ByStatusGraph.Killed_COLOR),
                "Killed Graph is loaded when killed checkbox is not selected.");
        executer.sleep(1000);
        test.log(LogStatus.PASS, "Successfully validated killed graph is loaded when killed checkbox is not selected.");


    }
}

