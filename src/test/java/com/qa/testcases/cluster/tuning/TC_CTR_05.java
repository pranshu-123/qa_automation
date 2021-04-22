package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Tuning;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_05 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_05.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningReportForDifferentClsuters(String clusterId) {
        test = extent.startTest("TC_CTR_05.validateTuningForDifferentDatePicker: " + clusterId,
                "Verify tuning report for different clusters.");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilPageFullyLoaded();
        TuningPageObject tuningPageObject = new TuningPageObject(driver);

        Tuning tuning = new Tuning(driver);
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.waitUntilElementPresent(tuningPageObject.archivesText);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);

        HomePage homePage = new HomePage(driver);

        LOGGER.info("Click on + button");
        String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        LOGGER.info("Clicked on Tuning Tab + icon");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab + icon");
        LOGGER.info("Clicked on Cluster Id button");
        test.log(LogStatus.INFO,"Clicked on Cluster Id button");
        homePage.selectMultiClusterId(clusterId);
        tuning.clickOnModalRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");
        waitExecuter.waitUntilElementPresent(tuningPageObject.archivesText);
        waitExecuter.sleep(50000);

        WebElement statusElement = driver.findElement(By.xpath(statusXpath));
        try{
            waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                    "SUCCESS");
            test.log(LogStatus.PASS, "Verified Tuning report is completed with status " + statusElement.getText());
        }catch (TimeoutException te) {
            throw new AssertionError("Tuning Report not completed successfully for cluster :"+ clusterId);
        }
    }
}
