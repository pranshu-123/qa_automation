package com.qa.testcases.emr.cluster.chargeback;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.clusters.ChargebackEmrPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_02 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_02.class);

	@Test
	public void TC_EMR_02_VerifyClusterList() {
		test = extent.startTest("TC_EMR_02_VerifyClusterList", "Verify the UI should list all the clusters connected to Unravel");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		ChargebackEmrPageObject emrPageObject = new ChargebackEmrPageObject(driver);

		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		emrChargeback.validateEmrChargebackHomePage();

		List<WebElement> listOfClusters = emrPageObject.resultsGroupedByTableClusterRows;
		waitExecuter.sleep(1000);
		//Verify that cluster list is not empty
		Assert.assertTrue(listOfClusters.size() > 0, "Cluster list is empty");
		test.log(LogStatus.PASS, "Verified that the cluster list is not empty. The size is: "
				+ listOfClusters.size());
	}

}
