package com.qa.scripts.clusters.impala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.qa.enums.UserAction;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.utils.WaitExecuter;

public class Impala {

	private WaitExecuter waitExecuter;
	private WebDriver driver;
	private ImpalaPageObject impalaPageObject;
	private UserActions userActions;
	private final HomePage homePage;
	private static final Logger LOGGER = Logger.getLogger(Impala.class.getName());

	/**
	 * Constructer to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public Impala(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		impalaPageObject = new ImpalaPageObject(driver);
		userActions = new UserActions(driver);
		homePage = new HomePage(driver);
	}

	public Boolean isMemoryGraphPresent() {
		List<WebElement> parentMemoryNodeList = impalaPageObject.memoryGraphToggleCheckboxList;
		waitExecuter.sleep(1000);
		if (parentMemoryNodeList.size() > 0) {
			List<String> memoryNodeListText = new ArrayList<String>();
			for (int i = 0; i < parentMemoryNodeList.size(); i++) {
				String text = parentMemoryNodeList.get(i).getText();
				memoryNodeListText.add(text);
			}
			LOGGER.info("The memory graph is being displayed for nodes: " + memoryNodeListText);
			return true;
		}
		if (parentMemoryNodeList.size() <= 0) {
			List<WebElement> childList = impalaPageObject.getChildElement(impalaPageObject.parentMemoryConsumptionGraph,
					impalaPageObject.checkNoDataOnGraph);
			for (int i = 0; i < childList.size(); i++) {
				String noDataDisplayed = childList.get(i).getText();
				LOGGER.info("The data displayed in memory graph is: " + noDataDisplayed);
			}
			return true;
		} else
			return false;
	}

	public Boolean isQueryGraphPresent() {
		List<WebElement> parentQueryUserList = impalaPageObject.queryGraphToggleCheckboxList;
		waitExecuter.sleep(1000);
		if (parentQueryUserList.size() > 0) {
			List<String> queryUserListText = new ArrayList<String>();
			for (int i = 0; i < parentQueryUserList.size(); i++) {
				String text = parentQueryUserList.get(i).getText();
				queryUserListText.add(text);
			}
			LOGGER.info("The query graph is being displayed for users: " + queryUserListText);
			return true;
		}
		if (parentQueryUserList.size() <= 0) {
			List<WebElement> childList = impalaPageObject.getChildElement(impalaPageObject.parentQueryGraph,
					impalaPageObject.checkNoDataOnGraph);
			for (int i = 0; i < childList.size(); i++) {
				String noDataDisplayed = childList.get(i).getText();
				LOGGER.info("The data displayed in query graph is: " + noDataDisplayed);
			}
			return true;
		}
		return false;
	}

	/**
	 * @return This method returns the list of labels displayed under query graph
	 */
	public List<String> getQueriesGraphLabels() {
		List<String> labels = new ArrayList<String>();
		for (WebElement label : impalaPageObject.queriesFooterLabels) {
			labels.add(label.getText());
		}
		return labels;
	}

