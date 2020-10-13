package com.qa.testcases.cluster.overview;

import com.qa.base.BaseClass;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.FileUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CO_20 extends BaseClass {

    /**
     * Validate download of Nodes graph as PNG file.
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_20_ValidateNodesGraphDownloadAsPNG(String clusterId) {

        test = extent.startTest("TC_CO_20_ValidateNodesGraphDownloadAsPNG: " + clusterId, "Validate download of Nodes graph as PNG file.");
        test.assignCategory("4620 - Cluster Overview");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        MouseActions.clickOnElement(driver, topPanelPageObject.overviewTab);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        HomePageObject homePageObject = new HomePageObject(driver);
        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(3000);

        // Select this month
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        homePage.clickOnNodesGraphDownloadMenu();
        homePage.nodesGraphDownloadPNG();
        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Nodes.png"), "File is not downloaded " +
                "or size of file is zero bytes.");

        homePage.clickOnNodesGraphDownloadMenu();
        homePage.nodesGraphDownloadJPEG();
        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Nodes.jpeg"), "File is not downloaded " +
                "or size of file is zero bytes.");


    }

}
