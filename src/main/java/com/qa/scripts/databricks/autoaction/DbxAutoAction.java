package com.qa.scripts.databricks.autoaction;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.qa.pagefactory.alerts.AutoActionsPageObject;
import com.qa.pagefactory.alerts.NewAutoActionPolicyPageObject;
import com.qa.pagefactory.databricks.autoaction.DBXAutoActionPageObject;
import com.qa.pagefactory.databricks.autoaction.DBXAutoActionPolicyPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

/**
 * @author bhavs
 *
 */
public class DbxAutoAction {

	private WebDriver driver;
	private WaitExecuter waitExecuter;
	private AutoActionsPageObject autoActionsPageObject;
	private NewAutoActionPolicyPageObject newAutoActionPolicyPageObject;
	private DBXAutoActionPageObject dbxAutoActionPageObject;
	private DBXAutoActionPolicyPageObject dbxAutoActionPolicyPageObject;

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DbxAutoAction.class.getName());

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public DbxAutoAction(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		autoActionsPageObject = new AutoActionsPageObject(driver);
		newAutoActionPolicyPageObject = new NewAutoActionPolicyPageObject(driver);
		dbxAutoActionPageObject = new DBXAutoActionPageObject(driver);
		dbxAutoActionPolicyPageObject = new DBXAutoActionPolicyPageObject(driver);
	}

	public void navigateToAutoAction() {
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.sleep(3000);
		waitExecuter.waitUntilElementClickable(dbxAutoActionPageObject.autoActionTab);
		dbxAutoActionPageObject.autoActionTab.click();
	}

	public void navigateToNewTemplate() {
		dbxAutoActionPageObject.newTemplate.click();
	}

	public void validateAutoActionsPageElements() {
		Assert.assertTrue(dbxAutoActionPageObject.newTemplate.isDisplayed());
		Assert.assertTrue(dbxAutoActionPageObject.edit.get(0).isDisplayed());
		Assert.assertTrue(dbxAutoActionPageObject.delete.get(0).isDisplayed());
		Assert.assertTrue(dbxAutoActionPageObject.copy.get(0).isDisplayed());
		Assert.assertTrue(dbxAutoActionPageObject.createdBy.isDisplayed());
		LOGGER.info("Page elements are displyed");
	}

	public void validateResultSet(String policyName) {
		waitExecuter.sleep(1000);
		List<String> rows =dbxAutoActionPageObject.resultSetRows.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		Assert.assertTrue(rows.size()>0);
		LOGGER.info("Resultant table shows data");
		List<String> data = dbxAutoActionPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		Assert.assertTrue(data.contains(policyName),"Policy is not listed");
		LOGGER.info(policyName + " policy name is present");
	}

	public void navigateToTemplatePage() {
		dbxAutoActionPageObject.newTemplate.click();
	}

	public void selectLongRunningJobs() {
		dbxAutoActionPageObject.longRunningJobs.click();
	}

	public void selectAllPurposeJob() {
		dbxAutoActionPageObject.allPurposeJobs.click();
	}

	public void validateNewTemplatePage() {
		Assert.assertTrue(dbxAutoActionPageObject.longRunningJobs.isDisplayed());
		Assert.assertTrue(dbxAutoActionPageObject.allPurposeJobs.isDisplayed());
	}

	public void editAndValidateJob(String policyName) {
		waitExecuter.sleep(2000);
		dbxAutoActionPageObject.edit.get(0).click();
		waitExecuter.sleep(1000);
		dbxAutoActionPolicyPageObject.policyName.clear();
		dbxAutoActionPolicyPageObject.policyName.sendKeys("Edited_"+policyName);
		JavaScriptExecuter.scrollOnElement(driver, dbxAutoActionPolicyPageObject.saveBtn);
		dbxAutoActionPolicyPageObject.saveBtn.click();
		waitExecuter.sleep(1000);
		Assert.assertTrue(dbxAutoActionPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList()).contains("Edited_"+policyName), "Policy not edited successfully");
		waitExecuter.sleep(1000);
		dbxAutoActionPageObject.delete.get(0).click();
	}

	public String fetchPolicyName() {
		return dbxAutoActionPageObject.resultSetValues.get(0).getText();
	}

	public void deleteJob(String policyName) {
		waitExecuter.sleep(2000);
		dbxAutoActionPageObject.delete.get(0).click();
		waitExecuter.sleep(2000);
		Assert.assertFalse(dbxAutoActionPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList()).contains(policyName),"Policy not deleted");
		LOGGER.info(policyName + " deleted");
	}

	public void activateAndValidateJob(String policyName) {
		dbxAutoActionPageObject.inactive.click();
		waitExecuter.sleep(1000);
		dbxAutoActionPageObject.activeTab.click();
		Assert.assertTrue(dbxAutoActionPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList()).contains(policyName), "Policy not activated successfully");
	}

	public void deActivateAndValidateJob(String policyName) {
		dbxAutoActionPageObject.activeAALists.get(0).click();
		validateInactiveJob(policyName);
	}

	public void validateInactiveJob(String policyName) {
		dbxAutoActionPageObject.inactiveTab.click();
		Assert.assertTrue(dbxAutoActionPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList()).contains(policyName), "Policy not deactivated successfully");
	}

	public void enterNewAutoActionPolicyDetails(String policyName, String triggerValue){
		waitExecuter.sleep(1000);
		newAutoActionPolicyPageObject.policyName.clear();
		newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
		waitExecuter.sleep(2000);
	}

	public void enterNewAutoActionPolicyDetails(String policyName, String triggerValue, String desc){
		newAutoActionPolicyPageObject.policyName.clear();
		waitExecuter.sleep(1000);
		newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
		newAutoActionPolicyPageObject.policyDescription.sendKeys(desc);
		waitExecuter.sleep(2000);
	}

	public void clearPreFilledName() {
		newAutoActionPolicyPageObject.policyName.clear();
		waitExecuter.sleep(1000);
	}

	public void saveAA() {
		JavaScriptExecuter.scrollOnElement(driver, dbxAutoActionPolicyPageObject.saveBtn);
		dbxAutoActionPolicyPageObject.saveBtn.click();
	}

	public void removeTriggerConditions() {
		dbxAutoActionPolicyPageObject.listOfCrosses.get(0).click();
		driver.navigate().refresh();
	}

	public String fetchMessage() {
		return dbxAutoActionPolicyPageObject.fatalMsgTextEle.getText();
	}

	public void selectRefinedScope(String scope) {
		dbxAutoActionPolicyPageObject.refineScopeBtn.click();
		for(WebElement el : dbxAutoActionPolicyPageObject.selectRefineScopeList) {
			if(el.getText().equalsIgnoreCase(scope)) {
				el.click();
				break;
			}
		}
	}

	public void selectScopeType(String scope,String type) {
		waitExecuter.sleep(2000);
		if(type.equalsIgnoreCase("all")) {
			driver.findElement(By.xpath(String.format(dbxAutoActionPolicyPageObject.allUserChkBox, scope))).click();
		}
		else if(type.equalsIgnoreCase("except")) {
			driver.findElement(By.xpath(String.format(dbxAutoActionPolicyPageObject.exceptUserChkBox, scope))).click();
		}
		else if(type.equalsIgnoreCase("only")){
			driver.findElement(By.xpath(String.format(dbxAutoActionPolicyPageObject.onlyUserChkBox, scope))).click();
		}
		else if(type.equalsIgnoreCase("always")){
			driver.findElement(By.xpath(String.format(dbxAutoActionPolicyPageObject.alwaysChkBox, scope))).click();
		}
		else {
			driver.findElement(By.xpath(String.format(dbxAutoActionPolicyPageObject.dailyChkBox, scope))).click();
		}
	}

	public void updateTime() {
		dbxAutoActionPolicyPageObject.startEndList.get(0).click();
		Select select = new Select(dbxAutoActionPolicyPageObject.hoursList.get(0));
		select.selectByIndex(5);
		dbxAutoActionPolicyPageObject.startEndList.get(0).click();
	}

	public void sendEmail(String email) {
		JavaScriptExecuter.scrollOnElement(driver, dbxAutoActionPolicyPageObject.actionEle);
		dbxAutoActionPolicyPageObject.actionEle.sendKeys(email);
		dbxAutoActionPolicyPageObject.addEmailEleBtn.click();
	}

	public void selectActionType(String actionType) {
		JavaScriptExecuter.scrollOnElement(driver, dbxAutoActionPolicyPageObject.actionButton);
		dbxAutoActionPolicyPageObject.actionButton.click();
		for(WebElement el : dbxAutoActionPolicyPageObject.selectActionList) {
			if(el.getText().equalsIgnoreCase(actionType)) {
				el.click();
				break;
			}
		}
	}

	public void selectTriggerCondition(String condition) {
		dbxAutoActionPolicyPageObject.actionCondition.click();
		for(WebElement el : dbxAutoActionPolicyPageObject.actionConditionList) {
			if(el.getText().equalsIgnoreCase(condition)) {
				el.click();
				break;
			}
		}
	}

	public void createInactiveJob() {
		dbxAutoActionPolicyPageObject.isEnabledCheckbox.click();
	}

	public void setTotalDurationValue(String metric, String value) {
		if(metric.equalsIgnoreCase("duration")) {
			dbxAutoActionPolicyPageObject.listOfCrosses.get(1).click();
		}
		else {
			dbxAutoActionPolicyPageObject.listOfCrosses.get(0).click();	
		}
		dbxAutoActionPolicyPageObject.totalValue.clear();
		dbxAutoActionPolicyPageObject.totalValue.sendKeys(value);
	}

	public Boolean validateHistoryPageForAutoAction(String policy) {
		Boolean flag = false;
		policy = dbxAutoActionPageObject.JobName.getText();
		dbxAutoActionPageObject.JobName.click();
		List<String> historyList = dbxAutoActionPolicyPageObject.jobHistoryListData.stream()
				.map(data -> data.getText()).distinct()
				.collect(Collectors.toList());
		waitExecuter.sleep(1000);
		Assert.assertTrue(TestUtils.isElementDisplayed(dbxAutoActionPolicyPageObject.historyPageHeader),"Does not navigated to History Page");
		if(historyList.contains(policy) || historyList.contains("--")) {
			flag = true;
		}
		return flag;
	}

	public void close() {
		dbxAutoActionPolicyPageObject.close.click();
	}
}