	//click on cluster drop down
	public void selectImpalaType(String resourceType) {
		// Click on Impala chargeback dropdown
		userActions.performActionWithPolling(impalaPageObject.impalaDropdownOption, UserAction.CLICK);
		List<WebElement> userList = impalaPageObject.selectType;
		String selectImpala = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getText().equals(resourceType)) {
				selectImpala = userList.get(i).getText();
				LOGGER.info("Selected Impala from dropdown " + selectImpala);
				waitExecuter.waitUntilElementClickable(userList.get(i));
				userActions.performActionWithPolling(userList.get(i), UserAction.CLICK);
				waitExecuter.sleep(2000);
			}
		}
	}

	/**
	 * This method clear the filter present on impala page
	 */
	public void clearFilter() {
		final List<WebElement> filterItems = impalaPageObject.filterRemoveElements;
		while (filterItems.size() != 0) {
			JavaScriptExecuter.clickOnElement(driver, filterItems.get(0));
			waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
			waitExecuter.sleep(1000);
		}
	}

	/**
	 * This method return the date labels displayed in x axis of the graph.
	 */
	public List<String> getGraphDateLabel(String graphName) {
		List<String> dateLabels = new ArrayList<String>();
		List<WebElement> dateLabelElements;
		if (impalaPageObject.graphXAxisDateLabels.size() < 2) {
			return dateLabels;
		}
		if (graphName.equalsIgnoreCase("memory")) {
			dateLabelElements = impalaPageObject.getChildElement(
					impalaPageObject.graphXAxisDateLabels.get(0), By.tagName("text"));
		} else {
			dateLabelElements = impalaPageObject.getChildElement(
					impalaPageObject.graphXAxisDateLabels.get(1), By.tagName("text"));
		}
		for (WebElement dateLabelElement : dateLabelElements) {
			dateLabels.add(dateLabelElement.getText());
		}
		return dateLabels;
	}

	public void selectQueueInGroupBy() {
		impalaPageObject.groupByDropdownButton.click();
		waitExecuter.sleep(2000);
		waitExecuter.waitUntilElementPresent(impalaPageObject.groupByQueueList);
		impalaPageObject.groupByQueueList.click();
	}

	public void selectUserInGroupBy() {
		waitExecuter.sleep(1000);
		try {
			impalaPageObject.groupByDropdownButton.click();
		} catch (StaleElementReferenceException e) {
			impalaPageObject.groupByDropdownButton.click();
		}
		waitExecuter.sleep(2000);
		impalaPageObject.groupByUserList.click();
	}

	/**
	 * This method is use to return all impala queries table rows.
	 *
	 * @return - impala queries table rows
	 */
	public List<WebElement> getImpalaJobsTableRecord() {
		return impalaPageObject.impalaQueriesTableRecords;
	}

	public List<WebElement> getImpalaQueriesTableHeaderColumnNames() {
		return impalaPageObject.listImpalaQueriesTableHeaderColumn;
	}

	public String getImpalaQueriesTableHeaderText() {
		String impalaQueriesHeaderText = impalaPageObject.impalaQueriesHeader.getText();
		LOGGER.info("The header value of Impala Queries table " + impalaQueriesHeaderText);
		return impalaQueriesHeaderText;
	}

	//validate column name in Impala Queries Table, i.e
	public Boolean validateHeaderColumnNameInImpalaQueriesTable() {
		LOGGER.info("Size of Headers in Impala Queries Table: " + getImpalaQueriesTableHeaderColumnNames().size());
		List<WebElement> listOfImpalaQueriesTableHeaderNames = getImpalaQueriesTableHeaderColumnNames();

		ArrayList<String> listOfImpalaQueriesColumnNames = new ArrayList<String>();

		for (int i = 0; i < listOfImpalaQueriesTableHeaderNames.size() - 1; i++) {
			listOfImpalaQueriesColumnNames.add(listOfImpalaQueriesTableHeaderNames.get(i).getText());
		}
		List<String> definedImpalaQueriesColumnNames = Arrays.asList("Type", "State", "User", "App Name / ID",
				"Start Time", "Duration", "Queue /Pool", "Peak Mem Limit per Host", "Estimated Peak Mem per Host",
				"Actual Peak Mem per Host");
		Boolean boolColumnNames = listOfImpalaQueriesColumnNames.equals(definedImpalaQueriesColumnNames);
		return boolColumnNames;
	}

	public void selecttable() {
		waitExecuter.waitUntilElementPresent(impalaPageObject.selectimpalatable);
		impalaPageObject.selectimpalatable.click();
	}

	public void verifyImpalaType(String impalaType,String clusterId) {
 		String selectedImpalaType = impalaPageObject.selectedType.getText();
 		if(!selectedImpalaType.equalsIgnoreCase(impalaType)) {
 			selectImpalaType(impalaType);
 			homePage.selectMultiClusterIdClusterPage(clusterId);
 		}	
 	}

	/*Get list of memory graph labels */
	public List<String> getMemoryLabels() {
		List<String> labels = new ArrayList<String>();
		for (WebElement label : impalaPageObject.memoryFooterLabels) {
			labels.add(label.getText());
		}
		return labels;
	}


	/**
	 * This method used to select impala in resource drowdown displayed at
	 * resource page. First it click on resource tab which navigates to
	 * resource page then it select impala.
	 */
	public void selectImpalaResource() {
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		// Click on Chargeback tab
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourcesTab);
		userActions.performActionWithPolling(impalaPageObject.resourcesTab, UserAction.CLICK);
	}
}
