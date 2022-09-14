package com.qa.testcases.emr.clusters;

import java.util.List;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.scripts.EmrDatePicker;
import com.qa.scripts.emr.clusters.EMRCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_CL_03 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_03.class.getName());

	@Test
	public void TC_CL_03_VerifyClusterDetailTable() {
		test = extent.startTest("TC_CL_03_VerifyClusterDetailTable", "Validate Cluster detail table");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_03_VerifyClusterDetailTable");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDatePicker emrDatePicker = new EmrDatePicker(driver);
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrDatePicker.selectLast6M();
		waitExecuter.sleep(1000);
		List<String> expectedHeaders = emrCluster.fetchClusterDetailsTableHeader();
		String[] actualHeaders = {"ID","Name","Status","Cost(USD)","Start time","Duration","Cluster tags","Action"};
		for(String headers : actualHeaders) {
			Assert.assertTrue(expectedHeaders.contains(headers), headers + ": header not displayed");
		}
		LOGGER.info("All headers validated");
		List<String> clusterIds = emrCluster.retrieveClusterIds();
		emrCluster.validateClusterDetailsTableValue(clusterIds.get(0));
		test.log(LogStatus.PASS, "Cluster details table validated");
		LOGGER.info("Cluster details table validated");
	}

}
