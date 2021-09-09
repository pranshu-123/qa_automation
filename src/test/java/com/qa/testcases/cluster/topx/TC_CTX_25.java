package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.TopX
public class TC_CTX_25 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_CTX_24.class);

    @Test
    public void verifyForDifferentCluster() {
        test = extent.startTest("TC_CTX_25.verifyForDifferentCluster",
            "Verify TopX report is generation for different clusters");
        test.assignCategory(" Cluster - Top X");
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        TopX topX = new TopX(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button", test);
        reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
        HomePage homePage = new HomePage(driver);
        homePage.clickOnClusterDropDown();
        waitExecuter.sleep(1000);
        List<String> clustersList =
            topX.getClustersList().stream().map(data -> data.getText()).collect(Collectors.toList());
        waitExecuter.sleep(1000);
        topX.closeModalIfExists();
        waitExecuter.sleep(1000);
        for (int i = 0; i < clustersList.size(); i++) {
            String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
            String clusterId = clustersList.get(i);
            topX.selectCluster(clusterId);
            waitExecuter.sleep(1000);
            topX.clickOnModalRunButton();
            waitExecuter.sleep(1000);
            WebElement statusElement = driver.findElement(By.xpath(statusXpath));
            try {
                waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                    "SUCCESS");
            } catch (TimeoutException te) {
                throw new AssertionError("Top X Report not completed successfully.");
            }
            test.log(LogStatus.PASS, "TopX report is generated for cluster id: " + clusterId);
            topX.validateLatestReport("Cluster", clusterId);
            LOGGER.pass("Correct value is displayed on report.", test);
            test.log(LogStatus.PASS, "Data is loaded for different top number.");
            topX.closeModalIfExists();
        }
    }
}
