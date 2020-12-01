package com.qa.testcases.cluster.queueanalysis;

import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.relevantcodes.extentreports.LogStatus;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_16 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_QU_16.class.getName());

	@Test
	public void validateMemoryQueueGraph() {
		test = extent.startTest("TC_QU_16.validateMemoryQueueGraph", "Validate Memory graphs for a queue");
		test.assignCategory("Jobs - Queue Analysis");
		test.log(LogStatus.INFO, "Login to the application");
		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
		QueueAnalysis queueAnalysis = new QueueAnalysis(driver);

		// Navigate to Queue Analysis tab from header
		test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
		test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
		test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
		queueAnalysis.navigateToQueueAnalysis();

		// Verify if the queues are present
		test.log(LogStatus.INFO, "Verify if the queues are present in table");
		LOGGER.info("Verify if the queues are present in table");
		if (qaPageObject.getQueueNameFromTable.size() > 0) {

			// Close confirmation message box and search the queue
			test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
			LOGGER.info("Close confirmation message box and search the queue");
			queueAnalysis.closeConfirmationBoxAndClickQueue();

			// Get expected list of tool tips
			test.log(LogStatus.INFO, "Get expected list of tool tips");
			LOGGER.info("Get expected list of tool tips");
			List<String> expectedMemoryToolTip = queueAnalysis.expectedMemToolTips();
			test.log(LogStatus.INFO, "Expected tool tip values- " + expectedMemoryToolTip);
			// Get actual list of tool tips
			test.log(LogStatus.INFO, "Get actual list of tool tips");
			LOGGER.info("Get actual list of tool tips");
			List<String> actualToolTipValues = queueAnalysis.actualMemToolTips();
			test.log(LogStatus.INFO, "Actual tool tip values- " + actualToolTipValues);
			
			Assert.assertTrue(actualToolTipValues.containsAll(expectedMemoryToolTip),
					"Tool tip of memory graph did not match set of values");
			test.log(LogStatus.PASS, "Tool tips verified of memory graph ");
		} else {
			Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed(), "No data available is not displayed");
			test.log(LogStatus.SKIP, "There are no queue present in table");
		}

	}
}