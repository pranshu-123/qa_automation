package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.HomePageObject;
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
@Marker.All
public class TC_CO_21 extends BaseClass {

    /**
     * Validate download of Nodes graph as JPEG file.
     */

    private static final Logger LOGGER = Logger.getLogger(TC_CO_21.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the  nodes graph should to be 'Download JPEG.'")
    public void ValidateNodesGraphDownloadAsJPEG(String clusterId) {

        test = extent.startTest("TC_CO_21.ValidateNodesGraphDownloadAsJPEG: " + clusterId,
                "Validate download of Nodes graph as JPEG file.");
        test.assignCategory(" Cluster Overview");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        MouseActions.clickOnElement(driver, topPanelPageObject.overviewTab);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        LOGGER.info("Select cluster: "+clusterId);

        HomePageObject homePageObject = new HomePageObject(driver);
        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(3000);

        // Select this month
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        LOGGER.info("Select date picker for 30 days.");

        homePage.clickOnNodesGraphDownloadMenu();
        LOGGER.info("Clicked on Nodes Graph download menu ... ");

        homePage.downloadNodesGraphAsFile("Download JPEG");
        LOGGER.info("Download JPEG file");

        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Nodes.jpeg"), "File is not downloaded " +
                "or size of file is zero bytes.");
        test.log(LogStatus.PASS, "Successfully downloaded Nodes graph as JPEG file.");
        LOGGER.info("Successfully downloaded Nodes graph as JPEG file.");
    }

}
