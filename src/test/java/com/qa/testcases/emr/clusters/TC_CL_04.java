package com.qa.testcases.emr.clusters;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.scripts.EmrDatePicker;
import com.qa.scripts.emr.clusters.EMRCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_CL_04 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_04.class.getName());

	@Test
	public void TC_CL_04_FilterClusterAsPerId() {
		test = extent.startTest("TC_CL_04_FilterClusterAsPerId", "Filter cluster as per the cluster Id");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_04_FilterClusterAsPerId");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDatePicker emrDatePicker = new EmrDatePicker(driver);
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrDatePicker.selectLast6M();
		waitExecuter.sleep(1000);
		String id = emrCluster.filterByClusterId();
		emrCluster.validateClusterDetailsTableValue(id);
		test.log(LogStatus.PASS, "Filter cluster as per the cluster Id");
		LOGGER.info("Filter cluster as per the cluster Id");
	}

}
