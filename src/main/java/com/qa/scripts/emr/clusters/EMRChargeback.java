package com.qa.scripts.emr.clusters;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.ChargebackEmrPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;

public class EMRChargeback {

	private static final Logger LOGGER = Logger.getLogger(EMRChargeback.class.getName());
	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ChargebackEmrPageObject chargebackEmrPageObject;
	private final UserActions userActions;

	/**
	 * Constructer to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public EMRChargeback(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		chargebackEmrPageObject = new ChargebackEmrPageObject(driver);
		userActions = new UserActions(driver);
	}

	/**
	 * This method used to select EMR in chargeback drowdown displayed at
	 * chargeback page. First it click on chargeback tab which navigates to
	 * chargeback page then it select EMR.
	 */
	public void selectClusterChargeback() {
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		waitExecuter.waitUntilElementClickable(chargebackEmrPageObject.clusterChargeBackTab);
		userActions.performActionWithPolling(chargebackEmrPageObject.clusterChargeBackTab, UserAction.CLICK);
		waitExecuter.waitUntilPageFullyLoaded();
	}

	public void selectEmrType() {
		chargebackEmrPageObject.chargebackTypeDropdownOption.click();
		chargebackEmrPageObject.selectType.get(0).click();
	}

	/**
	 * This method is use to return all results grouped by user table rows.
	 *
	 * @return - results grouped by user table rows
	 */
	public List<String> getResultsGroupedByTableRecords() {
		return chargebackEmrPageObject.resultsGroupedByTableRecordsRows
				.stream().map(el -> el.getText())
				.collect(Collectors.toList());
	}


	/**
	 * @return Heading of the page
	 */
	public String getHeading() {
		return chargebackEmrPageObject.chargeBackPageHeading.getText();
	}

	public void selectInsights() {
		int size = chargebackEmrPageObject.resultsGroupedByTableRecordsInsights.size();
		JavaScriptExecuter.scrollOnElement(driver, chargebackEmrPageObject.resultsGroupedByTableRecordsInsights.get(size-1));
		chargebackEmrPageObject.resultsGroupedByTableRecordsInsights.get(size-1).click();
	}

	public void selectGroupBySearchBox() {
		chargebackEmrPageObject.groupBySearchBox.click();
	}

	public List<String> retrieveGroupByList(){
		return chargebackEmrPageObject.groupByList.stream()
				.map( el -> el.getText()).collect(Collectors.toList());
	}


	public String retreiveErrorMessage() {
		return chargebackEmrPageObject.selectOnly1Message.getText();
	}

	/**
	 * Validate whether group by displayed for pie charts
	 */
	public void validateGroupByPieCharts() {
		for (WebElement element : chargebackEmrPageObject.pieChartGroupBySearchBoxs) {
			Boolean isGroupingDisplayed = false;
			waitExecuter.sleep(2000);
			if (!element.getText().equalsIgnoreCase("")) {
				isGroupingDisplayed = true;
			}
			Assert.assertTrue(isGroupingDisplayed, "Group by option not displayed for pie chart");
		}
	}

	public void selectDownloadMenu(String type) {
		if(type=="Chargeback Report") {
			chargebackEmrPageObject.downloadCSVFile.get(0).click();
		}
		else {
			chargebackEmrPageObject.downloadCSVFile.get(1).click();
		}
	}

	public void downloadAvgCostPieCharts(String type) {
		waitExecuter.sleep(2000);
		chargebackEmrPageObject.avgCostDownloadMenu.click();
		driver.findElement(By.xpath(String.format(chargebackEmrPageObject.downloadTotalAvgCostFile, type))).click();
	}

	public void downloadClusterPieCharts(String type) {
		waitExecuter.sleep(2000);
		chargebackEmrPageObject.clusterDownloadMenu.click();
		driver.findElement(By.xpath(String.format(chargebackEmrPageObject.downloadClusterFile, type))).click();
	}

	public void downloadTotalCostPieCharts(String type) {
		waitExecuter.sleep(2000);
		chargebackEmrPageObject.totalCostDownloadMenu.click();
		driver.findElement(By.xpath(String.format(chargebackEmrPageObject.downloadTotalCostFile, type))).click();
	}

	public void validateEmrChargebackHomePage() {
		validateGroupByPieCharts();
		chargebackEmrPageObject.resultsGroupedByTableRecordsRows.stream()
		.map(rows -> rows.isDisplayed());
		chargebackEmrPageObject.resultsGroupedByTableRecordsInsights.stream()
		.map(rows -> rows.isDisplayed());
	}

	public String fetchResultSetValues(String parameter) {
		int rows = chargebackEmrPageObject.resultsGroupedByTableRecordsRows.size();
		String value ="";
		List<WebElement> headingToBeClicked = chargebackEmrPageObject.resultsGroupedByTableRecordsValues
				.findElements(By.xpath("//tr[" + (rows) + "]/td/span"));
		switch(parameter)
		{
		case "EMR_Release":
			value = headingToBeClicked.get(0).getText();
			break;

		case "Clusters":
			value = headingToBeClicked.get(1).getText();
			break;

		case "EC2 Cost":
			value = headingToBeClicked.get(2).getText();
			break;
		case "EMR Cost":
			value = headingToBeClicked.get(3).getText();
			break;
		case "EBS Cost":
			value = headingToBeClicked.get(4).getText();
			break;
		case "AVG. Cost":
			value = headingToBeClicked.get(5).getText();
			break;
		case "Total Cost":
			value = headingToBeClicked.get(7).getText();
			break;
		}
		return value;
	}

	public String retreiveNoDataMsg() {
		return chargebackEmrPageObject.jobsNoDataAvailableText.getText();
	}

	public void searchChargebackReport(String report) {
		chargebackEmrPageObject.chargebackSearchTextBox.sendKeys(report);
	}

	public void searchClusterReport(String report) {
		chargebackEmrPageObject.clusterSearchTextBox.sendKeys(report);
	}

	public String selectClusterJobs() {
		int totalList = chargebackEmrPageObject.clusterJobs.size();
		String cluster = chargebackEmrPageObject.clusterName.get(0).getText();
		JavaScriptExecuter.scrollOnElement(driver, chargebackEmrPageObject.clusterJobs.get(totalList-1));
		waitExecuter.sleep(2000);
		chargebackEmrPageObject.clusterJobs.get(totalList-1).click();
		return cluster;
	}

	public void switchToNewTab() {
		Set<String> handles = driver.getWindowHandles();
		System.out.println("Handles"+handles);

		String parentHandle = driver.getWindowHandle();
		System.out.println("parentHandle"+parentHandle);

		Iterator<String> itr=handles.iterator();
		while(itr.hasNext()){
			String childHandle = itr.next();
			if(!parentHandle.equals(childHandle)){
				System.out.println("childHandle"+childHandle);

				driver.switchTo().window(childHandle);
			}
		}
	}

	public String retreiveClusterNameFromJobsPage() {
		switchToNewTab();
		return chargebackEmrPageObject.selectedCluster.getText();
	}

	public List<String> getAllClusterDetails(){
		return chargebackEmrPageObject.resultsGroupedByTableClusterRows
				.stream().map( data -> data.getText())
				.collect(Collectors.toList());
	}

	public boolean isJobsFromGraphHeaderDisplayed() {
		waitExecuter.waitUntilElementPresent(chargebackEmrPageObject.chargebackResultHeader);
		return chargebackEmrPageObject.chargebackResultHeader.isDisplayed();
	}
}
