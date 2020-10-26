package com.qa.testcases.jobs.applications.details.hive;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class TC_HIVE_40_PART1 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_HIVE_40_PART1.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void VerifyFilterByUserQueue(String clusterId) {
		test = extent.startTest("TC_HIVE_40_PART1.VerifyFilterByUserQueue",
				"Verify that User is able to see the apps sorted based on Users and Queue.");
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
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		// Navigate to Jobs tab from header
		test.log(LogStatus.INFO, "Navigate to jobs tab from header");
		LOGGER.info("Navigate to jobs tab from header");
		waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
		waitExecuter.sleep(1000);
		topPanelComponentPageObject.jobs.click();
		waitExecuter.sleep(3000);
		waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
		waitExecuter.waitUntilPageFullyLoaded();

		// Select last 7 days from date picker
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

		// Get 1st username from table for hive apps
		String filterByUsername = applicationsPageObject.getUsernameFromTable.getText().trim();
		LOGGER.info("User name should be filtered by- " + filterByUsername);
		// Get 1st queuename from table for hive apps
		String filterByQueue = applicationsPageObject.getQueueNameTable.getText().trim();
		LOGGER.info("Queue name should be filtered by- " + filterByQueue);
		waitExecuter.sleep(2000);
		// Click on user searchbox and get all usernames.
		test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
		LOGGER.info("Click on user searchbox and get all usernames.");
		executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
		applicationsPageObject.userSearchBox.click();
		waitExecuter.sleep(1000);
		applicationsPageObject.userSearchBox.sendKeys(filterByUsername);
		waitExecuter.sleep(1000);
		List<WebElement> userList = applicationsPageObject.getNamesFromDropDown;
		String usernameSelected = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getText().equals(filterByUsername)) {
				usernameSelected = userList.get(i).getText();
				LOGGER.info("Selected username from dropdown " + usernameSelected);
				userList.get(i).click();
				waitExecuter.sleep(2000);
				break;
			}
		}

		applicationsPageObject.queueNameSearchBox.click();
		waitExecuter.sleep(1000);
		applicationsPageObject.queueNameSearchBox.sendKeys(filterByQueue);
		waitExecuter.sleep(1000);
		List<WebElement> queueList = applicationsPageObject.getNamesFromDropDown;
		String queuenameSelected = null;
		for (int i = 0; i < queueList.size(); i++) {
			if (queueList.get(i).getText().equals(filterByQueue)) {
				queuenameSelected = queueList.get(i).getText();
				LOGGER.info("Selected username from dropdown " + queuenameSelected);
				queueList.get(i).click();
				waitExecuter.sleep(2000);
				break;
			}
		}

		executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.jobsPageHeader);
		waitExecuter.sleep(2000);

		int totalCount = Integer
				.parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
		waitExecuter.sleep(2000);

		if (totalCount > 0) {
			String usernameFromTable = applicationsPageObject.getUsernameFromTable.getAttribute("title");
			LOGGER.info("Username displayed in table " + usernameFromTable);
			Assert.assertEquals(usernameFromTable, usernameSelected,
					"The application in table contains username other than that of " + usernameFromTable);
		}

		else
			Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
					"The clusterId does not have any application under it and also does not display 'No Data Available' for it");

		waitExecuter.sleep(2000);
		applicationsPageObject.userSearchBox.click();
		waitExecuter.sleep(2000);
		userList.get(0).click();
		waitExecuter.sleep(2000);

		// Reset username filter to default
		test.log(LogStatus.INFO, "Reset username filter");
		LOGGER.info("Reset username filter");
		waitExecuter.sleep(2000);
		applicationsPageObject.resetButton.click();
		waitExecuter.sleep(3000);

	}
}
