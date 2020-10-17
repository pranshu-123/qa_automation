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
 * @author Sarbashree Ray
 */
@Marker.ClusterOverview
@Marker.All
public class TC_CO_10  extends BaseClass
{
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_10_VerifyclusterVCoresKPIgraphfilter(String clusterId) {
        test = extent.startTest("TC_CO_10_VerifyclusterVCoresKPIgraphfilter"+clusterId, "Verify cluster VCores KPI graph filter");
        test.assignCategory(" Cluster Overview");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(2000);

        // Take Screenshot and validate the graph
        OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.vcoreGraph,0);
        executer.sleep(4000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
                "Total Vcores allocated graph is not loaded");
        executer.sleep(3000);
        test.log(LogStatus.PASS, "Successfully validated total Vcores allocated graph is loaded");

        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
                "Total available graph is not loaded");
        executer.sleep(2000);
        test.log(LogStatus.PASS, "Successfully validated available graph is loaded");

        overviewGraph.VcoreTotalAllocatedCheckbox.click();
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box.");
        executer.sleep(2000);
        test.log(LogStatus.INFO, "Uncheck running check box and validate the accepted graph");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.vcoreGraph, 0);
        executer.sleep(1000);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.VCoresGraph.TotalAvailable_COLOR),
                "Total available Vcore graph is not loaded after deselecting total allocated.");
        executer.sleep(1000);
        test.log(LogStatus.PASS, "Total available Vcore graph is loaded after deselecting total allocated.");
        Assert.assertFalse(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
                "Total allocated Vcore graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total allocated Vcore graph is not loaded after deselecting total allocated.");

        overviewGraph.VcoreTotalAllocatedCheckbox.click();
        executer.sleep(2000);
        overviewGraph.VcoreTotalAvailableCheckbox.click();
        test.log(LogStatus.INFO, "Check running check box and unchecked accepted checkbox.");
        executer.sleep(3000);
        test.log(LogStatus.INFO, "Uncheck accepted check box and validate the running graph.");
        executer.sleep(1000);
        screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.vcoreGraph,0);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
        Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.VCoresGraph.TotalAllocated_COLOR),
                "Total available Vcore graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available Vcore graph is not loaded after deselecting total allocated.");
        executer.sleep(1000);
        Assert.assertFalse(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.VCoresGraph.TotalAvailable_COLOR),
                "Total available Vcore graph is loaded after deselecting total allocated.");
        test.log(LogStatus.PASS, "Total available Vcore graph is not loaded after deselecting total allocated.");
        executer.sleep(1000);
    }
}
