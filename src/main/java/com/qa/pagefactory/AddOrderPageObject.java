package com.qa.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOrderPageObject {
	
	@FindBy(xpath="//span[contains(text(),'Order Entry')]")
	public WebElement addOrderMasterDetail;

	@FindBy(xpath="//span[@class = 'dx-tab-text' and contains(text(),'Order')]")
	public WebElement orderTab;

	@FindBy(xpath="//span[@class = 'dx-tab-text' and contains(text(),'Addresses')]")
	public WebElement AddressTab;

	@FindBy(xpath="//tbody[@role='presentation']//tr[contains(@class,'dx-selection')]/td[1]")
	public WebElement orderList;
	
	@FindBy(xpath="(//div[@class='dx-dropdowneditor-icon'])[1]")
	public WebElement orderDropdown;

	@FindBy(xpath="(//tbody[@role='presentation']//tr[contains(@class,'dx-data-row')])[3]/td[2]")
	public WebElement orders;

	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-add']")
	public WebElement createNewOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-save']")
	public WebElement saveOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-trash']")
	public WebElement deleteOrder;
	
	@FindBy(xpath="//div[@class='dx-item dx-toolbar-item dx-toolbar-button']//i[@class='dx-icon dx-icon-add']")
	public List<WebElement> addNewLineItem;
	
	@FindBy(xpath="//a[@title='Delete']")
	public List<WebElement> deleteLineItems;
	
	@FindBy(xpath="//a[@title='Copy this row']")
	public List<WebElement> copyLineItems;
	
	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']")
	public List<WebElement> expandLineItems;
	
	@FindBy(xpath="//textarea[@class='dx-texteditor-input']")
	public WebElement addRemarkLineItems;
	
	@FindBy(xpath="//span[contains(text(),'Yes')]")
	public WebElement yes;
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	public WebElement save;
	
	@FindBy(xpath="//span[contains(text(),'Customer Address')]")
	public WebElement customerAddress;
	
	@FindBy(xpath="//span[contains(text(),'Shipping Address')]")
	public WebElement shippingAddress;

	@FindBy(xpath="//dx-number-box[@label='Advance Amount']//div/input")
	public WebElement advanceAmount;
	
	@FindBy(xpath="//a[@title='Cancel']")
	public WebElement cancel;
	
	@FindBy(xpath="//span[contains(text(),'Action')]")
	public WebElement btnAction;
	
	@FindBy(xpath="//div[contains(text(),'Submit')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//span[contains(text(),'Reports')]")
	public WebElement btnReports;
	
	@FindBy(xpath="//div[contains(text(),'Order Details')]")
	public WebElement btnOrderDetails;

	@FindBy(xpath="//span[contains(text(),'Multiple Ship To')]")
	public WebElement multipleShipTo;
	
	@FindBy(xpath="//dx-drop-down-box[@label='Item No']//div[@class='dx-dropdowneditor-icon']")
	public WebElement itemNo;
	
	@FindBy(xpath="(//tbody[@role='presentation'])[13]//tr[4]/td[1]")
	public WebElement itemList;
	
	@FindBy(xpath="//dx-text-box[@label='Quantity']")
	public WebElement quantity;
	
	@FindBy(xpath="//dx-drop-down-box[@label='Select ship to...']//div[@class='dx-dropdowneditor-icon']")
	public WebElement selectShipTo;
	
	@FindBy(xpath="(//span[@class='dx-checkbox-icon'])[5]")
	public WebElement shipToList;
	
	@FindBy(xpath="(//span[contains(text(),'Apply')])[2]")
	public WebElement btnApply;
	
	@FindBy(xpath="//span[contains(text(),'Close')]")
	public WebElement btnClose;
	
	@FindBy(xpath="//div[@data-dx_placeholder='MM/dd/yyyy']/../following-sibling::div//div[@class='dx-dropdowneditor-icon']")
	public WebElement expectedDateIcon;

	@FindBy(xpath="//a[contains(@class,'dx-calendar-navigator-next-month')]")
	public WebElement calendarNextMonth;

	@FindBy(xpath="(//span[contains(text(),'12')])[1]")
	public WebElement date;
	
	@FindBy(xpath="//span[contains(text(),'Add to order')]")
	public WebElement btnAddToOrder;

	@FindBy(xpath="//i[@class='dx-icon dx-icon-export']")
	public WebElement exportDropdown;
	
	@FindBy(xpath="//div[contains(text(),'Export all data to Excel')]")
	public WebElement exportAllData;

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public AddOrderPageObject(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
}
