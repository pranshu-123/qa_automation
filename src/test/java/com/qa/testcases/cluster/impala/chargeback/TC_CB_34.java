package com.qa.testcases.cluster.impala.chargeback;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Birender Kumar
 */

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_34 extends BaseClass {

	/**
	 * Validate the user is able to deselect the filters
	 */
	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the UI should deselect the filters as the clicks on each one of them")
	public void TC_CB_34_verifyICB_UserAbleToDeselectFilters(String clusterId) {
		test = extent.startTest("TC_CB_34_verifyICB_UserAbleToDeselectFilters: " + clusterId,
				"Validate the user is able to deselect the filters.");
		test.assignCategory(" Cluster - Impala Chargeback");

		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ChargebackImpalaPageObject chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
		chargeBackImpala.selectImpalaChargeback();

		// Cluster selected
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);

		// Click on datepicker button
		DatePicker datePicker = new DatePicker(driver);
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
		waitExecuter.sleep(1000);

		if (chargeBackImpala.isTotalNumberOfJobCountHeader()) {
			test.log(LogStatus.PASS, "Jobs count displayed.");
			System.out.println("Jobs count displayed");
			chargeBackImpala.clickOnGroupBySearchBox();
			// chargeBackImpala.verifyNumberOfOptionsInGroupBy();
			int totalOptionInGroupBy = chargeBackImpala.getNumberOfOptionsInGroupBy();
			waitExecuter.sleep(1000);
			System.out.println("Total number of GroupBy Options: " + totalOptionInGroupBy);
			waitExecuter.sleep(1000);
			for (int i = 0; i < totalOptionInGroupBy - 2; i++) {
				if (chargebackImpalaPageObject.firstRowOfTable.size() > 0) {
					chargeBackImpala.click1stRowOfTable();
					waitExecuter.waitUntilPageFullyLoaded();
				} else {
					break;
				}
			}
			chargeBackImpala.deselectGroupByFilters();
			waitExecuter.sleep(1000);
			test.log(LogStatus.PASS, "Validate the user is able to deselect the filters.");

		} else {
			System.out.println("Jobs count displayed is Zero");
			test.log(LogStatus.FAIL, "No Data Available.");

		}

	}
}
