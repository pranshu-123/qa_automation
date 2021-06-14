package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Marker.QueueAnalysis
@Marker.All
public class TC_QU_13 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_13.class.getName());

    @Test
    public void validateQueueGraph() {
        test = extent.startTest("TC_QU_13.validateQueueGraph",
                "Validate graphs for a queue");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        queueAnalysis.runAQueueAnalysisReport();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            queueAnalysis.navigateToQueueTable();
            List<String> expectedListOfGraph = new ArrayList<String>();
            List<String> graphNamesOFQueue = new ArrayList<String>();
            if (qaPageObject.getQueueList.size() > 0) {
                String selectedQueueName = qaPageObject.getQueueList.get(0).getText().trim().toLowerCase();
                expectedListOfGraph.add(selectedQueueName + "apps");
                expectedListOfGraph.add(selectedQueueName + "vcores");
                expectedListOfGraph.add(selectedQueueName + "mb");
                LOGGER.info("Expected list of graphs- " + expectedListOfGraph);
                test.log(LogStatus.PASS, "Expected list of graphs- " + expectedListOfGraph);
                queueAnalysis.select1stQueueFromTable();
                for (int i = 0; i < 3; i++) {
                    graphNamesOFQueue.add((qaPageObject.queueGraph.get(i).getAttribute("id").trim().toLowerCase()));
                    waitExecuter.sleep(1000);
                }
                LOGGER.info("Graphs loaded for selected queue for- " + graphNamesOFQueue);
                test.log(LogStatus.PASS, "Graphs loaded for selected queue for- " + graphNamesOFQueue);
                Assert.assertEquals(qaPageObject.queueGraph.size(), 3, "The graph did not load properly.");
                Assert.assertTrue(graphNamesOFQueue.equals(expectedListOfGraph), "Expected name of graph did not match the graph name present");
                test.log(LogStatus.PASS, "All 3 graphs are visible");
            } else {
                Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed());
                test.log(LogStatus.SKIP, "There is no queue in table, thus cannot verify graph");
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
