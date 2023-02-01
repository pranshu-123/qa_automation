package com.qa.workflows;

import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.AddOrderPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;

public class OrderManagementWorkflow {

	private static final Logger LOGGER = Logger.getLogger(NFMLoginWorkflow.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final AddOrderPageObject addOrderPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public OrderManagementWorkflow(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		addOrderPageObject = new AddOrderPageObject(driver);
	}
	
	public void selectAddOrderMasterDetail() {
		waitExecuter.waitUntilElementPresent(addOrderPageObject.addOrderMasterDetail);
		addOrderPageObject.addOrderMasterDetail.click();
	}
	
	public void validateOrderPageElements() {
		Assert.assertTrue(addOrderPageObject.AddressTab.isDisplayed());
		LOGGER.info("Address tab is displayed");
		Assert.assertTrue(addOrderPageObject.orderTab.isDisplayed());
		LOGGER.info("Order tab is displayed");
		Assert.assertTrue(addOrderPageObject.saveOrder.isDisplayed());
		LOGGER.info("Save Order button is displayed");
		Assert.assertTrue(addOrderPageObject.deleteOrder.isDisplayed());
		LOGGER.info("Delete Order button is displayed");
		Assert.assertTrue(addOrderPageObject.createNewOrder.isDisplayed());
		LOGGER.info("Create New Order button is displayed");
	}
	
	public void selectOrder() {
		waitExecuter.sleep(5000);
		waitExecuter.waitUntilElementPresent(addOrderPageObject.orderDropdown);
		addOrderPageObject.orderDropdown.click();
		waitExecuter.sleep(3000);
		addOrderPageObject.orders.click();
		waitExecuter.sleep(1000);
	}
	
	public void selectAddressTab() {
		addOrderPageObject.AddressTab.click();
	}
	
	public void validateAddressPageElements() {
		Assert.assertTrue(addOrderPageObject.customerAddress.isDisplayed());
		LOGGER.info("Customer Address section is displayed");
		Assert.assertTrue(addOrderPageObject.shippingAddress.isDisplayed());
		LOGGER.info("Shipping Address section is displayed");
	}
	
	public void provideAdvanceAmount(String amount) {
		waitExecuter.sleep(3000);
		addOrderPageObject.advanceAmount.sendKeys(amount);
	}
	
	public void saveOrder() {
		waitExecuter.sleep(3000);
		JavaScriptExecuter.scrollOnElement(driver, addOrderPageObject.saveOrder);
		addOrderPageObject.saveOrder.click();
	}
	
	public void createOrder() {
		addOrderPageObject.createNewOrder.click();
	}
	
	public void editCreatedOrder() {
		provideAdvanceAmount("3");
		createOrder();
	}

	public void deleteOrder() {
		waitExecuter.sleep(2000);
		addOrderPageObject.deleteOrder.click();
		
	}
	
	public void addNewLineItem() {
		waitExecuter.sleep(4000);
		addOrderPageObject.addNewLineItem.click();
		JavaScriptExecuter.scrollOnElement(driver, addOrderPageObject.expandLineItems.get(0));
		addOrderPageObject.expandLineItems.get(0).click();
		JavaScriptExecuter.scrollOnElement(driver, addOrderPageObject.itemNo);
		addOrderPageObject.itemNo.click();
		addOrderPageObject.itemList.click();
	}
	
	public void copyLineItem() {
		waitExecuter.sleep(1000);
		addOrderPageObject.copyLineItems.get(0).click();
		
	}
	
	public void deleteLineItem() {
		waitExecuter.sleep(1000);
		addOrderPageObject.deleteLineItems.get(0).click();
		
	}
	
	public void cancelLineItem() {
		waitExecuter.sleep(1000);
		addOrderPageObject.cancel.click();
		
	}
}
