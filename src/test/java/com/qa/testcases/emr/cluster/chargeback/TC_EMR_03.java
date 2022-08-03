package com.qa.testcases.emr.cluster.chargeback;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackEmrPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_03 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_03.class);

	@Test
	public void TC_EMR_03_verifyUserCanSelectCluster() {
		test = extent.startTest("TC_EMR_03_verifyUserCanSelectCluster", "Verify that the user can select a cluster");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);

		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		String clusterName = emrChargeback.selectClusterJobs();
		String selectedClusterName = emrChargeback.retreiveClusterNameFromJobsPage();
		Assert.assertTrue(selectedClusterName.contains(clusterName));
		String url = driver.getCurrentUrl();
		logger.info("New URL is fetched");
		Assert.assertTrue(url.contains("jobs/applications"));
		test.log(LogStatus.PASS,"Selecting a cluster navigates to Job page.");
	}

}
