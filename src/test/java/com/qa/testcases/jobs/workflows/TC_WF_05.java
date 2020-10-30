package com.qa.testcases.jobs.workflows;

import com.qa.base.BaseClass;
import com.qa.scripts.jobs.applications.Jobs;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
public class TC_WF_05 extends BaseClass {
    private LoggingUtils logger = new LoggingUtils(TC_WF_05.class);
    /**
     * Verify MR workflow details in Unravel UI.
     * @param clusterId - ClusterId to execute test cases on.
     */
    @Test
    public void verifyMRWorkflowDetails(String clusterId) {
        test = extent.startTest("verifyMRWorkflowDetails", "Verify MR workflow details in Unravel UI.");
        test.assignCategory("Workflow Page");
        logger.info("Click on Jobs Pipeline tab", test);
        Jobs jobs = new Jobs(driver);
        jobs.clickOnJobsPipelines();
    }
}
