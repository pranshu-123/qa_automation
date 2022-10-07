package com.qa.pagefactory.databricks.cost;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BudgetPageObject {

	@FindBy(id = "btn-new-budget")
	public WebElement newBudget;

	@FindBy(xpath = "//div//tr/td/div")
	public List<WebElement> activeBudgetTable;
	
	@FindBy(xpath = "//div[contains(text(),'actively')]/following-sibling::div//tr/th")
	public List<WebElement> activeBudgetTableHeader;

	@FindBy(xpath = "//h2[contains(text(),'Upcoming')]/following-sibling::div//tr/td")
	public List<WebElement> upcomingBudgetTable;

	@FindBy(xpath = "//h2[contains(text(),'Upcoming')]/following-sibling::div//tr/td")
	public WebElement expiredBudgetTable;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement yes;
	
	@FindBy(xpath = "//input[@type='search']")
	public WebElement search;	
	
	@FindBy(xpath = "//input[@name='name']")
	public WebElement addBudgetName;

	@FindBy(xpath = "//textarea")
	public WebElement addBudgetDescription;
	
	@FindBy(xpath = "//a[contains(text(),'Save')]")
	public WebElement addBudgetSave;
	
	@FindBy(xpath = "//a[contains(text(),'Close')]")
	public WebElement addBudgetClose;
	
	@FindBy(xpath = "//div[@class='vdp-datepicker']")
	public List<WebElement> dateWidget;
	
	@FindBy(xpath = "(//div[@class='vdp-datepicker__calendar'])[1]/span[@class='cell month']")
	public List<WebElement> futureStartDate;
	
	@FindBy(xpath = "(//div[@class='vdp-datepicker'])[2]//span[@class='cell month']")
	public List<WebElement> futureExpiryDate;
	
	@FindBy(xpath = "//div[contains(@class,'budget-value')]/input[@type='text']")
	public WebElement addBudgetDBU;
	
	@FindBy(xpath = "//label[contains(text(),'Scope')]/..//select")
	public WebElement scopeDropdown;
	
	@FindBy(xpath = "//span[@class='select2-selection select2-selection--multiple']")
	public WebElement users;
	
	@FindBy(xpath = "//li[@class='select2-results__option select2-results__option--highlighted']")
	public WebElement searchResult;
	
	@FindBy(xpath = "//label[contains(text(),'Users')]/following-sibling::div")
	public WebElement addedScope;
	
	@FindBy(xpath = "//li[@class='select2-selection__choice']")
	public WebElement editedScopeText;
		
	@FindBy(xpath = "//span[@class='text-error']")
	public List<WebElement> errorLabel;
	
	@FindBy(xpath = "//div[contains(text(),'Finance Budget')]/../following-sibling::td/div/span/b")
	public WebElement budgetStatus;
	
	public String actionButtons = "//div[contains(text(),'Finance Budget')]/../..//a/span[contains(text(),'%s')]"; //Chargeback,Trends,Optimize

	public String delete = "//div[contains(text(),'%s')]/../..//td//button[@title='Delete']";
	
	public String edit = "//div[contains(text(),'%s')]/../..//td//button[@title='Edit']";
	
	@FindBy(xpath = "//a//span[contains(text(),'Upcoming')]")
	public WebElement upcomingBudgetTab;
	
	@FindBy(xpath = "//a//span[contains(text(),'Expired')]")
	public WebElement expiredBudgetTab;
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public BudgetPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
