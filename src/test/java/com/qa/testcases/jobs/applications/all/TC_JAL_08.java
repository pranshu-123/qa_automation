package com.qa.testcases.jobs.applications.all;

import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.AllApps
@Marker.All
public class TC_JAL_08 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(TC_JAL_08.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void validateResetFunctionality(String clusterId) {
		test = extent.startTest("TC_JAL_08.validateResetFunctionality",
				"Selection of reset option should reset all the filters which are applied");
		test.assignCategory(" Jobs - Applications");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
		ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		DatePicker datePicker = new DatePicker(driver);
		DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
		AllApps allApps = new AllApps(driver);
		Actions builder = new Actions(driver);

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
		test.log(LogStatus.INFO, "Select last 7 days");
		LOGGER.info("Select last 7 days");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		datePicker.selectLast7Days();
		waitExecuter.sleep(2000);

		// Select cluster
		test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);
		allApps.selectCluster(clusterId);
		waitExecuter.sleep(3000);

		// Get total app counts before applying filter
		String totalCountBeforeFilter = applicationsPageObject.getTotalAppCount.getText();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Get total app counts before applying filter " + totalCountBeforeFilter);
		LOGGER.info("Get total app counts before applying filter " + totalCountBeforeFilter);

		// To apply filter - De-select all application types
		test.log(LogStatus.INFO, "To apply filter - De-select all application types");
		LOGGER.info("To apply filter - De-select all application types");
		allApps.deselectAllAppTypes();
		waitExecuter.sleep(2000);

		// After de-selecting app type check the app counts
		int totalCountAfterFilter = Integer
				.parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
		waitExecuter.sleep(2000);
		test.log(LogStatus.INFO, "After de-selecting app type check the app counts " + totalCountAfterFilter);
		LOGGER.info("After de-selecting app type check the app counts " + totalCountAfterFilter);
		Assert.assertEquals(totalCountAfterFilter, 0,
				"After de-selecting all app types, Showing result does not display '0' but " + totalCountAfterFilter);

		// Click on reset button
		test.log(LogStatus.INFO, "Click on reset button");
		LOGGER.info("Click on reset button");
		applicationsPageObject.resetButton.click();
		waitExecuter.sleep(2000);

		// Select cluster
		test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);
		allApps.selectCluster(clusterId);
		waitExecuter.sleep(3000);

		// Get total app count after clicking on reset button
		String totalCountAfterReset = applicationsPageObject.getTotalAppCount.getText();
		waitExecuter.sleep(2000);
		test.log(LogStatus.INFO, "Get total app count after clicking on reset button " + totalCountAfterReset);
		LOGGER.info("Get total app count after clicking on reset button " + totalCountAfterReset);
		Assert.assertEquals(totalCountBeforeFilter, totalCountAfterReset,
				"Total count of application are not same before and after filter ");
	}
}
