package com.qa.testcases.emr.cluster.jobtrends;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.ClusterJobTrends;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterjobtrends
public class TC_CJT_16  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CJT_16.class.getName());

	@Test
	public void TC_CJT_16_VerifyEmptyGraph() {
		test = extent.startTest("TC_CJT_16_VerifyEmptyGraph", "Validate Empty Graph generated when all filters are unselected");
		test.assignCategory("EMR/ClusterJobTrends");
		Log.startTestCase("TC_CJT_16_VerifyEmptyGraph");
		ClusterJobTrends clusterJobTrends = new ClusterJobTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		clusterJobTrends.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLastOneHour();
		waitExecuter.sleep(4000);
		clusterJobTrends.selectCluster();
		clusterJobTrends.validateEmptyGraphData();
		test.log(LogStatus.PASS, "Empty Graph validated");
		LOGGER.info("Empty Graph validated");
	}
}
