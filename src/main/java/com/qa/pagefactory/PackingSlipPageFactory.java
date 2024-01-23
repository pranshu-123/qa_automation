package com.qa.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PackingSlipPageFactory {
	
	@FindBy(xpath="//li[@aria-label='Packing Slip']//span")
	public WebElement packingSlip;
	
	@FindBy(xpath="//dx-drop-down-box[@label='Packing Slip No']//div[@class='dx-dropdowneditor-icon']")
	public WebElement packingSlipNoDropdown;
	
	@FindBy(xpath="(//dx-drop-down-box[@label='Order No*']//div[@class='dx-dropdowneditor-icon'])[1]")
	public WebElement orderNoDropdown;
	
	@FindBy(xpath="(//tbody[@role='presentation']//tr[contains(@class,'dx-data-row')])[1]/td[2]")
	public WebElement orders;
	
	@FindBy(xpath="//dx-select-box[@label='Ship From']//div[@class='dx-dropdowneditor-icon']")
	public WebElement shipFromDropdown;
	
	@FindBy(xpath="//dx-text-box[@label='Customer No*']//input[@class='dx-texteditor-input']")
	public WebElement customerNoTextbox;
	
	@FindBy(xpath="(//div[@data-dx_placeholder='MM/dd/yyyy']/../following-sibling::div//div[@class='dx-dropdowneditor-icon'])[1]")
	public WebElement packingSlipDate;

	@FindBy(xpath="//dx-text-box[@formcontrolname='businessUnitCode']//input")
	public WebElement buc;
	
	@FindBy(xpath="//dx-select-box[@valueexpr='shipVia']//div[@class='dx-dropdowneditor-icon']")
	public WebElement shipViaDropdown;
	
	@FindBy(xpath="//dx-text-box[@formcontrolname='name']//input")
	public WebElement customerName;
	
	@FindBy(xpath="//dx-text-box[@formcontrolname='customerPoNo']//input")
	public WebElement customerPoNo;
	
	@FindBy(xpath="//dx-check-box[@formcontrolname='differentShippingAddress']//span")
	public WebElement chkDifferentShippingAddress;
	
	@FindBy(xpath="//dx-drop-down-box[@formcontrolname='shiptoCode']/div/div//input")
	public WebElement shipToCode;
	
	@FindBy(xpath="(//dx-date-box[@formcontrolname='requestedDate']//input)[2]")
	public WebElement requestedDate;
	
	@FindBy(xpath="//dx-date-box[@formcontrolname='packingSlipDate']/div/input")
	public WebElement packingSlipDateValue;
	
	@FindBy(xpath="//dx-text-box[@formcontrolname='packingSlipStatus']//input")
	public WebElement packingSlipStatusValue;
	
	@FindBy(xpath="(//dx-date-box[@formcontrolname='promisedShipDate']//input)[2]")
	public WebElement promisedShippedDate;
	
	@FindBy(xpath="(//div[@data-dx_placeholder='MM/dd/yyyy']/../following-sibling::div//div[@class='dx-dropdowneditor-icon'])[2]")
	public WebElement finalInspectionDate;

	@FindBy(xpath="(//div[@data-dx_placeholder='MM/dd/yyyy']/../following-sibling::div//div[@class='dx-dropdowneditor-icon'])[3]")
	public WebElement deliveryConfirmationDate;
	
	@FindBy(xpath="//dx-date-box[@formcontrolname='deliveryConfirmationDate']/div/input")
	public WebElement deliveryConfirmationDateValue;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-save']")
	public WebElement savePackingSlip;
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	public WebElement saveConfirmation;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-trash']")
	public WebElement deleteOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-add']")
	public WebElement createNewOrder;
	
	@FindBy(xpath="//span[contains(text(),'Action')]")
	public WebElement action;
	
	@FindBy(xpath="(//span[contains(text(),'Confirm')])[last()]")
	public WebElement btnConfirm;
	
	@FindBy(xpath="//div[contains(text(),'Confirmation')]")
	public WebElement confirmation;

	@FindBy(xpath="//div[contains(text(),'UnConfirm')]")
	public WebElement unconfirm;
	
	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']/../../td[8]")
	public WebElement ShipQty;
	
	@FindBy(xpath="//a[@title='Delete']")
	public List<WebElement> deleteLineItems;

	@FindBy(xpath="//a[@title='Copy this row']")
	public List<WebElement> copyLineItems;

	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']")
	public List<WebElement> expandLineItems;
	
	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']/../..//a[contains(@href,'ITEM_NO')]")
	public List<WebElement> itemNoList;
	
	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']/../../td[2]")
	public List<WebElement> orderNoList;
	
	@FindBy(xpath="//dx-text-box[@formcontrolname='shipQtyBaseUom']//input")
	public WebElement shipQtyBoum;
	
	@FindBy(xpath="//dx-text-box[@formcontrolname='itemRevisionNo']//input")
	public WebElement itemRevisionNo;
	
	@FindBy(xpath="((//td[@aria-label='Column Location No']/../../../../../..//div/table)[2]//td[6])[1]")
	public WebElement LocationNo;
	
	@FindBy(xpath="((//td[@aria-label='Column Location No']/../../../../../..//div/table)[2]//td[8])[1]")
	public WebElement shipQty;
	
	@FindBy(xpath="(((//td[@aria-label='Column Location No']/../../../../../..//div/table)[2]//td[8])[1]//input)[2]")
	public WebElement shipQtyText;
	
	@FindBy(xpath="//div[contains(text(),'Sum')]")
	public WebElement sum;
	
	@FindBy(xpath="//dx-text-box[@formcontrolname='inventoryUom']//input")
	public WebElement inventoryUom;
	
	@FindBy(xpath="(//span[@class='dx-icon dx-icon-clear'])[last()]")
	public WebElement cancelShipQty;
	
	@FindBy(xpath="//div[contains(text(),'Export all data to PDF')]")
	public WebElement exportPdf;
	
	@FindBy(xpath="//div[contains(text(),'Export all data to Excel')]")
	public WebElement exportExcel;
	
	@FindBy(xpath="//span[contains(text(),'Yes')]")
	public WebElement yes;
	
	@FindBy(xpath="//span[contains(text(),'Shipping and Address Info')]")
	public WebElement shippingAddressInfo;
	
	@FindBy(xpath="(//span[contains(text(),'Packing Slip')])[2]")
	public WebElement packingSlipTab;
	
	@FindBy(xpath="//span[contains(text(),'Different Shipping Address')]")
	public WebElement lblDifferentShipingAddress;
	
	@FindBy(xpath="//dx-text-area[@formcontrolname='internalNotes']//textarea")
	public WebElement internalNotesTextArea;
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public PackingSlipPageFactory(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

}
