package com.qa.testcases.emr.cluster.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterinsights
public class TC_CI_06  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_06.class);

	@Test
	public void TC_CI_06_validateIncorrectSearchFunctionality() {
		test = extent.startTest("TC_CI_06_validateIncorrectSearchFunctionality",
				"Search as per Status");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_06_validateIncorrectSearchFunctionality");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		clusterInsights.validateResultSet();
		String msg = clusterInsights.enterIncorrectSearchParam();
		Assert.assertTrue(msg.contains("No data to display"));
		test.log(LogStatus.PASS, "Incorrect Cluster search validated");
		logger.info("Incorrect Cluster search validated");
	}
}
