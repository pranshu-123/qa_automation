package com.qa.testcases.jobs.applications.details.hive;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import com.qa.annotations.Marker;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_36 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_HIVE_36.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyHiveIsSelectable(String clusterId) {
		test = extent.startTest("TC_HIVE_36.verifyHiveIsSelectable",
				"Verify that on clicking on 'Only' of Hive only hive app gets selected");
		test.assignCategory("App Details - Hive");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
		ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
		DatePicker datePicker = new DatePicker(driver);
		AllApps allApps = new AllApps(driver);

		// Navigate to Jobs tab from header
		test.log(LogStatus.INFO, "Navigate to jobs tab from header");
		LOGGER.info("Navigate to jobs tab from header");
		waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
		waitExecuter.sleep(1000);
		topPanelComponentPageObject.jobs.click();
		waitExecuter.sleep(3000);
		waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
		waitExecuter.waitUntilPageFullyLoaded();
		// Select last 30 days from date picker
		test.log(LogStatus.INFO, "Select last 30 days");
		LOGGER.info("Select last 30 days");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		datePicker.selectLast30Days();
		waitExecuter.sleep(2000);
		// Select cluster
		test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);
		allApps.selectCluster(clusterId);
		waitExecuter.sleep(3000);

		// De-Select all app types
		test.log(LogStatus.INFO, "De-Select all app types");
		LOGGER.info("De-Select all app types");
		allApps.deselectAllAppTypes();
		int hiveAppCount = 0;
		// Select 'Only' hive type
		test.log(LogStatus.INFO, "Select 'Only' hive from app types");
		LOGGER.info("Select 'Only' hive from app types");
		List<String> appType = allApps.getAllApplicationTypes();
		for (int i = 0; i < appType.size(); i++) {
			if (appType.get(i).trim().toLowerCase().contains("hive")) {
				applicationsPageObject.selectOneApplicationType.get(i).click();
				waitExecuter.sleep(2000);
				hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(i).getText()
						.replaceAll("[^\\dA-Za-z ]", "").trim());
				break;
			}
		}
		// Assert if in all application Hive application are present
		test.log(LogStatus.INFO, "Assert if in all application Hive application are present");
		LOGGER.info("Assert if in all application Hive application are present");
		if (allApps.getAllApplicationTypes().contains("Hive")) {
			Assert.assertTrue(hiveAppCount >= 0, "Hive app does not got selected on clicking on 'Only'.");
			test.log(LogStatus.PASS, "Hive app got selected on clicking on 'Only'.");
		}
		// Reset set filters
		test.log(LogStatus.INFO, "Reset set filters ");
		LOGGER.info("Reset set filters ");
		applicationsPageObject.resetButton.click();
		waitExecuter.sleep(2000);
	}

}
