package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

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
                "Total Jobs by status allocated graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated total Vcores allocated graph is loaded");
        executer.sleep(2000);
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Success_COLOR),
                "Total available Failed graph is not loaded");
        test.log(LogStatus.PASS, "Successfully Failed validated available graph is loaded");

        overviewGraph.FailedChkBox.click();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box.");
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box and validate the accepted graph");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph, scrollY);
        executer.sleep(4000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Success_COLOR),
                "Success Graph is loaded when Success checkbox is not selected");
        test.log(LogStatus.PASS, "Successfully validated Success graph is loaded when Success checkbox is not selected.");


        overviewGraph.SuccessChkBox.click();
        executer.sleep(2000);
        overviewGraph.FailedChkBox.click();
        test.log(LogStatus.INFO, "Check running check box and unchecked accepted checkbox.");
        executer.sleep(3000);
        test.log(LogStatus.INFO, "Uncheck accepted check box and validate the running graph.");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph,scrollY);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Failed_COLOR),
                "Failed Graph is loaded when Failed checkbox is not selected.");
        executer.sleep(1000);
        test.log(LogStatus.PASS, "Successfully validated Failed graph is loaded when Failed checkbox is not selected.");

    }
}

