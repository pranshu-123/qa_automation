package com.qa.testcases.emr.clusters;

import java.util.List;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.scripts.EmrDatePicker;
import com.qa.scripts.emr.clusters.EMRCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_CL_12 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_12.class.getName());

	@Test
	public void TC_CL_12_validateClusterJobPage() {
		test = extent.startTest("TC_CL_12_validateClusterJobPage", "Validate Jobs page from selected cluster");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_12_validateClusterJobPage");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDatePicker emrDatePicker = new EmrDatePicker(driver);
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrDatePicker.selectLast6M();
		waitExecuter.sleep(1000);
		List<String> clusterId = emrCluster.retrieveClusterIds();
		emrCluster.selectJobs();
		LOGGER.info("View Jobs selected");
		emrCluster.navigateToSecondaryPage();
		emrCluster.validateJobsPage(clusterId.get(0));
		test.log(LogStatus.PASS, "Selected cluster job is visible on Jobs page");
		LOGGER.info("Selected cluster job is visible on Jobs page");
	}

}
