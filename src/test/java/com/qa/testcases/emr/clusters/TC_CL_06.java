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

public class TC_CL_06 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_06.class.getName());

	@Test
	public void TC_CL_06_FilterClusterAsPerActiveStatus() {
		test = extent.startTest("TC_CL_06_FilterClusterAsPerActiveStatus", "Filter cluster as per the Active Cluster Status");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_06_FilterClusterAsPerActiveStatus");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDatePicker emrDatePicker = new EmrDatePicker(driver);
		String expectedStatus = "WAITING";
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrDatePicker.selectLast6M();
		waitExecuter.sleep(1000);
		emrCluster.selectStatus("Active");
		emrCluster.validateClusterStatus(expectedStatus);
		test.log(LogStatus.PASS, "Filtering cluster as per the Active Status validated");
		LOGGER.info("Filtering cluster as per the Active Status validated");
	}

}
