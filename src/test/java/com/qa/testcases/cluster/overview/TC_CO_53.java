package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.FileUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_53 extends BaseClass {

   /**
   * Verify Inefficient Events graph 'Download SVG'
   */

  private static final Logger LOGGER = Logger.getLogger(TC_CO_53.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the Inefficient events graph should to be 'Download SVG.")
  public void validateInefficientEventsGraphDownloadAsSVG(String clusterId) {

    test = extent.startTest("TC_CO_53.validateInefficientEventsGraphDownloadAsSVG: " + clusterId,
            "Validate download of Inefficient Events graph as SVG file.");
    test.assignCategory(" Cluster Overview");

    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    MouseActions.clickOnElement(driver, topPanelPageObject.overviewTab);

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);
    LOGGER.info("Select cluster: "+clusterId);

    WaitExecuter executer = new WaitExecuter(driver);
    executer.sleep(3000);

    // Select this month
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    datePicker.selectLast30Days();
    LOGGER.info("Select date picker for 30 days.");

    homePage.clickOnInefficientEventsGraphDownloadMenu();
    LOGGER.info("Clicked on Inefficient Events Graph download menu ... ");

    homePage.downloadInefficientEventsGraphAsFile("Download SVG");
    LOGGER.info("Download SVG file");

    Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Jobs.svg+xml"), "File is not downloaded " +
            "or size of file is zero bytes.");
    test.log(LogStatus.PASS, "Successfully downloaded Inefficient Events graph as SVG file.");
    LOGGER.info("Successfully downloaded Inefficient Events graph as SVG file.");
    }

}
