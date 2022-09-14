package com.qa.testcases.emr.clusters;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.scripts.emr.clusters.EMRCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_CL_01 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CL_01.class.getName());

	@Test
	public void TC_CL_01_VerifyLandingPage() {
		test = extent.startTest("TC_CL_01_VerifyLandingPage", "Validate Cluster landing page");
		test.assignCategory("EMR Cluster");
		Log.startTestCase("TC_CL_01_VerifyLandingPage");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		EMRCluster emrCluster = new EMRCluster(driver);
		emrCluster.selectCluster();
		waitExecuter.sleep(1000);
		emrCluster.validateHomePage();
		test.log(LogStatus.PASS, "Homepage validated against all the filters");
		LOGGER.info("Homepage validated against all the filters");
	}

}
