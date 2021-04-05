package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Tuning;
import com.qa.scripts.clusters.UserReport;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * Verify cluster tuning report is generated for default filters
 */

@Marker.Tuning
@Marker.All
public class TC_CTR_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningReportGenerated(String clusterId) {
        test = extent.startTest("TC_CTR_01.validateTuningReportGenerated: "+ clusterId, "Verify cluster tuning " +
                "report is generated for default filters");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
//        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
//        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
//        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
//        waitExecuter.sleep(3000);
//        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);

        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button");
        //reportsPage.clickOnLatestReport(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        LOGGER.info("Clicked on Tuning Tab + icon");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab + icon");

        TuningPageObject tuningPageObject = new TuningPageObject(driver);

        Tuning tuning = new Tuning(driver);
        //tuning.closeConfirmationMessageNotification();
        //tuning.clickOnRunButton();

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        tuning.clickOnModalRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

//        waitExecuter.waitUntilElementPresent(tuningPageObject.runButton);
//        waitExecuter.waitUntilElementClickable(tuningPageObject.runButton);

//        try {
//            waitExecuter.waitUntilTextToBeInWebElement(tuningPageObject.confirmationMessageElement,
//                    "Cluster Tuning completed successfully.");
//            test.log(LogStatus.PASS, "Verified Tuning report is loaded properly.");
//            LOGGER.info("Verified Tuning report is loaded properly.");
//        } catch (TimeoutException te) {
//            throw new AssertionError("Tuning Report not completed successfully.");
//        }

        WebElement statusElement = driver.findElement(By.xpath(statusXpath));
        try{
            waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                    "SUCCESS");
            test.log(LogStatus.PASS, "Verified Tuning report is loaded properly.");
            LOGGER.info("Verified Tuning report is loaded properly.");
        }catch (TimeoutException te) {
            throw new AssertionError("Tuning Report not completed successfully.");
        }

    }
}
