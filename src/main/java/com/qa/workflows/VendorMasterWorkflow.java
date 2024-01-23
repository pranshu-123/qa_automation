package com.qa.workflows;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qa.pagefactory.VendorMasterPageFactory;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

public class VendorMasterWorkflow {

	private static final Logger LOGGER = Logger.getLogger(VendorMasterWorkflow.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final VendorMasterPageFactory vendorMasterPageFactory;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public VendorMasterWorkflow(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		vendorMasterPageFactory = new VendorMasterPageFactory(driver);
	}

	public void selectVendorMaster() {
		waitExecuter.waitUntilElementPresent(vendorMasterPageFactory.vendorMaster);
		vendorMasterPageFactory.vendorMaster.click();
	}

	public void createVendor(String name) {
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.sleep(12000);
		vendorMasterPageFactory.vendorTextbox.sendKeys(name);
		vendorMasterPageFactory.termsCodeDropdown.click();
		waitExecuter.sleep(2000);
		vendorMasterPageFactory.noTerms.click();
		waitExecuter.sleep(2000);
		vendorMasterPageFactory.paymentDropdown.click();
		vendorMasterPageFactory.payment.click();
	}

	public void createNewVendorMaster() {
		vendorMasterPageFactory.createNewOrder.click();
		waitExecuter.sleep(2000);
		vendorMasterPageFactory.saveConfirmation.click();
	}

	public void deleteVendorMaster() {
		vendorMasterPageFactory.deleteOrder.click();
		vendorMasterPageFactory.yes.click();
	}

	public void searchVendor(String name) {
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.sleep(12000);
		vendorMasterPageFactory.vendorNoDropdown.click();
		vendorMasterPageFactory.searchVendor.sendKeys(name);
		driver.findElement(By.xpath(String.format(vendorMasterPageFactory.vendorName, name))).click();
		waitExecuter.sleep(2000);

	}

	public void updateVendor() {
		vendorMasterPageFactory.termsCodeDropdown.click();
		waitExecuter.sleep(2000);
		vendorMasterPageFactory.creditCard.click();
		saveVendor();
	}

	public void navigateContactsTab() {
		vendorMasterPageFactory.contactTab.click();
	}

	public void navigateVendorTab() {
		vendorMasterPageFactory.vendorTab.click();
	}

	public void addCustomer(String fname, String lname) {
		vendorMasterPageFactory.firstNameTextBox.click();
		vendorMasterPageFactory.firstName.sendKeys(fname);
		vendorMasterPageFactory.lastName.sendKeys(lname);
	}

	public void saveVendor() {
		vendorMasterPageFactory.saveVendor.click();
	}

	public void deleteContact() {
		if(TestUtils.isElementDisplayed(vendorMasterPageFactory.deleteLineItems)) {
			vendorMasterPageFactory.deleteLineItems.get(0).click();
		}
	}

	public void copyContact() {
		vendorMasterPageFactory.copyLineItems.get(0).click();
	}

	public void generateReports(String type) {
		vendorMasterPageFactory.export.click();
		if(type.equalsIgnoreCase("pdf")) {
			vendorMasterPageFactory.exportPdf.click();
		}
		else {
			vendorMasterPageFactory.exportExcel.click();
		}
	}

	public void cancelCustomerAddition() {
		vendorMasterPageFactory.addContact.click();
		waitExecuter.sleep(1000);
		vendorMasterPageFactory.cancel.click();
	}

	public void activateVendor() {
		vendorMasterPageFactory.action.click();
		vendorMasterPageFactory.inActive.click();
	}

	public void validateActivation() {
		vendorMasterPageFactory.action.click();
		Assert.assertFalse(TestUtils.isElementDisplayed(vendorMasterPageFactory.inActive));
	}
	
	public void retrieveVendor() {
		vendorMasterPageFactory.retrieve.click();
	}
}
