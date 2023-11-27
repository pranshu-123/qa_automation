package com.qa.workflows;

import java.util.logging.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.AddOrderPageObject;
import com.qa.pagefactory.PurchaseOrderPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;

public class PurchaseManagementWorkflow {

	private static final Logger LOGGER = Logger.getLogger(NFMLoginWorkflow.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final PurchaseOrderPageObject purchaseOrderPageObject;
	private final AddOrderPageObject addOrderPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public PurchaseManagementWorkflow(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		purchaseOrderPageObject = new PurchaseOrderPageObject(driver);
		addOrderPageObject = new AddOrderPageObject(driver);
	}

	public void selectPurchaseOrder() {
		waitExecuter.waitUntilElementPresent(purchaseOrderPageObject.purchaseOrder);
		purchaseOrderPageObject.purchaseOrder.click();
	}

	public void createNewPurchaseOrder() {
		waitExecuter.sleep(5000);
		waitExecuter.waitUntilElementPresent(purchaseOrderPageObject.vendorNoDropdown);
		waitExecuter.waitUntilElementClickable(purchaseOrderPageObject.vendorNoDropdown);
		purchaseOrderPageObject.vendorNoDropdown.click();
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.vendor.click();
		purchaseOrderPageObject.expectedDate.click();
		waitExecuter.sleep(1000);
		purchaseOrderPageObject.expectedDateIcon.click();
		purchaseOrderPageObject.calendarNextMonth.click();
		purchaseOrderPageObject.date.click();
		purchaseOrderPageObject.createNewOrder.click();
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.save.click();
	}

	public void selectOrder() {
		waitExecuter.sleep(1000);
		waitExecuter.waitUntilElementClickable(addOrderPageObject.orderDropdown);
		waitExecuter.sleep(8000);
		waitExecuter.waitUntilPageFullyLoaded();
		addOrderPageObject.orderDropdown.click();
		waitExecuter.sleep(3000);
		addOrderPageObject.orders.click();
		waitExecuter.sleep(1000);
	}

	public void addLineItem(String qty, String price) {
		purchaseOrderPageObject.addNewLineItem.get(0).click();
		purchaseOrderPageObject.itemNo.sendKeys("1");
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.item.click();
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.orderQty.clear();
		purchaseOrderPageObject.orderQty.click();
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.orderQty.sendKeys(qty);
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.purchasePrice.clear();
		purchaseOrderPageObject.purchasePrice.click();
		waitExecuter.sleep(2000);
		purchaseOrderPageObject.purchasePrice.sendKeys(price);
		purchaseOrderPageObject.lineAmount.click();
	}

	public void verifyLineAmount(Double qty, Double price) {
		Double amt =   qty * price;
		String amount = String.valueOf(amt);
		String lineAmount = purchaseOrderPageObject.lineAmount.getText();
		Assert.assertTrue(lineAmount.contains(amount));

	}

	public void saveOrder() {
		JavaScriptExecuter.scrollOnElement(driver, purchaseOrderPageObject.saveOrder);
		purchaseOrderPageObject.saveOrder.click();
	}

	public void deleteOrder() {
		JavaScriptExecuter.scrollOnElement(driver, purchaseOrderPageObject.saveOrder);
		purchaseOrderPageObject.deleteOrder.click();
		purchaseOrderPageObject.yes.click();
	}

	public void copyLineItem() {
		purchaseOrderPageObject.copyLineItems.get(0).click();
	}

	public void deleteLineItem() {
		purchaseOrderPageObject.deleteLineItems.get(0).click();
		purchaseOrderPageObject.yes.click();
	}

	public void updateLineItem() {

	}

	public void pullFromWoBom() {

	}

	public void pullFromReorderPoints() {

	}
	public void pullFromAllocation() {

	}
	public void cancelPoQuantities() {

	}

	public void generateReports() {

	}
}
