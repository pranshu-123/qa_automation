package com.qa.testcases.emr.clusters;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.scripts.EmrDatePicker;
import com.qa.scripts.emr.clusters.EMRCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_CL_11 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_11.class.getName());

	@Test
	public void TC_CL_11_FilterClusterNotAsUnravelType() {
		test = extent.startTest("TC_CL_11_FilterClusterNotAsUnravelType", "Validate clusters as per selected Installation - Clusters Without Unravel");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_11_FilterClusterNotAsUnravelType");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		EmrDatePicker emrDatePicker = new EmrDatePicker(driver);
		String type = "without";
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrDatePicker.selectLast6M();
		waitExecuter.sleep(1000);
		emrCluster.selectInstallationType(type);
		LOGGER.info("Only non-unravel cluster selected");
		emrCluster.validateWithWithoutUnravelClustersCount();
		test.log(LogStatus.PASS, "Validated clusters as per selected Installation - Clusters Without Unravel");
		LOGGER.info("Validated clusters as per selected Installation - Clusters Without Unravel");
	}

}
