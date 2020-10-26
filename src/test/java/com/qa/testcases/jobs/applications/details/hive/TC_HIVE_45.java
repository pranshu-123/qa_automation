package com.qa.testcases.jobs.applications.details.hive;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_HIVE_45 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_HIVE_45.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void VerifyCopyAppNameID(String clusterId) {
		test = extent.startTest("TC_HIVE_45.VerifyCopyAppNameID",
				"Verify that on hovering over app, user is able to copy app name/id by clicking on '+'");
		test.assignCategory("App Details - Hive");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
		ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
		AllApps allApps = new AllApps(driver);
		DatePicker datePicker = new DatePicker(driver);
		Actions actions = new Actions(driver);

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
		datePicker.selectLastMonth();
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
			if (appType.get(i).trim().toLowerCase().contains(PageConstants.AppTypes.HIVE)) {
				applicationsPageObject.selectOneApplicationType.get(i).click();
				waitExecuter.sleep(2000);
				hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(i).getText()
						.replaceAll("[^\\dA-Za-z ]", "").trim());
				break;
			}
		}
		if (hiveAppCount > 0) {
			// Hive on to the first row
			test.log(LogStatus.INFO, "Hive on to the first row");
			LOGGER.info("Hive on to the first row");
			WebElement type = applicationsPageObject.getTypeFromTable;
			actions.moveToElement(type).perform();
			waitExecuter.sleep(1000);
			String tooltipValue = applicationsPageObject.getTypeFromTable.getAttribute("aria-describedby");
			waitExecuter.sleep(1000);
			// Validate that on hovering the row is highlighted
			test.log(LogStatus.INFO, "Validate that on hovering the row is highlighted");
			LOGGER.info("Validate that on hovering the row is highlighted");
			Assert.assertNotNull(tooltipValue, "On hover the row is not highlighted");
			test.log(LogStatus.PASS, "On hover, the application in table is highlighted");
		}
		else {
			Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
					"The clusterId does not have any application under it and also does not display 'No Data Available' for it"
							+ clusterId);
			test.log(LogStatus.PASS,
					"The clusterId does not have any application under it and 'No Data Available' is displayed");
		}
		// Click on show-all to view all type of applications
		applicationsPageObject.resetButton.click();
		waitExecuter.sleep(2000);
	}

}
