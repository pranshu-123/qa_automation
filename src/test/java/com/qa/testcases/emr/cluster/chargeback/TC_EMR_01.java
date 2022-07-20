package com.qa.testcases.emr.cluster.chargeback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_01 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_01.class);

	@Test
	public void TC_EMR_01_VerifyEMRChargeBackPage() {
		test = extent.startTest("TC_EMR_01_VerifyEMRChargeBackPage", "Validate Cluster Insights landing page");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		emrChargeback.validateEmrChargebackHomePage();

		Assert.assertTrue(driver.getCurrentUrl().contains("clusters/chargeback"), "User is not " +
				"directed to the cluster chargeback page.");
		test.log(LogStatus.PASS, "User is redirected to to cluster chargeback page");

		Assert.assertEquals(emrChargeback.getHeading(), PageConstants.Clusters.CHARGEBACK_EMR_HEADING,
				"Chargeback EMR is not displayed as page heading.");

		test.log(LogStatus.PASS, "Chargeback EMR is displayed as page heading.");
	}

}
