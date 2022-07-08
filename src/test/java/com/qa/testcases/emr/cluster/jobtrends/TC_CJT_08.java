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
public class TC_CJT_08  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CJT_08.class.getName());

	@Test
	public void TC_CJT_08_VerifyTezGraphForGroupByApplicationType() {
		test = extent.startTest("TC_CJT_08_VerifyTezGraphForGroupByApplicationType", "Validate Tez Graph for Group By Application Type filter");
		test.assignCategory("EMR/ClusterJobTrends");
		Log.startTestCase("TC_CJT_08_VerifyTezGraphForGroupByApplicationType");
		ClusterJobTrends clusterJobTrends = new ClusterJobTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		String groupBy = "Application Type";
		String filterType = "Tez";
		clusterJobTrends.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		clusterJobTrends.filterByGroup(groupBy);
		LOGGER.info("Job Trends filtered as per State group");
		clusterJobTrends.validateGraphs(filterType);
		test.log(LogStatus.PASS, filterType+" graph validated for group by state filter");
		LOGGER.info(filterType+" graph validated for group by state filter");
	}
}
