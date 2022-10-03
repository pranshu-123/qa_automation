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

public class TC_CL_13 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_13.class.getName());

	@Test
	public void TC_CL_13_validateEventCountOnInefficientClusterPage() {
		test = extent.startTest("TC_CL_13_validateEventCountOnInefficientClusterPage", "Validate Events count from Inefficient page should match with total terminated jobs");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_13_validateEventCountOnInefficientClusterPage");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDatePicker emrDatePicker = new EmrDatePicker(driver);
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrDatePicker.selectLast6M();
		waitExecuter.sleep(1000);
		emrCluster.selectClusterTabs("Inefficient");
		String nodeDownsizingCount = emrCluster.retrieveNodeDownsizingCount();
		LOGGER.info("Events count fetched");
		String clusterCount = emrCluster.retrieveClusterCount();
		LOGGER.info("Cluster Count fetched");
		Assert.assertEquals(nodeDownsizingCount, clusterCount);
		test.log(LogStatus.PASS, "Event count matches with cluster count");
		LOGGER.info("Event count matches with cluster count");
	}

}
