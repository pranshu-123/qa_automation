package com.qa.testcases.emr.cluster.chargeback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_07 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_07.class);

	@Test
	public void TC_EMR_07_verifyGroupByOption() {
		test = extent.startTest("TC_EMR_07_verifyGroupByOption", "Verify that the Grouped by drop-down box displays all the filters.");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		waitExecuter.sleep(2000);
		emrChargeback.selectGroupBySearchBox();
		Assert.assertTrue(emrChargeback.retrieveGroupByList().size()>0,"Group By list is empty");
		test.log(LogStatus.PASS,"Grouped by drop-down box displays all the filters");
	}

}
