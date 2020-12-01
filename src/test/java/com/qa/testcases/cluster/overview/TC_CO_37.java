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
@Marker.All
public class TC_CO_37 extends BaseClass {

    /**
     * Verify Memory graph 'Download XLS'
     */

    private static final Logger LOGGER = Logger.getLogger(TC_CO_37.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void ValidateMemoryGraphDownloadAsXLS(String clusterId) {

        test = extent.startTest("TC_CO_37.ValidateMemoryGraphDownloadAsXLS: " + clusterId,
                "Validate download of Memory graph as XLS file.");
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

        homePage.clickOnMemoryGraphDownloadMenu();
        LOGGER.info("Clicked on Memory Graph download menu ... ");

        homePage.downloadMemoryGraphAsFile("Download XLS");
        LOGGER.info("Download XLS file");

        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Memory.xls"), "File is not downloaded " +
                "or size of file is zero bytes.");
        test.log(LogStatus.PASS, "Successfully downloaded Memory graph as XLS file.");
        LOGGER.info("Successfully downloaded Memory graph as XLS file.");
    }

}