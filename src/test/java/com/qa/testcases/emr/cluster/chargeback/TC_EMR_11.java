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
public class TC_EMR_11 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_11.class);

	@Test
	public void TC_EMR_11_verifyChargebackInsights() {
		test = extent.startTest("TC_EMR_11_verifyChargebackInsights", "Verify Insights page should be accessible from Chargeback");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		emrChargeback.selectInsights();
		logger.info("Insight selected from chargeback page", test);
		waitExecuter.sleep(2000);
		emrChargeback.switchToNewTab();
		String url = driver.getCurrentUrl();
		logger.info("Navigated to insight page: "+ url, test);
		Assert.assertTrue(url.contains("/clusters/emrclusterinsights"));
		clusterInsights.validateLandingPage();
		test.log(LogStatus.PASS,"Insights page accessible from Chargeback");
	}

}
