package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_52 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_HIVE_52.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void VerifyHiveAppsWithTezTags(String clusterId) {
		test = extent.startTest("TC_HIVE_52.VerifyHiveAppsWithTezTags",
				"Verify that Hive apps that are tagged with TEZ should have parent app linked as Tez in \"parent app\" column");
		test.assignCategory("App Details - Hive");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
		ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
		AllApps allApps = new AllApps(driver);
		DatePicker datePicker = new DatePicker(driver);
		SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
		UserActions userAction = new UserActions(driver);
		// Navigate to Jobs tab from header
		test.log(LogStatus.INFO, "Navigate to jobs tab from header");
		LOGGER.info("Navigate to jobs tab from header");
		waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
		userAction.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
		waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
		waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
		waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
		waitExecuter.waitUntilPageFullyLoaded();
		// Select last 30 days from date picker
		test.log(LogStatus.INFO, "Select last 30 days");
		LOGGER.info("Select last 30 days");
		datePicker.clickOnDatePicker();
		waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
		datePicker.selectLastOneHour();
		waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
		// Select cluster
		test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);
		allApps.selectCluster(clusterId);
		waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
		// Select 'Only' hive type and get its jobs count
		test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
		LOGGER.info("Select 'Only' hive from app types and get its jobs count");
		//sparkApp.clickOnlyLink("Tez");
		sparkApp.clickOnlyLink("Hive");
		int appCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
				.replaceAll("[^\\dA-Za-z ]", "").trim());
		List<Integer> list = new ArrayList<>();
		if (appCount > 0) {
			sort: for (int i = 0; i < 2; i++) {
				// Sort by parent app
				test.log(LogStatus.INFO, "Sort by parent app");
				LOGGER.info("Sort by parent app");
				waitExecuter.waitUntilElementClickable(applicationsPageObject.sortByParentApp);
				applicationsPageObject.sortByParentApp.click();
				waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
				list.add(applicationsPageObject.checkTezInParentApp.size());
				waitExecuter.sleep(1000);

				for (int value : list) {
					// Assert that hive tag is present in parent app
					test.log(LogStatus.INFO, "Assert that hive tag is present in parent app");
					LOGGER.info("Assert that hive tag is present in parent app");
					if (value > 0) {
						Assert.assertTrue(value > 0, "There is no Hive as Parent App in Tez jobs");
						test.log(LogStatus.PASS, "Hive is present as Parent App in Tez jobs");
						break sort;
					} else
						LOGGER.info("There is no Hive as Parent App in Tez jobs");
						test.log(LogStatus.SKIP, "There is no Hive as Parent App in Tez jobs");
				}
			}
			// Click on reset
			test.log(LogStatus.INFO, "Click on reset");
			LOGGER.info("Click on reset");
			allApps.reset();
		} else {
			Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
					"The clusterId does not have any application under it and also does not display 'No Data Available' for it"
							+ clusterId);
			test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
			waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
			// Click on reset if there are no hive apps
			test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
			LOGGER.info("Click on reset if there are no hive apps");
			allApps.reset();
		}
	}
}
