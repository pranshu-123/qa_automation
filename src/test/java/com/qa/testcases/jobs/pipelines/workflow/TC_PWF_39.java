package com.qa.testcases.jobs.pipelines.workflow;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.jobs.JobsWorkflowPageObject;
import com.qa.scripts.jobs.applications.JobsWorkflow;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Ojasvi Pandey
 */

@Marker.All
@Marker.JobsWorkflow

public class TC_PWF_39 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_PWF_39.class.getName());

	@Test
	public void verifySparkTaggedWorkflowDetails() {
		test = extent.startTest("TC_PWF_39.verifySparkTaggedWorkflowDetails",
				"Verify Spark Tagged workflow details in Unravel UI.");
		test.assignCategory("Workflow Page");
		LOGGER.info("Click on Jobs Pipeline tab");
		test.log(LogStatus.INFO, "Initialize all classes");
		JobsWorkflow jobWorkflow = new JobsWorkflow(driver);
		JobsWorkflowPageObject workflowPageObject = new JobsWorkflowPageObject(driver);
		test.log(LogStatus.INFO, "Navigate to pipeline tab through Jobs page");
		jobWorkflow.clickOnJobsPipelineTab();
		test.log(LogStatus.INFO, "Select last 7 days workflows");
		jobWorkflow.selectLast7Days();
		test.log(LogStatus.INFO, "Search for map reduce workflow and navigate to workflow details page.");
		try {
			jobWorkflow.searchWorkflowName(PageConstants.WorkflowName.SPARK_TAGGED);
			if (workflowPageObject.unknownBadge.size() != 0) {
				List<String> workflowTime = jobWorkflow.isJobTimePresent();
				test.log(LogStatus.INFO, "Workflowid time duration on workflow page- " + workflowTime);
				LOGGER.info("Workflowid time duration on workflow page- " + workflowTime);
				List<String> workflowKPI = jobWorkflow.isKPIsPresent();
				test.log(LogStatus.INFO, "Workflowid KPI on workflow page- " + workflowKPI);
				LOGGER.info("Workflowid KPI on workflow page- " + workflowKPI);
				String workflowId = jobWorkflow.getWorkflowId();
				test.log(LogStatus.INFO, "Workflowid on navigating to workflow page- " + workflowId);
				LOGGER.info("Workflowid on navigating to workflow page- " + workflowId);
				List<String> getJobIdsFromGanttChart = jobWorkflow.getJobIdsFromParentTag();
				test.log(LogStatus.INFO, "All job ids from gantt chart- " + getJobIdsFromGanttChart);
				LOGGER.info("All job ids from gantt chart- " + getJobIdsFromGanttChart);
				jobWorkflow.close();
				jobWorkflow.scrollToTopOfThePage();
				Set<String> WFLId = jobWorkflow.searchByJobIDGetWFIId(getJobIdsFromGanttChart);
				test.log(LogStatus.INFO, "Get workflowid from all different job ids- " + WFLId);
				LOGGER.info("Get workflowid from all different job ids- " + WFLId);
				for (String id : WFLId) {
					Assert.assertTrue(id.equals(workflowId),
							"The workflow id does not match from workflow page to application page");
					Assert.assertNotNull(workflowTime, " Workflow duration -->Start, End, Duration are null");
					Assert.assertNotNull(workflowKPI, " Workflow KPI are null");
					test.log(LogStatus.PASS, "All the application KPIS, status and workflowIds validated");
				}
			}
			else{
				LOGGER.info("The workflow status is not success hence skipping the testcase");
				test.log(LogStatus.SKIP, "The workflow status is not in success state hence skipping the testcase");
			}
		} catch (NoSuchElementException ex) {
			if (workflowPageObject.close.isDisplayed()) {
				workflowPageObject.close.click();
				LOGGER.info("The workflow status is not success");
				throw ex;
			}
		}

	}
}