package com.qa.scripts.databricks.cost;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.pagefactory.databricks.cost.TrendsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;

public class CostTrends {
	private static final Logger LOGGER = Logger.getLogger(ChargeBackImpala.class.getName());
	private final WaitExecuter waitExecuter;
	private final TrendsPageObject trendsPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public CostTrends(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		new ChargebackImpalaPageObject(driver);
		new UserActions(driver);
		new ChargebackClusterPageObject(driver);
		new DatePicker(driver);
		trendsPageObject = new TrendsPageObject(driver);
	}

	public void validateGraphFooter(String footer) {
		waitExecuter.waitUntilElementPresent(trendsPageObject.dbuGraphHeader);
		waitExecuter.sleep(1500);
		trendsPageObject.graphFooter.stream()
		.forEach(f ->f.getText().equals(footer));
	}
	
	public void validateGeneratedGraph() {
		Assert.assertTrue(trendsPageObject.dbuGraphHeader.isDisplayed());
		Assert.assertTrue(trendsPageObject.costGraphHeader.isDisplayed());
		Assert.assertTrue(trendsPageObject.clusterGraphHeader.isDisplayed());
		trendsPageObject.generatedGraph.stream().forEach(f -> f.isDisplayed());
	}
	
	public void selectChargeback() {
		waitExecuter.sleep(3000);
		trendsPageObject.dbuChargebackButton.click();
	}
	
	public void selectOptimize() {
		trendsPageObject.dbuOptimizeButton.click();
	}
	
	public void filterSingleValue(String value) {
		waitExecuter.waitUntilElementPresent(trendsPageObject.dbuGraphHeader);
		waitExecuter.sleep(1500);
		trendsPageObject.searchTextArea.sendKeys(value);
		trendsPageObject.searchResult.click();
	}
}
