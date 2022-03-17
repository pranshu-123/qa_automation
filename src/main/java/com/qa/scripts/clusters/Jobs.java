package com.qa.scripts.clusters;

import com.qa.pagefactory.clusters.JobsPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 * This class contains all Jobs related  action methods
 * An implementation of the page object model can be
 * achieved by separating the abstraction of the test object and the test scripts
 */
public class Jobs {

	private static final Logger LOGGER = Logger.getLogger(ChargeBackImpala.class.getName());
	List<String> listOfAllFilterElements = new ArrayList<String>();
	private WaitExecuter waitExecuter;
	private WebDriver driver;
	private JobsPageObject jobsPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */

	public Jobs(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		jobsPageObject = new JobsPageObject(driver);
	}

	/**
	 * This method default filter present on Jobs page
	 */
	public List<String> getAllDefaultSelectedFilterElements() {
		waitExecuter.sleep(3000);
		jobsPageObject.filterInput.click();
		List<String> listOfAllDefaultFilterElements = new ArrayList<String>();
		if (jobsPageObject.defaultSelectedFilterElements.size() > 0) {
			for (WebElement e : jobsPageObject.defaultSelectedFilterElements) {
				Log.info("Filter elements: " + e.getText());
				listOfAllDefaultFilterElements.add(e.getText());
			}
		}
		return listOfAllDefaultFilterElements;
	}

	/**
	 * This method verify jobs Header on Jobs page
	 */
	public boolean verifyjobsHeaderisDisplayed() {
		System.out.println(jobsPageObject.clusterResourcesTab.getText());
		try {
			waitExecuter.waitUntilElementClickable(jobsPageObject.clusterResourcesTab);
			JavaScriptExecuter.clickOnElement(driver, jobsPageObject.clusterResourcesTab);
			waitExecuter.waitUntilElementPresent(jobsPageObject.getjobsPageHeader);
			LOGGER.info("Yarn Page Header is: " + jobsPageObject.getjobsPageHeader.getText());
		} catch (WebDriverException e) {
			LOGGER.info("click On verify jobs Header is Displayed:" + e.getStackTrace());
		}
		return false;
	}

	/**
	 * This method return the date labels displayed in x axis of the graph.
	 */
	public List<String> getGraphDateLabel(String graphName) {
		List<String> dateLabels = new ArrayList<String>();
		List<WebElement> dateLabelElements;
		if (jobsPageObject.graphXAxisDateLabels.size() < 2) {
			return dateLabels;
		}
		if (graphName.equalsIgnoreCase("Jobs")) {
			dateLabelElements = jobsPageObject.getChildElement(
					jobsPageObject.graphXAxisDateLabels.get(0), By.tagName("text"));
		} else {
			dateLabelElements = jobsPageObject.getChildElement(
					jobsPageObject.graphXAxisDateLabels.get(1), By.tagName("text"));
		}
		for (WebElement dateLabelElement : dateLabelElements) {
			dateLabels.add(dateLabelElement.getText());
		}
		return dateLabels;
	}

	/**
	 * This method verify the filter present on Jobs page
	 */
	public boolean verifyStateFilter() {

		List<String> defineListOfJobsApp = Arrays.asList("ACCEPTED", "RUNNING", "NEW");
		try {
			boolean boolYarnApp = defineListOfJobsApp.containsAll(listOfAllFilterElements);
		} catch (WebDriverException e) {
			LOGGER.info("click On verify State Filter:" + e.getStackTrace());
		}
		return false;
	}

	/**
	 * This method verify the Group By DropDown present on Jobs page
	 */
	public boolean clickOnGroupByDropDown() {
		try {
			waitExecuter.waitUntilElementPresent(jobsPageObject.groupByDropdownButton);
			waitExecuter.sleep(6000);
			jobsPageObject.groupByDropdownButton.click();
		} catch (WebDriverException e) {
			LOGGER.info("click On Group By DropDown" + e.getStackTrace());
		}
		return false;
	}

	/**
	 * This method verify the ApplicationType present on Jobs page
	 */
	public boolean selectApplicationType() {
		try {
			waitExecuter.waitUntilElementPresent(jobsPageObject.groupByAppType);
			waitExecuter.sleep(2000);
			jobsPageObject.groupByAppType.click();
		} catch (WebDriverException e) {
			LOGGER.info("click On select ApplicationType is:" + e.getStackTrace());
		}
		return false;
	}

	/**
	 * This method verify the select User present on Jobs page
	 */
	public boolean selectUser() {
		try {
			waitExecuter.waitUntilElementPresent(jobsPageObject.groupByUser);
			waitExecuter.sleep(2000);
			jobsPageObject.groupByUser.click();
		} catch (WebDriverException e) {
			LOGGER.info("click On select ApplicationType is:" + e.getStackTrace());
		}
		return false;
	}


	/**
	 * This method verify the select User present on Jobs page
	 */
	public boolean selectGroupByFilterValue(String value) {
		try {
			groupByFilterSelection(value);
		} catch (StaleElementReferenceException e) {
			LOGGER.info("click On select Group by is failed due to:" + e.getMessage());
			LOGGER.info("Retrying selection.........");
			driver.navigate().refresh();
			waitExecuter.sleep(2000);
			groupByFilterSelection(value);
		}
		return false;
	}

	public void groupByFilterSelection(String value) {
		waitExecuter.sleep(2000);
		clickOnGroupByDropDown();
		waitExecuter.sleep(2000);
		if(value.equalsIgnoreCase("user")) {
			jobsPageObject.groupByUser.click();
		}
		else if(value.equalsIgnoreCase("workspace")) {
			jobsPageObject.groupByWorkspace.click();
		}
		else if(value.equalsIgnoreCase("cluster")) {
			jobsPageObject.groupByCluster.click();
		}
		else {
			jobsPageObject.groupByTagKey.click();
		}
		waitExecuter.sleep(3000);
		LOGGER.info(value.toUpperCase() + " group selected successfully.");
	}
	/**
	 * This method verify the select Queue present on Jobs page
	 */
	public boolean selectQueue() {
		try {
			waitExecuter.waitUntilElementPresent(jobsPageObject.groupByQueue);
			waitExecuter.sleep(2000);
			jobsPageObject.groupByQueue.click();
		} catch (WebDriverException e) {
			LOGGER.info("click On select select Queue is:" + e.getStackTrace());
		}
		return false;
	}

	/**
	 * This method verify the select State present on Jobs page
	 */
	public boolean selectState() {
		try {
			waitExecuter.waitUntilElementPresent(jobsPageObject.groupByState);
			waitExecuter.sleep(2000);
			jobsPageObject.groupByState.click();
		} catch (WebDriverException e) {
			LOGGER.info("click On select select State is:" + e.getStackTrace());
		}
		return false;
	}

	/**
	 * This method verify deselect GroupBy Filters on Jobs page
	 */
	public boolean deselectGroupByFilters() {
		try {
			int countChargeBackDrill = jobsPageObject.listChargeBackDrillFromGroupByFilters.size();
			List<WebElement> listChargeBackDrill = jobsPageObject.listChargeBackDrillFromGroupByFilters;

			for (int i = countChargeBackDrill - 1; i >= 0; i--) {
				MouseActions.clickOnElement(driver, listChargeBackDrill.get(i));
				//listChargeBackDrill.get(i).click();
				waitExecuter.sleep(2000);
			}
		} catch (WebDriverException e) {
			LOGGER.info("click On select select State is:" + e.getStackTrace());
		}
		return false;

	}
}
