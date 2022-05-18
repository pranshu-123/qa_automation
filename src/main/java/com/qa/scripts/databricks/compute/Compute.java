package com.qa.scripts.databricks.compute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.pagefactory.databricks.compute.ComputePageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

public class Compute {

	private static final Logger LOGGER = Logger.getLogger(Compute.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ComputePageObject computePageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public Compute(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		computePageObject = new ComputePageObject(driver);
	}	

	public void navigateToCompute() {
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.sleep(1000);
		try {
			computePageObject.compute.click();
			waitExecuter.sleep(1000);
		}
		catch(ElementClickInterceptedException e) {
			LOGGER.info(e.getMessage());
			LOGGER.info("Waiting for page load to complete and retrying....");
			computePageObject.compute.click();
		}
		LOGGER.info("Navigated to Compute Page");
	}

	public String filterByCluster() {
		waitExecuter.sleep(2000);
		String clusterName = computePageObject.clusterName.getText();
		computePageObject.filterByClusterName.sendKeys(clusterName);
		waitExecuter.sleep(1000);
		computePageObject.filterByClusterName.sendKeys(Keys.ENTER);
		LOGGER.info("Filter by "+clusterName+" selected");
		return clusterName;
	}

	public void validateResultSet(String clusterName) {
		waitExecuter.sleep(1000);
		List<String> list = computePageObject.jobRunValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		Assert.assertTrue(list.size()>0);
		LOGGER.info("Result Set data displayed");
		Assert.assertTrue(list.contains(clusterName), "Cluster Name is not displayed");
	}

	public void resetStatuses() {
		computePageObject.reset.click();
	}

	public List<String> selectStatus(String status) {
		String statusCount = null;
		List<String> result = new ArrayList<String>();
		List<String> statuses = computePageObject.statusCheckboxes.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		waitExecuter.sleep(2000);
		for(int i=0;i<statuses.size();i++) {
			String st = computePageObject.statusCheckboxes.get(i).getText();
			if(st.contains(status)) {
				String count = computePageObject.statusCount.get(i).getText();
				statusCount = count.substring(1, count.length()-1);
			}
			if(!st.contains(status)) {
				JavaScriptExecuter.scrollOnElement(driver, computePageObject.statusCheckboxes.get(i));
				computePageObject.statusCheckboxes.get(i).click();
				waitExecuter.sleep(1000);
			}
		}
		result.add(status);
		result.add(statusCount);
		return result;
	}

	public void validateJobDetailsAsPerSelectedStatus(List<String> status) {
		if(TestUtils.isElementDisplayed(computePageObject.noDataAvailable)) {
			LOGGER.info("No data available against selected Job status");
		}
		else {
			Assert.assertTrue(computePageObject.jobRunValues.stream().count()>0);
			Assert.assertTrue(computePageObject.jobRunValues.stream()
					.map(element -> element.getText())
					.collect(Collectors.toList()).contains(status.get(0)));
			JavaScriptExecuter.scrollViewWithYAxis(driver, 500);
			if(TestUtils.isElementDisplayed(computePageObject.pagination.get(1))) {
				JavaScriptExecuter.scrollOnElement(driver, computePageObject.pagination.get(1));
				computePageObject.pagination.get(1).click();
				String pageCount = computePageObject.pageNumber.getText();
				String count = pageCount.substring(pageCount.length()-1, pageCount.length());
				long totalPages = Long.parseLong(count);
				long rowsCount = computePageObject.jobRunValuesRows.stream().count();
				long totalCount = (totalPages-1)*18 + rowsCount;
				Assert.assertEquals(status.get(1), String.valueOf(totalCount));
			}
			LOGGER.info("Result Set data displayed");
		}
	}

	public void validateTabsPresence() {
		Assert.assertTrue(computePageObject.allTab.isDisplayed(), "All Tab not displayed");
		Assert.assertTrue(computePageObject.finishedTab.isDisplayed(), "Finished Tab not displayed");
		Assert.assertTrue(computePageObject.inefficientTab.isDisplayed(), "Inefficient Tab not displayed");
		Assert.assertTrue(computePageObject.runningTab.isDisplayed(), "Running Tab not displayed");
	}

	public void navigateToTabs(String tab) {
		if(tab.equalsIgnoreCase("all")) {
			computePageObject.allTab.click();
		}
		else if(tab.equalsIgnoreCase("finished")) {
			computePageObject.finishedTab.click();
		}
		else if (tab.equalsIgnoreCase("inefficient")) {
			computePageObject.inefficientTab.click();
		}
		else {
			computePageObject.runningTab.click();
		}
	}

	public List<String> returnJobStatustes(String tab){
		if(tab.equalsIgnoreCase("all")) {
			computePageObject.allTab.click();
		}
		else if(tab.equalsIgnoreCase("finished")) {
			computePageObject.finishedTab.click();
		}
		else if (tab.equalsIgnoreCase("inefficient")) {
			computePageObject.inefficientTab.click();
		}
		else {
			computePageObject.runningTab.click();
		}
		List<String> statuses = computePageObject.statusCheckboxes.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		LOGGER.info("Following Job Statuses are displayed: "+statuses);
		return statuses;
	}

	public void selectSettings() {
		waitExecuter.sleep(1000);
		waitExecuter.waitUntilElementClickable(computePageObject.settings);
		computePageObject.settings.click();
	}

	public List<String> returnSettingsOption() {
		return computePageObject.settingsCheckboxes.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
	}

	public void verifyColumnHeaders(String setting) {
		for(WebElement element : computePageObject.settingsCheckboxes) {
			if(computePageObject.settingsCheckboxes.stream()
					.map(a-> a.getText().equalsIgnoreCase(setting)) != null) {
				element.click();
				break;
			}
		}
		Assert.assertFalse(
				computePageObject.jobRunHeaders.stream()
				.map(element -> element.getText())
				.collect(Collectors.toList()).contains(setting));
	}

	public void selectSpark() {
		waitExecuter.sleep(1000);
		computePageObject.sparkBtn.get(0).click();
	}

	public String filterByWorkspace() {
		computePageObject.workspaceSearchBox.click();
		computePageObject.selectWorkspace.click();
		waitExecuter.sleep(1000);
		return computePageObject.filteredChoice.getText();
	}

	public String filterByUser() {
		computePageObject.userSearchBox.click();
		computePageObject.selectWorkspace.click();
		waitExecuter.sleep(1000);
		return computePageObject.filteredChoice.getText();
	}

	public void validateFilteredRows(String choice) {
		Set<String> resultSet = new HashSet<String>();
		for(WebElement el: computePageObject.filteredResultRows) {
			resultSet.add(el.getAttribute("title"));

		}
		Assert.assertTrue(resultSet.contains(choice),"Filter not applied successfully");
	}

	public String invalidSearchParametersValidation(String type) {
		String errMsg ="No Data";
		if(type.equalsIgnoreCase("workspace")) {
			computePageObject.workspaceSearchBox.sendKeys("abc");
			errMsg = computePageObject.selectWorkspace.getText();
		}
		else if(type.equalsIgnoreCase("user")) {
			computePageObject.userSearchBox.sendKeys("abc");
			errMsg = computePageObject.selectWorkspace.getText();
		}
		else if(type.equalsIgnoreCase("cluster")) {
			computePageObject.clusterSearchBox.sendKeys("abc");
			errMsg = computePageObject.selectWorkspace.getText();
		}
		LOGGER.info("Search parameter is invalid: "+errMsg);
		return errMsg;
	}

	public void selectTags(String tagKey) {
		int position = 0;
		List<String> keys = computePageObject.tagKeyLabels.stream()
				.map(k -> k.getText())
				.collect(Collectors.toList());
		for(String key:keys) {
			if(key.equalsIgnoreCase(tagKey)) {
				JavaScriptExecuter.scrollOnElement(driver, computePageObject.tagKeyLabels.get(position));
				computePageObject.tagKeyLabels.get(position).click();
				break;
			}
			position++;
		}
		LOGGER.info(tagKey+  ": Key selected");
	}


	public void selectTagKey() {
		computePageObject.tagKeySearchBox.click();
		computePageObject.tagKeyValue.click();
		LOGGER.info("Tag Key selected");
	}

	public void paginateToNextLastPage(String navigation) {
		JavaScriptExecuter.scrollOnElement(driver, computePageObject.pagination.get(0));
		if(navigation.equalsIgnoreCase("next")) {
			computePageObject.pagination.get(0).click();
		}
		else {
			computePageObject.pagination.get(1).click();
		}
	}

	public void performGlobalSearch(String search) {
		computePageObject.globalSearch.sendKeys(search);
		computePageObject.globalSearch.sendKeys(Keys.ENTER);
	}

	public void validateGlobalSearchResult() {
		Assert.assertTrue(computePageObject.globalSearchResult.stream().count()>0,"Global Search does not show any data");
		LOGGER.info("Result Set data displayed");
	}

	public void validateSorting(String sortOn) {
		Set<String> initialSet = new HashSet<String>();
		Set<String> resultantSet = new HashSet<String>();
		TreeSet<String> sort = null;
		int size = computePageObject.jobRunResults.size();
		int startCounter = 0;
		if(sortOn.equalsIgnoreCase("status")) {
			startCounter = 0;
		}
		else if(sortOn.equalsIgnoreCase("cost")) {
			startCounter = 7;
		}
		else if(sortOn.equalsIgnoreCase("cluster")) {
			startCounter = 1;
		}
		else if(sortOn.equalsIgnoreCase("Cluster Type")) {
			startCounter = 2;
		}
		else {
			startCounter = 6;	
		}
		for(int i =startCounter; i< size;i=i+10) {
			initialSet.add(computePageObject.jobRunResults.get(i).getText());
		}
		LOGGER.info("Data captured not in order");
		sort = new TreeSet<String>(initialSet);
		driver.findElement(By.xpath(String.format(computePageObject.sortType, sortOn))).click();
		waitExecuter.sleep(1000);
		for(int i =startCounter; i< size;i=i+10) {
			resultantSet.add(computePageObject.jobRunResults.get(i).getText());
		}
		Assert.assertNotEquals(resultantSet, sort,"Data not sorted");
	}

	public void validateJobStatus(String status) {
		int size = computePageObject.jobRunStatus.size();
		Set<String> set = new HashSet<String>();
		for(int i = 0; i< size;i++) {
			set.add(computePageObject.jobRunStatus.get(i).getText());
		}
		Assert.assertTrue(set.contains(status),"Job Tab contains another jobs");
		Assert.assertTrue(set.size()==1);
	}
	public String returnSelectedTags() {
		return computePageObject.filteredChoice.getText();
	}

	public String filterByClusterName() {
		computePageObject.clusterSearchBox.click();
		computePageObject.selectWorkspace.click();
		waitExecuter.sleep(1000);
		return computePageObject.filteredChoice.getText();
	}
}
