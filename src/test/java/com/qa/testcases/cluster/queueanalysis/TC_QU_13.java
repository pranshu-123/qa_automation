package com.qa.testcases.cluster.queueanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

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
        // Close confirmation message box and search the queue
        test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
        LOGGER.info("Close confirmation message box and search the queue");
        queueAnalysis.closeConfirmationMessageNotification();
        waitExecuter.sleep(2000);
        List<String> expectedListOfGraph = new ArrayList<String>();
        List<String> graphNamesOFQueue = new ArrayList<String>();
        if(qaPageObject.getQueueNameFromTable.size() > 0) {
        String selectedQueueName = qaPageObject.getQueueNameFromTable.get(0).getText().trim().toLowerCase();
        expectedListOfGraph.add(selectedQueueName + "apps");
		expectedListOfGraph.add(selectedQueueName + "vcores");
		expectedListOfGraph.add(selectedQueueName + "mb");
		LOGGER.info("Expected list of graphs- "+expectedListOfGraph);
		test.log(LogStatus.PASS, "Expected list of graphs- "+expectedListOfGraph);
		qaPageObject.getQueueNameFromTable.get(0).click();
		waitExecuter.sleep(5000);
        for (int i = 0; i < qaPageObject.getQueueNameFromTable.size() ;i++) {       		
        		graphNamesOFQueue.add((qaPageObject.queueGraph.get(i).getAttribute("id").toString().trim().toLowerCase()));       		
        	}
        LOGGER.info("Graphs loaded for selected queue for- "+graphNamesOFQueue);
        test.log(LogStatus.PASS, "Graphs loaded for selected queue for- "+graphNamesOFQueue);
        Assert.assertEquals(qaPageObject.queueGraph.size(),3,"The graph did not load properly.");
        Assert.assertTrue(graphNamesOFQueue.equals(expectedListOfGraph),"Expected name of graph did not match the graph name present");
        test.log(LogStatus.PASS, "All 3 graphs are visible");
        }
        else {
        	Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed());
            test.log(LogStatus.SKIP, "There is no queue in table, thus cannot verify graph");
        }
    }
}
