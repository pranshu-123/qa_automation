package com.qa.scripts.databricks.cost;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
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
		if(budgetPageObject.activeBudgetTable.stream().map(f -> f.getText())
				.collect(Collectors.toList()).contains(budgetName)) {
			deleteExistingBudget(budgetName);
		}
		budgetPageObject.newBudget.click();
		budgetPageObject.addBudgetName.sendKeys(budgetName);
		budgetPageObject.addBudgetDescription.sendKeys(budgetName);
		budgetPageObject.addBudgetDBU.sendKeys("1");
	}
	
	public void setBudgetActivationDate() {
		waitExecuter.sleep(1000);
		budgetPageObject.dateWidget.get(0).click();
		budgetPageObject.futureStartDate.click();
		waitExecuter.sleep(2000);
		budgetPageObject.dateWidget.get(1).click();
		budgetPageObject.futureExpiryDate.click();
	}
	
	public void saveBudget() {
		budgetPageObject.addBudgetSave.click();
	}

	public void validateCreatedBudget(String budgetName) {
		waitExecuter.sleep(2000);
		Assert.assertTrue(budgetPageObject.activeBudgetTable.stream().map(f -> f.getText())
				.collect(Collectors.toList()).contains(budgetName),"Created Budget not displayed");
	}

	public void validateUpcomingBudget(String budgetName) {
		waitExecuter.sleep(2000);
		Assert.assertTrue(budgetPageObject.upcomingBudgetTable.stream().map(f -> f.getText())
				.collect(Collectors.toList()).contains(budgetName),"Created Budget not displayed");
	}
	
	public void deleteExistingBudget(String budgetName) {
		driver.findElement(By.xpath(String.format(budgetPageObject.delete,budgetName))).click();
		budgetPageObject.yes.click();
	}

	public void editExistingBudget(String budgetName) {
		driver.findElement(By.xpath(String.format(budgetPageObject.edit,budgetName))).click();
		waitExecuter.sleep(1000);
		Select select = new Select(budgetPageObject.scopeDropdown);
		select.selectByIndex(4);
		waitExecuter.sleep(1000);
		budgetPageObject.users.click();
		budgetPageObject.users.sendKeys("smananghat@unraveldata.com");
		waitExecuter.sleep(1000);
		budgetPageObject.searchResult.click();
		waitExecuter.sleep(1000);
		budgetPageObject.addBudgetSave.click();
	}

	public void verifyBudgetPageObjects() {
		Assert.assertTrue(budgetPageObject.newBudget.isDisplayed());
		budgetPageObject.activeBudgetTable.stream().forEach(element -> element.isDisplayed());
		budgetPageObject.upcomingBudgetTable.stream().forEach(element -> element.isDisplayed());
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

	public void selectActionButton(String action) {
		waitExecuter.sleep(2000);
		driver.navigate().refresh();
		driver.findElement(By.xpath(String.format(budgetPageObject.actionButtons,action))).click();
	}

	public void verifyUpdatedScope(String scope) {
		Assert.assertTrue(budgetPageObject.addedScope.getText().contains(scope));
	}
	
	public void searchCreatedBudget(String budgetName) {
		waitExecuter.sleep(1000);
		budgetPageObject.search.sendKeys(budgetName);
		waitExecuter.sleep(1000);
	}
}
