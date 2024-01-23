package com.qa.workflows;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.PackingSlipPageFactory;
import com.qa.pagefactory.VendorMasterPageFactory;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

public class PackingSlipWorkflow {

	private static final Logger LOGGER = Logger.getLogger(PackingSlipWorkflow.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final PackingSlipPageFactory packingSlipPageFactory;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public PackingSlipWorkflow(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		packingSlipPageFactory = new PackingSlipPageFactory(driver);
	}

	public void selectPackingSlip() {
		waitExecuter.waitUntilElementPresent(packingSlipPageFactory.packingSlip);
		packingSlipPageFactory.packingSlip.click();
	}

	public void selectOrder() {
		packingSlipPageFactory.orderNoDropdown.click();
		packingSlipPageFactory.orders.click();
	}

	public void validateLineItemsListed() {
		String qty = packingSlipPageFactory.ShipQty.getText();
		Assert.assertFalse(qty.isEmpty());
	}

	public String getBusinessUnitCode() {
		String code = packingSlipPageFactory.buc.getText();
		return code;
	}

	public void verifyCustomerFields() {
		String name = packingSlipPageFactory.customerName.getAttribute("aria-readonly");
		String custNo = packingSlipPageFactory.customerNoTextbox.getAttribute("aria-readonly");
		String custPoNo = packingSlipPageFactory.customerPoNo.getAttribute("aria-readonly");
		Assert.assertTrue(name.equals("true"));
		Assert.assertTrue(custNo.equals("true"));
		Assert.assertTrue(custPoNo.equals("true"));
	}

	public void verifyDifferentShipToFunctionality() {
		Assert.assertFalse(TestUtils.isElementDisplayed(packingSlipPageFactory.shipToCode));
		packingSlipPageFactory.chkDifferentShippingAddress.click();
		waitExecuter.sleep(1000);
		Assert.assertTrue(TestUtils.isElementDisplayed(packingSlipPageFactory.shipToCode));
		packingSlipPageFactory.shippingAddressInfo.click();
		Assert.assertTrue(TestUtils.isElementDisplayed(packingSlipPageFactory.lblDifferentShipingAddress));
	}

	public String getPackingSlipDefaultDate() {
		String value = packingSlipPageFactory.packingSlipDateValue.getAttribute("value");
		return value;
	}

	public void verifyRequestedAndPromisedDates() {
		String requestedDate = packingSlipPageFactory.requestedDate.getAttribute("aria-readonly");
		String promisedDate = packingSlipPageFactory.promisedShippedDate.getAttribute("aria-readonly");
		Assert.assertTrue(requestedDate.equals("true"));
		Assert.assertTrue(promisedDate.equals("true"));
	}

	public String getDeliveryConfirmationDefaultDate() {
		String value = packingSlipPageFactory.deliveryConfirmationDateValue.getAttribute("value");
		return value;
	}

	public void verifyPackingSlipStatus() {
		String value = packingSlipPageFactory.packingSlipStatusValue.getAttribute("value");
		Assert.assertTrue(value.equals("true"));
	}

	public void verifyLineItems() {
		Assert.assertTrue(packingSlipPageFactory.itemNoList.get(0).isDisplayed());
		Assert.assertTrue(packingSlipPageFactory.expandLineItems.get(0).isDisplayed());
	}

	public void verifyOrderNo() {
		Assert.assertTrue(packingSlipPageFactory.orderNoList.get(0).isDisplayed());
	}

	public void confirmPackingSlip() {
		packingSlipPageFactory.action.click();
		packingSlipPageFactory.confirmation.click();
		packingSlipPageFactory.btnConfirm.click();
	}

	public void expandLineItems() {
		waitExecuter.sleep(2000);
		packingSlipPageFactory.expandLineItems.get(0).click();
	}

	public void verifyDisabledLineItemsField() {
		String shipQtyBoum = packingSlipPageFactory.shipQtyBoum.getAttribute("aria-readonly");
		String itemRevisionNo = packingSlipPageFactory.itemRevisionNo.getAttribute("aria-readonly");
		String inventoryUom = packingSlipPageFactory.inventoryUom.getAttribute("aria-readonly");
		Assert.assertTrue(shipQtyBoum.equals("true"));
		Assert.assertTrue(itemRevisionNo.equals("true"));
		Assert.assertTrue(inventoryUom.equals("true"));
	}

	public void unConfirmPackingSlip() {
		packingSlipPageFactory.action.click();
		packingSlipPageFactory.unconfirm.click();
		packingSlipPageFactory.btnConfirm.click();
	}

	public void verifyLocationNo() {
		waitExecuter.sleep(2000);
		String locNo = packingSlipPageFactory.LocationNo.getText();
		Assert.assertFalse(locNo.isEmpty());
	}

	public void updateShipQty() {
		waitExecuter.sleep(2000);
		packingSlipPageFactory.shipQty.click();
		waitExecuter.sleep(2000);
		packingSlipPageFactory.cancelShipQty.click();
		packingSlipPageFactory.shipQtyText.sendKeys("5");
	}

	public Integer calculateSum() {
		waitExecuter.sleep(2000);
		String sum = packingSlipPageFactory.sum.getText();
		String value = sum.substring(0, sum.indexOf(' '));
		Integer i = Integer.parseInt(value);
		return i;
	}

	public void addNotes(String notes) {
		packingSlipPageFactory.shippingAddressInfo.click();
		packingSlipPageFactory.internalNotesTextArea.sendKeys(notes);
		packingSlipPageFactory.packingSlipTab.click();
	}

	public void savePakingSlip() {
		packingSlipPageFactory.savePackingSlip.click();
	}

	public void selectDifferentBillingAddress() {
		packingSlipPageFactory.chkDifferentShippingAddress.click();
	}

	public void navigateToShippingAndAddressInfo() {
		packingSlipPageFactory.shippingAddressInfo.click();
	}

	public boolean isShippingAddressDisplayed() {
		return TestUtils.isElementDisplayed(packingSlipPageFactory.lblDifferentShipingAddress);
	}
	
	public void createNewPackingSlip() {
		waitExecuter.sleep(2000);
		packingSlipPageFactory.createNewOrder.click();
	}
	
	public void deletePackingSlip() {
		waitExecuter.sleep(2000);
		packingSlipPageFactory.deleteOrder.click();
	}
	
	public void deletePackingSlipLineItems() {
		waitExecuter.sleep(2000);
		packingSlipPageFactory.deleteLineItems.get(0).click();
	}
}
