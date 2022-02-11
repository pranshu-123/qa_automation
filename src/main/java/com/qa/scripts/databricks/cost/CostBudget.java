package com.qa.scripts.databricks.cost;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.databricks.cost.BudgetPageObject;
import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;

public class CostBudget {

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ChargebackImpalaPageObject chargebackImpalaPageObject;
	private final ChargebackClusterPageObject chargebackClusterPageObject;
	private final DatePicker datePicker;
	private final BudgetPageObject budgetPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public CostBudget(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		chargebackClusterPageObject = new ChargebackClusterPageObject(driver);
		datePicker = new DatePicker(driver);
		budgetPageObject = new BudgetPageObject(driver);
	}

	public void createNewBudget(String budgetName) {
		waitExecuter.waitUntilElementClickable(budgetPageObject.newBudget);
		budgetPageObject.newBudget.click();
		budgetPageObject.addBudgetName.sendKeys(budgetName);
		budgetPageObject.addBudgetDescription.sendKeys(budgetName);
		budgetPageObject.addBudgetDBU.sendKeys("1");
		budgetPageObject.addBudgetSave.click();

	}

	public void validateCreatedBudget(String budgetName) {
		Assert.assertTrue(budgetPageObject.activeBudgetTable.stream().map(f -> f.getText())
				.collect(Collectors.toList()).contains(budgetName),"Created Budget not displayed");
	}

	public void deleteExistingBudget(String budgetName) {
		driver.findElement(By.xpath(String.format(budgetPageObject.delete,budgetName))).click();
		budgetPageObject.yes.click();
	}

	public void verifyBudgetPageObjects() {
		Assert.assertTrue(budgetPageObject.newBudget.isDisplayed());
		budgetPageObject.activeBudgetTable.stream().forEach(element -> element.isDisplayed());
		Assert.assertTrue(budgetPageObject.upcomingBudgetTable.isDisplayed());
		Assert.assertTrue(budgetPageObject.expiredBudgetTable.isDisplayed());
	}
	
	public void verifyBudgetActiveSectionColumns(String[] columnHeader) {
		List<String> list = budgetPageObject.activeBudgetTableHeader.stream().distinct()
		.map(header -> header.getText())
		.collect(Collectors.toList());
		
		for(String header : columnHeader) {
			Assert.assertTrue(list.contains(header),header + " column missing.");
		}
	}
}
