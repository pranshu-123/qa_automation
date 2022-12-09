package com.qa.workflows;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import com.qa.pagefactory.NFMHomepagePageObject;
import com.qa.utils.WaitExecuter;

public class NFMHomepageWorkflow {

	private static final Logger LOGGER = Logger.getLogger(NFMHomepageWorkflow.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final NFMHomepagePageObject nfmPageObject;
	static String parentHandle;
	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public NFMHomepageWorkflow(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		nfmPageObject = new NFMHomepagePageObject(driver);
	}

	public void selectAddOrder() {
		nfmPageObject.addOrderMasterDetails.click();
	}

	public void navigateToAddOrderPage() {
		Set<String> handles = driver.getWindowHandles();
		LOGGER.info("Handles" + handles);
		parentHandle = driver.getWindowHandle();
		LOGGER.info("parentHandle" + parentHandle);

		Iterator<String> itr = handles.iterator();
		while (itr.hasNext()) {
			String childHandle = itr.next();
			if (!parentHandle.equals(childHandle)) {
				LOGGER.info("childHandle" + childHandle);
				driver.switchTo().window(childHandle);
				LOGGER.info("Child windows Title: " + driver.getTitle());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				LOGGER.info(driver.getCurrentUrl());
			}
		}
		waitExecuter.sleep(5000);
	}

	public void logout() {
		driver.close();
		driver.switchTo().window(parentHandle);
		nfmPageObject.logout.click();
		driver.switchTo().alert().accept();
		waitExecuter.sleep(3000);
	}
}
