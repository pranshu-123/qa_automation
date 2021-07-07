package com.qa.testcases.cluster.yarn.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.clusters.YarnPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.Graphs;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.Log;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

@Marker.YarnResources
@Marker.All
public class YR_013 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(YR_013.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that all the graphs should be color-coded with different color")
    public void YR_013_verifyAppsCodedWithDiffColour(String clusterId) {
        test = extent.startTest("YR_013_verifyAppsCodedWithDiffColour: " + clusterId,
                "All the graphs shown for different apps should be colour coded with different colour.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_013_verifyAppsCodedWithDiffColour");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Yarn yarn = new Yarn(driver);
        Graphs graphs = new Graphs(driver);
        YarnPageObject yarnPageObject = new YarnPageObject(driver);
        Impala impala = new Impala(driver);

        waitExecuter.sleep(2000);

        yarn.verifyYarnResourceHeaderisDisplayed();
        LOGGER.info("Yarn Resource Header is displayed.");
        test.log(LogStatus.INFO, "Yarn Resource Header is displayed.");

        waitExecuter.sleep(2000);
        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(1000);
        //Select cluster id
        yarn.selectResourceType("Yarn");
        waitExecuter.sleep(2000);
        //Select cluster id
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("DatePicker is selected for last30 Days");
        test.log(LogStatus.INFO, "Date is selected from DatePicker");

        yarn.clickOnGroupByDropDown();
        yarn.selectApplicationType();
        test.log(LogStatus.INFO, "Selected Application Type, from dropdown.");

        //Check for Apps color
        File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver, yarnPageObject.vCoresAppGraph, 100);
        ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
        test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

        //List<String> rgbValueList = graphs.getRGBValuesFromElement(yarnPageObject.vcoreGraphAppsRGBValues,":","style");
        Assert.assertTrue(graphs.validateGraphIsGenerated(yarnPageObject.vcoreGraphAppNames, yarnPageObject.vcoreGraphAppsRGBValues,
                yarnPageObject.hashCodeVcoreColors), "The color code of graph does not match");
    }
}
