package com.qa.testcases.emr.cluster.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterinsights
public class TC_CI_14  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_14.class);

	@Test
	public void TC_CI_14_validateClusterRecommendation() {
		test = extent.startTest("TC_CI_14_validateClusterRecommendation",
				"Validate Recommendation section");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_14_validateClusterRecommendation");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		DatePicker datePicker = new DatePicker(driver);
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		clusterInsights.validateInsightsDetails();
		clusterInsights.validateClusterInsightCostWithTotalCost();
		test.log(LogStatus.PASS, "Recommendation section contains details of instances and cost of master and core");
		logger.info("Recommendation section contains details of instances and cost of master and core");
	}
}
