package com.qa.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorMasterPageFactory {
	
	@FindBy(xpath="//li[@aria-label='Vendor Master']//span")
	public WebElement vendorMaster;
	
	@FindBy(xpath="//dx-drop-down-box[@label='Vendor No']//div[@class='dx-dropdowneditor-icon']")
	public WebElement vendorNoDropdown;
	
	@FindBy(xpath="//dx-text-box[@label='Vendor Name*']//input[@class='dx-texteditor-input']")
	public WebElement vendorTextbox;
	
	@FindBy(xpath="//dx-select-box[@valueexpr='termsCode']//div[@class='dx-dropdowneditor-icon']")
	public WebElement termsCodeDropdown;
	
	@FindBy(xpath="//div[contains(text(),'No Terms')]")
	public WebElement noTerms;
	
	@FindBy(xpath="//div[contains(text(),'Credit Card')]")
	public WebElement creditCard;
	
	@FindBy(xpath="//dx-select-box[@valueexpr='paymentMethod']//div[@class='dx-dropdowneditor-icon']")
	public WebElement paymentDropdown;
	
	@FindBy(xpath="//div[contains(text(),'EFTN')]")
	public WebElement payment;
	
	@FindBy(xpath="(//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-save'])[last()-1]")
	public WebElement saveVendor;
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	public WebElement saveConfirmation;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-trash']")
	public WebElement deleteOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-add']")
	public WebElement createNewOrder;
	
	@FindBy(xpath="//dx-text-area[@formcontrolname='internalNotes']")
	public WebElement internalNotes;
	
	@FindBy(xpath="(//input[@aria-label='Filter cell'])[2]")
	public WebElement searchVendor;
	
	@FindBy(xpath="//dx-text-area[@formcontrolname='poNotes']")
	public WebElement poNotes;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]")
	public WebElement contactTab;
	
	@FindBy(xpath="//span[contains(text(),'Vendor')]")
	public WebElement vendorTab;

	@FindBy(xpath="//span[contains(text(),'Action')]")
	public WebElement action;
	
	@FindBy(xpath="//span[contains(text(),'Active')]")
	public WebElement active;

	@FindBy(xpath="//span[contains(text(),'InActive')]")
	public WebElement inActive;

	@FindBy(xpath="//span[contains(text(),'Retrieve')]")
	public WebElement retrieve;
	
	@FindBy(xpath="//span[contains(text(),'Add a row')]/..")
	public WebElement addContact;
	
	@FindBy(xpath="//div[@aria-label='export']")
	public WebElement export;
	
	@FindBy(xpath="//a[@title='Delete']")
	public List<WebElement> deleteLineItems;

	@FindBy(xpath="//a[@title='Copy this row']")
	public List<WebElement> copyLineItems;

	@FindBy(xpath="//div[@class='dx-datagrid-group-closed']")
	public List<WebElement> expandLineItems;
	
	@FindBy(xpath="//div[contains(text(),'Export all data to PDF')]")
	public WebElement exportPdf;
	
	@FindBy(xpath="//div[contains(text(),'Export all data to Excel')]")
	public WebElement exportExcel;
	
	@FindBy(xpath="//span[contains(text(),'Yes')]")
	public WebElement yes;
	
	@FindBy(xpath="//td[@aria-label='Expand']/../td[4]//input")
	public WebElement firstName;
	
	@FindBy(xpath="//td[@aria-label='Expand']/../td[4]")
	public WebElement firstNameTextBox;
	
	@FindBy(xpath="//td[@aria-label='Expand']/../td[6]//input")
	public WebElement lastName;
	
	@FindBy(xpath="//a[@title='Cancel']")
	public WebElement cancel;
	
	public String vendorName = "//td[contains(text(),'%s')]";
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public VendorMasterPageFactory(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

}
