package com.qa.testcases.cluster.overview;

import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author S.Ray
 */
public class TC_CO_14 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_14_VerifyJobsByStatusKPIGraphFilter(String clusterId) {
        // Select this month
        test = extent.startTest("TC_CO_14_VerifyJobsByStatusKPIGraphFilter"+clusterId, "Verify cluster by status KPI graph filter");
        test.assignCategory("4620 - Cluster Overview");
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        datePicker.clickOnDatePicker();
        datePicker.selectThisMonth();

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(2000);

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // Take Screenshot and validate the graph
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Failed_COLOR),
                "Total Vcores allocated graph is not loaded");
        test.log(LogStatus.PASS, "Successfully validated total Vcores allocated graph is loaded");

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Failed_COLOR),
                "Total available Failed graph is not loaded");
        test.log(LogStatus.PASS, "Successfully Failed validated available graph is loaded");

        overviewGraph.FailedChkBox.click();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box.");
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box and validate the accepted graph");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph, 0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Success_COLOR),
                "Total available Success graph is not loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available Success graph is loaded after deselecting total allocated.");


        overviewGraph.FailedChkBox.click();
        executer.sleep(2000);
        overviewGraph.SuccessChkBox.click();
        test.log(LogStatus.INFO, "Check running check box and unchecked accepted checkbox.");
        executer.sleep(3000);
        test.log(LogStatus.INFO, "Uncheck accepted check box and validate the running graph.");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.statusGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertFalse(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.ByStatusGraph.Success_COLOR),
                "Total available Success graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total Success available graph is not loaded after deselecting total allocated.");
    }
}

