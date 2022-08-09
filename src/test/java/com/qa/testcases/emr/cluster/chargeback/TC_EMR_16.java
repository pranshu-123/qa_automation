package com.qa.testcases.emr.cluster.chargeback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_16 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_16.class);

	@Test
	public void TC_EMR_16_verifyAvgCostFromInsights() {
		test = extent.startTest("TC_EMR_16_verifyAvgCostFromInsights", "Verify the Avg Cost displayed in the chargeback report");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		emrChargeback.selectClusterChargeback();
		String type = "AVG. Cost";
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		String cost = 	emrChargeback.fetchResultSetValues(type);
		emrChargeback.selectInsights();
		logger.info("Insight selected from chargeback page", test);
		waitExecuter.sleep(4000);
		emrChargeback.switchToNewTab();
		String url = driver.getCurrentUrl();
		logger.info("Navigated to insight page: "+ url, test);
		String avgCost = clusterInsights.retrieveClusterCostValues("Total Cost");
		Assert.assertEquals(cost, avgCost);
		test.log(LogStatus.PASS,"Cost on Chargeback page and Insights page are matching.");
	}

}