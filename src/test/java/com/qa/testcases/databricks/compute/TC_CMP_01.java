package com.qa.testcases.databricks.compute;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_01 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_01.class.getName());

	@Test
	public void TC_Compute_01_validateFilterByClusterName() {
		test = extent.startTest("TC_Compute_01_validateFilterByClusterName", "Verify \"Filter By Cluster Name\" filters available");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_01_validateFilterByClusterName");
		Compute compute = new Compute(driver);
		compute.navigateToCompute();
		String clusterName = compute.filterByCluster();
		compute.validateResultSet(clusterName);
		test.log(LogStatus.PASS, "Filtered functionality validated.");
		LOGGER.info("Filtered functionality validated.");
	}
}
