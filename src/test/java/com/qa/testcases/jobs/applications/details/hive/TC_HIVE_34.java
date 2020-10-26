package com.qa.testcases.jobs.applications.details.hive;

import java.util.logging.Logger;
import com.qa.annotations.Marker;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_34 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_HIVE_34.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyHiveAppsArePresent(String clusterId) {
		test = extent.startTest("TC_HIVE_34.verifyHiveAppsArePresent",
				"Verify that All the Hive apps that are run must be listed in the App list page");
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

		// Assert if in all application Hive application are present
		test.log(LogStatus.INFO, "Assert if in all application Hive application are present");
		LOGGER.info("Assert if in all application Hive application are present");
		Assert.assertTrue(allApps.getAllApplicationTypes().contains("Hive"),
				"The list of applications does not contains 'Hive' apps");
		test.log(LogStatus.PASS, "Verified that in all application Hive application are present.");
	}

}
