package com.qa.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderPageObject {

	@FindBy(xpath="//li[@aria-label='Purchase Order']//span")
	public WebElement purchaseOrder;

	@FindBy(xpath="//dx-drop-down-box[@label='Vendor No*']//div[@class='dx-dropdowneditor-icon']")
	public WebElement vendorNoDropdown;

	@FindBy(xpath="(//td[@aria-label='Column Vendor No']/../../../../../following-sibling::div//tbody/tr/td)[1]")
	public WebElement vendor;

	@FindBy(xpath="//dx-date-box[@formcontrolname='expectedDate']")
	public WebElement expectedDate;

	@FindBy(xpath="//dx-date-box[@formcontrolname='expectedDate']//div[@class='dx-dropdowneditor-icon']")
	public WebElement expectedDateIcon;

	@FindBy(xpath="//a[contains(@class,'dx-calendar-navigator-next-month')]")
	public WebElement calendarNextMonth;

	@FindBy(xpath="(//span[contains(text(),'12')])[1]")
	public WebElement date;

	@FindBy(xpath="//tbody[@role='presentation']//tr[contains(@class,'dx-selection')]/td[1]")
	public WebElement orderList;

	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-add']")
	public WebElement createNewOrder;

	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-save']")
	public WebElement saveOrder;

	@FindBy(xpath="(//span[contains(text(),'Purchase Order')])[3]")
	public WebElement header;

	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-trash']")
	public WebElement deleteOrder;

	@FindBy(xpath="//i[@class='dx-icon dx-icon-edit-button-addrow']")
	public List<WebElement> addNewLineItem;

	@FindBy(xpath="//a[@title='Delete']")
	public List<WebElement> deleteLineItems;

	@FindBy(xpath="//a[@title='Copy this row']")
	public List<WebElement> copyLineItems;

	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']")
	public List<WebElement> expandLineItems;

	@FindBy(xpath="//a[contains(@href,'ITEM_NO')]")
	public WebElement itemNo;

	@FindBy(xpath="//dx-drop-down-box[contains(@displayexpr,'itemNo')]//div[@class='dx-dropdowneditor-icon']")
	public WebElement itemDropdown;

	@FindBy(xpath="(//div[contains(text(),'Item No')]/../../../../../../following-sibling::div//table)[3]//tr[1]/td[1]")
	public WebElement item;

	@FindBy(xpath="(//div[contains(text(),'Order Qty')]/../../../../../../following-sibling::div//table)[1]//tr[last()-1]/td//div/div/input")
	public WebElement orderQty;

	@FindBy(xpath="(//div[contains(text(),'Purchase')]/../../../../../../following-sibling::div//table)[1]//tr[last()-1]/td//div/div/input")
	public WebElement purchasePrice;

	@FindBy(xpath="(//div[contains(text(),'Line')]/../../../../../../following-sibling::div//table)[1]//tr[last()-1]/td[9]")
	public WebElement lineAmount;

	@FindBy(xpath="//span[contains(text(),'Yes')]")
	public WebElement yes;

	@FindBy(xpath="//span[contains(text(),'Save')]")
	public WebElement save;
	
	@FindBy(xpath="//span[contains(text(),'Action')]")
	public WebElement action;

	@FindBy(xpath="//span[contains(text(),'Reports')]")
	public WebElement reports;
	
	@FindBy(xpath="//div[contains(text(),'Pull from WO BOM')]")
	public WebElement pullFromWoBom;

	@FindBy(xpath="//div[contains(text(),'Pull from Reorder Points')]")
	public WebElement pullFromreorderPoints;
	
	@FindBy(xpath="//div[contains(text(),'Pull from Allocations')]")
	public WebElement pullFromAllocations;
	
	@FindBy(xpath="//div[contains(text(),'Cancel PO Quantities')]")
	public WebElement cancelPOQuantities;
	
	@FindBy(xpath="//div[contains(text(),'Create PO from SO')]")
	public WebElement createPoFromSo;
	
	@FindBy(xpath="(//div[@class='dx-dropdowneditor-icon'])[last()]")
	public WebElement cancelDropdown;
	
	@FindBy(xpath="//div[contains(text(),'Cancel Full PO')]")
	public WebElement cancelFullPO;
	
	@FindBy(xpath="//span[contains(text(),'Show Balance Lines')]")
	public WebElement showBalanceLines;
	
	@FindBy(xpath="(//span[contains(text(),'Close')])[last()]")
	public WebElement close;
	
	@FindBy(xpath="//span[contains(text(),'Process Cancellations')]")
	public WebElement processCancellation;
	

	@FindBy(xpath="(//div[@class='dx-dropdowneditor-icon'])[last()-1]")
	public WebElement workOrder;
	
	@FindBy(xpath="(//div[@class='dx-dropdowneditor-icon'])[last()]")
	public WebElement operationCode;
	
	@FindBy(xpath="(//div[contains(text(),'Workorder No')])/../../../../../../following-sibling::div//tr[5]/td[1]")
	public WebElement workorderNumber;
	
	@FindBy(xpath="//span[contains(text(),'Apply')]")
	public WebElement apply;
	
	@FindBy(xpath="(//div[contains(@class,'dx-select-checkbox')])[last()]")
	public WebElement selectWorkOrder;
	
	@FindBy(xpath="//span[contains(text(),'Add to PO Lines')]")
	public WebElement addToPoLines;
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public PurchaseOrderPageObject(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
}
