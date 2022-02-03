package com.qa.scripts.databricks.cost;

import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChargeBackCluster {
	private static final Logger LOGGER = Logger.getLogger(ChargeBackImpala.class.getName());
	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ChargebackImpalaPageObject chargebackImpalaPageObject;
	private final UserActions userActions;
	private final ChargebackClusterPageObject chargebackClusterPageObject;

	/**
	 * Constructer to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ChargeBackCluster(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		userActions = new UserActions(driver);
		chargebackClusterPageObject = new ChargebackClusterPageObject(driver);
	}

	public void navigateToCostTab(String tab) {
		if(tab.equalsIgnoreCase("trends")) {
			chargebackClusterPageObject.costTrendsTab.click();
		}
		else if(tab.equalsIgnoreCase("chargeback")) {
			chargebackClusterPageObject.costChargeBackTab.click();
		}
		else {
			chargebackClusterPageObject.costBudgetTab.click();
		}
	}

	public List<String> fetchAllGroupByFilterValues(){
		chargebackClusterPageObject.groupBy.click();
		List<String> list =chargebackClusterPageObject.groupByValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		return list;

	}

	public void validatePieChartGraph() {
		chargebackClusterPageObject.graphsHeader.stream()
		.forEach(graph -> graph.isDisplayed());
		chargebackClusterPageObject.pieGraph.stream()
		.forEach(graph -> graph.isDisplayed());
	}

	public void validateGeneratedPieChartValues() {
		List<String> pieChartValues = chargebackClusterPageObject.pieChartValues.stream()
				.map(values -> values.getText()).distinct().skip(1)
				.collect(Collectors.toList());

		List<String> resultSet =chargebackClusterPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		for(String s : pieChartValues) {
			Assert.assertTrue(resultSet.contains(s));
		}
	}

	public void validateResultSet(String[] str) {
		List<String> list =chargebackClusterPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		for(String s : str) {
			Assert.assertTrue(list.contains(s));
		}
	}

	public void validateResultSetIsDisplayedWithValues() {
		chargebackClusterPageObject.user.isDisplayed();
		chargebackClusterPageObject.dbu.isDisplayed();
		chargebackClusterPageObject.cost.isDisplayed();
		chargebackClusterPageObject.clusterCount.isDisplayed();
		chargebackClusterPageObject.resultSetValues.stream().iterator().next().isDisplayed();
	}
	
	public void selectOptimize() {
		chargebackClusterPageObject.optimize.click();
	}
	
	
}


