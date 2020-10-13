package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import java.io.File;

@Marker.ClusterOverview
@Marker.All
public class TC_CO_13 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_13_VerifyJobsRunningKPIGraphFilter(String clusterId) {
        test = extent.startTest("TC_CO_13_VerifyJobsRunningKPIGraphFilter: "+clusterId, "Verify jobs 'running' KPI graph filter.");
        test.assignCategory("4620 - Cluster Overview");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(2000);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        //Default validate the Running graph
        test.log(LogStatus.INFO, "Running and accepted check box checked and validate the running graph");
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        int scrollY = 370;
        JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);
        executer.sleep(3000);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.runningGraph, scrollY);

        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.RunningGraph.RUNNING_COLOR),
                "Running graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated running graph is loaded");

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.RunningGraph.ACCEPTED_COLOR),
                "Running accepted graph is not loaded.");
        executer.sleep(3000);
        test.log(LogStatus.PASS, "Running accepted graph is loaded.");

        //Uncheck the running checkBox, and validate accepted graph
        JavaScriptExecuter.clickOnElement(driver,overviewGraph.runningChkBoxText);
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box.");
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box and validate the accepted graph");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.runningGraph,scrollY);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.RunningGraph.ACCEPTED_COLOR),
                "Accepted graph is not loaded after deselecting running checkbox.");
        test.log(LogStatus.PASS, "Accepted graph is loaded after deselecting running checkbox.");

        //Check the running checkBox and uncheck accepted check-box and validate running graph
        JavaScriptExecuter.clickOnElement(driver,overviewGraph.runningChkBoxText);
        executer.sleep(2000);
        JavaScriptExecuter.clickOnElement(driver,overviewGraph.acceptedChkBoxText);
        test.log(LogStatus.INFO, "Check running check box and unchecked accepted checkbox.");
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck accepted check box and validate the running graph.");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.runningGraph,scrollY);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.RunningGraph.RUNNING_COLOR),
                "Running graph is not loaded after deselecting accepted checkbox.");
        test.log(LogStatus.PASS, "Running graph is loaded after deselecting accepted checkbox.");


    }
}
