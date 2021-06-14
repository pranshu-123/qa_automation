package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_12 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_12.class.getName());

    @Test
    public void validatePagination() {
        test = extent.startTest("TC_QU_12.validatePagination", "Validate pagination in Queue Analysis page.");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        queueAnalysis.runAQueueAnalysisReport();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            waitExecuter.waitUntilElementClickable(qaPageObject.clickOnQAReports);
            qaPageObject.clickOnQAReports.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.select1stQAReport);
            waitExecuter.sleep(2000);
            // Verify if the queues are present
            test.log(LogStatus.INFO, "Verify if the reports are present in table");
            LOGGER.info("Verify if the reports are present in table");
            if (qaPageObject.getQueueReportList.size() > 0) {
                if (qaPageObject.pagination.size() > 0) {
                    String numOfPages = queueAnalysis.getPageNumber();
                    String navigatedPageValue = qaPageObject.enterPageNumberToNavigation.getAttribute("value");
                    LOGGER.info("Navigated page value --------- "+navigatedPageValue);
                    Assert.assertEquals(numOfPages, navigatedPageValue, "Pagination is not working");
                    test.log(LogStatus.PASS, "Pagination is working");
                } else {
                    throw new SkipException("Pagination is not present.");
                }
            } else {
                Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed(), "No data available is not displayed");
                LOGGER.info("There are no queue present in table.");
                test.log(LogStatus.SKIP, "There are no queue present in table");
            }

        } catch (TimeoutException te) {
            throw new AssertionError("Queue Analysis Report not completed successfully.");
        }
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
    }
}

