package com.qa.testcases.emr.cluster.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterinsights
public class TC_CI_09  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_09.class);

	@Test
	public void TC_CI_09_validateSortAsPerEMRCost() {
		test = extent.startTest("TC_CI_09_validateSortAsPerEMRCost",
				"Sort the result as per EMR cost");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_09_validateSortAsPerEMRCost");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		clusterInsights.validateResultSet();
		clusterInsights.validateSorting("EMR Cost");
		test.log(LogStatus.PASS, "Sorting test as per cluster emr cost was successful");
		logger.info("Sorting test as per cluster emr cost was successful");
	}
}
