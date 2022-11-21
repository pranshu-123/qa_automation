package com.qa.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOrderPageObject {
	
	@FindBy(xpath="//span[contains(text(),'Add Order Master Detail')]")
	public WebElement addOrderMasterDetail;

	@FindBy(xpath="//span[@class = 'dx-tab-text' and contains(text(),'Order')]")
	public WebElement orderTab;

	@FindBy(xpath="//span[@class = 'dx-tab-text' and contains(text(),'Addresses')]")
	public WebElement AddressTab;

	@FindBy(xpath="(//dx-drop-down-box[@valueexpr='orderNo']//input[@class = 'dx-texteditor-input'])[1]")
	public WebElement orderList;

	@FindBy(xpath="(//tbody[@role='presentation']//tr[contains(@class,'dx-data-row')])[2]/td[1]")
	public WebElement orders;

	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-add']")
	public WebElement createNewOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-save']")
	public WebElement saveOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-trash']")
	public WebElement deleteOrder;
	
	@FindBy(xpath="//div[@class='dx-item dx-toolbar-item dx-toolbar-button']//i[@class='dx-icon dx-icon-add']")
	public WebElement addNewLineItem;
	
	@FindBy(xpath="//a[@title='Delete']")
	public List<WebElement> deleteLineItems;
	
	@FindBy(xpath="//a[@title='Copy this row']")
	public List<WebElement> copyLineItems;
	
	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']")
	public List<WebElement> expandLineItems;
	
	@FindBy(xpath="//textarea[@class='dx-texteditor-input']")
	public WebElement addRemarkLineItems;
	
	@FindBy(xpath="(//table//div[contains(@class,'dx-dropdowneditor-button')])[3]")
	public WebElement itemNo;
	
	@FindBy(xpath="(//tbody[@role='presentation'])[7]//tr[1]/td[1]")
	public WebElement itemList;
	
	@FindBy(xpath="//span[contains(text(),'Yes')]")
	public WebElement yes;
	
	@FindBy(xpath="//span[contains(text(),'Customer Address')]")
	public WebElement customerAddress;
	
	@FindBy(xpath="//span[contains(text(),'Shipping Address')]")
	public WebElement shippingAddress;

	@FindBy(xpath="//dx-number-box[@label='Advance Amount']//div/input")
	public WebElement advanceAmount;

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public AddOrderPageObject(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
}
